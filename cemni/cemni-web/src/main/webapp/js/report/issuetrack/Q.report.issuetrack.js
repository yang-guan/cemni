/**
 * 促销品发放
 * 
 * @author zzy
 * @date 2017年2月3日 上午10:10:03
 */
Ext.ns('Q.issueTrack');
var dealUrl = path + '/IssueTrack';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.issueTrack.selStores = {
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
    })
};

// 列表
var gridColumn = [ {
    header : "POS单号",
    width : 120,
    sortable : false,
    dataIndex : "posno"
}, {
    header : "商品编码",
    width : 120,
    sortable : false,
    dataIndex : "goodsno"
}, {
    header : "礼品名称/商品名称",
    width : 180,
    sortable : false,
    dataIndex : "goodsname"
}, {
    header : "商品条形码",
    width : 150,
    sortable : false,
    dataIndex : "goodsbar"
}, {
    header : "是否赠品",
    width : 70,
    sortable : false,
    dataIndex : "flargess"
}, {
    header : "领取日期/消费日期",
    width : 150,
    sortable : false,
    dataIndex : "posbilldate",
    renderer : Q.common.timeRenderer
}, {
    header : "门店名称",
    width : 180,
    sortable : false,
    dataIndex : "storename"
}, {
    header : "客户名称",
    width : 120,
    sortable : false,
    dataIndex : "cardname"
}, {
    header : "手机号码",
    width : 100,
    sortable : false,
    dataIndex : "mobile"
}, {
    header : "会员卡号",
    width : 120,
    sortable : false,
    dataIndex : "cardno"
}, {
    header : "系列大类",
    width : 120,
    sortable : false,
    dataIndex : "goodsClassHighestNo"
}, {
    header : "系列分类",
    width : 120,
    sortable : false,
    dataIndex : "seriestypename"
}, {
    header : "销售金额",
    width : 100,
    sortable : false,
    dataIndex : "actualsaleamount"
}, {
    header : "珠宝折算额",
    width : 100,
    sortable : false,
    dataIndex : "jeweldiscountamount"
}, {
    header : "活动编号",
    width : 150,
    sortable : false,
    dataIndex : "actno"
}, {
    header : "活动主题",
    width : 300,
    sortable : false,
    dataIndex : "subject"
}, ];

Q.issueTrack.tbarBnt = [ "-", {
    id : "bigAreaName",
    index : 6,
    xtype : "uxcombo",
    emptyText : "大区",
    clearable : true,
    valueField : "orgId",
    displayField : "name",
    width : 100,
    store : Q.issueTrack.selStores.bigAreaStore,
    listeners : {
        "select" : function(c) {
            Ext.getCmp("areaName").reset();
            Ext.getCmp("storeNo").setValue("");
            Ext.getCmp("storeName").setValue("");
            Q.issueTrack.selStores.areaStore.removeAll();
            Q.issueTrack.selStores.areaStore.baseParams.filter_EQ_parentId = c.getValue();
            Q.issueTrack.selStores.areaStore.load();
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
    store : Q.issueTrack.selStores.areaStore,
    width : 100,
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
                    Ext.getCmp('storeName').setValue(nameArr.join(","));
                    Ext.getCmp('storeNo').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    index : 9,
    xtype : "textfield",
    emptyText : "活动主题",
    id : "subject",
    width : 150
}, {
    index : 10,
    xtype : "textfield",
    emptyText : "商品名称",
    id : "goodsname",
    width : 150
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
        var subject = Ext.getCmp('subject').getValue();
        var goodsname = Ext.getCmp('goodsname').getValue();
        var bigAreaName = Ext.getCmp('bigAreaName').getValue();
        var areaName = Ext.getCmp('areaName').getValue();
        var storeNo = Ext.getCmp('storeNo').getValue();

        if (!GEposbillDate && !LEposbillDate && !subject && !goodsname && !bigAreaName && !areaName && !storeNo) {
            Q.tips(Q.color("请输入查询条件", "red"));
            return;
        }

        var _store = vp.grid.getStore();
        _store.baseParams = {
            filter_GE_posbilldate : GEposbillDate,
            filter_LE_posbilldate : LEposbillDate,
            filter_LIKE_subject : subject,
            filter_LIKE_goodsname : goodsname,
            filter_EQ_bigAreaName : bigAreaName,
            filter_EQ_areaName : areaName,
            filter_EQ_storeNo : storeNo
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
        var subject = Ext.getCmp('subject').getValue();
        var goodsname = Ext.getCmp('goodsname').getValue();
        var bigAreaName = Ext.getCmp('bigAreaName').getValue();
        var areaName = Ext.getCmp('areaName').getValue();
        var storeNo = Ext.getCmp('storeNo').getValue();

        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();

        if (!GEposbillDate && !LEposbillDate && !subject && !goodsname && !bigAreaName && !areaName && !storeNo) {
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
    moduleName : '促销品发放追踪',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.issueTrack.tbarBnt,
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