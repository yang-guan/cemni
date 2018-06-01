Ext.ns("Q.telvisit");
var dealUrl = path + '/TelVisit';

Q.telvisit.selStores = {
    // 回访方式
    backfsStore : Q.common.selDict(2101),
    // 性别
    sexStore : Q.common.selDict(1100),
    // 使用状态
    enableStore : Q.common.selDict(1102),
    // 活跃状态
    activeStore : Q.common.selDict(1103),
    // 会员等级
    levelStore : Q.common.selDict(3100),
    // 产品分数段
    cpScoreStore : Q.common.selDict(9800),
    // 存货分类
    stockTypeStore : Q.common.selDict(9801),
    // 产品设计师款
    designerStore : Q.common.selDict(2200),
    // 客户类型
    typeStore : Q.common.selDict(1101),
    // 新老会员标识
    freshStore : Q.common.selDict(1114),
    // 任务类型
    taskTypeStore : Q.common.selDict(2107)
};

// 列表
var gridColumn = [ {
    dataIndex : "telVisitId",
    hidden : true
}, {
    dataIndex : "publishzt",
    hidden : true
}, {
    header : "回访任务单号",
    dataIndex : "telVisitNo"
}, {
    header : "回访方式",
    dataIndex : "backfsName"
}, {
    header : "任务类型",
    dataIndex : "taskTypeName"
}, {
    header : "回访开始日期",
    dataIndex : "startrq",
    renderer : Q.common.dateRenderer
}, {
    header : "回访结束日期",
    dataIndex : "endrq",
    renderer : Q.common.dateRenderer
}, {
    header : "是否已发布",
    dataIndex : "publishztName"
}, {
    header : "创建人",
    dataIndex : "cuser"
}, {
    header : "创建日期",
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    name : 'model.queryParamsStr',
    hidden : true
}, {
    name : 'model.telVisitId',
    hidden : true
}, {
    name : "model.publishzt",
    hidden : true,
    value : 0
}, {
    fieldLabel : "回访任务单号",
    name : 'model.telVisitNo',
    xtype : 'textfield',
    emptyText : '系统自动生成',
    readOnly : true,
    anchor : "85%"
}, {
    fieldLabel : "回访方式",
    hiddenName : 'model.backfs',
    id : "modelbackfs",
    xtype : 'uxcombo',
    value : 1,
    anchor : "86%",
    valueField : "value",
    displayField : "name",
    store : Q.telvisit.selStores.backfsStore
}, {
    fieldLabel : "任务类型",
    hiddenName : 'model.taskType',
    xtype : 'uxcombo',
    value : 3,
    anchor : "86%",
    valueField : "value",
    displayField : "name",
    store : Q.telvisit.selStores.taskTypeStore
}, {
    fieldLabel : "回访开始日期<font color='red'>*</font>",
    xtype : 'datefield',
    name : 'model.startrq',
    value : new Date(),
    editable : false,
    allowBlank : false,
    format : 'Y-m-d',
    anchor : "85%",
    listeners : {
        select : function() {
            var _form = vp.editWin.formPanel.getForm();
            var startrq = _form.findField('model.startrq').getValue();
            var endrq = _form.findField("model.endrq").getValue();
            if (startrq && endrq) {
                if (endrq < startrq) {
                    Q.warning("开始日期不能大于结束日期");
                    _form.findField('model.startrq').setValue("");
                }
            }
        }
    }
}, {
    fieldLabel : "回访结束日期<font color='red'>*</font>",
    xtype : 'datefield',
    name : 'model.endrq',
    value : new Date().getDate() + 30,
    editable : false,
    allowBlank : false,
    format : 'Y-m-d',
    anchor : "85%",
    listeners : {
        select : function() {
            var _form = vp.editWin.formPanel.getForm();
            var startrq = _form.findField('model.startrq').getValue();
            var endrq = _form.findField("model.endrq").getValue();
            if (startrq && endrq) {
                if (endrq < startrq) {
                    Q.warning("结束日期不能小于开始日期");
                    _form.findField('model.endrq').setValue("");
                }
            }
        }
    }
}, {
    columnWidth : 1,
    fieldLabel : "备注<font color='red'>*</font>",
    name : 'model.remark',
    xtype : 'textarea',
    height : 60,
    anchor : "93.5%",
    allowBlank : false
} ];

