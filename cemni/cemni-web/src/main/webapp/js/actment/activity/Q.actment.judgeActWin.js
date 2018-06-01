Ext.ns('Q.actment');
Q.actment.judgeActWin = function(activityId) {
    this.activityId = activityId;
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.actment.judgeActWin.superclass.constructor.call(this, {
        title : "活动评价",
        layout : "fit",
        items : [ _formPanel ],
        width : 600,
        height : 380
    });
    this.setFormValue(activityId);
};

Ext.extend(Q.actment.judgeActWin, Ext.ux.Window, {
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
                    columnWidth : 0.5
                },
                items : [ {
                    items : {
                        name : 'model.activity.activityId',
                        xtype : "hidden",
                    }
                }, {
                    items : {
                        name : 'model.judgeActId',
                        xtype : "hidden",
                    }
                }, {
                    items : {
                        fieldLabel : '活动单号',
                        name : 'model.activityNo',
                        xtype : "textfield",
                        readOnly : true,
                        anchor : '88%'
                    }
                }, {
                    items : {
                        fieldLabel : '活动主题<font color="red">*</font>',
                        name : 'model.activityTheme',
                        xtype : "textfield",
                        readOnly : true,
                        anchor : '88%'
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '活动效果<font color="red">*</font>',
                        name : 'model.actEffect',
                        xtype : "textarea",
                        allowBlank : false,
                        height : 50,
                        anchor : '94%'
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '活动总体评价<font color="red">*</font>',
                        name : 'model.totalJudge',
                        xtype : "textarea",
                        allowBlank : false,
                        height : 50,
                        anchor : '94%'
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '不足及改进<font color="red">*</font>',
                        name : 'model.improve',
                        xtype : "textarea",
                        allowBlank : false,
                        height : 50,
                        anchor : '94%'
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '优点及推广应用<font color="red">*</font>',
                        name : 'model.advantage',
                        xtype : "textarea",
                        allowBlank : false,
                        height : 50,
                        anchor : '94%'
                    }
                }, {
                    items : {
                        fieldLabel : '潜在加盟商数量<font color="red">*</font>',
                        name : 'model.potentialFra',
                        xtype : "numberfield",
                        allowBlank : false,
                        anchor : '88%'
                    }
                }, {
                    items : {
                        fieldLabel : '签约加盟商数量<font color="red">*</font>',
                        name : 'model.signingFra',
                        xtype : "numberfield",
                        allowBlank : false,
                        anchor : '88%'
                    }
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
        var _activityId = this.activityId;
        Q.confirm($("message.save.confirm"), {
            ok : function() {
                _win.formPanel.getForm().submit({
                    waitTitle : $("message.submit.data"),
                    waitMsg : $("message.submit.wait"),
                    url : path + "/JudgeAct_update.action?id=" + _activityId,
                    method : 'POST',
                    success : function(form, action) {
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
    },
    setFormValue : function(activityId) {
        var _win = this;
        var _form = _win.formPanel.getForm();
        _form.load({
            url : path + "/Activity_edit.action?id=" + activityId,
            waitTitle : $("message.load.data"),
            waitMsg : $("message.load.wait"),
            success : function(_form, action) {
                var data = action.result.data;
                _form.findField("model.activity.activityId").setValue(activityId);
                _form.findField("model.activityNo").setValue(data.activityNo);
                _form.findField("model.activityTheme").setValue(data.activityTheme);

                if (data.judgeAct) {
                    _form.findField("model.judgeActId").setValue(!data.judgeAct ? "" : data.judgeAct.judgeActId);
                    _form.findField("model.actEffect").setValue(data.judgeAct.actEffect);
                    _form.findField("model.totalJudge").setValue(data.judgeAct.totalJudge);
                    _form.findField("model.improve").setValue(data.judgeAct.improve);
                    _form.findField("model.advantage").setValue(data.judgeAct.advantage);
                    _form.findField("model.potentialFra").setValue(data.judgeAct.potentialFra);
                    _form.findField("model.signingFra").setValue(data.judgeAct.signingFra);
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
});