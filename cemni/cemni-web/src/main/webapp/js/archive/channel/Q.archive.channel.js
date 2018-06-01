/**
 * 渠道商管理
 * 
 * @author zzy
 * @date 2016年11月22日 上午10:02:03
 */
Ext.ns('Q.Channel');
var file_size_limit = 5;
var dealUrl = path + '/Channel';
var jsArr = path + '/plugins/UploadDialog/Neu.ux.UploadDialog.js';
Q.loadJs(jsArr);

Q.Channel.selStores = {
    // 渠道商类型
    typeStore : Q.common.selDict(1500),
    // 性别
    sexFra : Q.common.selDict(1100)
};

// 列表
var gridColumn = [ {
    header : "渠道商编码",
    width : 10,
    dataIndex : "channelno"
}, {
    header : "渠道商名称",
    width : 35,
    dataIndex : "channelname"
}, {
    header : "渠道商简称",
    width : 27,
    dataIndex : "name"
}, {
    header : "渠道商类型",
    width : 10,
    dataIndex : "typeName"
}, {
    header : "是否有效",
    dataIndex : "isValid",
    width : 8,
    renderer : Q.common.yesOrNotRenderer
}, {
    header : "创建日期",
    width : 10,
    dataIndex : "cdate",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    labelWidth : 80,
    columnWidth : 1,
    layout : 'column',
    xtype : 'fieldset',
    anchor : "-3",
    title : '基本信息',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            name : 'model.channelId',
            xtype : "hidden",
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '渠道商编码<font color="red">*</font>',
            name : 'model.channelno',
            emptyText : '系统自动生成',
            readOnly : true,
            xtype : "textfield",
            allowBlank : false,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '渠道商名称<font color="red">*</font>',
            name : 'model.channelname',
            allowBlank : false,
            xtype : "textfield",
            readOnly : true,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '渠道商简称<font color="red">*</font>',
            allowBlank : false,
            name : 'model.name',
            xtype : "textfield",
            readOnly : true,
            anchor : "85%"
        }
    }, {
        items : {
            fieldLabel : '渠道商类型<font color="red">*</font>',
            hiddenName : 'model.type',
            anchor : "86%",
            xtype : "uxcombo",
            valueField : "value",
            displayField : "name",
            allowBlank : false,
            emptyText : '请选择',
            store : Q.Channel.selStores.typeStore
        }
    }, {
        items : {
            fieldLabel : '实际控制人',
            name : 'model.person',
            xtype : "textfield",
            anchor : "85%",
        }
    } ]
}, {
    labelWidth : 80,
    columnWidth : 1,
    xtype : 'fieldset',
    anchor : "-3",
    title : '拓展信息',
    layout : 'column',
    defaults : {
        layout : 'form',
        border : false,
        columnWidth : .33
    },
    items : [ {
        items : {
            fieldLabel : '增值税登记号',
            name : 'model.vatno',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '营业执照编号',
            name : 'model.licenceno',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '社会信用代码',
            name : 'model.creditCode',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '组织机构代码',
            name : 'model.orgNo',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '法人代表',
            name : 'model.corporate',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '所在城市',
            name : 'model.city',
            anchor : "85%",
            xtype : "textfield",
        }
    }, {
        items : {
            fieldLabel : '业务联系人',
            name : 'model.business',
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        items : {
            fieldLabel : '联系电话',
            name : 'model.telephone',
            regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
            regexText : "请输入正确的联系人电话",
            anchor : "85%",
            xtype : "textfield"
        }
    }, {
        columnWidth : 1,
        items : {
            fieldLabel : '公司地址',
            name : 'model.address',
            xtype : "textfield",
            anchor : "94%"
        }
    }, {
        items : [ {
            fieldLabel : $("label.Annex"),// 上传附件
            xtype : "button",
            anchor : "85%",
            Qtext : "添加附件",
            name : 'uploadBtn',
            id : "updateload",
            text : $("label.AddUpLoadFile"),
            columnWidth : .5,
            handler : function() {
                var formPanel = vp.editWin.formPanel;
                var uploadFileGroupId = formPanel.form.findField("model.uploadFileGroupId").getValue();
                vp.openUploadWindows("", uploadFileGroupId, "uploadFile4View", formPanel, "");
            }
        }, {
            QfieldLabel : '附件上传ID',
            name : 'model.uploadFileGroupId',
            xtype : "hidden"
        }, {
            xtype : 'displayfield',
            name : 'uploadFile4View',
            QfieldLabel : '附件查看',
            fieldLabel : $("label.AttachmentsCheck")

        } ]
    } ]
} ];

