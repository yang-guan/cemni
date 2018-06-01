Ext.ns("Q.permission");

Q.permission.AuthFieldEditWin = function(config) {
    var win = this;
    config = config || {};
    Ext.applyIf(config, {
        dealUrl : path + "/permission/AuthField",
        moduleCode : "AuthFieldEdit"
    });
    var formPanel = this.formPanel = this.createFormPanel();
    config = Ext.apply({
        width : 380,
        height : 50,
        layout : "border",
        items : [ formPanel ]
    }, config);

    /**
     * @event submit 提交表单成功提示
     * @param {Ext.form.baseForm}
     *            form
     */
    this.addEvents("submit");

    Q.permission.AuthFieldEditWin.superclass.constructor.call(this, config);
};
Ext.extend(Q.permission.AuthFieldEditWin, Ext.ux.Window, {
    // window 的事件监听
    listeners : {
        hide : function() {
            this.resetWin();
            this.formPanel.getForm().reset();
            this.url = "";
            var items = this.formPanel.getForm().items.items;
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
                    // params: Ext.encode(params) == '{}'? params:Q.parseParams(params),//如查params为空对像时不用Q.parseParams转换
                    success : function(form, action) {
                        var result = action.result, msg = result.info;
                        win.hide();
                        win.fireEvent("submit");
                        Q.tips("<font color='blue'>" + $("message.save.success") + "</font>");
                        var grid = Ext.getCmp("authFieldGrid");
                        var store = grid.getStore();
                        store.load();
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
            labelWidth : 150,
            layout : "column",
            id : "authFieldPanel",
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
                    anchor : "100%"
                },
                columnWidth : 1,
                items : [ {
                    id : "model.authCode",
                    fieldLabel : $("authField.authCode") + "<font color='red'>*</font>",// 权限字段
                    allowBlank : false,
                    maxLength : 30,
                    name : "model.authCode"
                }, {
                    id : "model.fieldCode",
                    name : "model.fieldCode",
                    allowBlank : false,
                    maxLength : 50,
                    fieldLabel : $("authField.fieldCode") + "<font color='red'>*</font>"// 字段名
                }, {
                    id : "model.fieldName",
                    name : "model.fieldName",
                    allowBlank : false,
                    maxLength : 50,
                    fieldLabel : $("authField.fieldName") + "<font color='red'>*</font>"// 字段描述
                }, {
                    id : "model.fieldType",
                    name : "model.fieldType",
                    allowBlank : false,
                    maxLength : 20,
                    fieldLabel : $("authField.fieldType") + "<font color='red'>*</font>"// 字段类型
                }, {
                    xtype : "numberfield",
                    id : "model.fieldLength",
                    name : "model.fieldLength",
                    allowBlank : false,
                    allowDecimals : false,
                    allowNegative : false,
                    minValue : 1,
                    maxValue : 4000,
                    fieldLabel : $("authField.fieldLength") + "<font color='red'>*</font>"// 字段长度
                }, {
                    id : "model.tableName",
                    name : "model.tableName",
                    allowBlank : false,
                    maxLength : 30,
                    fieldLabel : $("authField.tableName") + "<font color='red'>*</font>"// 表名
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
    setFormValue : function(authCode) {
        var win = this;
        var form = win.formPanel.getForm();
        this.url = this.dealUrl + "_update.action?";
        Ext.Ajax.request({
            url : "AuthField_edit.action",
            params : {
                "id" : authCode
            },
            method : 'POST',
            success : function(result, request) {
                var json = Ext.decode(result.responseText);
                var form = Ext.getCmp("authFieldPanel").form;
                Q.each(json, function(v, k) {
                    var pro = "model." + k.replace(/\_/g, ".");
                    if (pro == "model.authCode") {
                        var field1 = form.findField("model.authCode");
                        field1.setValue(v.authCode);
                    }
                    var field = form.findField(pro);
                    if (!Ext.isEmpty(field)) {
                        // 这里不能全部转换成日期对象，datefield 字段下拉框可以通过format:"Y-m-d"转成字符串，可hidden 字段就不行，导致提交时提交对象了actin不能自动转。
                        if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(v) && field.getXType() != "hidden") {// 是否时间格式的字段
                            if (field.getXType() == "timefield") {
                                var sdate = v.substring(0, v.indexOf("."));// 这时的格式为,"Y-m-d
                                                                            // H:i:s.0"这种格式字符串转Date有问题,去除".0"
                                var dt = Date.parseDate(sdate, "Y-m-d H:i:s");
                                field.setValue(dt);// 设给字段时间格式
                            } else {
                                field.setValue(v);// 因为在列表页store中日期已被格式化,所以这里可直接设值
                            }
                        } else if (field.getXType() == "textarea") {
                            field.setValue(v.replace(/<br\/>/g, "\n")); // 如果是文本域时要把html格式换行转成js 格式以实现换行
                        } else {
                            field.setValue(v);
                        }
                    }
                });

            }
        });
    }
});