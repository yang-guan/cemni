/**
 * 团体会员积分调整
 * 
 * @author：yuhb
 * @date：2017年2月5日 下午3:22:36
 */
Ext.ns('Q.group.integraladj');
var dealUrl = path + '/GroupInteAdj';
Q.loadJs(path + "/js/integral/Q.group.integraladj.editWin.js");

Q.group.integraladj.selStores = {
    // 积分状态
    statusStore : Q.common.selDict(3101)
};

var gridColumn = [ {
    dataIndex : "groupCustId",
    hidden : true
}, {
    header : "会员卡号",
    dataIndex : "cardNo",
    width : 15
}, {
    header : "单位名称",
    dataIndex : "groupName",
    width : 40
}, {
    header : "当前会员积分",
    dataIndex : "credit",
    width : 15
}, {
    header : "已兑换积分",
    dataIndex : "convertedCredits",
    width : 15
}, {
    header : "积分状态",
    dataIndex : "creditStatusName",
    width : 15
} ];

// 按钮
Q.group.integraladj.tbarBnt = [ {
    text : '团体积分调整',
    iconCls : 'icon-edit',
    build : power.adj,
    handler : function() {
        var _grid = vp.grid;
        var sm = _grid.getSelectionModel();
        if (sm.getSelections().length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var _win = new Q.group.integraladj.editWin();
        _win.setFormValue(sm.getSelected().get("groupCustId"));
        _win.on("submit", function() {
            _grid.getStore().reload();
        });
        _win.show();
    }
}, {
    text : '冻结/解冻积分',
    iconCls : 'icon-lock',
    build : power.freezing,
    handler : function() {
        var _grid = vp.grid;
        var sm = _grid.getSelectionModel();
        if (sm.getSelections().length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        Q.confirm("确定“冻结/解冻”积分吗？", {
            ok : function() {
                Ext.getBody().submitMask();
                Ext.Ajax.request({
                    url : dealUrl + "_integralAdjFreezing.action",
                    params : {
                        id : sm.getSelected().get("groupCustId")
                    },
                    success : function(response) {
                        if (!Ext.decode(response.responseText).success) {
                            Q.error("冻结解冻积分失败！");
                            return;
                        } else {
                            Q.tips(Q.color("冻结解冻积分成功！", "red"));
                            _grid.getStore().reload();
                        }
                    },
                    failure : function() {
                        Q.error("冻结解冻积分失败！");
                    },
                    callback : function() {
                        Ext.getBody().unmask();
                    }
                });
            }
        });
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '团体会员积分',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.group.integraladj.tbarBnt,
        hideSubTab : true,
        subTab : [],
        activeTab : 0,
        hideBtn : [ "view", "delete" ],
        listEditStateFn : [],
        column : gridColumn,
        store : {
            url : path + '/GroupCust_getJson.action',
            sort : "cardNo",
            dir : "desc"
        }
    },

    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 330,
        height : 250,
        form : {
            labelWidth : 90,
            columnWidth : 1,
            items : [ {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '单位名称',
                name : "filter_LIKE_groupName",
                xtype : "textfield"
            }, {
                fieldLabel : '积分状态',
                hiddenName : "filter_LIKE_creditStatus",
                xtype : "uxcombo",
                clearable : true,
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.group.integraladj.selStores.statusStore
            }, {
                fieldLabel : '当前会员积分',
                name : "filter_LIKE_credit",
                xtype : "textfield"
            }, {
                fieldLabel : '已兑换积分',
                name : "filter_LIKE_convertedCredits",
                xtype : "textfield"
            } ]
        }
    }
};