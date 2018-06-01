Ext.ns("Q.undovisit");
var dealUrl = path + '/UndoVisit';

Q.undovisit.selStores = {
    // 回访方式
    backfsStore : Q.common.selDict(2101),
    // 回访类型
    backTypeStore : Q.common.selDict(1114),
    // 满意度
    saticFactionStore : Q.common.selDict(2104),
    // 是否已回访
    backZtStore : Q.common.selDict(2105),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 积分状态
    gradeztStore : Q.common.selDict(3101),
    // 等级
    lvStore : Q.common.selDict(3100),
    // 任务类型
    taskTypeStore : Q.common.selDict(2107),
    // 优先回访记录单
    returnRecordStore : Q.common.selDict(2108),
    // 意向价位
    purposePriceStore : Q.common.selDict(1116),
    // 意向产品类型
    purposeProductStore : Q.common.selDict(1117),
    // 意向产品品类
    purposeCategoryStore : Q.common.selDict(1118)
};

// 初始化加载
var initWin = function(formPanel) {
    var _form = formPanel.getForm();
    if (bntType != "edit") {// 查看
        _form.findField("model.storeManagerName").disable();
        return;
    }
    _form.findField("model.storeManagerName").enable();

    var _saletype = _form.findField("model.saletype").getValue();
    if (_saletype == 1) {// 已消费
        _form.findField("model.notshopservice").disable();// 店面服务
        _form.findField("model.notkhtalk").disable();// 客户咨询
        _form.findField("model.notkhadvice").disable();// 客户建议
        _form.findField("model.notinfoknowed").disable();// 已告知讯息
        _form.findField("model.notprofessorknow").disable();// 专业知识
        _form.findField("model.notintentioncp").disable();// 意向产品
        _form.findField("model.notshopenvi").disable();// 购物环境
        _form.findField("model.notnewrecoment").disable();// 新品推荐
        _form.findField("model.notfeedadvice").disable();// 反馈意见
        _form.findField("model.notsaleremark").disable();// 备注

        _form.findField("model.shopservice").enable();// 店面服务
        _form.findField("model.ornamentwear").enable();// 饰品佩戴
        _form.findField("model.khadvice").enable();// 客户建议
        _form.findField("model.khtalk").enable();// 客户咨询
        _form.findField("model.professorknow").enable();// 专业知识
        _form.findField("model.infoknowed").enable();// 已告知讯息
        _form.findField("model.shopenvi").enable();// 购物环境
        _form.findField("model.parentgant").enable();// 赠品发放
        _form.findField("model.feedadvice").enable();// 反馈意见
        _form.findField("model.wearupdate").enable();// 佩戴后维护
        _form.findField("model.parentmanyi").enable();// 赠品满意度
        _form.findField("model.careupdate").enable();// 保养维修
        _form.findField("model.saleremark").enable();// 备注
    } else {// 未消费
        _form.findField("model.notshopservice").enable();// 店面服务
        _form.findField("model.notkhtalk").enable();// 客户咨询
        _form.findField("model.notkhadvice").enable();// 客户建议
        _form.findField("model.notinfoknowed").enable();// 已告知讯息
        _form.findField("model.notprofessorknow").enable();// 专业知识
        _form.findField("model.notintentioncp").enable();// 意向产品
        _form.findField("model.notshopenvi").enable();// 购物环境
        _form.findField("model.notnewrecoment").enable();// 新品推荐
        _form.findField("model.notfeedadvice").enable();// 反馈意见
        _form.findField("model.notsaleremark").enable();// 备注

        _form.findField("model.shopservice").disable();// 店面服务
        _form.findField("model.ornamentwear").disable();// 饰品佩戴
        _form.findField("model.khadvice").disable();// 客户建议
        _form.findField("model.khtalk").disable();// 客户咨询
        _form.findField("model.professorknow").disable();// 专业知识
        _form.findField("model.infoknowed").disable();// 已告知讯息
        _form.findField("model.shopenvi").disable();// 购物环境
        _form.findField("model.parentgant").disable();// 赠品发放
        _form.findField("model.feedadvice").disable();// 反馈意见
        _form.findField("model.wearupdate").disable();// 佩戴后维护
        _form.findField("model.parentmanyi").disable();// 赠品满意度
        _form.findField("model.careupdate").disable();// 保养维修
        _form.findField("model.saleremark").disable();// 备注
    }
};

