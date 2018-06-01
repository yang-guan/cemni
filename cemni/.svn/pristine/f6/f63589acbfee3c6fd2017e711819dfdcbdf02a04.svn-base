<%@page import="com.huiju.module.web.util.WebUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibsComm.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="application.title" /></title>
<script type="text/javascript">
    Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';
    var path = '${ctx}';
    
    <%String s_authorities = WebUtils.getAuthorities();%>
    Q.power = {
        authGroup : {
            list : <%=s_authorities.indexOf("|B_PERMISSION_AUTHGROUP_LIST|") > -1%>,
            add : <%=s_authorities.indexOf("|B_PERMISSION_AUTHGROUP_ADD|") > -1%>,
            edit : <%=s_authorities.indexOf("|B_PERMISSION_AUTHGROUP_EDIT|") > -1%>,
            'delete' : <%=s_authorities.indexOf("|B_PERMISSION_AUTHGROUP_DELETE|") > -1%>
        }
    };

    //验证出错时设置背景颜色
    function setBgColor(m, r, dataIndex) {
        m.attr = "style='background-color:#FBF1BB'";
        if (r.cell == dataIndex) {
            delete r.cell;
            m.attr = "style='background-color:#aabbff'";
        }
    }

    var power = Q.power.authGroup;
    Q.power.authGroup = null;

    Ext.onReady(function() {
        var grid = new Ext.ux.grid.GridPanel({
            moduleName : "authGroup",
            id : "authGroupGrid",
            store : {
                url : path + '/permission/AuthGroup_getJson.action',
                autoLoad : true
            },
            cm : {
                defaults : {
                    width : 100
                },
                columns : [ {
                    header : $("authGroup.clientCode"),
                    hidden : true,
                    dataIndex : 'clientCode'
                }, {
                    header : $("authGroup.agrCode"),
                    sortable : true,
                    width : 20,
                    dataIndex : 'agrCode',
                    renderer : function(v, m) {
                        m.attr = "style='cursor:pointer'";
                        return "<font style='text-decoration:underline'>" + v + "</font>";
                    }
                }, {
                    header : $("authGroup.agrDesc"),
                    sortable : true,
                    dataIndex : 'agrDesc'
                } ]
            },
            sm : {
                singleSelect : true
            },
            viewConfig : {
                forceFit : true
            },
            getEditWin : function(config) {
                var win = new Q.permission.AuthGroupEditWin(config);
                this.getEditWin = function() {
                    return win;
                };
                return win;
            },
            tbar : [ {
                name : 'add',
                text : $("button.new"),
                iconCls : 'icon-add',
                build : power.add,
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var win = grid.getEditWin({
                        closable : true,
                        width : 400,
                        height : 150
                    });
                    win.resetWin();
                    Ext.getCmp("model.agrCode").setReadOnly(false);
                    win.setTitle($("authGroup.addAuthGroup"));
                    win.show();
                }
            }, {
                name : 'edit',
                text : $("button.edit"),
                iconCls : 'icon-edit',
                build : power.edit,
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var r = grid.getSelectionModel().getSelected();
                    var win = grid.getEditWin({
                        closable : true,
                        width : 400,
                        height : 150
                    });
                    Ext.getCmp("model.agrCode").setReadOnly(true);
                    win.setFormValue(r.json.clientCode, r.json.agrCode, r.json.agrDesc);
                    win.setTitle($("authGroup.modifyingAuthGroup"));
                    win.show();
                }
            }, {
                name : "view",
                text : $('button.view'),//查看
                build : power.list,
                iconCls : "icon-edit",
                hidden : true,
                handler : function(_self) {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var r = grid.getSelectionModel().getSelected();
                    var win = grid.getEditWin({
                        closable : true,
                        width : 500,
                        height : 334
                    });
                    win.formPanel.buttons[1].setText($("button.return"));
                    win.formPanel.buttons[0].hide();
                    win.setFormValue(r.id);
                    win.setTitle($("authGroup.modifyingauthGroup"));
                    win.show();
                }
            }, {
                name : 'delete',
                text : $("button.delete"),
                iconCls : 'icon-delete',
                build : power['delete'],
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var selectids = grid.getSelectionModel().getSelections();
                    Q.confirm($("message.delete.confirm"), {
                        ok : function() {
                            Ext.getBody().submitMask();
                            Ext.Ajax.request({
                                url : path + "/permission/AuthGroup_delete.action",
                                params : {
                                    "id.clientCode" : selectids[0].json.clientCode,
                                    "id.agrCode" : selectids[0].json.agrCode
                                },
                                success : function(response) {
                                    var json = Ext.decode(response.responseText);
                                    if (false === json.success) {
                                        //用户删除失败！未知系统异常！
                                        Q.error("<font color='red'>" + json.info || $("message.delete.failure") + "<br/><br/>" + $("message.system.error") + "</font>");
                                        return;
                                    }
                                    //删除成功！
                                    Q.tips("<font color='blue'>" + $("message.delete.success") + "</font>");
                                    grid.getStore().reload();
                                },
                                failure : function(response) {
                                    //删除失败！请检查与服务器的连接是否正常，或稍候再试。
                                    Q.error("<font color='red'>" + $("message.delete.failure") + "<br/><br/>" + $("message.system.disconnect") + "</font>");
                                },
                                callback : function() {
                                    Ext.getBody().unmask();
                                }
                            });
                        }
                    });
                }
            }, "->", {
                xtype : "textfield",
                build : power.list,
                //查询 资源组编码
                emptyText : $("authGroup.agrCode") + "/" + $("authGroup.agrDesc"),
                id : "searchParam",
                width : 200
            }, {
                text : $("button.search"),//查询
                build : power.list,
                iconCls : "icon-search",
                handler : function() {
                    var store = grid.getStore();
                    searchValue = Ext.getCmp("searchParam").getValue();
                    store.baseParams.filter_LIKE_agrCode_OR_agrDesc = searchValue;
                    store.load({
                        params : {
                            start : 0,
                            limit : 20
                        }
                    });
                }
            } ],
            listeners : {
                "afterrender" : function(g) {
                    var sm = g.getSelectionModel();
                    //监听选择变化事件
                    sm.on("selectionchange", function(sm) {
                        var r = sm.getSelected();
                        // 保存选中的记录
                        topSelected = r;
                        //有选中记录时，显示“详细” 显示已分配用户
                        if (r) {
                            var store = authFieldGrid.getStore(), data = [];
                            data["filter_EQ_agrCode"] = r.get('agrCode');
                            Ext.apply(store.baseParams, data);
                            store.load();
                            var userStore = userGrid.getStore(), userData = [];
                            userData["filter_EQ_agrCode"] = r.get('agrCode');
                            Ext.apply(userStore.baseParams, userData);
                            userStore.load();
                        }
                        try {
                            authFieldGrid.getView().refresh();
                        } catch (e) {
                        }
                    });

                    var sm = g.getSelectionModel();
                    g.getStore().on("load", function() {
                        sm.clearSelections();
                        var tbar = grid.getTopToolbar();
                        tbar.getObj = function(name) {
                            return this.find("name", name)[0];
                        };
                        if (power["delete"]) {
                            tbar.getObj("delete").setDisabled(true);
                        }
                        if (power.edit) {
                            tbar.getObj("edit").setDisabled(true);
                        }

                    });
                },
                "rowdblclick" : function(g, rowIndex, e) {
                    var tbar = g.getTopToolbar();
                    if (power.edit && tbar) {
                        tbar.find('name', 'edit')[0].handler();
                        var win = g.getEditWin();
                        var store = g.getStore();
                        if (store.getAt(rowIndex).get("isEditable") == "0") {
                            win.formPanel.buttons[0].hide();
                        }
                    } else { //有权限时进行操作
                        tbar.find('name', 'view')[0].handler();
                    }
                },
                "click" : function() {
                    var record = grid.getSelectionModel().getSelections();
                    var tbar = grid.getTopToolbar();
                    tbar.getObj = function(name) {
                        return this.find("name", name)[0];
                    };
                    if (Ext.isEmpty(record)) {//选择行为空时，给他设置隐藏
                        if (power["delete"]) {
                            tbar.getObj("delete").setDisabled(true);
                        }
                        if (power.edit) {
                            tbar.getObj("edit").setDisabled(true);
                        }
                    } else {
                        if (record.length > 1) {
                            if (power.edit) {
                                tbar.getObj("edit").setDisabled(true);
                            }
                        } else {
                            if (power.edit) {
                                tbar.getObj("edit").setDisabled(false);
                            }
                        }
                        if (power["delete"]) {
                            tbar.getObj("delete").setDisabled(false);
                        }
                    }
                }
            }
        });

        var authFieldGrid = new Ext.ux.grid.EditorGridPanel({
            id : "authFieldGridId",
            sm : {
                singleSelect : true
            },
            store : {
                url : path + "/permission/AuthGroup_getDetailsJson.action",
                autoLoad : false
            },
            sortable : false,
            pageSize : 0,
            cm : [ {
                header : $("authField.authCode"),
                dataIndex : 'authField.authCode',
                width : 15
            }, {
                header : $("authField.fieldName"),
                dataIndex : 'authField.fieldName',
                width : 20
            }, {
                header : $("authGroupDetail.fielOrder"),
                dataIndex : 'fieldOrder',
                width : 10,
                editor : {
                    xtype : "numberfield",
                    allowBlank : false,
                    name : "fieldOrder",
                    minValue : 0,
                    maxValue : 99
                },
                renderer : function(v, m, r) {
                    setBgColor(m, r, this.dataIndex);
                    return v;
                }
            }, {
                header : $("authGroupDetail.authValue") + "<font color='red'>*</font>",
                dataIndex : 'authValue',
                width : 55,
                editor : {
                    xtype : "textfield",
                    allowBlank : false,
                    name : "authValue",
                    listeners : {
                        render : function(p) {
                            p.getEl().on('click', function(p) {
                                var r = Ext.getCmp("authFieldGridId").getSelectionModel().getSelected();
                                var _win = new Q.org.chooseOrgWin({
                                    url : path + "/Org_getOrgTreeList.action",
                                    orgCodes : r.get("authValue")
                                });
                                _win.on("submit", function(sms) {
                                    var codeArr = [];
                                    Q.each(sms, function() {
                                        codeArr.push(this.data.orgCode);
                                    });
                                    r.set("authValue", codeArr.join(","));
                                });
                                _win.show();
                            });
                        }
                    }
                },
                renderer : function(v, m, r) {
                    setBgColor(m, r, this.dataIndex);
                    return v;
                }
            } ],
            tbar : [ {
                text : "添加",
                iconCls : "icon-add",
                handler : function() {
                    keys = [];
                    authFieldGrid.getStore().each(function(r) {
                        var key = [];
                        key.push(r.get("authField.authCode"));
                        key.push(r.get("authField.fieldName"));
                        key.push("1");
                        key.push(r.get("authValue"));
                        keys.push(key);
                    });
                    var win = new Q.permission.AuthGroupAddAuthFieldWin({
                        select : function(g, r) {
                            var records = [];
                            Q.each(r, function() {
                                records.push(new Ext.data.Record({
                                    "authField.authCode" : this.get("authCode"),
                                    "authField.fieldName" : this.get("fieldName"),
                                    "fieldOrder" : "1",
                                    "authValue" : ""
                                }));
                            });
                            authFieldGrid.getStore().add(records);
                        }
                    });
                    win.show();
                }
            }, {
                name : 'delete',
                text : "删除",
                iconCls : 'icon-delete',
                build : power['delete'],
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var selectids = grid.getSelectionModel().getSelections();
                    if (selectids.length <= 0) {
                        Q.tips("<font color='red'>" + $("message.pleaseSelect") + "</font>");
                        return;
                    }
                    Q.confirm($("message.delete.confirm"), {
                        ok : function() {
                            var records = grid.getSelectionModel().getSelections();
                            for (var i = 0, len = records.length; i < len; i++) {
                                grid.store.remove(records[i]);
                            }
                            keys = [];
                            authFieldGrid.getStore().each(function(r) {
                                var key = [];
                                key.push(r.get("authField.authCode"));
                                key.push(r.get("authField.fieldName"));
                                key.push(r.get("authValue"));
                                keys.push(key);
                            });

                        }
                    });
                }
            }, {
                name : 'save',
                text : $("button.save"),
                iconCls : 'icon-save',
                handler : function() {
                    //验证表格权限字段的值是否填写
                    var field = [ "authValue" ];
                    var isEmpty = false;
                    authFieldGrid.getStore().each(function(r) {
                        Q.each(field, function(p, i) {
                            if (p = "authValue") {
                                if (Ext.isEmpty(r.get("authValue"))) {
                                    Q.tips("<font color='blue'>" + $("authGroupDetail.authValueCanNotBeEmpty") + "</font>");
                                    isEmpty = true;
                                    return;
                                }
                            }
                        });
                    });
                    if (!isEmpty) {
                        Q.confirm($("message.save.confirm"), {
                            ok : function() {
                                Ext.getBody().submitMask();
                                var authCodes = [];
                                var fieldOrders = [];
                                var authValues = [];
                                authFieldGrid.getStore().each(function(r) {
                                    authCodes.push(r.get("authField.authCode"));
                                    fieldOrders.push(r.get("fieldOrder"));
                                    authValues.push(r.get("authValue"));
                                });
                                Ext.Ajax.request({
                                    url : path + "/permission/AuthGroup_saveDetails.action",
                                    params : {
                                        "model.agrCode" : grid.getSelectionModel().getSelections()[0].json.agrCode,
                                        "authCodes" : authCodes,
                                        "fieldOrders" : fieldOrders,
                                        "authValues" : authValues
                                    },
                                    success : function(response) {
                                        var json = Ext.decode(response.responseText);
                                        if (false === json.success) {
                                            Q.error("<font color='red'>" + json.info || $("message.save.failure") + "<br/><br/>" + $("message.system.error") + "</font>");
                                            return;
                                        }
                                        Q.tips("<font color='blue'>" + $("message.save.success") + "</font>");
                                        authFieldGrid.getStore().reload();
                                    },
                                    failure : function(response) {
                                        Q.error("<font color='red'>" + $("message.save.failure") + "<br/><br/>" + $("message.system.disconnect") + "</font>");
                                    },
                                    callback : function() {
                                        Ext.getBody().unmask();
                                    }
                                });
                            }
                        });
                    }
                }
            } ]
        });

        var userGrid = new Ext.ux.grid.GridPanel({
            store : {
                url : path + "/permission/AuthGroup_getRelatedUserJson.action",
                autoLoad : false
            },
            pageSize : 0,
            cm : [ {
                header : $("user.userCode"),
                sortable : true,
                dataIndex : 'userCode'
            } ],
            tbar : [ {
                text : "添加",
                iconCls : "icon-add",
                handler : function() {
                    var win = new Q.permission.AuthGroupAddUserWin();
                    win.on("submit", function() {
                        userGrid.getStore().reload();
                    });
                    var store = win.findByType(Ext.grid.GridPanel)[0].store;
                    store.baseParams.agrCode = topSelected.get('agrCode');
                    win.show();
                }
            }, {
                name : "delete",
                text : $("button.delete"),//删除
                iconCls : "icon-delete",
                handler : function() {
                    var grid = this.findParentByType(Ext.grid.GridPanel);
                    var selectids = grid.getSelectionModel().getSelections();
                    var params = {};
                    var userAuthGroups = [];
                    if (selectids.length <= 0) {
                        Q.tips("<font color='red'>" + $("message.pleaseSelect") + "</font>");
                        return;
                    }
                    for (var i = 0; i < selectids.length; i++) {
                        userAuthGroups.push({
                            "clientCode" : topSelected.get('clientCode'),
                            "userCode" : selectids[i].get('userCode'),
                            "agrCode" : topSelected.get('agrCode')
                        });
                    }
                    params.userAuthGroups = userAuthGroups;

                    Q.confirm($("message.delete.confirm"), {
                        ok : function() {
                            Ext.getBody().submitMask();
                            Ext.Ajax.request({
                                url : path + "/permission/AuthGroup_deleteAuthGroupUser.action",
                                params : Q.parseParams(params),
                                success : function(response) {
                                    var json = Ext.decode(response.responseText);
                                    if (false === json.success) {
                                        //删除失败,未知系统异常
                                        Q.error("<font color=''>" + $("message.delete.failure") + "<br/><br/>" + $("message.system.error") + "</font>");
                                        return;
                                    }
                                    //删除成功
                                    Q.tips("<font color='blue'>" + $("message.delete.success") + "</font>");
                                    grid.getStore().reload();
                                },
                                failure : function(response) {
                                    //删除失败! 请检查与服务器的连接是否正常，或稍候再试。
                                    Q.error("<font color = ''>" + $("message.delete.failure") + "<br/><br/>" + $("message.system.disconnect") + "</font>");
                                },
                                callback : function() {
                                    Ext.getBody().unmask();
                                }
                            });
                        }
                    });
                }
            } ]
        });

        var authPanel = {
            xtype : "panel",
            title : "权限配置",
            layout : "border",
            items : [ {
                layout : "border",
                title : $("authField.authCode"),
                region : "center",
                style : "padding:1px 1px 1.5px",
                items : authFieldGrid
            }, {
                layout : "border",
                region : "east",
                width : 200,
                title : $("authGroup.relatedUser"),
                collapsible : true,
                style : "padding:1px",
                items : userGrid
            } ]
        };

        var tab = getSplitTab({
            tabHeight : 260,
            gridPanel : grid,
            container : "vp_portid",
            column : 'agrCode',
            activeTab : 0
        }, authPanel);

        var vp = new Ext.Viewport({
            layout : 'border',
            id : "vp_portid",
            items : [ {
                xtype : "panel",
                region : "center",
                height : 350,
                border : false,
                layout : "border",
                items : [ grid, tab ]
            } ]
        });
    });
</script>
<script type="text/javascript" src="${ctx}/js/permission/Q.permission.AuthGroupEditWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/permission/Q.permission.AuthGroupAddUserWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/permission/Q.permission.AuthGroupAddAuthFieldWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/console/org/Q.org.chooseOrgWin.js?datec=${datec}"></script>
</head>
<body>
</body>
</html>