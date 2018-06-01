/**
 * 发送条件-添加
 * 
 * @author：yuhb
 * @date：2016年12月26日 下午11:41:34
 */
Q.sms.objcondWin = function() {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.sms.objcondWin.superclass.constructor.call(this, {
        title : "发送条件-添加",
        layout : "fit",
        items : [ _formPanel ],
        width : 330,
        height : 200
    });
};

Ext.extend(Q.sms.objcondWin, Ext.ux.Window, {
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
                        fieldLabel : "比较字段",
                        hiddenName : "typeCondId",
                        xtype : "uxcombo",
                        valueField : "typeCondId",
                        displayField : "compColumnName",
                        allowBlank : false,
                        store : Q.sms.selStores.selTypeCond,
                        listeners : {
                            select : function(c, r, i) {
                                _win.formPanel.getForm().findField("compVal").reset();
                                Q.sms.selStores.selCompVal.removeAll();
                                Q.sms.selStores.selCompVal.baseParams = {
                                    "filter_EQ_dictCode" : Q.sms.selStores.selTypeCond.getAt(i).get('compColumVal')
                                };
                                Q.sms.selStores.selCompVal.load();
                            }
                        },
                        width : 150
                    }, {
                        fieldLabel : "条件",
                        hiddenName : "compSymbol",
                        xtype : "uxcombo",
                        value : "=",
                        store : Q.sms.selStores.compSymbolStore,
                        width : 150
                    }, {
                        fieldLabel : "比较值",
                        hiddenName : "compVal",
                        xtype : "uxcombo",
                        valueField : "value",
                        displayField : "name",
                        store : Q.sms.selStores.selCompVal,
                        allowBlank : false,
                        width : 150
                    } ]
                } ]
            } ],
            buttons : [ {
                text : "确定",
                handler : function() {
                    _win.doSelect();
                }
            }, {
                text : $("button.return"),
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },
    doSelect : function() {
        var isValid = this.formPanel.form.isValid();
        if (!isValid) {
            return;
        }
        var _form = this.formPanel.getForm();
        var r = new Ext.data.Record({
            'typeCond.typeCondId' : _form.findField("typeCondId").getValue(),
            'typeCond.compColumnName' : _form.findField("typeCondId").getRawValue(),
            compSymbol : _form.findField("compSymbol").getValue(),
            compVal : _form.findField("compVal").getValue(),
            compValName : _form.findField("compVal").getRawValue()
        });
        this.fireEvent("submit", r);
        this.hide();
    }
});