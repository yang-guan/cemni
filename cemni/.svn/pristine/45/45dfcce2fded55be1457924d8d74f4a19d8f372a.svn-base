Ext.ns('Q.archive');
var bntType;
var dealUrl = path + '/IndividCust';
var original_img = path + '/js/archive/individcust/original.jpg';// 原始头像

Q.archive.selStores = {
    // 性别
    sexStore : Q.common.selDict(1100),
    // 客户类型
    typeStore : Q.common.selDict(1101),
    // 会员等级
    lvStore : Q.common.selDict(3100),
    // 积分状态
    creditStatusStore : Q.common.selDict(3101),
    // 积分来源
    creditOriginStore : Q.common.selDict(3104),
    // 积分变更类型
    creditModTypeStore : Q.common.selDict(3103),
    // 等级变更类型
    lvModTypeStore : Q.common.selDict(3102),
    // 使用状态
    enableStore : Q.common.selDict(1102),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 职业
    jobStore : Q.common.selDict(1105),
    // 收入
    incomeStore : Q.common.selDict(1106),
    // 品牌获知渠道
    channelStore : Q.common.selDict(1107),
    // 购买目的
    motivesStore : Q.common.selDict(1108),
    // 购买因素
    factorStore : Q.common.selDict(1109),
    // 信仰
    beliefStore : Q.common.selDict(1110),
    // 年龄段
    ageStore : Q.common.selDict(1113),
    // 新老会员标识
    freshStore : Q.common.selDict(1114),
    // 与推荐人关系
    relStore : Q.common.selDict(1111),
    // 审核状态
    statusStore : Q.common.selDict(1112),
    // 积分状态
    gradeztStore : Q.common.selDict(3101),
    // 短信类型
    smsStore : Q.common.selDict(8000),
    // 家庭成员-关系
    familyStore : Q.common.selDict(1119),
    // 纪念日
    anniversaryStore : Q.common.selDict(1120),
    // 产品分数段
    cpScoreStore : Q.common.selDict(9800),
    // 产品设计师款
    designerStore : Q.common.selDict(2200),
    // 意向价位
    purposePriceStore : Q.common.selDict(1116),
    // 意向产品类型
    purposeProductStore : Q.common.selDict(1117),
    // 意向产品品类
    purposeCategoryStore : Q.common.selDict(1118),

    // 省
    provinceStore : new Ext.data.JsonStore({
        url : path + "/Common_selArea.action?filter_EQ_parentcode=0",
        fields : [ "areacode", "name" ],
        autoLoad : true
    }),
    // 市
    cityStore : new Ext.data.JsonStore({
        url : path + "/Common_selArea.action",
        fields : [ "areacode", "name" ],
        autoLoad : false
    }),
    // 区县
    countyStore : new Ext.data.JsonStore({
        url : path + "/Common_selArea.action",
        fields : [ "areacode", "name" ],
        autoLoad : false
    })
};

// 客户来源
var custSrcRadioChg = function() {
    var _form = vp.editWin.formPanel.getForm();
    var srcRadio = _form.findField("sources").items;

    if (bntType == 'view') {
        _form.findField('model.sources').disable();
        _form.findField('model.planName').disable();
        _form.findField('model.unionActivitiesName').disable();
        _form.findField("model.referrer").disable();
        _form.findField("model.relationship").disable();
        _form.findField('model.srcStoreName').disable();
        _form.findField("model.otherSources").disable();

        for (var i = 0; i < srcRadio.length; i++) {
            srcRadio.get(i).disable();
        }
        return;
    }

    _form.findField('model.srcStoreName').enable();
    _form.findField("model.sources").enable();
    for (var i = 0; i < srcRadio.length; i++) {
        srcRadio.get(i).enable();
    }

    var v = _form.findField("model.sources").getGroupValue();
    if (!v) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();
        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();
        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();
    } else if (v == 7) {
        _form.findField("model.planId").enable();
        _form.findField('model.planName').enable();

        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();
        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();
    } else if (v == 8) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();

        _form.findField("model.unionActivitiesId").enable();
        _form.findField('model.unionActivitiesName').enable();

        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();
    } else if (v == 9) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();
        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();

        _form.findField("model.referrer").enable();
        _form.findField("model.relationship").enable();
    } else if (v == 1) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();
        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();
        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();

        _form.findField("model.otherSources").setValue("").disable();
    } else if (v == 4 || v == 3 || v == 2) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();
        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();
        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();
    } else if (v == 10) {
        _form.findField("model.planId").setValue("").disable();
        _form.findField('model.planName').setValue("").disable();
        _form.findField("model.unionActivitiesId").setValue("").disable();
        _form.findField('model.unionActivitiesName').setValue("").disable();
        _form.findField("model.referrer").setValue("").disable();
        _form.findField("model.relationship").setValue("").disable();

        _form.findField("model.otherSources").enable();
    }
};

