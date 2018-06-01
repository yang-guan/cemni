/**
 * 客户权益单
 * 
 * @author：WangYuanJun
 */
Ext.ns('Q.afterservice');
var dealUrl = path + '/RightMaint';

Q.afterservice.selStores = {
    // 处理状态
    handlestateStore : Q.common.selDict(4100),
    // 紧急程度
    urgencylevelStore : Q.common.selDict(4105),
    // 投诉等级
    complaintlevelStore : Q.common.selDict(4103),
    // 问题类型
    probtypeStore : Q.common.selDict(4102),
    // 审核状态
    reviewstateStore : Q.common.selDict(4104),

    // 投诉类型
    complainttypeStore : new Ext.data.JsonStore({
        url : path + "/Dict_selDict.action?dictCode=4101",
        fields : [ "name", "value", "dictId" ],
        autoLoad : true
    }),

    // 投诉等级2
    complaintlevelStoreByParent : new Ext.data.JsonStore({
        url : path + "/Dict_selDictByParentId.action",
        fields : [ "name", "value", "dictId" ],
        autoLoad : false
    }),

    // 问题类型2
    probtypeStoreByParent : new Ext.data.JsonStore({
        url : path + "/Dict_selDictByParentId.action",
        fields : [ "name", "value", "dictId" ],
        autoLoad : false
    }),
};

