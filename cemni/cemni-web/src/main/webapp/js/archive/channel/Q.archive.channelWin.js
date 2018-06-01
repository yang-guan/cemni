/**
 * 选择渠道商
 */
Ext.ns('Q.archive');

Q.archive.channelWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";// 需要执行的事件
    config.multiple = !config.multiple;// 是否多选：0否、1是
    config.param = config.param || {};// 传递的参数

    this.retConfig = {};
    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "渠道商-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config);
    Q.archive.channelWin.superclass.constructor.call(this, _config);
};
Ext.extend(Q.archive.channelWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
        	this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : cfg.multiple
            },
            cm : {
                columns : [ {
                    dataIndex : "channelId",
                    hidden : true
                }, {
                    header : "渠道商编码",
                    dataIndex : "channelno",
                    width : 15
                }, {
                    header : "渠道商名称",
                    dataIndex : "channelname",
                    width : 50
                }, {
                    header : "渠道商简称",
                    dataIndex : "name",
                    width : 35
                } ]
            },
            store : {
                url : path + "/Channel_getJson.action",
                sort : "channelId",
                dir : "desc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    if (cfg.action == "doSelect") {// 仅选择列表项返回给父页面
                        _win.doSelect();
                    } else {
                        // TODO
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
                name : "channelCodeOrName",
                emptyText : "渠道商编码/渠道商名称",
                width : 220
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams.filter_LIKE_channelno_OR_LIKE_channelname =  tbar.find("name", 'channelCodeOrName')[0].getValue();
                    _store.load({
                        params : {
                            start : 0,
                            limit : 20
                        }
                    });
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
        if (this.config.multiple) {
            this.fireEvent("submit", sms[0]);
        } else {
            this.fireEvent("submit", sms);
        }
        this.hide();
    }

});