// //////////////////////////////////////////////////////////////////////////////////////////////////////

// 列表
var gridColumn = [ {
    dataIndex : "telVisitRecordId",
    hidden : true
}, {
    dataIndex : "expiredtype",
    hidden : true
}, {
    dataIndex : "backzt",
    hidden : true
}, {
    header : "回访任务单号",
    width : 12,
    dataIndex : "telVisit.telVisitNo"
}, {
    header : "回访记录单号",
    width : 13,
    dataIndex : "telVisitRecordNo"
}, {
    header : "会员卡号",
    width : 10,
    dataIndex : "individCust.cardNo"
}, {
    header : "客户名称",
    width : 12,
    dataIndex : "individCust.name"
}, {
    header : "客户类型",
    width : 8,
    dataIndex : "individCust.typeName"
}, {
    header : "会员等级",
    width : 8,
    dataIndex : "individCust.lvName"
}, {
    header : "回访方式",
    width : 8,
    dataIndex : "backfsName"
}, {
    header : "回访类型",
    width : 8,
    dataIndex : "backtypeName"
}, {
    header : "任务类型",
    width : 8,
    dataIndex : "taskTypeName"
}, {
    header : "回访开始日期",
    width : 10,
    renderer : Q.common.dateRenderer,
    dataIndex : "startrq"
}, {
    header : "回访结束日期",
    width : 10,
    renderer : Q.common.dateRenderer,
    dataIndex : "endrq"
} ];

