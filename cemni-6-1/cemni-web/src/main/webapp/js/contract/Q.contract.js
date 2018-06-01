/**
 * guonianlun 2016年11月28日09:26:42 合同管理
 */
Ext.ns('Q.contract');
var file_size_limit = 5;
var dealUrl = path + '/Contract';
var jsArr = [ path + "/js/archive/franchisee/Q.archive.chooseFranWin.js", path + "/js/archive/channel/Q.archive.channelWin.js", path + "/js/archive/partner/Q.partnerSelect.js", path + "/js/archive/supplier/Q.archive.supplierWin.js", path + "/plugins/UploadDialog/Neu.ux.UploadDialog.js" ];
Q.loadJs(jsArr);

Q.contract.store = {
    // 合同类型
    contractType : Q.common.selDictContract(6400),
    // 其他合同性质
    contractNatrue : Q.common.selDict(6401),
    // 条款
    termsType : new Ext.data.JsonStore({
        url : path + "/Contract_getTerms.action?filter_EQ_natrue=1",
        fields : [ "termsName", "termsId" ],
        autoLoad : true
    })
};

var gridColumn = [ {
    header : "合同编号",
    width : 3,
    dataIndex : "contractNum"
}, {
    header : "合同名称",
    width : 5,
    dataIndex : "contractName"
}, {
    header : "合同甲方",
    width : 5,
    dataIndex : "partyA"
}, {
    header : "合同乙方",
    width : 5,
    dataIndex : "partyB"
}, {
    header : "合同类型",
    width : 3,
    dataIndex : "contractTypeName"
}, {
    header : "签约日期",
    width : 2.5,
    dataIndex : "signDate",
    renderer : Q.common.dateRenderer
}, {
    header : "生效日期",
    width : 2.5,
    dataIndex : "effDate",
    renderer : Q.common.dateRenderer
}, {
    header : "失效日期",
    width : 2.5,
    dataIndex : "invDate",
    renderer : Q.common.dateRenderer
} ];

