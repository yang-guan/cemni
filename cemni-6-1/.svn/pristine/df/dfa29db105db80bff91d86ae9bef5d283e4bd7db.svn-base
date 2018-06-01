/**
 * 异业伙伴管理
 * 
 * @author zzy
 * @date 2016年11月28日 下午4:13:12
 */
Ext.ns('Q.partner');
var dealUrl = path + '/Partner';

Q.partner.selStores = {
    // 异业伙伴类型
    typeStore : Q.common.selDict(1600),
    // 性别
    sexFra : Q.common.selDict(1100)
};

// 列表
var gridColumn = [ {
    header : "异业伙伴编码",
    width : 10,
    dataIndex : "partnerno"
}, {
    header : "异业伙伴名称",
    width : 40,
    dataIndex : "partnername"
}, {
    header : "异业伙伴简称",
    width : 25,
    dataIndex : "name"
}, {
    header : "异业伙伴类型",
    width : 15,
    dataIndex : "typeName"
}, {
    header : "创建日期",
    width : 10,
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    labelWidth : 100,
    anchor : "-3",
    columnWidth : 1,
    xtype : 'fieldset',
    title : '基本信息',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.partnerid',
            xtype : "hidden",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '异业伙伴编码<font color="red">*</font>',
            name : 'model.partnerno',
            emptyText : '系统自动生成',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '异业伙伴名称<font color="red">*</font>',
            name : 'model.partnername',
            allowBlank : false,
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '异业伙伴简称<font color="red">*</font>',
            allowBlank : false,
            anchor : "85%",
            name : 'model.name',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '异业伙伴类型<font color="red">*</font>',
            hiddenName : 'model.type',
            anchor : "85.5%",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            allowBlank : false,
            emptyText : '请选择',
            store : Q.partner.selStores.typeStore
        }
    }, {
        items : {
            fieldLabel : '实际控制人',
            name : 'model.person',
            anchor : "85%",
            xtype : "textfield"
        }
    } ]
}, {
    labelWidth : 100,
    anchor : "-3",
    columnWidth : 1,
    xtype : 'fieldset',
    title : '拓展信息',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '增值税登记号',
            name : 'model.vatno',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '营业执照编号',
            name : 'model.licenceno',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '社会信用代码',
            name : 'model.creditCode',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '组织机构代码',
            name : 'model.orgNo',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '法人代表',
            name : 'model.corporate',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '所在城市',
            name : 'model.city',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '业务联系人',
            name : 'model.business',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '联系电话',
            regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
            regexText : "请输入正确的电话号码",
            name : 'model.telephone',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '公司地址',
            name : 'model.address',
            xtype : "textfield",
            anchor : "94%"
        }
    } ]
} ];

var gridDtl1 = {
    tabTitle : "联系人信息",
    xtype : 'uxeditorgrid',
    foreignKey : "partner_partnerid",
    tabClassName : "contact",
    viewConfig : {
        autoScroll : true
    },
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "contactId",
            hidden : true
        }, {
            dataIndex : "_partnerid",
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
                editable : false,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                emptyText : '请选择',
                store : Q.partner.selStores.sexFra
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.partner.selStores.sexFra, v);
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
            width : 250,
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
                regexText : "请输入正确的电话号码",
                xtype : 'textfield'
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
            dataIndex : "qq",
            width : 90,
            editor : {
            	maxLength : 20,
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
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "是否创建短信",
            dataIndex : "isSms",
            width : 90,
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
            width : 200,
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
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]
};

var gridDtl2 = {
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
            renderer : Q.common.dateRenderer,
            dataIndex : "signDate",
            width : 80
        }, {
            header : "生效日期",
            renderer : Q.common.dateRenderer,
            dataIndex : "effDate",
            width : 80
        }, {
            header : "失效日期",
            renderer : Q.common.dateRenderer,
            dataIndex : "invDate",
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

// 异业联盟活动
var gridDtl3 = {
    tabTitle : "异业联盟活动",
    xtype : 'uxeditorgrid',
    foreignKey : "partner_partnerid",
    tabClassName : "parPartIn",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "parPartInId",
            hidden : true
        }, {
            dataIndex : "_partnerid",
            hidden : true
        }, {
            header : "活动单号",
            dataIndex : "activity.activityNo"
        }, {
            header : "活动主题",
            dataIndex : "activity.activityTheme"
        }, {
            header : "活动类型",
            dataIndex : "activity.activityTypeName"
        }, {
            header : "开始日期",
            dataIndex : "activity.beginTime",
            renderer : Q.common.dateRenderer,
        }, {
            header : "结束日期",
            dataIndex : "activity.endTime",
            renderer : Q.common.dateRenderer,
        }, {
            header : "发起人员",
            dataIndex : "activity.launchor"
        }, {
            header : "发起部门",
            dataIndex : "activity.launchDept"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "parPartInId",
        url : dealUrl + "_getRel3.action",
        sort : "parPartInId",
        autoLoad : false,
        dir : "desc"
    },
    vpAfterRender : function() {
        return 'view';
    }
};

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '异业合作伙伴',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'partnerid',
            url : dealUrl + '_getJson.action',
            dir : "desc",
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                return true;
            }
        }, {
            'view' : true
        }, {
            'delete' : function(r) {
                return true;
            }
        } ],
        addAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("contract");
            vp.editWin.hideTabItem("parPartIn");
            vp.editWin.setActiveTab([ "contract", "parPartIn" ]);
        },
        viewAfter : function(grid, selectids, win) {
            vp.editWin.showTabItem("parPartIn");
            vp.editWin.showTabItem("contract");
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("contract");
            vp.editWin.hideTabItem("parPartIn");
            vp.editWin.setActiveTab([ "contract", "parPartIn" ]);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            items : editFormItems,
            columnWidth : 0.5,
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _store = vp.editWin.getCompByTabClassName("contract").getStore();
                _store.baseParams = {
                    "filter_EQ_partyNum" : formPanel.getForm().findField("model.partnerno").getValue()
                };
                _store.load();
            }
        },
        centerTab : {
            items : [ gridDtl1, gridDtl3, gridDtl2 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 220,
        width : 350,
        form : {
            labelWidth : 90,
            columnWidth : 1,
            items : [ {
                fieldLabel : '异业伙伴编码',
                name : "filter_LIKE_partnerno",
                xtype : "textfield"
            }, {
                fieldLabel : '异业伙伴名称',
                name : "filter_LIKE_partnername",
                xtype : "textfield"
            }, {
                fieldLabel : '异业伙伴简称',
                name : "filter_LIKE_name",
                xtype : "textfield"
            }, {
                fieldLabel : '异业伙伴类型',
                hiddenName : "filter_EQ_type",
                clearable : true,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.partner.selStores.typeStore

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