// 表单
var editFormItems = [ {
    columnWidth : 1,
    xtype : 'fieldset',
    anchor : "-3",
    title : '基本信息',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.telVisitRecordId',
            xtype : 'hidden'
        }
    }, {
        items : {
            name : 'model.saletype',
            xtype : 'hidden'
        }
    }, {
        items : {
            fieldLabel : "回访记录单号",
            name : 'model.telVisitRecordNo',
            xtype : 'textfield',
            allowBlank : false,
            disabled : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : "回访日期",
            name : 'model.backtime',
            value : new Date(),
            xtype : 'datefield',
            allowBlank : false,
            format : 'Y-m-d',
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '回访方式',
            hiddenName : 'model.backfs',
            anchor : "90.8%",
            xtype : 'uxcombo',
            allowBlank : false,
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.backfsStore
        }
    }, {
        items : {
            fieldLabel : "会员卡号",
            anchor : "90%",
            disabled : true,
            xtype : 'textfield',
            name : 'model.individCust.cardNo'
        }
    }, {
        items : {
            fieldLabel : '客户名称',
            anchor : "90%",
            disabled : true,
            xtype : 'textfield',
            name : 'model.individCust.name'
        }
    }, {
        items : {
            fieldLabel : '手机号码',
            anchor : "90%",
            disabled : true,
            xtype : 'textfield',
            name : 'model.individCust.mobile'
        }
    }, {
        items : {
            fieldLabel : "回访人<font color='red'>*</font>",
            anchor : "90%",
            allowBlank : false,
            xtype : 'textfield',
            name : 'model.storeManagerName'
        }
    }, {
        items : {
            fieldLabel : '是否已回访',
            xtype : 'uxcombo',
            name : 'model.backzt',
            store : Q.common.yesOrNotStore,
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '回访类型',
            xtype : 'uxcombo',
            name : 'model.backtype',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.backTypeStore,
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '回访开始日期',
            name : 'model.startrq',
            xtype : "datefield",
            format : 'Y-m-d',
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '回访结束日期',
            xtype : "datefield",
            format : 'Y-m-d',
            name : 'model.endrq',
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '任务类型',
            xtype : 'uxcombo',
            name : 'model.taskType',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.taskTypeStore,
            readOnly : true,
            anchor : "90%"
        }
    } ]
}, {
    xtype : 'fieldset',
    title : '意向产品',
    layout : 'column',
    anchor : "-3",
    columnWidth : 1,
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '意向价位',
            anchor : "91%",
            hiddenName : 'model.individCust.purposePrice',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.purposePriceStore,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '意向产品类型',
            anchor : "91%",
            hiddenName : 'model.individCust.purposeProduct',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.purposeProductStore,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '意向产品品类',
            anchor : "91%",
            hiddenName : 'model.individCust.purposeCategory',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.purposeCategoryStore,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '需求时间',
            name : 'model.individCust.purposeDay',
            xtype : "datefield",
            readOnly : true,
            format : 'Y-m-d',
            anchor : "91%"
        }
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '已消费客户',
    anchor : "-3",
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '店面服务',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.shopservice'
        }
    }, {
        items : {
            fieldLabel : '饰品佩戴',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.ornamentwear'
        }
    }, {
        items : {
            fieldLabel : '专业知识',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.professorknow'
        }
    }, {
        items : {
            fieldLabel : '赠品发放',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.parentgant'
        }
    }, {
        items : {
            fieldLabel : '赠品满意度',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.parentmanyi'
        }
    }, {
        items : {
            fieldLabel : '保养维修',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.careupdate'
        }
    }, {
        items : {
            fieldLabel : '佩戴后维护',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.wearupdate'
        }
    }, {
        items : {
            fieldLabel : '购物环境',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.shopenvi'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '客户建议',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.khadvice'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '客户咨询',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.khtalk'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '已告知讯息',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.infoknowed'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '反馈意见',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.feedadvice'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            anchor : "95.7%",
            xtype : 'textarea',
            name : 'model.saleremark'
        },
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    anchor : "-3",
    title : '未消费客户',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },

    items : [ {
        items : {
            fieldLabel : '店面服务',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.notshopservice'
        }
    }, {
        items : {
            fieldLabel : '专业知识',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.notprofessorknow'
        }
    }, {
        items : {
            fieldLabel : '购物环境',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.undovisit.selStores.saticFactionStore,
            hiddenName : 'model.notshopenvi'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '客户咨询',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notkhtalk'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '客户建议',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notkhadvice'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '已告知讯息',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notinfoknowed'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '意向产品',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notintentioncp'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '新品推荐',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notnewrecoment'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '反馈意见',
            anchor : "95.7%",
            xtype : 'textfield',
            name : 'model.notfeedadvice'
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            anchor : "95.7%",
            xtype : 'textarea',
            name : 'model.notsaleremark'
        },
    } ]
} ];

// ///////////////////////////////////////////////////////////细单/////////////////////////////////////////////////////