var editFormItems = [ {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '基本信息',
    anchor : '-3',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.contractId',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.createDate',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.createUser',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.updateDate',
            xtype : "hidden"
        }
    }, {
        items : {
            name : 'model.updateUser',
            xtype : "hidden"
        }
    }, {
        items : {
            fieldLabel : '合同编码<font color="red">*</font>',
            name : 'model.contractNum',
            id : "contractnum",
            anchor : '85%',
            emptyText : '系统自动生成',
            xtype : "textfield",
            readOnly : true
        }
    }, {
        items : {
            fieldLabel : '合同类型<font color="red">*</font>',
            hiddenName : 'model.contractType',
            xtype : "uxcombo",
            id : "conTypeEdit",
            anchor : '85.6%',
            valueField : "value",
            displayField : "name",
            allowBlank : false,

            store : Q.contract.store.contractType,
            listeners : {
                "select" : function(c) {
                    var formPanel = vp.editWin.formPanel;
                    formPanel.getForm().findField("model.partyB").setValue("");
                    formPanel.getForm().findField("model.partyNum").setValue("");
                    var typeid = c.getValue();
                    if (typeid == "5" || typeid == "6") {
                        Ext.getCmp("partyb").getEl().dom.readOnly = false;
                        Ext.getCmp("partynum").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("partyb").getEl().dom.readOnly = true;
                        Ext.getCmp("partynum").getEl().dom.readOnly = true;
                    }
                    setTabHidden(typeid);
                    Q.contract.store.termsType.removeAll();
                    Ext.getCmp('termsList').setValue("");
                    Ext.getCmp('termsContent').setValue("");
                    Q.contract.store.termsType.baseParams = {
                        "filter_EQ_termsType" : typeid
                    };
                    Q.contract.store.termsType.load();
                }
            }
        }
    }, {
        items : {
            fieldLabel : '合同名称<font color="red">*</font>',
            name : 'model.contractName',
            allowBlank : false,
            maxLength : 64,
            anchor : '85%',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '合同甲方<font color="red">*</font>',
            name : 'model.partyA',
            allowBlank : false,
            anchor : '85%',
            value : "江苏千年珠宝有限公司",
            maxLength : 64,
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '合同乙方<font color="red">*</font>',
            xtype : "textfield",
            name : 'model.partyB',
            anchor : '85%',
            id : "partyb",
            allowBlank : false,
            emptyText : '请选择',
            listeners : {
                render : function(p) {
                    p.getEl().on('click', function(p) {
                        if (vp.editWin.formPanel.getForm().findField("model.contractName").disabled)
                            return;
                        var conTypeEdit = Ext.getCmp('conTypeEdit').getValue();
                        if (conTypeEdit != "5" && conTypeEdit != "" && conTypeEdit != "6") {
                            var _win = null;
                            if (conTypeEdit == "1") {
                                _win = new Q.archive.supplierWin();
                            } else if (conTypeEdit == "2") {
                                _win = new Q.archive.chooseFranWin();
                            } else if (conTypeEdit == "3") {
                                _win = new Q.archive.channelWin();
                            } else if (conTypeEdit == "4") {
                                _win = new Q.partner.chooseStoreWin();
                            }
                            _win.on("submit", function(r) {
                                if (conTypeEdit == "1") {
                                    Ext.getCmp('partyb').setValue(r.json.suppliername);
                                    Ext.getCmp('partynum').setValue(r.json.supplierno);
                                } else if (conTypeEdit == "2") {
                                    Ext.getCmp('partyb').setValue(r.json.fraName);
                                    Ext.getCmp('partynum').setValue(r.json.fraCode);
                                } else if (conTypeEdit == "3") {
                                    Ext.getCmp('partyb').setValue(r.json.channelname);
                                    Ext.getCmp('partynum').setValue(r.json.channelno);
                                } else if (conTypeEdit == "4") {
                                    Ext.getCmp('partyb').setValue(r.json.partnername);
                                    Ext.getCmp('partynum').setValue(r.json.partnerno);
                                }
                            });
                            _win.show();
                        }
                    });
                }
            }
        }
    }, {
        items : {
            fieldLabel : '乙方编码',
            id : "partynum",
            anchor : '85%',
            name : 'model.partyNum',
            maxLength : 64,
            xtype : "textfield"
        }
    }, {
        items : {
            format : 'Y-m-d',
            fieldLabel : '签订日期<font color="red">*</font>',
            name : 'model.signDate',
            allowBlank : false,
            anchor : '85%',
            xtype : "datefield",
            editable : false,
            listeners : {
                select : function() {
                    var form = vp.editWin.formPanel.getForm();
                    var signDate = form.findField("model.signDate").getValue();
                    var effDate = form.findField("model.effDate").getValue();
                    if (signDate !== "" && effDate !== "") {
                        if (effDate < signDate) {
                            Q.warning("签订日期不能晚于生效日期!");
                            form.findField("model.signDate").setValue("");
                        }
                    }
                }
            }
        }
    }, {
        items : {
            format : 'Y-m-d',
            fieldLabel : '生效日期<font color="red">*</font>',
            anchor : '85%',
            allowBlank : false,
            name : 'model.effDate',
            editable : false,
            xtype : "datefield",
            listeners : {
                select : function() {
                    var form = vp.editWin.formPanel.getForm();
                    var signDate = form.findField("model.signDate").getValue();
                    var effDate = form.findField("model.effDate").getValue();
                    var invDate = form.findField("model.invDate").getValue();
                    if (signDate && effDate) {
                        if (effDate < signDate) {
                            Q.warning("生效日期不能早于签订日期");
                            form.findField("model.effDate").setValue("");
                        }
                    }
                    if (invDate && effDate) {
                        if (invDate < effDate) {
                            Q.warning("生效日期不能小于失效日期");
                            form.findField("model.effDate").setValue("");
                        }
                    }
                }
            }
        }
    }, {
        items : {
            format : 'Y-m-d',
            fieldLabel : '失效日期<font color="red">*</font>',
            anchor : '85%',
            allowBlank : false,
            name : 'model.invDate',
            xtype : "datefield",
            editable : false,
            listeners : {
                select : function() {
                    var form = vp.editWin.formPanel.getForm();
                    var invDate = form.findField("model.invDate").getValue();
                    var effDate = form.findField("model.effDate").getValue();
                    if (invDate && effDate) {
                        if (invDate < effDate) {
                            Q.warning("失效日期不能早于生效日期");
                            form.findField("model.invDate").setValue("");
                        }
                    }
                }
            }
        }
    }, {
        items : {
            fieldLabel : '签约地点',
            name : 'model.addr',
            anchor : '85%',
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '合同丙方',
            name : 'model.partyC',
            anchor : '85%',
            maxLength : 64,
            xtype : "textfield"
        }
    }, {
        defaults : {
            anchor : "94%",
            height : "50"
        },
        columnWidth : 1,
        items : {
            fieldLabel : '备    注',
            name : 'model.remark',
            maxLength : 200,
            xtype : "textarea"
        }
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '合同条款',
    anchor : '-3',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : 1
    },
    items : [ {
        items : {
            fieldLabel : '合同条款<font color="red">*</font>',
            xtype : "uxcombo",
            hiddenName : 'model.termsId',
            id : "termsList",
            anchor : '45%',
            valueField : "termsId",
            displayField : "termsName",
            store : Q.contract.store.termsType,
            allowBlank : false,
            emptyText : "请选择",
            listeners : {
                "select" : function(c) {
                    Ext.Ajax.request({
                        url : dealUrl + "_getTermsContent.action",
                        params : {
                            "termsId" : c.getValue()
                        },
                        success : function(response) {
                            var o = Ext.util.JSON.decode(response.responseText);
                            Ext.getCmp('termsContent').setValue(o.content);
                        },
                        failure : function() {
                            Q.error("<font color='red'>" + "未获取到相应条款内容" + "</font>");
                        },
                        callback : function() {
                            Ext.getBody().unmask();
                        }
                    });

                }
            }
        }
    }, {
        xtype : "htmleditor",
        id : "termsContent",
        fieldLabel : '条款内容',
        readOnly : true,
        columnWidth : 1,
        anchor : '70%',
        autoScroll : true,
        height : 300
    } ]
}, {
    columnWidth : 1,
    xtype : 'fieldset',
    title : '附件',
    anchor : '-3',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : 1
    },
    items : [ {
        items : [ {
            fieldLabel : "附件上传",
            xtype : "button",
            anchor : "20%",
            Qtext : "添加附件",
            name : 'uploadBtn',
            id : "updateload",
            text : "选择附件",
            columnWidth : .5,
            handler : function() {
                var formPanel = vp.editWin.formPanel;
                var uploadFileGroupId = formPanel.form.findField("model.uploadFileGroupId").getValue();
                vp.openUploadWindows("", uploadFileGroupId, "uploadFile4View", formPanel, "");
            }
        }, {
            name : 'model.uploadFileGroupId',
            xtype : "hidden"
        }, {
            xtype : 'displayfield',
            name : 'uploadFile4View',
            fieldLabel : $("label.AttachmentsCheck")
        }, {
            xtype : 'displayfield',
            name : 'tishi',
            fieldLabel : "支持格式",
            value : "doc,docx,xls,xlsx,ppt,pptx,pdf   支持大小：每个文件  ≤ 5M"
        } ]

    } ]
} ];

