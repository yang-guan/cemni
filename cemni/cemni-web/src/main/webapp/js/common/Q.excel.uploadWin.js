/**
 * excel上传
 * 
 * @author：yuhb
 * @date：2016年12月28日 上午13:28:11
 */
Ext.ns("Q.excel");
Q.excel.uploadWin = function(config) {
    this.config = config || {};
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("upload");

    Q.excel.uploadWin.superclass.constructor.call(this, {
        title : "excel模版导入",
        layout : "fit",
        items : [ _formPanel ],
        width : 450,
        height : 150
    });
};

Ext.extend(Q.excel.uploadWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.formPanel.getForm().reset();
        }
    },
    createFormPanel : function() {
        var _win = this;
        return new Ext.form.FormPanel({
            fileUpload : true,
            region : "center",
            bodyStyle : "padding: 20px",
            border : false,
            labelWidth : 60,
            items : [ {
                xtype : "textfield",
                name : "fileName",
                hidden : true
            }, {
                fieldLabel : "选择文件<font color='red'>*</font>",
                name : "file",
                xtype : "textfield",
                inputType : 'file',
                allowBlank : false,
                width : 300,
                listeners : {
                    change : function(t, n, o) {
                        _win.formPanel.getForm().findField("fileName").setValue(n);
                    }
                }
            } ],
            buttons : [ {
                text : $("button.save"),
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
        var isValid = this.formPanel.form.isValid();
        if (!isValid) {
            return;
        }
        // 校验文件格式
        var _fileName = _win.formPanel.getForm().findField("fileName").getValue();
        var formatFlag = false;
        var suffix = _fileName.substring(_fileName.lastIndexOf(".") + 1);
        var typeArr = "xls,xlsx".split(",");
        for (var i = 0; i < typeArr.length; i++) {
            if (typeArr[i] == suffix) {
                formatFlag = true;
            }
        }
        if (!formatFlag) {
            Q.tips(Q.color("请选择excel文件导入！", "red"));
            return;
        }
        _win.formPanel.getForm().submit({
            waitTitle : "正在上传数据，请稍候...",
            waitMsg : $("message.import.wait"),
            url : _win.config.url,
            method : 'POST',
            success : function(form, action) {
                _win.hide();
                if (_win.config.tp == "1") {
                    Q.Activity.excleErgodic(action);
                } else if (_win.config.tp == "2") {
                    Q.Templatement.excleErgodic(action);
                } else {
                    _win.fireEvent("upload", action);
                    if (action.result.success) {
                        Q.tips(Q.color($("message.import.success"), "red"));
                    } else {
                        Q.tips(Q.color(action.result.msg, "red"));
                    }

                }
            },
            failure : function(form, action) {
                Q.error($("message.import.failure") + "，异常信息如下：<br/>" + action.result.msg);
            }
        });
    }
});