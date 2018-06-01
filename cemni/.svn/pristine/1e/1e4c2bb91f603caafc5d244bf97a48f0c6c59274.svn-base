/**
 * 批量修改客户归属门店
 * 
 * @author：yuhb
 * @date：2017年3月15日 上午1:45:31
 */
Ext.ns('Q.batch');
Q.batch.belongStoreWin = function(config) {
    this.config = config;
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.batch.belongStoreWin.superclass.constructor.call(this, {
        title : "批量修改客户归属门店",
        layout : "fit",
        items : [ _formPanel ],
        width : 400,
        height : 180
    });
};

Ext.extend(Q.batch.belongStoreWin, Ext.ux.Window, {
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
                    labelWidth : 130
                },
                items : [ {
                    items : [ {
                        name : "belongStoreNo",
                        xtype : 'hidden'
                    }, {
                        width : 200,
                        fieldLabel : "修改后的归属门店<font color='red'>*</font>",
                        name : "belongStoreName",
                        xtype : "textfield",
                        allowBlank : false,
                        listeners : {
                            render : function(p) {
                                p.getEl().on('click', function(p) {
                                    var _storeWin = new Q.afterservice.chooseOrgStoreWin();
                                    _storeWin.on("submit", function(r) {
                                        var _form = _win.formPanel.getForm();
                                        _form.findField("belongStoreNo").setValue(r.data.orgStoreCode);
                                        _form.findField("belongStoreName").setValue(r.data.orgStoreName);
                                    });
                                    _storeWin.show();
                                });
                            }
                        }
                    }, {
                        fieldLabel : '起始页码',
                        name : 'startPage',
                        xtype : "numberfield",
                        width : 200
                    }, {
                        fieldLabel : '结束页码',
                        name : 'endPage',
                        xtype : "numberfield",
                        width : 200
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
        Q.confirm("勾选列表中的“全选”复选框时，会根据“查询条件”批量修改用户的“归属门店”，<br/>请谨慎操作！是否继续？", {
            ok : function() {
                Ext.getBody().submitMask();

                var _form = _win.formPanel.getForm();
                _win.config.belongStoreNo = _form.findField("belongStoreNo").getValue();
                _win.config.belongStoreName = _form.findField("belongStoreName").getValue();
                _win.config.startPage = _form.findField("startPage").getValue();
                _win.config.endPage = _form.findField("endPage").getValue();
                Ext.Ajax.request({
                    url : dealUrl + "_updateBelongStore.action",
                    params : _win.config,
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error($("message.submit.failure"));
                            return;
                        }
                        _win.hide();
                        _win.fireEvent("submit");
                        Q.tips(Q.color($("message.save.success"), "red"));
                    },
                    failure : function(response) {
                        Q.error($("message.submit.failure"));
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
});