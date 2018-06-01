/**
 * 门店指标明细
 */
Ext.ns('Q.expandbusi');
var dealUrl = path + '/SalesmanAnalyze';

Q.SalesmanAnalyze = {};

Q.SalesmanAnalyze.store = {
    gridStore : {
        idProperty : 'salesmananalyzeId',
        url : dealUrl + '_getJson.action',
        dir : "desc",
        autoLoad : true
    }
};
Q.SalesmanAnalyze.selStores = {
    // 部门
    selDict : new Ext.data.JsonStore({
        url : path + "/Org_getOrgJson.action?filter_EQ_type=2&filter_EQ_isValid=1",
        fields : [ "name", "orgId" ],
        autoLoad : true
    }),
    // 业绩类别
    typeStore : Q.common.selDict(7100)

};
// 列表
var gridColumn = [ {
    header : "指标单号",
    width : 100,
    dataIndex : "salesmananalyzeNo"
}, {
    header : "指标年份",
    width : 100,
    dataIndex : "year"
}, {
    header : "创建者",
    width : 100,
    dataIndex : "cuser"
}, {
    header : "创建日期",
    width : 100,
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    name : 'model.salesmananalyzeId',
    xtype : "hidden"
}, {
    fieldLabel : '指标单号',
    name : 'model.salesmananalyzeNo',
    emptyText : '系统自动生成',
    xtype : "textfield",
    anchor : "65%",
    readOnly : true
}, {
    fieldLabel : '指标年份 <font color="red">*</font>',
    allowBlank : false,
    name : 'model.year',
    xtype : "uxcombo",
    anchor : "66%",
    allowBlank : false,
    store : Q.common.yearStore()
} ];