var gridDtl1 = {
    tabTitle : "会员信息",
    xtype : 'uxeditorgrid',
    foreignKey : "telVisit_telVisitId",
    tabClassName : "telVisitCustList",
    allowEmpty : false,
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "individCust.individCustId",
            hidden : true,
        }, {
            header : "会员卡号",
            dataIndex : "individCust.cardNo",
            width : 100
        }, {
            header : "客户名称",
            dataIndex : "individCust.name",
            width : 100
        }, {
            header : "手机号码",
            dataIndex : "individCust.mobile",
            width : 100
        }, {
            header : "客户类型",
            width : 80,
            dataIndex : "individCust.typeName"
        }, {
            header : "性别",
            dataIndex : "individCust.genderName",
            width : 50
        }, {
            header : "当前可用积分",
            dataIndex : "individCust.credit",
            width : 100
        }, {
            header : "新老会员",
            dataIndex : "individCust.freshName",
            width : 80
        }, {
            header : "使用状态",
            dataIndex : "individCust.enableName",
            width : 70
        }, {
            header : "活跃状态",
            dataIndex : "individCust.activeName",
            width : 70
        }, {
            header : "末次消费日期",
            dataIndex : "individCust.lastBuyTime",
            renderer : Q.common.timeRenderer,
            width : 150
        }, {
            header : "末次消费门店",
            dataIndex : "individCust.lastStoreName",
            width : 200
        }, {
            header : "归属门店",
            dataIndex : "individCust.belongStoreName",
            width : 200
        } ]
    },
    pageSize : 20,
    store : {
        url : dealUrl + "_qryTelVisitCust.action",
        autoLoad : false
    },
    tbar : [ {
        id : "searchBnt",
        text : "查询",
        iconCls : "icon-search",
        handler : function() {
            var _store = vp.editWin.getCompByTabClassName('telVisitCustList').getStore();
            var _win = new Q.telvisit.searchWin();

            _win.on("search", function(data) {
                Ext.getBody().loadMask();
                Ext.Ajax.request({
                    url : dealUrl + "_qryTelVisitCust2.action",
                    params : data,
                    success : function(response) {
                        _store.removeAll();
                        var json = Ext.decode(response.responseText);
                        vp.editWin.formPanel.getForm().findField("model.queryParamsStr").setValue(json.data.queryParamsStr);

                        _store.baseParams = {
                            queryParamsStr : json.data.queryParamsStr
                        };
                        _store.load();

                        // “查询、导入”按钮二选一
                        Ext.getCmp("uploadBnt").disable();
                        Ext.getCmp("indiAddBtn").disable();
                        Ext.getCmp("indiDeleteBtn").disable();
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            });
            _win.show();
        }
    }, {
        text : "下载模版",
        iconCls : "icon-excel",
        handler : function() {
            window.open(path + "/Common_downLoadExcelTpl.action?fileName=TelVisit.xlsx");
        }
    }, {
        id : "uploadBnt",
        text : "导入",
        iconCls : "icon-upload",
        handler : function() {
            var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
            console.info(_store);
            Q.confirm("“导入”时会先清除列表选中的数据，是否继续？", {
                ok : function() {
                    var _win = new Q.excel.uploadWin({
                        url : dealUrl + "_uploadExcel.action"
                    });
                    _win.on("upload", function(action) {
                        _store.removeAll();

                        vp.editWin.formPanel.getForm().findField("model.queryParamsStr").setValue("excel" + action.result.dataList);

                        _store.baseParams = {
                            queryParamsStr : "excel" + action.result.dataList
                        };
                        _store.load();
                        // “查询、导入”按钮二选一
                        Ext.getCmp("searchBnt").disable();
                        Ext.getCmp("indiAddBtn").disable();
                        Ext.getCmp("indiDeleteBtn").disable();
                    });
                    _win.show();
                }
            });
        }
    }, {
        id : "indiAddBtn",
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var _store = vp.editWin.getCompByTabClassName('telVisitCustList').getStore();
            var _win = new Q.telvisit.addWin();
            _win.on("search", function(data) {
                var filterIds = new Array();

                var _items = _store.data.items;
                for (var i = 0; i < _items.length; i++) {
                    filterIds.push(_items[i].data["individCust.individCustId"]);
                }
                data.filter_NOTIN_individCustId = filterIds.join(",");

                Ext.getBody().loadMask();
                Ext.Ajax.request({
                    url : dealUrl + "_qryTelVisitCust2.action?add=true",
                    params : data,
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        console.info(json);
                        Q.each(json.data.result, function() {
                            var r = new Ext.data.Record({
                                "individCust.individCustId" : this.individCustId,
                                "individCust.cardNo" : this.cardNo,
                                "individCust.name" : this.name,
                                "individCust.typeName" : this.typeName,
                                "individCust.mobile" : this.mobile,
                                "individCust.genderName" : this.genderName,
                                "individCust.credit" : this.credit,
                                "individCust.freshName" : this.freshName,
                                "individCust.enableName" : this.enableName,
                                "individCust.activeName" : this.activeName,
                                "individCust.lastBuyTime" : this.lastBuyTime,
                                "individCust.lastStoreName" : this.lastStoreName,
                                "individCust.belongStoreName" : this.belongStoreName
                            });
                            _store.add(r);
                            if (_store.data.items.length > 0) {
                                Ext.get("ext-comp-1042").dom.innerHTML = '共' + _store.data.items.length + '条';
                            } else {
                                Ext.get("ext-comp-1042").dom.innerHTML = '没有数据';
                            }
                        });

                        Ext.getCmp("searchBnt").disable();
                        Ext.getCmp("uploadBnt").disable();
                        Ext.getCmp("indiDeleteBtn").enable();
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            });
            _win.show();
        }
    }, {
        id : "indiDeleteBtn",
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]
};

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '回访任务单',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : [ {
            name : 'publishBnt',
            text : '发布',
            index : 6,
            build : power.publish,
            iconCls : 'icon-audit',
            handler : function() {
                var sm = vp.grid.getSelectionModel().getSelected();
                if (Ext.isEmpty(sm)) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return false;
                }
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : dealUrl + "_telVisitPublish.action",
                    params : {
                        id : sm.get("telVisitId")
                    },
                    success : function() {
                        Q.tips(Q.color("发布成功！"), "red");
                        vp.grid.getStore().reload();
                        vp.grid.getSelectionModel().clearSelections();
                    },
                    failure : function() {
                        Q.tips(Q.color("发布失败！", "red"));
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        } ],
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'telVisitId',
            url : dealUrl + '_getJson.action',
            sort : "telVisitId",
            dir : "desc"
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                if (r.get("publishzt") == 1) {
                    return false;
                }
                return true;
            }
        }, {
            'delete' : function(r) {
                if (r.get("publishzt") == 1) {
                    return false;
                }
                return true;
            }
        }, {
            'publishBnt' : function(r) {
                if (r.get("publishzt") == 1) {
                    return false;
                }
                var date = new Date();
                var result = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
                if (new Date(r.get("endrq")) < new Date(result)) {
                    return false;
                }
                return true;
            }
        } ]
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 90,
            columnWidth : 0.33,
            height : "1px;",// 去除该属性会造成页面高度撑不起来（不能去除引号使用纯数字）
            items : editFormItems
        },
        centerTab : {
            items : [ gridDtl1 ]
        }
    },
    addAfter : function(grid, selectids, win) {
        Ext.getCmp("indiDeleteBtn").disable();
    },
    viewAfter : function(grid, selectids, win) {
        Ext.getCmp("indiDeleteBtn").disable();
    },
    editAfter : function(grid, selectids, win) {
        Ext.getCmp("indiDeleteBtn").disable();
        Ext.getCmp("indiAddBtn").disable();
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 330,
        height : 230,
        form : {
            labelWidth : 80,
            columnWidth : 1,
            items : [ {
                fieldLabel : '回访任务单号',
                name : "filter_LIKE_telVisitNo",
                xtype : "textfield"
            }, {
                fieldLabel : '回访方式',
                hiddenName : "filter_EQ_backfs",
                xtype : 'uxcombo',
                clearable : true,
                valueField : "value",
                displayField : "name",
                anchor : "95.6%",
                store : Q.telvisit.selStores.backfsStore
            }, {
                fieldLabel : "回访开始日期",
                xtype : 'datefield',
                name : 'filter_GE_startrq',
                editable : false,
                format : 'Y-m-d',
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var startrq = _form.findField('filter_GE_startrq').getValue();
                        var endrq = _form.findField("filter_LE_endrq").getValue();
                        if (startrq && endrq) {
                            if (endrq < startrq) {
                                Q.warning("开始日期不能大于结束日期");
                                _form.findField('filter_GE_startrq').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "回访结束日期",
                xtype : 'datefield',
                name : 'filter_LE_endrq',
                editable : false,
                format : 'Y-m-d',
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var startrq = _form.findField('filter_GE_startrq').getValue();
                        var endrq = _form.findField("filter_LE_endrq").getValue();
                        if (startrq && endrq) {
                            if (endrq < startrq) {
                                Q.warning("结束日期不能小于开始日期");
                                _form.findField('filter_LE_endrq').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : '是否已发布',
                hiddenName : "filter_EQ_publishzt",
                xtype : 'uxcombo',
                clearable : true,
                anchor : "95.6%",
                store : Q.common.yesOrNotStore
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