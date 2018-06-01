Ext.ns('Q.contact');
var dealUrl = path + '/Contract';
var jsArr = [ path + "/js/contract/Q.contact.chooseLinkmanWin.js", path + "/js/common/Q.excel.uploadWin.js" ];
Q.loadJs(jsArr);

Q.contact.store = {
    // 合同类型
    contractType : Q.common.selDictContract(6400)
};

var gridColumn = [ {
    header : "合同编号",
    width : 3,
    dataIndex : "contractId",
    disabled : true
}, {
    header : "合同编号",
    width : 3,
    dataIndex : "contractNum"
}, {
    header : "合同名称",
    width : 5,
    dataIndex : "contractName"
}, {
    header : "合同甲方",
    width : 5,
    dataIndex : "partyA"
}, {
    header : "合同乙方",
    width : 5,
    dataIndex : "partyB"
}, {
    header : "合同类型",
    width : 3,
    dataIndex : "contractTypeName"
}, {
    header : "签约日期",
    width : 2.5,
    dataIndex : "signDate",
    renderer : Q.common.dateRenderer
}, {
    header : "生效日期",
    width : 2.5,
    dataIndex : "effDate",
    renderer : Q.common.dateRenderer
}, {
    header : "失效日期",
    width : 2.5,
    dataIndex : "invDate",
    renderer : Q.common.dateRenderer
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '合同联系人',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : [ {
            text : "选择联系人",
            iconCls : "icon-add",
            build : power.linkman,
            handler : function(p) {
                var _grid = vp.grid;
                var sm = _grid.getSelectionModel().getSelected();
                if (Ext.isEmpty(sm)) {
                    Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
                    return;
                }
                new Q.contact.chooseLinkmanWin(sm.get("contractId")).show();
            }
        } ],
        hideSubTab : true,
        hideBtn : [],
        subTab : [],
        listEditStateFn : [],
        column : gridColumn,
        store : {
            idProperty : 'contractId',
            url : dealUrl + '_getJson.action',
            sort : "contractId",
            dir : "desc"
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        height : 260,
        width : 330,
        form : {
            labelWidth : 60,
            columnWidth : 1,
            items : [ {
                fieldLabel : '合同编码',
                name : "filter_LIKE_contractNum",
                xtype : "textfield"
            }, {
                fieldLabel : '合同名称',
                name : "filter_LIKE_contractName",
                xtype : "textfield"
            }, {
                fieldLabel : '合同甲方',
                name : "filter_LIKE_partyA",
                xtype : "textfield"
            }, {
                fieldLabel : '合同乙方',
                name : "filter_LIKE_partyB",
                xtype : "textfield"
            }, {
                fieldLabel : '合同类型',
                hiddenName : 'filter_EQ_contractType',
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : '95.6%',
                store : Q.contact.store.contractType,
                clearable : true
            }, {
                fieldLabel : '签约日期',
                name : "filter_EQ_signDate",
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false
            } ]
        }
    }
};