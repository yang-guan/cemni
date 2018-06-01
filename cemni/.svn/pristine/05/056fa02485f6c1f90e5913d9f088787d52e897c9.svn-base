Ext.ns('Q.activity');

Q.activity.chooseStoreWin = function() {
    this.retConfig = {};
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    Q.activity.chooseStoreWin.superclass.constructor.call(this, {
        title : "发起部门-选择",
        layout : "border",
        items : [ _gridPanel ],
        width : 600,
        height : 450
    });
};

Q.activity.selStores = {
    // 组织机构类型
    typeStore : Q.common.selDict(8100),
};

Ext.extend(Q.activity.chooseStoreWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function() {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            pageSize : 0,
            sm : {
                singleSelect : true
            },
            cm : {
                columns : [ {
                    header : "组织机构编码",
                    dataIndex : "orgcode",
                    width : 180
                }, {
                    header : "组织机构名称",
                    dataIndex : "name",
                    width : 180
                }, {
                    header : "机构类型",
                    dataIndex : "typeName",
                    width : 180,
                    editor : {
                        editable : false,
                        xtype : "uxcombo",
                        store : Q.activity.selStores.typeStore
                    }
                } ]
            },
            store : {
                url : path + "/Activity_getOrgJson.action",
                sort : "type",
                dir : "asc",
                baseParams : {
                    filter_IN_type : [ 2, 3, 4, 5 ]
                }
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
                emptyText : "编码/名称/机构类型",
                name : "orgCode",
                width : 230
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams = {
                        filter_EQ_orgCode_OR_LIKE_name_OR_LIKE_typeName : tbar.find("name", 'orgCode')[0].getValue()
                    };
                    _store.load();
                }
            } ]
        });
    },

    doSelect : function() {
        var sm = this.gridPanel.getSelectionModel();
        if (sm.getSelections().length == 0) {
            Q.tips("请选择一个部门");
            return false;
        }
        this.retConfig = sm.getSelected();
        this.fireEvent("submit");
        this.hide();
    }

});