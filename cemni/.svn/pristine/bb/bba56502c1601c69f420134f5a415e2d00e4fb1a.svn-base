/**
 * 短信模版管理
 * 
 * @author：yuhb
 * @date：2016年12月21日 上午11:57:54
 */
Ext.ns("Q.sms");
var dealUrl = path + '/Sms';
var jsArr = [ path + "/js/sms/Q.sms.objcondWin.js", path + "/js/common/Q.excel.uploadWin.js", path + "/js/sms/Q.sms.objWin.js", path + "/js/actment/activity/Q.actment.chooseActWin.js" ];
Q.loadJs(jsArr);

Q.sms.selStores = {
    // "查询"短信类型
    smsTypeStore : Q.common.selDict(8000, false),

    // “新增、编辑、查看”短信类型
    selSmsTypeStore : new Ext.data.JsonStore({
        url : dealUrl + "_selSmsTypeStore.action",
        fields : [ "name", "value" ],
        autoLoad : false
    }),

    // 短信发送-循环发送-类型
    cycleTypeStore : new Ext.data.JsonStore({
        url : path + "/Dict_selDict.action",
        fields : [ "name", "value" ],
        autoLoad : false
    }),

    // 可选变量
    selParamVar : new Ext.data.JsonStore({
        url : dealUrl + "_selParamVar.action",
        fields : [ "name", "paramVarId" ],
        autoLoad : false
    }),

    // 发送条件-比较字段
    selTypeCond : new Ext.data.JsonStore({
        url : dealUrl + "_selTypeCond.action",
        fields : [ "compColumnName", "typeCondId", "compColumVal" ],
        autoLoad : false
    }),

    // 发送条件-条件
    compSymbolStore : new Ext.data.ArrayStore({
        fields : [ 'value', 'text' ],
        data : [ [ '>', '>' ], [ '>=', '>=' ], [ '=', '=' ], [ '<=', '<=' ], [ '<', '<' ] ]
    }),

    // 发送条件-比较值
    selCompVal : new Ext.data.JsonStore({
        url : path + "/Dict_selDict.action",
        fields : [ "name", "value" ],
        autoLoad : false
    })
};

