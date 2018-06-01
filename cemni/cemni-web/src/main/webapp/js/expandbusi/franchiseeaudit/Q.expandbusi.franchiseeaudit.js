/**
 * 加盟商稽核管理
 * 
 * @author：WangYuanJun
 * @date：2016年12月14日 上午10:10:29
 */
Ext.ns('Q.expandbusi');
var dealUrl = path + '/FranchiseeAudit';
var jsArr = [ path + "/js/archive/franchisee/Q.archive.chooseFranWin.js", path + "/js/console/store/Q.store.chooseStoreWin.js", path + "/plugins/UploadDialog/Neu.ux.UploadDialog.js" ];
Q.loadJs(jsArr);

Q.expandbusi.selStores = {
    // 稽核问题数量
    auditProbNumStore : Q.common.selDict(7500),
    // 加盟商类型
    typeFraStore : Q.common.selDict(1300),
    // 业务部门
    sourceFraStore : Q.common.selDict(1302),
};

// 列表
var gridColumn = [ {
    header : "加盟商编码",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraCode;
        }
    }
}, {
    header : "加盟商名称",
    width : 200,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraName;
        }
    }
}, {
    header : "加盟商类别",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraTypeName;
        }
    }
}, {
    header : "业务部门",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.sourcesName;
        }
    }
}, {
    header : "业务人员",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.intMan;
        }
    }
}, {
    header : "城市",
    width : 150,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.city;
        }
    }
}, {
    header : "法人代表",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.legalre;
        }
    }
}, {
    header : "创建日期",
    width : 120,
    dataIndex : "createDate",
    renderer : Q.common.dateRenderer
} ];

var editFormItems = [ {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '基础信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.franchiseeAuditId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.franchisee.franchiseeId',
            xtype : "hidden"
        }
    }, {
        items : {
            id : 'storeId',
            name : 'model.store.storeId',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '加盟商编码<font color="red">*</font>',
            allowBlank : false,
            name : 'model.franchisee.fraCode',
            xtype : "textfield",
            readOnly : true,
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        if (vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled) {
                            return;
                        }
                        var _win = new Q.archive.chooseFranWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            var _form = vp.editWin.formPanel.getForm();
                            _form.findField('model.franchisee.franchiseeId').setValue(json.franchiseeId);
                            _form.findField('model.franchisee.fraCode').setValue(json.fraCode);
                            _form.findField('model.franchisee.fraName').setValue(json.fraName);

                            if (json.sourcesName == null || json.sourcesName == '') {
                                _form.findField('model.franchisee.sourcesName').setValue('');
                            } else {
                                _form.findField('model.franchisee.sourcesName').setValue(json.sourcesName);
                            }
                            if (json.fraTypeName == null || json.fraTypeName == '') {
                                _form.findField('model.franchisee.fraTypeName').setValue('');
                            } else {
                                _form.findField('model.franchisee.fraTypeName').setValue(json.fraTypeName);
                            }
                            _form.findField('model.franchisee.intMan').setValue(json.intMan);
                            _form.findField('model.franchisee.city').setValue(json.city);
                            _form.findField('model.franchisee.legalre').setValue(json.legalre);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '加盟商名称<font color="red">*</font>',
            allowBlank : false,
            xtype : "textfield",
            name : 'model.franchisee.fraName',
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '加盟商类别',
            name : 'model.franchisee.fraTypeName',
            anchor : "85%",
            xtype : "textfield",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '业务部门',
            name : 'model.franchisee.sourcesName',
            anchor : "85%",
            xtype : "textfield",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '业务人员',
            xtype : "textfield",
            name : 'model.franchisee.intMan',
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '城市',
            xtype : "textfield",
            name : 'model.franchisee.city',
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '法人代表',
            xtype : "textfield",
            name : 'model.franchisee.legalre',
            anchor : "85%",
            readOnly : true
        }
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '稽核信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '稽核日期<font color="red">*</font>',
            allowBlank : false,
            name : 'model.auditDate',
            xtype : "datefield",
            format : "Y-m-d",
            anchor : "85%",
            editable : false
        }
    }, {
        items : {
            fieldLabel : '稽核人员<font color="red">*</font>',
            allowBlank : false,
            name : 'model.auditPerson',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 10
        }
    }, {
        items : {
            fieldLabel : '稽核门店<font color="red">*</font>',
            allowBlank : false,
            name : 'model.store.name',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.store.name").disabled) {
                            return;
                        }
                        var _win = new Q.store.chooseStoreWin({
                            action : 'franchiseeAudit',
                            franchiseeId : _form.findField("model.franchisee.franchiseeId").getValue(),
                        });
                        _win.on("submit", function(r) {
                            var json = r.json;
                            _form.findField("model.store.storeId").setValue(json.storeId);
                            _form.findField("model.store.name").setValue(json.name);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '稽核问题数量<font color="red">*</font>',
            allowBlank : false,
            name : 'model.auditProbNum',
            hiddenName : "model.auditProbNum",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            anchor : "85.5%",
            store : Q.expandbusi.selStores.auditProbNumStore
        }
    }, {
        items : {
            xtype : 'checkboxgroup',
            anchor : "96%",
            name : 'auditparts',
            fieldLabel : '稽核问题',
            items : [ {
                boxLabel : '品牌'
            }, {
                boxLabel : '渠道'
            }, {
                boxLabel : '产品'
            }, {
                boxLabel : '客户'
            }, {
                boxLabel : '团队'
            } ]
        }
    }, {
        items : {
            name : 'model.auditPart',
            xtype : "hidden"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '稽核内容',
            name : 'model.auditContent',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        columnWidth : 1,
        items : {
            columnWidth : 1,
            fieldLabel : '备注',
            name : 'model.remark',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : [ {
            fieldLabel : $("label.Annex"),// 上传附件
            xtype : "button",
            anchor : "85%",
            Qtext : "添加附件",
            name : 'uploadBtn',
            id : "updateload",
            text : $("label.AddUpLoadFile"),
            columnWidth : .5,
            handler : function() {
                var formPanel = vp.editWin.formPanel;
                var uploadFileGroupId = formPanel.form.findField("model.uploadFileGroupId").getValue();
                vp.openUploadWindows("", uploadFileGroupId, "uploadFile4View", formPanel, "");
            }
        }, {
            QfieldLabel : '附件上传ID',
            name : 'model.uploadFileGroupId',
            xtype : "hidden"
        }, {
            xtype : 'displayfield',
            name : 'uploadFile4View',
            QfieldLabel : '附件查看',
            fieldLabel : $("label.AttachmentsCheck")
        } ]
    } ]
} ];