var gridDtlFee = {
    id : "gridFee",
    tabTitle : "工费金损",
    xtype : 'uxeditorgrid',
    foreignKey : "contract_contractId",
    tabClassName : "contractFees",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "feeId",
            disabled : true
        }, {
            dataIndex : "_contractId",
            disabled : true
        }, {
            header : "品类",
            dataIndex : "type",
            disabled : false,
            editor : {
                maxLength : 64,
                name : "name",
                hiddenName : "name",
                allowBlank : false,
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "工费",
            dataIndex : "fee",
            disabled : false,
            editor : {
                maxLength : 18,
                name : "name",
                hiddenName : "name",
                allowBlank : false,
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "金损",
            dataIndex : "goldLoss",
            allowBlank : false,
            disabled : false,
            editor : {
                maxLength : 18,
                name : "name",
                hiddenName : "name",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "feeId",
        url : dealUrl + "_getAllFee.action",
        sort : "feeId",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.addDetail(grid);
            var materialStore = grid.getSelectWin().gridPanel.getStore();
            materialStore.on("beforeload", function(grid) {
            });
            materialStore.load({
                params : {
                    start : 0,
                    limit : 20
                }
            });
            grid.getSelectWin().show();
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]

};

var gridDtlPay = {
    id : "gridPay",
    tabTitle : "付款日期金额",
    xtype : 'uxeditorgrid',
    foreignKey : "contract_contractId",
    tabClassName : "contractPays",
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "payId",
            disabled : true
        }, {
            dataIndex : "_contractId",
            disabled : true
        }, {
            header : "付款日期",
            dataIndex : "payDate",
            disabled : false,
            editor : {
                editable : false,
                allowBlank : false,
                xtype : 'datefield',
                format : 'Y-m-d'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "付款金额",
            dataIndex : "payAmount",
            disabled : false,
            editor : {
                maxLength : 18,
                allowBlank : false,
                xtype : 'textfield',
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
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "payId",
        url : dealUrl + "_getAllPay.action",
        sort : "payId",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            vp.editWin.addDetail(grid);
            var materialStore = grid.getSelectWin().gridPanel.getStore();
            materialStore.load({
                params : {
                    start : 0,
                    limit : 20
                }
            });
            grid.getSelectWin().show();
        }
    }, {
        text : "删除",
        iconCls : "icon-delete",
        handler : function() {
            vp.editWin.deleteDetail(this.findParentByType(Ext.grid.GridPanel));
        }
    } ]

};

var formDtlAdmin = {
    id : "fromAdmin",
    tabTitle : "行政部",
    xtype : 'formpanel',
    foreignKey : "contract_contractId",
    tabClassName : "contractAdmins",
    loadUrl : dealUrl + "_getAllAdmin.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        title : '详细信息',
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "行政部主键id",
                maxLength : 18,
                name : "adminId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同管理主键id",
                maxLength : 18,
                name : "_contractId"
            } ]
        }, {
            defaults : {
                xtype : "uxcombo",
                anchor : "85%",
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "其他合同性质",
                name : 'contractNatrue',
                width : 150,
                valueField : "value",
                displayField : "name",
                allowBlank : true,
                store : Q.contract.store.contractNatrue
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%",
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同金额",
                maxLength : 18,
                name : "amount"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "付款方式",
                maxLength : 32,
                name : "payType"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "联系人",
                maxLength : 18,
                name : "contacts"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "采购项目",
                maxLength : 64,

                name : "purProject"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "培训课程",
                maxLength : 64,

                name : "trainc"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : " 顾问类别",
                maxLength : 32,
                name : "advClass"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "备注",
                maxLength : 200,
                name : "remark"
            } ]
        } ]
    } ]
};

