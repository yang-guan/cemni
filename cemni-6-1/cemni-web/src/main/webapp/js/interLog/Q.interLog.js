/**
 * 接口日志
 * 
 * @author：yuhb
 * @date：2017年1月5日 上午1:48:12
 */
Ext.ns('Q.interLog');
var dealUrl = path + "/InterLog";

Q.interLog.selStores = {
    // 接口来源
    srcStore : Q.common.selDict(9100)
};

// 列表
Q.interLog.gridColumn = [ {
    dataIndex : "url",
    hidden : true
}, {
    header : "接口ID",
    dataIndex : "interfaceLogId",
    width : 10
}, {
    header : "接口来源",
    dataIndex : "srcName",
    width : 10
}, {
    header : "是否成功",
    dataIndex : "status",
    width : 8,
    renderer : Q.common.yesOrNotRenderer
}, {
    header : "请求时间",
    dataIndex : "reqTime",
    renderer : Q.common.timeRenderer,
    width : 15
}, {
    header : "crm类方法",
    dataIndex : "crmClassMethod",
    width : 55
} ];

// 按钮
var tbarBnt = [ {
    name : "resendBnt",
    text : "重新发送",
    iconCls : "icon-edit",
    build : power.resendinter,
    handler : function() {
        var _grid = this.findParentByType(Ext.grid.GridPanel);
        var sm = _grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        Ext.Ajax.request({
            url : dealUrl + "_rePost.action",
            params : {
                "model.interfaceLogId" : sm.get("interfaceLogId")
            },
            success : function(response) {
                if (!Ext.decode(response.responseText).success) {
                    Q.error("重新发送失败！");
                    return;
                } else {
                    Q.tips(Q.color("重新发送成功！", "red"));
                    _grid.getStore().reload();
                }
            },
            failure : function() {
                Q.error("重新发送失败！");
            }
        });
    }
} ];

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : "接口日志",
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : tbarBnt,
        hideSubTab : true,
        hideBtn : [ "delete", "add", "edit" ],
        subTab : [],
        column : Q.interLog.gridColumn,
        store : {
            idProperty : "interfaceLogId",
            url : dealUrl + '_getJson.action',
            sort : "reqTime",
            dir : "desc"
        },
        listEditStateFn : [ {
            resendBnt : function(r) {
                if (r.data.status == 1 || !r.data.url) {
                    return false;
                }
                return true;
            }
        } ]
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 350,
        height : 240,
        form : {
            columnWidth : 1,
            labelWidth : 90,
            items : [ {
                fieldLabel : "接口来源",
                hiddenName : "filter_EQ_src",
                xtype : "uxcombo",
                clearable : true,
                valueField : "value",
                displayField : "name",
                store : Q.interLog.selStores.srcStore
            }, {
                fieldLabel : "是否成功",
                hiddenName : "filter_EQ_status",
                xtype : "uxcombo",
                clearable : true,
                store : Q.common.yesOrNotStore
            }, {
                fieldLabel : "请求开始时间",
                name : 'filter_GE_reqTime',
                xtype : "datefield",
                format : 'Y-m-d',
                maxValue : new Date()
            }, {
                fieldLabel : "请求结束时间",
                name : 'filter_LE_reqTime',
                xtype : "datefield",
                format : 'Y-m-d',
                maxValue : new Date()
            }, {
                fieldLabel : 'crm类方法',
                name : 'filter_LIKE_crmClassMethod',
                xtype : "textfield"
            } ]
        }
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        maximized : true,
        form : {
            labelWidth : 60,
            columnWidth : '0.5',
            items : [ {
                fieldLabel : '接口来源',
                hiddenName : 'model.src',
                xtype : "uxcombo",
                valueField : "value",
                displayField : "name",
                anchor : "90%",
                store : Q.interLog.selStores.srcStore
            }, {
                fieldLabel : '是否成功',
                hiddenName : 'model.status',
                xtype : "uxcombo",
                anchor : "90%",
                store : Q.common.yesOrNotStore
            }, {
                fieldLabel : '请求时间',
                name : 'model.reqTime',
                xtype : "datetimefield",
                format : "Y-m-d H:i:s",
                anchor : "90%"
            }, {
                fieldLabel : '响应时间',
                name : 'model.respTime',
                xtype : "datetimefield",
                format : "Y-m-d H:i:s",
                anchor : "90%"
            }, {
                fieldLabel : '重发人',
                name : 'model.muser',
                anchor : "90%",
                xtype : "textfield"
            }, {
                fieldLabel : '重发时间',
                name : 'model.mdate',
                xtype : "datetimefield",
                format : "Y-m-d H:i:s",
                anchor : "90%"
            }, {
                columnWidth : 1,
                fieldLabel : 'crm类方法',
                name : 'model.crmClassMethod',
                anchor : "95%",
                xtype : "textfield"
            }, {
                fieldLabel : '请求内容',
                name : 'model.reqContent',
                xtype : "textarea",
                anchor : "90%",
                height : 300
            }, {
                fieldLabel : '响应内容',
                name : 'model.respContent',
                xtype : "textarea",
                anchor : "90%",
                height : 300
            } ]
        }
    }
};