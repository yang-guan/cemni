/**
 * 字典表-弹出框
 * 
 * @author：yuhb
 * @date：2016年11月23日 上午11:07:48
 */
Q.dict.editWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "字典表",
        layout : "fit",
        items : [ _formPanel ],
        width : 350,
        height : 300
    }, config || {});
    Q.dict.editWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.dict.editWin, Ext.ux.Window, {
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
                        fieldLabel : "节点Id",
                        name : "model.dictId",
                        xtype : "textfield",
                        emptyText : "系统自动生成",
                        readOnly : true,
                        width : 180
                    }, {
                        fieldLabel : "节点编码<font color='red'>*</font>",
                        name : "model.dictCode",
                        xtype : "numberfield",
                        allowBlank : false,
                        maxLength : 4,
                        width : 180
                    }, {
                        fieldLabel : "节点名称<font color='red'>*</font>",
                        name : "model.name",
                        xtype : "textfield",
                        allowBlank : false,
                        width : 180
                    }, {
                        fieldLabel : "值<font color='red'>*</font>",
                        name : "model.value",
                        xtype : "numberfield",
                        allowBlank : false,
                        maxLength : 6,
                        width : 180
                    }, {
                        fieldLabel : "父节点Id",
                        name : "model.parentId",
                        xtype : "numberfield",
                        emptyText : "默认为空",
                        width : 180
                    }, {
                        fieldLabel : "序号<font color='red'>*</font>",
                        name : "model.orderNo",
                        xtype : "numberfield",
                        allowBlank : false,
                        maxLength : 2,
                        width : 180
                    }, {
                        fieldLabel : "是否有效",
                        hiddenName : "model.isValid",
                        xtype : "uxcombo",
                        value : 1,
                        width : 180,
                        store : Q.common.yesOrNotStore
                    }, {
                        fieldLabel : "备注<font color='red'>*</font>",
                        name : "model.remark",
                        xtype : "textfield",
                        allowBlank : false,
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
                _win.formPanel.getForm().submit({
                    waitTitle : $("message.submit.data"),
                    waitMsg : $("message.submit.wait"),
                    url : dealUrl + _win.config.operUrl,
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

    setFormValue : function(dictId) {
        var _win = this;
        var _form = _win.formPanel.getForm();
        _form.load({
            url : dealUrl + "_edit.action?id=" + dictId,
            waitTitle : $("message.load.data"),
            waitMsg : $("message.load.wait"),
            success : function(_form, action) {
                var data = action.result.data;
                Q.each(data, function(v, p) {
                    var field = _win.formPanel.form.findField("model." + p);
                    if (!Ext.isEmpty(field)) {
                        field.setValue(v);
                    }
                });
                Ext.getBody().unmask();
            },
            failure : function() {
                Q.error($("message.submit.failure"));
                Ext.getBody().unmask();
                _win.hide();
            }
        });
    }
});