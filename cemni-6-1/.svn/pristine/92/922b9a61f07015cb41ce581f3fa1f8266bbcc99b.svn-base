/**
 * 加盟商/区域代理业务分解
 * 
 * @author zzy
 * @date 2016年11月30日 上午9:49:07
 */
Ext.ns('Q.expandbusi');
var dealUrl = path + '/Agentanalyze';
var cfg, jsArr = (path + "/js/archive/franchisee/Q.archive.chooseFranWin.js");
Q.loadJs(jsArr);

Q.Agentanalyze = {};
Q.Agentanalyze.selStores = {
    // 部门
    selDict : new Ext.data.JsonStore({
        url : path + "/Org_getOrgJson.action?filter_EQ_type=2&filter_EQ_isValid=1",
        fields : [ "name", "orgId" ],
        autoLoad : true
    }),
    // 指标类别
    typeStore : Q.common.selDict(7200)
};

// 列表
var gridColumn = [ {
    header : "指标单号",
    dataIndex : "agentanalyzeno"
}, {
    header : "指标类别",
    dataIndex : "typeName"
}, {
    header : "指标年份",
    dataIndex : "year"
}, {
    header : "创建者",
    dataIndex : "cuser"
}, {
    header : "创建日期",
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    name : 'model.agentanalyzeid',
    xtype : "hidden"
}, {
    fieldLabel : '指标单号',
    name : 'model.agentanalyzeno',
    emptyText : '系统自动生成',
    readOnly : true,
    xtype : "textfield",
    anchor : "85%"
}, {
    fieldLabel : '指标类别 <font color="red">*</font>',
    hiddenName : 'model.type',
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    allowBlank : false,
    anchor : "86%",
    emptyText : '请选择',
    store : Q.Agentanalyze.selStores.typeStore,
    listeners : {
        // 判断类别选择项 来确定隐不隐藏加盟商名称
        select : function(v, m, r) {
            var dtlColumn = vp.editWin.getCompByTabClassName('detailed').getColumnModel();
            if (r == 1) {
                dtlColumn.setHidden(3, true);
            } else {
                dtlColumn.setHidden(3, false);
            }
        }
    }
}, {
    fieldLabel : '指标年份<font color="red">*</font>',
    hiddenName : 'model.year',
    anchor : "86%",
    xtype : "uxcombo",
    allowBlank : false,
    store : Q.common.yearStore()
} ];

