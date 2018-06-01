Q.contact.chooseLinkmanWin = function(contractId) {
    this.contractId = contractId;
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    Q.contact.chooseLinkmanWin.superclass.constructor.call(this, {
        title : "联系人选择",
        layout : "border",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    });
};

Ext.extend(Q.contact.chooseLinkmanWin, Ext.ux.Window, {
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
                    dataIndex : "contactId",
                    disabled : true
                }, {
                    header : "联系人名称",
                    dataIndex : "name"
                }, {
                    header : "手机号码",
                    dataIndex : "mobile"
                }, {
                    header : "联系人类型",
                    dataIndex : "typeName"
                } ]
            },
            store : {
                url : path + "/Contract_getAllContact.action?filter_EQ_contractId=" + this.contractId,
            },
            tbar : [ {
                text : "模版下载",
                iconCls : "icon-excel",
                handler : function() {
                    window.open(path + "/Common_downLoadExcelTpl.action?fileName=ContractLinkman.xlsx");
                }
            }, {
                text : "导入",
                iconCls : "icon-upload",
                handler : function() {
                    var _store = this.findParentByType(Ext.grid.GridPanel).getStore();
                    var win = new Q.excel.uploadWin({
                        url : dealUrl + "_excelContact.action?contractId=" + _win.contractId
                    });
                    win.on("upload", function(action) {
                        _store.load();
                    });
                    win.show();
                }
            }, {
                text : "删除",
                iconCls : "icon-delete",
                handler : function() {
                    var _grid = this.findParentByType(Ext.grid.GridPanel);
                    var sm = _grid.getSelectionModel().getSelected();
                    if (Ext.isEmpty(sm)) {
                        Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                        return;
                    }
                    var _store = _grid.getStore();
                    Ext.Ajax.request({
                        url : path + "/Contract_deleteContact.action",
                        params : {
                            "contactId" : sm.get("contactId")
                        },
                        success : function(response) {
                            Q.tips(Q.color("删除成功！"), "red");
                            _store.load();
                        },
                        failure : function() {
                            Q.tips(Q.color("删除失败！"), "red");
                        }
                    });
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    }
});