/**
 * 选择加盟商
 * 
 * @author：yuhb
 * @date：2016年11月25日 下午8:06:12
 */
Ext.ns('Q.archive');
Q.archive.chooseFranWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";// 需要执行的事件
    config.singleSelect = (config.singleSelect == undefined ? true : config.singleSelect);// 是否单选，默认为单选
    config.param = config.param || {};// 传递的参数

    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "加盟商-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 750,
        height : 450
    }, config);
    Q.archive.chooseFranWin.superclass.constructor.call(this, _config);

    // 加载表格数据
    var _store = this.gridPanel.getStore();
    if (config.action == "activity") {// 活动管理-参与加盟商
        _store.baseParams = {
            filter_NOTIN_franchiseeId : config.notIn
        };
    } else if (config.action == "agentanalyze") {// 活动管理-参与加盟商
        _store.baseParams = {
            filter_EQ_fraType : config.fraType
        };
    }

    // 加载数据
    Ext.getBody().loadMask();
    _store.load({
        callback : function() {
            Ext.getBody().unmask();
        }
    });
};

Ext.extend(Q.archive.chooseFranWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : cfg.singleSelect
            },
            cm : {
                columns : [ {
                    dataIndex : "franchiseeId",
                    hidden : true
                }, {
                    header : "加盟商编码",
                    dataIndex : "fraCode",
                    width : 15
                }, {
                    header : "加盟商名称",
                    dataIndex : "fraName",
                    width : 35
                }, {
                    header : "加盟商简称",
                    dataIndex : "shortName",
                    width : 20
                }, {
                    header : "类别",
                    dataIndex : "fraTypeName",
                    width : 10
                }, {
                    header : "状态",
                    dataIndex : "fraStatusName",
                    width : 8
                }, {
                    header : "实际控制人",
                    dataIndex : "actualCon",
                    width : 12
                } ]
            },
            store : {
                url : path + "/Franchisee_getJson.action",
                sort : "franchiseeId",
                dir : "desc",
                autoLoad : false
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    if (cfg.action == "activity") {// 活动关联加盟商
                        this.disable(); // 按钮灰化，防止重复点击
                        _win.activity2franch();
                    } else {// // 仅选择列表项返回给父页面
                        _win.doSelect();
                    }
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "加盟商编码/加盟商名称/实际控制人",
                name : "noOrNamerOrContor",
                width : 220
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    if (cfg.action == "activity") {// 活动管理-参与加盟商
                        _store.baseParams = {
                            filter_NOTIN_franchiseeId : cfg.notIn,
                            filter_LIKE_fraCode_OR_LIKE_fraName_OR_LIKE_actualCon : tbar.find("name", 'noOrNamerOrContor')[0].getValue()
                        };
                    } else if (cfg.action == "agentanalyze") {// 活动管理-参与加盟商
                        _store.baseParams = {
                            filter_EQ_fraType : cfg.fraType,
                            filter_LIKE_fraCode_OR_LIKE_fraName_OR_LIKE_actualCon : tbar.find("name", 'noOrNamerOrContor')[0].getValue()
                        };
                    } else {
                        _store.baseParams = {
                            filter_LIKE_fraCode_OR_LIKE_fraName_OR_LIKE_actualCon : tbar.find("name", 'noOrNamerOrContor')[0].getValue()
                        };
                    }
                    _store.load({
                        params : {
                            start : 0,
                            limit : 20
                        }
                    });
                }
            } ]
        });
    },

    activity2franch : function() {
        // 是否点击了列表中的“全选”复选框
        var isAllSelected = 0;
        var hd = this.gridPanel.getEl().select('div.x-grid3-hd-inner.x-grid3-hd-checker').first();
        var classList = hd.getAttribute("class").split(' ');
        for (var i = 0; i < classList.length; i++) {
            if (classList[i] == 'x-grid3-hd-checker-on') {
                isAllSelected = 1;
            }
        }
        var _win = this;
        var sms = this.gridPanel.getSelectionModel().getSelections();
        if (!isAllSelected && sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        var ids = [];
        Q.each(sms, function() {
            ids.push(this.data.franchiseeId);
        });
        var _store = this.gridPanel.getStore();
        var data = {
            filter_NOTIN_franchiseeId : cfg.notIn,
            ids : ids.join(","),
            isAllSelected : isAllSelected
        };
        Ext.apply(data, _store.baseParams);
        Ext.Ajax.request({
            url : path + "/Activity_chooseAllOrNot2.action",
            params : data,
            success : function(response) {
                var json = Ext.decode(response.responseText);
                _win.hide();
                _win.fireEvent("submit", json);
                Q.tips(Q.color("操作成功", "red"));
            },
            failure : function(response) {
                Q.tips(Q.color("操作失败", "red"));
            },
            callback : function() {
                Ext.getBody().unmask();
            }
        });
    },
    doSelect : function() {
        var sms = this.gridPanel.getSelectionModel().getSelections();
        if (sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        if (this.config.singleSelect) {
            this.fireEvent("submit", sms[0]);
        } else {
            this.fireEvent("submit", sms);
        }
        this.hide();
    }
});