Ext.ns("Q.telvisit");
Q.telvisit.addWin = function() {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    Q.telvisit.addWin.superclass.constructor.call(this, {
        title : "会员信息（精确查询）",
        layout : "fit",
        items : [ _formPanel ],
        width : 300,
        height : 150
    });
};

Ext.extend(Q.telvisit.addWin, Ext.ux.Window, {
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
                        fieldLabel : "会员卡号",
                        name : "cardNo",
                        xtype : "textfield",
                        width : 150
                    }, {
                        fieldLabel : "手机号码",
                        name : "mobile",
                        xtype : "textfield",
                        width : 150
                    } ]
                } ]
            } ],

            buttons : [ {
                text : "查询",
                handler : function() {
                    _win.searchWin2();
                }
            }, {
                text : "返回",
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },
    searchWin2 : function() {
        var _form = this.formPanel.getForm();
        var cardNo = _form.findField("cardNo").getValue();
        var mobile = _form.findField("mobile").getValue();
        if (!cardNo && !mobile) {
            Q.error("“会员卡号、手机号码”不能同时为空！");
            return;
        }
        var json = {
            filter_EQ_cardNo : cardNo,
            filter_EQ_mobile : mobile
        };
        this.fireEvent("search", json);
        this.hide();
    }
});