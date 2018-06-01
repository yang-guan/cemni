/**
 * 门店-主页
 * 
 * @author：yuhb
 * @date：2016年11月23日 上午10:07:48
 */
Ext.ns('Q.store');
var dealUrl = path + "/Store";

// 列表
Q.store.gridColumn = [ {
    header : "门店编码",
    dataIndex : "storeNo",
    width : 8
}, {
    header : "门店名称",
    dataIndex : "name",
    width : 20
}, {
    header : "门店属性",
    dataIndex : "attrName",
    width : 8
}, {
    header : "门店形态",
    dataIndex : "formName",
    width : 8
}, {
    header : "开店日期",
    width : 8,
    dataIndex : "shopupDate",
    renderer : Q.common.dateRenderer
}, {
    header : "是否有效",
    dataIndex : "isValid",
    renderer : Q.common.yesOrNotRenderer,
    width : 8
}, {
    header : "大区",
    width : 10,
    dataIndex : "bigAreaName"
}, {
    header : "区域",
    width : 10,
    dataIndex : "areaName"
}, {
    header : "加盟商",
    width : 20,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraName;
        }
    }
} ];

// 表单
var editFormItems = [ {
    fieldLabel : "门店ID",
    name : "model.storeId",
    xtype : "textfield"
}, {
    fieldLabel : "门店编码",
    name : "model.storeNo",
    xtype : "textfield"
}, {
    fieldLabel : "门店名称",
    name : "model.name",
    xtype : "textfield"
}, {
    fieldLabel : "门店属性",
    name : "model.attrName",
    xtype : "textfield"
}, {
    fieldLabel : "门店形态",
    name : "model.formName",
    xtype : "textfield"
}, {
    fieldLabel : "开店日期",
    name : "model.shopupDate",
    xtype : "datefield",
    format : "Y-m-d"
}, {
    fieldLabel : "是否有效",
    name : "model.isValid",
    xtype : "uxcombo",
    store : Q.common.yesOrNotStore
}, {
    fieldLabel : "大区",
    name : "model.bigAreaName",
    xtype : "textfield"
}, {
    fieldLabel : "省",
    name : "model.provinceName",
    xtype : "textfield"
}, {
    fieldLabel : "区域",
    name : "model.areaName",
    xtype : "textfield"
}, {
    fieldLabel : "市",
    name : "model.cityName",
    xtype : "textfield"
}, {
    fieldLabel : "加盟商",
    name : "model.franchisee.fraName",
    xtype : "textfield"
}, {
    fieldLabel : "区/县",
    name : "model.countyName",
    xtype : "textfield"
}, {
    fieldLabel : "地址",
    name : "model.addr",
    xtype : "textfield"
} ];

// 按钮
var tbarBnt = [ {
    text : "查询",
    index : 9,
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        _store.baseParams.filter_LIKE_storeNo_OR_LIKE_name = Ext.getCmp("storeNoOrName").getValue();
        _store.load();
    }
}, {
    xtype : "textfield",
    index : 8,
    emptyText : "门店编码/名称",
    id : "storeNoOrName",
    width : 150
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '门店',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : tbarBnt,
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        listEditStateFn : [],
        column : Q.store.gridColumn,
        store : {
            idProperty : "storeId",
            url : dealUrl + '_getJson.action',
            sort : "isValid,bigAreaNo,areaNo,attr,form,storeId",
            dir : "desc,asc,asc,asc,asc,asc"
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : false,
        width : 600,
        height : 280,
        form : {
            labelWidth : 60,
            columnWidth : 0.5,
            items : editFormItems
        }
    }
};