/**
 * 字典表多选框
 * 
 * @author：yuhb
 * @date：2017年3月28日 下午11:50:32
 */
Ext.ns('Q.dict');
Q.dict.chooseDictWin = function(config) {
    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : config.title,
        layout : "fit",
        items : [ _gridPanel ],
        width : 300,
        height : 350
    }, config);
    Q.dict.chooseDictWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.dict.chooseDictWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            pageSize : 0,
            cm : {
                columns : [ {
                    dataIndex : "dictId",
                    hidden : true
                }, {
                    dataIndex : "value",
                    hidden : true
                }, {
                    header : cfg.title,
                    dataIndex : "name"
                } ]
            },
            store : {
                url : path + "/Dict_selDict.action?filter_EQ_dictCode=" + cfg.dictCode,
                sort : "orderNo",
                dir : "asc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    var sms = _win.gridPanel.getSelectionModel().getSelections();
                    _win.fireEvent("submit", sms);
                    _win.hide();
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            } ]
        });
    }
});

// 字典表文本框函数
Q.dict.multiSelectText = function(p, title, dictCode, dictNameObj, dictValueObj, isHidden) {
    p.getEl().on('mouseover', function() {
        if (!p.getValue()) {
            Ext.QuickTips.unregister(p.el);
        } else {
            Ext.QuickTips.register({
                target : p.el,
                text : p.getValue()
            });
        }
    });
    p.getEl().on('click', function(p) {
        if (isHidden=(bntType == "view")) {
            return;
        }
       
        var _win = new Q.dict.chooseDictWin({
            title : title,
            dictCode : dictCode
        });
        _win.on("submit", function(r) {
            var codeArr = [];
            var nameArr = [];
            Q.each(r, function() {
                codeArr.push(this.data.value);
                nameArr.push(this.data.name);
            });
            dictNameObj.setValue(nameArr.join(","));
            dictValueObj.setValue(codeArr.join(","));
        });
        _win.show();
    });
};