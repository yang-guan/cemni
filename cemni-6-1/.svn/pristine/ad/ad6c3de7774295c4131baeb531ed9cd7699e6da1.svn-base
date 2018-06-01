// 回访任务汇总
Ext.ns('Q.reviewTask');
var dealUrl = path + '/ReviewTask';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.reviewTask.selStores = {
    // 回访方式
    backTypeStore : Q.common.selDict(2101),
    // 任务类型
    proNameStore : Q.common.selDict(2107)
};

// 列表
var gridColumn = [ {
    header : "回访任务单号",
    width : 10,
    sortable : false,
    dataIndex : "telvisitNo"
}, {
    header : "回访门店",
    width : 20,
    sortable : false,
    dataIndex : "storeName"
}, {
    header : "回访方式",
    width : 9,
    sortable : false,
    dataIndex : "backfsName"
}, {
    header : "回访名称",
    width : 9,
    sortable : false,
    dataIndex : "tasktypeName",
}, {
    header : "状态",
    width : 8,
    sortable : false,
    dataIndex : "visitSatus"
}, {
    header : "未回访数",
    width : 8,
    sortable : false,
    dataIndex : "unhasCnt"
}, {
    header : "已回访数",
    width : 8,
    sortable : false,
    dataIndex : "hasCnt"
}, {
    header : "总回访数",
    width : 8,
    sortable : false,
    dataIndex : "totalCnt"
}, {
    header : "回访成功率",
    width : 9,
    sortable : false,
    dataIndex : "succPercent"
}, {
    header : "创建日期",
    width : 9,
    sortable : false,
    renderer : Q.common.dateRenderer,
    dataIndex : "cdate"
} ];

Q.reviewTask.tbarBnt = [ "-", {
    index : 6,
    xtype : "textfield",
    emptyText : "回访任务单号",
    id : "EQ_telvisitNo",
    width : 150
}, {
    id : "EQ_storeNo",
    xtype : 'hidden'
}, {
    id : "EQ_storeName",
    index : 8,
    xtype : "textfield",
    emptyText : "回访门店",
    width : 200,
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
    id : "EQ_backfs",
    index : 9,
    xtype : "uxcombo",
    emptyText : "回访方式",
    clearable : true,
    valueField : "value",
    displayField : "name",
    width : 120,
    store : Q.reviewTask.selStores.backTypeStore
}, {
    id : "EQ_taskType",
    index : 10,
    xtype : "uxcombo",
    emptyText : "任务类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.reviewTask.selStores.proNameStore,
    width : 120
}, {
    index : 11,
    xtype : "datefield",
    emptyText : "创建日期",
    id : "sdate",
    width : 120,
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
    index : 12,
    xtype : "datefield",
    emptyText : "至",
    id : "edate",
    width : 120,
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
    index : 13,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        var sdate = Ext.util.Format.date(Ext.getCmp('sdate').getValue(), 'Y-m-d');
        var edate = Ext.util.Format.date(Ext.getCmp('edate').getValue(), 'Y-m-d');
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var backfs = Ext.getCmp('EQ_backfs').getValue();
        var taskType = Ext.getCmp('EQ_taskType').getValue();
        var telvisitNo = Ext.getCmp('EQ_telvisitNo').getValue();

        if (!sdate && !edate && !storeNo && !backfs && !taskType && !telvisitNo) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }
        _store.baseParams = {
            filter_EQ_sdate : sdate,
            filter_EQ_edate : edate,
            filter_EQ_storeNo : storeNo,
            filter_EQ_telvisit_backfs : backfs,
            filter_EQ_taskType : taskType,
            filter_EQ_telvisit_telvisitNo : telvisitNo
        };
        _store.load();
    }
}, {
    index : 14,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var sdate = Ext.getCmp('sdate').getValue();
        var edate = Ext.getCmp('edate').getValue();
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var backfs = Ext.getCmp('EQ_backfs').getValue();
        var taskType = Ext.getCmp('EQ_taskType').getValue();
        var telvisitNo = Ext.getCmp('EQ_telvisitNo').getValue();

        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();

        if (!sdate && !edate && !storeNo && !backfs && !taskType && !telvisitNo) {
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
    moduleName : '回访任务分析汇总表',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.reviewTask.tbarBnt,
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