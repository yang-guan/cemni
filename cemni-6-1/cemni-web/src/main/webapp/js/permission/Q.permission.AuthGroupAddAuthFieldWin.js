Ext.ns("Q.permission");
Q.permission.AuthGroupAddAuthFieldWin = function(config) {
    config = config || {};
    Ext.applyIf(config, {
        dealUrl : path + "/permission/AuthField",
        moduleCode : "authCode"
    });
    var grid = this.createGrid(false == config.singleSelect);

    Q.permission.AuthGroupAddAuthFieldWin.superclass.constructor.call(this, {
        title : $("authField.addAuthField"),
        layout : "border",
        width : 600,
        height : 450,
        items : [ grid ],
        listeners : {
            hide : function() {
                grid.getSelectionModel().clearSelections();
            }
        }
    });
    this.addEvents("select");

    if (Ext.isFunction(config.select)) {
        this.on("select", config.select);
    }
};

var keys = [];
Ext.extend(Q.permission.AuthGroupAddAuthFieldWin, Ext.ux.Window, {
    createGrid : function(singleSelect) {
        var win = this;
        var grid = new Ext.ux.grid.GridPanel({
            border : true,
            style : "padding:2px 2px 2px 0",
            sm : {
                singleSelect : singleSelect
            },
            viewConfig : {
                forceFit : true
            },
            store : {
                url : path + '/permission/AuthField_getList.action',
                baseParams : {
                    "keys" : keys
                }
            },
            cm : {
                defaultSortable : true,
                columns : [ {
                    header : $("authField.authCode"),
                    sortable : true,
                    dataIndex : 'authCode'
                }, {
                    header : $("authField.fieldName"),
                    sortable : false,
                    dataIndex : 'fieldName'
                }, {
                    header : $("authField.fieldCode"),
                    disabled : true,
                    sortable : true,
                    dataIndex : 'fieldCode'
                }, {
                    header : $("authField.fieldType"),
                    disabled : true,
                    sortable : true,
                    dataIndex : 'fieldType'
                }, {
                    header : $("authField.fieldLength"),
                    disabled : true,
                    sortable : true,
                    dataIndex : 'fieldLength'
                }, {
                    header : $("authField.tableName"),
                    disabled : true,
                    sortable : true,
                    dataIndex : 'tableName'
                } ]
            },
            tbar : [ {
                name : "save",
                text : $("label.select"),
                iconCls : "icon-save",
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var selections = grid.getSelectionModel().getSelections();

                    if (selections.length < 1) {
                        return;
                    }
                    win.fireEvent("select", grid, selections);
                    win.hide();
                }
            }, "->", {
                id : "searchParams",
                xtype : "textfield",
                emptyText : $("authField.authCode") + "/" + $("authField.fieldCode") + "/" + $("authField.tableName"),
                width : 180
            }, {
                text : $("button.search"),
                iconCls : "icon-search",
                handler : function() {
                    var store = grid.getStore();
                    searchValue = Ext.getCmp("searchParams").getValue();
                    store.baseParams.filter_LIKE_fieldCode_OR_tableName_OR_authCode = searchValue;
                    store.load();
                }
            } ],
            listeners : {
                "afterrender" : function(g) {
                    var sm = g.getSelectionModel();
                    g.getStore().on("load", function() {
                        sm.clearSelections();
                        var tbar = grid.getTopToolbar();
                        tbar.getObj = function(name) {
                            return this.find("name", name)[0];
                        };
                        tbar.getObj("save").setDisabled(true);
                    });
                },
                "click" : function() {
                    var record = grid.getSelectionModel().getSelections();
                    var tbar = grid.getTopToolbar();
                    tbar.getObj = function(name) {
                        return this.find("name", name)[0];
                    };
                    if (Ext.isEmpty(record)) {
                        tbar.getObj("save").setDisabled(true);
                    } else {
                        tbar.getObj("save").setDisabled(false);
                    }
                }
            }
        });
        return grid;
    }
});