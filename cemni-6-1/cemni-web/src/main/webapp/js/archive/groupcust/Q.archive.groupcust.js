Ext.ns('Q.archive');
var dealUrl = path + '/GroupCust';
var jsArr = [ path + "/js/common/Q.excel.uploadWin.js", path + "/js/archive/groupcust/Q.archive.groupBatchSearchWin.js" ];
Q.loadJs(jsArr);

Q.archive.selStores = {
    // 客户类型
    typeStore : Q.common.selDict(1101),
    // 性别
    sexStore : Q.common.selDict(1100),
    // 单位性质
    natureStore : Q.common.selDict(1201),
    // 行业类别
    categoryStore : Q.common.selDict(1200),
    // 会员等级
    lvStore : Q.common.selDict(3100),
    // 使用状态
    enableStore : Q.common.selDict(1102),
    // 活跃状态
    activeStore : Q.common.selDict(1103)
};

// 列表
var gridColumn = [ {
    dataIndex : "groupCustId",
    hidden : true
}, {
    dataIndex : "active",
    hidden : true
}, {
    dataIndex : "enable",
    hidden : true
}, {
    header : "会员卡号",
    dataIndex : "cardNo",
    width : 8
}, {
    header : "单位名称",
    dataIndex : "groupName",
    width : 24
}, {
    header : "单位性质",
    dataIndex : "natureName",
    width : 8
}, {
    header : "行业类别",
    dataIndex : "categoryName",
    width : 8
}, {
    header : "城市",
    dataIndex : "city",
    width : 8
}, {
    header : "会员等级",
    dataIndex : "lvName",
    width : 8
}, {
    header : "当前可用积分",
    dataIndex : "credit",
    width : 10
}, {
    header : "使用状态",
    dataIndex : "enableName",
    width : 8
}, {
    header : "活跃状态",
    dataIndex : "activeName",
    width : 8
}, {
    header : "创建日期",
    dataIndex : "createTime",
    renderer : Q.common.dateRenderer,
    width : 10
} ];

// 表单
var editFormItems = [ {
    anchor : "-3",
    columnWidth : 1,
    xtype : 'fieldset',
    title : '企业信息',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : [ {
            name : 'model.groupCustId',
            xtype : "hidden"
        }, {
            name : "model.enable",
            xtype : "hidden"
        }, {
            name : "model.active",
            xtype : "hidden"
        }, {
            name : "model.createTime",
            xtype : "hidden"
        }, {
            name : "model.lv",
            xtype : "hidden"
        }, {
            name : "model.credit",
            xtype : "hidden"
        }, {
            name : "model.creditStatus",
            xtype : "hidden"
        } ]
    }, {
        items : {
            fieldLabel : '会员卡号',
            name : 'model.cardNo',
            emptyText : '系统自动生成',
            xtype : "textfield",
            anchor : "85%",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '单位名称<font color="red">*</font>',
            anchor : "85%",
            allowBlank : false,
            name : 'model.groupName',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '客户类型<font color="red">*</font>',
            anchor : "86%",
            allowBlank : false,
            hiddenName : 'model.type',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            value : 2,
            store : Q.archive.selStores.typeStore
        }
    }, {
        items : {
            fieldLabel : '行业类别<font color="red">*</font>',
            anchor : "86%",
            allowBlank : false,
            hiddenName : 'model.category',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.categoryStore
        }
    }, {
        items : {
            fieldLabel : '单位性质<font color="red">*</font>',
            anchor : "86%",
            allowBlank : false,
            hiddenName : 'model.nature',
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            store : Q.archive.selStores.natureStore
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            name : 'model.remark',
            xtype : "textarea",
            height : 50,
            anchor : "94%"
        }
    } ]
}, {
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
            fieldLabel : '增值税号登记',
            anchor : "85%",
            name : 'model.vatNo',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '营业执照编号',
            anchor : "85%",
            name : 'model.businessLicense',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '法人代表',
            anchor : "85%",
            name : 'model.artificialPerson',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '城市',
            anchor : "85%",
            name : 'model.city',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '决策人姓名',
            anchor : "85%",
            name : 'model.dmName',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '决策人手机',
            anchor : "85%",
            name : 'model.dmMobile',
            regex : /^(1\d{10})$/,
            regexText : "请输入正确的手机号码",
            xtype : "numberfield"
        }
    }, {
        items : {
            fieldLabel : '决策人职务',
            anchor : "85%",
            name : 'model.dmTitle',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '决策人喜好',
            anchor : "85%",
            name : 'model.dmHobby',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '联系人通讯地址',
            anchor : "85%",
            name : 'model.address',
            xtype : "textfield"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '业务需求描述',
            name : 'model.businessRequirement',
            xtype : "textarea",
            height : 50,
            anchor : "94%"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '备注',
            name : 'model.note',
            xtype : "textarea",
            height : 50,
            anchor : "94%"
        }
    } ]
} ];

var gridDtl1 = {
    tabTitle : "采购我司产品信息",
    xtype : 'uxeditorgrid',
    foreignKey : "groupCust_groupCustId",
    tabClassName : "product",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "productId",
            disabled : true
        }, {
            dataIndex : "_groupCustId",
            disabled : true
        }, {
            header : "大类",
            dataIndex : "type",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "品名",
            dataIndex : "name",
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "产品价格",
            dataIndex : "price",
            editor : {
                maxLength : 100,
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
        idProperty : "productId",
        url : dealUrl + "_getRel.action",
        sort : "productId",
        dir : "desc",
        autoLoad : false
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
    } ]
};

