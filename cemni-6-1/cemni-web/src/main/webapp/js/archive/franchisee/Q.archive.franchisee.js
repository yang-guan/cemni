Ext.ns('Q.Franchisee');
var dealUrl = path + '/Franchisee';
Q.loadJs(path + "/js/console/store/Q.store.chooseStoreWin.js");

Q.Franchisee.selStores = {
    // 加盟商类型
    typeFra : Q.common.selDict(1300),
    // 加盟商状态
    stateFra : Q.common.selDict(1301),
    // 业务部门
    sourceFra : Q.common.selDict(1302),
    // 性别
    sexFra : Q.common.selDict(1100),
    // 不良记录
    recodeFra : Q.common.selDict(1304),
    // 订单类别
    typeSale : Q.common.selDict(9500)
};

// 列表
var gridColumn = [ {
    header : "加盟商编码",
    dataIndex : "fraCode",
    width : 8
}, {
    header : "加盟商名称",
    dataIndex : "fraName",
    width : 25
}, {
    header : "加盟商简称",
    dataIndex : "shortName",
    width : 20
}, {
    header : "状态",
    dataIndex : "fraStatusName",
    width : 7
}, {
    header : "业务部门",
    dataIndex : "sourcesName",
    width : 10
}, {
    header : "类别",
    dataIndex : "fraTypeName",
    width : 8
}, {
    header : "是否有效",
    dataIndex : "isValid",
    width : 8,
    renderer : Q.common.yesOrNotRenderer
}, {
    header : "创建日期",
    dataIndex : "createDate",
    renderer : Q.common.dateRenderer,
    width : 8
} ];

// 表单
var editFormItems = [ {
    labelWidth : 105,
    columnWidth : 1,
    xtype : 'fieldset',
    title : '基本信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.franchiseeId',
            xtype : 'hidden'
        }
    }, {
        items : {
            fieldLabel : '加盟商编码<font color="red">*</font>',
            name : 'model.fraCode',
            emptyText : '系统自动生成',
            xtype : "textfield",
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '加盟商名称<font color="red">*</font>',
            name : 'model.fraName',
            xtype : "textfield",
            readOnly : true,
            allowBlank : false,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '加盟商简称<font color="red">*</font>',
            name : 'model.shortName',
            xtype : "textfield",
            allowBlank : false,
            readOnly : true,
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '加盟商类别<font color="red">*</font>',
            hiddenName : 'model.fraType',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.Franchisee.selStores.typeFra,
            allowBlank : false,
            anchor : "91%"
        }
    }, {
        items : {
            fieldLabel : '加盟商状态<font color="red">*</font>',
            hiddenName : 'model.fraStatus',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.Franchisee.selStores.stateFra,
            allowBlank : false,
            anchor : "91%"
        }
    }, {
        items : {
            fieldLabel : '实际控制人',
            name : 'model.actualCon',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '累计首批拿货额',
            name : 'model.fpickCost',
            xtype : "numberfield",
            regex : /^(\-|\+)?\d+(\.\d{1,2})?$/,
            regexText : "请输入正确的拿货，如有小数点请保留两位",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '当月新店首批拿货',
            name : 'model.fpickMan',
            xtype : "numberfield",
            regex : /^(\-|\+)?\d+(\.\d{1,2})?$/,
            regexText : "请输入正确的拿货，如有小数点请保留两位",
            anchor : "90%"
        }
    } ]
}, {
    labelWidth : 100,
    columnWidth : 1,
    xtype : 'fieldset',
    title : '拓展信息',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '增值税号登记',
            name : 'model.vatNo',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '营业执照编号',
            name : 'model.busNo',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '社会信用代码',
            name : 'model.creditCode',
            maxLength : 40,
            anchor : "90%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '组织机构代码',
            name : 'model.orgNo',
            maxLength : 40,
            anchor : "90%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '法人代表',
            name : 'model.legalre',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '城市',
            name : 'model.city',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '公司地址',
            name : 'model.comAddr',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '业务联系人',
            name : 'model.busMan',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '联系人手机',
            name : 'model.mobile',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '联系人邮件',
            name : 'model.mail',
            xtype : "textfield",
            anchor : "90%"
        }
    }, {
        items : {
            fieldLabel : '联系人通讯地址',
            name : 'model.conAddr',
            xtype : "textfield",
            anchor : "90%"
        }
    } ]
}, {
    labelWidth : 100,
    columnWidth : 1,
    xtype : 'fieldset',
    title : '来源渠道',
    layout : 'column',
    anchor : "-3",
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '业务部门',
            hiddenName : 'model.sources',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.Franchisee.selStores.sourceFra,
            anchor : "91%"
        }
    }, {
        items : {
            fieldLabel : '业务人员',
            name : 'model.intMan',
            xtype : "textfield",
            anchor : "90%"
        }
    } ]
} ];

