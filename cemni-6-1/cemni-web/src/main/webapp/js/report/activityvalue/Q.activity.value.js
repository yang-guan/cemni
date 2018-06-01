// 活动价值分析
Ext.ns('Q.actValue');
var dealUrl = path + '/ActivityValue';
Q.loadJs(path + "/js/actment/activity/Q.deptSelectForRep.js");

Q.actValue.selStores = {
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
    header : "活动门店",
    width : 170,
    sortable : false,
    dataIndex : "storeName"
}, {
    header : "活动起止时间",
    width : 145,
    sortable : false,
    dataIndex : "period"
}, {
    header : "销售件数",
    width : 60,
    sortable : false,
    dataIndex : "goodsCnt"
}, {
    header : "金额",
    width : 65,
    sortable : false,
    dataIndex : "actuaSaleAmount"
}, {
    header : "新会员人数",
    width : 70,
    sortable : false,
    dataIndex : "newMemberNum"
}, {
    header : "老会员人数",
    width : 70,
    sortable : false,
    dataIndex : "oldMemberNum"
}, {
    header : "fans产生人数",
    width : 85,
    sortable : false,
    dataIndex : "fansMemberNum"
}, {
    header : "总人数",
    width : 60,
    sortable : false,
    dataIndex : "totalNum"
}, {
    header : "成本",
    width : 50,
    sortable : false,
    dataIndex : "actualCost"
}, {
    header : "投入产生比",
    width : 75,
    sortable : false,
    dataIndex : "ratio"
}, {
    header : "累计珠宝折算额",
    width : 100,
    sortable : false,
    dataIndex : "jewelDiscountAmount"
}, {
    header : "人均成本（总人数）",
    width : 120,
    sortable : false,
    dataIndex : "costTotalNum"
}, {
    header : "人均成本（fans人数）",
    width : 130,
    sortable : false,
    dataIndex : "fansTotalNum"
} ];

Q.actValue.tbarBnt = [ "-", {
    index : 6,
    xtype : "textfield",
    emptyText : "主推商品名称 ",
    id : "goodsClassHighesNo",
    width : 150
}, {
    id : "activityType",
    index : 7,
    xtype : "uxcombo",
    emptyText : "活动类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.actValue.selStores.actType,
    width : 120,
    listeners : {
        "select" : function(c, r, i) {
            Ext.getCmp("activityForm").reset();
            Q.actValue.selStores.formAct.removeAll();
            Q.actValue.selStores.formAct.baseParams = {
                "parentId" : r.get("dictId")
            };
            Q.actValue.selStores.formAct.load();
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
    store : Q.actValue.selStores.formAct,
    width : 120
}, {
    id : "orgCode",
    name : "orgCode",
    xtype : 'hidden'
}, {
    id : "orgName",
    index : 10,
    xtype : "textfield",
    emptyText : "活动门店",
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
        var _store = vp.grid.getStore();
        var GEbeginTime = Ext.util.Format.date(Ext.getCmp('GE_beginTime').getValue(), 'Y-m-d');
        var LEbeginTime = Ext.util.Format.date(Ext.getCmp('LE_beginTime').getValue(), 'Y-m-d');
        var goodsClassHighesNo = Ext.getCmp('goodsClassHighesNo').getValue();
        var activityType = Ext.getCmp('activityType').getValue();
        var activityForm = Ext.getCmp('activityForm').getValue();
        var orgCode = Ext.getCmp('orgCode').getValue();

        if (!GEbeginTime && !LEbeginTime && !goodsClassHighesNo && !activityType && !activityForm && !orgCode) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }

        _store.baseParams = {
            filter_GE_beginTime : GEbeginTime,
            filter_LE_beginTime : LEbeginTime,
            filter_LIKE_goodsClassHighesNo : goodsClassHighesNo,
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
        var goodsClassHighesNo = Ext.getCmp('goodsClassHighesNo').getValue();
        var activityType = Ext.getCmp('activityType').getValue();
        var activityForm = Ext.getCmp('activityForm').getValue();
        var orgCode = Ext.getCmp('orgCode').getValue();

        if (!GEbeginTime && !LEbeginTime && !goodsClassHighesNo && !activityType && !activityForm && !orgCode) {
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
    moduleName : '活动价值分析',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.actValue.tbarBnt,
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