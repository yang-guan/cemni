Ext.ns('Q.individcompanalyze');
var dealUrl = path + '/IndividCompAnalyze';
Q.loadJs(path + "/js/common/Q.excel.uploadWin.js");

Q.individcompanalyze.selStores = {
    // 门店属性
    attrStore : Q.common.selDict(8400)
};

// 列表
var gridColumn = [ {
    header : "指标单号",
    dataIndex : "individCompanalyzeNo"
}, {
    header : "指标年份",
    dataIndex : "year"
}, {
    header : "指标月份",
    dataIndex : "month"
}, {
    header : "创建者",
    dataIndex : "cuser"
}, {
    header : "创建时间",
    renderer : Q.common.timeRenderer,
    dataIndex : "cdate"
} ];

// 表单
var editFormItems = [ {
    name : 'model.individCompanalyzeId',
    xtype : "hidden"
}, {
    name : 'model.cuser',
    xtype : "hidden"
}, {
    name : 'model.cdate',
    xtype : "hidden"
}, {
    name : 'model.excelFlag',
    xtype : "hidden",
    value : 0
}, {
    fieldLabel : '指标单号',
    name : 'model.individCompanalyzeNo',
    xtype : 'textfield',
    anchor : "80%",
    emptyText : '系统自动生成',
    readOnly : true
}, {
    fieldLabel : "指标年份<font color='red'>*</font>",
    hiddenName : 'model.year',
    id : "modelyear",
    xtype : 'uxcombo',
    anchor : "80%",
    allowBlank : false,
    store : Q.common.yearStore()
}, {
    fieldLabel : "指标月份<font color='red'>*</font>",
    hiddenName : 'model.month',
    id : "modelmonth",
    xtype : 'uxcombo',
    anchor : "80%",
    allowBlank : false,
    valueField : "value",
    displayField : "name",
    store : new Ext.data.ArrayStore({
        fields : [ 'value', 'name' ],
        data : [ [ 1, 1 ], [ 2, 2 ], [ 3, 3 ], [ 4, 4 ], [ 5, 5 ], [ 6, 6 ], [ 7, 7 ], [ 8, 8 ], [ 9, 9 ], [ 10, 10 ], [ 11, 11 ], [ 12, 12 ] ]
    })
} ];

var gridfamaily = {
    tabTitle : "个人业绩完成指标",
    xtype : 'uxeditorgrid',
    foreignKey : "individCompAnalyze_individCompanalyzeId",
    tabClassName : "indicators",
    allowEmpty : false,
    pageSize : 20,
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "indicatorsId",
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
            header : "工号",
            dataIndex : "worknumber",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "姓名",
            dataIndex : "workName",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "职位",
            dataIndex : "position",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "岗位级别",
            dataIndex : "levelr",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "人员基数",
            dataIndex : "baseRadix",
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "额度",
            dataIndex : "moneyAmount"
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
            var url = dealUrl + "_downloadTemplate.action?id=" + vp.editWin.formPanel.getForm().findField("model.individCompanalyzeId").getValue();
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

                                worknumber : this.worknumber,
                                workName : this.workName,
                                position : this.position,
                                levelr : this.levelr,
                                baseRadix : this.baseRadix
                            });
                            _store.add(r);
                        });

                        // 导入标识
                        vp.editWin.formPanel.getForm().findField("model.excelFlag").setValue(1);

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
        store : Q.individcompanalyze.selStores.attrStore,
        clearable : true,
        width : 120
    }, "-", {
        text : "筛选",
        id : "searchBnt",
        iconCls : "icon-search",
        handler : function() {
            vp.editWin.getCompByTabClassName('indicators').getStore().load({
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

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '个人业绩完成指标分解',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'individCompanalyzeId',
            url : dealUrl + '_getJson.action',
            sort : 'individCompanalyzeId',
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
            _form.findField('model.year').readOnly = true;
            _form.findField('model.month').readOnly = true;

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