// 联系人页签
var gridDtl1 = {
    tabTitle : "联系人信息",
    xtype : 'uxeditorgrid',
    foreignKey : "franchisee_franchiseeId",
    tabClassName : "contact",
    viewConfig : {
        autoScroll : true
    },
    sm : {
        singleSelect : false
    },// 列表是否单选
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "contactId",
            hidden : true
        }, {
            dataIndex : "_franchiseeId",
            hidden : true
        }, {
            header : "姓名",
            dataIndex : "name",
            width : 70,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "性别",
            dataIndex : "sex",
            width : 50,
            editor : {
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.Franchisee.selStores.sexFra
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.Franchisee.selStores.sexFra, v);
            }
        }, {
            header : "职务",
            dataIndex : "duty",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "联系人地址",
            dataIndex : "addr",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "手机号码",
            dataIndex : "mobile",
            width : 90,
            editor : {
                regex : /^(1\d{10})$/,
                regexText : "请输入正确的手机号码",
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "固定号码",
            dataIndex : "phone",
            width : 100,
            editor : {
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的电话号码",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "生日",
            dataIndex : "birthday",
            width : 90,
            editor : {
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "QQ",
            dataIndex : "qq",
            width : 90,
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "微信",
            dataIndex : "weChat",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "邮箱",
            dataIndex : "mail",
            width : 120,
            editor : {
                regex : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
                regexText : "请输入正确的邮箱",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "关联联系人",
            dataIndex : "rleman",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "爱好/禁忌",
            dataIndex : "hobby",
            width : 150,
            editor : {
                maxLength : 150,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "是否发送短信",
            dataIndex : "isSms",
            width : 90,
            editor : {
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.yesOrNotRenderer(v);
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            width : 200,
            editor : {
                maxLength : 200,
                xtype : 'textfield'
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
        idProperty : "contactId",
        url : dealUrl + "_getRel.action",
        sort : "contactId",
        autoLoad : false,
        dir : "desc"
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

// 信用档案标签
var formdtl2 = {
    tabTitle : "信用档案",
    xtype : 'formpanel',
    foreignKey : "franchisee_franchiseeId",
    tabClassName : "credit",
    loadUrl : dealUrl + "_getRel2.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        layout : 'column',
        border : false,
        anchor : 0,
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 0.33
        },
        items : [ {
            items : [ {
                name : "creditId",
                hidden : true
            } ]
        }, {
            items : [ {
                name : "_franchiseeId",
                hidden : true
            } ]
        }, {
            items : [ {
                fieldLabel : "是否有贷款",
                xtype : "uxcombo",
                name : "loan",
                hiddenName : "loan",
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "是否自带渠道",
                xtype : "uxcombo",
                name : "ownSource",
                hiddenName : "ownSource",
                store : Q.common.yesOrNotStore,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "是否有担保",
                xtype : "uxcombo",
                name : "guarantee",
                hiddenName : "guarantee",
                store : Q.common.yesOrNotStore,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "股东股份状况",
                xtype : "uxcombo",
                name : "shares",
                hiddenName : "shares",
                store : Q.common.yesOrNotStore,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "首批欠款记录",
                xtype : "uxcombo",
                name : "firstArrears",
                hiddenName : "firstArrears",
                valueField : "value",
                displayField : "name",
                store : Q.Franchisee.selStores.recodeFra,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "补货欠款记录",
                xtype : "uxcombo",
                name : "addArrears",
                hiddenName : "addArrears",
                valueField : "value",
                displayField : "name",
                store : Q.Franchisee.selStores.recodeFra,
                anchor : "90%"
            } ]
        }, {
            items : [ {
                fieldLabel : "赊销不良记录   ",
                xtype : "uxcombo",
                name : "badRecord",
                hiddenName : "badRecord",
                valueField : "value",
                displayField : "name",
                store : Q.Franchisee.selStores.recodeFra,
                anchor : "90%"
            } ]
        } ]
    } ]
};

// 团队档案标签
var gridDtl3 = {
    id : 'gridDtl3',
    tabTitle : "团队档案",
    xtype : 'uxeditorgrid',
    foreignKey : "franchisee_franchiseeId",
    tabClassName : "team",
    sm : {
        singleSelect : false
    },// 列表是否单选
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "teamId",
            hidden : true
        }, {
            dataIndex : "_franchiseeId",
            hidden : true
        }, {
            header : "门店名称",
            dataIndex : "name",
            width : 150,
            editor : {
                maxLength : 100,
                xtype : 'textfield',
                listeners : {
                    render : function(p) {
                        p.getEl().on('click', function(p) {
                            if (vp.editWin.formPanel.getForm().findField("model.fraType").disabled) {
                                return;
                            }
                            var _win = new Q.store.chooseStoreWin();
                            _win.on("submit", function(r) {
                                var row = Ext.getCmp("gridDtl3").getSelectionModel().getSelected();
                                row.set("name", r.get("name"));
                            });
                            _win.show();
                        });
                    }
                }
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "姓名",
            dataIndex : "tname",
            editor : {
                maxLength : 100,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "岗位名称",
            dataIndex : "duty",
            editor : {
                maxLength : 100,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "手机号码",
            dataIndex : "mobile",
            editor : {
                xtype : "numberfield",
                regex : /^(1\d{10})$/,
                regexText : "请输入正确的手机号码"
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "是否自招",
            dataIndex : "joinWay",
            editor : {
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        }, {
            header : "入职日期",
            dataIndex : "joinTime",
            editor : {
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "离职日期",
            dataIndex : "outTime",
            editor : {
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "是否托管",
            dataIndex : "free",
            editor : {
                editable : false,
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
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
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "teamId",
        url : dealUrl + "_getRel3.action",
        sort : "teamId",
        autoLoad : false,
        dir : "desc"
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

// 所辖门店
var gridDtl4 = {
    tabTitle : "所辖门店",
    xtype : 'uxeditorgrid',
    foreignKey : "franchisee_franchiseeId",
    tabClassName : "store",
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "storeId",
            hidden : true
        }, {
            dataIndex : "_franchiseeId",
            hidden : true
        }, {
            header : "门店编码",
            dataIndex : "storeNo",
            width : 80
        }, {
            header : "门店名称",
            dataIndex : "name",
            width : 150
        }, {
            header : "门店属性",
            dataIndex : "attrName",
            width : 60
        }, {
            header : "门店形态",
            dataIndex : "formName",
            width : 60
        }, {
            header : "开店日期",
            dataIndex : "shopupDate",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "是否有效",
            dataIndex : "isValid",
            renderer : Q.common.yesOrNotRenderer,
            width : 60
        }, {
            header : "省",
            dataIndex : "provinceName",
            width : 120
        }, {
            header : "市",
            dataIndex : "cityName",
            width : 120
        }, {
            header : "县/区",
            dataIndex : "countyName",
            width : 120
        }, {
            header : "地址",
            dataIndex : "addr",
            width : 300
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "storeId",
        url : dealUrl + "_getRel4.action",
        sort : "storeId",
        autoLoad : false,
        dir : "desc"
    },
    vpAfterRender : function() {
        return 'view';
    }
};

// 合同信息
var gridDtl5 = {
    tabTitle : "合同信息",
    xtype : 'uxeditorgrid',
    tabClassName : "contract",
    cm : {
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "contractId",
            hidden : true
        }, {
            header : "合同编号",
            dataIndex : "contractNum",
            width : 80
        }, {
            header : "合同名称",
            dataIndex : "contractName",
            width : 110
        }, {
            header : "签约日期",
            dataIndex : "signDate",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "生效日期",
            dataIndex : "effDate",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "失效日期",
            dataIndex : "invDate",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "签约地址",
            dataIndex : "addr",
            width : 200
        } ]
    },
    pageSize : 20,
    store : {
        idProperty : "contractId",
        url : path + "/Contract_getJson.action",
        sort : "contractId",
        autoLoad : false,
        dir : "desc"
    },
    vpAfterRender : function() {
        return 'view';
    }
};

// 互动档案
var gridDtl6 = {
    tabTitle : "互动档案",
    xtype : 'uxeditorgrid',
    foreignKey : "franchisee_franchiseeId",
    tabClassName : "fraPartIn",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "fraPartInId",
            hidden : true
        }, {
            dataIndex : "_franchiseeId",
            hidden : true
        }, {
            header : "活动单号",
            dataIndex : "activity.activityNo",
            width : 80
        }, {
            header : "活动类型",
            dataIndex : "activity.activityTypeName",
            width : 60
        }, {
            header : "活动主题",
            dataIndex : "activity.activityTheme",
            width : 200
        }, {
            header : "开始日期",
            dataIndex : "activity.beginTime",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "结束日期",
            dataIndex : "activity.endTime",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "发起人员",
            dataIndex : "activity.launchor",
            width : 80
        }, {
            header : "发起部门",
            dataIndex : "activity.launchDept",
            width : 150
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "fraPartInId",
        url : dealUrl + "_getRel5.action",
        sort : "fraPartInId",
        autoLoad : false,
        dir : "desc"
    },
    vpAfterRender : function() {
        return 'view';
    }
};

var gridDtl7 = {
    tabTitle : "销售订单",
    xtype : 'uxeditorgrid',
    tabClassName : "saleorder",
    cm : {
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "订单ID",
            dataIndex : "orderId",
            disabled : true
        }, {
            header : "订单类型",
            dataIndex : "type",
            valueField : "value",
            displayField : "name",
            store : Q.Franchisee.selStores.typeSale,
            renderer : function(v, m, r) {
                return Q.common.getSelText(Q.Franchisee.selStores.typeSale, v);
            }
        }, {
            header : "订单号码",
            dataIndex : "orderNo",
            width : 80
        }, {
            header : "订单日期",
            dataIndex : "orderDate",
            renderer : Q.common.dateRenderer,
            width : 80
        }, {
            header : "门店编码",
            dataIndex : "storeNo",
            width : 80
        }, {
            header : "门店名称",
            dataIndex : "storeName",
            width : 200
        }, {
            header : "销售金额",
            dataIndex : "totalAmount",
            width : 80
        } ]
    },
    pageSize : 20,
    store : {
        idProperty : "orderId",
        url : path + "/SaleOrder_getJson.action",
        sort : "orderId",
        dir : "desc",
        autoLoad : false
    },
    vpAfterRender : function() {
        return 'view';
    }
};

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '加盟商管理',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        triggerField : "fraCode",
        subTab : [],
        store : {
            idProperty : 'franchiseeId',
            url : dealUrl + '_getJson.action',
            dir : 'franchiseeId',
            dir : "desc"
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                return true;
            }
        }, {
            'view' : true
        }, {
            'delete' : true
        } ],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.fraType").disabled = true;
            vp.editWin.showTabItem("contract");
            vp.editWin.showTabItem("store");
            vp.editWin.showTabItem("fraPartIn");
            vp.editWin.showTabItem("saleorder");
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("contract");
            vp.editWin.hideTabItem("store");
            vp.editWin.hideTabItem("fraPartIn");
            vp.editWin.hideTabItem("saleorder");
            vp.editWin.setActiveTab([ "contract", "store", "fraPartIn", "saleorder" ]);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            items : editFormItems,
            columnWidth : 0.33,
            height : 300,
            setFormValueAfter : function(formPanel) {
                var fraCode = formPanel.getForm().findField("model.fraCode").getValue();
                var contractgrid = vp.editWin.getCompByTabClassName("contract");
                var saleordergrid = vp.editWin.getCompByTabClassName("saleorder");

                contractgrid.store.baseParams = {
                    "filter_EQ_partyNum" : fraCode
                };
                contractgrid.getStore().load({
                    params : {
                        start : 0,
                        limit : 20
                    }
                });
                saleordergrid.store.baseParams = {
                    "filter_EQ_fraCode" : fraCode
                };
                saleordergrid.getStore().load({
                    params : {
                        start : 0,
                        limit : 20
                    }
                });
            }
        },
        centerTab : {
            items : [ gridDtl1, formdtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 230,
        width : 330,
        form : {
            labelWidth : 80,
            columnWidth : 1,
            items : [ {
                fieldLabel : '加盟商编码',
                name : "filter_LIKE_fraCode",
                xtype : "textfield"
            }, {
                fieldLabel : '加盟商名称',
                name : "filter_LIKE_fraName",
                xtype : "textfield"
            }, {
                fieldLabel : '加盟商简称',
                name : "filter_LIKE_shortName",
                xtype : "textfield"
            }, {
                fieldLabel : '加盟商类型',
                hiddenName : "filter_EQ_fraType",
                xtype : 'uxcombo',
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.Franchisee.selStores.typeFra,
                clearable : true
            }, {
                fieldLabel : '业务部门',
                hiddenName : "filter_EQ_sources",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.Franchisee.selStores.sourceFra,
                clearable : true
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