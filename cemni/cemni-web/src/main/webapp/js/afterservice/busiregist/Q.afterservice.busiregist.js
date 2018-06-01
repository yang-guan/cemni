/**
 * 客户业务登记
 * 
 * @author：WangYuanJun
 */
Ext.ns('Q.afterservice');
var dealUrl = path + '/BusiRegist';
var jsArr = [ path + "/js/archive/individcust/Q.archive.chooseIndiWin.js", path + "/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js" ];
Q.loadJs(jsArr);

Q.afterservice.selStores = {
    // 受理状态
    acceptStateStore : Q.common.selDict(4200),
    // 业务类型
    businessTypeStore : Q.common.selDict(4201),
};

// 列表
var gridColumn = [ {
    header : "登记单号",
    sortable : false,
    width : 120,
    dataIndex : "registerNo"
}, {
    header : "登记日期",
    sortable : false,
    dataIndex : "registerDate",
    renderer : Q.common.dateRenderer
}, {
    header : "会员卡号",
    sortable : false,
    dataIndex : "cardNo"
}, {
    header : "客户名称",
    sortable : false,
    dataIndex : "custName"
}, {
    header : "手机号码",
    sortable : false,
    dataIndex : "custMobile"
}, {
    header : "业务类型",
    sortable : false,
    dataIndex : "businessTypeName"
}, {
    header : "处理门店/部门",
    sortable : false,
    width : 200,
    dataIndex : "handleStoreOrgName"
}, {
    header : "待受理门店/部门",
    sortable : false,
    width : 200,
    dataIndex : "acceptStoreorgName"
}, {
    header : "受理人员",
    sortable : false,
    dataIndex : "acceptPerson"
}, {
    header : "受理状态",
    sortable : false,
    dataIndex : "acceptStateName"
} ];

// 表单
var editFormItems = [ {
    title : '基础信息',
    xtype : 'fieldset',
    layout : 'column',
    columnWidth : 1,
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.busiRegistId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.individCust.individCustId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.store.storeId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.store.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.orgId',
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
            name : 'model.acceptStore.storeId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.acceptStore.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.acceptOrg.orgId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.acceptOrg.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.acceptStoreNo',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.acceptOrgCode',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '登记单号<font color="red">*</font>',
            name : 'model.registerNo',
            emptyText : '系统自动生成',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '登记日期',
            xtype : "datefield",
            format : "Y-m-d",
            name : 'model.registerDate',
            anchor : "85%",
            value : new Date(),
            editable : false
        }
    }, {
        items : {
            fieldLabel : '待受理门店',
            name : 'model.acceptOrgStoreName',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.acceptOrgStoreName").disabled) {
                            return;
                        }
                        var _win = new Q.afterservice.chooseOrgStoreWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            var orgStoreName = r.json.orgStoreTypeName;
                            if (orgStoreName == '门店') {
                                _form.findField("model.acceptStore.storeId").setValue(json.orgStoreId);
                                _form.findField("model.acceptOrg.orgId").setValue(null);
                                _form.findField("model.acceptStoreNo").setValue(json.orgStoreCode);
                                _form.findField("model.acceptOrgCode").setValue(null);
                            } else {
                                _form.findField("model.acceptOrg.orgId").setValue(json.orgStoreId);
                                _form.findField("model.acceptStore.storeId").setValue(null);
                                _form.findField("model.acceptOrgCode").setValue(json.orgStoreCode);
                                _form.findField("model.acceptStoreNo").setValue(null);
                            }
                            _form.findField("model.acceptOrgStoreName").setValue(json.orgStoreName);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '会员卡号',
            name : 'model.individCust.cardNo',
            xtype : "textfield",
            anchor : "85%",
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
                            _form.findField("model.custname").setValue(json.name);
                            _form.findField("model.custMobile").setValue(json.mobile);
                            _form.findField("model.custname").disable();
                            _form.findField("model.custMobile").disable();
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '客户名称<font color="red">*</font>',
            name : 'model.custname',
            xtype : "textfield",
            allowBlank : false,
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '手机号码<font color="red">*</font>',
            name : 'model.custMobile',
            xtype : "numberfield",
            allowBlank : false,
            anchor : "85%",
            regex : /^(1\d{10})$/,
            regexText : "请输入正确的电话号码"
        }
    }, {
        items : {
            fieldLabel : '处理门店<font color="red">*</font>',
            name : 'model.orgStoreName',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true,
            allowBlank : false,
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
            fieldLabel : '受理人员',
            name : 'model.acceptPerson',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 10
        }
    }, {
        items : {
            fieldLabel : '受理状态',
            hiddenName : 'model.acceptState',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            value : 1,
            anchor : "85.5%",
            store : Q.afterservice.selStores.acceptStateStore
        }
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '受理信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '业务类型',
            hiddenName : 'model.businessType',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            anchor : "85.5%",
            store : Q.afterservice.selStores.businessTypeStore
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
        columnWidth : 1,
        items : {
            fieldLabel : '业务内容',
            name : 'model.businessContent',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '处理结果',
            name : 'model.handleResult',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    } ]
} ];

