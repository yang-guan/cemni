/**
 * 部门、大区、区域
 * 
 * @author：yuhb
 * @date：2016年12月7日 下午1:14:14
 */
Ext.ns('Q.org');
Q.org.editWin = function(config) {
    var _typeName = this.typeName = function() {
        switch (config.type) {
        case nodetype.dept:
            return "部门";
            break;
        case nodetype.bigArea:
            return "大区";
            break;
        default:
            return "区域";
            break;
        }
    }();

    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : _typeName + (config.operflag == "add" ? "-新增" : "-编辑"),
        layout : "fit",
        items : [ _formPanel ],
        width : 350,
        height : 230
    }, config);
    Q.org.editWin.superclass.constructor.call(this, _config);
    this.setFormValue(config);
};

Ext.extend(Q.org.editWin, Ext.ux.Window, {
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
                    columnWidth : 1,
                    labelWidth : 80
                },
                items : [ {
                    items : [ {
                        name : "model.orgId",
                        xtype : 'hidden'
                    }, {
                        name : "model.type",
                        xtype : 'hidden'
                    }, {
                        name : "model.parentId",
                        xtype : 'hidden'
                    }, {
                        fieldLabel : _win.typeName + "编码<font color='red'>*</font>",
                        name : "model.orgCode",
                        xtype : "textfield",
                        allowBlank : false,
                        anchor : "95.6%",
                    }, {
                        fieldLabel : _win.typeName + "名称<font color='red'>*</font>",
                        name : "model.name",
                        xtype : "textfield",
                        allowBlank : false,
                        maxLength : 50,
                        anchor : "95.6%",
                    }, {
                        fieldLabel : "序号",
                        name : "model.orderNo",
                        xtype : "numberfield",
                        maxLength : 2,
                        anchor : "95.6%",
                    }, {
                        fieldLabel : "责任人",
                        name : "model.responsor",
                        xtype : "textfield",
                        maxLength : 30,
                        anchor : "95.6%",
                    }, {
                        fieldLabel : "是否有效",
                        hiddenName : "model.isValid",
                        xtype : "uxcombo",
                        value : 1,
                        anchor : "96.3%",
                        store : Q.common.yesOrNotStore
                    } ]
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
                    url : org_dealUrl + (_win.config.operflag == "add" ? "_save.action" : "_update.action"),
                    method : 'POST',
                    success : function(form, action) {
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
            _form.findField("model.parentId").setValue(config.parentId);
            _form.findField("model.type").setValue(config.type);
        } else {
            _form.load({
                url : org_dealUrl + "_edit.action?id=" + config.orgId,
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
                },
                failure : function() {
                    Q.error($("message.load.failure"));
                    _win.hide();
                }
            });
        }
    }
});