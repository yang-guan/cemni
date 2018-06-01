/**
 * 会员等级数据整理
 * 
 * @author：WangYuanJun
 * @date：2017年2月23日 下午3:35:31
 */
Ext.ns('Q.membergrade');
var dealUrl = path + '/MemberGrade';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.membergrade.selStores = {
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
    // 会员等级
    lvStore : Q.common.selDict(3100)
};

// 列表
var gridColumn = [ {
    header : "大区",
    sortable : false,
    dataIndex : "bigAreaName"
}, {
    header : "门店",
    sortable : false,
    width : 250,
    dataIndex : "storeName"
}, {
    header : "会员等级",
    sortable : false,
    dataIndex : "lvName"
}, {
    header : "人数",
    sortable : false,
    dataIndex : "activePerson"
}, {
    header : "人数比",
    sortable : false,
    dataIndex : "percentActivePerson"
}, {
    header : "金额",
    sortable : false,
    dataIndex : "activeMoney"
}, {
    header : "金额比",
    sortable : false,
    dataIndex : "percentActiveMoney"
}, {
    header : "人数",
    sortable : false,
    dataIndex : "sleepPerson"
}, {
    header : "人数比",
    sortable : false,
    dataIndex : "percentSleepPerson"
}, {
    header : "金额",
    sortable : false,
    dataIndex : "sleepMoney"
}, {
    header : "金额比",
    sortable : false,
    dataIndex : "percentSleepMoney"
}, {
    header : "人数",
    sortable : false,
    dataIndex : "historyPerson"
}, {
    header : "人数比",
    sortable : false,
    dataIndex : "percentHistoryPerson"
}, {
    header : "金额",
    sortable : false,
    dataIndex : "historyMoney"
}, {
    header : "金额比",
    sortable : false,
    dataIndex : "percentHistoryMoney"
}, {
    header : "人数",
    sortable : false,
    dataIndex : "invalidPerson"
}, {
    header : "人数比",
    sortable : false,
    dataIndex : "percentInvalidPerson"
}, {
    header : "金额",
    sortable : false,
    dataIndex : "invalidMoney"
}, {
    header : "金额比",
    sortable : false,
    dataIndex : "percentInvalidMoney"
}, {
    header : "人数",
    sortable : false,
    dataIndex : "sumPerson"
}, {
    header : "人数比",
    sortable : false,
    dataIndex : "percentSumPerson"
}, {
    header : "金额",
    sortable : false,
    dataIndex : "sumMoney"
}, {
    header : "金额比",
    sortable : false,
    dataIndex : "percentSumMoney"
} ];

var rows = new Ext.ux.grid.ColumnHeaderGroup({
    rows : [ [ {
        header : "",
        align : "center",
        colspan : 5
    }, {
        header : "活跃",
        align : "center",
        colspan : 4
    }, {
        header : "沉睡",
        align : "center",
        colspan : 4
    }, {
        header : "历史",
        align : "center",
        colspan : 4
    }, {
        header : "无效",
        align : "center",
        colspan : 4
    }, {
        header : "合计",
        align : "center",
        colspan : 4
    } ] ]
});

