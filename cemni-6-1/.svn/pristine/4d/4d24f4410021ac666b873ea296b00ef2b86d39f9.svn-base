/**
 * 选择异业伙伴
 * 
 * @author：yuhb
 * @date：2016年11月25日 下午8:06:12
 */
Ext.ns('Q.actment');

Q.actment.choosePartWin = function(config) {
    config = config || {};
    config.action = config.action || "doSelect";// 需要执行的事件
    config.singleSelect = (config.singleSelect == undefined ? true : config.singleSelect);// 是否单选，默认为单选
    config.param = config.param || {};// 传递的参数

    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "异业伙伴-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config);
    Q.actment.choosePartWin.superclass.constructor.call(this, _config);

    // 加载表格数据
    var _store = this.gridPanel.getStore();
    if (config.action == "activity") {// 活动管理-参与异业伙伴
        _store.baseParams = {
            filter_NOTIN_partnerid : config.notIn
        };
    }

    // 加载数据
    _store.load({
        params : {
            start : 0,
            limit : 20
        },
        callback : function() {
            Ext.getBody().unmask();
        }
    });
};

Ext.extend(Q.actment.choosePartWin, Ext.ux.Window, {
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
                    dataIndex : "partnerid",
                    hidden : true
                }, {
                    header : "异业伙伴编码",
                    dataIndex : "partnerno",
                    width : 80
                }, {
                    header : "异业伙伴名称",
                    dataIndex : "partnername",
                    width : 150
                }, {
                    header : "异业伙伴简称",
                    dataIndex : "name",
                    width : 120
                }, {
                    header : "异业伙伴类型",
                    dataIndex : "typeName",
                    width : 80
                }, {
                    header : "实际控制人",
                    dataIndex : "person",
                    width : 120
                } ]
            },
            store : {
                url : path + "/Partner_getJson.action",
                sort : "partnerid",
                dir : "desc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    if (cfg.action == "doSelect" || cfg.action == "activity") {// 仅选择列表项返回给父页面
                        this.disable(); // 按钮灰化，防止重复点击
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
                emptyText : "异业伙伴编码/异业伙伴名称/实际控制人",
                name : "noOrNamerOrContor",
                width : 220
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    if (cfg.action == "activity") { // 活动管理-参与异业伙伴
                        _store.baseParams = {
                            filter_NOTIN_partnerid : cfg.notIn,
                            filter_LIKE_partnerno_OR_LIKE_partnername_OR_LIKE_person : tbar.find("name", 'noOrNamerOrContor')[0].getValue()
                        };
                    } else {
                        _store.baseParams = {
                            filter_LIKE_partnerno_OR_LIKE_partnername_OR_LIKE_person : tbar.find("name", 'noOrNamerOrContor')[0].getValue()
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

    doSelect : function() {
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
			ids.push(this.data.partnerid);
		});
		var _store = this.gridPanel.getStore();
		var data = {
			filter_NOTIN_partnerid : cfg.notIn,
			ids : ids.join(","),
			isAllSelected : isAllSelected
		};
		Ext.apply(data, _store.baseParams);
		Ext.Ajax.request({
			url : path + "/Activity_chooseAllOrNot3.action",
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
	}

});