// 查询表单
var searchFormItems = [ {
    fieldLabel : '指标单号',
    name : "filter_LIKE_salesmananalyzeNo",
    xtype : "textfield"

}, {
    fieldLabel : '指标年份',
    clearable : true,
    name : "filter_EQ_year",
    anchor : "95.6%",
    xtype : "uxcombo",
    store : Q.common.yearStore()
}, {
    fieldLabel : '创建者',
    name : "filter_EQ_cuser",
    xtype : "textfield"
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
        colspan : 6
    }, {
        header : "二月",
        align : "center",
        colspan : 6
    }, {
        header : "三月",
        align : "center",
        colspan : 6
    }, {
        header : "四月",
        align : "center",
        colspan : 6
    }, {
        header : "五月",
        align : "center",
        colspan : 6
    }, {
        header : "六月",
        align : "center",
        colspan : 6
    }, {
        header : "七月",
        align : "center",
        colspan : 6
    }, {
        header : "八月",
        align : "center",
        colspan : 6
    }, {
        header : "九月",
        align : "center",
        colspan : 6
    }, {
        header : "十月",
        align : "center",
        colspan : 6
    }, {
        header : "十一月",
        align : "center",
        colspan : 6
    }, {
        header : "十二月",
        align : "center",
        colspan : 6
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
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    }, {
        header : "指标",
        align : "center",
        colspan : 3
    }, {
        header : "完成情况",
        align : "center",
        colspan : 3
    } ] ]
});
var gridDtl1 = {
    tabTitle : " 门店指标明细",
    xtype : 'uxeditorgrid',
    foreignKey : "salesmananalyze_salesmananalyzeId",
    tabClassName : "storedetail",

    viewConfig : {
        autoScroll : true,
    },
    sm : {
        singleSelect : false
    },// 列表是否单选
    plugins : rows,
    cm : {
        columns : [ {
            header : "门店指标明细",
            dataIndex : "storedetailid",
            disabled : true
        }, {
            header : "业务员ID",
            dataIndex : "_salesmananalyzeId",
            disabled : true
        }, {
            header : "所属部门",
            dataIndex : "dept",
            align : "center",
            width : 150,
            editor : {
                maxLength : 50,
                allowBlank : true,
                xtype : 'uxcombo',
                editable : false,
                valueField : "orgId",
                displayField : "name",
                store : Q.SalesmanAnalyze.selStores.selDict
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.SalesmanAnalyze.selStores.selDict, v, 'name', 'orgId');
            }

        }, {
            header : "业务员姓名",
            dataIndex : "salesman",
            align : "center",
            editor : {
                maxLength : 50,
                allowBlank : true,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "业绩类别",
            dataIndex : "type",
            align : "center",
            editor : {
                maxLength : 50,
                allowBlank : true,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.SalesmanAnalyze.selStores.typeStore,
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.SalesmanAnalyze.selStores.typeStore, v);
                ;
            }
        }, {
            header : "区代开发",
            dataIndex : "janiexploit",
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
            header : "首批铺货",
            dataIndex : "janigoods",
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
            header : "区代开发",
            dataIndex : "janfexploit",
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
            header : "首批铺货",
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
            header : "区代开发",
            dataIndex : "febiexploit",
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
            dataIndex : "febishop",
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
            header : "首批铺货",
            dataIndex : "febigoods",
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
            header : "区代开发",
            dataIndex : "febfexploit",
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
            dataIndex : "febfshop",
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
            header : "首批铺货",
            dataIndex : "febfgoods",
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
            header : "区代开发",
            dataIndex : "mariexploit",
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
            dataIndex : "marishop",
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
            header : "首批铺货",
            dataIndex : "marigoods",
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
            header : "区代开发",
            dataIndex : "marfexploit",
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
            dataIndex : "marfshop",
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
            header : "首批铺货",
            dataIndex : "marfgoods",
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
            header : "区代开发",
            dataIndex : "apriexploit",
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
            dataIndex : "aprishop",
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
            header : "首批铺货",
            dataIndex : "aprigoods",
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
            header : "区代开发",
            dataIndex : "aprfexploit",
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
            dataIndex : "aprfshop",
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
            header : "首批铺货",
            dataIndex : "aprfgoods",
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
            header : "区代开发",
            dataIndex : "mayiexploit",
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
            dataIndex : "mayishop",
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
            header : "首批铺货",
            dataIndex : "mayigoods",
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
            header : "区代开发",
            dataIndex : "mayfexploit",
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
            dataIndex : "mayfshop",
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
            header : "首批铺货",
            dataIndex : "mayfgoods",
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
            header : "区代开发",
            dataIndex : "juniexploit",
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
            dataIndex : "junishop",
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
            header : "首批铺货",
            dataIndex : "junigoods",
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
            header : "区代开发",
            dataIndex : "junfexploit",
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
            dataIndex : "junfshop",
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
            header : "首批铺货",
            dataIndex : "junfgoods",
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
            header : "区代开发",
            dataIndex : "juliexploit",
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
            dataIndex : "julishop",
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
            header : "首批铺货",
            dataIndex : "juligoods",
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
            header : "区代开发",
            dataIndex : "julfexploit",
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
            dataIndex : "julfshop",
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
            header : "首批铺货",
            dataIndex : "julfgoods",
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
            header : "区代开发",
            dataIndex : "augiexploit",
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
            dataIndex : "augishop",
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
            header : "首批铺货",
            dataIndex : "augigoods",
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
            header : "区代开发",
            dataIndex : "augfexploit",
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
            dataIndex : "augfshop",
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
            header : "首批铺货",
            dataIndex : "augfgoods",
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
            header : "区代开发",
            dataIndex : "sepiexploit",
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
            dataIndex : "sepishop",
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
            header : "首批铺货",
            dataIndex : "sepigoods",
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
            header : "区代开发",
            dataIndex : "sepfexploit",
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
            dataIndex : "sepfshop",
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
            header : "首批铺货",
            dataIndex : "sepfgoods",
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
            header : "区代开发",
            dataIndex : "octiexploit",
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
            dataIndex : "octishop",
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
            header : "首批铺货",
            dataIndex : "octigoods",
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
            header : "区代开发",
            dataIndex : "octfexploit",
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
            dataIndex : "octfshop",
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
            header : "首批铺货",
            dataIndex : "octfgoods",
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
            header : "区代开发",
            dataIndex : "noviexploit",
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
            dataIndex : "novishop",
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
            header : "首批铺货",
            dataIndex : "novigoods",
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
            header : "区代开发",
            dataIndex : "novfexploit",
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
            dataIndex : "novfshop",
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
            header : "首批铺货",
            dataIndex : "novfgoods",
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
            header : "区代开发",
            dataIndex : "deciexploit",
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
            dataIndex : "decishop",
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
            header : "首批铺货",
            dataIndex : "decigoods",
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
            header : "区代开发",
            dataIndex : "decfexploit",
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
            dataIndex : "decfshop",
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
            header : "首批铺货",
            dataIndex : "decfgoods",
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
        } ],
    },
    listeners : {
        "beforeedit" : function(e) {// 判断是否

            return vp.editWin.beforeedit(); // 字段编辑前处理语句，无特殊情况这句固定的就ok,

        }
    },
    pageSize : 0,
    store : {
        idProperty : "storedetailid",
        url : dealUrl + "_getRel.action",
        sort : "storedetailid",
        autoLoad : false,
        dir : "desc"
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
    } ],
};

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '业务员业务',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : Q.SalesmanAnalyze.store.gridStore,
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
        editAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.year").setReadOnly(true);
        },
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.year").setReadOnly(true);
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
            columnWidth : '0.5',
            height : 100
        },
        centerTab : {
            items : [ gridDtl1 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 220,
        width : 300,
        form : {
            labelWidth : 80,
            items : searchFormItems,
            columnWidth : '1'
        }
    },
    vpInstanceAfert : function() {
        // 隐藏提交按钮
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};
