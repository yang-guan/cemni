/**
 * 个人会员积分调整
 * 
 * @author：yuhb
 * @date：2017年2月5日 下午2:22:36
 */
Ext.ns('Q.cust.integraladj');
var dealUrl = path + '/CustInteAdj';
Q.loadJs(path + "/js/integral/Q.cust.integraladj.editWin.js");

Q.cust.integraladj.selStores = {
    // 积分状态
    statusStore : Q.common.selDict(3101),
    // 客户类型
    typeStore : Q.common.selDict(1101),
};

var gridColumn = [ {
    dataIndex : "individCustId",
    hidden : true
}, {
    dataIndex : "creditStatus",
    hidden : true
}, {
    header : "会员卡号",
    dataIndex : "cardNo"
}, {
    header : "客户名称",
    dataIndex : "name"
}, {
    header : "手机号码",
    dataIndex : "mobile"
}, {
    header : "客户类型",
    dataIndex : "typeName"
}, {
    header : "当前会员积分",
    dataIndex : "credit"
}, {
    header : "已兑换积分",
    dataIndex : "convertedCredits"
}, {
    header : "积分状态",
    dataIndex : "creditStatusName"
} ];

// 按钮
Q.cust.integraladj.tbarBnt = [ {
    name : 'integralAdjBnt',
    text : '个人积分调整',
    iconCls : 'icon-edit',
    build : power.adj,
    handler : function() {
        var _grid = vp.grid;
        var sm = _grid.getSelectionModel();
        if (sm.getSelections().length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var _win = new Q.cust.integraladj.editWin();
        _win.setFormValue(sm.getSelected().get("individCustId"));
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
                        id : sm.getSelected().get("individCustId")
                    },
                    success : function(response) {
                        var json = Ext.decode(response.responseText);
                        if (!json.success) {
                            Q.error(json.msg);
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
    moduleName : '个人会员积分',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.cust.integraladj.tbarBnt,
        hideSubTab : true,
        subTab : [],
        activeTab : 0,
        hideBtn : [ "view", "delete" ],
        listEditStateFn : [],
        column : gridColumn,
        store : {
            idProperty : 'individCustId',
            url : path + '/IndividCust_getJson.action?filter_EQ_enable=1',
            sort : "cardNo",
            dir : "desc"
        },
        listEditStateFn : [ {
            integralAdjBnt : function(r) {
                if (r.data.creditStatus == 2) {
                    return false;
                }
                return true;
            }
        } ]
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 350,
        height : 300,
        form : {
            labelWidth : 90,
            columnWidth : 1,
            items : [ {
                fieldLabel : '会员卡号',
                name : "filter_LIKE_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_name",
                xtype : "textfield"
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_mobile",
                xtype : "textfield"
            }, {
                fieldLabel : "客户类型",
                hiddenName : "filter_IN_type",
                xtype : "lovcombo",
                valueField : "value",
                displayField : "name",
                store : Q.cust.integraladj.selStores.typeStore,
                clearable : true
            }, {
                fieldLabel : '积分状态',
                hiddenName : "filter_LIKE_creditStatus",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.cust.integraladj.selStores.statusStore,
                clearable : true
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