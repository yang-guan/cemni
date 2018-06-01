Ext.ns('Q.archive');
Q.archive.chooseIndiWin = function(config) {
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "个人会员-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 750,
        height : 450
    }, config || {});
    Q.archive.chooseIndiWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.archive.chooseIndiWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function() {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : true
            },
            cm : {
                defaultSortable : false,
                columns : [ {
                    dataIndex : "individCustId",
                    hidden : true
                }, {
                    dataIndex : "fresh",
                    hidden : true
                }, {
                    dataIndex : "purposePrice",
                    hidden : true
                }, {
                    dataIndex : "purposeProduct",
                    hidden : true
                }, {
                    dataIndex : "purposeCategory",
                    hidden : true
                }, {
                    dataIndex : "purposeDay",
                    hidden : true,
                    format : 'Y-m-d'
                }, {
                    header : "会员卡号",
                    dataIndex : "cardNo",
                }, {
                    header : "客户名称",
                    dataIndex : "name"
                }, {
                    header : "客户类型",
                    dataIndex : "typeName"
                }, {
                    header : "会员等级",
                    dataIndex : "lvName"
                }, {
                    header : "手机号码",
                    dataIndex : "mobile"
                }, {
                    header : "活跃状态",
                    dataIndex : "activeName"
                }, {
                    header : "新老会员",
                    dataIndex : "freshName"
                } ]
            },
            store : {
                url : path + "/IndividCust_getJsonAll.action",
                sort : "individCustId",
                autoLoad : false,
                dir : "desc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    _win.doSelect();
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "会员卡号/手机号码/客户名称",
                name : "cardNoOrMobileOrName",
                width : 180
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    var _param = tbar.find("name", 'cardNoOrMobileOrName')[0].getValue();
                    if (!_param) {
                        return;
                    }
                    if (isNaN(_param)) {
                        _store.baseParams = {
                            filter_EQ_cardNo_OR_name : _param
                        };
                    } else {
                        _store.baseParams = {
                            filter_EQ_cardNo_OR_mobile_OR_name : _param
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
        this.fireEvent("submit", sms[0]);
        this.hide();
    }
});