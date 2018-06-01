/**
 * @author chao
 * 
 */
Q.competitor.custSurveyWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.custSurveyWin.superclass.constructor.call(this, {
        title : "客户调研",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 372
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.custSurveyWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.close();
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
                    	height : 33,
                        fieldLabel : "售后服务",
                        name : "model.aftersaleservice",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "会员权益",
                        name : "model.memberrights",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "促销品",
                        name : "model.promotion",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "会员等级",
                        name : "model.membergrade",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                        fieldLabel : "备注",
                        name : "model.comments",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                        fieldLabel : "上传附件",
                        xtype : "button",
                        anchor : "95%",
                        name : 'uploadBtn',
                        text : "上传附件",
                        handler : function() {
                            var formPanel = _win.formPanel;
                            var uploadFileGroupId = formPanel.getForm().findField("model.uploadFileGroupId").getValue();
                            vp.openUploadWindows("", uploadFileGroupId, "uploadFile4View", formPanel, "");
                        }
                    }, {
                        xtype : 'displayfield',
                        name : 'uploadFile4View',
                        fieldLabel : "附件查看",
                        anchor : "95%"
                    }, {
                        QfieldLabel : '附件上传ID',
                        name : 'model.uploadFileGroupId',
                        xtype : "hidden"
                    } ]
                } ]
            } ],
            buttons : [ {
                text : "保存",
                handler : function() {
                    _win.doSelect();
                }
            }, {
                text : "返回",
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    },

    doSelect : function() {
        var _form = this.formPanel.getForm();
        var arr = new Array();
        arr.push(_form.findField("uploadFile4View").getValue());
        var retR = new Ext.data.Record({
            'aftersaleservice' : _form.findField("model.aftersaleservice").getValue(),
            'memberrights' : _form.findField("model.memberrights").getValue(),
            'promotion' : _form.findField("model.promotion").getValue(),
            'membergrade' : _form.findField("model.membergrade").getValue(),
            'comments' : _form.findField("model.comments").getValue(),
            'uploadFileGroupId' : _form.findField("model.uploadFileGroupId").getValue(),
            'uploadFile4View' : arr
        });
        this.fireEvent("submit", retR);
        this.hide();
    },

    setWinForm : function(sm) {
        var _form = this.formPanel.getForm();
        var json = sm.data;
        _form.findField("model.aftersaleservice").setValue(json.aftersaleservice);
        _form.findField("model.memberrights").setValue(json.memberrights);
        _form.findField("model.promotion").setValue(json.promotion);
        _form.findField("model.membergrade").setValue(json.membergrade);
        _form.findField("model.comments").setValue(json.comments);
        _form.findField("model.uploadFileGroupId").setValue(json.uploadFileGroupId);
        if (json.uploadFile4View != null && json.uploadFile4View != "") {
            if ((json.uploadFile4View)[1] != null && (json.uploadFile4View)[1] != "") {
                var a = "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + (json.uploadFile4View)[1] + "'\" title=\"" + $("label.ClickToDownload") + "\">" + (json.uploadFile4View)[0] + "</a>]"
                _form.findField("uploadFile4View").setValue(a);
            } else {
                _form.findField("uploadFile4View").setValue((json.uploadFile4View)[0]);
            }
        }
    }
});