var formdtlBrand = {
    id : "formBrand",
    tabTitle : "服务信息",
    xtype : 'formpanel',
    foreignKey : "contract_contractId",
    tabClassName : "contractBrands",
    loadUrl : dealUrl + "_getAllBrand.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        title : '详细信息',
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                maxLength : 18,
                name : "brandId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                maxLength : 18,
                name : "_contractId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "甲方联系人",
                maxLength : 18,
                name : "contactsA"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "乙方联系人",
                maxLength : 18,
                name : "contactsB"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "丙方联系人",
                maxLength : 18,
                name : "contactsC"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "甲方联系人电话",
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的电话号码",
                name : "phoneA"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "乙方联系人电话",
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的电话号码",
                name : "phoneB"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "丙方联系人电话",
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的电话号码",
                name : "phoneC"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同金额",
                maxLength : 18,
                name : "amount"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : " 合作项目",
                maxLength : 64,
                name : "project"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "备注",
                maxLength : 200,
                name : "remark"
            } ]
        } ]
    } ]

};

var formdtlOp = {
    id : "formOp",
    tabTitle : "运营部",
    xtype : 'formpanel',
    foreignKey : "contract_contractId",
    tabClassName : "contractOps",
    loadUrl : dealUrl + "_getAllOp.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        title : '详细信息',
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                maxLength : 18,
                name : "operateId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                maxLength : 18,
                name : "_contractId"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "房租",
                maxLength : 18,
                name : "rent"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "水费",
                maxLength : 18,
                name : "waterFree"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "电费",
                maxLength : 18,
                name : "electricityFree"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "物业费",
                maxLength : 18,
                name : "propertFee"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "保底",
                maxLength : 18,
                name : "atend"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "保底完成额",
                maxLength : 18,
                name : "atendComAmount"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "保底差额",
                maxLength : 18,
                name : "atendDifAmount"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : " 扣款方式",
                maxLength : 20,
                name : "debitType"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "镶嵌扣点",
                maxLength : 18,
                name : "preciousPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "裸钻扣点",
                maxLength : 18,
                name : "nkPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "克拉钻扣点",
                maxLength : 18,
                name : "cdPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "彩宝扣点",
                maxLength : 18,
                name : "multPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "黄金扣点",
                maxLength : 18,
                name : "goldPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "K金扣点",
                maxLength : 18,
                name : "kgoldPoints"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "翡翠扣点",
                maxLength : 18,
                name : "emeraldPoints"
            } ]
        }, {
            defaults : {
                xtype : "datefield",
                anchor : "85%",
                format : "Y-m-d",
                editable : false
            },
            columnWidth : .33,
            items : [ {
                id : "accountsDateId",
                fieldLabel : "结算日期 ",
                maxLength : 18,
                name : "accountsDate",
                listeners : {
                    select : function() {
                        var payDate = Ext.getCmp("payDateId").getValue();
                        var accountsDate = Ext.getCmp("accountsDateId").getValue();
                        if (payDate && accountsDate) {
                            if (payDate < accountsDate) {
                                Q.warning("结算日期不能大于回款日期");
                                Ext.getCmp("accountsDateId").setValue("");
                            }
                        }
                    }
                }
            } ]
        }, {
            defaults : {
                xtype : "datefield",
                anchor : "85%",
                format : "Y-m-d",
                editable : false
            },
            columnWidth : .33,
            items : [ {
                id : "payDateId",
                fieldLabel : "回款日期",
                maxLength : 18,
                name : "payDate",
                listeners : {
                    select : function() {
                        var payDate = Ext.getCmp("payDateId").getValue();
                        var accountsDate = Ext.getCmp("accountsDateId").getValue();
                        if (payDate && accountsDate) {
                            if (payDate < accountsDate) {
                                Q.warning("回款日期不能小于结算日期");
                                Ext.getCmp("payDateId").setValue("");
                            }
                        }
                    }
                }
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "区域及负责人",
                maxLength : 18,
                name : "areaPerson"
            } ]
        } ]
    } ]

};

