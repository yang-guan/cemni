/**
 * 会员NC卡号
 * 
 * @author：WangYuanJun
 * @date：2017年4月17日 上午10:25:31
 */
Ext.ns('Q.archive');
Q.archive.ncNoHisWin = function(config) {
    this.config = config;
    var _formPanel = this.formPanel = this.createFormPanel(config);
    this.addEvents("submit");

    Q.archive.ncNoHisWin.superclass.constructor.call(this, {
        title : "NC卡号信息维护",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 180
    });
};

Ext.extend(Q.archive.ncNoHisWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function(cfg) {
        var _win = this;
        return new Ext.form.FormPanel({
            items : [ {
                xtype : "fieldset",
                layout : "column",
                border : false,
                defaults : {
                    layout : "form",
                    border : false,
                    labelWidth : 100
                },
                items : [ {
                    items : [ {
                        name : "model.individCustId",
                        xtype : 'hidden',
                        value : cfg.individCustId
                    } ]
                }, {
                    items : [ {
                        fieldLabel : 'NC历史卡号',
                        xtype : "textfield",
                        value : cfg.ncNoHis,
                        readOnly : true,
                        width : 330
                    } ]
                }, {
                    items : [ {
                        fieldLabel : "有效的NC卡号<font color='red'>*</font>",
                        xtype : "textfield",
                        name : "model.ncNo",
                        value : cfg.ncNo,
                        allowBlank : false,
                        width : 330
                    } ]
                }, {
                    items : [ {
                        fieldLabel : "已失效的NC卡号",
                        xtype : "textfield",
                        name : "model.ncNoInvalid",
                        value : cfg.ncNoInvalid,
                        width : 330
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
        _win.formPanel.getForm().submit({
            waitTitle : $("message.submit.data"),
            waitMsg : $("message.submit.wait"),
            url : dealUrl + "_editNcNoHis.action",
            method : 'POST',
            success : function() {
                _win.hide();
                _win.fireEvent("submit");
                Q.tips(Q.color($("message.save.success"), "red"));
            },
            failure : function() {
                Q.error($("message.submit.failure"));
            }
        });
    }
});