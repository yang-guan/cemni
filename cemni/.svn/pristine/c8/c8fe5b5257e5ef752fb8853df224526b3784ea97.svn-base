// 回访内容分析
Ext.ns('Q.reviewContent');
var dealUrl = path + '/ReviewContent';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.reviewContent.selStores = {
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
    // 消费用户
    ConsumerStore : Q.common.selDict(7700),
    // 回访类型
    backTypeStore : Q.common.selDict(1114),
    // 任务类型
    proNameStore : Q.common.selDict(2107)
};
// 列表
var gridColumn = [ {
    header : "回访记录单号",
    width : 130,
    sortable : false,
    dataIndex : "telvisitrecordno"
}, {
    header : "回访日期",
    width : 80,
    sortable : false,
    renderer : Q.common.dateRenderer,
    dataIndex : "backtime"
}, {
    header : "大区",
    width : 130,
    sortable : false,
    dataIndex : "bigAreaName"
}, {
    header : "门店",
    width : 200,
    sortable : false,
    dataIndex : "storeName"
}, {
    header : "回访类型",
    width : 80,
    sortable : false,
    dataIndex : "fresh"
}, {
    header : "任务类型",
    width : 80,
    sortable : false,
    dataIndex : "taskType"
}, {
    header : "会员卡号",
    width : 80,
    sortable : false,
    dataIndex : "cardno",
}, {
    header : "客户名称",
    width : 100,
    sortable : false,
    dataIndex : "name"
}, {
    header : "店面服务",
    width : 80,
    sortable : false,
    dataIndex : "shopservice"
}, {
    header : "饰品佩戴",
    width : 100,
    sortable : false,
    dataIndex : "ornamentwear"
}, {
    header : "客户建议",
    width : 100,
    sortable : false,
    dataIndex : "khadvice"
}, {
    header : "客户咨询",
    width : 100,
    sortable : false,
    dataIndex : "khtalk"
}, {
    header : "专业知识",
    width : 100,
    sortable : false,
    dataIndex : "professorknow"
}, {
    header : "已告知讯息",
    width : 100,
    sortable : false,
    dataIndex : "infoknowed"
}, {
    header : "购物环境",
    width : 100,
    sortable : false,
    dataIndex : "shopenvi"
}, {
    header : "赠品发放",
    width : 100,
    sortable : false,
    dataIndex : "parentgant"
}, {
    header : "反馈意见",
    width : 100,
    sortable : false,
    dataIndex : "feedadvice"
}, {
    header : "佩戴后维护",
    width : 100,
    sortable : false,
    dataIndex : "wearupdate"
}, {
    header : "赠品满意度",
    width : 100,
    sortable : false,
    dataIndex : "parentmanyi"
}, {
    header : "保养维修",
    width : 100,
    sortable : false,
    dataIndex : "careupdate"
}, {
    header : "总体满意度",
    width : 80,
    sortable : false,
    dataIndex : "satisfaction"
}, {
    header : "备注",
    width : 200,
    sortable : false,
    dataIndex : "saleremark"
}, {
    header : "店面服务",
    width : 100,
    sortable : false,
    dataIndex : "notshopservice",
    hidden : true
}, {
    header : "客户咨询",
    width : 100,
    sortable : false,
    dataIndex : "notkhtalk",
    hidden : true
}, {
    header : "客户建议",
    width : 100,
    sortable : false,
    dataIndex : "notkhadvice",
    hidden : true
}, {
    header : "专业知识",
    width : 100,
    sortable : false,
    dataIndex : "notprofessorknow",
    hidden : true
}, {
    header : "已告知讯息",
    width : 100,
    sortable : false,
    dataIndex : "notinfoknowed",
    hidden : true
}, {
    header : "购物环境",
    width : 100,
    sortable : false,
    dataIndex : "notshopenvi",
    hidden : true
}, {
    header : "反馈意见",
    width : 100,
    sortable : false,
    dataIndex : "notfeedadvice",
    hidden : true
}, {
    header : "意向产品",
    width : 100,
    sortable : false,
    dataIndex : "notintentioncp",
    hidden : true
}, {
    header : "新品推荐",
    width : 100,
    sortable : false,
    dataIndex : "notnewrecoment",
    hidden : true
}, {
    header : "总体满意度",
    width : 100,
    sortable : false,
    dataIndex : "notsatisfaction"
}, {
    header : "备注",
    width : 200,
    sortable : false,
    dataIndex : "notsaleremark",
    hidden : true
} ];

