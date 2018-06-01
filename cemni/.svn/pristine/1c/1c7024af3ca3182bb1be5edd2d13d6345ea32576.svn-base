Ext.ns('Q.partner');
/**
 * 异业联盟弹出查询窗口 2016年12月16日09:52:09 郭念伦
 */
Q.partner.chooseStoreWin = function() {
    this.retConfig = {};
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    Q.partner.chooseStoreWin.superclass.constructor.call(this, {
        title : "异业联盟选择",
        layout : "border",
        items : [ _gridPanel ],
        width : 800,
        height : 350
    });
};
Ext.extend(Q.partner.chooseStoreWin, Ext.ux.Window, {
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
            viewConfig : {
                forceFit : false
            },
            cm : {
                columns : [ {
                    Qheader : "异业联盟编码",
                    header : "异业联盟编码",
                    dataIndex : "partnerno",
                    width : 180
                }, {
                    Qheader : "异业联盟名称",
                    header : "异业联盟名称",
                    dataIndex : "partnername",
                    width : 180
                }, {
                    QfieldLabel : "异业联盟简称",
                    header : "异业联盟简称",
                    dataIndex : "name",
                    width : 180
                }, {
                    QfieldLabel : "实际控制人",
                    header : "实际控制人",
                    dataIndex : "person",
                    width : 180
                } ]
            },
            store : {
                url : path + "/Partner_getJson.action",
                dir : "desc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    _win.submitChoose();
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "异业联盟编码/异业联盟名称/实际控制人",
                name : "fraCode",
                width : 230
            },
            {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams.filter_EQ_partnerno_OR_LIKE_partnername_OR_LIKE_person = tbar.find("name", 'fraCode')[0].getValue();
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
    submitChoose : function() {
        var sm = this.gridPanel.getSelectionModel().getSelections();
        if (sm.length == 0) {
            Q.tips("<font color='red'>请选择一个异业联盟</font>");
            return false;
        }
        this.hide();
        this.fireEvent("submit",sm[0]);
    }
});
