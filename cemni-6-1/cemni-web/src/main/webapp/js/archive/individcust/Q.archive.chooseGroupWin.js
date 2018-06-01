Q.archive.chooseGroupWin = function() {
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    Q.archive.chooseGroupWin.superclass.constructor.call(this, {
        title : "所属团体",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    });
};

Ext.extend(Q.archive.chooseGroupWin, Ext.ux.Window, {
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
                columns : [ {
                    dataIndex : "groupCustId",
                    hidden : true
                }, {
                    header : "团体编码",
                    dataIndex : "cardNo",
                    width : 30
                }, {
                    header : "团体名称",
                    dataIndex : "groupName",
                    width : 70
                } ]
            },
            store : {
                url : path + "/GroupCust_getJson.action",
                sort : "groupCustId",
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
                emptyText : "团队名称/编码",
                name : "_groupName",
                width : 200
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams.filter_LIKE_groupName_OR_LIKE_cardNo = tbar.find("name", '_groupName')[0].getValue();
                    _store.load();
                }
            } ]
        });
    },
    doSelect : function() {
        var sm = this.gridPanel.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        this.fireEvent("submit", sm);
        this.hide();
    }
});