// 加载短信类型-查询下拉框：过滤“短信验证码”
Q.sms.selStores.smsTypeStore.load({
    callback : function(_store) {
        for (var i = 0; i < _store.length; i++) {
            var r = _store[i];
            if (r.get("value") == 13) {
                Q.sms.selStores.smsTypeStore.remove(r);
                break;
            }
        }
    }
});

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var checkShowTab = function(type) {
    // 1.生日短信：条件
    // 2.节日问候n：条件
    // 3.客情维护n：条件（月初、月中、月底）
    // 5.入会提醒：
    // 6.消费感谢：
    // 7.保养短信n：（3个月、6个月、1年）
    // 8.等级变化：
    // 9.积分变化：
    // 10.大件销售n：条件、对象
    // 11.客商生日：
    // 12.营销短信n：对象

    // “选择活动”输入框的控制
    if (type == 12) {
        Ext.getCmp("activityTheme").setValue(Ext.getCmp("activityTheme2").getValue()).enable();
    } else {
        Ext.getCmp("activityTheme").setValue("").disable();
    }

    // 发送时间-控制
    if (bntType == "view") {
        Ext.getCmp("sendType_2").disable();
        Ext.getCmp("sendType_3").disable();
        Ext.getCmp("timedSendStr").disable();
        Ext.getCmp("cycleType").disable();

        Ext.getCmp("activityTheme").disable();
    } else {
        // 可选变量
        Q.sms.selStores.selParamVar.removeAll();
        Q.sms.selStores.selParamVar.load({
            params : {
                "model.type" : type
            }
        });

        if (type == 1 || type == 5 || type == 6 || type == 8 || type == 9 || type == 10 || type == 11) {
            Ext.getCmp("sendType_2").disable();
            Ext.getCmp("sendType_3").disable();
            Ext.getCmp("timedSendStr").disable();
            Ext.getCmp("cycleType").disable();
        } else if (type == 2) {
            Ext.getCmp("sendType_2").enable().setValue(true);
            Ext.getCmp("sendType_3").disable();
            Ext.getCmp("timedSendStr").enable();
            Ext.getCmp("timedSendStr").allowBlank = false;
            Ext.getCmp("cycleType").disable();
            Ext.getCmp("cycleType").allowBlank = true;
        } else if (type == 3 || type == 7) {
            Ext.getCmp("sendType_2").disable();
            Ext.getCmp("sendType_3").enable().setValue(true);// 选中
            Ext.getCmp("timedSendStr").disable();
            Ext.getCmp("cycleType").enable();
            Ext.getCmp("cycleType").allowBlank = false;
        } else {
            if (Ext.getCmp("sendType_3").checked) {
                Ext.getCmp("sendType_2").enable();
                Ext.getCmp("sendType_3").enable().setValue(true);
                Ext.getCmp("timedSendStr").disable();
                Ext.getCmp("timedSendStr").allowBlank = true;
                Ext.getCmp("cycleType").enable();
                Ext.getCmp("cycleType").allowBlank = false;
            } else {// 默认“定时发送”
                Ext.getCmp("sendType_2").enable().setValue(true);
                Ext.getCmp("sendType_3").enable();
                Ext.getCmp("timedSendStr").enable();
                Ext.getCmp("timedSendStr").allowBlank = false;
                Ext.getCmp("cycleType").disable();
                Ext.getCmp("cycleType").allowBlank = true;
            }
        }
    }

    // tab的“显示、隐藏”
    if (type == 1 || type == 2 || type == 3) {
        vp.editWin.showTabItem("objCondtionList");
        vp.editWin.hideTabItem("objCustList");
        vp.editWin.setActiveTab([ "objCustList" ]);
    } else if (type == 10) {
        vp.editWin.showTabItem("objCondtionList");
        vp.editWin.showTabItem("objCustList");
        vp.editWin.setActiveTab([]);
    } else if (type == 12) {
        vp.editWin.hideTabItem("objCondtionList");
        vp.editWin.setActiveTab([ "objCondtionList" ]);
        vp.editWin.showTabItem("objCustList");
    } else {
        vp.editWin.hideTabItem("objCondtionList");
        vp.editWin.hideTabItem("objCustList");
        vp.editWin.setActiveTab([ "objCondtionList", "objCustList" ]);
    }

    // 查询条件
    if (!Ext.getCmp("objCondtionList").hidden) {
        Q.sms.selStores.selTypeCond.removeAll();
        Q.sms.selStores.selTypeCond.load({
            params : {
                "model.type" : type
            }
        });
    }
};

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////
// 表单
Q.sms.editFormItems = [ {
    xtype : "fieldset",
    layout : "column",
    style : "padding: 0px;",
    border : false,
    anchor : "0",
    defaults : {
        layout : "form",
        border : false,
        labelAlign : "right",
        labelWidth : 60
    },
    items : [ {
        items : [ {
            name : 'model.smsId',
            xtype : 'hidden'
        }, {
            name : 'model.activityId',
            id : "activityId",
            xtype : 'hidden'
        }, {
            name : 'model.activityTheme',
            id : "activityTheme2",
            xtype : 'hidden'
        } ]
    }, {
        columnWidth : 0.37,
        items : [ {
            fieldLabel : "短信类型<font color='red'>*</font>",
            id : "type",
            hiddenName : "model.type",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.sms.selStores.selSmsTypeStore,
            allowBlank : false,
            width : 152,
            listeners : {
                select : function(c) {
                    var v = c.getValue();
                    var _win = vp.editWin;
                    var _form = _win.formPanel.getForm();
                    _form.reset();
                    this.setValue(v);

                    Ext.getCmp("objCondtionList").getStore().removeAll();
                    Ext.getCmp("objCustList").getStore().removeAll();
                    checkShowTab(v);

                    // 循环发送时间
                    var sms_cycle = 8002;// 默认
                    if (v == 3) {// 客情维护
                        sms_cycle = 8003;
                    } else if (v == 7) {// 保养短信
                        sms_cycle = 8005;
                    }
                    Q.sms.selStores.cycleTypeStore.removeAll();
                    Q.sms.selStores.cycleTypeStore.load({
                        params : {
                            "dictCode" : sms_cycle
                        }
                    });
                }
            }
        }, {
            fieldLabel : "短信名称<font color='red'>*</font>",
            name : "model.name",
            xtype : "textfield",
            allowBlank : false,
            maxLength : 30,
            width : 150
        } ]
    }, {
        columnWidth : 0.28,
        items : [ {
            fieldLabel : "是否有效",
            hiddenName : "model.isValid",
            xtype : "uxcombo",
            store : Q.common.yesOrNotStore,
            value : 0,
            width : 100
        }, {
            fieldLabel : "可选变量",
            xtype : "uxcombo",
            valueField : "paramVarId",
            displayField : "name",
            store : Q.sms.selStores.selParamVar,
            width : 100,
            listeners : {
                select : function(c) {
                    Ext.getCmp("content").setValue(Ext.getCmp("content").getValue() + "#" + c.getRawValue() + "#");
                }
            }
        } ]
    }, {
        columnWidth : 0.35,
        items : [ {
            xtype : "fieldset",
            layout : "column",
            border : false,
            columnWidth : 1,
            style : "padding: 0px;text-align: right;",
            defaults : {
                layout : "form",
                border : false,
            },
            items : [ {
                labelWidth : 60,
                columnWidth : 0.57,
                items : [ {
                    fieldLabel : "发送时间",
                    id : "sendType_2",
                    name : "model.sendType",
                    xtype : "radio",
                    inputValue : 2,
                    boxLabel : "定时发送",
                    listeners : {
                        check : function(radio, isCheck) {
                            if (isCheck) {
                                Ext.getCmp("cycleType").allowBlank = true;
                                Ext.getCmp("cycleType").setValue("");
                                Ext.getCmp("cycleType").disable();
                                Ext.getCmp("timedSendStr").allowBlank = false;
                                Ext.getCmp("timedSendStr").enable();
                            } else {
                                Ext.getCmp("cycleType").allowBlank = false;
                                Ext.getCmp("cycleType").enable();
                                Ext.getCmp("timedSendStr").allowBlank = true;
                                Ext.getCmp("timedSendStr").setValue("");
                                Ext.getCmp("timedSendStr").disable();
                            }
                        }
                    }
                }, {
                    id : "sendType_3",
                    name : "model.sendType",
                    xtype : "radio",
                    inputValue : 3,
                    boxLabel : "循环发送"
                } ]
            }, {
                labelWidth : 1,
                columnWidth : 0.43,
                items : [ {
                    id : 'timedSendStr',
                    name : 'model.timedSendStr',
                    xtype : "datefield",
                    format : 'Y-m-d',
                    editable : false,
                    anchor : "100%"
                }, {
                    id : "cycleType",
                    hiddenName : "model.cycleType",
                    xtype : "uxcombo",
                    valueField : "value",
                    displayField : "name",
                    store : Q.sms.selStores.cycleTypeStore,
                    anchor : "102%"
                } ]
            } ]
        } ]
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : "短信内容<font color='red'>*</font>",
            id : "content",
            name : "model.content",
            xtype : "textarea",
            allowBlank : false,
            maxLength : 200,
            anchor : "99.8%"
        }
    } ]
} ];

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var conditionTab = {
    id : "objCondtionList",
    tabTitle : "发送条件",
    xtype : 'uxeditorgrid',
    foreignKey : "sms_smsId",
    tabClassName : "objCondtionList",
    allowEmpty : false,
    pageSize : 0,
    cm : {
        defaultSortable : false,
        pageSize : 0,
        columns : [ {
            dataIndex : 'objConditionId',
            hidden : true
        }, {
            dataIndex : 'typeCond.typeCondId',
            hidden : true
        }, {
            dataIndex : 'compVal',
            hidden : true
        }, {
            header : "比较字段",
            dataIndex : "typeCond.compColumnName"
        }, {
            header : "条件",
            dataIndex : "compSymbol"
        }, {
            header : "比较值",
            dataIndex : "compValName"
        } ]
    },
    store : {
        idProperty : "objConditionId",
        url : dealUrl + "_qryObjCond.action",
        sort : "objConditionId",
        dir : "desc",
        autoLoad : false
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.sms.objcondWin();
            _win.on("submit", function(r) {
                _store.add(r);
            });
            _win.show();
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]
};

