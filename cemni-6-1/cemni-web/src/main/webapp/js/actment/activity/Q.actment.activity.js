Ext.ns('Q.Activity');
var dealUrl = path + '/Activity';
var file_size_limit = 5;
var arrScopeStore = [];

Q.Activity.selStores = {
    // 活动类型
    actType : new Ext.data.JsonStore({
        url : path + "/Dict_selDict.action?dictCode=6100",
        fields : [ "name", "value", "dictId" ],
        autoLoad : true
    }),
    // 活动形式
    formAct : new Ext.data.JsonStore({
        url : path + "/Dict_selDictByParentId.action",
        fields : [ "name", "value", "dictId" ],
        autoLoad : false
    }),
    // 活动状态
    actStatus : Q.common.selDict(6103),
    // 产品设计师款
    designStore : Q.common.selDict(2200),
    // 存货分类
    stockTypeStore : Q.common.selDict(9801)
};

var checkShowTab = function(type, code) {
    if (!type) {
        vp.editWin.showTabItem("fraPartIn");
        vp.editWin.showTabItem("indiPartIn");
    } else if (type == 2) {
        if (code == "0013") {
            vp.editWin.showTabItem("fraPartIn");
            vp.editWin.showTabItem("indiPartIn");
        } else {
            vp.editWin.showTabItem("fraPartIn");
            vp.editWin.hideTabItem("indiPartIn");
            vp.editWin.setActiveTab([ "indiPartIn" ]);
        }
    } else if (type == 3 || type == 4) {
        vp.editWin.showTabItem("fraPartIn");
        vp.editWin.showTabItem("indiPartIn");
    } else if (type == 5) {
        vp.editWin.showTabItem("indiPartIn");
        vp.editWin.hideTabItem("fraPartIn");
        vp.editWin.setActiveTab([ "fraPartIn" ]);
    }
};

// 列表
var gridColumn = [ {
    dataIndex : "activityId",
    hidden : true
}, {
    header : "活动单号",
    dataIndex : "activityNo",
    width : 10
}, {
    header : "活动主题",
    dataIndex : "activityTheme",
    width : 20
}, {
    header : "活动类型",
    dataIndex : "activityTypeName",
    width : 8
}, {
    header : "发起人员",
    dataIndex : "launchor",
    width : 8
}, {
    header : "发起部门",
    dataIndex : "org",
    renderer : function(v) {
        if (!!v) {
            return v.name;
        }
    },
    width : 15
}, {
    header : "开始日期",
    dataIndex : "beginTime",
    renderer : Q.common.dateRenderer,
    width : 8
}, {
    header : "结束日期",
    dataIndex : "endTime",
    renderer : Q.common.dateRenderer,
    width : 8
}, {
    header : "活动状态",
    dataIndex : "statusName",
    width : 7
}, {
    header : "审核状态",
    dataIndex : "auditStatusName",
    width : 7
}, {
    header : "是否模版",
    dataIndex : "template",
    renderer : Q.common.yesOrNotRenderer,
    width : 7
} ];