// 列表
var gridColumn = [ {
    dataIndex : "individCustId",
    hidden : true
}, {
    dataIndex : "mobile",
    hidden : true
}, {
    dataIndex : "ncNo",
    hidden : true
}, {
    dataIndex : "ncNoHis",
    hidden : true
}, {
    dataIndex : "ncNoInvalid",
    hidden : true
}, {
    dataIndex : "active",
    hidden : true
}, {
    dataIndex : "enable",
    hidden : true
}, {
    header : "会员卡号",
    width : 90,
    dataIndex : "cardNo"
}, {
    header : "客户名称",
    width : 80,
    dataIndex : "name"
}, {
    header : "客户类型",
    width : 80,
    dataIndex : "typeName"
}, {
    header : "性别",
    width : 50,
    dataIndex : "genderName"
}, {
    header : "会员等级",
    width : 80,
    dataIndex : "lvName"
}, {
    header : "当前可用积分",
    width : 90,
    dataIndex : "credit"
}, {
    header : "审核状态",
    width : 70,
    dataIndex : "status",
    renderer : function(v) {
        if (v == 'NEW') {
            return "新建";
        } else if (v == 'CONFIRM') {
            return "审核中";
        } else if (v == 'PASS') {
            return "已审核";
        } else if (v == 'NOPASS') {
            return "驳回";
        } else {
            return v;
        }
    }
}, {
    header : "使用状态",
    width : 70,
    dataIndex : "enableName"
}, {
    header : "活跃状态",
    width : 70,
    dataIndex : "activeName"
}, {
    header : "新老会员",
    width : 70,
    dataIndex : "freshName"
}, {
    header : "末次消费日期",
    dataIndex : "lastBuyTime",
    renderer : Q.common.dateRenderer,
    width : 100
}, {
    header : "末次消费门店",
    dataIndex : "lastStoreName",
    sortable : false,
    width : 200
}, {
    header : "归属门店",
    dataIndex : "belongStoreName",
    sortable : false,
    width : 200
}, {
    header : "门店属性",
    sortable : false,
    dataIndex : "belongAttrName",
    width : 70
}, {
    header : "地址",
    dataIndex : "fullAddress",
    sortable : false,
    width : 250
}, {
    header : "创建日期",
    width : 80,
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    xtype : 'fieldset',
    title : '基本信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false
    },
    items : [ {
        columnWidth : 0.85,
        xtype : 'fieldset',
        layout : 'column',
        border : false,
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 0.33,
            labelWidth : 80
        },
        items : [ {
            items : [ {
                name : 'model.individCustId',
                xtype : "hidden"
            }, {
                id : "groupCustId",
                name : 'model.groupCustId',
                xtype : "hidden"
            }, {
                name : 'model.imgPath',
                xtype : "hidden"
            } ]
        }, {
            items : [ {
                fieldLabel : '会员卡号',
                anchor : "85%",
                name : 'model.cardNo',
                emptyText : '系统自动生成',
                xtype : "textfield",
                readOnly : true
            } ]
        }, {
            items : [ {
                fieldLabel : '性别',
                anchor : "85.8%",
                hiddenName : 'model.gender',
                xtype : "uxcombo",
                value : 1,
                valueField : "value",
                displayField : "name",
                store : Q.archive.selStores.sexStore
            } ]
        }, {
            items : [ {
                fieldLabel : '客户类型',
                anchor : "85%",
                name : 'model.type',
                xtype : "uxcombo",
                value : 1,
                valueField : "value",
                displayField : "name",
                store : Q.archive.selStores.typeStore,
                readOnly : true
            } ]
        }, {
            items : [ {
                fieldLabel : '客户名称<font color="red">*</font>',
                anchor : "85%",
                allowBlank : false,
                name : 'model.name',
                xtype : "textfield"
            } ]
        }, {
            items : [ {
                fieldLabel : '固定电话',
                anchor : "85%",
                name : 'model.phone',
                xtype : "textfield"
            } ]
        }, {
            items : [ {
                fieldLabel : '所属团体',
                name : 'model.groupCustName',
                id : "groupName",
                anchor : "85%",
                xtype : "textfield",
                readOnly : true,
                listeners : {
                    render : function(p) {
                        p.getEl().on('click', function(p) {
                            if (bntType == 'view') {
                                return;
                            }
                            var _win = new Q.archive.chooseGroupWin();
                            _win.on("submit", function(r) {
                                Ext.getCmp('groupCustId').setValue(r.data.groupCustId);
                                Ext.getCmp('groupName').setValue(r.data.groupName);
                            });
                            _win.show();
                        });
                    }
                }
            } ]
        }, {
            items : [ {
                fieldLabel : '手机号码<font color="red">*</font>',
                anchor : "85%",
                allowBlank : false,
                name : 'model.mobile',
                xtype : "numberfield",
                regex : /^\d{11}$/,
                regexText : "请输入正确的手机号码"
            } ]
        }, {
            items : [ {
                fieldLabel : '身份证号',
                anchor : "85%",
                name : 'model.idcard',
                xtype : "textfield",
                regex : /(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                regexText : "请输入有效的身份证号"
            } ]
        }, {
            items : [ {
                fieldLabel : '是否发送短信',
                anchor : "85.8%",
                hiddenName : 'model.isSendSms',
                xtype : "uxcombo",
                value : 1,
                store : Q.common.yesOrNotStore
            } ]
        }, {
            items : [ {
                fieldLabel : '生日<font color="red">*</font>',
                name : 'model.birthday',
                xtype : "datefield",
                maxValue : new Date(),
                editable : false,
                allowBlank : false,
                format : 'Y-m-d',
                anchor : "85%"
            } ]
        }, {
            items : [ {
                fieldLabel : '结婚纪念日',
                name : 'model.weddingDay',
                xtype : "datefield",
                editable : false,
                format : 'Y-m-d',
                anchor : "85%"
            } ]
        }, {
            items : [ {
                fieldLabel : '电子邮箱',
                name : 'model.email',
                xtype : "textfield",
                anchor : "85%"
            } ]
        }, {
            columnWidth : 1.015,
            items : [ {
                fieldLabel : 'NC卡号',
                name : 'model.ncNo',
                xtype : "textfield",
                readOnly : true,
                anchor : "92.5%"
            } ]
        }, {
            columnWidth : 1.015,
            items : [ {
                fieldLabel : '收货地址',
                name : 'model.shippingAddr',
                xtype : "textfield",
                anchor : "92.5%"
            } ]
        }, {
            xtype : 'panel',
            layout : 'hbox',
            columnWidth : 1,
            labelWidth : 1,
            border : false,
            defaults : {
                layout : 'form',
                border : false
            },
            items : [ {
                width : 80,
                items : [ {
                    xtype : 'label',
                    text : "地址:"
                } ]
            }, {
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'model.country',
                    value : 0,
                    width : 70,
                    store : new Ext.data.ArrayStore({
                        fields : [ 'value', 'text' ],
                        data : [ [ '0', '中国' ], [ '-1', '其它' ] ]
                    }),
                    listeners : {
                        select : function(c) {
                            var _form = vp.editWin.formPanel.getForm();
                            if (c.getValue() == -1) {
                                _form.findField("model.province").setValue("");
                                _form.findField("model.province").readOnly = true;
                                _form.findField("model.city").setValue("");
                                _form.findField("model.city").readOnly = true;
                                _form.findField("model.county").setValue("");
                                _form.findField("model.county").readOnly = true;
                            } else {
                                _form.findField("model.province").readOnly = false;
                                _form.findField("model.city").readOnly = false;
                                _form.findField("model.county").readOnly = false;
                            }
                        }
                    }
                } ]
            }, {
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'model.province',
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.provinceStore,
                    emptyText : "省份",
                    width : 110,
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
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'model.city',
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.cityStore,
                    emptyText : "地市",
                    width : 110,
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
                items : [ {
                    xtype : "uxcombo",
                    hiddenName : 'model.county',
                    valueField : "areacode",
                    displayField : "name",
                    store : Q.archive.selStores.countyStore,
                    emptyText : "区县",
                    width : 110
                } ]
            }, {
                items : [ {
                    xtype : "textfield",
                    name : 'model.town',
                    emptyText : '乡镇',
                    width : 120
                } ]
            }, {
                items : [ {
                    xtype : "textfield",
                    name : 'model.address',
                    width : 250
                } ]
            } ]
        } ]
    }, {
        columnWidth : 0.15,
        items : [ {
            xtype : 'box',
            id : 'IndividCustImg',
            width : 110,
            height : 120,
            style : "margin-left: 15px; margin-bottom: 2px;",
            autoEl : {
                tag : 'img',
                src : original_img
            }
        }, {
            xtype : "button",
            width : 80,
            name : 'uploadBtn',
            text : "上传头像",
            style : "margin-left: 33px;",
            handler : function() {
                var _win = new Q.img.uploadWin({
                    url : dealUrl + "_uploadImage.action"
                });
                _win.on("upload", function(action) {
                    var imageFullPath = action.result.imageFullPath;

                    var _form = vp.editWin.formPanel.getForm();
                    _form.findField("model.imgPath").setValue(imageFullPath);
                    Ext.getCmp('IndividCustImg').getEl().dom.src = dealUrl + "_showImage.action?imageFullPath=" + imageFullPath;
                });
                _win.show();
            }
        } ]
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
            anchor : "86%",
            hiddenName : 'model.purposePrice',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.purposePriceStore
        }
    }, {
        items : {
            fieldLabel : '意向产品类型',
            anchor : "86%",
            hiddenName : 'model.purposeProduct',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.purposeProductStore
        }
    }, {
        items : {
            fieldLabel : '意向产品品类',
            anchor : "86%",
            hiddenName : 'model.purposeCategory',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.purposeCategoryStore
        }
    }, {
        items : {
            fieldLabel : '需求时间',
            name : 'model.purposeDay',
            xtype : "datefield",
            editable : false,
            format : 'Y-m-d',
            anchor : "86%"
        }
    } ]
}, {
    xtype : 'fieldset',
    title : '拓展信息',
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
            fieldLabel : '职业',
            anchor : "86%",
            hiddenName : 'model.job',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.jobStore
        }
    }, {
        items : {
            fieldLabel : '收入',
            anchor : "86%",
            hiddenName : 'model.income',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.incomeStore
        }
    }, {
        items : {
            fieldLabel : '品牌获知渠道',
            anchor : "86%",
            hiddenName : 'model.brandChannel',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.channelStore
        }
    }, {
        items : {
            fieldLabel : '购买目的',
            anchor : "86%",
            hiddenName : 'model.motives',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.motivesStore
        }
    }, {
        items : {
            fieldLabel : '喜欢千年的理由',
            anchor : "86%",
            hiddenName : 'model.purchaseFactors',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.factorStore
        }
    }, {
        items : {
            fieldLabel : '信仰',
            anchor : "86%",
            hiddenName : 'model.belief',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.beliefStore
        }
    }, {
        items : {
            fieldLabel : '年龄段',
            anchor : "86%",
            hiddenName : 'model.age',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.ageStore
        }
    }, {
        items : {
            fieldLabel : '微信号',
            anchor : "85%",
            name : 'model.wechat',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '微信ID',
            anchor : "85%",
            name : 'model.wechatId',
            xtype : "textfield"
        }
    } ]
}, {
    xtype : 'fieldset',
    title : '兴趣爱好',
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
            fieldLabel : '所属私人俱乐部',
            anchor : "85%",
            name : 'model.club',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '对什么主题有兴趣',
            anchor : "85%",
            name : 'model.subject',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢的化妆品品牌',
            anchor : "85%",
            name : 'model.cosmeticsBrand',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢的菜式',
            anchor : "85%",
            name : 'model.likeFood',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢的消费场所',
            anchor : "85%",
            name : 'model.consumerPlace',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '嗜好和娱乐',
            anchor : "85%",
            name : 'model.hobby',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢读什么书',
            anchor : "85%",
            name : 'model.likeBook',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢的度假方式',
            anchor : "85%",
            name : 'model.resort',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '喜欢观赏的运动',
            anchor : "85%",
            name : 'model.likeSport',
            xtype : "textfield"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            name : 'model.remark',
            xtype : "textfield",
            anchor : "94%"
        }
    } ]
}, {
    xtype : 'fieldset',
    title : '客户来源',
    layout : 'column',
    anchor : "-3",
    columnWidth : 1,
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        columnWidth : 1,
        items : {
            fieldLabel : '来源渠道',
            name : "sources",
            xtype : "radiogroup",
            items : [ {
                boxLabel : 'CRM',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 2,
                checked : true
            }, {
                boxLabel : '市场活动',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 7
            }, {
                boxLabel : '异业联盟',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 8
            }, {
                boxLabel : '客户推荐',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 9
            }, {
                boxLabel : 'NC',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 1
            }, {
                boxLabel : '耀我网',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 4
            }, {
                boxLabel : '微信',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 3
            }, {
                boxLabel : '其他',
                xtype : 'radio',
                name : 'model.sources',
                inputValue : 10
            } ],
            listeners : {
                change : function(c) {
                    custSrcRadioChg();
                }
            }
        }
    }, {
        items : {
            name : 'model.planId',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '活动方案',
            name : "model.planName",
            xtype : "textfield",
            anchor : "85%",
            listeners : {
                render : function(p) {
                    var _form = vp.editWin.formPanel.getForm();
                    p.getEl().on('click', function(p) {
                        var _win = new Q.actment.chooseActWin();
                        _win.on("submit", function(r) {
                            _form.findField("model.planId").setValue(r.data.activityId);
                            _form.findField('model.planName').setValue(r.data.activityTheme);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            name : 'model.unionActivitiesId',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '异业联盟活动',
            name : 'model.unionActivitiesName',
            xtype : "textfield",
            anchor : "85%",
            listeners : {
                render : function(p) {
                    var _form = vp.editWin.formPanel.getForm();
                    p.getEl().on('click', function(p) {
                        var _win = new Q.actment.chooseActWin();
                        _win.on("submit", function(r) {
                            _form.findField('model.unionActivitiesId').setValue(r.data.activityId);
                            _form.findField('model.unionActivitiesName').setValue(r.data.activityTheme);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '推荐人',
            name : 'model.referrer',
            xtype : "textfield",
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _win = new Q.archive.chooseIndiWin();
                        _win.on("submit", function(r) {
                            vp.editWin.formPanel.getForm().findField('model.referrer').setValue(r.json.name + "（" + r.json.cardNo + "）");
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            name : 'model.srcStoreNo',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '来源门店<font color="red">*</font>',
            name : 'model.srcStoreName',
            xtype : "textfield",
            allowBlank : false,
            anchor : "85%",
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        var _form = vp.editWin.formPanel.getForm();
                        var _win = new Q.store.chooseStoreWin();
                        _win.on("submit", function(r) {
                            _form.findField('model.srcStoreNo').setValue(r.json.storeNo);
                            _form.findField('model.srcStoreName').setValue(r.json.name);
                        });
                        _win.show();
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '其他来源',
            anchor : "85%",
            name : 'model.otherSources',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '与推荐人关系',
            hiddenName : 'model.relationship',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.relStore,
            anchor : "85.6%"
        }
    } ]
} ];

// ///////////////////////////////////////////////////////////细单/////////////////////////////////////////////////////

var gridDtl1 = {
    tabTitle : "家庭成员",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "individCustFamily",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "familyRelId",
            hidden : true
        }, {
            dataIndex : "_individCustId",
            hidden : true
        }, {
            header : "姓名",
            dataIndex : "name",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "关系",
            dataIndex : "relationship",
            editor : {
                editable : false,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                emptyText : '请选择',
                store : Q.archive.selStores.familyStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.archive.selStores.familyStore, v);
            }
        }, {
            header : "出生日期",
            dataIndex : "birthday",
            editor : new Ext.form.DateField({
                editable : false,
                format : 'Y-m-d'
            }),
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "爱好",
            dataIndex : "hobby",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "联系电话",
            dataIndex : "tel",
            editor : {
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的联系电话",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "学历",
            dataIndex : "education",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "百日宴",
            dataIndex : "hundredDays",
            editor : new Ext.form.DateField({
                editable : false,
                format : 'Y-m-d'
            }),
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
                ;
            }
        }, {
            header : "是否有抚养权",
            dataIndex : "custody",
            editor : {
                hiddenName : 'model.custody',
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },

            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        }, {
            header : "子女教育",
            dataIndex : "kidEducation",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "子女爱好",
            dataIndex : "kidHobby",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "familyRelId",
        url : dealUrl + "_getRel.action",
        dir : "desc",
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
    } ]
};

var gridDtl2 = {
    tabTitle : "纪念日",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "anniversary",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "anniversaryId",
            hidden : true
        }, {
            dataIndex : "_individCustId",
            hidden : true
        }, {
            header : "纪念日名称",
            dataIndex : "name",
            editor : {
                editable : false,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                emptyText : '请选择',
                store : Q.archive.selStores.anniversaryStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.archive.selStores.anniversaryStore, v);
            }
        }, {
            header : "日期",
            dataIndex : "days",
            editor : new Ext.form.DateField({
                format : 'Y-m-d'
            }),
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            editor : {
                maxLength : 100,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "anniversaryId",
        url : dealUrl + "_getRel2.action",
        dir : "desc",
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
    } ]
};

var gridDtl3 = {
    tabTitle : "价值贡献",
    xtype : 'formpanel',
    foreignKey : "individCustId",
    tabClassName : "contributeAlias",
    loadUrl : dealUrl + "_getValue4.action",
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
    foreignKey : "individCust_individCustId",
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
        url : dealUrl + "_getRel4.action",
        sort : "posId",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl5 = {
    tabTitle : "积分变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
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
        url : dealUrl + "_getRel9.action",
        sort : "integralAdjHisId",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl6 = {
    tabTitle : "等级变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
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
        url : dealUrl + "_getRel10.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl7 = {
    tabTitle : "状态变化",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "activeStatusAlias",
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
        url : dealUrl + "_getRel5.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl8 = {
    tabTitle : "权益单",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "rightMaintAlias",
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
            dataIndex : "handleStateName"
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
            }
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
        url : dealUrl + "_getRightMain.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl9 = {
    tabTitle : "售后服务",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "afterServAlias",
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
        url : dealUrl + "_getRel6.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl10 = {
    tabTitle : "核定记录",
    xtype : 'uxeditorgrid',
    foreignKey : "individCustId",
    tabClassName : "reviewLogAlias",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "operationLogId",
            disabled : true
        }, {
            dataIndex : "_individCustId",
            disabled : true
        }, {
            header : "核定人",
            dataIndex : "cuser",
            width : 10
        }, {
            header : "操作时间",
            dataIndex : "cdate",
            renderer : Q.common.timeRenderer,
            width : 15
        }, {
            header : "核定结果",
            dataIndex : "result",
            width : 10
        }, {
            header : "核定意见",
            dataIndex : "remark",
            width : 60
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "operationLogId",
        url : dealUrl + "_getRel7.action?filter_EQ_type=1",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl11 = {
    tabTitle : "客户拜访",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "callRegistAlias",
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
            dataIndex : "visitPerson"
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
    foreignKey : "individCust_individCustId",
    tabClassName : "busiRegistAlias",
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
            width : 300
        }, {
            header : "处理结果",
            dataIndex : "handleResult",
            width : 300
        } ]
    },

    pageSize : 0,
    store : {
        idProperty : "busiRegistId",
        url : dealUrl + "_getBusiRegist.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl13 = {
    tabTitle : "短信维系",
    xtype : 'uxeditorgrid',
    foreignKey : "individCustId",
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
        url : dealUrl + "_getRel11.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl14 = {
    tabTitle : "电话回访",
    xtype : 'uxeditorgrid',
    foreignKey : "individCust_individCustId",
    tabClassName : "recordList",
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
    foreignKey : "individCustId",
    tabClassName : "indipartinAlias",// 避免和实体类属性同名，否则会级联
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
            dataIndex : "activityTypeName"
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
        url : dealUrl + "_getRel16.action",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl16 = {
    tabTitle : "操作日志",
    xtype : 'uxeditorgrid',
    foreignKey : "individCustId",
    tabClassName : "operationLog",
    pageSize : 0,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "operationLogId",
            hidden : true
        }, {
            dataIndex : "_individCustId",
            hidden : true
        }, {
            header : "操作人",
            dataIndex : "cuser",
            width : 10
        }, {
            header : "操作日期",
            dataIndex : "cdate",
            renderer : Q.common.timeRenderer,
            width : 15
        }, {
            header : "日志",
            dataIndex : "remark",
            width : 75
        } ]
    },
    store : {
        idProperty : "operationLogId",
        url : dealUrl + "_getRel7.action?filter_EQ_type=2",
        dir : "desc",
        autoLoad : false
    }
};

var gridDtl20 = {
    tabTitle : "推荐明细",
    xtype : 'uxeditorgrid',
    foreignKey : "individCustId",
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
        url : dealUrl + "_qryRefInfo.action",
        autoLoad : false
    }
};

// /////////////////////////////////////////////////////////////////////////////////////////////////////////
Q.archive.custTbarBnt = [ {
    text : '查询',
    index : 5,
    iconCls : 'icon-search',
    build : power.search,
    handler : function() {
        var store = vp.grid.getStore();
        var win = new Q.archive.custSearchWin();
        win.on("search", function(data) {
            store.baseParams = {
                start : 0,
                limit : 20

            };
            Ext.apply(store.baseParams, data);
            store.load();
        });
        win.show();
    }
}, {
    text : '批量查询',
    index : 6,
    iconCls : 'icon-search',
    build : power.batchsearch,
    handler : function() {
        var store = vp.grid.getStore();
        var win = new Q.archive.custBatchSearchWin();
        win.on("search", function(data) {
            store.baseParams = {
                start : 0,
                limit : 20
            };
            Ext.apply(store.baseParams, data);
            store.load();
        });
        win.show();
    }
}, {
    name : 'lockBnt',
    text : '冻结/解冻',
    index : 7,
    iconCls : 'icon-lock',
    build : power.changecode,
    handler : function() {
        var sm = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        var enableStr = (sm.get("enable") == 1 ? "冻结" : "解冻");
        Q.confirm("确定执行“" + enableStr + "”操作吗？", {
            ok : function() {
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : dealUrl + "_chgEnable.action",
                    params : {
                        id : sm.get("individCustId")
                    },
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error("操作失败！");
                            return;
                        }
                        Q.tips(Q.color("操作成功！", "red"));
                        vp.grid.getStore().reload();
                        vp.grid.getSelectionModel().clearSelections();
                    },
                    failure : function(response) {
                        Q.error("操作失败！");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
}, {
    name : 'invalidBnt',
    text : '无效',
    index : 8,
    iconCls : 'icon-toReject',
    build : power.changecode2,
    handler : function() {
        var sm = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        Q.confirm("确定执行“失效”操作吗？", {
            ok : function() {
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : dealUrl + "_custInvalid.action",
                    params : {
                        id : sm.get("individCustId")
                    },
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error("操作失败！");
                            return;
                        }
                        Q.tips(Q.color("操作成功！", "red"));
                        vp.grid.getStore().reload();
                        vp.grid.getSelectionModel().clearSelections();
                    },
                    failure : function(response) {
                        Q.error("操作失败！");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
}, {
    text : "下载模版",
    index : 9,
    iconCls : "icon-excel",
    build : power.download,
    handler : function() {
        window.open(path + "/Common_downLoadExcelTpl.action?fileName=IndividCust.xlsx");
    }
}, {
    text : '导入',
    index : 10,
    iconCls : 'icon-upload',
    build : power.import,
    handler : function() {

        var _win = new Q.excel.uploadWin({
            url : dealUrl + "_excel.action"
        });
        _win.on("upload", function() {
            vp.grid.getStore().load();
        });
        _win.show();
    }
}, {
    text : "导出",
    index : 11,
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {

        var store = vp.grid.getStore();
        var totalCount = store.getTotalCount();
        if (totalCount > 5000) {
            Q.tips(Q.color("每次导出上限为5000", "red"));
            return;
        } else {
            window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(vp.grid.getStore().baseParams));
        }
    }
}, {
    name : "sendSmsBnt",
    text : '发送验证码',
    index : 12,
    iconCls : 'icon-recv',
    build : power.sendsmg,
    handler : function() {
        var r = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(r)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        Ext.Ajax.request({
            url : dealUrl + "_sendMsg.action?id=" + r.get("individCustId"),
            success : function(response) {
                var json = Ext.decode(response.responseText);
                if (json.success) {
                    Ext.MessageBox.prompt("提示", "请输入您的验证码：", function(btn, value) {
                        if (btn == 'ok') {
                            if (!value) {
                                Q.tips(Q.color("验证码为空！", "red"));
                            } else {
                                Ext.Ajax.request({
                                    url : dealUrl + "_checkCode.action",
                                    params : {
                                        mobile : r.get("mobile"),
                                        code : value
                                    },
                                    success : function(resp) {
                                        var json2 = Ext.decode(resp.responseText);
                                        Q.tips(Q.color(json2.msg, "red"));
                                        if (json2.success) {
                                            vp.grid.getStore().reload();
                                        }
                                    }
                                });
                            }
                        }
                    });
                } else {
                    Q.tips(Q.color(json.msg, "red"));
                }
            }
        });
    }
}, {
    text : "批量修改",
    index : 13,
    iconCls : "icon-edit",
    build : power.belongstore,
    handler : function() {
        var _store = vp.grid.getStore();
        if (_store.length == 0) {
            Q.tips(Q.color("暂无数据！", "red"));
            return false;
        }

        // 是否点击了列表中的“全选”复选框
        var isAllSelected = 0;
        var hd = vp.grid.getEl().select('div.x-grid3-hd-inner.x-grid3-hd-checker').first();
        var classList = hd.getAttribute("class").split(' ');
        for (var i = 0; i < classList.length; i++) {
            if (classList[i] == 'x-grid3-hd-checker-on') {
                isAllSelected = 1;
            }
        }

        var sms = vp.grid.getSelectionModel().getSelections();
        if (!isAllSelected && sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }

        var ids = [];
        Q.each(sms, function() {
            ids.push(this.data.individCustId);
        });
        var data = {
            isAllSelected : isAllSelected,
            ids : ids
        };
        Ext.apply(data, _store.baseParams);
        var _win = new Q.batch.belongStoreWin(data);
        _win.on("submit", function(r) {
            _store.reload();
        });
        _win.show();
    }
}, {
    name : 'ncNoHisBnt',
    text : "NC卡号",
    index : 14,
    iconCls : "icon-edit",
    build : power.ncnohis,
    handler : function() {
        var _store = vp.grid.getStore();
        var sms = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sms)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        var _win = new Q.archive.ncNoHisWin({
            individCustId : sms.data.individCustId,
            ncNo : sms.data.ncNo,
            ncNoHis : sms.data.ncNoHis,
            ncNoInvalid : sms.data.ncNoInvalid
        });
        _win.on("submit", function() {
            _store.reload();
        });
        _win.show();
    }
} ];

var menuOverride = [ {
    text : "提交",
    name : 'CONFIRM',
    iconCls : "icon-toConfirm",
    hidden : false,
    handler : function() {
        vp.dealstate(this.name, this.text);
    }
}, {
    text : "通过",
    name : 'PASS',
    iconCls : "icon-toPass",
    hidden : false,
    handler : function() {
        vp.dealstate(this.name, this.text);
    }
}, {
    text : "不通过",
    name : 'NOPASS',
    iconCls : "icon-toNoPass",
    hidden : false,
    handler : function() {
        vp.dealstate(this.name, this.text, true, this.message);
    }
} ];

// /////////////////////////////////////////////////////////////////////////////////////////////////////////

var Org2Indiv = [];
Ext.Ajax.request({
    url : dealUrl + "_getOrgByUserId.action",
    success : function(response) {
        var json = Ext.decode(response.responseText);
        if (json.success) {
            Q.each(json.data, function(r) {
                Org2Indiv.push(r.orgcode);
            });
        }
    }
});

var cfg = {
    isAudit : true,
    dealUrl : dealUrl,
    moduleName : '个人档案',
    playListMode : playListMode.normal,
    menuOverride : menuOverride,
    vp : {
        sm : {
            singleSelect : false
        },
        addOtherBtn : Q.archive.custTbarBnt,
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        viewConfig : {
            forceFit : false
        },
        column : gridColumn,
        store : {
            idProperty : 'individCustId',
            url : dealUrl + '_getJson.action',
            sort : "individCustId",
            dir : "desc"
        },
        listEditStateFn : [ {
            edit : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                var flag = 0;
                for (var i = 0; i < Org2Indiv.length; i++) {
                    if (Org2Indiv[i] == r.json.belongStoreNo) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    return false;
                }
                if (sms.length != 1 || r.data.enable == 2) {
                    return false;
                }
                return true;
            }
        }, {
            view : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                if (sms.length != 1) {
                    return false;
                }
                return true;
            }
        }, {
            lockBnt : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                if (sms.length != 1) {
                    return false;
                }
                return true;
            }
        }, {
            invalidBnt : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                if (sms.length != 1 || r.data.active == 4) {
                    return false;
                }
                return true;
            }
        }, {
            sendSmsBnt : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                if (sms.length == 1 && (r.data.status == 'NEW' || r.data.status == 'NOPASS')) {
                    return true;
                } else {
                    return false;
                }
            }
        }, {
            ncNoHisBnt : function(r) {
                var sms = vp.grid.getSelectionModel().getSelections();
                if (sms.length != 1) {
                    return false;
                }
                return true;
            }
        } ],
        addAfter : function() {
            bntType = "add";
            Ext.getCmp('IndividCustImg').getEl().dom.value = "";
            Ext.getCmp('IndividCustImg').getEl().dom.src = original_img;

            var _win = vp.editWin;
            _win.hideTabItem("posOrder");
            _win.hideTabItem("activeStatusAlias");
            _win.hideTabItem("afterServAlias");
            _win.hideTabItem("reviewLogAlias");
            _win.hideTabItem("contributeAlias");
            _win.hideTabItem("integralAdjHis");
            _win.hideTabItem("gradeAdjHis");
            _win.hideTabItem("smslog");
            _win.hideTabItem("rightMaintAlias");
            _win.hideTabItem("callRegistAlias");
            _win.hideTabItem("busiRegistAlias");
            _win.hideTabItem("recordList");
            _win.hideTabItem("indipartinAlias");
            _win.hideTabItem("operationLog");
            _win.hideTabItem("refInfo");
            _win.setActiveTab([ "indipartinAlias", "callRegistAlias", "operationLog", "posOrder", "activeStatusAlias", "afterServAlias", "reviewLogAlias", "contributeAlias", "integralAdjHis", "gradeAdjHis", "smslog", "rightMaintAlias", "recordList", "busiRegistAlias", "refInfo" ]);

            var _formPanel = _win.formPanel;
            var tbar1 = _formPanel.getTopToolbar().find("name", "CONFIRM")[0];
            var tbar2 = _formPanel.getTopToolbar().find("name", "PASS")[0];
            var tbar3 = _formPanel.getTopToolbar().find("name", "NOPASS")[0];
            tbar1.hide();
            tbar2.hide();
            tbar3.hide();
            custSrcRadioChg();

            // 根据登录人查询归属的组织机构：客服部（优先）、门店
            Ext.Ajax.request({
                url : dealUrl + "_getOneOrgByUserId.action",
                success : function(response) {
                    var json = Ext.decode(response.responseText);
                    if (json.success) {
                        var _form = _formPanel.getForm();
                        _form.findField('model.srcStoreNo').setValue(json.data.orgcode);
                        _form.findField('model.srcStoreName').setValue(json.data.name);
                    }
                }
            });
        },
        viewAfter : function() {
            bntType = "view";
            var _win = vp.editWin;
            _win.showTabItem("posOrder");
            _win.showTabItem("activeStatusAlias");
            _win.showTabItem("afterServAlias");
            _win.showTabItem("reviewLogAlias");
            _win.showTabItem("contributeAlias");
            _win.showTabItem("integralAdjHis");
            _win.showTabItem("gradeAdjHis");
            _win.showTabItem("smslog");
            _win.showTabItem("rightMaintAlias");
            _win.showTabItem("callRegistAlias");
            _win.showTabItem("busiRegistAlias");
            _win.showTabItem("recordList");
            _win.showTabItem("indipartinAlias");
            _win.showTabItem("operationLog");
            _win.showTabItem("refInfo");
        },
        editAfter : function() {
            bntType = "edit";
            var _win = vp.editWin;
            _win.hideTabItem("posOrder");
            _win.hideTabItem("activeStatusAlias");
            _win.hideTabItem("afterServAlias");
            _win.hideTabItem("reviewLogAlias");
            _win.hideTabItem("contributeAlias");
            _win.hideTabItem("integralAdjHis");
            _win.hideTabItem("gradeAdjHis");
            _win.hideTabItem("smslog");
            _win.hideTabItem("rightMaintAlias");
            _win.hideTabItem("callRegistAlias");
            _win.hideTabItem("busiRegistAlias");
            _win.hideTabItem("recordList");
            _win.hideTabItem("indipartinAlias");
            _win.hideTabItem("operationLog");
            _win.hideTabItem("refInfo");
            _win.setActiveTab([ "indipartinAlias", "callRegistAlias", "operationLog", "posOrder", "activeStatusAlias", "afterServAlias", "reviewLogAlias", "contributeAlias", "integralAdjHis", "gradeAdjHis", "smslog", "rightMaintAlias", "recordList", "busiRegistAlias", "refInfo" ]);

            var _formPanel = _win.formPanel;
            var tbar2 = _formPanel.getTopToolbar().find("name", "PASS")[0];
            var tbar3 = _formPanel.getTopToolbar().find("name", "NOPASS")[0];
            tbar2.hide();
            tbar3.hide();
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            items : editFormItems,
            labelWidth : 105,
            columnWidth : 1,
            height : 300,
            setFormValueAfter : function(formPanel) {
                custSrcRadioChg();
                var _form = formPanel.getForm();

                var provinceId = _form.findField("model.province").getValue();
                var cityId = _form.findField("model.city").getValue();
                var countyId = _form.findField("model.county").getValue();
                if (provinceId) {
                    Q.archive.selStores.cityStore.baseParams = {
                        "filter_EQ_parentcode" : provinceId
                    };
                    Q.archive.selStores.cityStore.load({
                        callback : function() {
                            _form.findField("model.city").setValue(cityId);
                        }
                    });
                }
                // 区县
                if (cityId) {
                    Q.archive.selStores.countyStore.baseParams = {
                        "filter_EQ_parentcode" : cityId
                    };
                    Q.archive.selStores.countyStore.load({
                        callback : function() {
                            _form.findField("model.county").setValue(countyId);
                        }
                    });
                }
                var imgPath = _form.findField("model.imgPath").getValue();
                if (imgPath) {
                    Ext.getCmp('IndividCustImg').getEl().dom.src = dealUrl + "_showImage.action?imageFullPath=" + imgPath + "&custId=" + _form.findField("model.individCustId").getValue();
                } else {
                    Ext.getCmp('IndividCustImg').getEl().dom.src = original_img;
                }

                // 根据登录人查询归属的组织机构：客服部（优先）、门店
                var srcStoreNo = _form.findField('model.srcStoreNo').getValue();
                if (!srcStoreNo) {
                    Ext.Ajax.request({
                        url : dealUrl + "_getOneOrgByUserId.action",
                        success : function(response) {
                            var json = Ext.decode(response.responseText);
                            if (json.success) {
                                _form.findField('model.srcStoreNo').setValue(json.data.orgcode);
                                _form.findField('model.srcStoreName').setValue(json.data.name);
                            }
                        }
                    });
                }
            }
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7, gridDtl8, gridDtl9, gridDtl10, gridDtl11, gridDtl12, gridDtl13, gridDtl14, gridDtl15, gridDtl20, gridDtl16 ]
        },
        addTabSetFormReadOnly : [ gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7, gridDtl8, gridDtl9, gridDtl10, gridDtl11, gridDtl12, gridDtl13, gridDtl14, gridDtl15, gridDtl20, gridDtl16 ]
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};