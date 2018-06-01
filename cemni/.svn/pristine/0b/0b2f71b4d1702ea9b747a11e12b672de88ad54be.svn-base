Ext.ns('Q.archive');
Q.archive.custBatchSearchWin = function(config) {
    var formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("search");

    var _config = this.config = Ext.apply({
        title : "个人档案批量查询",
        width : 700,
        height : 490,
        layout : "border",
        items : formPanel
    }, config || {});
    Q.archive.custBatchSearchWin.superclass.constructor.call(this, _config);
};

var checkboxGroup = new Ext.form.CheckboxGroup({
    xtype : 'checkboxgroup',
    name : 'source',
    anchor : "-3",
    fieldLabel : '来源渠道',
    items : [ {
        boxLabel : 'CRM',
        value : 2
    }, {
        boxLabel : '市场活动',
        value : 7
    }, {
        boxLabel : '异业联盟',
        value : 8
    }, {
        boxLabel : '客户推荐',
        value : 9
    }, {
        boxLabel : 'NC',
        value : 1
    }, {
        boxLabel : '耀我网',
        value : 4
    }, {
        boxLabel : '微信',
        value : 3
    }, {
        boxLabel : '其他',
        value : 10
    } ]
});

Ext.extend(Q.archive.custBatchSearchWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        var _win = this;
        return new Ext.form.FormPanel({
            region : "center",
            layout : "column",
            border : true,
            bodyStyle : "padding:10px",
            defaults : {
                columnWidth : 0.5,
                layout : "form",
                border : false,
                labelWidth : 100
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
                    fieldLabel : "手机号码",
                    name : "filter_LIKE_mobile"
                }, {
                    fieldLabel : "审核状态",
                    hiddenName : "filter_IN_status",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.statusStore
                }, {
                    fieldLabel : "活跃状态",
                    hiddenName : "filter_IN_active",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.activeStore
                }, {
                    fieldLabel : "会员等级",
                    hiddenName : "filter_IN_lv",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.lvStore
                }, {
                    fieldLabel : "产品分数",
                    hiddenName : "filter_IN_posOrderList_scoreSegment",
                    xtype : 'lovcombo',
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.archive.selStores.cpScoreStore,
                    anchor : "90.5%"
                }, {
                    fieldLabel : "当前可用积分",
                    name : "filter_GE_credit"
                }, {
                    fieldLabel : "累计珠宝折算额",
                    name : "filter_GE_jewerlyAmount"
                }, {
                    fieldLabel : "生日",
                    xtype : "datefield",
                    name : "filter_GE_birthday",
                    editable : false,
                    format : 'Y-m-d',
                }, {
                    fieldLabel : "出生月日",
                    xtype : "datefield",
                    name : "filter_GE_birthMonthday",
                    editable : false,
                    format : "m-d"
                }, {
                    xtype : "datefield",
                    fieldLabel : "末次消费日期",
                    editable : false,
                    name : "filter_GE_lastBuyTime",
                    format : "Y-m-d"
                }, {
                    xtype : "datefield",
                    fieldLabel : "创建日期",
                    editable : false,
                    name : "filter_GE_cdate",
                    format : "Y-m-d"
                }, {
                    name : "filter_IN_belongStoreNo",
                    xtype : "hidden"
                }, {
                    fieldLabel : "归属大区门店",
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
                                if (_win.formPanel.getForm().findField('belongStoreNameList').getValue()) {
                                    Q.warning("归属大区门店和归属门店二选一！");
                                    return;
                                }
                                var _storeWin = new Q.org.chooseOrgWin({
                                    url : path + "/Org_getOrgTreeList.action",
                                });
                                _storeWin.on("submit", function(r) {
                                    var codeArr = [];
                                    var nameArr = [];
                                    Q.each(r, function() {
                                        if (this.data.type == 5) {
                                            codeArr.push(this.data.orgCode);
                                            nameArr.push(this.data.name);
                                        }
                                    });
                                    _win.formPanel.getForm().findField('filter_IN_belongStoreNo').setValue(codeArr.join(","));
                                    _win.formPanel.getForm().findField('belongStoreName').setValue(nameArr.join(","));
                                });
                                _storeWin.show();
                            });
                        }
                    }
                } ]
            }, {
                defaults : {
                    xtype : "textfield",
                    anchor : "90%"
                },
                items : [ {
                    fieldLabel : "客户名称",
                    name : "filter_LIKE_name"
                }, {
                    fieldLabel : "客户类型",
                    hiddenName : "filter_IN_type",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    store : Q.archive.selStores.typeStore,
                    anchor : "90.5%",
                    clearable : true
                }, {
                    fieldLabel : "新老会员",
                    hiddenName : "filter_IN_fresh",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    store : Q.archive.selStores.freshStore,
                    anchor : "90.5%",
                    clearable : true
                }, {
                    fieldLabel : "使用状态",
                    hiddenName : "filter_IN_enable",
                    xtype : "lovcombo",
                    valueField : "value",
                    displayField : "name",
                    anchor : "90.5%",
                    clearable : true,
                    store : Q.archive.selStores.enableStore
                }, {
                    fieldLabel : "产品设计师款",
                    hiddenName : "filter_IN_posOrderList_designerStyle",
                    xtype : 'lovcombo',
                    valueField : "value",
                    displayField : "name",
                    clearable : true,
                    store : Q.archive.selStores.designerStore,
                    anchor : "90.5%"
                }, {
                    name : "filter_IN_lastStoreNo",
                    xtype : "hidden"
                }, {
                    fieldLabel : "末次消费门店",
                    name : "lastStoreName",
                    xtype : "textfield",
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
                                    _win.formPanel.getForm().findField('lastStoreName').setValue(nameArr.join(","));
                                    _win.formPanel.getForm().findField('filter_IN_lastStoreNo').setValue(codeArr.join(","));
                                });
                                _storeWin.show();
                            });
                        }
                    }
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_credit"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_jewerlyAmount"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_birthday",
                    xtype : "datefield",
                    editable : false,
                    format : "Y-m-d"
                }, {
                    fieldLabel : "至",
                    name : "filter_LE_birthMonthday",
                    xtype : "datefield",
                    editable : false,
                    format : "m-d"
                }, {
                    fieldLabel : "至",
                    xtype : "datefield",
                    editable : false,
                    name : "filter_LE_lastBuyTime",
                    format : "Y-m-d"
                }, {
                    fieldLabel : "至",
                    xtype : "datefield",
                    name : "filter_LE_cdate",
                    editable : false,
                    format : "Y-m-d"
                }, {
                    fieldLabel : "归属门店",
                    xtype : "textfield",
                    name : "belongStoreNameList",
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
                                if (_win.formPanel.getForm().findField('belongStoreName').getValue()) {
                                    Q.warning("归属大区门店和归属门店二选一！");
                                    return;
                                }
                                var _storeWin = new Q.afterservice.chooseOrgStoreWin({
                                    singleSelect : false
                                });
                                _storeWin.on("submit", function(r) {
                                    var codeArr = [];
                                    var nameArr = [];
                                    Q.each(r, function() {
                                        codeArr.push(this.data.orgStoreCode);
                                        nameArr.push(this.data.orgStoreName);
                                    });
                                    _win.formPanel.getForm().findField('filter_IN_belongStoreNo').setValue(codeArr.join(","));
                                    _win.formPanel.getForm().findField('belongStoreNameList').setValue(nameArr.join(","));
                                });
                                _storeWin.show();
                            });
                        }
                    }
                }, {
                    fieldLabel : "来源",
                    name : "filter_IN_sources",
                    xtype : "hidden",
                } ]
            }, {
                columnWidth : .37,
                items : [ {
                    fieldLabel : "地址",
                    xtype : "uxcombo",
                    hiddenName : 'filter_EQ_province',
                    anchor : "100%",
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.provinceStore,
                    emptyText : "省份",
                    listeners : {
                        select : function(c) {
                            var _form = vp.editWin.formPanel.getForm();
                            _form.findField("model.city").reset();
                            _form.findField("model.county").reset();

                            Q.archive.selStores.cityStore.removeAll();
                            Q.archive.selStores.cityStore.baseParams = {
                                "filter_EQ_parentcode" : c.getValue()
                            };
                            Q.archive.selStores.cityStore.load();
                            Q.archive.selStores.countyStore.removeAll();
                        }
                    }
                } ]
            }, {
                columnWidth : .281,
                labelWidth : 1,
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'filter_EQ_city',
                    anchor : "100%",
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.cityStore,
                    emptyText : "地市",
                    listeners : {
                        select : function(c) {
                            var _form = vp.editWin.formPanel.getForm();
                            _form.findField("model.county").reset();
                            Q.archive.selStores.countyStore.removeAll();
                            Q.archive.selStores.countyStore.baseParams = {
                                "filter_EQ_parentcode" : c.getValue()
                            };
                            Q.archive.selStores.countyStore.load();
                        }
                    }
                } ]
            }, {
                columnWidth : .339,
                labelWidth : 1,
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'filter_EQ_county',
                    anchor : "90%",
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.countyStore,
                    emptyText : "区县"
                } ]
            }, {
                columnWidth : 1,
                items : checkboxGroup
            } ],
            buttons : [ {
                text : "查询",
                handler : function() {
                    var ids = [];
                    var cbitems = checkboxGroup.items;
                    for (var i = 0; i < cbitems.length; i++) {
                        if (cbitems.itemAt(i).checked) {
                            ids.push(cbitems.itemAt(i).value);
                        }
                    }
                    if (ids) {
                        _win.formPanel.getForm().findField('filter_IN_sources').setValue(ids.toString());
                    }
                    _win.doSelect();
                }
            }, {
                text : "返回",
                handler : function() {
                    _win.hide();
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