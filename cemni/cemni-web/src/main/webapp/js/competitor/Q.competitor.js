Ext.ns('Q.competitor');
var dealUrl = path + '/Competitor';
var jsArr = [ path + "/js/competitor/Q.competitor.custSurveyWin.js", path + "/js/competitor/Q.competitor.perInfoWin.js", path + "/js/competitor/Q.competitor.chanSurveyWin.js", path + "/js/competitor/Q.competitor.marActivityWin.js", path + "/js/competitor/Q.competitor.adDeployWin.js", path + "/js/competitor/Q.competitor.goodStatusWin.js", path + "/js/competitor/Q.competitor.newStyleWin.js", path + "/plugins/UploadDialog/Neu.ux.UploadDialog.js" ];
Q.loadJs(jsArr);

Q.competitor.selStores = {
    // 竞品名称
    cpnameStore : Q.common.selDict(2301),
    // 调研部门
    deptStore : new Ext.data.JsonStore({
        url : path + "/Org_qryDeptStore.action",
        fields : [ "name", "orgCode" ],
        autoLoad : true
    })
};

// 列表
var gridColumn = [ {
    dataIndex : "cpid",
    hidden : true
}, {
    header : "竞争对手名称",
    dataIndex : "cpnameName"
}, {
    header : "调研日期",
    dataIndex : "surveytime",
    renderer : Q.common.dateRenderer
}, {
    header : "调研部门",
    dataIndex : "surveydeptName"
}, {
    header : "调研城市",
    dataIndex : "surveycity"
}, {
    header : "创建者",
    dataIndex : "cuser"
}, {
    header : "创建日期",
    dataIndex : "ctime",
    renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
    name : 'model.cpid',
    xtype : "hidden"
}, {
    fieldLabel : '竞品名称' + "<font color='red'>*</font>",
    hiddenName : 'model.cpname',
    allowBlank : false,
    xtype : 'uxcombo',
    valueField : "value",
    displayField : "name",
    store : Q.competitor.selStores.cpnameStore
}, {
    fieldLabel : '调研日期',
    name : 'model.surveytime',
    xtype : 'datefield',
    editable : false,
    format : 'Y-m-d',
    value : new Date()
}, {
    fieldLabel : '调研部门' + "<font color='red'>*</font>",
    hiddenName : 'model.surveydept',
    allowBlank : false,
    xtype : "uxcombo",
    valueField : "orgCode",
    displayField : "name",
    store : Q.competitor.selStores.deptStore,
    listeners : {
        "select" : function(c) {
            qrydeptCode(c.getValue());
        }
    }
}, {
    fieldLabel : '调研城市',
    name : 'model.surveycity',
    xtype : "textfield"
}, {
    columnWidth : 1,
    fieldLabel : '品牌定位',
    name : 'model.brandgoal',
    xtype : "textarea",
    height : 61,
    anchor : "97.4%"
}, {
    columnWidth : 1,
    fieldLabel : '商业模式',
    name : 'model.commercialmode',
    xtype : "textarea",
    height : 61,
    anchor : "97.4%"
}, {
    columnWidth : 1,
    fieldLabel : '独特优势/劣势',
    name : 'model.typicalprocon',
    xtype : "textarea",
    height : 61,
    anchor : "97.4%"
} ];