Q.membergrade.tbarBnt = [ "-", {
    id : "bigAreaId",
    index : 6,
    xtype : "uxcombo",
    emptyText : "大区",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    width : 120,
    store : Q.membergrade.selStores.bigAreaStore,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("areaId").reset();
            Ext.getCmp("storeNo").setValue("");
            Ext.getCmp("storeName").setValue("");
            Q.membergrade.selStores.areaStore.removeAll();
            Q.membergrade.selStores.areaStore.baseParams.filter_EQ_parentId = c.getValue();
            Q.membergrade.selStores.areaStore.load();
        }
    }
}, {
    id : "areaId",
    index : 7,
    xtype : "uxcombo",
    emptyText : "区域",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    store : Q.membergrade.selStores.areaStore,
    width : 120,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("storeNo").setValue("");
            Ext.getCmp("storeName").setValue("");
        }
    }
}, {
    index : 17,
    id : "storeNo",
    xtype : 'hidden'
}, {
    id : "storeName",
    index : 8,
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
                    parentId : Ext.getCmp("areaId").getValue(),
                    singleSelect : false
                });
                _win.on("submit", function(r) {
                    var codeArr = [];
                    var nameArr = [];
                    Q.each(r, function() {
                        codeArr.push(this.data.storeNo);
                        nameArr.push(this.data.name);
                    });
                    Ext.getCmp('storeName').setValue(nameArr.join(","));
                    Ext.getCmp('storeNo').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    id : "lvName",
    index : 9,
    xtype : "uxcombo",
    emptyText : "会员等级",
    clearable : true,
    valueField : "name",
    displayField : "name",
    store : Q.membergrade.selStores.lvStore,
    width : 100
}, {
    index : 11,
    xtype : "datefield",
    emptyText : "消费日期",
    format : 'Y-m-d',
    id : "GE_posbillDate",
    width : 100,
    listeners : {
        select : function() {
            var GE_posbillDate = Ext.getCmp('GE_posbillDate').getValue();
            var LE_posbillDate = Ext.getCmp("LE_posbillDate").getValue();
            if (GE_posbillDate && LE_posbillDate) {
                if (LE_posbillDate < GE_posbillDate) {
                    Q.warning("开始日期不能大于结束日期");
                    Ext.getCmp('GE_posbillDate').setValue("");
                }
            }
        }
    }
}, {
    index : 12,
    xtype : "datefield",
    emptyText : "至",
    id : "LE_posbillDate",
    format : 'Y-m-d',
    width : 100,
    listeners : {
        select : function() {
            var GE_posbillDate = Ext.getCmp('GE_posbillDate').getValue();
            var LE_posbillDate = Ext.getCmp("LE_posbillDate").getValue();
            if (GE_posbillDate && LE_posbillDate) {
                if (LE_posbillDate < GE_posbillDate) {
                    Q.warning("结束日期不能小于开始日期");
                    Ext.getCmp('LE_posbillDate').setValue("");
                }
            }
        }
    }
}, {
    index : 13,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var GEposbillDate = Ext.util.Format.date(Ext.getCmp('GE_posbillDate').getValue(), 'Y-m-d');
        var LEposbillDate = Ext.util.Format.date(Ext.getCmp('LE_posbillDate').getValue(), 'Y-m-d');
        var lvName = Ext.getCmp('lvName').getValue();
        var bigAreaId = Ext.getCmp('bigAreaId').getValue();
        var areaId = Ext.getCmp('areaId').getValue();
        var storeNo = Ext.getCmp('storeNo').getValue();

        if (!GEposbillDate && !LEposbillDate && !lvName && !bigAreaId && !areaId && !storeNo) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }

        var _store = vp.grid.getStore();
        _store.baseParams = {
            filter_GE_posbilldate : GEposbillDate,
            filter_LE_posbilldate : LEposbillDate,
            filter_EQ_bigAreaId : bigAreaId,
            filter_EQ_areaId : areaId,
            filter_EQ_storeNo : storeNo,
            filter_EQ_lvName : lvName
        };
        _store.load();
    }
}, {
    index : 14,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var GEposbillDate = Ext.getCmp('GE_posbillDate').getValue();
        var LEposbillDate = Ext.getCmp('LE_posbillDate').getValue();
        var lvName = Ext.getCmp('lvName').getValue();
        var bigAreaId = Ext.getCmp('bigAreaId').getValue();
        var areaId = Ext.getCmp('areaId').getValue();
        var storeNo = Ext.getCmp('storeNo').getValue();
        var store = vp.grid.getStore();
        var totalCount = store.getTotalCount();

        if (!GEposbillDate && !LEposbillDate && !lvName && !bigAreaId && !areaId && !storeNo) {
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
    moduleName : '会员等级数据整理',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.membergrade.tbarBnt,
        viewConfig : {
            forceFit : false
        },
        plugins : rows,
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