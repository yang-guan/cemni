Ext.ns("Q.actment");
Q.actment.searchWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel(config);
    
    this.addEvents("search");

    var _config = this.config = Ext.apply({
        title : "会员信息-添加",
        layout : "fit",
        items : [ _formPanel ],
        width : 350,
        height : 150
    }, config || {});

    Q.actment.searchWin.superclass.constructor.call(this,_config);
};

var bntType;
Ext.extend(Q.actment.searchWin, Ext.ux.Window, {
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
                    labelWidth : 100
                },
                items : [ {
                    items : [ {
                        fieldLabel : "会员卡号",
                        xtype : "textfield",
                        anchor : "80%",
                        name : "cardNo"
                    }, {
                        fieldLabel : "手机号码",
                        name : "mobile",
                        xtype : "textfield",
                        anchor : "80%"
                    } ]
                } ]
            } ],

            buttons : [ {
                text : "查询",
                handler : function() {
                    _win.searchWin();
                }
            }, {
                text : "返回",
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },
    searchWin : function() {
        var _form = this.formPanel.getForm();
        if(""==_form.findField("cardNo").getValue()&&""==_form.findField("mobile").getValue()){
        	Q.tips(Q.color("请输入会员卡号或手机号", "red"));
        	return;
        }
        var json = {
            filter_EQ_cardNo : _form.findField("cardNo").getValue(),
            filter_EQ_mobile : _form.findField("mobile").getValue()
        };
        this.fireEvent("search", json);
        this.hide();
    }
});