var addbtn1 = [ {
    powerEffect : true,
    displayType : [ "add" ],
    text : "个人档案新建",
    build : true,
    iconCls : "icon-add",
    handler : function(_self) {
        Ext.Ajax.request({
            url : dealUrl + "_linkIndividCust.action",
            success : function(response) {
                if (!Ext.decode(response.responseText).success) {
                    Q.error("<font color=''>" + $("message.submit.failure") + $("message.system.error"));
                    return;
                } else {
                    var data = Ext.decode(response.responseText).data;
                    var json = {
                        id : data.moduleId,
                        url : data.link,
                        iconCls : data.icon,
                        title : data.moduleName
                    };
                    window.parent.showPage(json);
                }
            },
            failure : function() {
                Q.error($("message.submit.failure"));
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '客户业务登记单',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        store : {
            idProperty : 'busiRegistId',
            url : dealUrl + '_getJson.action',
            sort : "busiRegistId",
            dir : "desc"
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                if (r.json.acceptState == 2) {
                    return false;
                } else {
                    return true;
                }
            }
        } ],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.acceptOrgStoreName").disabled = true;
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.acceptOrgStoreName").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.custname").setDisabled(false);
            vp.editWin.formPanel.getForm().findField("model.custMobile").setDisabled(false);
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.acceptOrgStoreName").disabled = false;
        }
    },
    editWin : {
        addOtherBtn : addbtn1,
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 80,
            items : editFormItems,
            columnWidth : '0.5',
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();
                if (!!_form.findField("model.individCust.individCustId").getValue()) {
                    // 登记人是会员
                    _form.findField("model.custname").setDisabled(true);
                    _form.findField("model.custMobile").setDisabled(true);
                } else {
                    _form.findField("model.custname").setDisabled(false);
                    _form.findField("model.custMobile").setDisabled(false);
                }
                if (!!_form.findField("model.store.storeId").getValue()) {
                    // 处理的是门店
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.store.name").getValue());
                } else {
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.org.name").getValue());
                }
                if (!!_form.findField("model.acceptStore.storeId").getValue()) {
                    // 待受理的是门店
                    _form.findField("model.acceptOrgStoreName").setValue(_form.findField("model.acceptStore.name").getValue());
                } else {
                    _form.findField("model.acceptOrgStoreName").setValue(_form.findField("model.acceptOrg.name").getValue());
                }
            }
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 240,
        width : 600,
        form : {
            labelWidth : 80,
            columnWidth : 0.5,
            items : [ {
                fieldLabel : '登记单号',
                name : "filter_LIKE_registerNo",
                xtype : "textfield"
            }, {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_individCust_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_individCust_name_OR_LIKE_custname",
                xtype : "textfield"
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_individCust_mobile_OR_LIKE_custMobile",
                xtype : "numberfield"
            }, {
                fieldLabel : '处理门店',
                name : "filter_LIKE_store_name_OR_LIKE_org_name",
                xtype : "textfield"
            }, {
                fieldLabel : '待受理门店',
                name : "filter_LIKE_acceptStore_name_OR_LIKE_acceptOrg_name",
                xtype : "textfield"
            }, {
                fieldLabel : '登记日期',
                name : "filter_GE_registerDate",
                xtype : "datefield",
                format : "Y-m-d",
                editable : false
            }, {
                fieldLabel : '至',
                name : "filter_LE_registerDate",
                xtype : "datefield",
                format : "Y-m-d",
                editable : false
            }, {
                fieldLabel : '业务类型',
                hiddenName : 'filter_EQ_businessType',
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.6%",
                store : Q.afterservice.selStores.businessTypeStore,
                clearable : true,
            }, {
                fieldLabel : '处理状态',
                hiddenName : 'filter_EQ_acceptState',
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.6%",
                store : Q.afterservice.selStores.acceptStateStore,
                clearable : true,
            } ]
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};