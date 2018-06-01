Ext.ns('Q.archive');
Q.archive.indiInfoWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();

    Q.archive.indiInfoWin.superclass.constructor.call(this, {
        title : "个人客户信息",
        layout : "fit",
        items : [ _formPanel ],
        width : 700,
        height : 350
    });
};

Ext.extend(Q.archive.indiInfoWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        return new Ext.form.FormPanel({
            border : false,
            items : [ {
                xtype : "fieldset",
                layout : "column",
                border : false,
                defaults : {
                    layout : "form",
                    border : false,
                    columnWidth : 0.5,
                    labelWidth : 120
                },
                items : [ {
                    items : {
                        fieldLabel : '客户名称',
                        name : 'individCustName',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '手机号码',
                        name : 'individCustMobile',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '固话号码',
                        name : 'individCustPhone',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '出生年月',
                        name : 'individCustYear',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '职业',
                        name : 'individCustjob',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '所属私人俱乐部',
                        name : 'individCustAddressClub',
                        xtype : "textfield"
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '地址',
                        name : 'individCustAddress',
                        xtype : "textfield",
                        width : 480
                    }
                }, {
                    items : {
                        fieldLabel : '对什么主题有兴趣',
                        name : 'individCustAddressSubject',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢的化妆品品牌',
                        name : 'individCustAddressCosmeticsBrand',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢的菜式',
                        name : 'individCustAddressLikeFood',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢的消费场所',
                        name : 'individCustAddressConsumerPlace',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '嗜好和娱乐',
                        name : 'individCustAddressHobby',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢读什么书',
                        name : 'individCustAddressLikeBook',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢的度假方式',
                        name : 'individCustAddressResort',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '喜欢观赏的运动',
                        name : 'individCustAddressLikeSport',
                        xtype : "textfield"
                    }
                }, {
                    columnWidth : 1,
                    items : {
                        fieldLabel : '备注',
                        name : 'individCustRemark',
                        xtype : "textfield",
                        width : 480
                    }
                }, {
                    items : {
                        fieldLabel : '意向价位',
                        name : 'purposePriceName',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '意向产品类型',
                        name : 'purposeProductName',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '意向产品品类',
                        name : 'purposeCategoryName',
                        xtype : "textfield"
                    }
                }, {
                    items : {
                        fieldLabel : '需求时间',
                        name : 'purposeDay',
                        xtype : "textfield"
                    }
                } ]
            } ]
        });
    },
    setWinValue : function(individCustId) {
        var _win = this;
        Ext.getBody().loadMask();
        Ext.Ajax.request({
            url : dealUrl + "_getIndividCustInfo.action",
            params : {
                "model.individCust.individCustId" : individCustId
            },
            success : function(response) {
                var json = Ext.decode(response.responseText);
                if (!Ext.decode(response.responseText).success) {
                    Q.error("<font color=''>" + $("message.submit.failure") + $("message.system.error"));
                    return;
                } else {
                    var _form = _win.formPanel.getForm();
                    var data = json.data;

                    _form.findField('individCustName').setValue(data.name);
                    _form.findField('individCustMobile').setValue(data.mobile);
                    _form.findField('individCustPhone').setValue(data.phone);
                    _form.findField('individCustYear').setValue(Ext.util.Format.date(data.birthday, 'Y-m-d'));
                    _form.findField('individCustAddress').setValue(data.fullAddress);
                    _form.findField('individCustjob').setValue(data.jobName);
                    _form.findField('individCustAddressClub').setValue(data.club);
                    _form.findField('individCustAddressSubject').setValue(data.subject);
                    _form.findField('individCustAddressCosmeticsBrand').setValue(data.cosmeticsBrand);
                    _form.findField('individCustAddressLikeFood').setValue(data.likeFood);
                    _form.findField('individCustAddressConsumerPlace').setValue(data.consumerPlace);
                    _form.findField('individCustAddressHobby').setValue(data.hobby);
                    _form.findField('individCustAddressLikeBook').setValue(data.likeBook);
                    _form.findField('individCustAddressResort').setValue(data.resort);
                    _form.findField('individCustAddressLikeSport').setValue(data.likeSport);
                    _form.findField('individCustRemark').setValue(data.remark);
                    _form.findField('purposePriceName').setValue(data.purposePriceName);
                    _form.findField('purposeProductName').setValue(data.purposeProductName);
                    _form.findField('purposeCategoryName').setValue(data.purposeCategoryName);
                    _form.findField('purposeDay').setValue(Ext.util.Format.date(data.purposeDay, 'Y-m-d'));
                }
            },
            failure : function() {
                Q.error($("message.submit.failure"));
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    }
});