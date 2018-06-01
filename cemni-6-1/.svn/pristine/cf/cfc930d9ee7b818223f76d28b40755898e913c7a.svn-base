Ext.ns('Q.memcompanalyze');
var dealUrl = path + '/MemCompAnalyze';
Q.loadJs(path + "/js/common/Q.excel.uploadWin.js");

Q.memcompanalyze.selStores = {
    // 门店属性
    attrStore : Q.common.selDict(8400),
    // 指标类别
    typeChooseStore : Q.common.selDict(2700)
};

// 列表
var gridColumn = [ {
    header : "会员指标单号",
    dataIndex : "memcompanalyzenNo"
}, {
    header : "指标年份",
    dataIndex : "year"
}, {
    header : "指标类别",
    dataIndex : "typeName"
}, {
    header : "创建者",
    dataIndex : "cuser"
}, {
    header : "创建时间",
    dataIndex : "cdate",
    renderer : Q.common.timeRenderer
} ];

// 表单
var editFormItems = [ {
    name : 'model.memcompanalyzeId',
    xtype : "hidden"
}, {
    name : 'model.cuser',
    xtype : "hidden"
}, {
    name : 'model.cdate',
    xtype : "hidden"
}, {
    fieldLabel : '会员指标单号',
    name : 'model.memcompanalyzenNo',
    xtype : 'textfield',
    emptyText : '系统自动生成',
    readOnly : true,
    anchor : "80%"
}, {
    fieldLabel : "指标类别<font color='red'>*</font>",
    hiddenName : 'model.type',
    xtype : 'uxcombo',
    valueField : "value",
    displayField : "name",
    store : Q.memcompanalyze.selStores.typeChooseStore,
    allowBlank : false,
    anchor : "80%"
}, {
    fieldLabel : "指标年份<font color='red'>*</font>",
    hiddenName : 'model.year',
    xtype : 'uxcombo',
    store : Q.common.yearStore(),
    allowBlank : false,
    anchor : "80%"
} ];

var rows = new Ext.ux.grid.ColumnHeaderGroup({
    rows : [ [ {
        align : "center",
        colspan : 1
    }, {
        align : "center",
        colspan : 1
    }, {
        align : "center",
        colspan : 4
    }, {
        header : "一月",
        align : "center",
        colspan : 4
    }, {
        header : "二月",
        align : "center",
        colspan : 4
    }, {
        header : "三月",
        align : "center",
        colspan : 4
    }, {
        header : "四月",
        align : "center",
        colspan : 4
    }, {
        header : "五月",
        align : "center",
        colspan : 4
    }, {
        header : "六月",
        align : "center",
        colspan : 4
    }, {
        header : "七月",
        align : "center",
        colspan : 4
    }, {
        header : "八月",
        align : "center",
        colspan : 4
    }, {
        header : "九月",
        align : "center",
        colspan : 4
    }, {
        header : "十月",
        align : "center",
        colspan : 4
    }, {
        header : "十一月",
        align : "center",
        colspan : 4
    }, {
        header : "十二月",
        align : "center",
        colspan : 4
    } ], [ {
        align : "center",
        colspan : 1
    }, {
        align : "center",
        colspan : 1
    }, {
        align : "center",
        colspan : 4
    },{
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    }, {
        header : "新会员",
        align : "center",
        colspan : 2
    }, {
        header : "老会员",
        align : "center",
        colspan : 2
    } ] ]
});

