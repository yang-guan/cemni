<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglibsComm.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>对外组织架构</title>
<script type="text/javascript" src="${ctx}/js/common/Q.common.js?datec=${datec}"></script>
<script>
    var power = eval(${permissions});
    var path = "${ctx}";
    var org_dealUrl = "${ctx}/OrgExt";

    Ext.onReady(function() {
        Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif";
        Ext.QuickTips.init();
        Ext.form.Field.prototype.msgTarget = 'qtip';

        var _grid = new Ext.ux.grid.GridPanel({
            style : "padding:2px 2px 2px 0",
            border : true,
            pageSize : 0,
            sm : {
                singleSelect : true
            },
            cm : {
                columns : [ {
                    dataIndex : "orgId",
                    hidden : true
                }, {
                    dataIndex : "type",
                    hidden : true
                }, {
                    header : "编码",
                    dataIndex : "orgCode",
                    width : 15
                }, {
                    header : "名称",
                    dataIndex : "name",
                    width : 45
                }, {
                    header : "机构类型",
                    dataIndex : "type",
                    width : 15,
                    renderer : function(v) {
                        return v == 1 ? "公司" : "门店";
                    }
                }, {
                    header : "是否有效",
                    dataIndex : "isValid",
                    width : 15,
                    renderer : Q.common.yesOrNotRenderer
                }, {
                    header : "序号",
                    dataIndex : "orderNo",
                    width : 10
                } ]
            },
            store : {
                idProperty : "orgId",
                url : org_dealUrl + '_getOrgJson.action',
                sort : "orderNo",
                dir : "asc",
                autoLoad : false
            },
            tbar : [ {
                id : "orgExtAddId",
                text : "新增",
                iconCls : "icon-add",
                build : power.add,
                handler : function() {
                    var nodeId = tree.getSelectionModel().getSelectedNode().id;
                    var _grid = this.findParentByType(Ext.grid.GridPanel);

                    var _win = new Q.orgExt.editWin({
                        operflag : "add",
                        parentId : nodeId
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = nodeId;
                        _store.load();
                    });
                    _win.show();
                }
            }, {
                id : "orgExtEditId",
                text : "编辑",
                iconCls : "icon-edit",
                build : power.edit,
                handler : function() {
                    var _grid = this.findParentByType(Ext.grid.GridPanel);
                    var sm = _grid.getSelectionModel().getSelected();
                    if (Ext.isEmpty(sm)) {
                        Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                        return;
                    }
                    var _win = new Q.orgExt.editWin({
                        operflag : "edit",
                        orgId : sm.get("orgId")
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = tree.getSelectionModel().getSelectedNode().id;
                        _store.load();
                    });
                    _win.show();
                }
            }, {
                id : "orgExtRelStoreId",
                text : "关联门店",
                iconCls : "icon-add",
                build : power.addstore2org,
                handler : function() {
                    var _grid = this.findParentByType(Ext.grid.GridPanel);
                    var _win = new Q.store.chooseStoreWin({
                        action : "org2store",
                        singleSelect : false,
                        param : {
                            parentId : tree.getSelectionModel().getSelectedNode().id
                        }
                    });
                    _win.on("submit", function() {
                        tree.updateTree();
                        _grid.getStore().reload();
                    });
                    _win.show();
                }
            }, {
                id : "orgExtRemoveStoreId",
                text : "移除门店",
                iconCls : "icon-delete",
                build : power.delstore2org,
                handler : function() {
                    var _grid = this.findParentByType(Ext.grid.GridPanel);
                    var sms = _grid.getSelectionModel().getSelections();
                    if (sms.length == 0) {
                        Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                        return;
                    }
                    var orgIdArr = [];
                    for (var i = 0; i < sms.length; i++) {
                        orgIdArr.push(sms[i].get("orgId"));
                    }
                    Q.confirm($("message.delete.confirm"), {
                        ok : function() {
                            Ext.getBody().submitMask();
                            Ext.Ajax.request({
                                url : org_dealUrl + "_org2storeRemove.action",
                                params : {
                                    ids : orgIdArr
                                },
                                success : function(response) {
                                    if (!Ext.decode(response.responseText).success) {
                                        Q.error($("message.delete.failure"));
                                        return;
                                    } else {
                                        Q.tips(Q.color($("message.delete.success"), "red"));
                                        tree.updateTree();
                                        _grid.getStore().reload();
                                    }
                                },
                                failure : function() {
                                    Q.error($("message.delete.failure") + "<br/>" + $("message.system.disconnect"));
                                },
                                callback : function() {
                                    Ext.getBody().unmask();
                                }
                            });
                        }
                    });
                }
            } ],
            listeners : {
                afterrender : function(g) {
                    var sm = g.getSelectionModel();
                    g.getStore().on("load", function() {
                        sm.clearSelections();
                    });
                }
            }
        });

        var tree = new Ext.ux.tree.TreePanel({
            id : "0",
            url : org_dealUrl + "_getOrgTree.action?key={0}",
            nodeName : "key",
            rootVisible : false,
            expanded : true,
            treeType : TreeType.INFO,
            width : 250,
            listeners : {
                click : function(node, event) {
                    var _store = _grid.getStore();
                    _store.removeAll();

                    var _type = node.attributes.attr.type;
                    if (_type == 2) {
                        Ext.getCmp("orgExtAddId").disable();
                        Ext.getCmp("orgExtEditId").disable();
                        Ext.getCmp("orgExtRelStoreId").disable();
                        Ext.getCmp("orgExtRemoveStoreId").disable();
                    } else {
                        if (_type == 0) {
                            Ext.getCmp("orgExtRelStoreId").disable();
                            Ext.getCmp("orgExtRemoveStoreId").disable();
                        } else {
                            Ext.getCmp("orgExtAddId").enable();
                            Ext.getCmp("orgExtEditId").enable();
                            Ext.getCmp("orgExtRelStoreId").enable();
                            Ext.getCmp("orgExtRemoveStoreId").enable();
                        }
                        _store.baseParams = {
                            filter_EQ_parentId : node.id
                        };
                        _store.load();
                    }
                }
            }
        });

        var vp = new Ext.Viewport({
            layout : "border",
            items : [ tree, _grid ]
        });
    });
</script>
<script type="text/javascript" rc="${ctx}/plugins/ext3/ux/q-model.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/orgext/Q.orgext.editWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/store/Q.store.chooseStoreWin.js?datec=${datec}"></script>
<c:forEach items="${jsPath}" var="item">
	<script type="text/javascript" src="${ctx}${item}"></script>
</c:forEach>
</head>
<body>
</body>
</html>