/**
 * 公司
 * 
 * @author：yuhb
 * @date：2016年12月7日 上午10:51:55
 */
Ext.ns('Q.company');
Q.company.editWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "公司",
        layout : "fit",
        items : [ _formPanel ],
        width : 400,
        height : 400
    }, config || {});
    Q.company.editWin.superclass.constructor.call(this, _config);
    this.setFormValue(config);
};

Q.company.selStores = {
    // 省
    provinceStore : new Ext.data.JsonStore({
        url : path + "/Common_selArea.action?filter_EQ_parentcode=0",
        fields : [ "areacode", "name" ],
        autoLoad : true
    }),
    // 市
    cityStore : new Ext.data.JsonStore({
        url : path + "/Common_selArea.action",
        fields : [ "areacode", "name" ],
        autoLoad : false
    })
};

Ext.extend(Q.company.editWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        var _win = this;
        return new Ext.form.FormPanel({
            items : [ {
                xtype : "fieldset",
                layout : "column",
                border : false,
                defaults : {
                    layout : "form",
                    border : false,
                    columnWidth : 1
                },
                items : [ {
                    items : [ {
                        name : "model.org.orgId",
                        xtype : 'hidden'
                    }, {
                        name : "model.org.type",
                        xtype : 'hidden'
                    }, {
                        name : "model.org.parentId",
                        xtype : 'hidden'
                    }, {
                        name : "model.companyId",
                        xtype : 'hidden'
                    }, {
                        name : "model.stateName",
                        xtype : 'hidden'
                    }, {
                        name : "model.provinceName",
                        xtype : 'hidden'
                    }, {
                        name : "model.cityName",
                        xtype : 'hidden'
                    }, {
                        fieldLabel : "公司编码",
                        name : "model.companyCode",
                        xtype : "textfield",
                        emptyText : "系统自动生成",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : "公司名称",
                        name : "model.companyName",
                        xtype : "textfield",
                        allowBlank : false,
                        maxLength : 50,
                        width : 180
                    }, {
                        fieldLabel : "序号",
                        name : "model.org.orderNo",
                        xtype : "numberfield",
                        allowBlank : false,
                        maxLength : 2,
                        width : 180
                    }, {
                        fieldLabel : "责任人",
                        name : "model.org.responsor",
                        xtype : "textfield",
                        maxLength : 30,
                        width : 180
                    }, {
                        fieldLabel : "是否有效",
                        hiddenName : "model.isValid",
                        xtype : "uxcombo",
                        value : 1,
                        width : 180,
                        store : Q.common.yesOrNotStore
                    }, {
                        fieldLabel : "国家",
                        hiddenName : "model.state",
                        xtype : "uxcombo",
                        value : 0,
                        width : 180,
                        store : new Ext.data.ArrayStore({
                            fields : [ 'value', 'text' ],
                            data : [ [ '0', '中国' ], [ '-1', '其它' ] ]
                        }),
                        listeners : {
                            "select" : function(c) {
                                var _form = _win.formPanel.getForm();
                                if (c.getValue() == '-1') {
                                    _form.findField("model.province").setValue("");
                                    _form.findField("model.province").readOnly = true;
                                    _form.findField("model.city").setValue("");
                                    _form.findField("model.city").readOnly = true;
                                } else {
                                    _form.findField("model.province").readOnly = false;
                                    _form.findField("model.city").readOnly = false;
                                }
                            }
                        }
                    }, {
                        fieldLabel : "省",
                        hiddenName : "model.province",
                        xtype : "uxcombo",
                        valueField : "areacode",
                        displayField : "name",
                        store : Q.company.selStores.provinceStore,
                        listeners : {
                            "select" : function(c) {
                                var _form = _win.formPanel.getForm();
                                _form.findField("model.city").reset();
                                Q.company.selStores.cityStore.removeAll();
                                Q.company.selStores.cityStore.baseParams = {
                                    "filter_EQ_parentcode" : c.getValue()
                                };
                                Q.company.selStores.cityStore.load();
                            }
                        },
                        width : 180
                    }, {
                        fieldLabel : "市",
                        hiddenName : "model.city",
                        xtype : "uxcombo",
                        valueField : "areacode",
                        displayField : "name",
                        store : Q.company.selStores.cityStore,
                        width : 180
                    }, {
                        fieldLabel : "地址",
                        name : "model.addr",
                        xtype : "textfield",
                        maxLength : 100,
                        width : 180
                    }, {
                        fieldLabel : "邮政编码",
                        name : "model.postCode",
                        xtype : "numberfield",
                        maxLength : 6,
                        width : 180
                    }, {
                        fieldLabel : "货币",
                        name : "model.coin",
                        xtype : "textfield",
                        value : "CNY",
                        maxLength : 50,
                        width : 180
                    } ]
                } ]
            } ],
            buttons : [ {
                text : $("button.save"),
                handler : function() {
                    _win.doSubmit();
                }
            }, {
                text : $("button.return"),
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },

    doSubmit : function() {
        var isValid = this.formPanel.form.isValid();
        if (!isValid) {
            return;
        }
        var _win = this;
        Q.confirm($("message.save.confirm"), {
            ok : function() {
                // 国家/省/市
                var _form = _win.formPanel.getForm();
                _form.findField("model.stateName").setValue(_form.findField("model.state").getRawValue());
                _form.findField("model.provinceName").setValue(_form.findField("model.province").getRawValue());
                _form.findField("model.cityName").setValue(_form.findField("model.city").getRawValue());

                _win.formPanel.getForm().submit({
                    waitTitle : $("message.submit.data"),
                    waitMsg : $("message.submit.wait"),
                    url : company_dealUrl + (_win.config.operflag == "add" ? "_save.action" : "_update.action"),
                    method : 'POST',
                    success : function() {
                        _win.hide();
                        _win.fireEvent("submit");
                        Q.tips(Q.color($("message.save.success"), "red"));
                        tree.updateTree();
                    },
                    failure : function() {
                        Q.error($("message.submit.failure"));
                    }
                });
            }
        });
    },

    setFormValue : function(config) {
        var _win = this;
        var _form = _win.formPanel.getForm();
        // 新增
        if (config.operflag == "add") {
            _form.findField("model.org.parentId").setValue(config.parentId);
            _form.findField("model.org.type").setValue(config.type);
        } else {
            _form.load({
                url : company_dealUrl + "_edit.action?id=" + config.companyId,
                waitTitle : $("message.load.data"),
                waitMsg : $("message.load.wait"),
                success : function(_form, action) {
                    var data = action.result.data;

                    _form.findField("model.org.orgId").setValue(data.org.orgId);
                    _form.findField("model.org.type").setValue(data.org.type);
                    _form.findField("model.org.parentId").setValue(data.org.parentId);
                    _form.findField("model.org.orderNo").setValue(data.org.orderNo);
                    _form.findField("model.org.responsor").setValue(data.org.responsor);

                    _form.findField("model.isValid").setValue(data.isValid);

                    _form.findField("model.companyId").setValue(data.companyId);
                    _form.findField("model.companyCode").setValue(data.companyCode);
                    _form.findField("model.companyName").setValue(data.companyName);
                    _form.findField("model.addr").setValue(data.addr);
                    _form.findField("model.postCode").setValue(data.postCode);
                    _form.findField("model.coin").setValue(data.coin);
                    _form.findField("model.state").setValue(data.state);
                    _form.findField("model.province").setValue(data.province);

                    if (!!data.province) {
                        Q.company.selStores.cityStore.baseParams = {
                            "filter_EQ_parentcode" : data.province
                        };
                        Q.company.selStores.cityStore.load({
                            callback : function() {
                                _form.findField("model.city").setValue(data.city);
                            }
                        });
                    }
                    Ext.getBody().unmask();
                },
                failure : function() {
                    Q.error($("message.load.failure"));
                    Ext.getBody().unmask();
                    _win.hide();
                }
            });
        }
    }
});