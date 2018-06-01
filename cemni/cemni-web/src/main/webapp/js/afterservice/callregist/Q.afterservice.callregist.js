/**
 * 客户拜访登记
 * 
 * @author：WangYuanJun
 */
Ext.ns('Q.afterservice');
var dealUrl = path + '/CallRegist';
var jsArr = [ path + "/js/archive/individcust/Q.archive.chooseIndiWin.js", path + "/js/archive/individcust/Q.archive.indiInfoWin.js", path + "/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js" ];
Q.loadJs(jsArr);

Q.afterservice.selStores = {
    // 拜访形式
    visitFormStore : Q.common.selDict(4300),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 会员等级
    lvStore : Q.common.selDict(3100),
    // 积分状态
    gradeztStore : Q.common.selDict(3101)
};

// 列表
var gridColumn = [ {
    header : "拜访单号",
    width : 130,
    dataIndex : "visitNo"
}, {
    header : "会员卡号",
    width : 100,
    dataIndex : "individCust",
    renderer : function(v) {
        if (!!v) {
            return v.cardNo;
        }
    }
}, {
    header : "客户名称",
    width : 90,
    dataIndex : "individCust",
    renderer : function(v) {
        if (!!v) {
            return v.name;
        }
    }
}, {
    header : "拜访人员",
    width : 90,
    dataIndex : "visitPerson",
}, {
    header : "拜访门店/部门",
    width : 160,
    dataIndex : "orgStoreName"
}, {
    header : "拜访日期",
    width : 120,
    dataIndex : "visitDate",
    renderer : Q.common.dateRenderer
}, {
    header : "填写人",
    width : 120,
    dataIndex : "writePerson",
}, {
    header : "填写日期",
    width : 120,
    dataIndex : "writeDate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    title : '基础信息',
    xtype : 'fieldset',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.callRegistId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.individCust.individCustId',
            xtype : "hidden"
        }
    }, {
        items : {
            id : 'storeId',
            name : 'model.store.storeId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.orgId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.store.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.org.name',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.storeNo',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.orgCode',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.posOrder.posId',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '拜访单号<font color="red">*</font>',
            name : 'model.visitNo',
            emptyText : '系统自动生成',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '拜访日期<font color="red">*</font>',
            xtype : "datefield",
            format : "Y-m-d",
            name : 'model.visitDate',
            value : new Date(),
            allowBlank : false,
            anchor : "85%",
            editable : false
        }
    }, {
        items : {
            fieldLabel : '拜访人员<font color="red">*</font>',
            xtype : "textfield",
            name : 'model.visitPerson',
            allowBlank : false,
            anchor : "85%",
            maxLength : 10
        }
    }, {
        items : {
            fieldLabel : '拜访形式<font color="red">*</font>',
            hiddenName : 'model.visitForm',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.afterservice.selStores.visitFormStore,
            allowBlank : false,
            anchor : "85.5%"
        }
    }, {
        items : {
            fieldLabel : '拜访费用<font color="red">*</font>',
            xtype : "numberfield",
            name : 'model.visitMoney',
            allowBlank : false,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '拜访门店<font color="red">*</font>',
            name : 'model.orgStoreName',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true,
            allowBlank : false,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.orgStoreName").disabled) {
                            return;
                        }
                        var _win = new Q.afterservice.chooseOrgStoreWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            var orgStoreName = r.json.orgStoreTypeName;
                            if (orgStoreName == '门店') {
                                _form.findField("model.store.storeId").setValue(json.orgStoreId);
                                _form.findField("model.org.orgId").setValue(null);
                                _form.findField("model.storeNo").setValue(json.orgStoreCode);
                                _form.findField("model.orgCode").setValue(null);
                            } else {
                                _form.findField("model.org.orgId").setValue(json.orgStoreId);
                                _form.findField("model.store.storeId").setValue(null);
                                _form.findField("model.orgCode").setValue(json.orgStoreCode);
                                _form.findField("model.storeNo").setValue(null);
                            }
                            _form.findField("model.orgStoreName").setValue(json.orgStoreName);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '拜访事由<font color="red">*</font>',
            xtype : "textarea",
            name : 'model.visitReason',
            allowBlank : false,
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    } ],
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '个人资料',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '会员卡号<font color="red">*</font>',
            name : 'model.individCust.cardNo',
            allowBlank : false,
            xtype : "textfield",
            anchor : "85%",
            readOnly : true,
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        if (_form.findField("model.individCust.cardNo").disabled) {
                            return;
                        }
                        var _win = new Q.archive.chooseIndiWin();
                        _win.on("submit", function(r) {
                            var json = r.json;
                            _form.findField("model.individCust.individCustId").setValue(json.individCustId);
                            _form.findField("model.individCust.cardNo").setValue(json.cardNo);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            id : 'seeMore',
            xtype : "button",
            anchor : "85%",
            html : "<a>查看更多...</a>",
            listeners : {
                click : function() {
                    var _individCustId = vp.editWin.formPanel.getForm().findField("model.individCust.individCustId").getValue();
                    if (!!_individCustId) {
                        var _win = new Q.archive.indiInfoWin();
                        _win.setWinValue(_individCustId);
                        _win.show();
                    }
                }
            }
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '',
            xtype : "label",
            anchor : "85%",
            hideLabel : true,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '出生地',
            name : 'model.birthPlace',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '工作单位',
            name : 'model.company',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '职位',
            name : 'model.job',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '喜欢服饰品牌',
            name : 'model.clothes',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '喜欢的话题',
            name : 'model.topic',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    }, {
        items : {
            fieldLabel : '车子型号',
            name : 'model.carNum',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 50
        }
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '客户与你',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        columnWidth : 1,
        items : {
            fieldLabel : '主要交谈内容',
            name : 'model.chatContent',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '访问结论',
            name : 'model.accessConclusion',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '建议维护',
            name : 'model.suggestMaintain',
            xtype : "textarea",
            height : 60,
            anchor : "94%",
            maxLength : 500
        }
    }, {
        items : {
            fieldLabel : '填写人',
            name : 'model.writePerson',
            xtype : "textfield",
            anchor : "85%",
            maxLength : 200
        }
    }, {
        items : {
            fieldLabel : '填写日期',
            name : 'model.writeDate',
            xtype : "datefield",
            format : "Y-m-d",
            anchor : "85%",
            maxValue : new Date(),
            editable : false
        }
    } ]
} ];