// 表单
var editFormItems = [ {
    xtype : "fieldset",
    layout : "column",
    style : "padding: 0 0 0 30px;",
    border : false,
    anchor : "0",
    defaults : {
        layout : "form",
        border : false,
        columnWidth : 0.33
    },
    items : [ {
        items : {
            name : 'model.activityId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.indiPartInParam',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.auditStatus',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.auditCost',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.activityScope',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.variety',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.series',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.assistDept',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.type',
            id : "type",
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.orgCode',
            id : "code",
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.orgId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.createDate',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.createUser',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '活动单号',
            name : 'model.activityNo',
            emptyText : '系统自动生成',
            xtype : "textfield",
            readOnly : true,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '活动类型<font color="red">*</font>',
            hiddenName : 'model.activityType',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.Activity.selStores.actType,
            emptyText : '请选择',
            allowBlank : false,
            anchor : "85.6%",
            listeners : {
                select : function(c, r, i) {
                    var _form = vp.editWin.formPanel.getForm();
                    _form.findField("model.activityForm").reset();
                    _form.findField("model.lowerLimit").setValue("");
                    _form.findField("model.preferential").setValue("");
                    Q.Activity.selStores.formAct.removeAll();
                    Q.Activity.selStores.formAct.baseParams = {
                        "parentId" : r.get("dictId")
                    };
                    Q.Activity.selStores.formAct.load();
                    var acttype = c.getValue();
                    if (acttype == "1" && _form.findField("model.activityForm").getValue("2") || _form.findField("model.activityForm").getValue("3")) {
                        Ext.getCmp("lowerLimitid").getEl().dom.readOnly = false;
                        Ext.getCmp("preferentialid").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("lowerLimitid").getEl().dom.readOnly = true;
                        Ext.getCmp("preferentialid").getEl().dom.readOnly = true;
                    }
                    if (acttype == "1" && _form.findField("model.activityForm").getValue("28")) {
                        Ext.getCmp("integralReward").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("integralReward").getEl().dom.readOnly = true;
                    }
                }
            }
        }
    }, {
        items : {
            fieldLabel : '活动形式<font color="red">*</font>',
            hiddenName : 'model.activityForm',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.Activity.selStores.formAct,
            emptyText : '请选择',
            allowBlank : false,
            anchor : "85.6%",
            listeners : {
                "select" : function(c) {
                    var formPanel = vp.editWin.formPanel;
                    formPanel.getForm().findField("model.lowerLimit").setValue("");
                    formPanel.getForm().findField("model.preferential").setValue("");
                    var typeid = c.getValue();
                    if (typeid == "2" || typeid == "3") {
                        Ext.getCmp("lowerLimitid").getEl().dom.readOnly = false;
                        Ext.getCmp("preferentialid").getEl().dom.readOnly = false;
                        Ext.getCmp("integralReward").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("lowerLimitid").getEl().dom.readOnly = true;
                        Ext.getCmp("preferentialid").getEl().dom.readOnly = true;
                    }
                    if (typeid == "28") {
                        Ext.getCmp("integralReward").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("integralReward").getEl().dom.readOnly = true;
                    }
                }
            }
        }
    }, {
        columnWidth : 0.66,
        items : {
            fieldLabel : '活动主题<font color="red">*</font>',
            name : 'model.activityTheme',
            xtype : "textfield",
            maxLength : 20,
            allowBlank : false,
            emptyText : '请输入20个汉字/数字/字母/符号之内的活动主题',
            anchor : "92.3%"
        }
    }, {
        items : {
            fieldLabel : '卡券数量',
            name : 'model.cardAmount',
            xtype : "numberfield",
            readOnly : true,
            anchor : "84.7%"
        }
    }, {
        items : {
            fieldLabel : '是否生成卡券',
            anchor : "85.6%",
            hiddenName : 'model.isCreateCard',
            xtype : "uxcombo",
            value : 0,
            store : Q.common.yesOrNotStore
        }
    }, {
        items : {
            fieldLabel : '积分奖励',
            name : 'model.integralReward',
            id : 'integralReward',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '发起部门<font color="red">*</font>',
            name : 'model.org.name',
            xtype : "textfield",
            id : "launchdept",
            editable : false,
            allowBlank : false,
            emptyText : '请选择',
            readOnly : true,
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        if (bntType == "view") {
                            return;
                        }
                        var _win = new Q.activity.chooseStoreWin();
                        var _indistore = vp.editWin.getCompByTabClassName('indiPartIn').getStore();
                        var _frastore = vp.editWin.getCompByTabClassName('fraPartIn').getStore();
                        var _parstore = vp.editWin.getCompByTabClassName('parPartIn').getStore();
                        _win.on("submit", function() {
                            var retConfigs = _win.retConfig;
                            vp.editWin.formPanel.getForm().findField("model.org.type").setValue(retConfigs.json.type);
                            vp.editWin.formPanel.getForm().findField("model.org.orgCode").setValue(retConfigs.json.orgcode);
                            vp.editWin.formPanel.getForm().findField("model.org.orgId").setValue(retConfigs.json.orgid);
                            var v = vp.editWin.formPanel.getForm().findField("model.org.type").getValue();
                            var p = vp.editWin.formPanel.getForm().findField("model.org.orgCode").getValue();
                            checkShowTab(v, p);
                            if (retConfigs.json.type == 5 || retConfigs.json.type == 3 || retConfigs.json.type == 4) {
                                vp.editWin.formPanel.getForm().findField("model.activityScope").setValue(retConfigs.get('orgcode'));
                                vp.editWin.formPanel.getForm().findField("model.activityScopeName").setValue(retConfigs.get('name'));
                                Ext.getCmp("indiDownLoadBnt").disable();
                                Ext.getCmp("indiPartInUploadBnt").enable();
                                Ext.getCmp("indiExportBnt").disable();
                                Ext.getCmp("parExportBnt").disable();
                                arrScopeStore = [];
                                arrScopeStore.push(retConfigs.get('orgcode'));
                            } else if (retConfigs.json.type == 2) {
                                vp.editWin.formPanel.getForm().findField("model.activityScope").setValue(retConfigs.get('orgcode'));
                                vp.editWin.formPanel.getForm().findField("model.activityScopeName").setValue(retConfigs.get('name'));
                                Ext.getCmp("indiDownLoadBnt").enable();
                                Ext.getCmp("indiPartInUploadBnt").enable();
                                Ext.getCmp("indiExportBnt").enable();
                                Ext.getCmp("parExportBnt").enable();
                                arrScopeStore = [];
                                arrScopeStore.push(retConfigs.get('orgcode'));
                            } else {
                                vp.editWin.formPanel.getForm().findField("model.activityScope").setValue(null);
                                vp.editWin.formPanel.getForm().findField("model.activityScopeName").setValue("");
                                arrScopeStore = [];
                            }
                            Ext.getCmp('launchdept').setValue(retConfigs.get('name'));
                            vp.editWin.formPanel.getForm().findField("model.orgCode").setValue(retConfigs.get('orgcode'));
                            if (Ext.getCmp('launchdept').getValue() != null || Ext.getCmp('launchdept').getValue() != "") {
                                Ext.getCmp("indiPartInBnt").enable();
                                Ext.getCmp("fraPartInBnt").enable();
                                Ext.getCmp("parPartInBnt").enable();
                            }
                            _indistore.removeAll();
                            _frastore.removeAll();
                            _parstore.removeAll();
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            name : 'model.orgCode',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '适用范围<font color="red">*</font>',
            name : 'model.activityScopeName',
            xtype : "textfield",
            editable : false,
            allowBlank : false,
            anchor : "85%",
            readOnly : true,
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
                        if (bntType == "view" || vp.editWin.formPanel.getForm().findField("model.org.type").getValue() == 5) {
                            return;
                        }
                        var _form = vp.editWin.formPanel.getForm();
                        var activityScopeForm = _form.findField("model.activityScope").getValue(); // 选择之前的适用范围
                        var _win = new Q.org.chooseOrgWin({
                            url : path + "/Org_getOrgTreeList.action",
                            orgCodes : _form.findField("model.activityScope").getValue()
                        });
                        _win.on("submit", function(r) {
                            var codeArr = [];
                            var nameArr = [];
                            arrScopeStore = [];
                            Q.each(r, function() {
                                // 活动范围门店Code
                                if (this.data.type == 5) {
                                    arrScopeStore.push(this.data.orgCode);
                                    codeArr.push(this.data.orgCode);
                                    nameArr.push(this.data.name);
                                }
                            });
                            _form.findField("model.activityScope").setValue(codeArr.join(","));
                            _form.findField("model.activityScopeName").setValue(nameArr.join(","));

                            // 使用范围变动，参与会员清空
                            if (_form.findField("model.activityScope").getValue() != activityScopeForm) {
                                vp.editWin.getCompByTabClassName('indiPartIn').getStore().removeAll();
                            }
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '开始日期<font color="red">*</font>',
            name : 'model.beginTime',
            xtype : "datefield",
            format : 'Y-m-d',
            editable : false,
            allowBlank : false,
            anchor : "85%",
            listeners : {
                select : function() {
                    var form = vp.editWin.formPanel.getForm();
                    var beginTime = form.findField("model.beginTime").getValue();
                    var endTime = form.findField("model.endTime").getValue();
                    if (beginTime !== "" && endTime !== "") {
                        if (endTime < beginTime) {
                            Q.warning("开始日期不能晚于结束日期");
                            form.findField("model.beginTime").setValue("");
                        }
                    }
                }
            }
        }
    }, {
        items : {
            fieldLabel : '结束日期<font color="red">*</font>',
            name : 'model.endTime',
            xtype : "datefield",
            format : 'Y-m-d',
            allowBlank : false,
            editable : false,
            anchor : "85%",
            listeners : {
                select : function() {
                    var form = vp.editWin.formPanel.getForm();
                    var beginTime = form.findField("model.beginTime").getValue();
                    var endTime = form.findField("model.endTime").getValue();
                    if (beginTime !== "" && endTime !== "") {
                        if (endTime < beginTime) {
                            Q.warning("结束日期不能早于开始日期");
                            form.findField("model.endTime").setValue("");
                        }
                    }
                }
            }
        }
    }, {
        items : {
            fieldLabel : '协助部门',
            name : 'model.assistDeptName',
            xtype : "textfield",
            anchor : "85%",
            editable : false,
            readOnly : true,
            emptyText : '请选择',
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (bntType == "view") {
                            return;
                        }
                        var _win = new Q.activity.chooseOrgWin({
                            singleSelect : false,
                            orgCodes : _form.findField("model.assistDept").getValue()
                        });
                        _win.on("submit", function(r) {
                            var nameArr = [];
                            var codeArr = [];
                            Q.each(r, function() {
                                codeArr.push(this.data.orgCode);
                                nameArr.push(this.data.name);
                            });
                            _form.findField("model.assistDept").setValue(codeArr.join(","));
                            _form.findField("model.assistDeptName").setValue(nameArr.join(","));
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '适用品类',
            name : 'model.varietyName',
            xtype : "textfield",
            emptyText : '请选择',
            editable : false,
            readOnly : true,
            anchor : "85%",
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
                    var _form = vp.editWin.formPanel.getForm();
                    var isHidden = (bntType == "view");
                    Q.dict.multiSelectText(p, "适用品类", 9801, _form.findField('model.varietyName'), _form.findField('model.variety'), isHidden);
                }
            }
        }
    }, {
        items : {
            fieldLabel : '适用系列',
            name : 'model.seriesName',
            xtype : "textfield",
            emptyText : '请选择',
            editable : false,
            readOnly : true,
            anchor : "85%",
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
                    var _form = vp.editWin.formPanel.getForm();
                    if (_form.findField("model.varietyName").getValue() != "") {
                        return;
                    }
                    var isHidden = (bntType == "view");
                    Q.dict.multiSelectText(p, "适用系列", 2200, _form.findField('model.seriesName'), _form.findField('model.series'), isHidden);
                }
            }
        }
    }, {
        items : {
            fieldLabel : '是否模版',
            hiddenName : 'model.template',
            xtype : "uxcombo",
            hidden : true,
            store : Q.common.yesOrNotStore,
            disabled : true,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '累计珠宝额>=',
            name : 'model.jewelleryAmountG',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '累计珠宝额<=',
            name : 'model.jewelleryAmountL',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            id : "lowerLimitid",
            fieldLabel : '购买下限值',
            name : 'model.lowerLimit',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            id : "preferentialid",
            fieldLabel : '优惠金额',
            name : 'model.preferential',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '客流目标',
            name : 'model.cardtatget',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '销售目标',
            name : 'model.saletatget',
            xtype : "numberfield",
            anchor : "85%"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '二维码地址',
            name : 'model.wecharCodeAddr',
            xtype : "textfield",
            anchor : "93.8%",
            readOnly : true
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '定位承诺',
            name : 'model.promise',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '市场概览',
            name : 'model.overview',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '活动背景',
            name : 'model.background',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '活动目的',
            name : 'model.aim',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '传播计划',
            name : 'model.plan',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '需协助事项',
            name : 'model.assist',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '活动内容',
            name : 'model.content',
            xtype : "htmleditor",
            anchor : "93.8%",
            height : 250
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '适用规则',
            name : 'model.rules',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            name : 'model.remark',
            xtype : "textarea",
            anchor : "93.8%",
            height : 50
        }
    }, {
        items : [ {
            fieldLabel : $("label.Annex"),// 上传附件
            xtype : "button",
            anchor : "80%",
            Qtext : "添加附件",
            name : 'uploadBtn',
            id : "updateload",
            text : $("label.AddUpLoadFile"),
            columnWidth : 0.5,
            handler : function() {
                var formPanel = vp.editWin.formPanel;
                var uploadFileGroupId = formPanel.form.findField("model.uploadFileGroupId").getValue();
                vp.openUploadWindows("", uploadFileGroupId, "uploadFile4View", formPanel, "");
            }
        }, {
            name : 'model.uploadFileGroupId',
            xtype : "hidden"
        }, {
            xtype : 'displayfield',
            name : 'uploadFile4View',
            QfieldLabel : '附件查看',
            fieldLabel : $("label.AttachmentsCheck")
        } ]
    } ]
} ];

// /////////////////////////////////////////////////////////////////////////////////////////////////////////

// 参与会员
var gridDtlIndi = {
    tabTitle : "参与会员",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "indiPartIn",
    pageSize : 20,
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        columns : [ {
            dataIndex : "individCust.individCustId",
            disabled : true
        }, {
            dataIndex : "indiPartInId",
            disabled : true
        }, {
            header : "会员卡号",
            dataIndex : "individCust.cardNo",
            width : 10
        }, {
            header : "客户名称",
            dataIndex : "individCust.name",
            width : 15
        }, {
            header : "客户类型",
            dataIndex : "individCust.typeName",
            width : 8
        }, {
            header : "活跃状态",
            dataIndex : "individCust.activeName",
            width : 8
        }, {
            header : "会员等级",
            dataIndex : "individCust.lvName",
            width : 8
        }, {
            header : "可用积分",
            dataIndex : "individCust.credit",
            width : 8
        }, {
            header : "卡券编码",
            dataIndex : "couponNo",
            width : 15
        }, {
            header : "使用人",
            dataIndex : "userName",
            width : 8
        }, {
            header : "是否参与",
            dataIndex : "isPartIn",
            renderer : Q.common.yesOrNotRenderer,
            width : 8
        }, {
            header : "是否使用",
            dataIndex : "isUser",
            renderer : Q.common.yesOrNotRenderer,
            width : 8
        } ]
    },
    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    store : {
        idProperty : "indiPartInId",
        url : dealUrl + "_getIndi.action",
        autoLoad : false
    },
    tbar : [ {
        id : "indiPartInBnt",
        text : "查询",
        iconCls : "icon-search",
        handler : function() {
            Q.confirm("“查询”时会先清除列表中“已存在”的数据，是否继续？", {
                ok : function() {
                    var _store = vp.editWin.getCompByTabClassName('indiPartIn').getStore();
                    var _win = new Q.actment.custBatchSearchWin();
                    _win.on("search", function(data) {
                        var _form = vp.editWin.formPanel.getForm();
                        _form.findField("model.indiPartInParam").setValue(JSON.stringify(data));

                        _store.baseParams = {
                            indiPartInParam : JSON.stringify(data),
                            activityScope : _form.findField("model.activityScope").getValue()
                        };
                        _store.load({
                            callback : function() {
                                if (_form.findField("model.isCreateCard").getValue() == 1) {
                                    _form.findField("model.cardAmount").setValue(_store.getTotalCount());// 回填卡卷数量
                                } else {
                                    _form.findField("model.cardAmount").setValue(0);// 回填卡卷数量
                                }
                            }
                        });
                        // “查询、导入、添加”按钮三选一
                        Ext.getCmp("indiPartInUploadBnt").disable();
                        Ext.getCmp("indiAdd").disable();
                        Ext.getCmp("indiDeleteBtn").disable();
                    });
                    _win.show();
                }
            });
        }
    }, {
        id : "indiDownLoadBnt",
        text : "模版下载",
        iconCls : "icon-excel",
        handler : function() {
            window.open(path + "/Common_downLoadExcelTpl.action?fileName=ActivityIndiPartIn.xlsx");
        }
    }, {
        id : "indiPartInUploadBnt",
        name : "indiPartInUploadBnt",
        text : "导入",
        iconCls : "icon-upload",
        handler : function() {
            var _store = vp.editWin.getCompByTabClassName('indiPartIn').getStore();
            var _form = vp.editWin.formPanel.getForm();
            Q.confirm("“导入”时会先清除列表中“已存在”的数据，是否继续？", {
                ok : function() {
                    _store.removeAll();
                    var _win = new Q.excel.uploadWin({
                        url : path + "/TelVisit_uploadExcel.action"
                    });
                    _win.on("upload", function(action) {
                        _form.findField("model.indiPartInParam").setValue("excel" + action.result.dataList);

                        _store.baseParams = {
                            indiPartInParam : "excel" + action.result.dataList
                        };
                        _store.load({
                            callback : function() {
                                if (_form.findField("model.isCreateCard").getValue() == 1) {
                                    _form.findField("model.cardAmount").setValue(_store.getTotalCount());// 回填卡卷数量
                                } else {
                                    _form.findField("model.cardAmount").setValue(0);// 回填卡卷数量
                                }
                            }
                        });
                        // “查询、导入、添加”按钮二选一
                        Ext.getCmp("indiPartInBnt").disable();
                        Ext.getCmp("indiAdd").disable();
                        Ext.getCmp("indiDeleteBtn").disable();
                    });
                    _win.show();
                }
            });
        }
    }, {
        id : "indiExportBnt",
        text : "导出",
        iconCls : "icon-excel",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var totalCount = _store.getTotalCount();
            if (totalCount > 5000) {
                Q.tips(Q.color("每次导出上限为5000", "red"));
                return;
            } else {
                window.open(dealUrl + "_export3.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(_store.baseParams));
            }
        }
    }, {
        id : "indiAdd",
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = vp.editWin.getCompByTabClassName('indiPartIn').getStore();
            var _form = vp.editWin.formPanel.getForm();
            var _win = new Q.actment.searchWin();

            _win.on("search", function(data) {

                var filterIds = new Array();

                var _items = _store.data.items;
                for (var i = 0; i < _items.length; i++) {
                    filterIds.push(_items[i].data["individCust.individCustId"]);
                }
                data.filter_NOTIN_individCustId = filterIds.join(",");

                Ext.getBody().loadMask();
                Ext.Ajax.request({
                    url : dealUrl + "_getIndi.action?add=true",
                    params : data,
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        Q.each(json.data, function() {
                            var r = new Ext.data.Record({
                                'individCust.individCustId' : this.individCustId,
                                'individCust.cardNo' : this.cardNo,
                                'individCust.name' : this.name,
                                'individCust.activeName' : this.activeName,
                                'individCust.lvName' : this.lvName,
                                'individCust.credit' : this.credit
                            });
                            _store.add(r);

                        });

                    },
                    callback : function() {
                        if (_form.findField("model.isCreateCard").getValue() == 1) {
                            _form.findField("model.cardAmount").setValue(_store.data.items.length);// 回填卡卷数量
                        } else {
                            _form.findField("model.cardAmount").setValue(0);// 回填卡卷数量
                        }
                        Ext.getBody().unmask();
                    }
                });
                // “查询、导入、添加”三选一
                Ext.getCmp("indiPartInUploadBnt").disable();
                Ext.getCmp("indiPartInBnt").disable();
                Ext.getCmp("indiDeleteBtn").enable();

            });
            _win.show();

        }
    }, {
        id : "indiDeleteBtn",
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]
};

