Ext.ns("Q.permission");

// 页面store 公共类
Q.permission.store = {
/*
 * purchasingCategoryStore:new Ext.data.JsonStore({
 * url:path_core+"/base/DataDict_getAll.action?groupCode=contractAttribute", fields: ['basecode',
 * 'basecodeName',{name:'codename',convert: function(v, r){ return String.format("{0}[{1}]", r.basecode,
 * r.basecodeName); }}], autoLoad:true })
 */
}

var dealUrl = path + "/permission/AuthField";
var moduleName = $("authField.addAuthField");
var triggerField = 'authCode';

// 列表
var gridColumn = [ {
    Qheader : "权限字段编码",
    header : $("authField.authCode"),
    dataIndex : 'authCode'
}, {
    Qheader : "字段名称",
    header : $("authField.fieldCode"),
    dataIndex : 'fieldCode'
}, {
    Qheader : "字段描述",
    header : $("authField.fieldName"),
    dataIndex : 'fieldName'
}, {
    Qheader : "字段类型",
    header : $("authField.fieldType"),
    dataIndex : 'fieldType'
}, {
    Qheader : "字段长度",
    header : $("authField.fieldLength"),
    dataIndex : 'fieldLength'
}, {
    Qheader : "表名",
    header : $("authField.tableName"),
    dataIndex : 'tableName'
} ];

var gridStore = {
    idProperty : "authCode",
    url : dealUrl + "_getJson.action",
    baseParams : {
    // initStates:initStatesStr
    }
};

// 按钮
var addBtn = [ {
    xtype : 'textfield',
    name : "filter_LIKE_fieldCode_OR_tableName_OR_authCode",
    index : 11,
    emptyText : $("authField.authCode") + "/" + $("authField.fieldCode") + "/" + $("authField.tableName")
}, {
    name : "newSearch",
    Qtext : "查询",
    text : $('button.search'),
    index : 12,
    iconCls : "icon-search",
    handler : function(_self) {
        var grid = this.findParentByType(Ext.grid.GridPanel); // 查找出所属的父Grid
        var store = grid.getStore();
        var tbar = grid.getTopToolbar();
        var searchValue = tbar.find("name", "filter_LIKE_fieldCode_OR_tableName_OR_authCode")[0];
        store.baseParams.filter_LIKE_fieldCode_OR_tableName_OR_authCode = searchValue.getValue();
        if (!Ext.isEmpty(store.lastOptions)) {
            store.load({
                params : {
                    start : 0,
                    limit : store.lastOptions.params.limit
                }
            });
        } else {
            store.load({
                params : {
                    start : 0,
                    limit : 20
                }
            });
        }
    }
} ];

var editFormItems = [ {
    fieldLabel : $("authField.authCode") + "<font color='red'>*</font>",// 权限字段
    allowBlank : false,
    maxLength : 30,
    name : "model.authCode"
}, {
    name : "model.fieldCode",
    allowBlank : false,
    maxLength : 50,
    fieldLabel : $("authField.fieldCode") + "<font color='red'>*</font>"// 字段名
}, {
    name : "model.fieldName",
    allowBlank : false,
    maxLength : 50,
    fieldLabel : $("authField.fieldName") + "<font color='red'>*</font>"// 字段描述
}, {
    name : "model.fieldType",
    allowBlank : false,
    maxLength : 20,
    fieldLabel : $("authField.fieldType") + "<font color='red'>*</font>"// 字段类型
}, {
    xtype : "numberfield",
    name : "model.fieldLength",
    allowBlank : false,
    allowDecimals : false,
    allowNegative : false,
    minValue : 1,
    maxValue : 4000,
    fieldLabel : $("authField.fieldLength") + "<font color='red'>*</font>"// 字段长度
}, {
    name : "model.tableName",
    allowBlank : false,
    maxLength : 30,
    fieldLabel : $("authField.tableName") + "<font color='red'>*</font>"// 表名
} ];

// 查询表单
var searchFormItems = [

];

