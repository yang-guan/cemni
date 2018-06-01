/**
 * @author chao
 * 
 */
Q.competitor.adDeployWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.adDeployWin.superclass.constructor.call(this, {
        title : "品牌广告投放",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 409
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.adDeployWin, Ext.ux.Window, {
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
                        fieldLabel : "广告形式",
                        name : "model.adtype",
                        xtype : "textarea",
                        allowBlank : false,
                        anchor : "95%"
                    }, {
                        fieldLabel : "投放开始日期",
                        name : "model.adstart",
                        format : 'Y-m-d',
                        xtype : 'datefield',
                        anchor : "95%",
                        editable : false,
                        listeners : {
                            select : function() {
                                var _form = _win.formPanel.getForm();
                                var createBeginDate = _form.findField('model.adstart').getValue();
                                var createEndDate = _form.findField("model.adend").getValue();
                                if (createBeginDate && createEndDate) {
                                    if (createEndDate < createBeginDate) {
                                        Q.warning("创建日期不能大于结束日期");
                                        _form.findField('model.adstart').setValue("");
                                    }
                                }
                            }
                        }
                    }, {
                        fieldLabel : "投放结束日期",
                        name : "model.adend",
                        allowBlank : true,
                        format : 'Y-m-d',
                        xtype : 'datefield',
                        anchor : "95%",
                        editable : false,
                        listeners : {
                            select : function() {
                                var _form = _win.formPanel.getForm();
                                var createBeginDate = _form.findField('model.adstart').getValue();
                                var createEndDate = _form.findField("model.adend").getValue();
                                if (createBeginDate && createEndDate) {
                                    if (createEndDate < createBeginDate) {
                                        Q.warning("结束日期不能小于创建日期");
                                        Ext.getCmp('model.adend').setValue("");
                                    }
                                }
                            }
                        }
                    }, {
                        fieldLabel : "广告投放费用",
                        name : "model.adcost",
                        maxLength : 10,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                        height : 33,
                        fieldLabel : "评估及应对建议",
                        name : "model.evaluateadvice",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                        height : 33,
                        fieldLabel : "市场策略",
                        name : "model.mktstrategy",
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
            'adtype' : _form.findField("model.adtype").getValue(),
            'adstart' : _form.findField("model.adstart").getValue(),
            'adend' : _form.findField("model.adend").getValue(),
            'adcost' : _form.findField("model.adcost").getValue(),
            'evaluateadvice' : _form.findField("model.evaluateadvice").getValue(),
            'mktstrategy' : _form.findField("model.mktstrategy").getValue(),
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
        _form.findField("model.adtype").setValue(json.adtype);
        _form.findField("model.adstart").setValue(new Date(json.adstart));
        _form.findField("model.adend").setValue(new Date(json.adend));
        _form.findField("model.adcost").setValue(json.adcost);
        _form.findField("model.evaluateadvice").setValue(json.evaluateadvice);
        _form.findField("model.mktstrategy").setValue(json.mktstrategy);
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