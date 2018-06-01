/**
 * guonianlun 2016年11月28日09:26:42 合同管理
 */
Ext.ns('Q.terms');
var dealUrl = path + '/ContractTerms';
Q.terms.store = {
    contractType : Q.common.selDictContract(6400),
};

var gridColumn = [ {
    header : "条款编码",
    dataIndex : "termsNum"
}, {
    header : "条款名称",
    dataIndex : "termsName"
}, {
    header : "合同类型",
    dataIndex : "termsTypeName"
}, {
    header : "创建人",
    dataIndex : "createUser"
}, {
    header : "创建日期",
    dataIndex : "createDate",
    renderer : Q.common.dateRenderer
} ];

var editFormItems = [ {
    name : 'model.termsId',
    xtype : "hidden"
}, {
    name : 'model.createDate',
    xtype : "hidden"
}, {
    name : 'model.createUser',
    xtype : "hidden"
}, {
    name : 'model.updateDate',
    xtype : "hidden"
}, {
    name : 'model.updateUser',
    xtype : "hidden"
}, {
    name : 'model.natrue',
    xtype : "hidden",
    value : 1
}, {
    columnWidth : .33,
    anchor : "85%",
    fieldLabel : '条款编码',
    name : 'model.termsNum',
    emptyText : '系统自动生成',
    xtype : "textfield",
    readOnly : true
}, {
    columnWidth : .33,
    anchor : "85%",
    fieldLabel : '条款名称<font color="red">*</font>',
    name : 'model.termsName',
    allowBlank : false,
    xtype : "textfield"
}, {
    columnWidth : .33,
    anchor : "86%",
    fieldLabel : '合同类型<font color="red">*</font>',
    hiddenName : 'model.termsType',
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    allowBlank : false,
    store : Q.terms.store.contractType
}, {
    columnWidth : 1,
    anchor : "94%",
    fieldLabel : '备  注',
    name : 'model.remark',
    xtype : "textarea"
}, {
    columnWidth : 1,
    anchor : "94%",
    xtype : "htmleditor",
    id : "model.content",
    fieldLabel : '条款内容<font color="red">*</font>',
    columnWidth : 1,
    allowBlank : false,
    autoScroll : true,
    height : 400
} ];

var searchFormItems = [ {
    fieldLabel : '条款编码',
    name : "filter_LIKE_termsNum",
    width : 180,
    xtype : "textfield"
}, {
    fieldLabel : '条款名称',
    name : "filter_LIKE_termsName",
    width : 180,
    xtype : "textfield"
}, {
    fieldLabel : '合同类型',
    hiddenName : 'filter_EQ_termsType',
    xtype : "uxcombo",
    width : 180,
    valueField : "value",
    displayField : "name",
    anchor : '95.6%',
    editable : true,
    store : Q.terms.store.contractType,
    clearable : true
}, {
    fieldLabel : '创建日期',
    width : 180,
    name : "filter_GE_createDate",
    format : 'Y-m-d',
    xtype : "datefield",
    editable : false,
    id : "createBeginDate",
    listeners : {
        select : function() {
            var createBeginDate = Ext.getCmp('createBeginDate').getValue();
            var createEndDate = Ext.getCmp("createEndDate").getValue();
            if (createBeginDate && createEndDate) {
                if (createEndDate < createBeginDate) {
                    Q.warning("创建日期不能大于结束日期");
                    Ext.getCmp('createBeginDate').setValue("");
                }
            }
        }
    }
}, {
    fieldLabel : '至',
    width : 180,
    name : "filter_LE_createDate",
    format : 'Y-m-d',
    xtype : "datefield",
    editable : false,
    id : "createEndDate",
    listeners : {
        select : function() {
            var createBeginDate = Ext.getCmp('createBeginDate').getValue();
            var createEndDate = Ext.getCmp("createEndDate").getValue();
            if (createBeginDate && createEndDate) {
                if (createEndDate < createBeginDate) {
                    Q.warning("结束日期不能小于创建日期");
                    Ext.getCmp('createEndDate').setValue("");
                }
            }
        }
    }
}, {
    fieldLabel : '创建人',
    name : "filter_LIKE_createUser",
    width : 180,
    xtype : "textfield"
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '条款管理',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        hideBtn : [ "delete" ],
        store : {
            idProperty : 'termsId',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        },
        subTab : [],
        listEditStateFn : [ {
            'edit' : function(r) {
                return true;
            }
        } ]
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 70,
            items : editFormItems,
            columnWidth : '1'
        },
        submitBefore : function(formPanel) {
            var content = formPanel.getForm().findField("model.content").getValue();
            if (!content) {
                Q.error("条款内容不可为空！");
                return false;
            }
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 260,
        width : 320,
        form : {
            labelWidth : 80,
            items : searchFormItems,
            columnWidth : '1'
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};