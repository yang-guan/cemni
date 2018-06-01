/**
 * 加盟商盈利状况
 * 
 * @author：WangYuanJun
 * @date：2016年12月26日 下午16:08:29
 */
Ext.ns('Q.expandbusi');
var dealUrl = path + '/FranchiseeProfit';
var jsArr = [ path + "/js/archive/franchisee/Q.archive.chooseFranWin.js", path + "/js/console/store/Q.store.chooseStoreWin.js" ];
Q.loadJs(jsArr);

Q.expandbusi.selStores = {
    // 加盟商类型
    typeFraStore : Q.common.selDict(1300),
    // 业务部门
    sourceFraStore : Q.common.selDict(1302)
};
var allSelectedRecords = [];

// 列表
var gridColumn = [ {
    header : "加盟商编码",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraCode;
        }
    }
}, {
    header : "加盟商名称",
    width : 200,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraName;
        }
    }
}, {
    header : "加盟商类别",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.fraTypeName;
        }
    }
}, {
    header : "业务部门",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.sourcesName;
        }
    }
}, {
    header : "业务人员",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.intMan;
        }
    }
}, {
    header : "城市",
    width : 150,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.city;
        }
    }
}, {
    header : "法人代表",
    width : 123,
    dataIndex : "franchisee",
    renderer : function(v) {
        if (!!v) {
            return v.legalre;
        }
    }
}, {
    header : "创建日期",
    width : 120,
    dataIndex : "createDate",
    renderer : Q.common.dateRenderer
} ];

var editFormItems = [ {
    name : 'model.franchiseeProfitId',
    xtype : "hidden"
}, {
    name : 'model.franchisee.franchiseeId',
    xtype : "hidden"
}, {
    fieldLabel : '加盟商编码<font color="red">*</font>',
    allowBlank : false,
    name : 'model.franchisee.fraCode',
    xtype : "textfield",
    readOnly : true,
    anchor : "85%",
    listeners : {
        render : function(p) {
            p.getEl().on('click', function(p) {
                if (vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled) {
                    return;
                }
                var _win = new Q.archive.chooseFranWin();
                _win.on("submit", function(r) {
                    var json = r.json;
                    var _form = vp.editWin.formPanel.getForm();
                    _form.findField('model.franchisee.franchiseeId').setValue(json.franchiseeId);
                    _form.findField('model.franchisee.fraCode').setValue(json.fraCode);
                    _form.findField('model.franchisee.fraName').setValue(json.fraName);

                    if (json.sourcesName == null || json.sourcesName == '') {
                        _form.findField('model.franchisee.sourcesName').setValue('');
                    } else {
                        _form.findField('model.franchisee.sourcesName').setValue(json.sourcesName);
                    }
                    if (json.fraTypeName == null || json.fraTypeName == '') {
                        _form.findField('model.franchisee.fraTypeName').setValue('');
                    } else {
                        _form.findField('model.franchisee.fraTypeName').setValue(json.fraTypeName);
                    }
                    _form.findField('model.franchisee.intMan').setValue(json.intMan);
                    _form.findField('model.franchisee.city').setValue(json.city);
                    _form.findField('model.franchisee.legalre').setValue(json.legalre);
                });
                _win.show();
            });
        }
    }
}, {
    fieldLabel : '加盟商名称<font color="red">*</font>',
    allowBlank : false,
    xtype : "textfield",
    name : 'model.franchisee.fraName',
    anchor : "85%",
    readOnly : true
}, {
    fieldLabel : '加盟商类别',
    name : 'model.franchisee.fraTypeName',
    anchor : "85%",
    xtype : "textfield",
    readOnly : true
}, {
    fieldLabel : '业务部门',
    name : 'model.franchisee.sourcesName',
    anchor : "85%",
    xtype : "textfield",
    readOnly : true
}, {
    fieldLabel : '业务人员',
    xtype : "textfield",
    name : 'model.franchisee.intMan',
    anchor : "85%",
    readOnly : true
}, {
    fieldLabel : '城市',
    xtype : "textfield",
    name : 'model.franchisee.city',
    anchor : "85%",
    readOnly : true
}, {
    fieldLabel : '法人代表',
    xtype : "textfield",
    name : 'model.franchisee.legalre',
    anchor : "85%",
    readOnly : true
}, {
    name : 'model.customerPattern',
    xtype : 'hidden'
}, {
    fieldLabel : '门店名称',
    xtype : "textfield",
    name : 'model.storeName',
    maxLength : 50,
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
                var _form = vp.editWin.formPanel.getForm();
                if (_form.findField("model.storeName").disabled) {
                    return;
                }
                var _win = new Q.store.chooseStoreWin({
                    singleSelect : false,
                    action : 'franchiseeProfit',
                    franchiseeId : _form.findField("model.franchisee.franchiseeId").getValue(),
                    storeScope : _form.findField("model.customerPattern").getValue()
                });
                _win.on("submit", function(r) {
                    var arrName = [];
                    var arrStoreId = [];
                    Ext.each(r, function(re) {
                        arrName.push(re.get("name"));
                        arrStoreId.push(re.get("storeId"));
                    });
                    _form.findField("model.storeName").setValue(arrName.join(','));
                    _form.findField("model.customerPattern").setValue(arrStoreId.join(','));
                });
                _win.show();
            });
        }
    }
}, {
    fieldLabel : '平衡年限预测',
    xtype : "textfield",
    name : 'model.balanceLifeTest',
    maxLength : 50,
    anchor : "85%"
}, {
    columnWidth : 1,
    fieldLabel : '备注 ',
    xtype : "textfield",
    name : 'model.remark',
    maxLength : 200,
    anchor : "94%"
} ];

