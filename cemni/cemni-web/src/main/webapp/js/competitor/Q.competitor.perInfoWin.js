/**
 * @author chao
 * 
 */
Q.competitor.perInfoWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.perInfoWin.superclass.constructor.call(this, {
        title : "人事信息",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 489
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.perInfoWin, Ext.ux.Window, {
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
                        fieldLabel : "竞品岗位",
                        name : "model.job",
                        allowBlank : false,
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "提成方式",
                        name : "model.commissionway",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                        fieldLabel : "销售额（月）",
                        name : "model.salespermonth",
                        xtype : "numberfield",
                        anchor : "95%"
                    }, {
                        fieldLabel : "提点系数",
                        name : "model.percentage",
                        maxLength : 2,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                        fieldLabel : "竞品底薪",
                        name : "model.basicsalary",
                        maxLength : 10,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                        fieldLabel : "月综合收入",
                        name : "model.avgsalary",
                        maxLength : 10,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                        fieldLabel : "年终奖",
                        name : "model.yearendaward",
                        maxLength : 10,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "其他福利",
                        name : "model.otherprofits",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                        fieldLabel : "社保公积金",
                        maxLength : 10,
                        name : "model.socialinsurance",
                        xtype : 'numberfield',
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
            'job' : _form.findField("model.job").getValue(),
            'commissionway' : _form.findField("model.commissionway").getValue(),
            'salespermonth' : _form.findField("model.salespermonth").getValue(),
            'percentage' : _form.findField("model.percentage").getValue(),
            'basicsalary' : _form.findField("model.basicsalary").getValue(),
            'avgsalary' : _form.findField("model.avgsalary").getValue(),
            'yearendaward' : _form.findField("model.yearendaward").getValue(),
            'otherprofits' : _form.findField("model.otherprofits").getValue(),
            'socialinsurance' : _form.findField("model.socialinsurance").getValue(),
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
        _form.findField("model.job").setValue(json.job);
        _form.findField("model.commissionway").setValue(json.commissionway);
        _form.findField("model.salespermonth").setValue(json.salespermonth);
        _form.findField("model.percentage").setValue(json.percentage);
        _form.findField("model.basicsalary").setValue(json.basicsalary);
        _form.findField("model.avgsalary").setValue(json.avgsalary);
        _form.findField("model.yearendaward").setValue(json.yearendaward);
        _form.findField("model.otherprofits").setValue(json.otherprofits);
        _form.findField("model.socialinsurance").setValue(json.socialinsurance);
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