function qrydeptCode(deptCode) {
    var _win = vp.editWin;
    if (deptCode == '0007') {
        _win.showTabItem("goodStatusList");
        _win.showTabItem("newStyleList");

        _win.hideTabItem("custsurveyList");
        _win.hideTabItem("perinfolist");
        _win.hideTabItem("chansurveyList");
        _win.hideTabItem("maractivityList");
        _win.hideTabItem("adDeployList");
        _win.setActiveTab([ "custsurveyList", "perinfolist", "chansurveyList", "maractivityList", "adDeployList" ]);
    } else if (deptCode == "0013") {
        _win.showTabItem("custsurveyList");

        _win.hideTabItem("goodStatusList");
        _win.hideTabItem("perinfolist");
        _win.hideTabItem("chansurveyList");
        _win.hideTabItem("maractivityList");
        _win.hideTabItem("adDeployList");
        _win.hideTabItem("newStyleList");
        _win.setActiveTab([ "goodStatusList", "perinfolist", "chansurveyList", "maractivityList", "adDeployList", "newStyleList" ]);
    } else if (deptCode == "0006001") {
        _win.showTabItem("chansurveyList");

        _win.hideTabItem("goodStatusList");
        _win.hideTabItem("perinfolist");
        _win.hideTabItem("custsurveyList");
        _win.hideTabItem("maractivityList");
        _win.hideTabItem("adDeployList");
        _win.hideTabItem("newStyleList");

        _win.setActiveTab([ "goodStatusList", "perinfolist", "custsurveyList", "maractivityList", "adDeployList", "newStyleList" ]);
    } else if (deptCode == "0006002") {
        _win.showTabItem("chansurveyList");

        _win.hideTabItem("goodStatusList");
        _win.hideTabItem("perinfolist");
        _win.hideTabItem("custsurveyList");
        _win.hideTabItem("maractivityList");
        _win.hideTabItem("adDeployList");
        _win.hideTabItem("newStyleList");

        _win.setActiveTab([ "goodStatusList", "perinfolist", "custsurveyList", "maractivityList", "adDeployList", "newStyleList" ]);
    } else if (deptCode == '0005') {
        _win.showTabItem("adDeployList");
        _win.showTabItem("maractivityList");

        _win.hideTabItem("custsurveyList");
        _win.hideTabItem("perinfolist");
        _win.hideTabItem("chansurveyList");
        _win.hideTabItem("goodStatusList");
        _win.hideTabItem("newStyleList");

        _win.setActiveTab([ "goodStatusList", "perinfolist", "custsurveyList", "chansurveyList", "newStyleList" ]);
    } else if (deptCode == "0011") {
        _win.showTabItem("perinfolist");

        _win.hideTabItem("maractivityList");
        _win.hideTabItem("custsurveyList");
        _win.hideTabItem("adDeployList");
        _win.hideTabItem("chansurveyList");
        _win.hideTabItem("goodStatusList");
        _win.hideTabItem("newStyleList");

        _win.setActiveTab([ "goodStatusList", "adDeployList", "maractivityList", "custsurveyList", "chansurveyList", "newStyleList" ]);
    }
}