var formdtlExp = {
    id : "formExp",
    tabTitle : "连锁发展部",
    xtype : 'formpanel',
    foreignKey : "contract_contractId",
    tabClassName : "contractExps",
    loadUrl : dealUrl + "_getAllExp.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        title : '详细信息',
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "拓展部主键id",
                maxLength : 18,
                name : "expandId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同管理主键id",
                maxLength : 18,
                name : "_contractId"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "门店面积",
                maxLength : 18,
                name : "storeArea"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "租金",
                maxLength : 18,
                name : "rent"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "扣点",
                maxLength : 18,
                name : "points"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "加盟商",
                maxLength : 18,
                name : "franChisee"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "年限 ",
                maxLength : 18,
                name : "storeYears"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "首批 ",
                maxLength : 64,
                name : "firstBatch"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "补货",
                maxLength : 200,
                name : "replenishA"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : " 合同主体 ",
                maxLength : 200,
                name : "contractBodyA"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                hidden : true,
                hideMode : "visibility"
            },
            columnWidth : .33,
            items : [ {} ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "区域代理",
                maxLength : 200,
                name : "areaAgent"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "年限",
                maxLength : 18,
                name : "agentYears"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "补货",
                maxLength : 200,
                name : "replenishB"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "开店",
                maxLength : 200,
                name : "openStore"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "85%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同主体",
                maxLength : 200,
                name : "contractBodyB"
            } ]
        } ]
    } ]
};