// 查询表单
var searchFormItems = [ {
    fieldLabel : '加盟商名称',
    name : "filter_LIKE_franchisee_fraName",
    xtype : "textfield"
}, {
    fieldLabel : '加盟商类别',
    hiddenName : "filter_EQ_franchisee_fraType",
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    anchor : "95.6%",
    store : Q.expandbusi.selStores.typeFraStore,
    clearable : true,
}, {
    fieldLabel : '业务部门',
    hiddenName : "filter_EQ_franchisee_sources",
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    anchor : "95.6%",
    store : Q.expandbusi.selStores.sourceFraStore,
    clearable : true,
}, {
    fieldLabel : '业务人员',
    name : "filter_LIKE_franchisee_intMan",
    xtype : "textfield"
}, {
    fieldLabel : '引入城市',
    name : "filter_LIKE_franchisee_city",
    xtype : "textfield"
}, {
    fieldLabel : '法人代表',
    name : "filter_LIKE_franchisee_legalre",
    xtype : "datefield",
    xtype : "textfield"
}, {
    fieldLabel : '创建日期',
    name : "filter_GE_createDate",
    xtype : "datefield",
    format : "Y-m-d",
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
    fieldLabel : '到',
    name : "filter_LE_createDate",
    xtype : "datefield",
    format : "Y-m-d",
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
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '加盟商稽核',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        store : {
            idProperty : 'franchiseeAuditId',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        // autoLoad : false
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                return true;
            }
        }, {
            'view' : true
        }, {
            'delete' : true
        } ],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.store.name").disabled = true;
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.store.name").disabled = false;
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.store.name").disabled = false;

        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        listeners : {},
        form : {
            labelWidth : 85,
            items : editFormItems,
            height : 300,
            setFormValueAfter : function(formPanel) {
                var arrAuditPart = formPanel.getForm().findField("model.auditPart").getValue().split('');
                var auditParts = formPanel.getForm().findField("auditparts").items;
                for (var i = 0; i < auditParts.length; i++) {
                    if (arrAuditPart[i] == '0') {
                        auditParts.items[i].setValue(false);
                    } else {
                        auditParts.items[i].setValue(true);
                    }
                }
            }
        },
        submitBefore : function(formPanel) {
            var auditParts = formPanel.getForm().findField("auditparts").items;
            var auditPart = '';
            for (var i = 0; i < auditParts.length; i++) {
                if (auditParts.get(i).checked) {
                    auditPart += '1';
                } else {
                    auditPart += '0';
                }
            }
            formPanel.getForm().findField("model.auditPart").setValue(auditPart);
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 320,
        width : 300,
        form : {
            labelWidth : 70,
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