// 活动汇总
Ext.ns('Q.repAct');
var dealUrl = path + '/RepActivity';
Q.loadJs(path + "/js/actment/activity/Q.deptSelectForRep.js");

Q.repAct.selStores = {
    // 大区
    bigAreaStore : new Ext.data.JsonStore({
        url : path + "/Org_getOrgJson.action?filter_EQ_type=3&filter_EQ_isValid=1",
        fields : [ "orgId", "name" ],
        autoLoad : true
    }),
    // 区域
    areaStore : new Ext.data.JsonStore({
        url : path + "/Org_getOrgJson.action?filter_EQ_type=4&filter_EQ_isValid=1",
        fields : [ "orgId", "name" ],
        autoLoad : false
    }),
    // 活动类型
    actType : new Ext.data.JsonStore({
        url : path + "/Dict_selDict.action?dictCode=6100",
        fields : [ "name", "value", "dictId" ],
        autoLoad : true
    }),
    // 活动形式
    formAct : new Ext.data.JsonStore({
        url : path + "/Dict_selDictByParentId.action",
        fields : [ "name", "value", "dictId" ],
        autoLoad : false
    })
};

// 列表
var gridColumn = [ {
    header : "活动单号",
    width : 110,
    sortable : false,
    dataIndex : "activityNo"
}, {
    header : "活动主题",
    width : 200,
    sortable : false,
    dataIndex : "activityTheme"
}, {
    header : "活动类型",
    width : 100,
    sortable : false,
    dataIndex : "activityTypeName"
}, {
    header : "活动形式",
    width : 100,
    sortable : false,
    dataIndex : "activityFormName"
}, {
    header : "发起部门",
    width : 150,
    sortable : false,
    dataIndex : "orgName"
}, {
    header : "开始日期",
    width : 90,
    sortable : false,
    dataIndex : "beginTime",
    renderer : Q.common.dateRenderer
}, {
    header : "结束日期",
    width : 90,
    sortable : false,
    dataIndex : "endTime",
    renderer : Q.common.dateRenderer
}, {
    header : "费用",
    width : 100,
    sortable : false,
    dataIndex : "auditCost"
}, {
    header : "参与人数",
    width : 100,
    sortable : false,
    dataIndex : "joinCount"
}, {
    header : "邀请人数",
    width : 100,
    sortable : false,
    dataIndex : "totalCount"
}, {
    header : "累计珠宝折算额",
    width : 120,
    sortable : false,
    dataIndex : "jewelDiscountAmount"
}, {
    header : "活动产出总金额",
    width : 120,
    sortable : false,
    dataIndex : "actualCost"
}, {
    header : "费用率",
    width : 80,
    sortable : false,
    dataIndex : "costPercent"
}, {
    header : "消费人数",
    width : 100,
    sortable : false,
    dataIndex : "xfCount"
} ];