// 查询表单
var searchFormItems = [ {
    fieldLabel : '拜访单号',
    name : "filter_LIKE_visitNo",
    xtype : "textfield"
}, {
    fieldLabel : '拜访日期',
    name : "filter_EQ_visitDate",
    xtype : "datefield",
    format : "Y-m-d",
    editable : false
}, {
    fieldLabel : '会员卡号',
    name : "filter_LIKE_individCust_cardNo",
    xtype : "textfield"
}, {
    fieldLabel : '客户名称',
    name : "filter_LIKE_individCust_name",
    xtype : "textfield"
}, {
    fieldLabel : '手机号码',
    name : "filter_LIKE_individCust_mobile",
    xtype : "numberfield"
}, {
    fieldLabel : '拜访人员',
    name : "filter_LIKE_visitPerson",
    xtype : "textfield"
}, {
    fieldLabel : '拜访门店',
    name : "filter_LIKE_store_name_OR_LIKE_org_name",
    xtype : "textfield"
}, {
    fieldLabel : '填写人',
    name : "filter_LIKE_writePerson",
    xtype : "textfield"
}, {
    fieldLabel : '填写日期',
    name : "filter_EQ_writeDate",
    xtype : "datefield",
    format : "Y-m-d",
    editable : false
} ];

// ///////////////////////////////////////////////////////////细单/////////////////////////////////////////////////////

