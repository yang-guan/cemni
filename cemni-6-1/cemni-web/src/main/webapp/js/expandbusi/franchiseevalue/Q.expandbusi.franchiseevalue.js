/**
 * 加盟商价值管理
 * 
 * @author：WangYuanJun
 * @date：2016年12月28日 上午10:41:58
 */
Ext.ns('Q.expandbusi');
var dealUrl = path + '/FranchiseeValue';
var jsArr = [ path + "/js/archive/franchisee/Q.archive.chooseFranWin.js" ];
Q.loadJs(jsArr);

Q.expandbusi.selStores = {
    // 加盟商类型
    typeFraStore : Q.common.selDict(1300),
    // 业务部门
    sourceFraStore : Q.common.selDict(1302)
};

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
    name : 'model.franchiseeValueId',
    xtype : "hidden"
}, {
    name : 'model.franchisee.franchiseeId',
    xtype : "hidden"
}, {
    fieldLabel : '加盟商编码<font color="red">*</font>',
    allowBlank : false,
    name : 'model.franchisee.fraCode',
    xtype : "textfield",
    anchor : "85%",
    readOnly : true,
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
    fieldLabel : '法人代表',
    xtype : "textfield",
    name : 'model.franchisee.legalre',
    anchor : "85%",
    readOnly : true
}, {
    fieldLabel : '城市',
    xtype : "textfield",
    name : 'model.franchisee.city',
    anchor : "85%",
    readOnly : true
}, {
    fieldLabel : '备注 ',
    xtype : "textfield",
    name : 'model.remark',
    anchor : "85%",
    maxLength : 50
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

var gridDtl1 = {
    tabTitle : "价值分析",
    xtype : 'uxeditorgrid',
    foreignKey : "franchiseeValue_franchiseeValueId",
    tabClassName : "storeCost",
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
            dataIndex : "storeCostId",
            disabled : true
        }, {
            header : "加盟商价值管理ID",
            dataIndex : "_franchiseeValueId",
            disabled : true
        }, {
            header : "合同年限",
            dataIndex : "contractLife",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "盈亏平衡年限",
            dataIndex : "breakEvenAge",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "公司支持成本",
            dataIndex : "costSupport",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "整体拿货成本",
            dataIndex : "takeGoodsCost",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "借货免息",
            dataIndex : "borrowFree",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "借款免息",
            dataIndex : "loanFree",
            editor : {
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
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
            header : "汰换原因 ",
            dataIndex : "eliminateReason",
            editor : {
                maxLength : 50,
                xtype : 'textfield',
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
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "storeCostId",
        url : dealUrl + "_getStoreCost.action",
        sort : "storeCostId",
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

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '加盟商价值',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'franchiseeValueId',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        },
        listEditStateFn : [],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = true;
        },
        addAfter : function(grid, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.franchisee.fraCode").disabled = false;
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 80,
            items : editFormItems,
            columnWidth : '0.33',
            height : 200
        },
        centerTab : {
            items : [ gridDtl1 ]
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