Q.repAct.tbarBnt = [ "-", {
    index : 6,
    xtype : "textfield",
    emptyText : "活动主题 ",
    id : "activityTheme",
    width : 150
}, {
    id : "activityType",
    index : 7,
    xtype : "uxcombo",
    emptyText : "活动类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.repAct.selStores.actType,
    width : 120,
    listeners : {
        "select" : function(c, r, i) {
            Ext.getCmp("activityForm").reset();
            Q.repAct.selStores.formAct.removeAll();
            Q.repAct.selStores.formAct.baseParams = {
                "parentId" : r.get("dictId")
            };
            Q.repAct.selStores.formAct.load();
        }
    }
}, {
    id : "activityForm",
    index : 8,
    xtype : "uxcombo",
    emptyText : "活动形式",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.repAct.selStores.formAct,
    width : 120
}, {
    id : "orgCode",
    name : "orgCode",
    xtype : 'hidden'
}, {
    id : "orgName",
    index : 10,
    xtype : "textfield",
    emptyText : "发起部门（门店）",
    width : 150,
    listeners : {
        render : function(p) {
            p.getEl().on('mouseover', function(p1) {
                if (!p.getValue()) {
                    Ext.QuickTips.unregister(p.el);
                } else {
                    Ext.QuickTips.register({
                        target : p.el,
                        text : p.getValue()
                    });
                }
            });
            p.getEl().on('click', function(p) {
                var _win = new Q.activity.chooseOrgWin({
                    singleSelect : false,
                    orgCodes : Ext.getCmp('orgCode').getValue()
                });
                _win.on("submit", function(r) {
                    var nameArr = [];
                    var codeArr = [];
                    Q.each(r, function() {
                        codeArr.push(this.data.orgCode);
                        nameArr.push(this.data.name);
                    });
                    Ext.getCmp('orgName').setValue(nameArr.join(","));
                    Ext.getCmp('orgCode').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    index : 11,
    xtype : "datefield",
    emptyText : "开始日期",
    format : 'Y-m-d',
    id : "GE_beginTime",
    width : 100,
    listeners : {
        select : function() {
            var GE_beginTime = Ext.getCmp('GE_beginTime').getValue();
            var LE_beginTime = Ext.getCmp("LE_beginTime").getValue();
            if (GE_beginTime && LE_beginTime) {
                if (LE_beginTime < GE_beginTime) {
                    Q.warning("开始日期不能大于结束日期");
                    Ext.getCmp('GE_beginTime').setValue("");
                }
            }
        }
    }
}, {
    index : 12,
    xtype : "datefield",
    emptyText : "至",
    id : "LE_beginTime",
    format : 'Y-m-d',
    width : 100,
    listeners : {
        select : function() {
            var GE_beginTime = Ext.getCmp('GE_beginTime').getValue();
            var LE_beginTime = Ext.getCmp("LE_beginTime").getValue();
            if (GE_beginTime && LE_beginTime) {
                if (LE_beginTime < GE_beginTime) {
                    Q.warning("结束日期不能小于开始日期");
                    Ext.getCmp('LE_beginTime').setValue("");
                }
            }
        }
    }
}, {
    index : 13,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var GEbeginTime = Ext.util.Format.date(Ext.getCmp('GE_beginTime').getValue(), 'Y-m-d');
        var LEbeginTime = Ext.util.Format.date(Ext.getCmp('LE_beginTime').getValue(), 'Y-m-d');
        var activityTheme = Ext.getCmp('activityTheme').getValue();
        var activityType = Ext.getCmp('activityType').getValue();
        var activityForm = Ext.getCmp('activityForm').getValue();
        var orgCode = Ext.getCmp('orgCode').getValue();

        if (!GEbeginTime && !LEbeginTime && !activityTheme && !activityType && !activityForm && !orgCode) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }

        var _store = vp.grid.getStore();
        _store.baseParams = {
            filter_GE_beginTime : GEbeginTime,
            filter_LE_beginTime : LEbeginTime,
            filter_LIKE_activityTheme : activityTheme,
            filter_EQ_activityType : activityType,
            filter_EQ_activityForm : activityForm,
            filter_IN_orgCode : orgCode
        };
        _store.load();
    }
}, {
    index : 14,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();
        var GEbeginTime = Ext.getCmp('GE_beginTime').getValue();
        var LEbeginTime = Ext.getCmp('LE_beginTime').getValue();
        var activityTheme = Ext.getCmp('activityTheme').getValue();
        var activityType = Ext.getCmp('activityType').getValue();
        var activityForm = Ext.getCmp('activityForm').getValue();
        var orgCode = Ext.getCmp('orgCode').getValue();

        if (!GEbeginTime && !LEbeginTime && !activityTheme && !activityType && !activityForm && !orgCode) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        } else if (totalCount == 0) {
            Q.tips(Q.color("请先查询出结果在进行导出", "red"));
            return;
        } else if (totalCount > 5000) {
            Q.tips(Q.color("每次导出上限为5000", "red"));
            return;
        }
        window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(vp.grid.getStore().baseParams));
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '活动汇总查询',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.repAct.tbarBnt,
        viewConfig : {
            forceFit : false
        },
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        listEditStateFn : [],
        store : {
            url : dealUrl + '_getJson.action',
            autoLoad : false
        }
    }
};