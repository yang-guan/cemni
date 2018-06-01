Ext.ns("Q.actment");
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.actment.custBatchSearchWin = function(config) {
    var formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    var _config = this.config = Ext.apply({
        title : "参与会员-查询",
        width : 700,
        height : 400,
        layout : "border",
        items : formPanel
    }, config || {});
    Q.actment.custBatchSearchWin.superclass.constructor.call(this, _config);
};

Q.actment.selStores = {
    // 客户类型
    typeStore : Q.common.selDict(1101),
    // 会员等级
    lvStore : Q.common.selDict(3100),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 产品设计师款
    designStore : Q.common.selDict(2200),
    // 产品分数段
    gradeStore : Q.common.selDict(9800),
    // 存货分类
    stockTypeStore : Q.common.selDict(9801),
    // 新老会员标识
    freshStore : Q.common.selDict(1114),
};

Ext.extend(Q.actment.custBatchSearchWin, Ext.ux.Window, {
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
                labelWidth : 100
            },
            items : [ {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                columnWidth : 0.5,
                items : [ {
                    fieldLabel : "会员卡号",
                    name : "filter_EQ_cardNo"
                }, {
                    fieldLabel : "新老会员",
                    hiddenName : "filter_IN_fresh",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    store : Q.actment.selStores.freshStore,
                    clearable : true
                }, {
                    fieldLabel : "产品设计师款",
                    hiddenName : "filter_IN_posOrderList_designerStyle",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.actment.selStores.designStore
                }, {
                    fieldLabel : "产品分数",
                    hiddenName : "filter_IN_posOrderList_scoreSegment",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.actment.selStores.gradeStore
                }, {
                    fieldLabel : "生日",
                    xtype : "datefield",
                    name : "filter_GE_birthday",
                    format : "Y-m-d"
                }, {
                    fieldLabel : "出生月日",
                    xtype : "datefield",
                    name : "filter_GE_birthMonthday",
                    format : "m-d"
                }, {
                    xtype : "datefield",
                    fieldLabel : "末次消费日期",
                    name : "filter_GE_lastBuyTime",
                    format : "Y-m-d"
                }, {
                    fieldLabel : "累计珠宝折算额",
                    name : "filter_GE_jewerlyAmount"
                }, {
                    fieldLabel : "当前可用积分",
                    name : "filter_GE_credit"
                }, {
                    name : "filter_IN_lastStoreNo",
                    xtype : "hidden"
                }, {
                    fieldLabel : "末次消费门店",
                    xtype : "textfield",
                    name : "lastStoreName",
                    emptyText : '请选择',
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
                                var _form = win.formPanel.getForm();

                                var _win = new Q.store.chooseStoreWin({
                                    singleSelect : false
                                });
                                _win.on("submit", function(r) {
                                    var nameArr = [];
                                    var noArr = [];
                                    Q.each(r, function(r1) {
                                        nameArr.push(r1.data.name);
                                        noArr.push(r1.data.storeNo);
                                    });
                                    _form.findField("lastStoreName").setValue(nameArr.join(","));
                                    _form.findField("filter_IN_lastStoreNo").setValue(noArr.join(","));
                                });
                                _win.show();
                            });
                        }
                    }
                }, {
                    fieldLabel : "手机号码",
                    name : "filter_EQ_mobile"
                } ]
            }, {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                columnWidth : 0.5,
                items : [ {
                    fieldLabel : "客户名称",
                    name : "filter_LIKE_name"
                }, {
                    fieldLabel : '客户类型',
                    hiddenName : 'filter_IN_type',
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    store : Q.actment.selStores.typeStore,
                    clearable : true
                }, {
                    fieldLabel : "会员等级",
                    hiddenName : "filter_IN_lv",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.actment.selStores.lvStore
                }, {
                    fieldLabel : "活跃状态",
                    hiddenName : "filter_IN_active",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.actment.selStores.activeStore
                }, {
                    name : "filter_IN_posOrderList_stockType",
                    xtype : "hidden"
                }, {
                    fieldLabel : "存货分类",
                    name : "stockTypeName",
                    xtype : "textfield",
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
                            var _form = win.formPanel.getForm();
                            Q.dict.multiSelectText(p, "存货分类", 9801, _form.findField('stockTypeName'), _form.findField('filter_IN_posOrderList_stockType'));
                        }
                    }
                }, {
                    fieldLabel : "至",
                    xtype : "datefield",
                    name : "filter_LE_birthday",
                    format : "Y-m-d"
                }, {
                    fieldLabel : "至",
                    xtype : "datefield",
                    name : "filter_LE_birthMonthday",
                    format : "m-d"
                }, {
                    fieldLabel : "至",
                    xtype : "datefield",
                    name : "filter_LE_lastBuyTime",
                    format : "Y-m-d"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_jewerlyAmount"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_credit"
                }, {
                    name : "filter_IN_belongStoreNo",
                    xtype : "hidden"
                }, {
                    name : "addOrSearch",
                    xtype : "hidden",
                    value : "search"
                }, {
                    fieldLabel : "归属门店",
                    xtype : "textfield",
                    name : "belongStoreName",
                    emptyText : '请选择',
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
                                var _form = win.formPanel.getForm();
                                var _win = new Q.afterservice.chooseOrgStoreWin({
                                    singleSelect : false,
                                    arrScopeStore : arrScopeStore,
                                    action : "activitySearch"
                                });
                                _win.on("submit", function(r) {
                                    var nameArr = [];
                                    var noArr = [];
                                    Q.each(r, function(r1) {
                                        nameArr.push(r1.data.orgStoreName);
                                        noArr.push(r1.data.orgStoreCode);
                                    });
                                    _form.findField("belongStoreName").setValue(nameArr.join(","));
                                    _form.findField("filter_IN_belongStoreNo").setValue(noArr.join(","));
                                });
                                _win.show();
                            });
                        }
                    }
                } ]
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