// 查询表单
var searchFormItems = [ {
    fieldLabel : '指标单号',
    clearable : true,
    name : "filter_LIKE_agentanalyzeno",
    xtype : "textfield"
}, {
    fieldLabel : '指标年份',
    clearable : true,
    name : "filter_EQ_year",
    xtype : "uxcombo",
    anchor : "95.6%",
    store : Q.common.yearStore()
}, {
    fieldLabel : '创建者',
    name : "filter_EQ_cuser",
    xtype : "textfield"
}, {
    fieldLabel : '指标类别',
    hiddenName : "filter_EQ_type",
    clearable : true,
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    anchor : "95.6%",
    store : Q.Agentanalyze.selStores.typeStore
}, {
    fieldLabel : '创建日期',
    name : "filter_GE_cdate",
    editable : false,
    xtype : "datefield",
    format : "Y-m-d",
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
    fieldLabel : '至',
    name : "filter_LE_cdate",
    editable : false,
    xtype : "datefield",
    format : "Y-m-d",
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

var rows = new Ext.ux.grid.ColumnHeaderGroup({
    rows : [ [ {
        header : "",
        align : "center",
        colspan : 1
    }, {
        header : "",
        align : "center",
        colspan : 1
    }, {
        header : "",
        align : "center",
        colspan : 3
    }, {
        header : "一月",
        align : "center",
        colspan : 5
    }, {
        header : "二月",
        align : "center",
        colspan : 5
    }, {
        header : "三月",
        align : "center",
        colspan : 5
    }, {
        header : "四月",
        align : "center",
        colspan : 5
    }, {
        header : "五月",
        align : "center",
        colspan : 5
    }, {
        header : "六月",
        align : "center",
        colspan : 5
    }, {
        header : "七月",
        align : "center",
        colspan : 5
    }, {
        header : "八月",
        align : "center",
        colspan : 5
    }, {
        header : "九月",
        align : "center",
        colspan : 5
    }, {
        header : "十月",
        align : "center",
        colspan : 5
    }, {
        header : "十一月",
        align : "center",
        colspan : 5
    }, {
        header : "十二月",
        align : "center",
        colspan : 5
    } ], [ {
        header : "",
        align : "center",
        colspan : 1
    }, {
        header : "",
        align : "center",
        colspan : 1
    }, {
        header : "",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 2
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    } ] ]
});
var gridDtl1 = {
    id : 'res_Detailed_gridDtl1',
    tabTitle : "加盟商指标明细",
    xtype : 'uxeditorgrid',
    foreignKey : "agentanalyze_agentanalyzeid",
    tabClassName : "detailed",

    viewConfig : {
        autoScroll : true,
    },
    plugins : rows,
    sm : {
        singleSelect : false
    },// 列表是否单选
    cm : {
        columns : [ {
            header : "加盟商明细ID",
            dataIndex : "detailedid",
            disabled : true
        }, {
            header : "加盟商ID",
            dataIndex : "_agentanalyzeid",
            disabled : true
        }, {
            dataIndex : "deptId",
            disabled : true,
            hidden : true
        }, {
            dataIndex : "fraId",
            disabled : true,
            hidden : true
        }, {
            dataIndex : "regionId",
            hidden : true,
            disabled : true
        }, {
            header : "所属部门",
            dataIndex : "dept",
            align : "center",
            width : 150,
            editor : {
                maxLength : 50,
                allowBlank : true,// 是否可以为空
                xtype : 'uxcombo',
                editable : false,
                valueField : "orgId",
                displayField : "name",
                store : Q.Agentanalyze.selStores.selDict
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.Agentanalyze.selStores.selDict, v, 'name', 'orgId');
            }

        }, {
            header : "加盟商名称",
            dataIndex : "franame",
            align : "center",
            width : 200,
            editor : {
                maxLength : 50,
                allowBlank : true,
                xtype : 'textfield',
                readOnly : true,
                listeners : {
                    render : function(p) {
                        p.getEl().on('click', function(p) {
                            var _win = new Q.archive.chooseFranWin();
                            _win.on("submit", function(r) {
                                var row = Ext.getCmp("res_Detailed_gridDtl1").getSelectionModel().getSelected();
                                row.set('franame', r.get("fraName"));
                                row.set('fraId', r.get("franchiseeId"));
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
            header : "所属区代",
            dataIndex : "region",
            align : "center",
            width : 200,
            editor : {
                maxLength : 50,
                allowBlank : true,
                xtype : 'textfield',
                readOnly : true,
                listeners : {
                    render : function(p) {
                        p.getEl().on('click', function(p) {
                            var _win = new Q.archive.chooseFranWin({
                                singleSelect : true,
                                action : 'agentanalyze',
                                fraType : 2
                            });
                            _win.on("submit", function(r) {
                                var row = Ext.getCmp("res_Detailed_gridDtl1").getSelectionModel().getSelected();
                                row.set('region', r.get("fraName"));
                                row.set('fraId', r.get("franchiseeId"));
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
            header : "铺货",
            dataIndex : "janigoods",
            align : "center",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            dataIndex : "janishop",
            align : "center",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            dataIndex : "janfgoods",
            align : "center",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            dataIndex : "janfshop",
            align : "center",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "janfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "febigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "febishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "febfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "febfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "febfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "marigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "marishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "marfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "marfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "marfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "aprigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "aprishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "aprfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "aprfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "aprfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "mayigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "mayishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "mayfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "mayfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "mayfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "junigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "junishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "junfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "junfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "junfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "juligoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "julishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "julfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "julfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "julfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "augigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "augishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "augfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "augfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "augfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "sepigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "sepishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "sepfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "sepfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "sepfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "octigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "octishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "octfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "octfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "octfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "novigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "novishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "novfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "novfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "novfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "decigoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "decishop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "铺货",
            align : "center",
            dataIndex : "decfgoods",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "开店数",
            align : "center",
            dataIndex : "decfshop",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "年度累计拿货额",
            align : "center",
            dataIndex : "decfgoodstake",
            editor : {
                regex : /^[0-9]+$/,
                regexText : "请输入整数",
                maxLength : 50,
                allowBlank : true,
                xtype : 'numberfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ],
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "detailedid",
        url : dealUrl + "_getRel.action",
        sort : "detailedid",
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
    } ],
    loadValueAfter : function(grid, store, callbackRecords) {
        // 判断点击的类别是区域代理还是加盟商 来选择隐藏表头
        var dtlColumn = vp.editWin.getCompByTabClassName('detailed').getColumnModel();
        if (vp.grid.getSelectionModel().getSelected().get("typeName") == '区域代理指标') {
            dtlColumn.setHidden(3, true);
        } else {
            dtlColumn.setHidden(3, false);
        }
    }
};

// //////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '加盟商/区域代理',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'agentanalyzeid',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        },
        listEditStateFn : [],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.year").setReadOnly(true);
            vp.editWin.formPanel.getForm().findField("model.type").setReadOnly(true);
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.year").setReadOnly(true);
            vp.editWin.formPanel.getForm().findField("model.type").setReadOnly(true);
        }
    },
    editWin : {
        autoScroll : true,
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 70,
            items : editFormItems,
            columnWidth : '0.33',
            height : 100
        },
        centerTab : {
            items : [ gridDtl1 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 240,
        width : 300,
        form : {
            labelWidth : 80,
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