var gridDtl1 = {
    tabTitle : "家庭成员",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "individCustFamily",
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "familyRelId",
            disabled : true
        }, {
            header : "姓名",
            dataIndex : "name"
        }, {
            header : "关系",
            dataIndex : "relationship"
        }, {
            header : "出生日期",
            dataIndex : "birthday",
            renderer : Q.common.dateRenderer
        }, {
            header : "爱好",
            dataIndex : "hobby"
        }, {
            header : "联系电话",
            dataIndex : "tel"
        }, {
            header : "学历",
            dataIndex : "education"
        }, {
            header : "百日宴",
            dataIndex : "hundredDays",
            renderer : Q.common.dateRenderer
        }, {
            header : "是否有抚养权",
            dataIndex : "custody",
            renderer : function(v, m, r) {
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        }, {
            header : "子女教育",
            dataIndex : "kidEducation"
        }, {
            header : "子女爱好",
            dataIndex : "kidHobby"
        }, {
            header : "备注",
            dataIndex : "remark"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "familyRelId",
        url : path + "/IndividCust_getRel.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl2 = {
    tabTitle : "纪念日",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "anniversary",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "anniversaryId",
            disabled : true
        }, {
            header : "纪念日名称",
            dataIndex : "name"
        }, {
            header : "日期",
            dataIndex : "days",
            renderer : Q.common.dateRenderer
        }, {
            header : "备注",
            dataIndex : "remark"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "anniversaryId",
        url : path + "/IndividCust_getRel2.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl3 = {
    tabTitle : "价值贡献",
    xtype : 'formpanel',
    foreignKey : "callRegist_callRegistId",
    tabClassName : "contributeAlias",
    loadUrl : path + "/IndividCust_getValue4.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        layout : 'column',
        border : false,
        anchor : 0,
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 0.33,
            labelWidth : 120
        },
        items : [ {
            items : [ {
                fieldLabel : "累计消费珠宝折算额",
                name : "sumjewelDiscountAmount",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "会员等级",
                name : "lvName",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "忠粉勋章",
                name : "loyaltyMedal",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "累计消费额",
                name : "sumactualSaleAmount",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "消费次数",
                name : "posnum",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "消费额平均客单价",
                name : "avgsaleprice",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "折算额平均客单价",
                name : 'avgprice',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "消费件数",
                name : "posCnt",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "平均单件金额",
                name : 'avgSinglePrice',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "首次消费日期",
                name : "fristBuyTime",
                xtype : "datefield",
                format : 'Y-m-d H:i:s',
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "首次消费门店",
                name : "fristStoreName",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "首次消费所属加盟商",
                name : "firstFranchisee",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "末次消费日期",
                name : "lastBuyTime",
                xtype : "datefield",
                format : 'Y-m-d H:i:s',
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "末次消费门店",
                name : "lastStoreName",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "末次消费所属加盟商",
                name : "lastFranchisee",
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "活跃状态",
                name : 'activeName',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "当前可用积分",
                name : 'credit',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "已兑换积分",
                name : 'convertedCredits',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "积分状态",
                name : 'creditStatusName',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "新老会员",
                name : 'freshName',
                xtype : "textfield",
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "新老会员变更时间",
                name : 'freshChgTime',
                xtype : "datefield",
                format : 'Y-m-d H:i:s',
                anchor : "90%"
            } ]
        } ]
    } ]
};

