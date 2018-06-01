Ext.ns('Q.telvisitrecord');
var dealUrl = path + '/TelVisitRecord';

Q.telvisitrecord.selStores = {
    // 回访方式
    backfsStore : Q.common.selDict(2101),
    // 回访类型
    backTypeStore : Q.common.selDict(1114),
    // 满意度
    saticFactionStore : Q.common.selDict(2104),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 积分状态
    gradeztStore : Q.common.selDict(3101),
    // 等级
    lvStore : Q.common.selDict(3100),
    // 任务类型
    taskTypeStore : Q.common.selDict(2107),
    // 意向价位
    purposePriceStore : Q.common.selDict(1116),
    // 意向产品类型
    purposeProductStore : Q.common.selDict(1117),
    // 意向产品品类
    purposeCategoryStore : Q.common.selDict(1118)
};

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
    width : 120,
    dataIndex : "telVisit",
    renderer : function(v) {
        if (!!v) {
            return v.telVisitNo;
        }
    }
}, {
    header : "回访记录单号",
    width : 130,
    dataIndex : "telVisitRecordNo"
}, {
    header : "会员卡号",
    width : 90,
    dataIndex : "individCust.cardNo"
}, {
    header : "客户名称",
    width : 100,
    dataIndex : "individCust.name"
}, {
    header : "客户类型",
    width : 80,
    dataIndex : "individCust.typeName"
}, {
    header : "回访方式",
    width : 80,
    dataIndex : "backfsName"
}, {
    header : "回访类型",
    width : 80,
    dataIndex : "backtypeName"
}, {
    header : "任务类型",
    width : 80,
    dataIndex : "taskTypeName"
}, {
    header : "回访开始日期",
    width : 100,
    renderer : Q.common.dateRenderer,
    dataIndex : "startrq"
}, {
    header : "回访结束日期",
    width : 100,
    renderer : Q.common.dateRenderer,
    dataIndex : "endrq"
}, {
    header : "是否已过期",
    width : 80,
    dataIndex : "expiredtypeName"
}, {
    header : "是否已回访",
    width : 80,
    dataIndex : "backztName",
}, {
    header : "回访日期",
    dataIndex : "backtime",
    renderer : Q.common.dateRenderer,
    width : 100
}, {
    header : "回访人",
    width : 80,
    dataIndex : "storeManagerName"
}, {
    header : "回访门店",
    width : 250,
    dataIndex : "storeName"
} ];

// 表单
var editFormItems = [ {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '基本信息',
    anchor : "-3",
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
            name : 'model.individCust.individCustId',
            xtype : 'hidden'
        }
    }, {
        items : {
            fieldLabel : "回访记录单号",
            name : 'model.telVisitRecordNo',
            xtype : 'textfield',

            disabled : true,
            emptyText : '系统自动生成',
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '回访日期',
            name : 'model.backtime',
            format : 'Y-m-d',
            anchor : "90%",
            xtype : 'datefield'
        }
    }, {
        items : {
            fieldLabel : "回访方式",
            name : 'model.backfs',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.backfsStore
        }
    }, {
        items : {
            fieldLabel : "会员卡号",
            anchor : "90%",
            xtype : 'textfield',
            name : 'model.individCust.cardNo'
        }
    }, {
        items : {
            fieldLabel : '客户名称',
            name : 'model.individCust.name',
            xtype : 'textfield',
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '手机号码',
            name : 'model.individCust.mobile',
            xtype : 'textfield',
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : "回访人员",
            name : 'model.storeManagerName',
            xtype : 'textfield',
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '是否已回访',
            xtype : 'uxcombo',
            name : 'model.backzt',
            store : Q.common.yesOrNotStore,
            anchor : "91%"
        }
    }, {
        items : {
            fieldLabel : '回访类型',
            xtype : 'uxcombo',
            name : 'model.backtype',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.backTypeStore,
            anchor : "91%"
        }
    }, {
        items : {
            fieldLabel : '回访开始日期',
            anchor : "90%",
            format : 'Y-m-d',
            xtype : 'datefield',
            name : 'model.startrq'
        }
    }, {
        items : {
            fieldLabel : '回访结束日期',
            anchor : "90%",
            xtype : 'datefield',
            format : 'Y-m-d',
            name : 'model.endrq'
        }
    }, {
        items : {
            fieldLabel : "任务类型",
            anchor : "91%",
            name : 'model.taskType',
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.taskTypeStore
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
            name : 'model.individCust.purposePrice',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.purposePriceStore,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '意向产品类型',
            anchor : "91%",
            name : 'model.individCust.purposeProduct',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.purposeProductStore,
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '意向产品品类',
            anchor : "91%",
            name : 'model.individCust.purposeCategory',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.purposeCategoryStore,
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
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.shopservice'
        }
    }, {
        items : {
            fieldLabel : '饰品佩戴',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.ornamentwear'
        }
    }, {
        items : {
            fieldLabel : '专业知识',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.professorknow'
        }
    }, {
        items : {
            fieldLabel : '赠品发放',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.parentgant'
        }
    }, {
        items : {
            fieldLabel : '赠品满意度',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.parentmanyi'
        }
    }, {
        items : {
            fieldLabel : '保养维修',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.careupdate'
        }
    }, {
        items : {
            fieldLabel : '佩戴后维护',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.wearupdate'
        }
    }, {
        items : {
            fieldLabel : '购物环境',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.shopenvi'
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
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.notshopservice'
        }
    }, {
        items : {
            fieldLabel : '专业知识',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.notprofessorknow'
        }
    }, {
        items : {
            fieldLabel : '购物环境',
            anchor : "91%",
            xtype : 'uxcombo',
            valueField : "value",
            displayField : "name",
            store : Q.telvisitrecord.selStores.saticFactionStore,
            name : 'model.notshopenvi'
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
                fieldLabel : "消费次数",
                name : "posnum",
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
                fieldLabel : "累计消费额",
                name : "sumactualSaleAmount",
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
                fieldLabel : "会员等级",
                name : "lvName",
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
            width : 120
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
            width : 100
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
            width : 100
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
            width : 80
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

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '回访历史记录单',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        viewConfig : {
            forceFit : false
        },
        store : {
            idProperty : 'telVisitRecordId',
            url : path + '/TelVisitRecord_getJson.action?filter_EQ_expiredtype_OR_EQ_backzt=1',
            sort : "telVisitRecordId",
            dir : "desc"
        },
        listEditStateFn : []
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 80,
            height : 300,
            items : editFormItems
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7, gridDtl8, gridDtl9, gridDtl11, gridDtl12, gridDtl13, gridDtl14, gridDtl15 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 600,
        height : 280,
        form : {
            labelWidth : 80,
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
                            }
                        }
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
                name : "filter_EQ_backtype",
                clearable : true,
                xtype : 'uxcombo',
                value : null,
                valueField : "value",
                displayField : "name",
                store : Q.telvisitrecord.selStores.backTypeStore
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_individCust_name",
                xtype : "textfield"
            }, {
                fieldLabel : '回访方式',
                name : "filter_EQ_backfs",
                clearable : true,
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.telvisitrecord.selStores.backfsStore
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_individCust_mobile",
                xtype : "textfield"
            }, {
                fieldLabel : '任务类型',
                clearable : true,
                name : "filter_EQ_taskType",
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                store : Q.telvisitrecord.selStores.taskTypeStore
            }, {
                fieldLabel : '回访人',
                name : "filter_LIKE_storeManagerName",
                xtype : "textfield"
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
    }
};