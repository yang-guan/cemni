/**
 * @author chao
 * 
 */
Q.competitor.newStyleWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.newStyleWin.superclass.constructor.call(this, {
        title : "深圳新工艺款式动态",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 402
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.newStyleWin, Ext.ux.Window, {
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
                        fieldLabel : "品类",
                        name : "model.szcategory",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "工艺",
                        name : "model.sztech",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                        fieldLabel : "上市日期",
                        name : "model.sztimetomkt",
                        format : "Y-m-d",
                        editable : false,
                        xtype : 'datefield',
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "批发政策",
                        name : "model.szwholesalepolicy",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                    	height : 33,
                        fieldLabel : "零售动态",
                        name : "model.szsalestatus",
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
            'szcategory' : _form.findField("model.szcategory").getValue(),
            'sztech' : _form.findField("model.sztech").getValue(),
            'sztimetomkt' : _form.findField("model.sztimetomkt").getValue(),
            'szwholesalepolicy' : _form.findField("model.szwholesalepolicy").getValue(),
            'szsalestatus' : _form.findField("model.szsalestatus").getValue(),
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
        _form.findField("model.szcategory").setValue(json.szcategory);
        _form.findField("model.sztech").setValue(json.sztech);
        _form.findField("model.sztimetomkt").setValue(new Date(json.sztimetomkt));
        _form.findField("model.szwholesalepolicy").setValue(json.szwholesalepolicy);
        _form.findField("model.szsalestatus").setValue(json.szsalestatus);
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