// 参与加盟商
var gridDtlFra = {
    tabTitle : "参与加盟商",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "fraPartIn",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        pageSize : 0,
        columns : [ {
            dataIndex : "franchisee.franchiseeId",
            disabled : true
        }, {
            dataIndex : "fraPartInId",
            disabled : true
        }, {
            dataIndex : "_activityId",
            disabled : true
        }, {
            header : "加盟商编码",
            dataIndex : "franchisee.fraCode",
            width : 10
        }, {
            header : "加盟商名称",
            dataIndex : "franchisee.fraName",
            width : 25
        }, {
            header : "加盟商简称",
            dataIndex : "franchisee.shortName",
            width : 25
        }, {
            header : "加盟商类别",
            dataIndex : "franchisee.fraTypeName",
            width : 10
        }, {
            header : "加盟商状态",
            dataIndex : "franchisee.fraStatusName",
            width : 10
        }, {
            header : "实际控制人",
            dataIndex : "franchisee.actualCon",
            width : 10
        }, {
            header : "是否参与",
            dataIndex : "isPartIn",
            width : 10,
            editor : {
                editable : false,
                readOnly : true,
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        } ]
    },

    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    store : {
        idProperty : "fraPartInId",
        url : dealUrl + "_getFra.action",
        sort : "fraPartInId",
        dir : "asc",
        autoLoad : false
    },
    tbar : [ {
        id : "fraPartInBnt",
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var arr = new Array();
            for (var i = 0; i < _store.data.items.length; i++) {
                arr.push(_store.data.items[i].data["franchisee.franchiseeId"]);
            }

            var _win = new Q.archive.chooseFranWin({
                singleSelect : false,
                action : 'activity',
                notIn : arr
            });
            _win.on("submit", function(r) {
                Q.each(r, function(r1) {
                    var retR = new Ext.data.Record({
                        'franchisee.franchiseeId' : r1.franchiseeId,
                        'franchisee.activityId' : r1.activityId,
                        'franchisee.fraCode' : r1.fraCode,
                        'franchisee.fraName' : r1.fraName,
                        'franchisee.shortName' : r1.shortName,
                        'franchisee.fraTypeName' : r1.fraTypeName,
                        'franchisee.fraStatusName' : r1.fraStatusName,
                        'franchisee.actualCon' : r1.actualCon,
                        'isPartIn' : 0
                    });
                    _store.add(retR);
                });
            });
            _win.show();
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    }, {
        id : "fraExportBnt",
        text : "导出",
        iconCls : "icon-excel",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(_store.baseParams));
        }
    }, {
        text : "修改",
        id : "changeBtn",
        iconCls : "icon-edit",
        handler : function() {
            var _grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = _grid.getSelectionModel().getSelections();
            if (sm == 0) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var ids = [];
            Q.each(sm, function() {
                ids.push(this.data.fraPartInId);
            });
            Q.confirm("确认选中的加盟商参与了活动？", {
                ok : function() {
                    Ext.getBody().submitMask();
                    Ext.Ajax.request({
                        url : dealUrl + "_changeIsPartIn.action",
                        params : {
                            "fraPartInId" : ids.join(",")
                        },
                        success : function(response) {
                            var json = Ext.decode(response.responseText);
                            if (!json.success) {
                                Q.tips(Q.color("修改失败！"), "red");
                                return;
                            }
                            Q.tips(Q.color("修改成功！"), "red");
                            _grid.getStore().reload();
                            _grid.getSelectionModel().clearSelections();
                        },
                        failure : function(response) {
                            Q.tips(Q.color("修改失败！"), "red");
                        },
                        callback : function() {
                            Ext.getBody().unmask();
                        }
                    });
                }
            });
        }
    } ]
};

