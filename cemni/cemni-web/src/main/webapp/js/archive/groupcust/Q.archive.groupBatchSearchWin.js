Q.archive.groupBatchSearchWin = function() {
    var formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    Q.archive.groupBatchSearchWin.superclass.constructor.call(this, {
        title : "团体档案批量查询",
        width : 600,
        height : 310,
        layout : "border",
        items : formPanel
    });
};

Ext.extend(Q.archive.groupBatchSearchWin, Ext.ux.Window, {
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
                layout : "form",
                border : false,
                columnWidth : 0.5,
                labelWidth : 90
            },
            items : [ {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                items : [ {
                    fieldLabel : "会员卡号",
                    name : "filter_LIKE_cardNo"
                }, {
                    fieldLabel : "增值税号登记",
                    name : "filter_LIKE_vatNo"
                }, {
                    fieldLabel : "活跃状态",
                    hiddenName : "filter_EQ_active",
                    xtype : "uxcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.activeStore
                }, {
                    fieldLabel : "积分情况",
                    name : "filter_GE_credit"
                }, {
                    fieldLabel : "珠宝折算额",
                    name : "filter_GE_jewerlyAmount"
                }, {
                    xtype : "datefield",
                    fieldLabel : "末次消费日期",
                    name : "filter_GE_lastBuyTime",
                    editable : false,
                    format : "Y-m-d"
                }, {
                    xtype : "datefield",
                    fieldLabel : "创建日期",
                    name : "filter_GE_createTime",
                    editable : false,
                    format : "Y-m-d"
                }, {
                    fieldLabel : "商品条码",
                    name : "filter_LIKE_posOrderlist_goodsBar",
                } ]
            }, {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                items : [ {
                    fieldLabel : "单位名称",
                    name : "filter_LIKE_groupName"
                }, {
                    fieldLabel : "营业执照编号",
                    name : "filter_LIKE_businessLicense"
                }, {
                    fieldLabel : "使用状态",
                    hiddenName : "filter_EQ_enable",
                    xtype : "uxcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.enableStore
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_credit"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_jewerlyAmount"
                }, {
                    xtype : "datefield",
                    fieldLabel : "至",
                    name : "filter_LE_lastBuyTime",
                    editable : false,
                    format : "Y-m-d"
                }, {
                    xtype : "datefield",
                    fieldLabel : "至",
                    name : "filter_LE_createTime",
                    editable : false,
                    format : "Y-m-d"
                }, ]
            } ],
            buttons : [ {
                text : "查询",
                handler : function() {
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