// 查询表单
var searchFormItems = [ {
    fieldLabel : '加盟商名称',
    name : "filter_LIKE_franchisee_fraName",
    xtype : "textfield"
}, {
    fieldLabel : '加盟商类别',
    hiddenName : "filter_EQ_franchisee_fraType",
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    anchor : "95.6%",
    store : Q.expandbusi.selStores.typeFraStore,
    clearable : true,
}, {
    fieldLabel : '业务部门',
    hiddenName : "filter_EQ_franchisee_sources",
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    anchor : "95.6%",
    store : Q.expandbusi.selStores.sourceFraStore,
    clearable : true,
}, {
    fieldLabel : '业务人员',
    name : "filter_LIKE_franchisee_intMan",
    xtype : "textfield"
}, {
    fieldLabel : '引入城市',
    name : "filter_LIKE_franchisee_city",
    xtype : "textfield"
}, {
    fieldLabel : '法人代表',
    name : "filter_LIKE_franchisee_legalre",
    xtype : "datefield",
    xtype : "textfield"
}, {
    fieldLabel : '创建日期',
    name : "filter_GE_createDate",
    xtype : "datefield",
    format : "Y-m-d",
    editable : false,
    id : "createBeginDate",
    listeners : {
        select : function() {
            var createBeginDate = Ext.getCmp('createBeginDate').getValue();
            var createEndDate = Ext.getCmp("createEndDate").getValue();
            if (createBeginDate && createEndDate) {
                if (createEndDate < createBeginDate) {
                    Q.warning("创建日期不能大于结束日期");
                    Ext.getCmp('createBeginDate').setValue("");
                }
            }
        }
    }
}, {
    fieldLabel : '到',
    name : "filter_LE_createDate",
    xtype : "datefield",
    format : "Y-m-d",
    editable : false,
    id : "createEndDate",
    listeners : {
        select : function() {
            // Ext.getCmp：配合ID使用：获取对象
            var createBeginDate = Ext.getCmp('createBeginDate').getValue();
            var createEndDate = Ext.getCmp("createEndDate").getValue();
            if (createBeginDate && createEndDate) {
                if (createEndDate < createBeginDate) {
                    Q.warning("结束日期不能小于创建日期");
                    Ext.getCmp('createEndDate').setValue("");
                }
            }
        }
    }
} ];