// 参与异业伙伴
var gridDtlPar = {
    tabTitle : "参与异业伙伴",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "parPartIn",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        pageSize : 0,
        columns : [ {
            dataIndex : "partner.partnerid",
            disabled : true
        }, {
            dataIndex : "parPartInId",
            disabled : true
        }, {
            dataIndex : "_activityId",
            disabled : true
        }, {
            header : "异业伙伴编码",
            dataIndex : "partner.partnerno",
            width : 100
        }, {
            header : "异业伙伴名称",
            dataIndex : "partner.partnername",
            width : 150
        }, {
            header : "异业伙伴简称",
            dataIndex : "partner.name"
        }, {
            header : "异业伙伴类别",
            dataIndex : "partner.typeName"
        }, {
            header : "实际控制人",
            dataIndex : "partner.person"
        }, {
            header : "是否参与",
            dataIndex : "isPartIn",
            editor : {
                editable : false,
                readOnly : true,
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        } ]
    },
    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    store : {
        idProperty : "parPartInId",
        url : dealUrl + "_getPar.action",
        sort : "parPartInId",
        dir : "asc",
        autoLoad : false
    },
    tbar : [ {
        id : "parPartInBnt",
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var arr = new Array();
            for (var i = 0; i < _store.data.items.length; i++) {
                arr.push(_store.data.items[i].data["partner.partnerid"]);
            }
            var _win = new Q.actment.choosePartWin({
                singleSelect : false,
                url : 'activity',
                notIn : arr
            });
            _win.on("submit", function(r) {
                Q.each(r, function(r1) {
                    var retR = new Ext.data.Record({
                        'partner.partnerid' : r1.partnerid,
                        'partner.activityId' : r1.activityId,
                        'partner.partnerno' : r1.partnerno,
                        'partner.partnername' : r1.partnername,
                        'partner.name' : r1.name,
                        'partner.typeName' : r1.typeName,
                        'partner.person' : r1.person,
                        'isPartIn' : 0
                    });
                    _store.add(retR);
                });
            });
            _win.show();
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    }, {
        id : "parExportBnt",
        text : "导出",
        iconCls : "icon-excel",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            window.open(dealUrl + "_export2.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(_store.baseParams));
        }
    }, {
        text : "修改",
        id : "changeBtn2",
        iconCls : "icon-edit",
        handler : function() {
            var _grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = _grid.getSelectionModel().getSelections();
            if (sm == 0) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var ids = [];
            Q.each(sm, function() {
                ids.push(this.data.parPartInId);
            });
            Q.confirm("确认选中的异业伙伴参与了活动？", {
                ok : function() {
                    Ext.getBody().submitMask();
                    Ext.Ajax.request({
                        url : dealUrl + "_changeIsPartIn2.action",
                        params : {
                            "parPartInId" : ids.join(",")
                        },
                        success : function(response) {
                            var json = Ext.decode(response.responseText);
                            if (!json.success) {
                                Q.tips(Q.color("修改失败！"), "red");
                                return;
                            }
                            Q.tips(Q.color("修改成功！"), "red");
                            _grid.getStore().reload();
                            _grid.getSelectionModel().clearSelections();
                        },
                        failure : function(response) {
                            Q.tips(Q.color("修改失败！"), "red");
                        },
                        callback : function() {
                            Ext.getBody().unmask();
                        }
                    });
                }
            });
        }
    } ]
};

