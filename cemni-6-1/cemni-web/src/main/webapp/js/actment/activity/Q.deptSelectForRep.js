Ext.ns('Q.activity');

Q.activity.chooseOrgWin = function(config) {
	config = config || {};
	config.action = config.action || "doSelect";// 需要执行的事件
	config.singleSelect = (config.singleSelect == undefined ? true : config.singleSelect);// 是否单选，默认为单选
	config.param = config.param || {};// 传递的参数
	var _gridPanel = this.gridPanel = this.createGridPanel(config);
	this.addEvents("submit");

	var _config = this.config = Ext.apply({
		title : "部门-选择",
		layout : "border",
		items : [ _gridPanel ],
		width : 600,
		height : 380
	}, config);
	Q.activity.chooseOrgWin.superclass.constructor.call(this, _config);
	this.init(config.orgCodes);
};

Q.activity.selStores = {
	// 组织机构类型
	typeStore : Q.common.selDict(8100),
};

Ext.extend(Q.activity.chooseOrgWin, Ext.ux.Window, {
	listeners : {
		hide : function() {
			this.hide();
			this.url = "";
		}
	},
	createGridPanel : function(cfg) {
		var _win = this;
		return new Ext.ux.grid.GridPanel({
			pageSize : 0,
			sm : {
				singleSelect : cfg.singleSelect
			},
			cm : {
				columns : [ {
					Qheader : "部门编码",
					header : "部门编码",
					dataIndex : "orgCode",
					width : 180
				}, {
					Qheader : "部门名称",
					header : "部门名称",
					dataIndex : "name",
					width : 180
				} ]
			},
			store : {
				url : path + "/Activity_getOrgJsonForAct.action",
				sort : "type",
				dir : "asc",
				baseParams : {
					filter_IN_type : [2,3,4,5]
				},
				autoLoad : false
			},
			tbar : [ {
				text : "确定",
				iconCls : "icon-save",
				handler : function() {
					_win.doSelect();
				}
			}, {
				text : "返回",
				iconCls : "icon-return",
				handler : function() {
					_win.hide();
				}
			}, "->", {
				xtype : "textfield",
				emptyText : "编码/名称",
				name : "orgCode",
				width : 230
			}, {
				text : "查询",
				iconCls : "icon-search",
				handler : function() {
					var _store = _win.gridPanel.getStore();
					var tbar = _win.gridPanel.getTopToolbar();
					_store.baseParams = {
						filter_EQ_orgCode_OR_LIKE_name : tbar.find("name", 'orgCode')[0].getValue(),
						filter_IN_type : [2,3,4,5]
					};
					_store.load();
				}
			} ]
		});
	},
    init : function(orgCodes) {
        var sm = this.gridPanel.getSelectionModel();
        var _store = this.gridPanel.getStore();
        _store.load({
            callback : function() {
                if (orgCodes) {
                    var _orgCodeArr = orgCodes.split(",");
                    var cnt = _store.getCount();
                    for (var i = 0; i < cnt; i++) {
                        var r = _store.getAt(i);
                        for (var j = 0; j < cnt; j++) {
                            if (_orgCodeArr[j] == r.data.orgCode) {
                                sm.selectRow(i, true);
                            }
                        }
                    }
                }
                Ext.getBody().unmask();
            }
        });
    },
	doSelect : function() {
		var sms = this.gridPanel.getSelectionModel().getSelections();
		if (this.config.singleSelect) {
			this.fireEvent("submit", sms[0]);
		} else {
			this.fireEvent("submit", sms);
		}
		this.hide();
	}

});