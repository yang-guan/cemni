Ext.ns("Q.telvisit");
Q.telvisit.searchWin = function() {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    Q.telvisit.searchWin.superclass.constructor.call(this, {
        title : "会员信息-查询",
        layout : "fit",
        items : [ _formPanel ],
        width : 700,
        height : 400
    });
};

var bntType;
Ext.extend(Q.telvisit.searchWin, Ext.ux.Window, {
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
                    columnWidth : 0.5,
                    labelWidth : 100
                },
                items : [ {
                    items : [ {
                        fieldLabel : "会员卡号",
                        xtype : "textfield",
                        anchor : "90%",
                        name : "cardNo"
                    }, {
                        fieldLabel : "新老会员",
                        hiddenName : "fresh",
                        anchor : "90%",
                        xtype : "lovcombo",
                        valueField : "value",
                        displayField : "name",
                        clearable : true,
                        store : Q.telvisit.selStores.freshStore
                    }, {
                        fieldLabel : "产品设计师款",
                        hiddenName : "designerStyle",
                        xtype : 'lovcombo',
                        valueField : "value",
                        displayField : "name",
                        clearable : true,
                        store : Q.telvisit.selStores.designerStore,
                        anchor : "90%"
                    }, {
                        fieldLabel : "产品分数",
                        hiddenName : "scoreSegment",
                        xtype : 'lovcombo',
                        valueField : "value",
                        displayField : "name",
                        clearable : true,
                        store : Q.telvisit.selStores.cpScoreStore,
                        anchor : "90%"
                    }, {
                        fieldLabel : "存货分类",
                        name : "stockTypeName",
                        emptyText : '请选择',
                        xtype : "textfield",
                        anchor : "90%",
                        listeners : {
                            render : function(p) {
                                p.getEl().on('mouseover', function(p1) {
                                    if (!p.getValue()) {
                                        Ext.QuickTips.unregister(p.el);
                                    } else {
                                        Ext.QuickTips.register({
                                            target : p.el,
                                            text : p.getValue()
                                        });
                                    }
                                });

                                var _form = _win.formPanel.getForm();

                                var isHidden = (bntType == "view");
                                Q.dict.multiSelectText(p, "存货分类", 9801, _form.findField('stockTypeName'), _form.findField('stockType'), isHidden);
                            }
                        }
                    }, {
                        fieldLabel : "生日",
                        name : "birthday",
                        xtype : "datefield",
                        editable : false,
                        format : 'Y-m-d',
                        anchor : "90%"
                    }, {
                        fieldLabel : "出生月日",
                        name : "birthMonthday",
                        xtype : "datefield",
                        editable : false,
                        format : 'm-d',
                        anchor : "90%"
                    }, {
                        fieldLabel : "末次消费日期",
                        name : "lastBuyTime",
                        xtype : "datefield",
                        editable : false,
                        format : 'Y-m-d',
                        anchor : "90%"
                    }, {
                        fieldLabel : "累计珠宝折算额",
                        name : "jewerlyAmount",
                        xtype : "textfield",
                        anchor : "90%"
                    }, {
                        fieldLabel : "当前可用积分",
                        name : "credit",
                        xtype : "textfield",
                        anchor : "90%"
                    }, {
                        name : "lastStoreNo",
                        xtype : "hidden"
                    }, {
                        fieldLabel : "末次消费门店",
                        name : "lastStoreName",
                        emptyText : '请选择',
                        xtype : "textfield",
                        anchor : "90%",
                        listeners : {
                            render : function(p) {
                                p.getEl().on('mouseover', function(p1) {
                                    if (!p.getValue()) {
                                        Ext.QuickTips.unregister(p.el);
                                    } else {
                                        Ext.QuickTips.register({
                                            target : p.el,
                                            text : p.getValue()
                                        });
                                    }
                                });
                                p.getEl().on('click', function(p) {
                                    var _storeWin = new Q.store.chooseStoreWin({
                                        singleSelect : false
                                    });
                                    _storeWin.on("submit", function(r) {
                                        var codeArr = [];
                                        var nameArr = [];
                                        Q.each(r, function() {
                                            codeArr.push(this.data.storeNo);
                                            nameArr.push(this.data.name);
                                        });
                                        _win.formPanel.getForm().findField('lastStoreNo').setValue(codeArr.join(","));
                                        _win.formPanel.getForm().findField('lastStoreName').setValue(nameArr.join(","));
                                    });
                                    _storeWin.show();
                                });
                            }
                        }
                    }, {
                        name : 'stockType',
                        xtype : 'hidden'
                    } ]
                }, {
                    items : [ {
                        fieldLabel : "客户名称",
                        xtype : "textfield",
                        anchor : "90%",
                        name : "name"
                    }, {
                        fieldLabel : "客户类型",
                        hiddenName : "type",
                        xtype : "lovcombo",
                        valueField : "value",
                        displayField : "name",
                        store : Q.telvisit.selStores.typeStore,
                        anchor : "90.5%",
                        clearable : true
                    }, {
                        fieldLabel : "会员等级",
                        hiddenName : "lv",
                        xtype : 'lovcombo',
                        valueField : "value",
                        displayField : "name",
                        clearable : true,
                        store : Q.telvisit.selStores.levelStore,
                        anchor : "90%"
                    }, {
                        fieldLabel : "活跃状态",
                        hiddenName : "active",
                        xtype : 'lovcombo',
                        valueField : "value",
                        displayField : "name",
                        clearable : true,
                        store : Q.telvisit.selStores.activeStore,
                        anchor : "90%"
                    }, {
                        fieldLabel : "手机号码",
                        name : "mobile",
                        xtype : "textfield",
                        anchor : "90%"
                    }, {
                        fieldLabel : "至",
                        name : "birthday2",
                        xtype : "datefield",
                        editable : false,
                        format : 'Y-m-d',
                        anchor : "90%"
                    }, {
                        fieldLabel : "至",
                        name : "birthMonthday2",
                        xtype : "datefield",
                        editable : false,
                        format : 'm-d',
                        anchor : "90%"
                    }, {
                        fieldLabel : "至",
                        name : "lastBuyTime2",
                        xtype : "datefield",
                        format : 'Y-m-d',
                        editable : false,
                        anchor : "90%"
                    }, {
                        fieldLabel : "至",
                        name : "jewerlyAmount2",
                        xtype : "textfield",
                        anchor : "90%"
                    }, {
                        fieldLabel : "至",
                        name : "credit2",
                        xtype : "textfield",
                        anchor : "90%"
                    }, {
                        name : "belongStoreNo",
                        xtype : "hidden"
                    }, {
                        fieldLabel : "归属门店",
                        xtype : "textfield",
                        name : "belongStoreName",
                        emptyText : '请选择',
                        anchor : "90%",
                        listeners : {
                            render : function(p) {
                                p.getEl().on('mouseover', function(p1) {
                                    if (!p.getValue()) {
                                        Ext.QuickTips.unregister(p.el);
                                    } else {
                                        Ext.QuickTips.register({
                                            target : p.el,
                                            text : p.getValue()
                                        });
                                    }
                                });
                                p.getEl().on('click', function(p) {
                                    var _storeWin = new Q.store.chooseStoreWin({
                                        singleSelect : false
                                    });
                                    _storeWin.on("submit", function(r) {
                                        var codeArr = [];
                                        var nameArr = [];
                                        Q.each(r, function() {
                                            codeArr.push(this.data.storeNo);
                                            nameArr.push(this.data.name);
                                        });
                                        _win.formPanel.getForm().findField('belongStoreNo').setValue(codeArr.join(","));
                                        _win.formPanel.getForm().findField('belongStoreName').setValue(nameArr.join(","));
                                    });
                                    _storeWin.show();
                                });
                            }
                        }
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
        var json = {
            filter_EQ_cardNo : _form.findField("cardNo").getValue(),
            filter_LIKE_name : _form.findField("name").getValue(),
            filter_IN_type : _form.findField("type").getValue(),
            filter_EQ_mobile : _form.findField("mobile").getValue(),
            filter_IN_lv : _form.findField("lv").getValue(),
            filter_IN_active : _form.findField("active").getValue(),
            filter_IN_posOrderList_scoreSegment : _form.findField("scoreSegment").getValue(),
            filter_IN_posOrderList_stockType : _form.findField("stockType").getValue(), // 存货分类
            filter_IN_posOrderList_designerStyle : _form.findField("designerStyle").getValue(),
            filter_IN_fresh : _form.findField("fresh").getValue(),// 新老会员
            filter_GE_credit : _form.findField("credit").getValue(),
            filter_LE_credit : _form.findField("credit2").getValue(),
            filter_GE_jewerlyAmount : _form.findField("jewerlyAmount").getValue(),
            filter_LE_jewerlyAmount : _form.findField("jewerlyAmount2").getValue(),
            filter_GE_birthday : Ext.util.Format.date(_form.findField("birthday").getValue(), 'Y-m-d'),
            filter_LE_birthday : Ext.util.Format.date(_form.findField("birthday2").getValue(), 'Y-m-d'),
            filter_GE_birthMonthday : Ext.util.Format.date(_form.findField("birthMonthday").getValue(), 'm-d'),
            filter_LE_birthMonthday : Ext.util.Format.date(_form.findField("birthMonthday2").getValue(), 'm-d'),
            filter_GE_lastBuyTime : Ext.util.Format.date(_form.findField("lastBuyTime").getValue(), 'Y-m-d'),
            filter_LE_lastBuyTime : Ext.util.Format.date(_form.findField("lastBuyTime2").getValue(), 'Y-m-d'),
            filter_IN_belongStoreNo : _form.findField("belongStoreNo").getValue(),
            filter_IN_lastStoreNo : _form.findField("lastStoreNo").getValue()
        };
        this.fireEvent("search", json);
        this.hide();
    }
});