var gridDtl1 = {
    tabTitle : "客户调研",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "custsurveyList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "客户调研表ID",
            dataIndex : "crid",
            disabled : true
        }, {
            header : "竞争对手表ID",
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "售后服务",
            dataIndex : "aftersaleservice",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "会员权益",
            dataIndex : "memberrights",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "促销品",
            dataIndex : "promotion",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "会员等级",
            dataIndex : "membergrade",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true,
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "crid",
        url : dealUrl + "_getCustSurvey.action",
        sort : "crid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.custSurveyWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.custSurveyWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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
    tabTitle : "人事信息",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "perinfolist",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "infoid",
            disabled : true
        }, {
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "竞品岗位",
            dataIndex : "job",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "提成方式",
            dataIndex : "commissionway",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "销售额（月）",
            dataIndex : "salespermonth",
            editor : {
                maxLength : 2,
                xtype : 'numberfield'
            }
        }, {
            header : "提点系数",
            dataIndex : "percentage",
            editor : {
                maxLength : 2,
                xtype : 'numberfield'
            }
        }, {
            header : "竞品底薪",
            dataIndex : "basicsalary",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "月综合收入",
            dataIndex : "avgsalary",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "年终奖",
            dataIndex : "yearendaward",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "其他福利",
            dataIndex : "otherprofits",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "社保公积金",
            dataIndex : "socialinsurance",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "infoid",
        url : dealUrl + "_getPerInfo.action",
        sort : "infoid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.perInfoWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.perInfoWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

var gridDtl3 = {
    tabTitle : "渠道调研",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "chansurveyList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "渠道调研表ID",
            dataIndex : "channelSurveyid",
            disabled : true
        }, {
            header : "竞争对手表ID",
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "渠道拓展",
            dataIndex : "cnexpand",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "竞品销售",
            dataIndex : "cpsale",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "竞品扣点",
            dataIndex : "pointdeduction",
            width : 200,
            editor : {
                xtype : 'numberfield'
            }
        }, {
            header : "重大活动/事件",
            dataIndex : "vevents",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "商场调整",
            dataIndex : "malladjust",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "拓展人员变动",
            dataIndex : "salesadjust",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "channelSurveyid",
        url : dealUrl + "_getChanSurvey.action",
        sort : "channelSurveyid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.chanSurveyWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.chanSurveyWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

var gridDtl4 = {
    tabTitle : "终端市场活动",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "maractivityList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "marActivityid",
            disabled : true
        }, {
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "活动名称",
            dataIndex : "promoname",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "活动开始日期",
            dataIndex : "promostart",
            renderer : function(v, m, r) {
                return Ext.util.Format.date(v, 'Y-m-d');
            },
            editor : new Ext.form.DateField({
                allowBlank : true,
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            })
        }, {
            header : "活动结束日期",
            dataIndex : "promoend",
            renderer : function(v, m, r) {
                return Ext.util.Format.date(v, 'Y-m-d');
            },
            editor : new Ext.form.DateField({
                allowBlank : true,
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            })
        }, {
            header : "活动内容",
            dataIndex : "promo",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "评估及应对建议",
            dataIndex : "evaluateadvice",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "市场策略",
            dataIndex : "mktstrategy",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "marActivityid",
        url : dealUrl + "_getMarActivity.action",
        sort : "marActivityid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.marActivityWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.marActivityWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

var gridDtl5 = {
    tabTitle : "品牌广告投放",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "adDeployList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "广告投放表ID",
            dataIndex : "cpadid",
            disabled : true
        }, {
            header : "竞争对手表ID",
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "广告形式",
            dataIndex : "adtype",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "投放开始日期",
            dataIndex : "adstart",
            renderer : function(v, m, r) {
                return Ext.util.Format.date(v, 'Y-m-d');
            },
            editor : new Ext.form.DateField({
                allowBlank : true,
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            })
        }, {
            header : "投放结束日期",
            dataIndex : "adend",
            renderer : function(v, m, r) {
                return Ext.util.Format.date(v, 'Y-m-d');
            },
            editor : new Ext.form.DateField({
                allowBlank : true,
                format : 'Y-m-d',
                xtype : 'datefield',
                editable : false
            })
        }, {
            header : "广告投放费用",
            dataIndex : "adcost",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "评估及应对建议",
            dataIndex : "evaluateadvice",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "市场策略",
            dataIndex : "mktstrategy",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "cpadid",
        url : dealUrl + "_getadDeploy.action",
        sort : "cpadid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.adDeployWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.adDeployWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

var gridDtl6 = {
    tabTitle : "竞品商品动态",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "goodStatusList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            header : "竞品商品动态表ID",
            dataIndex : "cpgsid",
            disabled : true
        }, {
            header : "竞争对手表ID",
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "产品系列",
            dataIndex : "pdseries",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "品类",
            dataIndex : "pdcategory",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "级别",
            dataIndex : "pdgrade",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "铺货量",
            dataIndex : "pddistriamo",
            width : 200,
            editor : {
                maxLength : 100,
                xtype : 'numberfield'
            }
        }, {
            header : "钻石重量",
            dataIndex : "jewelweight",
            width : 100,
            editor : {
                maxLength : 100,
                xtype : 'textfield'
            }
        }, {
            header : "价格",
            dataIndex : "pdprice",
            editor : {
                maxLength : 10,
                xtype : 'numberfield'
            }
        }, {
            header : "卖点分析",
            dataIndex : "pdspanalysis",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "评估及应对",
            dataIndex : "evaluatestrategy",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "cpgsid",
        url : dealUrl + "_getStatus.action",
        sort : "cpgsid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.goodStatusWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.goodStatusWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

var gridDtl7 = {
    tabTitle : "深圳新工艺款式动态",
    xtype : 'uxeditorgrid',
    foreignKey : "competitor_cpid",
    tabClassName : "newStyleList",
    sm : {
        singleSelect : false
    },
    viewConfig : {
        autoScroll : true
    },
    cm : {
        defaultSortable : true,
        defaults : {
            menuDisabled : true
        },
        columns : [ {
            dataIndex : "cpszid",
            disabled : true
        }, {
            dataIndex : "_cpid",
            disabled : true
        }, {
            header : "品类",
            dataIndex : "szcategory",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "工艺",
            dataIndex : "sztech",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "上市日期",
            dataIndex : "sztimetomkt",
            renderer : function(v, m, r) {
                return Ext.util.Format.date(v, 'Y-m-d');
            },
            editor : new Ext.form.DateField({
                allowBlank : true,
                format : 'Y-m-d',
                xtype : 'datefield'
            })
        }, {
            header : "批发政策",
            dataIndex : "szwholesalepolicy",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "零售动态",
            dataIndex : "szsalestatus",
            width : 200,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "备注",
            dataIndex : "comments",
            width : 300,
            editor : {
                xtype : 'textfield'
            }
        }, {
            header : "附件",
            dataIndex : "uploadFile4View",
            width : 200,
            renderer : function(v) {
                if (!!v) {
                    if (v[1] != null && v[1] != "") {
                        var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + v[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + v[0] + "</a>]";
                        return a;
                    } else {
                        return v[0];
                    }
                }
            }
        }, {
            dataIndex : "uploadFileGroupId",
            hidden : true
        } ]
    },
    listeners : {
        "beforeedit" : function(e) {
            return vp.editWin.beforeedit();
        }
    },
    pageSize : 0,
    store : {
        idProperty : "cpszid",
        url : dealUrl + "_getnewStyle.action",
        sort : "cpszid",
        autoLoad : false,
        dir : "desc"
    },
    tbar : [ {
        text : "添加",
        iconCls : "icon-add",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel).getStore();
            var _win = new Q.competitor.newStyleWin({
                bz : "add"
            });
            _win.on("submit", function(r) {
                grid.add(r);
            });
            _win.show();
        }
    }, {
        text : "编辑",
        iconCls : "icon-edit",
        handler : function() {
            var grid = this.findParentByType(Ext.grid.GridPanel);
            var sm = grid.getSelectionModel();
            if (Ext.isEmpty(sm.getSelected())) {
                Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                return false;
            }
            var _win = new Q.competitor.newStyleWin({
                bz : "edit",
                sm : sm.getSelected()
            });
            _win.on("submit", function(r) {
                vp.editWin.deleteDetail(grid);
                grid.getStore().add(r);
            });
            _win.show();
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

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '竞争对手管理',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        activeTab : 0,
        store : {
            idProperty : 'cpid',
            url : dealUrl + '_getJson.action',
            dir : "desc"
        },
        listEditStateFn : []
    },
    addAfter : function() {
        vp.editWin.showTabItem("goodStatusList");
        vp.editWin.showTabItem("newStyleList");
        vp.editWin.showTabItem("custsurveyList");
        vp.editWin.showTabItem("perinfolist");
        vp.editWin.showTabItem("chansurveyList");
        vp.editWin.showTabItem("maractivityList");
        vp.editWin.showTabItem("adDeployList");
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        nextBillState : 'New',
        maximized : true,
        form : {
            labelWidth : 90,
            items : editFormItems,
            columnWidth : '0.5',
            height : 300,
            setFormValueAfter : function(formPanel) {
                var _form = formPanel.getForm();
                var code = _form.findField("model.surveydept").getValue();
                qrydeptCode(code);
                var id = _form.findField("model.cpid").getValue();
                if (id != null && id != "") {
                    _form.findField("model.surveydept").setReadOnly(true);
                }
            }
        },
        centerTab : {
            items : [ gridDtl1, gridDtl2, gridDtl3, gridDtl4, gridDtl5, gridDtl6, gridDtl7 ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 250,
        width : 300,
        form : {
            columnWidth : 1,
            items : [ {
                fieldLabel : '竞争对手名称',
                hiddenName : "filter_LIKE_cpname",
                xtype : 'uxcombo',
                clearable : true,
                valueField : "value",
                displayField : "name",
                store : Q.competitor.selStores.cpnameStore,
                width : 180
            }, {
                fieldLabel : '调研部门',
                hiddenName : "filter_LIKE_surveydept",
                clearable : true,
                xtype : "uxcombo",
                valueField : "orgCode",
                displayField : "name",
                store : Q.competitor.selStores.deptStore
            }, {
                fieldLabel : '调研城市',
                name : "filter_LIKE_surveycity",
                xtype : "textfield"
            }, {
                fieldLabel : "调研开始日期",
                name : 'filter_GE_surveytime',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_surveytime = _form.findField('filter_GE_surveytime').getValue();
                        var filter_LE_surveytime = _form.findField("filter_LE_surveytime").getValue();
                        if (filter_GE_surveytime && filter_LE_surveytime) {
                            if (filter_GE_surveytime > filter_LE_surveytime) {
                                Q.warning("调研开始日期不能大于调研结束日期！");
                                _form.findField('filter_GE_surveytime').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "调研结束日期",
                name : 'filter_LE_surveytime',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_surveytime = _form.findField('filter_GE_surveytime').getValue();
                        var filter_LE_surveytime = _form.findField("filter_LE_surveytime").getValue();
                        if (filter_GE_surveytime && filter_LE_surveytime) {
                            if (filter_GE_surveytime > filter_LE_surveytime) {
                                Q.warning("调研结束日期不能小于调研开始日期！");
                                _form.findField('filter_LE_surveytime').setValue("");
                            }
                        }
                    }
                }
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