// -----------------------------detail----------------------------
var gridDtl1 = {
    tabTitle : "开店成本",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeProfit_franchiseeProfitId",
    tabClassName : "shopCost",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "开店成本ID",
            dataIndex : "shopCostId",
            disabled : true
        }, {
            header : "加盟商盈利状况ID",
            dataIndex : "_franchiseeProfitId",
            disabled : true
        }, {
            header : "加盟费",
            dataIndex : "joinfee",
            editor : {
                name : "joinfee",
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "保证金",
            dataIndex : "bond",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "装修费",
            dataIndex : "decorationCost",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "信息费",
            dataIndex : "infoCost",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "道具费",
            dataIndex : "propCost",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "物料费",
            dataIndex : "materielCost",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "房租",
            dataIndex : "rent",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "工资",
            dataIndex : "wages",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "活动",
            dataIndex : "activity",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "首批拿货",
            dataIndex : "firstTakeGoods",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "累计拿货",
            dataIndex : "cumulativeTakeGoods",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "总和",
            dataIndex : "total",
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "shopCostId",
        url : dealUrl + "_getShopCost.action",
        sort : "shopCostId",
        autoLoad : false,
        dir : "asc"
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

var gridDtl2 = {
    tabTitle : "拓展成本",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeProfit_franchiseeProfitId",
    tabClassName : "expandCost",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "拓展成本ID",
            dataIndex : "expandCostId",
            disabled : true
        }, {
            header : "加盟商盈利状况ID",
            dataIndex : "_franchiseeProfitId",
            disabled : true
        }, {
            header : "人员",
            dataIndex : "person",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "公关",
            dataIndex : "publicRelations",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "活动",
            dataIndex : "activity",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "总和",
            dataIndex : "total",
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remake",
            editor : {
                maxLength : 50,
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "expandCostId",
        url : dealUrl + "_getExpandCost.action",
        sort : "expandCostId",
        autoLoad : false,
        dir : "asc"
    },
    tbar : [ {
        Qtext : "添加",
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.addDetail(grid);
        }
    }, {
        Qtext : "删除",
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.deleteDetail(grid);
        }
    } ]
};

var gridDtl3 = {
    tabTitle : "收入",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeProfit_franchiseeProfitId",
    tabClassName : "revenue",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "收入ID",
            dataIndex : "revenueId",
            disabled : true
        }, {
            header : "加盟商盈利状况ID",
            dataIndex : "_franchiseeProfitId",
            disabled : true
        }, {
            header : "销售",
            dataIndex : "sale",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "返利",
            dataIndex : "rebate",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "总和",
            dataIndex : "total",
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remake",
            editor : {
                maxLength : 50,
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "revenueId",
        url : dealUrl + "_getRevenue.action",
        sort : "revenueId",
        autoLoad : false,
        dir : "asc"
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

var gridDtl4 = {
    tabTitle : "盈利",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeProfit_franchiseeProfitId",
    tabClassName : "profit",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "盈利ID",
            dataIndex : "profitId",
            disabled : true
        }, {
            header : "加盟商盈利状况ID",
            dataIndex : "_franchiseeProfitId",
            disabled : true
        }, {
            header : "盈利",
            dataIndex : "profit",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "ROI",
            dataIndex : "roi",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remake",
            editor : {
                maxLength : 50,
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "profitId",
        url : dealUrl + "_getProfit.action",
        sort : "profitId",
        autoLoad : false,
        dir : "asc"
    },
    tbar : []
};

var gridDtl5 = {
    tabTitle : "公司支持成本",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeProfit_franchiseeProfitId",
    tabClassName : "costSupport",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "公司支持成本ID",
            dataIndex : "costSupportId",
            disabled : true
        }, {
            header : "加盟商盈利状况ID",
            dataIndex : "_franchiseeProfitId",
            disabled : true
        }, {
            header : "人员支持",
            dataIndex : "personSupport",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "活动支持",
            dataIndex : "activitySupport",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "道具支持",
            dataIndex : "propSupport",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "总和",
            dataIndex : "total",
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remake",
            editor : {
                maxLength : 50,
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "costSupportId",
        url : dealUrl + "_getCostSupport.action",
        sort : "costSupportId",
        autoLoad : false,
        dir : "asc"
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

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '加盟商盈利状况',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'franchiseeProfitId',
            url : dealUrl + '_getJson.action',
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
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = true;
            vp.editWin.formPanel.getForm().findField("model.storeName").disabled = true;
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.storeName").disabled = false;
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
            vp.editWin.formPanel.getForm().findField("model.storeName").disabled = false;
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        listeners : {},
        form : {
            labelWidth : 80,
            items : editFormItems,
            columnWidth : '0.33',
            height : 200
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 320,
        width : 300,
        form : {
            labelWidth : 70,
            items : searchFormItems,
            columnWidth : '1'
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};