var gridDtl4 = {
    tabTitle : "POS单",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "posOrder",
    pageSize : 0,
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "posId",
            hidden : true
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
            header : "NC卡号",
            dataIndex : "ncNo",
            width : 150
        }, {
            header : "手机号码",
            dataIndex : "mobile",
            width : 90
        }, {
            header : "当前等级",
            dataIndex : "lvName",
            width : 80
        }, {
            header : "免费调换次数",
            dataIndex : "exchangeChance",
            width : 90
        }, {
            header : "免费改款次数",
            dataIndex : "restructureChance",
            width : 90
        }, {
            header : "商品编码",
            dataIndex : "goodsNo",
            width : 100
        }, {
            header : "商品名称",
            dataIndex : "goodsName",
            width : 150
        }, {
            header : "商品条形码",
            dataIndex : "goodsBar",
            width : 130
        }, {
            header : "数量",
            dataIndex : "goodsCnt",
            width : 60
        }, {
            header : "折扣",
            dataIndex : "discount",
            width : 60
        }, {
            header : "单价",
            dataIndex : "goodsPrice",
            width : 80
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
            width : 250
        }, {
            header : "分数段",
            dataIndex : "scoreSegmentName",
            width : 60
        }, {
            header : "设计师款",
            dataIndex : "designerStyleName",
            width : 100
        }, {
            header : "存货分类",
            dataIndex : "stockTypeName",
            width : 250
        }, {
            header : "商品（大类）",
            dataIndex : "goodsClassHighestNo",
            width : 150
        }, {
            header : "系类分类",
            dataIndex : "seriesTypeName",
            width : 150
        }, {
            header : "单据类型",
            dataIndex : "billTypeName",
            width : 80
        }, {
            header : "是否赠品",
            dataIndex : "flargess",
            renderer : Q.common.yesOrNotRenderer,
            width : 70
        }, {
            header : "纪念日积分倍率",
            dataIndex : "anniversaryIntegral",
            width : 100
        }, {
            header : "纪念日积分活动",
            dataIndex : "anniversaryEvent",
            width : 250
        } ]
    },
    store : {
        idProperty : "posId",
        url : path + "/IndividCust_getRel4.action",
        sort : "posId",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl5 = {
    tabTitle : "积分变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "integralAdjHis",
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "integralAdjHisId",
            hidden : true
        }, {
            dataIndex : "individCustId",
            hidden : true
        }, {
            header : "会员卡号",
            dataIndex : "individCust.cardNo",
            width : 120
        }, {
            header : "客户名称",
            dataIndex : "individCust.name",
            width : 100
        }, {
            header : "积分来源",
            dataIndex : "creditOriginName",
            width : 100
        }, {
            header : "变更类型",
            dataIndex : "modTypeName",
            width : 100
        }, {
            header : "变更前可用积分",
            dataIndex : "creditBefore",
            width : 100
        }, {
            header : "变更后可用积分",
            dataIndex : "creditAfter",
            width : 100
        }, {
            header : "变更前积分状态",
            dataIndex : "creditStatusName",
            width : 100
        }, {
            header : "变更人",
            dataIndex : "muser",
            width : 100
        }, {
            header : "变更时间",
            dataIndex : "mdate",
            renderer : Q.common.timeRenderer,
            width : 150
        }, {
            header : "变更原因",
            dataIndex : "modReason",
            width : 300,
            renderer : function(v, c, r) {
                if (v) {
                    c.attr = "ext:qtip='" + v + "'";
                    return v;
                }
            }
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "integralAdjHisId",
        url : path + "/IndividCust_getRel9.action",
        sort : "integralAdjHisId",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl6 = {
    tabTitle : "等级变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "gradeAdjHis",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "gradeAdjHisId",
            hidden : true
        }, {
            dataIndex : "individCustId",
            hidden : true
        }, {
            header : "会员卡号",
            dataIndex : "individCust.cardNo",
            width : 10
        }, {
            header : "客户名称",
            dataIndex : "individCust.name",
            width : 10
        }, {
            header : "变更类型",
            dataIndex : "modTypeName",
            width : 10
        }, {
            header : "变更前等级",
            dataIndex : "lvBeforeName",
            width : 10
        }, {
            header : "变更后等级",
            dataIndex : "lvAfterName",
            width : 10
        }, {
            header : "变更人",
            dataIndex : "muser",
            width : 15
        }, {
            header : "变更时间",
            dataIndex : "mdate",
            renderer : Q.common.timeRenderer,
            width : 15
        }, {
            header : "变更原因",
            dataIndex : "modReason",
            width : 20,
            renderer : function(v, c, r) {
                if (v) {
                    c.attr = "ext:qtip='" + v + "'";
                    return v;
                }
            }
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "gradeAdjHisId",
        url : path + "/IndividCust_getRel10.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl7 = {
    tabTitle : "状态变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "activeStatus",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "activeStatusId",
            disabled : true
        }, {
            dataIndex : "_individCustId",
            disabled : true
        }, {
            header : "会员卡号",
            width : 10,
            dataIndex : "individCust.cardNo"
        }, {
            header : "客户名称",
            width : 10,
            dataIndex : "individCust.name"
        }, {
            header : "变更前状态",
            width : 10,
            dataIndex : "beforeStatusName"
        }, {
            header : "变更后状态",
            width : 10,
            dataIndex : "afterStatusName"
        }, {
            header : "变更人",
            width : 15,
            dataIndex : "muser"
        }, {
            header : "变更时间",
            width : 15,
            dataIndex : "mdate",
            renderer : Q.common.timeRenderer
        }, {
            header : "变更原因",
            width : 30,
            dataIndex : "reason",
            renderer : function(v, c, r) {
                if (v) {
                    c.attr = "ext:qtip='" + v + "'";
                    return v;
                }
            }
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "activeStatusId",
        url : path + "/IndividCust_getRel5.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl8 = {
    tabTitle : "权益单",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "RightMaint",
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "rightMaintId",
            disabled : true
        }, {
            header : "权益单号",
            dataIndex : "complaintNo"
        }, {
            header : "处理状态",
            dataIndex : "handleStateName",
        }, {
            header : "处理门店",
            dataIndex : "orgStoreName",
            width : 180
        }, {
            header : "购买日期",
            dataIndex : "posOrder",
            renderer : function(v) {
                if (!!v) {
                    return Q.common.dateRenderer(v.posbillDate);
                }
            },
        }, {
            header : "购买产品",
            dataIndex : "posOrder",
            renderer : function(v) {
                if (!!v) {
                    return v.goodsName;
                }
            }
        }, {
            header : "购买金额",
            dataIndex : "posOrder",
            renderer : function(v) {
                if (!!v) {
                    return v.actualSaleAmount;
                }
            }
        }, {
            header : "商品条码",
            dataIndex : "posOrder",
            renderer : function(v) {
                if (!!v) {
                    return v.goodsBar;
                }
            }
        }, {
            header : "处理日期",
            dataIndex : "handleDate",
            renderer : Q.common.dateRenderer
        }, {
            header : "投诉问题",
            dataIndex : "probExplain",
            width : 200
        }, {
            header : "问题类型",
            dataIndex : "probTypeName"
        }, {
            header : "投诉等级",
            dataIndex : "complaintLevelName"
        }, {
            header : "问题说明",
            dataIndex : "probExplain",
            width : 230
        }, {
            header : "客户建议",
            dataIndex : "customerAdvice",
            width : 230
        }, {
            header : "处理结果",
            dataIndex : "finalResult",
            width : 230
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "rightMaintId",
        url : path + "/IndividCust_getRightMain.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl9 = {
    tabTitle : "售后服务",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "afterServ",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "afterservId",
            disabled : true
        }, {
            dataIndex : "individCust.individCustId",
            disabled : true
        }, {
            header : "商品条码",
            dataIndex : "goodsBar"
        }, {
            header : "商品名称",
            dataIndex : "goodsName"
        }, {
            header : "服务项目",
            dataIndex : "servicePro"
        }, {
            header : "原号",
            dataIndex : "oldNo"
        }, {
            header : "改号",
            dataIndex : "newNo"
        }, {
            header : "服务开始时间",
            dataIndex : "startDay",
            renderer : Q.common.timeRenderer
        }, {
            header : "服务结束时间",
            dataIndex : "endDay",
            renderer : Q.common.timeRenderer
        }, {
            header : "服务门店",
            dataIndex : "storeName"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "afterservId",
        url : path + "/IndividCust_getRel6.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl11 = {
    tabTitle : "客户拜访",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "callRegist",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "callRegistId",
            disabled : true
        }, {
            header : "拜访单号",
            dataIndex : "visitNo"
        }, {
            header : "拜访日期",
            dataIndex : "visitDate",
            renderer : Q.common.dateRenderer
        }, {
            header : "拜访人员",
            dataIndex : "visitPerson",
        }, {
            header : "拜访形式",
            dataIndex : "visitFormName"
        }, {
            header : "拜访事由",
            dataIndex : "visitReason"
        }, {
            header : "拜访费用",
            dataIndex : "visitMoney"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "callRegistId",
        url : path + "/CallRegist_qryCallRegist.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl12 = {
    tabTitle : "咨询记录",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "BusiRegist",
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "busiRegistId",
            disabled : true
        }, {
            header : "登记单号",
            width : 120,
            dataIndex : "registerNo"
        }, {
            header : "登记日期",
            width : 100,
            dataIndex : "registerDate",
            renderer : Q.common.dateRenderer
        }, {
            header : "受理门店",
            dataIndex : "orgStoreName",
            width : 150
        }, {
            header : "受理人员",
            width : 100,
            dataIndex : "acceptPerson"
        }, {
            header : "业务类型",
            width : 100,
            dataIndex : "businessTypeName"
        }, {
            header : "处理日期",
            width : 100,
            dataIndex : "handleDate",
            renderer : Q.common.dateRenderer
        }, {
            header : "业务内容",
            dataIndex : "businessContent",
            width : 300,
        }, {
            header : "处理结果",
            dataIndex : "handleResult",
            width : 300,
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "busiRegistId",
        url : path + "/IndividCust_getBusiRegist.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl13 = {
    tabTitle : "短信维系",
    xtype : 'uxeditorgrid',
    foreignKey : "callRegist_callRegistId",
    tabClassName : "smslog",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "smsLogId",
            hidden : true
        }, {
            header : "短信类型",
            dataIndex : "typeName",
            width : 10
        }, {
            header : "发送时间",
            dataIndex : "cdate",
            renderer : Q.common.timeRenderer,
            width : 15
        }, {
            header : "短信内容",
            dataIndex : "reqContent",
            width : 75
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "smsLogId",
        url : path + "/IndividCust_getRel11.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl14 = {
    tabTitle : "电话回访",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_callRegist_callRegistId",
    tabClassName : "callRegist",
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "telVisitRecordId",
            disabled : true
        }, {
            header : "回访记录单号",
            dataIndex : "telVisitRecordNo",
            width : 15
        }, {
            header : "回访方式",
            dataIndex : "backfsName",
            width : 8
        }, {
            header : "回访类型",
            dataIndex : "backtypeName",
            width : 12
        }, {
            header : "是否已过期",
            dataIndex : "expiredtypeName",
            width : 10
        }, {
            header : "是否已回访",
            dataIndex : "backztName",
            width : 10
        }, {
            header : "回访人",
            dataIndex : "storeManagerName",
            width : 8
        }, {
            header : "回访日期",
            dataIndex : "backtime",
            renderer : Q.common.dateRenderer,
            width : 12
        }, {
            header : "回访门店",
            dataIndex : "storeName",
            width : 25
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "telVisitRecordId",
        url : path + "/UndoVisit_qryTelVisit.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl15 = {
    tabTitle : "参与活动",
    xtype : 'uxeditorgrid',
    foreignKey : "callRegist_callRegistId",
    pageSize : 0,
    cm : {
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "活动单号",
            dataIndex : "activityNo"
        }, {
            header : "活动类型",
            dataIndex : "activityTypeName",
        }, {
            header : "活动主题",
            dataIndex : "activityTheme"
        }, {
            header : "开始日期",
            dataIndex : "beginTime",
            renderer : Q.common.dateRenderer
        }, {
            header : "结束日期",
            dataIndex : "endTime",
            renderer : Q.common.dateRenderer
        }, {
            header : "是否参与",
            dataIndex : "isPartIn",
            renderer : Q.common.yesOrNotRenderer
        } ]
    },
    store : {
        idProperty : "activityNo",
        url : path + "/IndividCust_getRel16.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl20 = {
    tabTitle : "推荐明细",
    xtype : 'uxeditorgrid',
    foreignKey : "callRegist_callRegistId",
    tabClassName : "refInfo",
    pageSize : 0,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "客户名称",
            dataIndex : "name",
            width : 10
        }, {
            header : "会员卡号",
            dataIndex : "cardNo",
            width : 10
        }, {
            header : "首次消费时间",
            dataIndex : "fristBuyTime",
            width : 16
        }, {
            header : "末次消费时间",
            dataIndex : "lastBuyTime",
            width : 16
        }, {
            header : "累计消费金额",
            dataIndex : "actualSaleAmount",
            width : 10
        }, {
            header : "累计消费珠宝折算额",
            dataIndex : "jewerlyAmount",
            width : 18
        }, {
            header : "累计消费件数",
            dataIndex : "posCnt",
            width : 10
        }, {
            header : "累计消费次数",
            dataIndex : "posNoCnt",
            width : 10
        } ]
    },
    store : {
        url : path + "/IndividCust_qryRefInfo.action",
        autoLoad : false
    }
};

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '客户拜访登记单',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'callRegistId',
            url : dealUrl + '_getJson.action',
            sort : "callRegistId",
            dir : "desc"
        },
        listEditStateFn : [],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = true;
            Ext.getCmp('seeMore').disabled = false;
            Ext.getCmp('seeMore').removeClass('x-item-disabled');
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = true;
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.individCust.cardNo").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.orgStoreName").disabled = false;
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        maximized : true,
        form : {
            labelWidth : 80,
            items : editFormItems,
            columnWidth : 1,
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();
                if (!!_form.findField("model.store.storeId").getValue()) {
                    // 处理的是门店
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.store.name").getValue());
                } else {
                    _form.findField("model.orgStoreName").setValue(_form.findField("model.org.name").getValue());
                }
            }
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7, gridDtl8, gridDtl9, gridDtl11, gridDtl12, gridDtl13, gridDtl14, gridDtl15, gridDtl20 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 330,
        width : 300,
        form : {
            labelWidth : 70,
            items : searchFormItems,
            columnWidth : 1
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};