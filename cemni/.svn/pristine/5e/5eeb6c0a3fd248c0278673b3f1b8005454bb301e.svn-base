Ext.ns('Q.adjHis');
var dealUrl = path + '/CustAdjHis';

Q.adjHis.selStores = {
    // 积分状态
    statusStore : Q.common.selDict(3101)
};

var gridColumn = [ {
    dataIndex : "individCustId",
    hidden : true
}, {
    dataIndex : "creditStatus",
    hidden : true
}, {
    header : "会员卡号",
    sortable : false,
    dataIndex : "individCust.cardNo",
    width : 10
}, {
    header : "客户名称",
    sortable : false,
    dataIndex : "individCust.name",
    width : 10
}, {
    header : "手机号码",
    sortable : false,
    dataIndex : "individCust.mobile",
    width : 10
}, {
    header : "变更前可用积分",
    sortable : false,
    dataIndex : "creditBefore",
    width : 12
}, {
    header : "变更后可用积分",
    sortable : false,
    dataIndex : "creditAfter",
    width : 12
}, {
    header : "当前会员积分",
    sortable : false,
    dataIndex : "individCust.credit",
    width : 10
}, {
    header : "已兑换积分",
    sortable : false,
    dataIndex : "individCust.convertedCredits",
    width : 8
}, {
    header : "积分状态",
    sortable : false,
    dataIndex : "creditStatusName",
    width : 8
}, {
    header : "变更人",
    sortable : false,
    dataIndex : "muser",
    width : 10
}, {
    header : "变更时间",
    sortable : false,
    dataIndex : "mdate",
    renderer : Q.common.dateRenderer,
    width : 10
}, {
    header : "变更原因",
    sortable : false,
    dataIndex : "modReason",
    width : 24,
    renderer : function(v, c, r) {
        c.attr = "ext:qtip='" + v + "'";
        return v;
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '积分调整历史',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        column : gridColumn,
        subTab : [],
        hideBtn : [],
        listEditStateFn : [],
        store : {
            url : dealUrl + '_getJson.action?filter_EQ_modType=2&filter_EQ_custType=1',
            sort : "individCust.cardNo",
            dir : "desc"
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 300,
        width : 350,
        form : {
            labelWidth : 90,
            columnWidth : 1,
            items : [ {
                fieldLabel : '会员卡号',
                name : "filter_EQ_individCust_cardNo",
                xtype : "textfield"
            }, {
                fieldLabel : '客户名称',
                name : "filter_LIKE_individCust_name",
                xtype : "textfield"
            }, {
                fieldLabel : '手机号码',
                name : "filter_EQ_individCust_mobile",
                xtype : "textfield"
            }, {
                fieldLabel : '积分状态',
                hiddenName : "filter_EQ_creditstatus",
                xtype : "uxcombo",
                clearable : true,
                valueField : "value",
                displayField : "name",
                anchor : "95.8%",
                store : Q.adjHis.selStores.statusStore
            }, {
                fieldLabel : '变更人',
                name : "filter_LIKE_muser",
                xtype : "textfield"
            }, {
                fieldLabel : "变更开始时间",
                name : 'filter_GE_mdate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_mdate = _form.findField('filter_GE_mdate').getValue();
                        var filter_LE_mdate = _form.findField("filter_LE_mdate").getValue();
                        if (filter_GE_mdate && filter_LE_mdate) {
                            if (filter_GE_mdate > filter_LE_mdate) {
                                Q.warning("开始日期不能大于结束日期！");
                                _form.findField('filter_GE_mdate').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "变更结束时间",
                name : 'filter_LE_mdate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_mdate = _form.findField('filter_GE_mdate').getValue();
                        var filter_LE_mdate = _form.findField("filter_LE_mdate").getValue();
                        if (filter_GE_mdate && filter_LE_mdate) {
                            if (filter_GE_mdate > filter_LE_mdate) {
                                Q.warning("结束日期不能小于开始日期！");
                                _form.findField('filter_LE_mdate').setValue("");
                            }
                        }
                    }
                }
            } ]
        }
    }
};