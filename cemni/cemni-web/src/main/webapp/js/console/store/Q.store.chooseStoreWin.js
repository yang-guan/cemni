Ext.ns('Q.store');
Q.store.chooseStoreWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";// 需要执行的事件
    config.singleSelect = (config.singleSelect == undefined ? true : config.singleSelect);// 是否单选，默认为单选
    config.param = config.param || {};// 传递的参数

    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "门店-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 800,
        height : 450
    }, config);
    Q.store.chooseStoreWin.superclass.constructor.call(this, _config);

    // 加载表格数据(第一次进入)
    var _store = this.gridPanel.getStore();
    if (config.action == "org2store") {// 门店2组织架构
        _store.baseParams = {
            filter_SIZE_orgExt : 0
        };
    } else if (config.action == "report2store") {// 报表2门店
        _store.baseParams = {
            filter_EQ_org_parentId : config.parentId
        };
    } else if (config.action == "franchiseeAudit") {// 加盟商稽核管理
        _store.baseParams = {
            filter_EQ_franchisee_franchiseeId : config.franchiseeId
        };
    } else if (config.action == "franchiseeProfit") {// 加盟商盈利状况
        _store.baseParams = {
            filter_EQ_franchisee_franchiseeId : config.franchiseeId
        };
        var arrStore = config.storeScope.split(",");
        var sm = _gridPanel.getSelectionModel();
        _store.load({
            callback : function() {
                var cnt = _store.getCount();
                for (var i = 0; i < cnt; i++) {
                    var _storeId = _store.getAt(i).data.storeId;
                    for (var j = 0; j < cnt; j++) {
                        if (arrStore[j] == _storeId) {
                            sm.selectRow(i, true);
                        }
                    }
                }
                Ext.getBody().unmask();
            }
        });
    } else if (config.action == "activitySearch") { // 活动管理参与会员筛选归属门店
        _store.baseParams = {
            filter_IN_storeNo : config.arrScopeStore.join(",")
        };
    }
    if (config.action != "franchiseeProfit") {
        _store.load();
    }
};

Ext.extend(Q.store.chooseStoreWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : cfg.singleSelect
            },
            cm : {
                columns : [ {
                    dataIndex : "storeId",
                    hidden : true
                }, {
                    header : "门店编码",
                    width : 8,
                    dataIndex : "storeNo"
                }, {
                    header : "门店名称",
                    width : 20,
                    dataIndex : "name"
                }, {
                    header : "门店属性",
                    width : 8,
                    dataIndex : "attrName"
                }, {
                    header : "门店形态",
                    width : 8,
                    dataIndex : "formName"
                }, {
                    header : "开店日期",
                    width : 9,
                    dataIndex : "shopupDate",
                    renderer : Q.common.dateRenderer
                }, {
                    header : "是否有效",
                    width : 8,
                    dataIndex : "isValid",
                    renderer : Q.common.yesOrNotRenderer
                }, {
                    header : "大区",
                    width : 8,
                    dataIndex : "bigAreaName"
                }, {
                    header : "区域",
                    width : 8,
                    dataIndex : "areaName"
                } ]
            },
            store : {
                url : path + "/Store_getJson.action",
                sort : "isValid,bigAreaNo,areaNo,attr,form,storeId",
                dir : "desc,asc,asc,asc,asc,asc",
                autoLoad : false
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    if (cfg.action == "org2store") {// 门店2组织架构
                        _win.org2storeSubmit();
                    } else {
                        _win.doSelect();// 仅选择
                    }
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "门店编码/门店名称",
                name : "storeNoName",
                width : 150
            }, "-", {
                emptyText : "是否有效",
                name : "isValid",
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore,
                clearable : true,
                width : 100
            }, "-", {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    var _isValid = tbar.find("name", 'isValid')[0].getValue();
                    var _storeNoName = tbar.find("name", 'storeNoName')[0].getValue();

                    if (cfg.action == "org2store") {// 门店2组织架构
                        _store.baseParams = {
                            filter_SIZE_orgExt : 0,
                            filter_EQ_isValid : _isValid,
                            filter_LIKE_storeNo_OR_LIKE_name : _storeNoName
                        };
                    } else if (cfg.action == "report2store") {// 报表2门店
                        _store.baseParams = {
                            filter_EQ_org_parentId : cfg.parentId,
                            filter_EQ_isValid : _isValid,
                            filter_LIKE_storeNo_OR_LIKE_name : _storeNoName
                        };
                    } else if (cfg.action == "franchiseeProfit" || cfg.action == 'franchiseeAudit') {// 加盟商盈利状况,加盟商稽核管理
                        _store.baseParams = {
                            filter_EQ_franchisee_franchiseeId : cfg.franchiseeId,
                            filter_EQ_isValid : _isValid,
                            filter_LIKE_storeNo_OR_LIKE_name : _storeNoName
                        };
                    } else if (cfg.action == "activitySearch") { // 活动管理参与会员筛选归属门店
                        _store.baseParams = {
                            filter_IN_storeNo : cfg.arrScopeStore.join(","),
                            filter_EQ_isValid : _isValid,
                            filter_LIKE_storeNo_OR_LIKE_name : _storeNoName
                        };
                    } else {
                        _store.baseParams = {
                            filter_EQ_isValid : _isValid,
                            filter_LIKE_storeNo_OR_LIKE_name : _storeNoName
                        };
                    }
                    _store.load();
                }
            } ]
        });
    },

    doSelect : function() {
        var sms = this.gridPanel.getSelectionModel().getSelections();
        if (sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        if (this.config.singleSelect) {
            this.fireEvent("submit", sms[0]);
        } else {
            this.fireEvent("submit", sms);
        }
        this.hide();
    },

    // 门店2组织架构-关联
    org2storeSubmit : function() {
        var sms = this.gridPanel.getSelectionModel().getSelections();
        if (sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var ids = [];
        for (var i = 0; i < sms.length; i++) {
            ids.push(sms[i].get("storeId"));
        }

        var _win = this;
        Ext.getBody().submitMask();
        Ext.Ajax.request({
            url : org_dealUrl + "_org2store.action",
            params : {
                ids : ids,
                "model.parentId" : _win.config.param.parentId
            },
            success : function(response) {
                if (!Ext.decode(response.responseText).success) {
                    Q.error($("message.submit.failure"));
                    return;
                } else {
                    _win.hide();
                    _win.fireEvent("submit");
                    Q.tips(Q.color($("message.save.success"), "red"));
                }
            },
            failure : function() {
                Q.error($("message.submit.failure") + "<br/>" + $("message.system.disconnect"));
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    }
});