var gridDtl2 = {
    tabTitle : "采购竞品信息",
    xtype : 'uxeditorgrid',
    foreignKey : "groupCust_groupCustId",
    tabClassName : "competitorProduct",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "competitorId",
            disabled : true
        }, {
            dataIndex : "_groupCustId",
            disabled : true
        }, {
            header : "品牌",
            dataIndex : "brand",
            editor : {
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "品名",
            dataIndex : "name",
            editor : {
                xtype : 'textfield',
                editable : true
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "产品价格",
            dataIndex : "price",
            editor : {
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
        idProperty : "competitorId",
        url : dealUrl + "_getRel2.action",
        sort : "competitorId",
        dir : "desc",
        autoLoad : false
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
    } ]
};

var gridDtl3 = {
    tabTitle : "团体客户成员信息",
    xtype : 'uxeditorgrid',
    foreignKey : "groupCustId",
    tabClassName : "individCust",
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "individCustId",
            disabled : true
        }, {
            header : "会员卡号",
            dataIndex : "cardNo"
        }, {
            header : "客户姓名",
            dataIndex : "name"
        }, {
            header : "性别",
            dataIndex : "gender",
            store : Q.archive.selStores.sexStore,
            renderer : function(v) {
                return Q.common.getSelText(Q.archive.selStores.sexStore, v);
            }
        }, {
            header : "手机号码",
            dataIndex : "mobile",
            regex : /^(1\d{10})$/,
            regexText : "请输入正确的手机号码"
        } ]
    },
    pageSize : 0,
    store : {
        idProperty : "individCustId",
        url : dealUrl + "_getRel3.action",
        sort : "individCustId",
        dir : "desc",
        autoLoad : false
    }
};

// /////////////////////////////////////////////////////////////////////////////////////////////////////////

Q.archive.groupTbarBnt = [ {
    text : '批量查询',
    index : 6,
    iconCls : 'icon-search',
    build : power.batchsearch,
    handler : function() {
        var store = vp.grid.getStore();
        var win = new Q.archive.groupBatchSearchWin();
        win.on("search", function(data) {
            store.baseParams = data;
            store.load();
        });
        win.show();
    }
}, {
    text : '冻结/解冻',
    index : 7,
    iconCls : 'icon-lock',
    build : power.changecode,
    handler : function() {
        var sm = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        var enableStr = (sm.get("enable") == 1 ? "冻结" : "解冻");
        Q.confirm("确定执行“" + enableStr + "”操作吗？", {
            ok : function() {
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : path + "/GroupCust_chgEnable.action",
                    params : {
                        "id" : sm.get("groupCustId")
                    },
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error("操作失败！");
                            return;
                        }
                        Q.tips(Q.color("操作成功！", "red"));
                        vp.grid.getStore().reload();
                        vp.grid.getSelectionModel().clearSelections();
                    },
                    failure : function(response) {
                        Q.error("操作失败!");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
}, {
    name : 'invalidBnt',
    text : '无效',
    index : 8,
    iconCls : 'icon-toReject',
    build : power.changecode2,
    handler : function() {
        var sm = vp.grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return false;
        }
        Q.confirm("确定执行“失效”操作吗？", {
            ok : function() {
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : path + "/GroupCust_custInvalid.action",
                    params : {
                        "id" : sm.get("groupCustId")
                    },
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error("操作失败！");
                            return;
                        }
                        Q.tips(Q.color("操作成功！", "red"));
                        vp.grid.getStore().reload();
                        vp.grid.getSelectionModel().clearSelections();
                    },
                    failure : function(response) {
                        Q.error("操作失败！");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
}, {
    text : "下载模版",
    index : 9,
    iconCls : "icon-excel",
    build : power.download,
    handler : function(self) {
        window.open(path + "/Common_downLoadExcelTpl.action?fileName=GroupCust.xlsx");
    }
}, {
    text : '导入',
    index : 10,
    iconCls : 'icon-upload',
    build : power.import,
    handler : function() {
        var _win = new Q.excel.uploadWin({
            url : dealUrl + "_excel.action"
        });
        _win.on("upload", function() {
            vp.grid.getStore().load();
        });
        _win.show();
    }
}, {
    text : "导出",
    index : 11,
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(vp.grid.getStore().baseParams));
    }
} ];

// /////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '团体档案',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.archive.groupTbarBnt,
        hideSubTab : true,
        hideBtn : [ "delete" ],
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'groupCustId',
            url : dealUrl + '_getJson.action',
            sort : 'groupCustId',
            dir : "desc"
        },
        listEditStateFn : [ {
            edit : function(r) {
                if (r.data.enable == 2) {
                    return false;
                }
                return true;
            }
        }, {
            invalidBnt : function(r) {
                if (r.data.active == 4) {
                    return false;
                }
                return true;
            }
        } ],
        addAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("individCust");
            vp.editWin.formPanel.getForm().findField("model.type").disabled = true;
            vp.editWin.setActiveTab([ "individCust" ]);
        },
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.type").disabled = true;
            vp.editWin.showTabItem("individCust");
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("individCust");
            vp.editWin.setActiveTab([ "individCust" ]);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            height : 300,
            labelWidth : 92,
            items : editFormItems
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 350,
        height : 240,
        form : {
            labelWidth : 90,
            columnWidth : 1,
            items : [ {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '单位名称',
                name : "filter_LIKE_groupName",
                xtype : "textfield"
            }, {
                fieldLabel : '增值税号登记',
                name : "filter_LIKE_vatNo",
                xtype : "textfield"
            }, {
                fieldLabel : '营业执照编号',
                name : "filter_LIKE_businessLicense",
                xtype : "textfield"
            }, {
                fieldLabel : '商品条码',
                name : "filter_LIKE_posOrderList_goodsBar",
                xtype : "textfield"
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