var gridfamaily = {
    tabTitle : "会员完成指标明细",
    xtype : 'uxeditorgrid',
    foreignKey : "memCompAnalyze_memcompanalyzeId",
    tabClassName : "memdetailist",
    allowEmpty : false,
    pageSize : 20,
    viewConfig : {
        forceFit : false
    },
    plugins : rows,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "memdetailId",
            disabled : true
        }, {
            dataIndex : "bigAreaId",
            disabled : true
        }, {
            dataIndex : "bigAreaNo",
            disabled : true
        }, {
            dataIndex : "areaId",
            disabled : true
        }, {
            dataIndex : "areaNo",
            disabled : true
        }, {
            dataIndex : "storeId",
            disabled : true
        }, {
            dataIndex : "storeNo",
            disabled : true
        }, {
            dataIndex : "attr",
            disabled : true
        }, {
            header : "大区",
            dataIndex : "bigAreaName",
            width : 100
        }, {
            header : "区域",
            dataIndex : "areaName",
            width : 100
        }, {
            header : "门店",
            dataIndex : "name",
            width : 200
        }, {
            header : "门店属性",
            dataIndex : "attrName",
            width : 80
        }, {
            Qheader : "一月",
            header : "人数",
            dataIndex : "janipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "janiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "janfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "janfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "二月",
            header : "人数",
            dataIndex : "febipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "febiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "febfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "febfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "3月",
            header : "人数",
            dataIndex : "maripeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "mariprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "marfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "marfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "4月",
            header : "人数",
            dataIndex : "apripeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "apriprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "aprfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "aprfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "5月",
            header : "人数",
            dataIndex : "mayipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "mayiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "mayfpeople",
            editor : {

                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "mayfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "6月",
            header : "人数",
            dataIndex : "junipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "juniprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "junfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "junfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "7月",
            header : "人数",
            dataIndex : "julipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "juliprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "julfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "julfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "8月",
            header : "人数",
            dataIndex : "augipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "augiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "augfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "augfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "9月",
            header : "人数",
            dataIndex : "sepipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "sepiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "sepfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "sepfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "10月",
            header : "人数",
            dataIndex : "octipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "octiprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "octfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "octfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "11月",
            header : "人数",
            dataIndex : "novipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "noviprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "novfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "novfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            Qheader : "12月",
            header : "人数",
            dataIndex : "decipeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "deciprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人数",
            dataIndex : "decfpeople",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "客单价",
            dataIndex : "decfprice",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        } ]
    },
    store : {
        url : dealUrl + "_getDetail.action",
        autoLoad : false
    },
    listeners : {
        beforeedit : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    tbar : [ {
        text : "载入",
        id : "loadStoreBtn",
        iconCls : "icon-add",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            Q.confirm("“载入”时会先清除列表选中的数据，是否继续？", {
                ok : function() {
                    _store.removeAll();
                    Ext.getBody().loadMask();
                    Ext.Ajax.request({
                        url : path + "/Store_qryBigAreaStore.action",
                        success : function(response) {
                            Q.each(Ext.decode(response.responseText), function() {
                                var r = new Ext.data.Record({
                                    bigAreaId : this.bigAreaId,
                                    bigAreaNo : this.bigAreaNo,
                                    bigAreaName : this.bigAreaName,
                                    areaId : this.areaId,
                                    areaNo : this.areaNo,
                                    areaName : this.areaName,
                                    storeId : this.storeId,
                                    storeNo : this.storeNo,
                                    name : this.name,
                                    attr : this.attr,
                                    attrName : this.attrName
                                });
                                _store.add(r);
                            });
                        },
                        callback : function() {
                            Ext.getBody().unmask();
                        }
                    });
                }
            });
        }
    }, {
        text : "模版下载",
        iconCls : "icon-excel",
        handler : function() {
            var url = dealUrl + "_downloadTemplate.action?id=" + vp.editWin.formPanel.getForm().findField("model.memcompanalyzeId").getValue();
            window.open(url);
        }
    }, {
        text : "导入",
        id : "excelBnt",
        iconCls : "icon-excel",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            Q.confirm("“导入”时会先清除列表选中的数据，是否继续？", {
                ok : function() {
                    var _win = new Q.excel.uploadWin({
                        url : dealUrl + "_excel.action"
                    });
                    _win.on("upload", function(action) {
                        _store.removeAll();
                        Q.each(action.result.dataList, function() {
                            var r = new Ext.data.Record({
                                bigAreaId : this.bigAreaId,
                                bigAreaNo : this.bigAreaNo,
                                bigAreaName : this.bigAreaName,
                                areaId : this.areaId,
                                areaNo : this.areaNo,
                                areaName : this.areaName,
                                storeId : this.storeId,
                                storeNo : this.storeNo,
                                name : this.name,
                                attr : this.attr,
                                attrName : this.attrName,

                                janipeople : this.janipeople,
                                janiprice : this.janiprice,
                                janfpeople : this.janfpeople,
                                janfprice : this.janfprice,
                                febipeople : this.febipeople,
                                febiprice : this.febiprice,
                                febfpeople : this.febfpeople,
                                febfprice : this.febfprice,
                                maripeople : this.maripeople,
                                mariprice : this.mariprice,
                                marfpeople : this.marfpeople,
                                marfprice : this.marfprice,
                                apripeople : this.apripeople,
                                apriprice : this.apriprice,
                                aprfpeople : this.aprfpeople,
                                aprfprice : this.aprfprice,
                                mayipeople : this.mayipeople,
                                mayiprice : this.mayiprice,
                                mayfpeople : this.mayfpeople,
                                mayfprice : this.mayfprice,
                                junipeople : this.junipeople,
                                juniprice : this.juniprice,
                                junfpeople : this.junfpeople,
                                junfprice : this.junfprice,
                                julipeople : this.julipeople,
                                juliprice : this.juliprice,
                                julfpeople : this.julfpeople,
                                julfprice : this.julfprice,
                                augipeople : this.augipeople,
                                augiprice : this.augiprice,
                                augfpeople : this.augfpeople,
                                augfprice : this.augfprice,
                                sepipeople : this.sepipeople,
                                sepiprice : this.sepiprice,
                                sepfpeople : this.sepfpeople,
                                sepfprice : this.sepfprice,
                                octipeople : this.octipeople,
                                octiprice : this.octiprice,
                                octfpeople : this.octfpeople,
                                octfprice : this.octfprice,
                                novipeople : this.novipeople,
                                noviprice : this.noviprice,
                                novfpeople : this.novfpeople,
                                novfprice : this.novfprice,
                                decipeople : this.decipeople,
                                deciprice : this.deciprice,
                                decfpeople : this.decfpeople,
                                decfprice : this.decfprice
                            });
                            _store.add(r);
                        });

                        // “导入、筛选”按钮二选一
                        Ext.getCmp("orgName").disable().setValue("");
                        Ext.getCmp("attr").disable().setValue("");
                        Ext.getCmp("searchBnt").disable();
                    });
                    _win.show();
                }
            });
        }
    }, "->", {
        id : "orgName",
        emptyText : "大区/区域/门店",
        xtype : "textfield"
    }, "-", {
        id : "attr",
        xtype : "uxcombo",
        valueField : "value",
        displayField : "name",
        emptyText : "门店属性",
        store : Q.memcompanalyze.selStores.attrStore,
        clearable : true,
        width : 120
    }, "-", {
        text : "筛选",
        id : "searchBnt",
        iconCls : "icon-search",
        handler : function() {
            vp.editWin.getCompByTabClassName('memdetailist').getStore().load({
                params : {
                    filter_LIKE_bigAreaName_OR_LIKE_areaName_OR_LIKE_name : Ext.getCmp("orgName").getValue(),
                    filter_EQ_attr : Ext.getCmp("attr").getValue()
                }
            });

            // “导入、筛选”按钮二选一
            Ext.getCmp("excelBnt").disable();
        }
    } ]
};