// 费用预估
var gridDtlExpectCost = {
    id : "cost",
    tabTitle : "费用预估",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    allowEmpty : false,
    tabClassName : "expectCost",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "expectCostId",
            disabled : true
        }, {
            dataIndex : "_activityId",
            disabled : true
        }, {
            header : "项目",
            dataIndex : "actItems",
            width : 150,
            editor : {
                xtype : 'textfield',
                allowBlank : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "规格",
            dataIndex : "actSize",
            width : 100,
            editor : {
                editable : true,
                xtype : "textfield",
                allowBlank : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "单价",
            dataIndex : "actPrice",
            width : 100,
            editor : {
                xtype : 'textfield',
                allowBlank : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "数量",
            dataIndex : "actCount",
            width : 100,
            editor : {
                xtype : 'numberfield',
                allowBlank : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "预算",
            dataIndex : "budget",
            width : 100,
            editor : {
                xtype : 'textfield',
                allowBlank : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "实际花费",
            dataIndex : "actualCost",
            width : 100,
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            width : 250,
            editor : {
                xtype : 'textfield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "expectCostId",
        url : dealUrl + "_getCost.action",
        sort : "expectCostId",
        dir : "asc",
        autoLoad : false
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            vp.editWin.addDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    }, {
        text : "实际花费",
        id : "actualcost",
        iconCls : "icon-edit",
        handler : function() {
            var _grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = _grid.getSelectionModel().getSelections();
            if (sm == 0) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var ids = [];
            Q.each(sm, function() {
                ids.push(this.data.expectCostId);
            });
            var _win = new Q.actment.actualCostWin({
                singleSelect : false,
                url : dealUrl + "_actualCost.action?expectCostId=" + ids.join(",")
            });
            _win.on("submit", function() {
                _grid.getStore().reload({
                    callback : function() {
                        var total1 = 0;
                        var total2 = 0;
                        this.each(function(data) {
                            total1 += parseFloat(data.json.budget);
                            total2 += parseFloat(data.json.actualCost);
                        });
                        Ext.getCmp('applyTotalCost').setValue(total1);
                        Ext.getCmp('actuallyTotalCost').setValue(total2);
                    }
                });
            });
            _win.show();
        }
    }, "->", {
        text : "申请费用总金额"
    }, {
        xtype : "textfield",
        id : "applyTotalCost",
        width : 100,
        readOnly : true
    }, {
        text : "实际花费总金额"
    }, {
        xtype : "textfield",
        id : "actuallyTotalCost",
        width : 100,
        readOnly : true
    }, {
        text : "审核费用总金额"
    }, {
        xtype : "textfield",
        id : "auditCost",
        width : 100,
        readOnly : true
    } ],
    loadValueAfter : function(grid, store, callbackRecords) {
        // 申请费用总金额累加
        var total1 = 0;
        for (var i = 0; i < callbackRecords.length; i++) {
            total1 += parseFloat(callbackRecords[i].data.budget);
        }
        Ext.getCmp('applyTotalCost').setValue(total1);
        // 获取数据库审核费用总金额
        var _form = vp.editWin.formPanel.getForm();
        Ext.getCmp('auditCost').setValue(_form.findField("model.auditCost").getValue());

        // 判断实际花费
        var actualCost = 0;
        for (var i = 0; i < callbackRecords.length; i++) {
            if (!!callbackRecords[i].data.actualCost) {
                actualCost += parseFloat(callbackRecords[i].data.actualCost);
            }
        }
        if (actualCost == 0) {
            Ext.getCmp('actuallyTotalCost').setValue(_form.findField("model.auditCost").getValue());
        } else {
            Ext.getCmp('actuallyTotalCost').setValue(actualCost);
        }
    }
};

// 赠品信息
var gridDtlgive = {
    id : "gridgive",
    tabTitle : "赠品信息",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    validField : [ 'invCode', 'invName', 'giveNum', 'nprice' ],
    tabClassName : "actGive",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "invId",
            disabled : true
        }, {
            dataIndex : "_activityId",
            disabled : true
        }, {
            header : "赠送商品编码",
            dataIndex : "invCode",
            editor : {
                maxLength : 20,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "赠送商品名称",
            dataIndex : "invName",
            editor : {
                maxLength : 100,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "赠送数量",
            dataIndex : "giveNum",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "单价",
            dataIndex : "nprice",
            editor : {
                xtype : 'numberfield',
                allowDecimals : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "invId",
        url : dealUrl + "_getActGive.action",
        sort : "invId",
        dir : "asc",
        autoLoad : false
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.addDetail(grid);
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.deleteDetail(grid);
        }
    } ]
};

// 会员活动产出
var gridDtlSale1 = {
    tabTitle : "会员活动产出",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "posOrder",
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "posId",
            disabled : true
        }, {
            header : "POS单号",
            dataIndex : "posNo",
            width : 110
        }, {
            header : "POS单日期",
            dataIndex : "posbillDate",
            renderer : Q.common.timeRenderer,
            width : 140
        }, {
            header : "客户名称",
            dataIndex : "cardName",
            width : 150
        }, {
            header : "会员卡号",
            dataIndex : "cardNo",
            width : 100
        }, {
            header : "商品名称",
            dataIndex : "goodsName",
            width : 150
        }, {
            header : "卡券编码",
            dataIndex : "couponNo",
            width : 200
        }, {
            header : "单价",
            dataIndex : "goodsPrice",
            width : 100
        }, {
            header : "数量",
            dataIndex : "goodsCnt",
            width : 60
        }, {
            header : "实际销售金额",
            dataIndex : "actualSaleAmount",
            width : 100
        }, {
            header : "珠宝折算额",
            dataIndex : "jewelDiscountAmount",
            width : 100
        }, {
            header : "门店名称",
            dataIndex : "storeName",
            width : 200
        }, {
            header : "主营业员",
            dataIndex : "mainclerkName",
            width : 80
        }, {
            header : "副营业员",
            dataIndex : "assistantName",
            width : 80
        } ]
    },
    pageSize : 0,
    store : {
        url : path + "/Activity_getPos.action",
        autoLoad : false
    },
    vpAfterRender : function() {
        return 'view';
    }
};

// 加盟商活动产出
var gridDtlSale = {
    tabTitle : "加盟商活动产出",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "saleOrder",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "orderId",
            disabled : true
        }, {
            dataIndex : "_activityId",
            disabled : true
        }, {
            header : "加盟商编码",
            dataIndex : "fraCode",
            width : 8
        }, {
            header : "加盟商名称",
            dataIndex : "fraName",
            width : 24
        }, {
            header : "订单类型",
            dataIndex : "typeName",
            width : 8
        }, {
            header : "订单号码",
            dataIndex : "orderNo",
            width : 10
        }, {
            header : "订单日期",
            dataIndex : "orderDate",
            width : 10
        }, {
            header : "门店编码",
            dataIndex : "storeNo",
            width : 10
        }, {
            header : "门店名称",
            dataIndex : "storeName",
            width : 20
        }, {
            header : "销售金额",
            dataIndex : "totalAmount",
            width : 10
        } ]
    },
    pageSize : 0,
    store : {
        url : path + "/Activity_getOrder.action",
        autoLoad : false
    },
    vpAfterRender : function() {
        return 'view';
    }
};
// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

