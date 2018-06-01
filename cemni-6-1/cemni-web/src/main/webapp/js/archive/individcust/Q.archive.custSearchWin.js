Ext.ns('Q.archive');
Q.archive.custSearchWin = function(config) {
    var formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    var _config = Ext.apply({
        title : "个人档案精确查询",
        width : 330,
        height : 220,
        layout : "border",
        items : formPanel,
    }, config || {});
    Q.archive.custSearchWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.archive.custSearchWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        var win = this;
        return new Ext.form.FormPanel({
            region : "center",
            layout : "column",
            border : true,
            bodyStyle : "padding:10px",
            defaults : {
                columnWidth : 1,
                layout : "form",
                border : false,
                labelWidth : 70,
            },
          
            items : [ {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                items : [ {
                    fieldLabel : '查询ID',
                    xtype : "hidden",
                    value : "1",
                    name : "filter_cid"
                },{
                    fieldLabel : "会员卡号",
                    name : "filter_EQ_cardNo"
                }, {
                    fieldLabel : "客户名称",
                    name : "filter_EQ_name"
                }, {
                    fieldLabel : "手机号码",
                    name : "filter_EQ_mobile"
                }, {
                    fieldLabel : "商品条码",
                    name : "filter_EQ_posOrderList_goodsBar"
                } ]
            } ],
           
            buttons : [ {
                text : "查询",
                handler : function() {
                    if (!win.formPanel.getForm().findField('filter_EQ_cardNo').getValue() && !win.formPanel.getForm().findField('filter_EQ_name').getValue() && !win.formPanel.getForm().findField('filter_EQ_mobile').getValue() && !win.formPanel.getForm().findField('filter_EQ_posOrderList_goodsBar').getValue()) {
                        return;
                    }
                    win.doSelect();
                }
            }, {
                text : "返回",
                handler : function() {
                    win.hide();
                }
            } ]
        });
    },
    doSelect : function() {
        var _form = this.formPanel.form;
        if (!_form.isValid()) {
            return;
        }
        this.fireEvent("search", _form.getValues());
        this.hide();
    }
});