// 列表
var gridColumn = [ {
    dataIndex : "reviewState",
    hidden : true
}, {
    header : "客户权益单号",
    width : 100,
    dataIndex : "complaintNo"
}, {
    header : "会员卡号",
    width : 90,
    dataIndex : "individCust",
    renderer : function(v) {
        if (!!v) {
            return v.cardNo;
        }
    }
}, {
    header : "客户名称",
    width : 80,
    dataIndex : "individCust",
    renderer : function(v) {
        if (!!v) {
            return v.name;
        }
    }
}, {
    header : "手机号码",
    width : 90,
    dataIndex : "individCust",
    renderer : function(v) {
        if (!!v) {
            return v.mobile;
        }
    }
}, {
    header : "投诉日期",
    width : 80,
    dataIndex : "complaintDate",
    renderer : Q.common.dateRenderer
}, {
    header : "投诉类型",
    width : 80,
    dataIndex : "complaintTypeName"
}, {
    header : "问题类型",
    width : 80,
    dataIndex : "probTypeName"
}, {
    header : "处理状态",
    width : 80,
    dataIndex : "handleStateName"
}, {
    header : "受理门店/部门",
    width : 120,
    dataIndex : "orgStoreName"
}, {
    header : "审核状态",
    width : 80,
    dataIndex : "reviewStateName"
} ];

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 表单
var editFormItems = [ {
    title : '投诉信息',
    xtype : 'fieldset',
    anchor : "-3",
    layout : 'column',
    columnWidth : 1,
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.rightMaintId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.individCust.individCustId',
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
            name : 'model.org.orgId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.store.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.storeNo',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.orgCode',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.posOrder.posId',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '权益单号<font color="red">*</font>',
            name : 'model.complaintNo',
            xtype : "textfield",
            emptyText : '系统自动生成',
            readOnly : true,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '投诉日期',
            xtype : "datefield",
            format : "Y-m-d",
            name : 'model.complaintDate',
            value : new Date(),
            anchor : "85%",
            editable : false
        }
    }, {
        items : {
            fieldLabel : '处理状态',
            name : "model.handleState",
            hiddenName : "model.handleState",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            value : 1,
            store : Q.afterservice.selStores.handlestateStore,
            anchor : "85.5%"
        }
    }, {
        items : {
            fieldLabel : '会员卡号<font color="red">*</font>',
            name : 'model.individCust.cardNo',
            xtype : "textfield",
            anchor : "85%",
            allowBlank : false,
            readOnly : true,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.individCust.cardNo").disabled) {
                            return;
                        }
                        var _win = new Q.archive.chooseIndiWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            _form.findField("model.individCust.individCustId").setValue(json.individCustId);
                            _form.findField("model.individCust.cardNo").setValue(json.cardNo);
                            _form.findField("model.individCust.name").setValue(json.name);
                            _form.findField("model.individCust.mobile").setValue(json.mobile);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '客户名称 ',
            name : 'model.individCust.name',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '手机号码',
            name : 'model.individCust.mobile',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '购买日期',
            name : 'model.posOrder.posbillDate',
            xtype : "textfield",
            emptyText : '请选择',
            readOnly : true,
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.posOrder.posbillDate").disabled || !_form.findField("model.individCust.cardNo").getValue()) {
                            return;
                        }
                        var _win = new Q.afterservice.chooseOrderWin({
                            cardNo : _form.findField("model.individCust.cardNo").getValue()
                        });
                        _win.on("submit", function(r) {
                            var json = r.json;
                            _form.findField("model.posOrder.posId").setValue(json.posId);
                            _form.findField("model.posOrder.posbillDate").setValue(Ext.util.Format.date(json.posbillDate, 'Y-m-d'));
                            _form.findField("model.posOrder.storeName").setValue(json.storeName);
                            _form.findField("model.posOrder.goodsBar").setValue(json.goodsBar);
                            _form.findField("model.posOrder.goodsName").setValue(json.goodsName);
                            _form.findField("model.posOrder.actualSaleAmount").setValue(json.actualSaleAmount);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '购买门店',
            name : 'model.posOrder.storeName',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '商品条码',
            name : 'model.posOrder.goodsBar',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '购买产品',
            name : 'model.posOrder.goodsName',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '购买金额',
            id : 'posActualSaleAmount',
            name : 'model.posOrder.actualSaleAmount',
            anchor : "85%",
            readOnly : true,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '紧急程度<font color="red">*</font>',
            name : "model.urgencyLevel",
            hiddenName : "model.urgencyLevel",
            xtype : "uxcombo",
            valueField : "value",
            allowBlank : false,
            displayField : "name",
            anchor : "85.5%",
            store : Q.afterservice.selStores.urgencylevelStore
        }
    }, {
        items : {
            fieldLabel : '投诉类型<font color="red">*</font>',
            id : "complaintType",
            name : "model.complaintType",
            hiddenName : "model.complaintType",
            xtype : "uxcombo",
            allowBlank : false,
            valueField : "value",
            displayField : "name",
            anchor : "85.5%",
            store : Q.afterservice.selStores.complainttypeStore,
            listeners : {
                select : function(c, r, i) {
                    Ext.getCmp('complaintLevel').reset();
                    Q.afterservice.selStores.complaintlevelStoreByParent.removeAll();
                    Q.afterservice.selStores.complaintlevelStoreByParent.baseParams = {
                        "parentId" : r.get("dictId")
                    };
                    Q.afterservice.selStores.complaintlevelStoreByParent.load();

                    Ext.getCmp('probType').reset();
                    Q.afterservice.selStores.probtypeStore.removeAll();
                }
            }
        }
    }, {
        items : {
            id : 'complaintLevel',
            fieldLabel : '投诉等级<font color="red">*</font>',
            name : "model.complaintLevel",
            hiddenName : "model.complaintLevel",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            allowBlank : false,
            anchor : "85.5%",
            store : Q.afterservice.selStores.complaintlevelStoreByParent,
            listeners : {
                select : function(c, r, i) {
                    Ext.getCmp('probType').reset();
                    Q.afterservice.selStores.probtypeStoreByParent.removeAll();
                    Q.afterservice.selStores.probtypeStoreByParent.baseParams = {
                        "parentId" : r.get('dictId')
                    };
                    Q.afterservice.selStores.probtypeStoreByParent.load();
                }
            }
        }
    }, {
        items : {
            id : 'probType',
            fieldLabel : '问题类型<font color="red">*</font>',
            name : "model.probType",
            hiddenName : "model.probType",
            xtype : "uxcombo",
            valueField : "value",
            allowBlank : false,
            displayField : "name",
            anchor : "85.5%",
            store : Q.afterservice.selStores.probtypeStoreByParent
        }
    }, {
        items : {
            fieldLabel : '处理门店<font color="red">*</font>',
            name : 'model.orgStoreName',
            xtype : "textfield",
            allowBlank : false,
            anchor : "85%",
            readOnly : true,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.orgStoreName").disabled) {
                            return;
                        }
                        var _win = new Q.afterservice.chooseOrgStoreWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            var orgStoreName = r.json.orgStoreTypeName;
                            if (orgStoreName == '门店') {
                                _form.findField("model.store.storeId").setValue(json.orgStoreId);
                                _form.findField("model.org.orgId").setValue(null);
                                _form.findField("model.storeNo").setValue(json.orgStoreCode);
                                _form.findField("model.orgCode").setValue(null);
                            } else {
                                _form.findField("model.org.orgId").setValue(json.orgStoreId);
                                _form.findField("model.store.storeId").setValue(null);
                                _form.findField("model.orgCode").setValue(json.orgStoreCode);
                                _form.findField("model.storeNo").setValue(null);
                            }
                            _form.findField("model.orgStoreName").setValue(json.orgStoreName);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '处理日期',
            name : 'model.handleDate',
            xtype : "datefield",
            format : "Y-m-d",
            anchor : "85%",
            editable : false
        }
    }, {
        items : {
            fieldLabel : '审核状态',
            name : "model.reviewState",
            hiddenName : "model.reviewState",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            readOnly : true,
            value : 1,
            anchor : "85%",
            store : Q.afterservice.selStores.reviewstateStore
        }
    }, {
        columnWidth : 1,
        items : [ {
            fieldLabel : '问题简要说明',
            name : 'model.probExplain',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }, {
            fieldLabel : '客户处理建议',
            name : 'model.customerAdvice',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }, {
            fieldLabel : '处理方式及结果',
            name : 'model.handleTypeResult',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        } ]
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
}, {
    columnWidth : 1,
    anchor : "-3",
    xtype : 'fieldset',
    title : '本案例处理分析',
    layout : 'form',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '投诉产生原因',
            name : 'model.complaintReason',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : {
            fieldLabel : '投诉处理过程',
            name : 'model.complaintProcess',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : {
            fieldLabel : '投诉处理措施',
            name : 'model.complaintStep',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : {
            fieldLabel : '最终处理结果',
            name : 'model.finalResult',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    } ]
}, {
    columnWidth : 1,
    anchor : "-3",
    xtype : 'fieldset',
    title : '责任部门/人员',
    layout : 'form',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '原因分析',
            name : 'model.causeAnalysis',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : {
            fieldLabel : '改善策略',
            name : 'model.improveStrategy',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    } ]
} ];

