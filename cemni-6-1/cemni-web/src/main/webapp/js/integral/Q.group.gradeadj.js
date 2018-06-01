/**
 * 团体会员等级调整
 * 
 * @author：yuhb
 * @date：2017年2月5日 下午3:22:36
 */
Ext.ns('Q.group.gradeadj');
var dealUrl = path + '/GroupGradeAdj';
Q.loadJs(path + "/js/integral/Q.group.gradeadj.editWin.js");

Q.group.gradeadj.selStores = {
    lvStore : Q.common.selDict(3100),
    lvStore2 : Q.common.selDict(3100, false)
};

var gridColumn = [ {
    dataIndex : "groupCustId",
    hidden : true
}, {
    dataIndex : "lv",
    hidden : true
}, {
    header : "会员卡号",
    dataIndex : "cardNo",
    width : 15
}, {
    header : "单位名称",
    dataIndex : "groupName",
    width : 45
}, {
    header : "当前会员等级",
    dataIndex : "lvName",
    width : 15
}, {
    header : "珠宝折算额",
    dataIndex : "jewerlyAmount",
    width : 15
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '团体会员等级',
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
                var _win = new Q.group.gradeadj.editWin();
                _win.setFormValue(sm.getSelected().get("groupCustId"));
                _win.on("submit", function() {
                    _grid.getStore().reload();
                });
                _win.show();
            }
        } ],
        hideSubTab : true,
        subTab : [],
        activeTab : 0,
        hideBtn : [ "view", "delete" ],
        column : gridColumn,
        store : {
            url : path + '/GroupCust_getJson.action',
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
        height : 250,
        width : 350,
        form : {
            labelWidth : 100,
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
                fieldLabel : '增值税号登记',
                name : "filter_LIKE_vatNo",
                xtype : "textfield"
            }, {
                fieldLabel : '营业执照编号',
                name : "filter_LIKE_businessLicense",
                xtype : "textfield"
            }, {
                fieldLabel : '商品条码',
                name : "filter_LIKE_posOrderList_goodsBar",
                xtype : "textfield"
            } ]
        }
    }
};