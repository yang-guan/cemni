/**
 * @author chao
 * 
 */
Q.competitor.goodStatusWin = function(config) {
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("submit");

    Q.competitor.goodStatusWin.superclass.constructor.call(this, {
        title : "竞品商品动态",
        layout : "fit",
        items : [ _formPanel ],
        width : 500,
        height : 480
    });
    if (config.bz == "edit") {
        this.setWinForm(config.sm);
    }
};

Ext.extend(Q.competitor.goodStatusWin, Ext.ux.Window, {
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
                    	height : 30,
                        fieldLabel : "产品系列",
                        name : "model.pdseries",
                        xtype : "textarea",
                        anchor : "95%"
                    }, {
                    	height : 30,
                        fieldLabel : "品类",
                        name : "model.pdcategory",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                    	height : 30,
                        fieldLabel : "级别",
                        name : "model.pdgrade",
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                        fieldLabel : "铺货量",
                        name : "model.pddistriamo",
                        maxLength : 100,
                        xtype : 'numberfield',
                        anchor : "95%"
                    },  {
                    	height : 30,
                        fieldLabel : "钻石重量",
                        name : "model.jewelweight",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                        fieldLabel : "价格",
                        name : "model.pdprice",
                        maxLength : 10,
                        xtype : 'numberfield',
                        anchor : "95%"
                    }, {
                    	height : 30,
                        fieldLabel : "卖点分析",
                        name : "model.pdspanalysis",
                        maxLength : 100,
                        xtype : 'textarea',
                        anchor : "95%"
                    }, {
                    	height : 30,
                        fieldLabel : "评估及应对",
                        name : "model.evaluatestrategy",
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
            'pdseries' : _form.findField("model.pdseries").getValue(),
            'pdcategory' : _form.findField("model.pdcategory").getValue(),
            'pdgrade' : _form.findField("model.pdgrade").getValue(),
            'pddistriamo' : _form.findField("model.pddistriamo").getValue(),
            'pdprice' : _form.findField("model.pdprice").getValue(),
            'jewelweight': _form.findField("model.jewelweight").getValue(),
            'pdspanalysis' : _form.findField("model.pdspanalysis").getValue(),
            'evaluatestrategy' : _form.findField("model.evaluatestrategy").getValue(),
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
        _form.findField("model.pdseries").setValue(json.pdseries);
        _form.findField("model.pdcategory").setValue(json.pdcategory);
        _form.findField("model.pdgrade").setValue(json.pdgrade);
        _form.findField("model.pddistriamo").setValue(json.pddistriamo);
        _form.findField("model.pdprice").setValue(json.pdprice);
        _form.findField("model.pdspanalysis").setValue(json.pdspanalysis);
        _form.findField("model.evaluatestrategy").setValue(json.evaluatestrategy);
        _form.findField("model.comments").setValue(json.comments);
        _form.findField("model.uploadFileGroupId").setValue(json.uploadFileGroupId);
        _form.findField("model.jewelweight").setValue(json.jewelweight);
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