// ////////////////////////////////////////////////////查询///////////////////////////////////////////////////////////

var gridDtl1 = {
    tabTitle : "审核情况",
    xtype : 'uxeditorgrid',
    foreignKey : "rightMaint_rightMaintId",
    tabClassName : "rightMaintAudit",
    pageSize : 0,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "审核情况ID",
            dataIndex : "rightMaintAuditId",
            disabled : true
        }, {
            header : "核定人",
            dataIndex : "auditor"
        }, {
            header : "核定时间",
            dataIndex : "auditTime",
            renderer : Q.common.timeRenderer
        }, {
            header : "核定结果",
            dataIndex : "resultName"
        }, {
            header : "核定意见",
            dataIndex : "suggest"
        } ]
    },
    store : {
        idProperty : "rightMaintAuditId",
        url : dealUrl + "_getRightMaintAudit.action",
        sort : "rightMaintAuditId",
        dir : "desc",
        autoLoad : false
    }
};

var cfg = {
    isAudit : true,
    dealUrl : dealUrl,
    moduleName : "客户权益单",
    playListMode : playListMode.normal,
    menuOverride : [ {
        text : "提交",
        name : 'CONFIRM',
        iconCls : "icon-toConfirm",
        hidden : true,
        handler : function() {
            vp.dealstate(this.name, this.text);
        }
    } ],
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        store : {
            idProperty : 'rightMaintId',
            url : dealUrl + '_getJson.action',
            sort : "rightMaintId",
            dir : "desc"
        },
        listEditStateFn : [ {
            edit : function(r) {
                var state = r.get('reviewState');
                if (state == 2 || state == 3 || state == 4) {
                    return false;
                } else {
                    return true;
                }
            }
        } ],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.posOrder.posbillDate").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = true;
            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.disable();
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.posOrder.posbillDate").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.hide();
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.posOrder.posbillDate").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.disable();
        }
    },

    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 100,
            items : editFormItems,
            columnWidth : '0.5',
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();

                // 处理的是门店
                if (!!_form.findField("model.store.storeId").getValue()) {
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.store.name").getValue());
                } else {
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.org.name").getValue());
                }

                // 回填-购买日期
                var posbillDate = _form.findField('model.posOrder.posbillDate').getValue();
                _form.findField('model.posOrder.posbillDate').setValue(Ext.util.Format.date(posbillDate, 'Y-m-d'));

                // 下拉框级联
                var _complaintType = _form.findField('model.complaintType').getValue();
                var _complaintLevel = _form.findField('model.complaintLevel').getValue();
                var _probType = _form.findField('model.probType').getValue();
                // 回填-投诉等级
                if (_complaintType != "") {
                    var k = Q.afterservice.selStores.complainttypeStore.find('value', _complaintType);
                    Q.afterservice.selStores.complaintlevelStoreByParent.baseParams = {
                        "parentId" : Q.afterservice.selStores.complainttypeStore.getAt(k).get('dictId')
                    };
                    Q.afterservice.selStores.complaintlevelStoreByParent.load({
                        callback : function(record, options, success) {
                            _form.findField("model.complaintLevel").setValue(_complaintLevel);
                            // 回填-问题类型
                            if (_complaintLevel != "") {
                                var k2 = Q.afterservice.selStores.complaintlevelStoreByParent.find('value', _complaintLevel);
                                Q.afterservice.selStores.probtypeStoreByParent.baseParams = {
                                    "parentId" : Q.afterservice.selStores.complaintlevelStoreByParent.getAt(k2).get('dictId')
                                };
                                Q.afterservice.selStores.probtypeStoreByParent.load({
                                    callback : function() {
                                        _form.findField("model.probType").setValue(_probType);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        },
        centerTab : {
            items : [ gridDtl1 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 300,
        width : 600,
        form : {
            labelWidth : 90,
            columnWidth : 0.5,
            items : [ {
                fieldLabel : '客户权益单号',
                name : "filter_LIKE_complaintNo",
                xtype : "textfield"
            }, {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_individCust_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_individCust_name",
                xtype : "textfield"
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_individCust_mobile",
                xtype : "textfield"
            }, {
                fieldLabel : '购买门店',
                name : "filter_LIKE_store_name",
                xtype : "textfield"
            }, {
                fieldLabel : '商品条码',
                name : "filter_LIKE_posOrder_goodsBar",
                xtype : "textfield"
            }, {
                fieldLabel : '投诉类型',
                hiddenName : "filter_EQ_complaintType",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.afterservice.selStores.complainttypeStore,
                clearable : true,
                listeners : {
                    select : function(c, r, i) {
                        var _form = vp.searchWin.formPanel.getForm();
                        _form.findField('filter_EQ_complaintLevel').reset();
                        Q.afterservice.selStores.complaintlevelStoreByParent.removeAll();
                        Q.afterservice.selStores.complaintlevelStoreByParent.baseParams = {
                            "parentId" : r.get("dictId")
                        };
                        Q.afterservice.selStores.complaintlevelStoreByParent.load();
                        _form.findField('filter_EQ_probType').reset();
                        Q.afterservice.selStores.probtypeStoreByParent.removeAll();
                    },
                    "clear" : function(_form) {
                        var _form = vp.searchWin.formPanel.getForm();
                        _form.findField('filter_EQ_complaintType').reset();
                        _form.findField('filter_EQ_complaintLevel').reset();
                        _form.findField('filter_EQ_probType').reset();
                        Q.afterservice.selStores.complaintlevelStoreByParent.removeAll();
                        Q.afterservice.selStores.probtypeStoreByParent.removeAll();
                    }
                }
            }, {
                fieldLabel : '投诉等级',
                hiddenName : "filter_EQ_complaintLevel",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.afterservice.selStores.complaintlevelStoreByParent,
                clearable : true,
                listeners : {
                    select : function(c, r, i) {
                        var _form = vp.searchWin.formPanel.getForm();
                        _form.findField('filter_EQ_probType').reset();
                        Q.afterservice.selStores.probtypeStoreByParent.removeAll();
                        Q.afterservice.selStores.probtypeStoreByParent.baseParams = {
                            "parentId" : r.get('dictId')
                        };
                        Q.afterservice.selStores.probtypeStoreByParent.load();
                    },
                    "clear" : function(_form) {
                        var _form = vp.searchWin.formPanel.getForm();
                        _form.findField('filter_EQ_complaintLevel').reset();
                        _form.findField('filter_EQ_probType').reset();
                        Q.afterservice.selStores.probtypeStoreByParent.removeAll();
                    }
                }
            }, {
                fieldLabel : '问题类型',
                hiddenName : "filter_EQ_probType",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.afterservice.selStores.probtypeStoreByParent,
                clearable : true
            }, {
                fieldLabel : '紧急程度',
                hiddenName : "filter_EQ_urgencyLevel",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.afterservice.selStores.urgencylevelStore,
                clearable : true
            }, {
                fieldLabel : "投诉日期",
                name : 'filter_GE_complaintDate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_complaintDate = _form.findField('filter_GE_complaintDate').getValue();
                        var filter_LE_complaintDate = _form.findField("filter_LE_complaintDate").getValue();
                        if (filter_GE_complaintDate && filter_LE_complaintDate) {
                            if (filter_GE_complaintDate > filter_LE_complaintDate) {
                                Q.warning("开始日期不能大于结束日期！");
                                _form.findField('filter_GE_complaintDate').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "至",
                name : 'filter_LE_complaintDate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_complaintDate = _form.findField('filter_GE_complaintDate').getValue();
                        var filter_LE_complaintDate = _form.findField("filter_LE_complaintDate").getValue();
                        if (filter_GE_complaintDate && filter_LE_complaintDate) {
                            if (filter_GE_complaintDate > filter_LE_complaintDate) {
                                Q.warning("结束日期不能小于开始日期！");
                                _form.findField('filter_LE_complaintDate').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : '处理状态',
                hiddenName : "filter_EQ_handleState",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.afterservice.selStores.handlestateStore,
                clearable : true
            }, {
                fieldLabel : '处理门店',
                name : "filter_LIKE_store_name_OR_LIKE_org_name",
                xtype : "textfield"
            } ]
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0].hide();
        });
    }
};