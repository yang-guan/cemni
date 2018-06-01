/**
 * 图片上传
 * 
 * @author：yuhb
 * @date：2017年2月20日 上午11:49:01
 */
Ext.ns("Q.img");
Q.img.uploadWin = function(config) {
    this.config = config || {};
    var _formPanel = this.formPanel = this.createFormPanel();
    this.addEvents("upload");

    Q.img.uploadWin.superclass.constructor.call(this, {
        title : "图片上传",
        layout : "fit",
        items : [ _formPanel ],
        width : 450,
        height : 150
    });
};

Ext.extend(Q.img.uploadWin, Ext.ux.Window, {
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
        var typeArr = "jpg,jpeg,gif,png,bmp".split(",");
        for (var i = 0; i < typeArr.length; i++) {
            if (typeArr[i] == suffix.toLowerCase()) {
                formatFlag = true;
            }
        }
        if (!formatFlag) {
            Q.tips(Q.color("请选择“jpg|jpeg|gif|png|bmp”文件导入！", "red"));
            return;
        }
        _win.formPanel.getForm().submit({
            waitTitle : "正在上传数据，请稍候...",
            waitMsg : $("message.import.wait"),
            url : _win.config.url,
            method : 'POST',
            success : function(form, action) {
                _win.hide();
                _win.fireEvent("upload", action);
                Q.tips("图片上传成功！", "red");
            },
            failure : function(form, action) {
                Q.error($("message.import.failure") + "，异常信息如下：<br/>" + action.result.msg);
            }
        });
    }
});