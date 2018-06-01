Ext.ns('Q.archive');
Q.archive.supplierWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";
    config.multiple = !config.multiple;
    config.param = config.param || {};

    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "供应商-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config);
    Q.archive.supplierWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.archive.supplierWin, Ext.ux.Window, {
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
                    dataIndex : "supplierid",
                    hidden : true
                }, {
                    header : "供应商编码",
                    dataIndex : "supplierno",
                    width : 15
                }, {
                    header : "供应商名称",
                    dataIndex : "suppliername",
                    width : 40
                }, {
                    header : "供应商简称",
                    dataIndex : "name",
                    width : 45
                } ]
            },
            store : {
                url : path + "/Supplier_getJson.action",
                sort : "supplierid",
                dir : "asc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    if (cfg.action == "doSelect") {
                        _win.doSelect();
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
                name : "supplierCodeOrName",
                emptyText : "供应商编码/供应商名称",
                width : 220
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams.filter_LIKE_supplierno_OR_LIKE_suppliername = tbar.find("name", 'supplierCodeOrName')[0].getValue();
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
        if (this.config.multiple) {
            this.fireEvent("submit", sms[0]);
        } else {
            this.fireEvent("submit", sms);
        }
        this.hide();
    }
});