var gridDtl1 = {
    tabTitle : "家庭成员",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
    foreignKey : "individCust_recordList_telVisitRecordId",
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
            dataIndex : "registerDate",
            width : 100,
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
            dataIndex : "handleDate",
            width : 100,
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
    foreignKey : "recordList_telVisitRecordId",
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
    foreignKey : "telVisitRecordId",
    tabClassName : "telVisitRecord",
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
    foreignKey : "recordList_telVisitRecordId",
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
    foreignKey : "recordList_telVisitRecordId",
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

var bntType;
var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '待处理回访单',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : [ {
            name : 'backBnt',
            text : '回访',
            index : 2,
            build : power.back,
            iconCls : 'icon-edit',
            handler : function() {
                var sm = vp.grid.getSelectionModel().getSelected();
                if (Ext.isEmpty(sm)) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return false;
                }

                bntType = "edit";
                var edit = vp.editWin;
                edit.setFormValue(sm, this.name);
                edit.show();
            }
        } ],
        hideBtn : [ "edit" ],
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'telVisitRecordId',
            url : dealUrl + '_getJson.action',
            sort : "telVisitRecordId",
            dir : "desc"
        },
        listEditStateFn : [ {
            backBnt : function(r) {
                if (new Date(r.get("endrq")) < new Date()) {
                    return false;
                }
                return true;
            }
        } ]
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            height : 300,
            labelWidth : 80,
            items : editFormItems,
            setFormValueAfter : function(formPanel) {
                initWin(formPanel);
            }
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7, gridDtl8, gridDtl9, gridDtl11, gridDtl12, gridDtl13, gridDtl14, gridDtl15, gridDtl20 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 600,
        height : 270,
        form : {
            labelWidth : 100,
            columnWidth : 0.5,
            items : [ {
                fieldLabel : '回访任务单号',
                name : "filter_LIKE_telVisit_telVisitNo",
                xtype : "textfield"
            }, {
                fieldLabel : '回访记录单号',
                name : "filter_LIKE_telVisitRecordNo",
                xtype : "textfield"
            }, {
                fieldLabel : "回访开始日期",
                xtype : 'datefield',
                name : 'filter_GE_startrq',
                editable : false,
                format : 'Y-m-d',
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var startrq = _form.findField('filter_GE_startrq').getValue();
                        var endrq = _form.findField("filter_LE_endrq").getValue();
                        if (startrq && endrq) {
                            if (endrq < startrq) {
                                Q.warning("开始日期不能大于结束日期");
                                _form.findField('filter_GE_startrq').setValue("");
                                return;
                            }
                        }
                        _form.findField('filter_GE_returnRecord').reset();

                    }
                }
            }, {
                fieldLabel : "回访结束日期",
                xtype : 'datefield',
                name : 'filter_LE_endrq',
                editable : false,
                format : 'Y-m-d',
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var startrq = _form.findField('filter_GE_startrq').getValue();
                        var endrq = _form.findField("filter_LE_endrq").getValue();
                        if (startrq && endrq) {
                            if (endrq < startrq) {
                                Q.warning("结束日期不能小于开始日期");
                                _form.findField('filter_LE_endrq').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_individCust_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '回访类型',
                hiddenName : "filter_EQ_backtype",
                clearable : true,
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.undovisit.selStores.backTypeStore,
                value : null
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_individCust_name",
                xtype : "textfield"
            }, {
                fieldLabel : '回访方式',
                hiddenName : "filter_EQ_backfs",
                clearable : true,
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.undovisit.selStores.backfsStore
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_individCust_mobile",
                xtype : "numberfield"
            }, {
                fieldLabel : '任务类型',
                clearable : true,
                hiddenName : "filter_EQ_taskType",
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.undovisit.selStores.taskTypeStore
            }, {
                fieldLabel : '优先回访记录单',
                clearable : true,
                hiddenName : "filter_GE_returnRecord",
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.undovisit.selStores.returnRecordStore,
                listeners : {
                    "select" : function(c, r, i) {
                        var _form = vp.searchWin.formPanel.getForm();
                        if (!_form.findField('filter_LE_endrq').getValue()) {
                            _form.findField('filter_GE_returnRecord').reset();
                            Q.tips(Q.color("请选择回访结束日期!", "red"));
                        }
                        _form.findField('filter_GE_startrq').reset();
                    }
                }
            }, {
                fieldLabel : "回访门店",
                xtype : "textfield",
                name : "filter_IN_storeName",
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
                                var nameArr = [];
                                Q.each(r, function() {
                                    nameArr.push(this.data.name);
                                });
                                vp.searchWin.formPanel.getForm().findField('filter_IN_storeName').setValue(nameArr.join(","));
                            });
                            _storeWin.show();
                        });
                    }
                }
            } ]
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0].hide();
        });
    }
};