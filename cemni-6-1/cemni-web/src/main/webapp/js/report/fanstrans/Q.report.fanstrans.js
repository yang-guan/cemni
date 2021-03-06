// fans转化率
Ext.ns('Q.fansTrans');
var dealUrl = path + '/FansTrans';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.fansTrans.storeStatus = {
        //门店类型 
        storStatus : new Ext.data.JsonStore({
            url : path + "/Dict_selDict.action?dictCode=8400",
            fields : [ "name", "value", "dictId" ],
            autoLoad : true
        }),
      //来源渠道
        sourChannel : new Ext.data.JsonStore({
            url : path + "/Dict_selDict.action?dictCode=1115",
            fields : [ "name", "value", "dictId" ],
            autoLoad : true
        })
    };

// 列表
var gridColumn = [ {
    header : "归属门店",
    width : 170,
    sortable : false,
    dataIndex : "belongstorename"
}, {
    header : "门店属性",
    width : 145,
    sortable : false,
    dataIndex : "attr"
}, {
    header : "总人数",
    width : 60,
    sortable : false,
    dataIndex : "totalcnt"
}, {
    header : "Fans人数",
    width : 65,
    sortable : false,
    dataIndex : "fans"
}, {
    header : "转化人数",
    width : 70,
    sortable : false,
    dataIndex : "trans"
}, {
    header : "转化率",
    width : 70,
    sortable : false,
    dataIndex : "transRatio"
}];

Q.fansTrans.tbarBnt = [ "-", {
    id : "EQ_storeNo",
    xtype : 'hidden'
}, {
    id : "EQ_storeName",
    index : 6,
    xtype : "textfield",
    emptyText : "归属门店",
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
},{
    id : "attr",
    index : 7,
    xtype : "uxcombo",
    emptyText : "门店类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.fansTrans.storeStatus.storStatus,
    width : 120
},{
    id : "sources",
    index : 8,
    xtype : "uxcombo",
    emptyText : "来源渠道",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.fansTrans.storeStatus.sourChannel,
    width : 120
}, {
    index : 9,
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
    index : 10,
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
    index : 11,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        var GEbeginTime = Ext.util.Format.date(Ext.getCmp('GE_beginTime').getValue(), 'Y-m-d');
        var LEbeginTime = Ext.util.Format.date(Ext.getCmp('LE_beginTime').getValue(), 'Y-m-d');
//        var orgCode = Ext.getCmp('orgCode').getValue();
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var attr = Ext.getCmp('attr').getValue();
        var sources = Ext.getCmp('sources').getValue();

        if (!GEbeginTime && !LEbeginTime && !storeNo && !attr&& !sources) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }

        _store.baseParams = {
            filter_GE_beginTime : GEbeginTime,
            filter_LE_beginTime : LEbeginTime,
            filter_EQ_storeNo : storeNo,
            filter_EQ_attr : attr,
            filter_EQ_sources : sources,
        };
        _store.load();
    }
}, {
    index : 12,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var GEbeginTime = Ext.getCmp('GE_beginTime').getValue();
        var LEbeginTime = Ext.getCmp('LE_beginTime').getValue();
        var storeNo = Ext.getCmp('EQ_storeNo').getValue();
        var attr = Ext.getCmp('attr').getValue();
        var sources = Ext.getCmp('sources').getValue();
        
        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();
        if (!GEbeginTime && !LEbeginTime && !storeNo && !attr&& !sources) {
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
    moduleName : 'fans转化率',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.fansTrans.tbarBnt,
        //自适应：forceFit : true或者删除本段代码默认为true
//        viewConfig : {
//            forceFit : false
//        },
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