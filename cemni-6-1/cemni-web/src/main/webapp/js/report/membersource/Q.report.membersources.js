
Ext.ns('Q.memberSources');
var dealUrl = path + '/MemSources';

Q.memberSources.storeStatus = {
        //门店类型 
        storStatus : new Ext.data.JsonStore({
            url : path + "/Dict_selDict.action?dictCode=8400",
            fields : [ "name", "value", "dictId" ],
            autoLoad : true
        })
    };

//列表
var gridColumn = [ {
 header : "归属门店",
 width : 170,
 sortable : false,
 dataIndex : "belongstorename"
},{
    header : "门店属性",
    width : 70,
    sortable : false,
    dataIndex : "attr"
}, {
 header : "创建人数",
 width : 70,
 sortable : false,
 dataIndex : "totalcnt"
}, {
 header : "消费人数",
 width : 70,
 sortable : false,
 dataIndex : "ConsumerCnt"
}, {
 header : "NC",
 width : 45,
 sortable : false,
 dataIndex : "NC"
}, {
 header : "NC占比",
 width : 60,
 sortable : false,
 dataIndex : "Ratio1"
}, {
 header : "CRM",
 width :45,
 sortable : false,
 dataIndex : "CRM"
}, {
 header : "CRM占比",
 width : 65,
 sortable : false,
 dataIndex : "Ratio2"
}, {
 header : "微信",
 width : 45,
 sortable : false,
 dataIndex : "webchat"
}, {
 header : "微信占比",
 width : 65,
 sortable : false,
 dataIndex : "Ratio3"
}, {
 header : "耀我网",
 width :50,
 sortable : false,
 dataIndex : "YAOWO"
}, {
 header : "耀我网占比",
 width : 75,
 sortable : false,
 dataIndex : "Ratio4"
}, {
 header : "市场活动",
 width : 65,
 sortable : false,
 dataIndex : "MarketActivity"
}, {
 header : "市场活动占比",
 width : 85,
 sortable : false,
 dataIndex : "Ratio5"
}, {
 header : "异业联盟",
 width : 70,
 sortable : false,
 dataIndex : "alliance"
}, {
 header : "异业联盟占比",
 width : 90,
 sortable : false,
 dataIndex : "Ratio6"
}, {
 header : "客户推荐",
 width : 70,
 sortable : false,
 dataIndex : "CustRecommend"
}, {
 header : "客户推荐占比",
 width : 90,
 sortable : false,
 dataIndex : "Ratio7"
}, {
 header : "其他",
 width : 45,
 sortable : false,
 dataIndex : "Other"
}, {
 header : "其他占比",
 width : 60,
 sortable : false,
 dataIndex : "Ratio8"
}];

Q.memberSources.tbarBnt = [ "-", {
    index : 6,
    xtype : "textfield",
    emptyText : "门店 ",
    id : "belongStoreName",
    width : 150
},{
    id : "attr",
    index : 7,
    xtype : "uxcombo",
    emptyText : "门店类型",
    clearable : true,
    valueField : "value",
    displayField : "name",
    store : Q.memberSources.storeStatus.storStatus,
    width : 120
},{
 index : 8,
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
 index :9,
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
 index :10,
 text : "查询",
 iconCls : "icon-search",
 handler : function() {
     var _store = vp.grid.getStore();
     var sCdate = Ext.util.Format.date(Ext.getCmp('GE_beginTime').getValue(), 'Y-m-d');
     var eCdate = Ext.util.Format.date(Ext.getCmp('LE_beginTime').getValue(), 'Y-m-d');
     var belongStoreName = Ext.getCmp('belongStoreName').getValue();
     var attr = Ext.getCmp('attr').getValue();
     if (!sCdate && !eCdate && !belongStoreName&& !attr) {
         Q.tips(Q.color("请输入查询条件", "red"));
         return;
     }

     _store.baseParams = {
         filter_s_Cdate : sCdate,
         filter_e_Cdate : eCdate,
         filter_LIKE_belongStoreName : belongStoreName,
         filter_EQ_attr : attr,
     };
     _store.load();
 }
}, {
 index : 11,
 text : "导出",
 iconCls : "icon-excel",
 build : power['export'],
 handler : function() {
     var _store = vp.grid.getStore();
     var totalCount = _store.getTotalCount();
     var sCdate = Ext.getCmp('GE_beginTime').getValue();
     var eCdate = Ext.getCmp('LE_beginTime').getValue();
     var belongStoreName = Ext.getCmp('belongStoreName').getValue();
     var attr = Ext.getCmp('attr').getValue();

     if (!sCdate && !eCdate && !belongStoreName&& !attr) {
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
    moduleName : '会员来源',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.memberSources.tbarBnt,
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