/**
 * 个人会员等级调整
 * 
 * @author：yuhb
 * @date：2017年2月5日 下午3:23:56
 */
Ext.ns('Q.cust.gradeadj');
var dealUrl = path + '/CustGradeAdj';
Q.loadJs(path + "/js/integral/Q.cust.gradeadj.editWin.js");

Q.cust.gradeadj.selStores = {
    lvStore : Q.common.selDict(3100),
    lvStore2 : Q.common.selDict(3100, false),
    // 客户类型
    typeStore : Q.common.selDict(1101),
};

var gridColumn = [ {
    dataIndex : "individCustId",
    hidden : true
}, {
    dataIndex : "lv",
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
    header : "当前会员等级",
    dataIndex : "lvName"
}, {
    header : "珠宝折算额",
    dataIndex : "jewerlyAmount"
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '个人会员等级',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : [ {
            name : 'lvBnt',
            text : '会员等级调整',
            iconCls : 'icon-edit',
            build : power.edit,
            handler : function() {
                var _grid = vp.grid;
                var sm = _grid.getSelectionModel();
                if (sm.getSelections().length == 0) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return;
                }
                var _win = new Q.cust.gradeadj.editWin();
                _win.setFormValue(sm.getSelected().get("individCustId"));
                _win.on("submit", function() {
                    _grid.getStore().reload();
                });
                _win.show();
            }
        } ],
        hideSubTab : true,
        subTab : [],
        column : gridColumn,
        store : {
            idProperty : "individCustId",
            url : path + '/IndividCust_getJson.action?filter_EQ_enable=1',
            sort : "cardNo",
            dir : "desc"
        },
        listEditStateFn : [ {
            lvBnt : function(r) {
                if (r.data.lv == 1) {
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
            columnWidth : 1,
            labelWidth : 80,
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
                store : Q.cust.gradeadj.selStores.typeStore,
                clearable : true
            }, {
                fieldLabel : '会员等级',
                hiddenName : "filter_LIKE_lv",
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                store : Q.cust.gradeadj.selStores.lvStore,
                clearable : true
            }, {
                fieldLabel : '商品条码',
                name : "filter_LIKE_posOrderList_goodsBar",
                xtype : "textfield"
            }, {
                fieldLabel : '证书号码',
                name : "filter_LIKE_posOrderList_certificateNo",
                xtype : "textfield"
            } ]
        }
    }
};