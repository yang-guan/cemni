Ext.ns('Q.yearTarget');
var dealUrl = path + '/YearTarget';
Q.loadJs(path + "/js/common/Q.excel.uploadWin.js");

Q.yearTarget.store = {
    yearTargetType : Q.common.selDict(5100),
    attrStore : Q.common.selDict(8400)
};

var gridColumn = [ {
    header : "指标单号",
    dataIndex : "yearNum"
}, {
    header : "指标类别",
    dataIndex : "typeName"
}, {
    header : "指标年份",
    dataIndex : "partYear"
}, {
    header : "指标销售总金额",
    dataIndex : "allAmount"
}, {
    header : "创建者",
    dataIndex : "createUser"
}, {
    header : "创建时间",
    dataIndex : "createDate",
    renderer : Q.common.timeRenderer
} ];

var editFormItems = [ {
    name : 'model.yearId',
    xtype : "hidden"
}, {
    name : 'model.createUser',
    xtype : "hidden"
}, {
    name : 'model.createDate',
    xtype : "hidden"
}, {
    fieldLabel : '销售指标单号',
    name : 'model.yearNum',
    emptyText : '系统自动生成',
    xtype : "textfield",
    readOnly : true
}, {
    fieldLabel : '指标类别<font color="red">*</font>',
    hiddenName : 'model.type',
    xtype : "uxcombo",
    valueField : "value",
    displayField : "name",
    allowBlank : false,
    store : Q.yearTarget.store.yearTargetType
}, {
    fieldLabel : '指标年份<font color="red">*</font>',
    hiddenName : 'model.partYear',
    xtype : "uxcombo",
    allowBlank : false,
    store : Q.common.yearStore()
}, {
    fieldLabel : '销售总金额',
    name : 'model.allAmount',
    xtype : "numberfield",
    disabled : true
} ];

var gridDtlDetail = {
    tabTitle : "门店指标明细",
    xtype : 'uxeditorgrid',
    foreignKey : "yearTarget_yearId",
    tabClassName : "yearDetails",
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
            dataIndex : "yearDetailsId",
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
            header : "1月",
            dataIndex : "jan",
            allowBlank : false,
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
    store : {
        url : dealUrl + "_getAllDetails.action",
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
            var url = dealUrl + "_export.action?id=" + vp.editWin.formPanel.getForm().findField("model.yearId").getValue();
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
        store : Q.yearTarget.store.attrStore,
        clearable : true,
        width : 120
    }, "-", {
        text : "筛选",
        id : "searchBnt",
        iconCls : "icon-search",
        handler : function() {
            vp.editWin.getCompByTabClassName('yearDetails').getStore().load({
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

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '年度销售指标',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        store : {
            idProperty : 'yearId',
            url : dealUrl + '_getJson.action',
            sort : 'yearId',
            dir : "desc"
        },
        subTab : [],
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
            _form.findField('model.partYear').readOnly = true;

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
            columnWidth : 0.25,
            height : "-1px",
            items : editFormItems
        },
        centerTab : {
            items : [ gridDtlDetail ]
        },
        submitAfter : function() {
            var tabGrid = vp.editWin.getCompByTabClassName('yearDetails');
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