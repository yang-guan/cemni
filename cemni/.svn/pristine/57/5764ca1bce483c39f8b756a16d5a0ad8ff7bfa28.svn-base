/**
 * @author chao
 * 
 */
Q.competitor.chanSurveyWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.chanSurveyWin.superclass.constructor.call(this, {
        title : "渠道调研",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 435
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.chanSurveyWin, Ext.ux.Window, {
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
                        fieldLabel : "渠道拓展",
                        name : "model.cnexpand",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "竞品销售",
                        name : "model.cpsale",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                        fieldLabel : "竞品扣点",
                        name : "model.pointdeduction",
                        xtype : "numberfield",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "重大活动/事件",
                        name : "model.vevents",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "商场调整",
                        name : "model.malladjust",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "拓展人员变动",
                        name : "model.salesadjust",
                        maxLength : 100,
                        xtype : 'textarea',
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
            'cnexpand' : _form.findField("model.cnexpand").getValue(),
            'cpsale' : _form.findField("model.cpsale").getValue(),
            'pointdeduction' : _form.findField("model.pointdeduction").getValue(),
            'vevents' : _form.findField("model.vevents").getValue(),
            'malladjust' : _form.findField("model.malladjust").getValue(),
            'salesadjust' : _form.findField("model.salesadjust").getValue(),
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
        _form.findField("model.cnexpand").setValue(json.cnexpand);
        _form.findField("model.cpsale").setValue(json.cpsale);
        _form.findField("model.pointdeduction").setValue(json.pointdeduction);
        _form.findField("model.vevents").setValue(json.vevents);
        _form.findField("model.malladjust").setValue(json.malladjust);
        _form.findField("model.salesadjust").setValue(json.salesadjust);
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