Q.reviewContent.tbarBnt = [ "-", {
    id : "bigAreaName",
    index : 6,
    xtype : "uxcombo",
    emptyText : "大区",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    width : 120,
    store : Q.reviewContent.selStores.bigAreaStore,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("areaName").reset();
            Ext.getCmp("EQ_storeNo").setValue("");
            Ext.getCmp("EQ_storeName").setValue("");
            Q.reviewContent.selStores.areaStore.removeAll();
            Q.reviewContent.selStores.areaStore.baseParams.filter_EQ_parentId = c.getValue();
            Q.reviewContent.selStores.areaStore.load();
        }
    }
}, {
    id : "areaName",
    index : 7,
    xtype : "uxcombo",
    emptyText : "区域",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    store : Q.reviewContent.selStores.areaStore,
    width : 120,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("EQ_storeNo").setValue("");
            Ext.getCmp("EQ_storeName").setValue("");
        }
    }
}, {
    index : 8,
    id : "EQ_storeNo",
    xtype : 'hidden'
}, {
    id : "EQ_storeName",
    index : 9,
    xtype : "textfield",
    emptyText : "回访门店",
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
                var _win = new Q.store.chooseStoreWin({
                    action : "report2store",
                    parentId : Ext.getCmp("areaName").getValue(),
                    singleSelect : false
                });
                _win.on("submit", function(r) {
                    var codeArr = [];
                    var nameArr = [];
                    Q.each(r, function() {
                        codeArr.push(this.data.storeNo);
                        nameArr.push(this.data.name);
                    });
                    Ext.getCmp('EQ_storeName').setValue(nameArr.join(","));
                    Ext.getCmp('EQ_storeNo').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    id : "EQ_isPos",
    index : 10,
    xtype : "uxcombo",
    emptyText : "客户类型",
    width : 120,
    value : 1,
    store : new Ext.data.ArrayStore({
        fields : [ 'value', 'text' ],
        data : [ [ '1', '未消费客户' ], [ '2', '已消费客户' ] ]
    }),
}, {
    id : "EQ_fresh",
    index : 11,
    xtype : "uxcombo",
    emptyText : "回访类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.reviewContent.selStores.backTypeStore,
    width : 120,
}, {
    id : "EQ_taskType",
    index : 12,
    xtype : "uxcombo",
    emptyText : "任务类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.reviewContent.selStores.proNameStore,
    width : 120
}, {
    index : 13,
    xtype : "datefield",
    emptyText : "回访日期",
    id : "sdate",
    width : 100,
    format : 'Y-m-d',
    listeners : {
        select : function() {
            var sdate = Ext.getCmp('sdate').getValue();
            var edate = Ext.getCmp("edate").getValue();
            if (sdate && edate) {
                if (sdate > edate) {
                    Q.warning("开始日期不能大于结束日期");
                    Ext.getCmp('sdate').setValue("");
                }
            }
        }
    }
}, {
    index : 14,
    xtype : "datefield",
    emptyText : "至",
    id : "edate",
    width : 100,
    format : 'Y-m-d',
    listeners : {
        select : function() {
            var sdate = Ext.getCmp('sdate').getValue();
            var edate = Ext.getCmp("edate").getValue();
            if (sdate && edate) {
                if (sdate > edate) {
                    Q.warning("结束日期不能小于开始日期");
                    Ext.getCmp('sdate').setValue("");
                }
            }
        }
    }
}, {
    index : 15,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        var isPos = Ext.getCmp('EQ_isPos').getValue();
        var sdate = Ext.util.Format.date(Ext.getCmp('sdate').getValue(), 'Y-m-d');
        var edate = Ext.util.Format.date(Ext.getCmp('edate').getValue(), 'Y-m-d');
        var fresh = Ext.getCmp('EQ_fresh').getValue();
        var taskType = Ext.getCmp('EQ_taskType').getValue();
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var areaName = Ext.getCmp('areaName').getValue();
        var bigAreaName = Ext.getCmp('bigAreaName').getValue();

        if (!sdate && !edate && !storeNo && !areaName && !bigAreaName && !isPos && !fresh && fresh !== 0 && !taskType) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }
        if (isPos == 2) {
            for (var int = 10; int <= 34; int++) {
                if (int > 23) {
                    vp.grid.getColumnModel().setHidden(int, true);
                } else {
                    vp.grid.getColumnModel().setHidden(int, false);
                }
            }
        } else if (isPos == 1) {
            for (var int = 10; int <= 34; int++) {
                if (int > 23) {
                    vp.grid.getColumnModel().setHidden(int, false);
                } else {
                    vp.grid.getColumnModel().setHidden(int, true);
                }
            }
        } else {
            for (var int = 10; int <= 34; int++) {
                if (int > 23) {
                    vp.grid.getColumnModel().setHidden(int, true);
                } else {
                    vp.grid.getColumnModel().setHidden(int, false);
                }
            }
        }
        _store.baseParams = {
            filter_EQ_sdate : sdate,
            filter_EQ_edate : edate,
            filter_EQ_storeNo : storeNo,
            filter_EQ_areaName : areaName,
            filter_EQ_bigAreaName : bigAreaName,
            filter_EQ_isPos : isPos,
            filter_EQ_fresh : fresh,
            filter_EQ_taskType : taskType
        };
        _store.load();
    }
}, {
    index : 16,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var isPos = Ext.getCmp('EQ_isPos').getValue();
        var sdate = Ext.getCmp('sdate').getValue();
        var edate = Ext.getCmp('edate').getValue();
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var areaName = Ext.getCmp('areaName').getValue();
        var bigAreaName = Ext.getCmp('bigAreaName').getValue();
        var fresh = Ext.getCmp('EQ_fresh').getValue();
        var taskType = Ext.getCmp('EQ_taskType').getValue();
        var store = vp.grid.getStore();
        var totalCount = store.getTotalCount();

        if (!sdate && !edate && !storeNo && !areaName && !bigAreaName && !isPos && !fresh && !taskType) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }
        if (totalCount == 0) {
            Q.tips(Q.color("请先查询出结果在进行导出", "red"));
            return;
        }
        if (totalCount > 5000) {
            Q.tips(Q.color("每次导出上限为5000", "red"));
            return;
        }
        window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(vp.grid.getStore().baseParams));
    }
} ];

Ext.onReady(function() {
    var isPos = Ext.getCmp('EQ_isPos').getValue();
    if (isPos == 1) {
        for (var int = 10; int <= 34; int++) {
            if (int > 23) {
                vp.grid.getColumnModel().setHidden(int, false);
            } else {
                vp.grid.getColumnModel().setHidden(int, true);
            }
        }
    } else if (isPos == 2) {
        for (var int = 10; int <= 34; int++) {
            if (int > 23) {
                vp.grid.getColumnModel().setHidden(int, true);
            } else {
                vp.grid.getColumnModel().setHidden(int, false);
            }
        }
    }
});

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '回访内容分析',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.reviewContent.tbarBnt,
        viewConfig : {
            forceFit : false
        },
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        listEditStateFn : [],
        store : {
            url : dealUrl + '_query.action',
            autoLoad : false
        }
    }
};