var gridDtl1 = {
    tabTitle : "渠道信息",
    xtype : 'uxeditorgrid',
    foreignKey : "channel_channelId",
    tabClassName : "channelinfo",
    viewConfig : {
        autoScroll : true
    },
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "渠道信息ID",
            dataIndex : "channelinfoId",
            disabled : true
        }, {
            dataIndex : "_channelId",
            disabled : true
        }, {
            header : "地址",
            dataIndex : "address",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "广告位",
            dataIndex : "advertisement",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "坪效",
            dataIndex : "efficient",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "同类品牌销售及盈利",
            dataIndex : "profitability",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "平面路线图等工程信息",
            dataIndex : "engineeringinfo",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "停车位",
            dataIndex : "parking",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "回款及时性",
            dataIndex : "payment",
            width : 90,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "春调日期",
            dataIndex : "sdate",
            width : 90,
            editor : {
                format : "Y-m-d",
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "秋调日期",
            dataIndex : "adate",
            width : 90,
            editor : {
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            header : "调位档案",
            dataIndex : "archives",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            width : 200,
            editor : {
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
        idProperty : "channelinfoId",
        url : dealUrl + "_getRel.action",
        sort : "channelinfoId",
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

var gridDtl2 = {
    tabTitle : "联系人信息",
    xtype : 'uxeditorgrid',
    foreignKey : "channel_channelId",
    tabClassName : "contact",
    viewConfig : {
        autoScroll : true
    },
    sm : {
        singleSelect : false
    },
    cm : {
        defaultSortable : false,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "联系人信息ID",
            dataIndex : "contactId",
            disabled : true
        }, {
            header : "渠道商ID",
            dataIndex : "_channelId",
            disabled : true
        }, {
            header : "姓名",
            dataIndex : "name",
            width : 70,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "性别",
            dataIndex : "sex",
            width : 50,
            editor : {
                editable : false,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                emptyText : '请选择',
                store : Q.Channel.selStores.sexFra
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.Channel.selStores.sexFra, v);
            }
        }, {
            header : "职务",
            dataIndex : "duty",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "联系人地址",
            dataIndex : "addr",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "手机号码",
            dataIndex : "mobile",
            width : 90,
            editor : {
                regex : /^(1\d{10})$/,
                regexText : "请输入正确的手机号码",
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "固定号码",
            dataIndex : "phone",
            width : 100,
            editor : {
                regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
                regexText : "请输入正确的电话号码",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "生日",
            dataIndex : "birthday",
            width : 90,
            editor : {
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.dateRenderer(v);
            }
        }, {
            dataIndex : "qq",
            width : 90,
            editor : {
                xtype : 'numberfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "微信",
            dataIndex : "weChat",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "邮箱",
            dataIndex : "mail",
            width : 120,
            editor : {
                regex : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
                regexText : "请输入正确的邮箱",
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "关联联系人",
            dataIndex : "rleman",
            width : 100,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "爱好/禁忌",
            dataIndex : "hobby",
            width : 150,
            editor : {
                xtype : 'textfield'
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return v;
            }
        }, {
            header : "是否发送短信",
            dataIndex : "isSms",
            width : 90,
            editor : {
                editable : false,
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore
            },
            renderer : function(v, m, r) {
                vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
                return Q.common.getSelText(Q.common.yesOrNotStore, v, 'text', 'value');
            }
        }, {
            header : "备注",
            dataIndex : "remark",
            width : 200,
            editor : {
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
        idProperty : "contactId",
        url : dealUrl + "_getRel1.action",
        sort : "contactId",
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
            if (this.isPrototypeOf(grid)) {
                return;
            }
            vp.editWin.deleteDetail(grid);
        }
    } ]
};

var gridDtl3 = {
    tabTitle : "合同信息",
    xtype : 'uxeditorgrid',
    tabClassName : "contract",
    cm : {
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "contractId",
            disabled : true
        }, {
            header : "合同编号",
            dataIndex : "contractNum",
            width : 80
        }, {
            header : "合同名称",
            dataIndex : "contractName",
            width : 110
        }, {
            header : "签约日期",
            renderer : Q.common.dateRenderer,
            dataIndex : "signDate",
            width : 80
        }, {
            header : "生效日期",
            renderer : Q.common.dateRenderer,
            dataIndex : "effDate",
            width : 80
        }, {
            header : "失效日期",
            renderer : Q.common.dateRenderer,
            dataIndex : "invDate",
            width : 80
        }, {
            header : "签约地址",
            dataIndex : "addr",
            width : 200
        } ]
    },
    pageSize : 20,
    store : {
        idProperty : "contractId",
        url : path + "/Contract_getJson.action",
        sort : "contractId",
        autoLoad : false,
        dir : "desc"
    },
    vpAfterRender : function() {
        return 'view';
    }
};

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '渠道商',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'channelId',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        },
        listEditStateFn : [ {
            'edit' : function(r) {
                return true;
            }
        }, {
            'view' : true

        } ],
        viewAfter : function(grid, selectids, win) {
            vp.editWin.showTabItem("contract");
        },
        editAfter : function(grid, selectids, win) {
            vp.editWin.hideTabItem("contract");
            vp.editWin.setActiveTab([ "contract" ]);
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            items : editFormItems,
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _store = vp.editWin.getCompByTabClassName("contract").getStore();
                _store.baseParams = {
                    "filter_EQ_partyNum" : formPanel.getForm().findField("model.channelno").getValue()
                };
                _store.load();
            }
        },
        centerTab : {
            items : [ gridDtl2, gridDtl1, gridDtl3 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 210,
        width : 330,
        form : {
            labelWidth : 80,
            columnWidth : 1,
            items : [ {
                fieldLabel : '渠道商编码',
                name : "filter_LIKE_channelno",
                xtype : "textfield"
            }, {
                fieldLabel : '渠道商名称',
                name : "filter_LIKE_channelname",
                xtype : "textfield"
            }, {
                fieldLabel : '渠道商简称',
                name : "filter_LIKE_name",
                xtype : "textfield"
            }, {
                fieldLabel : '渠道商类型',
                hiddenName : "filter_EQ_type",
                clearable : true,
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.Channel.selStores.typeStore
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