var formdtlCom = {
    id : "formCom",
    labelWidth : 70,
    tabTitle : "商品部",
    xtype : 'formpanel',
    foreignKey : "contract_contractId",
    tabClassName : "contractComs",
    loadUrl : dealUrl + "_getAllCom.action",
    items : [ {
        columnWidth : 1,
        xtype : 'fieldset',
        title : '详细信息',
        layout : 'column',
        defaults : {
            layout : 'form',
            border : false,
            columnWidth : 1
        },
        items : [ {
            defaults : {
                xtype : "textfield",
                anchor : "95%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "商品部主键id",
                maxLength : 18,
                name : "commodityId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%",
                hidden : true
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合同管理主键id",
                maxLength : 32,
                name : "_contractId"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "联系人",
                maxLength : 32,
                name : "contact"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合作项目",
                maxLength : 64,
                name : "projectName"
            } ]
        }, {
            defaults : {
                xtype : 'numberfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合作总额",
                maxLength : 18,
                name : "coopAmount"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "合作账期",
                maxLength : 200,
                name : "coopDate"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "付款方式",
                maxLength : 32,
                name : "payType"
            } ]
        }, {
            defaults : {
                xtype : 'textfield',
                allowDecimals : true,
                decimalPrecision : 2,
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "信用额度",
                maxLength : 18,
                name : "creditLimit"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%"
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "逾期利息",
                maxLength : 20,
                name : "overRest"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%",
                editable : false
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "付款日期",
                maxLength : 100,
                name : "payDate"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "95%",
                editable : false
            },
            columnWidth : .33,
            items : [ {
                fieldLabel : "付款周期",
                maxLength : 100,
                name : "paycycle"
            } ]
        }, {
            defaults : {
                xtype : "textfield",
                anchor : "97%"
            },
            columnWidth : 1,
            items : [ {
                fieldLabel : "备注",
                maxLength : 200,
                name : "remark"
            } ]
        } ]
    } ]
};

