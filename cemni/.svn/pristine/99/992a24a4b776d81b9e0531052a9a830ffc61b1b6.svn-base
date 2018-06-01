/**
 * 用户关联组织机构
 * 
 * @author：yuhb
 * @date：2016年12月15日 下午12:34:34
 */
Ext.ns('Q.user2stat2org');
var org_dealUrl = path + "/Org";
var dealUrl = path + "/User2stat2org";
var jsArr = [ path + "/js/console/org/Q.org.chooseOrgWin.js" ];
Q.loadJs(jsArr);

// 列表
Q.user2stat2org.gridColumn = [ {
    dataIndex : 'userId',
    hidden : true
}, {
    header : "用户编号",
    dataIndex : 'userCode',
    width : 10
}, {
    header : "用户名称",
    dataIndex : 'userName',
    width : 15
}, {
    header : "角色",
    dataIndex : 'roleNames',
    width : 30,
    renderer : function(v, c, r) {
        c.attr = "ext:qtip='" + v + "'";
        return v;
    }
}, {
    dataIndex : 'orgCodes',
    hidden : true
}, {
    header : "组织机构",
    dataIndex : 'orgNames',
    width : 45,
    renderer : function(v, c, r) {
        if (v) {
            c.attr = "ext:qtip='" + v + "'";
            return v;
        }
    }
} ];

Q.user2stat2org.tbarBnt = [ {
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();
        _store.baseParams = {
            filter_userCodeOrName : Ext.getCmp("userCodeOrName").getValue()
        };
        _store.load();
    }
}, {
    xtype : "textfield",
    emptyText : "用户编码/名称",
    id : "userCodeOrName",
    width : 150
}, "->", {
    text : "关联组织机构",
    iconCls : "icon-add",
    build : power.adduser2org,
    handler : function() {
        var _grid = vp.grid;
        var sm = _grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var _win = new Q.org.chooseOrgWin({
            url : path + "/Org_getOrgTreeList.action",
            orgCodes : sm.get("orgCodes")
        });
        _win.on("submit", function(r) {
            Ext.getBody().submitMask();
            var ids = [];
            if (!!r) {
                Q.each(r, function() {
                    ids.push(this.data.orgId);
                });
            }
            Ext.Ajax.request({
                url : dealUrl + "_updUser2org.action",
                params : {
                    ids : ids,
                    "model.userId" : sm.get("userId")
                },
                success : function(response) {
                    if (!Ext.decode(response.responseText).success) {
                        Q.error("操作失败！");
                        return;
                    } else {
                        Q.tips(Q.color("操作成功！", "red"));
                        _grid.getStore().reload();
                    }
                },
                failure : function() {
                    Q.error("操作失败！");
                },
                callback : function() {
                    Ext.getBody().unmask();
                }
            });
        });
        _win.show();
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '用户关联组织机构',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.user2stat2org.tbarBnt,
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        column : Q.user2stat2org.gridColumn,
        store : {
            url : dealUrl + '_queryUser.action'
        },
        listEditStateFn : [ {
            removeOrgBnt : function(r) {
                if (!r.data.orgNames) {
                    return false;
                }
                return true;
            }
        } ]
    }
};