Q.group.gradeadj.editWin = function() {
    Ext.getBody().loadMask();
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.group.gradeadj.editWin.superclass.constructor.call(this, {
        title : "团体会员等级调整",
        layout : "fit",
        items : [ _formPanel ],
        width : 360,
        height : 300
    });
};

Ext.extend(Q.group.gradeadj.editWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        },
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
                    columnWidth : 1,
                    labelWidth : 100
                },
                items : [ {
                    items : [ {
                        name : 'model.groupCust.groupCustId',
                        xtype : 'hidden'
                    }, {
                        name : 'model.modType',
                        xtype : 'hidden',
                        value : 2
                    }, {
                        name : 'model.creditOrigin',
                        xtype : 'hidden',
                        value : 1
                    }, {
                        name : 'model.custType',
                        xtype : 'hidden',
                        value : 2
                    }, {
                        fieldLabel : '会员卡号',
                        name : 'cardNo',
                        xtype : "textfield",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : '单位名称',
                        name : 'groupName',
                        xtype : "textfield",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : '珠宝折算额',
                        name : 'jewerlyAmount',
                        xtype : "textfield",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : '当前会员等级',
                        name : "curLv",
                        xtype : "textfield",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : '调整后会员等级<font color="red">*</font>',
                        hiddenName : 'model.lvAfter',
                        xtype : "uxcombo",
                        readOnly : false,
                        valueField : "value",
                        displayField : "name",
                        allowBlank : false,
                        store : Q.group.gradeadj.selStores.lvStore2,
                        width : 182
                    }, {
                        fieldLabel : '等级调整原因<font color="red">*</font>',
                        name : 'model.modReason',
                        xtype : "textarea",
                        allowBlank : false,
                        width : 180
                    } ]
                } ],
                buttons : [ {
                    text : $("button.save"),
                    handler : function() {
                        _win.submitWin();
                    }
                }, {
                    text : $("button.return"),
                    handler : function() {
                        _win.hide();
                    }
                } ]
            } ]
        });
    },

    submitWin : function() {
        var isValid = this.formPanel.form.isValid();
        if (!isValid) {
            return;
        }
        var _win = this;
        Q.confirm($("message.save.confirm"), {
            ok : function() {
                _win.formPanel.getForm().submit({
                    waitTitle : $("message.submit.data"),
                    waitMsg : $("message.submit.wait"),
                    url : dealUrl + "_update.action",
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

    setFormValue : function(groupCustId) {
        var _win = this;
        var _form = _win.formPanel.getForm();
        _form.load({
            url : path + "/GroupCust_edit.action?id=" + groupCustId,
            waitTitle : $("message.load.data"),
            waitMsg : $("message.load.wait"),
            success : function(_form, action) {
                var data = action.result.data;
                _form.findField("model.groupCust.groupCustId").setValue(data.groupCustId);
                _form.findField("cardNo").setValue(data.cardNo);
                _form.findField("groupName").setValue(data.groupName);
                _form.findField("jewerlyAmount").setValue(data.jewerlyAmount);

                // 会员等级只能往高出调整
                var curLv = data.lv;
                Q.group.gradeadj.selStores.lvStore2.load({
                    callback : function() {
                        var that = this;
                        this.each(function(data) {
                            if (curLv) {
                                if (data.json.value == curLv) {
                                    _form.findField("curLv").setValue(data.json.name);
                                    that.remove(this);
                                } else if (data.json.value > curLv) {
                                    that.remove(this);
                                }
                            }
                        });
                        Ext.getBody().unmask();
                    }
                });
            },
            failure : function() {
                Q.error($("message.load.failure"));
                Ext.getBody().unmask();
                _win.hide();
            }
        });
    }
});