// 活动评价
var formdtl2 = {
    tabTitle : "活动评价",
    xtype : 'formpanel',
    foreignKey : "activity_activityId",
    tabClassName : "judgeAct",
    loadUrl : path + "/JudgeAct_getJudge.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        border : false,
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            items : [ {
                name : "activity.activityId",
                hidden : true
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "94%",
                height : "30"
            },
            columnWidth : 1,
            items : [ {
                fieldLabel : "活动效果",
                name : "actEffect"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "94%",
                height : "30"
            },
            columnWidth : 1,
            items : [ {
                fieldLabel : "活动总体评价",
                name : "totalJudge"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "94%",
                height : "30"
            },
            columnWidth : 1,
            items : [ {
                fieldLabel : "不足及改进",
                name : "improve"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "94%",
                height : "30"
            },
            columnWidth : 1,
            items : [ {
                fieldLabel : "优点及推广应用",
                name : "advantage"
            } ]
        }, {
            columnWidth : 0.33,
            items : [ {
                fieldLabel : '潜在加盟商数量',
                name : 'potentialFra',
                xtype : "textfield",
                anchor : "85%"
            } ]
        }, {
            columnWidth : 0.33,
            items : [ {
                fieldLabel : '签约加盟商数量',
                name : 'signingFra',
                xtype : "textfield",
                anchor : "85%"
            } ]
        }, {
            columnWidth : 0.33,
            items : [ {
                fieldLabel : '转化率',
                name : 'transRate',
                xtype : "textfield",
                anchor : "85%"
            } ]
        } ]
    } ]
};

// //////////////////////////////////////////////////////////////////////////////////////////////////////////

