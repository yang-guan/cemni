Ext.ns('Q.afterservice');

Q.afterservice.chooseOrgStoreWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";// 需要执行的事件
    config.singleSelect = (config.singleSelect == undefined ? true : config.singleSelect);// 是否单选，默认为单选
    config.param = config.param || {};// 传递的参数

    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "组织机构-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config);
    Q.afterservice.chooseOrgStoreWin.superclass.constructor.call(this, _config);

    var _store = this.gridPanel.getStore();
    if (config.action == "activitySearch") { // 活动管理参与会员筛选归属门店
        _store.baseParams = {
            filter_IN_orgStoreCode : config.arrScopeStore.join(",")
        };
    }
    _store.load();
};

Ext.extend(Q.afterservice.chooseOrgStoreWin, Ext.ux.Window, {
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
                    dataIndex : "orgStoreId",
                    hidden : true
                }, {
                    header : "组织机构编码",
                    dataIndex : "orgStoreCode",
                    width : 60
                }, {
                    header : "组织机构名称",
                    dataIndex : "orgStoreName",
                    width : 180
                }, {
                    header : "机构类型",
                    dataIndex : "orgStoreTypeName",
                    width : 60
                }, {
                    header : "是否有效",
                    width : 60,
                    dataIndex : "isValid",
                    renderer : Q.common.yesOrNotRenderer
                } ]
            },
            store : {
                url : path + "/BusiRegist_getOrgStoreList.action",
                sort : "orgStoreId",
                dir : "desc",
                autoLoad : false
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    _win.doSelect();// 仅选择

                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "编码/名称",
                name : "orgStoreNoName",
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
                    if (cfg.action == "activitySearch") { // 活动管理参与会员筛选归属门店
                        _store.baseParams = {
                            filter_IN_orgStoreCode : cfg.arrScopeStore.join(","),
                            filter_EQ_isValid : tbar.find("name", 'isValid')[0].getValue(),
                            filter_LIKE_orgStoreNo_OR_LIKE_orgStoreName : tbar.find("name", 'orgStoreNoName')[0].getValue()
                        };
                    } else {
                        _store.baseParams = {
                            filter_EQ_isValid : tbar.find("name", 'isValid')[0].getValue(),
                            filter_LIKE_orgStoreNo_OR_LIKE_orgStoreName : tbar.find("name", 'orgStoreNoName')[0].getValue()
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
    }

});