Ext.ns('Q.scaleTarget');
var dealUrl = path + '/ScaleTarget';
Q.loadJs(path + "/js/common/Q.excel.uploadWin.js");

Q.scaleTarget.store = {
    attrStore : Q.common.selDict(8400),
    scaleTypeStore : Q.common.selDict(5201)
};

var gridColumn = [ {
    header : "指标单号",
    dataIndex : "scaleNum"
}, {
    header : "指标年份",
    dataIndex : "partYear"
}, {
    header : "创建者",
    dataIndex : "createUser"
}, {
    header : "创建时间",
    dataIndex : "createDate",
    renderer : Q.common.timeRenderer
} ];

var editFormItems = [ {
    name : 'model.scaleId',
    xtype : "hidden"
}, {
    name : 'model.createUser',
    xtype : "hidden"
}, {
    name : 'model.createDate',
    xtype : "hidden"
}, {
    fieldLabel : '销售指标单号',
    name : 'model.scaleNum',
    emptyText : '系统自动生成',
    xtype : "textfield",
    readOnly : true,
    anchor : "80%"
}, {
    fieldLabel : '指标年份<font color="red">*</font>',
    hiddenName : 'model.partYear',
    xtype : "uxcombo",
    store : Q.common.yearStore(),
    allowBlank : false,
    anchor : "80%"
} ];

var gridDtlDetail = {
    tabTitle : "门店指标明细",
    xtype : 'uxeditorgrid',
    foreignKey : "scaleTarget_scaleId",
    tabClassName : "scaleDetails",
    allowEmpty : false,
    pageSize : 20,
    viewConfig : {
        forceFit : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "scaleDetailsId",
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
            dataIndex : "scaleType",
            disabled : true
        }, {
            header : "分数段等级",
            dataIndex : "scaleTypeName",
            width : 80
        }, {
            header : "1月",
            dataIndex : "jan",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "2月",
            dataIndex : "feb",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "3月",
            dataIndex : "mar",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "4月",
            dataIndex : "apr",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "5月",
            dataIndex : "may",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "6月",
            dataIndex : "jun",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "7月",
            dataIndex : "jul",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "8月",
            dataIndex : "aug",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "9月",
            dataIndex : "sep",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "10月",
            dataIndex : "oct",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "11月",
            dataIndex : "nov",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "12月",
            dataIndex : "dec",
            allowBlank : false,
            disabled : false,
            editor : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2
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
    store : {
        url : dealUrl + "_getAllDetails.action",
        autoLoad : false
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
                        url : dealUrl + "_loadStore.action",
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
                                    attrName : this.attrName,
                                    scaleType : this.dictValue,
                                    scaleTypeName : this.dictName
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
            var url = dealUrl + "_export.action?id=" + vp.editWin.formPanel.getForm().findField("model.scaleId").getValue();
            window.open(url);
        }
    }, {
        text : "导入",
        id : "excelBnt",
        iconCls : "icon-upload",
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
                                scaleType : this.scaleType,
                                scaleTypeName : this.scaleTypeName,

                                jan : this.jan,
                                feb : this.feb,
                                mar : this.mar,
                                apr : this.apr,
                                may : this.may,
                                jun : this.jun,
                                jul : this.jul,
                                aug : this.aug,
                                sep : this.sep,
                                oct : this.oct,
                                nov : this.nov,
                                dec : this.dec
                            });
                            _store.add(r);
                        });

                        // “导入、筛选”按钮二选一
                        Ext.getCmp("orgName").disable().setValue("");
                        Ext.getCmp("attr").disable().setValue("");
                        Ext.getCmp("scaleType").disable().setValue("");
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
        store : Q.scaleTarget.store.attrStore,
        clearable : true,
        width : 120
    }, "-", {
        id : "scaleType",
        xtype : "uxcombo",
        valueField : "value",
        displayField : "name",
        emptyText : "分数段类型",
        store : Q.scaleTarget.store.scaleTypeStore,
        clearable : true,
        width : 120
    }, "-", {
        text : "筛选",
        id : "searchBnt",
        iconCls : "icon-search",
        handler : function() {
            vp.editWin.getCompByTabClassName('scaleDetails').getStore().load({
                params : {
                    filter_LIKE_bigAreaName_OR_LIKE_areaName_OR_LIKE_name : Ext.getCmp("orgName").getValue(),
                    filter_EQ_attr : Ext.getCmp("attr").getValue(),
                    filter_EQ_scaleType : Ext.getCmp("scaleType").getValue()
                }
            });

            // “导入、筛选”按钮二选一
            Ext.getCmp("excelBnt").disable();
        }
    } ]
};

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '分数段销售指标',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        store : {
            idProperty : 'scaleId',
            url : dealUrl + '_getJson.action',
            sort : "scaleId",
            dir : "desc"
        },
        subTab : [],
        listEditStateFn : [],
        addAfter : function() {
            Ext.getCmp("orgName").disable().setValue("");
            Ext.getCmp("attr").disable().setValue("");
            Ext.getCmp("scaleType").disable().setValue("");
            Ext.getCmp("searchBnt").disable();
            Ext.getCmp('loadStoreBtn').setVisible(true);
        },
        editAfter : function() {
            vp.editWin.formPanel.getForm().findField('model.partYear').readOnly = true;

            Ext.getCmp("orgName").enable().setValue("");
            Ext.getCmp("attr").enable().setValue("");
            Ext.getCmp("scaleType").enable().setValue("");
            Ext.getCmp("searchBnt").enable();
            Ext.getCmp('loadStoreBtn').setVisible(false);
        },
        viewAfter : function() {
            Ext.getCmp("orgName").enable().setValue("");
            Ext.getCmp("attr").enable().setValue("");
            Ext.getCmp("scaleType").enable().setValue("");
            Ext.getCmp("searchBnt").enable();
            Ext.getCmp('loadStoreBtn').setVisible(false);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 200,
            labelAlign : "right",
            columnWidth : 0.5,
            height : "-1px",
            items : editFormItems
        },
        centerTab : {
            items : [ gridDtlDetail ]
        },
        submitAfter : function() {
            var tabGrid = vp.editWin.getCompByTabClassName('scaleDetails');
            tabGrid.getStore().proxy = new Ext.data.HttpProxy({
                url : dealUrl + "_getAllDetails.action"
            });
        }
    },
    vpInstanceAfert : function() {
        vp.editWin.on("show", function() {
            var tbar = vp.editWin.formPanel.getTopToolbar().find("name", "submit")[0];
            tbar.hide();
        });
    }
};