// 审核记录
var gridDtlAudit = {
    tabTitle : "审核记录",
    xtype : 'uxeditorgrid',
    foreignKey : "activity_activityId",
    tabClassName : "audit",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "activityAuditId",
            disabled : true
        }, {
            header : "核定人",
            dataIndex : "auditor",
            width : 15
        }, {
            header : "核定时间",
            dataIndex : "auditTime",
            renderer : Q.common.timeRenderer,
            width : 15
        }, {
            header : "核定结果",
            dataIndex : "resultName",
            width : 10
        }, {
            header : "审核费用总金额",
            dataIndex : "amount",
            width : 10
        }, {
            header : "核定意见",
            dataIndex : "suggest",
            width : 50
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "activityAuditId",
        url : path + "/Activity_getAudit.action",
        sort : "activityAuditId",
        dir : "desc",
        autoLoad : false
    },
    vpAfterRender : function() {
        return 'view';
    }
};

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var bntType;
var Org2act = [];
var cfg = {
    isAudit : true,
    dealUrl : dealUrl,
    moduleName : '活动管理',
    playListMode : playListMode.normal,
    menuOverride : [ {
        text : "提交",
        name : 'CONFIRM',
        iconCls : "icon-toConfirm",
        hidden : false,
        handler : function() {
            vp.dealstate(this.name, this.text);
        }
    } ],
    vp : {
        addOtherBtn : [ {
            name : 'judgeBnt',
            text : '活动评价',
            index : 6,
            iconCls : 'icon-toConfirm',
            build : power.judge,
            handler : function() {
                var _grid = vp.grid;
                var sm = _grid.getSelectionModel().getSelected();
                if (Ext.isEmpty(sm)) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return;
                }
                var _win = new Q.actment.judgeActWin(sm.get("activityId"));
                _win.on("submit", function() {
                    _grid.getStore().reload();
                });
                _win.show();
            }
        }, {
            name : 'tplBnt',
            text : '另存模版',
            index : 7,
            iconCls : 'icon-edit',
            build : power.template,
            handler : function() {
                var selected = vp.grid.getSelectionModel().getSelected();
                if (Ext.isEmpty(selected)) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return false;
                }
                var id = selected.get("activityId");
                Q.confirm("确认将该活动设置为活动模版？", {
                    ok : function() {
                        Ext.getBody().submitMask();
                        Ext.Ajax.request({
                            url : path + "/Activity_changeCode.action",
                            params : {
                                "id" : id
                            },
                            success : function(response) {
                                var json = Ext.decode(response.responseText);
                                if (!json.success) {
                                    Q.tips(Q.color("操作失败", "red"));
                                    return;
                                }
                                Q.tips(Q.color("操作成功", "red"));
                                vp.grid.getStore().reload();
                                vp.grid.getSelectionModel().clearSelections();
                            },
                            failure : function(response) {
                                Q.tips(Q.color("操作失败", "red"));
                            },
                            callback : function() {
                                Ext.getBody().unmask();
                            }
                        });
                    }
                });
            }
        } ],
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'activityId',
            url : dealUrl + '_getJson.action',
            sort : "activityId",
            dir : "desc"
        },
        listEditStateFn : [ {
            edit : function(r) {
                if (r.json.launchorCode != s_userCode || (r.json.auditStatusName == '审核中' || r.json.auditStatusName == '已通过' || r.json.auditStatusName == '驳回')) {
                    return false;
                }
                return true;
            }
        }, {
            judgeBnt : function(r) {
                if (r.json.launchorCode != s_userCode || r.json.statusName != '已结束' && r.json.statusName != '已评价') {
                    return false;
                }
                return true;
            }
        }, {
            tplBnt : function(r) {
                if (r.json.template == '1' || r.json.statusName != '已结束' && r.json.statusName != '已评价') {
                    return false;
                }
                return true;
            }
        } ],

        addAfter : function(grid, selectids, win) {
            bntType = "add";
            var select = vp.editWin.formPanel.getForm().findField("model.activityForm").getValue();
            if (select == "2" || select == "3") {
                Ext.getCmp("lowerLimitid").getEl().dom.readOnly = false;
                Ext.getCmp("preferentialid").getEl().dom.readOnly = false;

            } else {
                Ext.getCmp("lowerLimitid").getEl().dom.readOnly = true;
                Ext.getCmp("preferentialid").getEl().dom.readOnly = true;
            }
            if (select == "28") {
                Ext.getCmp("integralReward").getEl().dom.readOnly = false;
            } else {
                Ext.getCmp("integralReward").getEl().dom.readOnly = true;
            }

            vp.editWin.hideTabItem("posOrder");
            vp.editWin.hideTabItem("saleOrder");
            vp.editWin.hideTabItem("audit");
            vp.editWin.hideTabItem("judgeAct");
            vp.editWin.setActiveTab([ "posOrder", "saleOrder", "audit", "judgeAct" ]);
            Ext.getCmp("indiDeleteBtn").disable();

            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.hide();
            Ext.getCmp('applyTotalCost').setValue('0');
            Ext.getCmp('actuallyTotalCost').setValue('0');
            Ext.getCmp('auditCost').setValue('0');
            Ext.getCmp("changeBtn").disable();
            Ext.getCmp("changeBtn2").disable();
            Ext.getCmp("actualcost").disable();
            if (Ext.getCmp('launchdept').getValue() == "" || Ext.getCmp('launchdept').getValue() == null) {
                Ext.getCmp("indiPartInBnt").disable();
                Ext.getCmp("fraPartInBnt").disable();
                Ext.getCmp("parPartInBnt").disable();
            }
            vp.editWin.formPanel.getForm().findField("model.org.type").setValue("");
            vp.editWin.formPanel.getForm().findField("model.org.orgCode").setValue("");
            var type = vp.editWin.formPanel.getForm().findField("model.org.type").getValue();
            var code = vp.editWin.formPanel.getForm().findField("model.org.orgCode").getValue();
            checkShowTab(type, code);
            vp.editWin.formPanel.getForm().findField("model.isCreateCard").disabled = false;

            // 发起部门回填
            Ext.Ajax.request({
                url : path + "/IndividCust_getOrgByUserId.action",
                success : function(response) {
                    var json = Ext.decode(response.responseText);
                    if (json.success) {
                        if (json.data.length > 0) {
                            var orgType = json.data[0].type;
                            var orgCode = json.data[0].orgcode;
                            var orgId = json.data[0].orgid;
                            var orgName = json.data[0].name;
                            var _form = vp.editWin.formPanel.getForm();

                            var _indistore = vp.editWin.getCompByTabClassName('indiPartIn').getStore();
                            var _frastore = vp.editWin.getCompByTabClassName('fraPartIn').getStore();
                            var _parstore = vp.editWin.getCompByTabClassName('parPartIn').getStore();
                            _form.findField("model.org.type").setValue(orgType);
                            _form.findField("model.org.orgCode").setValue(orgCode);
                            _form.findField("model.org.name").setValue(orgName);
                            _form.findField("model.org.orgId").setValue(orgId);
                            var v = _form.findField("model.org.type").getValue();
                            var p = _form.findField("model.org.orgCode").getValue();
                            checkShowTab(v, p);
                            if (orgType == 5 || orgType == 3 || orgType == 4) {
                                _form.findField("model.activityScope").setValue(orgCode);
                                _form.findField("model.activityScopeName").setValue(orgName);
                                Ext.getCmp("indiDownLoadBnt").disable();
                                Ext.getCmp("indiPartInUploadBnt").enable();
                                Ext.getCmp("indiExportBnt").disable();
                                Ext.getCmp("parExportBnt").disable();
                                // 参与会员筛选归属门店
                                arrScopeStore = [];
                                arrScopeStore.push(orgCode);
                            } else if (orgType == 2) {//组织架构等级为2（部门），如客服部、商品中心、运营中心等
                                _form.findField("model.activityScope").setValue(orgCode);
                                _form.findField("model.activityScopeName").setValue(orgName);
                                Ext.getCmp("indiDownLoadBnt").enable();
                                Ext.getCmp("indiPartInUploadBnt").enable();
                                Ext.getCmp("indiExportBnt").enable();
                                Ext.getCmp("parExportBnt").enable();
                                arrScopeStore = [];
                                arrScopeStore.push(orgCode);
                            }
                            Ext.getCmp('launchdept').setValue(orgName);
                            _form.findField("model.orgCode").setValue(orgCode);
                            if (Ext.getCmp('launchdept').getValue() != null || Ext.getCmp('launchdept').getValue() != "") {
                                Ext.getCmp("indiPartInBnt").enable();
                                Ext.getCmp("fraPartInBnt").enable();
                                Ext.getCmp("parPartInBnt").enable();
                            }
                            _indistore.removeAll();
                            _frastore.removeAll();
                            _parstore.removeAll();
                        }
                    }
                }
            });
        },
        viewAfter : function(grid, selectids, win) {
            bntType = "view";
            vp.editWin.showTabItem("posOrder");
            vp.editWin.showTabItem("saleOrder");
            vp.editWin.showTabItem("audit");
            vp.editWin.showTabItem("judgeAct");
            Ext.getCmp("indiDeleteBtn").disable();
            if (selectids[0].json.launchorCode == s_userCode) {
                Ext.getCmp("changeBtn").enable();
                Ext.getCmp("changeBtn2").enable();
                Ext.getCmp("actualcost").enable();
            }
            ;
            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.disable();
            // 发起部门回填
            Ext.Ajax.request({
                url : path + "/IndividCust_getOrgByUserId.action",
                success : function(response) {
                    var json = Ext.decode(response.responseText);
                    if (json.success) {
                        if (json.data.length > 0) {
                            var orgType = json.data[0].type;
                            if (orgType == 2) {
                                // Ext.getCmp("changeBtn").disable();
                                // Ext.getCmp("changeBtn2").disable();
                                Ext.getCmp("indiExportBnt").enable();
                                Ext.getCmp("fraExportBnt").enable();
                                Ext.getCmp("parExportBnt").enable();
                            } else if (orgType == 5 || orgType == 3 || orgType == 4) {
                                // Ext.getCmp("changeBtn").disable();
                                // Ext.getCmp("changeBtn2").disable();
                                Ext.getCmp("indiExportBnt").disable();
                                Ext.getCmp("fraExportBnt").disable();
                                Ext.getCmp("parExportBnt").disable();
                            }
                        }
                    }
                }
            });
        },
        editAfter : function(grid, selectids, win) {
            bntType = "edit";
            var tbar2 = vp.editWin.formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            tbar2.disable();
            vp.editWin.hideTabItem("posOrder");
            vp.editWin.hideTabItem("saleOrder");
            vp.editWin.hideTabItem("audit");
            vp.editWin.hideTabItem("judgeAct");
            vp.editWin.setActiveTab([ "posOrder", "saleOrder", "audit", "judgeAct" ]);
            Ext.getCmp("indiDeleteBtn").disable();
            Ext.getCmp("indiAdd").disable();
            Ext.getCmp("changeBtn").disable();
            Ext.getCmp("changeBtn2").disable();
            Ext.getCmp("actualcost").disable();
            Ext.Ajax.request({
                url : path + "/IndividCust_getOrgByUserId.action",
                success : function(response) {
                    var json = Ext.decode(response.responseText);
                    if (json.success) {
                        if (json.data.length > 0) {
                            var orgType = json.data[0].type;
                            if (orgType == 2) {
                                Ext.getCmp("indiExportBnt").enable();
                                Ext.getCmp("fraExportBnt").enable();
                                Ext.getCmp("parExportBnt").enable();
                            } else if (orgType == 5 || orgType == 3 || orgType == 4) {
                                Ext.getCmp("indiExportBnt").disable();
                                Ext.getCmp("fraExportBnt").disable();
                                Ext.getCmp("parExportBnt").disable();

                            }
                        }
                    }
                }
            });
            vp.editWin.formPanel.getForm().findField("model.isCreateCard").disabled = true;
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 85,
            height : 300,
            columnWidth : 1,
            items : editFormItems,
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();
                var _activityType = _form.findField('model.activityType').getValue();
                var _activityForm = _form.findField('model.activityForm').getValue();
                if (_activityType != "") {
                    var k = Q.Activity.selStores.actType.find('value', _activityType);
                    Q.Activity.selStores.formAct.baseParams = {
                        "parentId" : Q.Activity.selStores.actType.getAt(k).get('dictId')
                    };
                    Q.Activity.selStores.formAct.load({
                        callback : function(record, options, success) {
                            _form.findField("model.activityForm").setValue(_activityForm);

                        }
                    });
                }

                var _type = _form.findField("model.org.type").getValue();
                var _code = _form.findField("model.org.orgCode").getValue();
                if (bntType != "add") {
                    _form.findField("model.activityType").readOnly = true;
                    _form.findField("model.template").readOnly = true;
                }
                checkShowTab(_type, _code);
                var select = formPanel.getForm().findField("model.activityForm").getValue();
                if (select == "2" || select == "3") {
                    Ext.getCmp("lowerLimitid").getEl().dom.readOnly = false;
                    Ext.getCmp("preferentialid").getEl().dom.readOnly = false;
                } else {
                    Ext.getCmp("lowerLimitid").getEl().dom.readOnly = true;
                    Ext.getCmp("preferentialid").getEl().dom.readOnly = true;
                }
                if (select == "28") {
                    Ext.getCmp("integralReward").getEl().dom.readOnly = false;
                } else {
                    Ext.getCmp("integralReward").getEl().dom.readOnly = true;
                }
                arrScopeStore = [];
                arrScopeStore.push(_form.findField('model.activityScope').getValue());

            }
        },
        submitBefore : function(formPanel) {
            var integralReward = formPanel.getForm().findField("model.integralReward").getValue();
            var activityForm = formPanel.getForm().findField("model.activityForm").getValue();
            var seriesName = formPanel.getForm().findField("model.seriesName").getValue();
            var varietyName = formPanel.getForm().findField("model.varietyName").getValue();
            var preferential = formPanel.getForm().findField("model.preferential").getValue();
            var lowerLimit = formPanel.getForm().findField("model.lowerLimit").getValue();
            var jewelleryAmountL = formPanel.getForm().findField("model.jewelleryAmountL").getValue();
            var jewelleryAmountG = formPanel.getForm().findField("model.jewelleryAmountG").getValue();
            var content = formPanel.getForm().findField("model.content").getValue();
            if (jewelleryAmountG != "" && jewelleryAmountL != "" && jewelleryAmountG > jewelleryAmountL) {
                Q.tips("<font color='red'>请检查累计珠宝额大小关系！</font>");
                return false;
            }
            if (activityForm == "28" && integralReward == "" && integralReward == 0) {
                Q.tips("<font color='red'>活动形式为积分，请输入积分奖励	且不能为0！</font>");
                return false;
            }
            if (activityForm == "2") {
                if (seriesName == "" && varietyName == "" || seriesName != "" && varietyName != "") {
                    Q.tips("<font color='red'>活动形式为满减，适用品类/适用系列需选择其中一种！</font>");
                    return false;
                }
                if (lowerLimit == "" && lowerLimit == 0) {
                    Q.tips("<font color='red'>活动形式为满减，请输入购买下限值且不能为0！</font>");
                    return false;
                }
                if (preferential == "") {
                    Q.tips("<font color='red'>活动形式为满减，请输入优惠金额！</font>");
                    return false;
                }
                if (preferential > lowerLimit) {
                    Q.tips("<font color='red'>优惠金额不能大于下限值！</font>");
                    return false;
                }

                if (content == "") {
                    Q.tips("<font color='red'>活动形式为满减，请输入活动内容！</font>");
                    return false;
                }
            }
            if (activityForm == "3") {
                if (seriesName == "" && varietyName == "" || seriesName != "" && varietyName != "") {
                    Q.tips("<font color='red'>活动形式为满赠，适用品类/适用系列需选择其中一种！</font>");
                    return false;
                }
                if (lowerLimit == "" && lowerLimit == 0) {
                    Q.tips("<font color='red'>活动形式为满赠，请输入购买下限值且不能为0！</font>");
                    return false;
                }
                if (preferential == "") {
                    Q.tips("<font color='red'>活动形式为满赠，请输入优惠金额！</font>");
                    return false;
                }
                if (preferential > lowerLimit) {
                    Q.tips("<font color='red'>优惠金额不能大于下限值！</font>");
                    return false;
                }
                if (content == "") {
                    Q.tips("<font color='red'>活动形式为满赠，请输入活动内容！</font>");
                    return false;
                }
                var _actGive = vp.editWin.getCompByTabClassName('actGive').getStore().data.items;
                if (_actGive.length == 0) {
                    Q.tips(Q.color(("活动形式为满赠，赠品信息必须添加明细！"), "red"));
                    return false;
                }
            } else {
                if (seriesName == "" && varietyName == "" || seriesName != "" && varietyName != "") {
                    Q.tips("<font color='red'>适用品类/适用系列需选择其中一种！</font>");
                    return false;
                }
                if (content == "") {
                    Q.tips("<font color='red'>请输入活动内容！</font>");
                    return false;
                }
            }
            var _expectCostItems = vp.editWin.getCompByTabClassName('expectCost').getStore().data.items;
            var flag = Q.each(_expectCostItems, function() {
                if (!this.data.actItems) {
                    Q.tips(Q.color(("费用预估中“项目”不可为空！"), "red"));
                    return false;
                }
                if (!this.data.actSize) {
                    Q.tips(Q.color(("费用预估中“规格”不可为空！"), "red"));
                    return false;
                }
                if (!this.data.actPrice) {
                    Q.tips(Q.color(("费用预估中“单价”不可为空！"), "red"));
                    return false;
                }
                if (!this.data.actCount) {
                    Q.tips(Q.color(("费用预估中“数量”不可为空！"), "red"));
                    return false;
                }
                if (!this.data.budget) {
                    Q.tips(Q.color(("费用预估中“预算”不可为空！"), "red"));
                    return false;
                }
            });
            return flag;
        },
        centerTab : {
            items : [ gridDtlIndi, gridDtlFra, gridDtlPar, gridDtlExpectCost, gridDtlgive, gridDtlSale1, gridDtlSale, formdtl2, gridDtlAudit ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 500,
        height : 210,
        form : {
            labelWidth : 60,
            columnWidth : 0.5,
            items : [ {
                fieldLabel : '活动单号',
                name : "filter_EQ_activityNo",
                xtype : "textfield"
            }, {
                fieldLabel : '活动类型',
                hiddenName : "filter_EQ_activityType",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.7%",
                clearable : true,
                store : Q.Activity.selStores.actType
            }, {
                fieldLabel : '活动主题',
                name : "filter_LIKE_activityTheme",
                xtype : "textfield"
            }, {
                fieldLabel : '活动状态',
                hiddenName : "filter_EQ_status",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.7%",
                clearable : true,
                store : Q.Activity.selStores.actStatus
            }, {
                fieldLabel : '发起人员',
                name : "filter_LIKE_launchor",
                xtype : "textfield"
            }, {
                fieldLabel : '发起部门',
                name : "filter_LIKE_org_name",
                xtype : "textfield"
            }, {
                fieldLabel : "开始日期",
                name : 'filter_GE_beginTime',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_beginTime = _form.findField('filter_GE_beginTime').getValue();
                        var filter_LE_endTime = _form.findField("filter_LE_endTime").getValue();
                        if (filter_GE_beginTime && filter_LE_endTime) {
                            if (filter_GE_beginTime > filter_LE_endTime) {
                                Q.warning("开始日期不能大于结束日期！");
                                _form.findField('filter_GE_beginTime').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "结束日期",
                name : 'filter_LE_endTime',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_beginTime = _form.findField('filter_GE_beginTime').getValue();
                        var filter_LE_endTime = _form.findField("filter_LE_endTime").getValue();
                        if (filter_GE_beginTime && filter_LE_endTime) {
                            if (filter_GE_beginTime > filter_LE_endTime) {
                                Q.warning("结束日期不能小于开始日期！");
                                _form.findField('filter_LE_endTime').setValue("");
                            }
                        }
                    }
                }
            } ]
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};