// /////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '会员完成指标分解',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        sactiveTab : 0,
        store : {
            idProperty : 'memcompanalyzeId',
            url : dealUrl + '_getJson.action',
            sort : 'memcompanalyzeId',
            dir : "desc"
        },
        listEditStateFn : [],
        addAfter : function() {
            Ext.getCmp("orgName").disable().setValue("");
            Ext.getCmp("attr").disable().setValue("");
            Ext.getCmp("searchBnt").disable();
            Ext.getCmp('loadStoreBtn').setVisible(true);
        },
        editAfter : function() {
            var _form = vp.editWin.formPanel.getForm();
            _form.findField('model.type').readOnly = true;
            _form.findField('model.year').readOnly = true;

            Ext.getCmp("orgName").enable().setValue("");
            Ext.getCmp("attr").enable().setValue("");
            Ext.getCmp("searchBnt").enable();
            Ext.getCmp('loadStoreBtn').setVisible(false);
        },
        viewAfter : function() {
            Ext.getCmp("orgName").enable().setValue("");
            Ext.getCmp("attr").enable().setValue("");
            Ext.getCmp("searchBnt").enable();
            Ext.getCmp('loadStoreBtn').setVisible(false);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 120,
            labelAlign : "right",
            columnWidth : 0.33,
            height : "-1px",
            items : editFormItems
        },
        centerTab : {
            items : [ gridfamaily ]
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};