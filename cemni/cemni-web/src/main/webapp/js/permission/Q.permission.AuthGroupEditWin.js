Ext.ns("Q.permission");
Q.permission.AuthGroupEditWin = function(config) {
    var win = this;
    config = config || {};

    Ext.applyIf(config, {
        dealUrl : path + "/permission/AuthGroup",
        moduleCode : "AuthGroupEdit"
    });
    var formPanel = this.formPanel = this.createFormPanel();
    config = Ext.apply({
        width : 380,
        height : 350,
        layout : "border",
        items : [ formPanel ]
    }, config);

    this.addEvents("submit");
    Q.permission.AuthGroupEditWin.superclass.constructor.call(this, config);
};

Ext.extend(Q.permission.AuthGroupEditWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.resetWin();
            this.formPanel.getForm().reset();
            this.url = "";
            this.formPanel.buttons[1].setText($("button.cancel"));
            this.formPanel.buttons[0].show();
        }
    },
    resetWin : function() {
        if (!this.hidden) {
            this.hide();// 如果窗口状态为不隐藏时，隐藏它
            this.formPanel.getForm().reset();
            this.url = "";
        }
    },
    isValidWin : function() {
        var isValid = this.formPanel.form.isValid();
        if (!isValid) {
            return false;
        }
        return true;
    },
    submitWin : function() {
        var win = this;
        if (!win.isValidWin()) {
            return;
        }
        var url = (this.url || this.dealUrl + "_save.action?");
        Q.confirm($("message.save.confirm"), {
            ok : function() {
                win.formPanel.getForm().submit({
                    waitTitle : $("message.submit.data"),
                    waitMsg : $('message.submit.wait'),
                    url : url,
                    method : 'POST',
                    success : function(form, action) {
                        var result = action.result, msg = result.info;
                        win.hide();
                        win.fireEvent("submit");
                        Q.tips("<font color='blue'>" + $("message.save.success") + "</font>");
                        var grid = Ext.getCmp("authGroupGrid");
                        var store = grid.getStore();
                        store.reload();
                    },
                    failure : function(form, action) {
                        if (action && action.result) {
                            Q.error(action.result.info || $("message.submit.failure"));
                        } else {
                            Q.error($("message.submit.failure"));
                        }
                    }
                });
            }
        });
    },
    createFormPanel : function() {
        var win = this;

        var formPanel = new Ext.form.FormPanel({
            region : "center",
            labelWidth : 160,
            layout : "column",
            id : "authGroupPanel",
            border : true,
            bodyStyle : "padding:10px",
            defaults : {
                columnWidth : 1,
                layout : "form",
                border : false
            },
            items : [ {
                defaults : {
                    xtype : "textfield",
                    anchor : "95%"
                },
                columnWidth : 1,
                items : [ {
                    id : "model.agrCode",
                    fieldLabel : $("authGroup.agrCode") + "<font color='red'>*</font>",// 资源组编码
                    allowBlank : false,
                    maxLength : 20,
                    name : "model.agrCode"
                }, {
                    id : "model.agrDesc",
                    name : "model.agrDesc",
                    maxLength : 50,
                    fieldLabel : $("authGroup.agrDesc")
                }, {
                    hidden : true,
                    id : "model.clientCode",
                    fieldLabel : $("authGroup.agrCode"),// 客户端编码
                    name : "model.clientCode"
                } ]
            } ],
            buttons : [ {
                text : $("button.save"),
                handler : function() {
                    win.submitWin();
                }
            }, {
                text : $("button.return"),
                handler : function() {
                    win.hide();
                }
            } ]
        });
        return formPanel;

    },
    setFormValue : function(clientCode, agrCode, agrDesc) {
        var win = this;
        var form = win.formPanel.getForm();
        this.url = this.dealUrl + "_update.action?";
        var field1 = form.findField("model.agrCode");
        field1.setValue(agrCode);
        var field2 = form.findField("model.agrDesc");
        field2.setValue(agrDesc);
        var field3 = form.findField("model.clientCode");
        field3.setValue(clientCode);
    }
});