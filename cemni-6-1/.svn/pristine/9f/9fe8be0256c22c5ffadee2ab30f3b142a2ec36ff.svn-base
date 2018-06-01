// 销售指标
Ext.ns('Q.salesTarget');
var dealUrl = path + '/SalesTarget';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.salesTarget.selStores = {
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
    // 门店属性
    attrStore : Q.common.selDict(8400)
};

// 列表
var gridColumn = [ {
    header : "大区",
    width : 100,
    sortable : false,
    dataIndex : "bigAreaName"
}, {
    header : "区域",
    width : 80,
    sortable : false,
    dataIndex : "areaName"
}, {
    header : "门店",
    width : 200,
    sortable : false,
    dataIndex : "name"
}, {
    header : "门店属性",
    width : 80,
    sortable : false,
    dataIndex : "attrName"
}, {
    header : "销售指标",
    width : 100,
    sortable : false,
    dataIndex : "bzj",
}, {
    header : "珠宝折算额",
    width : 100,
    sortable : false,
    dataIndex : "bje"
}, {
    header : "完成率",
    width : 100,
    sortable : false,
    dataIndex : "bpc"
}, {
    header : "销售指标",
    width : 100,
    sortable : false,
    dataIndex : "czj"
}, {
    header : "珠宝折算额",
    width : 100,
    sortable : false,
    dataIndex : "cje"
}, {
    header : "完成率",
    width : 100,
    sortable : false,
    dataIndex : "cpc"
}, {
    header : "折算增长额",
    width : 100,
    sortable : false,
    dataIndex : "jje"
}, {
    header : "折算增长率",
    width : 80,
    sortable : false,
    dataIndex : "jpc"
}, {
    header : "排名",
    width : 60,
    sortable : false,
    dataIndex : "rank"
} ];

var rows = new Ext.ux.grid.ColumnHeaderGroup({
    rows : [ [ {
        header : "",
        sortable : false,
        colspan : 6
    }, {
        header : "本期",
        sortable : false,
        align : "center",
        colspan : 3
    }, {
        header : "同期",
        sortable : false,
        align : "center",
        colspan : 3
    }, {
        header : "差额",
        sortable : false,
        align : "center",
        colspan : 2
    }, {
        header : "",
        sortable : false,
        colspan : 1
    } ] ]
});

Q.salesTarget.tbarBnt = [ "-", {
    id : "EQ_bigAreaId",
    index : 8,
    xtype : "uxcombo",
    emptyText : "大区",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    width : 120,
    store : Q.salesTarget.selStores.bigAreaStore,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("EQ_areaId").reset();
            Ext.getCmp("IN_storeNo").setValue("");
            Ext.getCmp("EQ_storeName").setValue("");
            Q.salesTarget.selStores.areaStore.removeAll();
            Q.salesTarget.selStores.areaStore.baseParams.filter_EQ_parentId = c.getValue();
            Q.salesTarget.selStores.areaStore.load();
        }
    }
}, {
    index : 9,
    id : "EQ_areaId",
    xtype : "uxcombo",
    emptyText : "区域",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    store : Q.salesTarget.selStores.areaStore,
    width : 120,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("IN_storeNo").setValue("");
            Ext.getCmp("EQ_storeName").setValue("");
        }
    }
}, {
    index : 10,
    id : "EQ_storeName",
    xtype : "textfield",
    emptyText : "门店",
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
                    parentId : Ext.getCmp("EQ_areaId").getValue(),
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
                    Ext.getCmp('IN_storeNo').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    index : 11,
    id : "EQ_attr",
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    emptyText : "门店属性",
    store : Q.salesTarget.selStores.attrStore,
    clearable : true,
    width : 120
}, {
    index : 12,
    id : "IN_storeNo",
    xtype : 'hidden'
}, {
    index : 13,
    xtype : "datefield",
    emptyText : "至",
    id : "eqdate",
    width : 100,
    format : 'Y-m-d',
    maxValue : new Date(),
    minValue : Ext.util.Format.date(new Date(), "Y-m") + "-01",
    value : new Date()
}, {
    index : 14,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        var bigAreaId = Ext.getCmp('EQ_bigAreaId').getValue();
        var areaId = Ext.getCmp('EQ_areaId').getValue();
        var storeNo = Ext.getCmp('IN_storeNo').getValue();
        var EQ_attr = Ext.getCmp('EQ_attr').getValue();
        var eqdate = Ext.util.Format.date(Ext.getCmp('eqdate').getValue(), 'Y-m-d');

        if (!bigAreaId && !areaId && !storeNo && !EQ_attr && !eqdate) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }
        _store.baseParams = {
            filter_EQ_date : eqdate,
            filter_EQ_bigAreaId : bigAreaId,
            filter_EQ_areaId : areaId,
            filter_IN_storeNo : storeNo,
            filter_EQ_attr : EQ_attr
        };
        _store.load();
    }
}, {
    index : 15,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var bigAreaId = Ext.getCmp('EQ_bigAreaId').getValue();
        var areaId = Ext.getCmp('EQ_areaId').getValue();
        var storeNo = Ext.getCmp('IN_storeNo').getValue();
        var eqdate = Ext.getCmp('eqdate').getValue();

        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();

        if (!bigAreaId && !areaId && !storeNo && !eqdate) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        } else if (totalCount == 0) {
            Q.tips(Q.color("请先查询出结果在进行导出", "red"));
            return;
        } else if (totalCount > 5000) {
            Q.tips(Q.color("每次导出上限为5000", "red"));
            return;
        }
        window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(_store.baseParams));
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '销售指标',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.salesTarget.tbarBnt,
        viewConfig : {
            forceFit : false
        },
        hideSubTab : true,
        plugins : rows,
        column : gridColumn,
        subTab : [],
        listEditStateFn : [],
        store : {
            url : dealUrl + '_query.action',
            autoLoad : false
        }
    }
};