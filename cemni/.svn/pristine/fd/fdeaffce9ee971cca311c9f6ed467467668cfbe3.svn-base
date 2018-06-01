/**
 * 短信日志
 * 
 * @author：yuhb
 * @date：2017年1月5日 上午1:48:12
 */
Ext.ns('Q.smslog');
var dealUrl = path + "/SmsLog";

// 列表
Q.smslog.gridColumn = [ {
    dataIndex : "smsLogId",
    hidden : true
}, {
    header : "手机号码",
    dataIndex : "mobile",
    width : 10
}, {
    header : "短信类型",
    dataIndex : "typeName",
    width : 10
}, {
    header : "发送时间",
    dataIndex : "cdate",
    renderer : Q.common.timeRenderer,
    width : 20
}, {
    header : "是否成功",
    dataIndex : "status",
    width : 10,
    renderer : Q.common.yesOrNotRenderer
}, {
    header : "重发人",
    dataIndex : "muser",
    width : 10
}, {
    header : "重发时间",
    dataIndex : "mdate",
    renderer : Q.common.timeRenderer,
    width : 20
}, {
    header : "重发次数",
    dataIndex : "reSendCnt",
    width : 10
} ];

// 按钮
Q.smslog.tbarBnt = [ {
    name : "resendBnt",
    text : "重新发送",
    iconCls : "icon-edit",
    build : power.resendsms,
    handler : function() {
        var _grid = vp.grid;
        var sms = _grid.getSelectionModel().getSelections();
        if (sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var ids = [];
        Q.each(sms, function() {
            ids.push(this.data.smsLogId);
        });
        Ext.getBody().submitMask();
        Ext.Ajax.request({
            url : dealUrl + "_reSendSms.action",
            params : {
                ids : ids
            },
            success : function(response) {
                if (!Ext.decode(response.responseText).success) {
                    Q.error("短信重新发送失败！");
                    return;
                } else {
                    Q.tips(Q.color("短信重新发送成功！", "red"));
                    _grid.getStore().reload();
                }
            },
            failure : function() {
                Q.error("短信重新发送失败！");
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : "短信日志",
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.smslog.tbarBnt,
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        sm : {
            singleSelect : false
        },
        column : Q.smslog.gridColumn,
        store : {
            idProperty : "smsLogId",
            url : dealUrl + '_getJson.action',
            sort : "smsLogId",
            dir : "desc"
        },
        listEditStateFn : [ {
            resendBnt : function() {
                var k = 0;
                Q.each(vp.grid.getSelectionModel().getSelections(), function() {
                    if (this.data.status == 1) {
                        k++;
                    }
                });
                return !k;
            }
        } ]
    },
    editWin : {
        createEditWin : eval("Q.comm.CommModelEditWin"),
        width : 600,
        height : 380,
        form : {
            labelWidth : 70,
            columnWidth : 0.5,
            items : [ {
                fieldLabel : '日志ID',
                name : 'model.smsLogId',
                xtype : "textfield",
                anchor : "90%"
            }, {
                fieldLabel : '手机号码',
                name : 'model.mobile',
                xtype : "textfield",
                anchor : "90%"
            }, {
                fieldLabel : '短信类型',
                name : 'model.typeName',
                xtype : "textfield",
                anchor : "90%"
            }, {
                fieldLabel : '发送时间',
                name : 'model.cdate',
                xtype : "datetimefield",
                format : "Y-m-d H:i:s",
                anchor : "90%"
            }, {
                fieldLabel : '重发人',
                name : 'model.muser',
                xtype : "textfield",
                anchor : "90%"
            }, {
                fieldLabel : '重发时间',
                name : 'model.mdate',
                xtype : "datetimefield",
                format : "Y-m-d H:i:s",
                anchor : "90%"
            }, {
                fieldLabel : '重发次数',
                name : 'model.reSendCnt',
                xtype : "textfield",
                anchor : "90%"
            }, {
                fieldLabel : '是否成功',
                hiddenName : 'model.status',
                xtype : "uxcombo",
                store : Q.common.yesOrNotStore,
                anchor : "90.7%"
            }, {
                columnWidth : 1,
                fieldLabel : '请求报文',
                name : 'model.reqContent',
                xtype : "textarea",
                anchor : "95%",
                height : 60
            }, {
                columnWidth : 1,
                fieldLabel : '返回报文',
                name : 'model.respContent',
                xtype : "textarea",
                anchor : "95%",
                height : 110
            } ]
        }
    },
    searchWin : {
        createSearchWin : eval("Q.comm.CommSearchWin"),
        width : 350,
        height : 250,
        form : {
            columnWidth : 1,
            labelWidth : 100,
            items : [ {
                fieldLabel : "短信类型",
                hiddenName : "filter_EQ_type",
                xtype : "uxcombo",
                clearable : true,
                valueField : "value",
                displayField : "name",
                anchor : "95.6%",
                store : Q.common.selDict(8000)
            }, {
                fieldLabel : "是否成功",
                hiddenName : "filter_EQ_status",
                xtype : "uxcombo",
                clearable : true,
                anchor : "95.6%",
                store : Q.common.yesOrNotStore
            }, {
                fieldLabel : '手机号码',
                name : "filter_LIKE_mobile",
                xtype : "numberfield"
            }, {
                fieldLabel : "发送开始日期",
                name : 'filter_GE_cdate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_cdate = _form.findField('filter_GE_cdate').getValue();
                        var filter_LE_cdate = _form.findField("filter_LE_cdate").getValue();
                        if (filter_GE_cdate && filter_LE_cdate) {
                            if (filter_GE_cdate > filter_LE_cdate) {
                                Q.warning("发送开始日期不能大于发送结束日期！");
                                _form.findField('filter_GE_cdate').setValue("");
                            }
                        }
                    }
                }
            }, {
                fieldLabel : "发送结束日期",
                name : 'filter_LE_cdate',
                xtype : "datefield",
                format : 'Y-m-d',
                editable : false,
                maxValue : new Date(),
                listeners : {
                    select : function() {
                        var _form = vp.searchWin.formPanel.getForm();
                        var filter_GE_cdate = _form.findField('filter_GE_cdate').getValue();
                        var filter_LE_cdate = _form.findField("filter_LE_cdate").getValue();
                        if (filter_GE_cdate && filter_LE_cdate) {
                            if (filter_GE_cdate > filter_LE_cdate) {
                                Q.warning("发送结束日期不能小于发送开始日期！");
                                _form.findField('filter_LE_cdate').setValue("");
                            }
                        }
                    }
                }
            } ]
        }
    }
};