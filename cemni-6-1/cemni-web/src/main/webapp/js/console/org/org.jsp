<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglibsComm.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织架构</title>
<script type="text/javascript" src="${ctx}/js/common/Q.common.js?datec=${datec}"></script>
<script>
    var power = eval(${permissions});
    var path = "${ctx}";
    var org_dealUrl = "${ctx}/Org";
    var company_dealUrl = "${ctx}/Company";
    var store_dealUrl = "${ctx}/Store";

    var tree;
    var nodetype = {
        company : 1,
        dept : 2,
        bigArea : 3,
        area : 4
    };

    Ext.onReady(function() {
        Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif";
        Ext.QuickTips.init();
        Ext.form.Field.prototype.msgTarget = 'qtip';

        var deptGrid = new Ext.ux.grid.GridPanel({
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
                    header : "部门编码",
                    dataIndex : "orgCode",
                    width : 15
                }, {
                    header : "部门名称",
                    dataIndex : "name",
                    width : 35
                }, {
                    header : "是否有效",
                    dataIndex : "isValid",
                    width : 10,
                    renderer : Q.common.yesOrNotRenderer
                }, {
                    header : "序号",
                    dataIndex : "orderNo",
                    width : 10
                }, {
                    header : "负责人",
                    dataIndex : "responsor",
                    width : 25
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
                text : "新增",
                iconCls : "icon-add",
                build : power.add,
                handler : function() {
                    var nodeId = tree.getSelectionModel().getSelectedNode().id;
                    var _grid = this.findParentByType(Ext.grid.GridPanel);

                    var _win = new Q.org.editWin({
                        operflag : "add",
                        parentId : nodeId,
                        type : nodetype.dept
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = nodeId;
                        _store.load();
                    });
                    _win.show();
                }
            }, {
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
                    var _win = new Q.org.editWin({
                        operflag : "edit",
                        orgId : sm.get("orgId"),
                        type : nodetype.dept
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = tree.getSelectionModel().getSelectedNode().id;
                        _store.load();
                    });
                    _win.show();
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

        ///////////////////////////////////////////////////////大区////////////////////////////////////////////////////
        var bigAreaGrid = new Ext.ux.grid.GridPanel({
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
                    header : "大区编码",
                    dataIndex : "orgCode",
                    width : 15
                }, {
                    header : "大区名称",
                    dataIndex : "name",
                    width : 35
                }, {
                    header : "是否有效",
                    dataIndex : "isValid",
                    width : 10,
                    renderer : Q.common.yesOrNotRenderer
                }, {
                    header : "序号",
                    dataIndex : "orderNo",
                    width : 10
                }, {
                    header : "负责人",
                    dataIndex : "responsor",
                    width : 25
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
                text : "新增",
                iconCls : "icon-add",
                build : power.add,
                handler : function() {
                    var nodeId = tree.getSelectionModel().getSelectedNode().id;
                    var _grid = this.findParentByType(Ext.grid.GridPanel);

                    var _win = new Q.org.editWin({
                        operflag : "add",
                        parentId : nodeId,
                        type : nodetype.bigArea
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = nodeId;
                        _store.load();
                    });
                    _win.show();
                }
            }, {
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
                    var _win = new Q.org.editWin({
                        operflag : "edit",
                        orgId : sm.get("orgId"),
                        type : nodetype.bigArea
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = tree.getSelectionModel().getSelectedNode().id;
                        _store.load();
                    });
                    _win.show();
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

        ///////////////////////////////////////////////////////区域////////////////////////////////////////////////////
        var areaGrid = new Ext.ux.grid.GridPanel({
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
                    header : "区域编码",
                    dataIndex : "orgCode",
                    width : 15
                }, {
                    header : "区域名称",
                    dataIndex : "name",
                    width : 35
                }, {
                    header : "是否有效",
                    dataIndex : "isValid",
                    width : 10,
                    renderer : Q.common.yesOrNotRenderer
                }, {
                    header : "序号",
                    dataIndex : "orderNo",
                    width : 10
                }, {
                    header : "负责人",
                    dataIndex : "responsor",
                    width : 25
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
                text : "新增",
                iconCls : "icon-add",
                build : power.add,
                handler : function() {
                    var nodeId = tree.getSelectionModel().getSelectedNode().id;
                    var _grid = this.findParentByType(Ext.grid.GridPanel);

                    var _win = new Q.org.editWin({
                        operflag : "add",
                        parentId : nodeId,
                        type : nodetype.area
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = nodeId;
                        _store.load();
                    });
                    _win.show();
                }
            }, {
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
                    var _win = new Q.org.editWin({
                        operflag : "edit",
                        orgId : sm.get("orgId"),
                        type : nodetype.area
                    });
                    _win.on("submit", function() {
                        var _store = _grid.getStore();
                        _store.baseParams.filter_EQ_parentId = tree.getSelectionModel().getSelectedNode().id;
                        _store.load();
                    });
                    _win.show();
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

        ///////////////////////////////////////////////////////门店////////////////////////////////////////////////////
        var storeGrid = new Ext.ux.grid.GridPanel({
            style : "padding:2px 2px 2px 0",
            border : true,
            pageSize : 20,
            sm : {
                singleSelect : true
            },
            cm : {
                columns : [ {
                    dataIndex : "org.orgId",
                    hidden : true
                }, {
                    dataIndex : "storeId",
                    hidden : true
                }, {
                    header : "门店编码",
                    width : 15,
                    dataIndex : "storeNo"
                }, {
                    header : "门店名称",
                    width : 40,
                    dataIndex : "name"
                }, {
                    header : "门店属性",
                    width : 10,
                    dataIndex : "attrName"
                }, {
                    header : "门店形态",
                    width : 10,
                    dataIndex : "formName"
                }, {
                    header : "开店日期",
                    width : 15,
                    dataIndex : "shopupDate",
                    renderer : Q.common.dateRenderer
                }, {
                    header : "是否有效",
                    width : 10,
                    dataIndex : "isValid",
                    renderer : Q.common.yesOrNotRenderer
                } ]
            },
            store : {
                idProperty : "storeId",
                url : store_dealUrl + '_getJson.action',
                sort : "storeId",
                dir : "desc",
                autoLoad : false
            }
        });

        tree = new Ext.ux.tree.TreePanel({
            id : "1",
            url : org_dealUrl + "_getOrgTree.action?key={0}",
            nodeName : "key",
            rootVisible : false,
            expanded : true,
            treeType : TreeType.INFO,
            width : 250,
            listeners : {
                click : function(node, event) {
                    var _attr = node.attributes.attr;
                    if (_attr.type == nodetype.company) {// 部门
                        var _store = deptGrid.getStore();
                        _store.baseParams.filter_EQ_parentId = node.id;
                        _store.load();
                        Ext.getCmp('centerVp').add(deptGrid).show().doLayout();
                    } else if (_attr.type == nodetype.dept) {// 大区
                        var _store = bigAreaGrid.getStore();
                        _store.baseParams.filter_EQ_parentId = node.id;
                        _store.load();
                        Ext.getCmp('centerVp').add(bigAreaGrid).show().doLayout();
                    } else if (_attr.type == nodetype.bigArea) {// 区域
                        var _store = areaGrid.getStore();
                        _store.baseParams.filter_EQ_parentId = node.id;
                        _store.load();
                        Ext.getCmp('centerVp').add(areaGrid).show().doLayout();
                    } else if (_attr.type == nodetype.area) {// 门店
                        var _store = storeGrid.getStore();
                        _store.baseParams = {
                            filter_EQ_org_parentId : node.id
                        };
                        _store.load();
                        Ext.getCmp('centerVp').add(storeGrid).show().doLayout();
                    } else {
                        event.stopEvent();
                    }
                }
            }
        });

        new Ext.Viewport({
            layout : "border",
            items : [ tree, {
                id : "centerVp",
                region : "center",
                xtype : "tabpanel",
                activeTab : 0,
                items : deptGrid,
                headerCfg : {
                    style : "display:none"
                }
            } ]
        });
    });
</script>
<script type="text/javascript" rc="${ctx}/plugins/ext3/ux/q-model.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/org/Q.org.editWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/org/Q.company.editWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/store/Q.store.chooseStoreWin.js?datec=${datec}"></script>
<c:forEach items="${jsPath}" var="item">
	<script type="text/javascript" src="${ctx}${item}"></script>
</c:forEach>
</head>
<body>
</body>
</html>