// 发送对象
var objCustTab = {
    id : "objCustList",
    tabTitle : "发送对象",
    xtype : 'uxeditorgrid',
    foreignKey : "sms_smsId",
    tabClassName : "objCustList",
    allowEmpty : false,
    pageSize : 0,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "individCustId",
            hidden : true
        }, {
            header : "会员卡号",
            dataIndex : "cardNo",
        }, {
            header : "客户名称",
            dataIndex : "name",
        }, {
            header : "手机号码",
            dataIndex : "mobile"
        } ]
    },
    store : {
        idProperty : "individCustId",
        url : dealUrl + "_qryObjCust.action",
        autoLoad : false
    },
    tbar : [ {
        text : "模版下载",
        iconCls : "icon-excel",
        handler : function() {
            window.open(path + "/Common_downLoadExcelTpl.action?fileName=SmsSendIndividCust.xlsx");
        }
    }, {
        text : "导入",
        iconCls : "icon-upload",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.excel.uploadWin({
                url : dealUrl + "_uploadExcel.action"
            });
            _win.on("upload", function(action) {
                Q.each(action.result.dataList, function() {
                    var _individCustId = this.individCustId;
                    // 过滤掉列表中已存在的数据
                    var isExists = false;
                    var cnt = _store.getCount();
                    for (var i = 0; i < cnt; i++) {
                        if (_individCustId == _store.getAt(i).data.individCustId) {
                            isExists = true;
                        }
                    }
                    if (!isExists) {
                        var r = new Ext.data.Record({
                            individCustId : _individCustId,
                            cardNo : this.cardNo,
                            name : this.name,
                            mobile : this.mobile
                        });
                        _store.add(r);
                    }
                });
            });
            _win.show();
        }
    }, {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.sms.objWin();
            _win.on("submit", function(data) {
                Q.each(data, function() {
                    var _individCustId = this.individCustId;
                    // 过滤掉列表中已存在的数据
                    var isExists = false;
                    var cnt = _store.getCount();
                    for (var i = 0; i < cnt; i++) {
                        if (_individCustId == _store.getAt(i).data.individCustId) {
                            isExists = true;
                        }
                    }
                    if (!isExists) {
                        var r = new Ext.data.Record({
                            individCustId : _individCustId,
                            cardNo : this.cardNo,
                            name : this.name,
                            mobile : this.mobile
                        });
                        _store.add(r);
                    }
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
    }, "->", {
        xtype : "textfield",
        emptyText : "选择活动",
        id : "activityTheme",
        readOnly : true,
        width : 150,
        listeners : {
            render : function(p) {
                if (bntType == 'view') {
                    return;
                }
                p.getEl().on('click', function(p) {
                    var _win = new Q.actment.chooseActWin();
                    _win.on("submit", function(r) {
                        Ext.getCmp('activityId').setValue(r.data.activityId);
                        Ext.getCmp('activityTheme').setValue(r.data.activityTheme);
                    });
                    _win.show();
                });
            }
        }
    } ]
};

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var bntType;
var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : "短信模版",
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : [],
        hideSubTab : true,
        subTab : [],
        listEditStateFn : [],
        column : [ {
            header : "模版ID",
            dataIndex : "smsId",
            width : 10
        }, {
            header : "短信类型",
            dataIndex : "typeName",
            width : 10
        }, {
            header : "短信名称",
            dataIndex : "name",
            width : 35
        }, {
            header : "是否有效",
            dataIndex : "isValid",
            renderer : Q.common.yesOrNotRenderer,
            width : 10
        }, {
            header : "发送时间",
            dataIndex : "sendTimeStr",
            width : 20
        }, {
            header : "创建时间",
            dataIndex : "cdate",
            renderer : Q.common.timeRenderer,
            width : 15
        } ],
        store : {
            idProperty : "smsId",
            url : dealUrl + '_getJson.action',
            sort : "smsId",
            dir : "desc"
        },
        addAfter : function(grid, selectids, win) {
            bntType = "add";
            Q.sms.selStores.selSmsTypeStore.load({
                params : {
                    "bntType" : bntType
                }
            });
            vp.editWin.hideTabItem("objCondtionList");
            vp.editWin.hideTabItem("objCustList");
            vp.editWin.setActiveTab([ "objCondtionList", "objCustList" ]);
        },
        editAfter : function(grid, selectids, win) {
            bntType = "edit";
            Q.sms.selStores.selSmsTypeStore.load({
                params : {
                    "bntType" : bntType
                }
            });
        },
        viewAfter : function(grid, selectids, win) {
            bntType = "view";
            Q.sms.selStores.selSmsTypeStore.load({
                params : {
                    "bntType" : bntType
                }
            });
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : false,
        width : 700,
        height : 450,
        form : {
            labelWidth : 100,
            items : Q.sms.editFormItems,
            columnWidth : 1,
            height : "1px;",// 去除该属性会造成页面高度撑不起来（不能去除引号使用纯数字）
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();
                var _type = _form.findField("model.type").getValue();

                // 循环发送时间
                if (_type == 1 || _type == 5 || _type == 6 || _type == 8 || _type == 9 || _type == 10 || _type == 11) {
                    // TODO
                } else {
                    var sms_cycle = 8002;// 默认
                    if (_type == 3) {// 客情维护
                        sms_cycle = 8003;
                    } else if (_type == 7) {// 保养短信
                        sms_cycle = 8005;
                    }
                    Q.sms.selStores.cycleTypeStore.removeAll();
                    Q.sms.selStores.cycleTypeStore.load({
                        params : {
                            "dictCode" : sms_cycle
                        },
                        callback : function() {
                            _form.findField("model.cycleType").setValue(Ext.getCmp("cycleType").getValue());
                        }
                    });
                }

                // 短信类型不能修改
                _form.findField("model.type").readOnly = true;
                checkShowTab(_type);
            }
        },
        centerTab : {
            items : [ conditionTab, objCustTab ]
        },
        listeners : {
            show : function() {
                this.buttons[1].hide();
            }
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 300,
        height : 180,
        form : {
            labelWidth : 70,
            columnWidth : 1,
            items : [ {
                fieldLabel : "短信类型",
                hiddenName : "filter_EQ_type",
                xtype : "uxcombo",
                clearable : true,
                valueField : "value",
                displayField : "name",
                anchor : "95.6%",
                store : Q.sms.selStores.smsTypeStore
            }, {
                fieldLabel : "是否有效",
                hiddenName : "filter_EQ_isValid",
                xtype : "uxcombo",
                anchor : "95.6%",
                clearable : true,
                store : Q.common.yesOrNotStore
            }, {
                fieldLabel : '短信名称',
                name : "filter_LIKE_name",
                xtype : "textfield"
            } ]
        }
    }
};