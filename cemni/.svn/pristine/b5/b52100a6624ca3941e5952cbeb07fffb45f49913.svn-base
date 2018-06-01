/**
 * 发送对象-添加
 * 
 * @author：yuhb
 * @date：2017年3月19日 下午10:03:35
 */
Q.sms.objWin = function() {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.sms.objWin.superclass.constructor.call(this, {
        title : "发送对象（精确查询）-添加",
        layout : "fit",
        items : [ _formPanel ],
        width : 330,
        height : 200
    });
};

Ext.extend(Q.sms.objWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        var _win = this;
        return new Ext.form.FormPanel({
            items : [ {
                xtype : "fieldset",
                layout : "column",
                border : false,
                defaults : {
                    layout : "form",
                    border : false,
                    columnWidth : 1
                },
                items : [ {
                    items : [ {
                        fieldLabel : "会员卡号",
                        name : "cardNo",
                        xtype : "textfield",
                        width : 150
                    }, {
                        fieldLabel : "客户名称",
                        name : "name",
                        xtype : "textfield",
                        width : 150
                    }, {
                        fieldLabel : "手机号码",
                        name : "mobile",
                        xtype : "numberfield",
                        width : 150
                    } ]
                } ]
            } ],
            buttons : [ {
                text : "确定",
                handler : function() {
                    _win.doSelect();
                }
            }, {
                text : $("button.return"),
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },
    doSelect : function() {
        var _win = this;
        var _form = this.formPanel.getForm();
        var cardNo = _form.findField("cardNo").getValue();
        var name = _form.findField("name").getValue();
        var mobile = _form.findField("mobile").getValue();
        if (!cardNo && !name && !mobile) {
            Q.error("“会员卡号、客户名称、手机号码”不能同时为空！");
            return;
        }
        Ext.getBody().loadMask();
        Ext.Ajax.request({
            url : dealUrl + "_exactQryObjCust.action",
            params : {
                filter_EQ_cardNo_OR_EQ_name_OR_EQ_mobile : [ cardNo, name, mobile ]
            },
            success : function(response) {
                var json = Ext.decode(response.responseText);
                var data = json.data;
                if (data.length == 0) {
                    Q.error("没有符合条件的会员信息！");
                    return;
                }
                _win.hide();
                _win.fireEvent("submit", data);
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    }
});