cfg = {
    isAudit : false,// 是否需要审核右键
    dealUrl : dealUrl,// 各种操作的url地址
    moduleName : moduleName,// 模块名称
    playListMode : playListMode.normal, // normal/audit/undeal/panel //三种列表模式
    // 重写审核通过和审核不过按钮
    /*
     * menuOverride:[{text:"测试",
     * name:'TOPASS',iconCls:"icon-toPass",hidden:true,handler:function(){vp.dealstate(this.name,this.text)}},
     * {text:"测试1",
     * name:'TONOPASS',iconCls:"icon-toNoPass",hidden:true,handler:function(){vp.dealstate(this.name,this.text)}}],
     */
    // displayTbar:true,
    // accessControl:accessControl, //可以实现不同角色是表单和列表的字段，是否显示/是否可编辑/是否必填等的初始化配置；
    vp : {
        sm : {
            singleSelect : false
        },// 列表是否单选
        addOtherBtn : addBtn, // 在固化的按钮基础上追加按钮
        // hideBtn:["delete"),//隐藏固化的按钮
        column : gridColumn,// 初始化列表
        store : gridStore, // 初始化 列表store
        // disabledColumn:disabledColumn, //隐藏的列表项
        // addOtherColumn:addOtherColumn,//追加列表项
        hideSubTab : true,// 隐藏的tab列表项
        subTab : [],// subTab第一层数据为细单，数组中的第二层数组为有带细单的细单，(且第一个为细单,后面几个为细单的细单)
        activeTab : 0,// 默认展示哪儿tab
        // tabHeight:350,//tab 的高度设置
        // triggerField:triggerField,//点击列表的哪一列显示对应的tab列表
        logModuleCode : "BiddingJAction",
        billTypeCode : "TZB",// 模块类型编码
        baseParams : {
        // initStates:initStatesStr
        },// 列表数据初始化状态过滤
        listEditStateFn : [ {
            "edit" : true
        }, {
            "view" : true
        }, {
            "delete" : true
        }, {
            "search" : false
        } ],// 在添加固有的方法上再追加操作，参数：当前grid列表，win 当前编辑窗口
        addAfter : function(grid, win) {

        },// 在修改固有的方法上再追加操作，参数：grid当前列表，selectids 当前选记录，win 当前编辑窗口
        editAfter : function(grid, selectids, win) {
            win.formPanel.form.findField("model.authCode").setReadOnly(true);
        },// 在查看固有的方法上再追加操作，参数：grid当前列表，selectids 当前选记录，win 当前编辑窗口
        viewAfter : function(grid, selectids, win) {
        }

    },// 按钮是否可用及编辑查看窗口字段是否可编辑权限控制
    editWin : {
        // addOtherBtn:addbtn1, //在固化的按钮基础上追加按钮
        nextBillState : "New",// 提交后下一步的状态
        maximized : false, // 是否最大化窗口，默认为否
        height : 280,
        width : 420,
        form : {
            items : editFormItems,// 编辑表单字段
            columnWidth : 1
        // 配置表单有几列
        // height:250
        },
        centerTab : {
            items : []
        },/*
             * westTab:{ width:400, //collapsible:true, items:[formDtl1] }, eastTab:{ width:400, height:300,
             * items:[grid2] }, southTab:{ height:200, //collapsible:true, items:[grid3] },
             */
        // formPanel 表单
        submitBefore : function(formPanel) {
            // return false
        },// params组装的参数值,formPanel 表单
        submitAfter : function(params, formPanel) {

        },// params提交的数据,表单
        submitSuccessAfter : function(form, action) {
            // alert("submitSuccessAfter")
        }
    },
    searchWin : {
        checkboxgroup : checkboxgroup,// 查询单据状态参数
        checkboxgroup1 : checkboxgroup1,
        isShowStatus : false,// 是否显示查询状态
        height : 180,
        width : 420,
        form : {
            items : searchFormItems,// 查询表单字段
            columnWidth : 1
        // 配置表单有几列
        },
        searchLoadBefore : function() {
            return true;
        },
        searchReportSubmitBefore : function(cfg1, data) {
            // Q.tips(Q.color("123123123","red"))
        }
    },
    vpInstanceAfert : function() {
        // 隐藏提交按钮
        vp.editWin.on("show", function() {
            var btn = vp.editWin.buttons[1];
            btn.hide();
        });
        // var searchBtn = vp.grid.getTopToolbar().find("name","search")[0];
        // searchBtn.handler();
    },// editWin 编辑窗口 gridVp 列表grid
    // 使用说明 返回值 为 站内链接 使用 用于创建查看 编辑窗, 返回字符串"view" 为创建查看窗口,"edit”为创建编辑窗口
    vpAfterRender : function() {
        return 'view';
    }
};