function setTabHidden(typeid) {
    if (typeid == "1") {
        vp.editWin.setActiveTab([ "contractBrands", "contractPays", "contractOps", "contractExps", "contractAdmins" ]);
        vp.editWin.hideTabItem("contractBrands");
        vp.editWin.hideTabItem("contractPays");
        vp.editWin.hideTabItem("contractOps");
        vp.editWin.hideTabItem("contractExps");
        vp.editWin.hideTabItem("contractAdmins");
        vp.editWin.showTabItem("contractComs");
        vp.editWin.showTabItem("contractFees");
    } else if (typeid == "2") {
        vp.editWin.setActiveTab([ "contractBrands", "contractComs", "contractPays", "contractFees", "contractExps", "contractAdmins" ]);
        vp.editWin.hideTabItem("contractComs");
        vp.editWin.hideTabItem("contractFees");
        vp.editWin.hideTabItem("contractBrands");
        vp.editWin.hideTabItem("contractPays");
        vp.editWin.hideTabItem("contractExps");
        vp.editWin.hideTabItem("contractAdmins");
        vp.editWin.showTabItem("contractOps");
    } else if (typeid == "3") {
        vp.editWin.setActiveTab([ "contractBrands", "contractFees", "contractPays", "contractOps", "contractComs", "contractAdmins" ]);
        vp.editWin.hideTabItem("contractComs");
        vp.editWin.hideTabItem("contractFees");
        vp.editWin.hideTabItem("contractBrands");
        vp.editWin.hideTabItem("contractPays");
        vp.editWin.hideTabItem("contractOps");
        vp.editWin.hideTabItem("contractAdmins");
        vp.editWin.showTabItem("contractExps");
    } else if (typeid == "4") {
        vp.editWin.setActiveTab([ "contractComs", "contractFees", "contractExps", "contractOps", "contractAdmins" ]);
        vp.editWin.hideTabItem("contractComs");
        vp.editWin.hideTabItem("contractFees");
        vp.editWin.hideTabItem("contractOps");
        vp.editWin.hideTabItem("contractExps");
        vp.editWin.hideTabItem("contractAdmins");
        vp.editWin.showTabItem("contractBrands");
        vp.editWin.showTabItem("contractPays");
    } else if (typeid == "5") {
        vp.editWin.setActiveTab([ "contractComs", "contractBrands", "contractPays", "contractOps", "contractExps", "contractFees" ]);
        vp.editWin.hideTabItem("contractComs");
        vp.editWin.hideTabItem("contractFees");
        vp.editWin.hideTabItem("contractBrands");
        vp.editWin.hideTabItem("contractPays");
        vp.editWin.hideTabItem("contractOps");
        vp.editWin.hideTabItem("contractExps");
        vp.editWin.showTabItem("contractAdmins");
    } else if (typeid == "6") {
        vp.editWin.setActiveTab([ "contractComs", "contractFees", "contractExps", "contractOps", "contractAdmins" ]);
        vp.editWin.hideTabItem("contractComs");
        vp.editWin.hideTabItem("contractFees");
        vp.editWin.hideTabItem("contractOps");
        vp.editWin.hideTabItem("contractExps");
        vp.editWin.hideTabItem("contractAdmins");
        vp.editWin.showTabItem("contractBrands");
        vp.editWin.showTabItem("contractPays");
    } else {
        vp.editWin.showTabItem("contractComs");
        vp.editWin.showTabItem("contractFees");
        vp.editWin.showTabItem("contractBrands");
        vp.editWin.showTabItem("contractPays");
        vp.editWin.showTabItem("contractOps");
        vp.editWin.showTabItem("contractExps");
        vp.editWin.showTabItem("contractAdmins");
        vp.editWin.setActiveTab([]);
    }
};

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '合同管理',
    playListMode : playListMode.normal,
    accessControl : {
        hiddenTab : {
            "contractExps" : false
        }
    },
    vp : {
        hideSubTab : true,
        column : gridColumn,
        hideBtn : [ "delete" ],
        store : {
            idProperty : 'contractId',
            url : dealUrl + '_getJson.action',
            sort : "contractId",
            dir : "desc"
        },
        subTab : [],
        listEditStateFn : [],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.formPanel.getForm().findField("model.contractName").disabled = true;
        },
        editAfter : function(grid, selectids, win) {
            Ext.getCmp("conTypeEdit").readOnly = true;
        },
        addAfter : function(grid, win) {
            var findContractId = vp.editWin.formPanel.getForm().findField("model.contractId").getValue();
            if ("" == findContractId || typeof (findContractId) == "undefined") {
                Q.contract.store.termsType.removeAll();
                vp.editWin.showTabItem("contractComs");
                vp.editWin.showTabItem("contractFees");
                vp.editWin.showTabItem("contractBrands");
                vp.editWin.showTabItem("contractPays");
                vp.editWin.showTabItem("contractOps");
                vp.editWin.showTabItem("contractExps");
                vp.editWin.showTabItem("contractAdmins");
                vp.editWin.setActiveTab([]);
            }
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 70,
            items : editFormItems,
            columnWidth : '0.33',
            height : 300,
            setFormValueAfter : function(formPanel) {
                var formValue = formPanel.getForm().findField("model.termsId").getValue();

                var select = formPanel.getForm().findField("model.contractType").getValue();
                setTabHidden(select);
                if ("" != select) {
                    if (select == "5") {
                        Ext.getCmp("partyb").getEl().dom.readOnly = false;
                        Ext.getCmp("partynum").getEl().dom.readOnly = false;
                    } else {
                        Ext.getCmp("partyb").getEl().dom.readOnly = true;
                        Ext.getCmp("partynum").getEl().dom.readOnly = true;
                    }
                    Q.contract.store.termsType.baseParams = {
                        "filter_EQ_termsType" : select
                    };
                    Q.contract.store.termsType.load({
                        callback : function(record, options, success) {
                            formPanel.getForm().findField("model.termsId").setValue(formValue);
                        }
                    });
                }
                Ext.Ajax.request({
                    url : dealUrl + "_getTermsContent.action",
                    params : {
                        "termsId" : formValue
                    },
                    success : function(response) {
                        var o = Ext.util.JSON.decode(response.responseText);
                        Ext.getCmp('termsContent').setValue(o.content);
                    },
                    failure : function() {
                        Ext.getCmp('termsContent').setValue("" + "未选择条款" + "");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        },
        centerTab : {
            items : [ formdtlCom, gridDtlFee, formdtlBrand, gridDtlPay, formdtlOp, formdtlExp, formDtlAdmin ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 260,
        width : 320,
        form : {
            labelWidth : 60,
            columnWidth : 1,
            items : [ {
                fieldLabel : '合同编码',
                name : "filter_LIKE_contractNum",
                xtype : "textfield"
            }, {
                fieldLabel : '合同名称',
                name : "filter_LIKE_contractName",
                xtype : "textfield"
            }, {
                fieldLabel : '合同甲方',
                name : "filter_LIKE_partyA",
                xtype : "textfield"
            }, {
                fieldLabel : '合同乙方',
                name : "filter_LIKE_partyB",
                xtype : "textfield"
            }, {
                fieldLabel : '合同类型',
                hiddenName : 'filter_EQ_contractType',
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : '95.6%',
                store : Q.contract.store.contractType,
                clearable : true
            }, {
                fieldLabel : '签约日期',
                format : 'Y-m-d',
                editable : false,
                name : "filter_EQ_signDate",
                xtype : "datefield"
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