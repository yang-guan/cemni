/**
 * 供应商管理
 */
Ext.ns('Q.Supplier');
var dealUrl = path + '/Supplier';

Q.Supplier.selStores = {
	// 年度等级评估
	gradStore : Q.common.selDict(1400),
	// 性别
	sexFra : Q.common.selDict(1100)
};

// 列表
var gridColumn = [ {
	header : "供应商编码",
	width : 15,
	dataIndex : "supplierno"
}, {
	header : "供应商名称",
	width : 40,
	dataIndex : "suppliername"
}, {
	header : "供应商简称",
	width : 25,
	dataIndex : "name"
}, {
	header : "是否有效",
	dataIndex : "isValid",
	width : 10,
	renderer : Q.common.yesOrNotRenderer
}, {
	header : "创建日期",
	width : 10,
	dataIndex : "cdate",
	renderer : Q.common.dateRenderer
} ];

// 表单
var editFormItems = [ {
	columnWidth : 1,
	layout : 'column',
	xtype : 'fieldset',
	anchor : "-3",
	title : '基本信息',
	defaults : {
		layout : 'form',
		border : false,
		columnWidth : .33,
		labelWidth : 90
	},
	items : [ {
		items : {
			name : 'model.supplierid',
			xtype : "hidden",
			anchor : "85%"
		}
	}, {
		items : {
			fieldLabel : '供应商编码',
			name : 'model.supplierno',
			emptyText : '系统自动生成',
			readOnly : true,
			xtype : "textfield",
			anchor : "85%"
		}
	}, {
		items : {
			fieldLabel : '供应商名称',
			name : 'model.suppliername',
			anchor : "85%",
			readOnly : true,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '供应商简称',
			anchor : "85%",
			name : 'model.name',
			readOnly : true,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '实际控制人',
			name : 'model.person',
			xtype : "textfield",
			maxLength : 30,
			anchor : "85%"
		}
	} ]
}, {
	columnWidth : 1,
	xtype : 'fieldset',
	anchor : "-3",
	title : '拓展信息',
	layout : 'column',
	defaults : {
		layout : 'form',
		border : false,
		columnWidth : .33,
		labelWidth : 90
	},
	items : [ {
		columnWidth : 1,
		items : {
			fieldLabel : '主体范围名称',
			name : 'model.amountscope',
			height : 35,
			xtype : "textarea",
			maxLength : 200,
			anchor : "94%"
		}
	}, {
		items : {
			fieldLabel : '纳税人识别码',
			name : 'model.taxpayercode',
			anchor : "85%",
			maxLength : 40,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '社会信用代码',
			name : 'model.creditCode',
			maxLength : 40,
			anchor : "85%",
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '组织机构代码',
			name : 'model.orgNo',
			maxLength : 40,
			anchor : "85%",
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '业务联系人',
			name : 'model.business',
			anchor : "85%",
			maxLength : 30,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '联系电话',
			name : 'model.telephone',
			regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
			regexText : "请输入正确的联系人电话",
			anchor : "85%",
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '传真号码',
			name : 'model.faxnum',
			anchor : "85%",
			maxLength : 40,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '开户账户',
			name : 'model.account',
			anchor : "85%",
			maxLength : 50,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '合作项目',
			anchor : "85%",
			maxLength : 150,
			name : 'model.project',
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '合作账期',
			anchor : "85%",
			maxLength : 50,
			name : 'model.accounttime',
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '年度等级评估',
			hiddenName : 'model.gradeassess',
			anchor : "86%",
			xtype : "uxcombo",
			valueField : "value",
			displayField : "name",
			emptyText : '请选择',
			store : Q.Supplier.selStores.gradStore
		}
	}, {
		items : {
			fieldLabel : '次年预计目标',
			anchor : "85%",
			maxLength : 50,
			name : 'model.target',
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '工费',
			anchor : "85%",
			name : 'model.fee',
			maxLength : 20,
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '金损',
			anchor : "85%",
			maxLength : 20,
			name : 'model.goidloss',
			xtype : "textfield"
		}
	}, {
		items : {
			fieldLabel : '产品出错率',
			anchor : "85%",
			maxLength : 20,
			name : 'model.errorrate',
			xtype : "textfield"
		}
	}, {
		columnWidth : 1,
		items : {
			fieldLabel : '开票全称',
			maxLength : 90,
			name : 'model.invoicename',
			xtype : "textfield",
			anchor : "94%"
		}
	}, {
		columnWidth : 1,
		items : {
			fieldLabel : '开户行',
			name : 'model.bank',
			anchor : "94%",
			maxLength : 100,
			xtype : "textfield"
		}
	}, {
		columnWidth : 1,
		items : {
			fieldLabel : '公司地址',
			name : 'model.address',
			xtype : "textfield",
			maxLength : 200,
			anchor : "94%"
		}
	}, {
		height : "30",
		columnWidth : 1,
		items : {
			fieldLabel : '备注',
			name : 'model.remark',
			xtype : "textarea",
			maxLength : 200,
			anchor : "94%"
		}
	} ]
} ];

var gridDtl1 = {
	tabTitle : "联系人信息",
	xtype : 'uxeditorgrid',
	foreignKey : "supplier_supplierid",
	tabClassName : "contact",
	viewConfig : {
		autoScroll : true
	},
	sm : {
		singleSelect : false
	},
	cm : {
		defaultSortable : false,
		defaults : {
			menuDisabled : true
		},
		columns : [
				{
					dataIndex : "contactId",
					hidden : true
				},
				{
					dataIndex : "_Supplierid",
					hidden : true
				},
				{
					header : "姓名",
					dataIndex : "name",
					width : 70,
					editor : {
						maxLength : 20,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "性别",
					dataIndex : "sex",
					width : 50,
					editor : {
						editable : false,
						xtype : "uxcombo",
						valueField : "value",
						displayField : "name",
						emptyText : '请选择',
						store : Q.Supplier.selStores.sexFra
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return Q.common.getSelText(Q.Supplier.selStores.sexFra,
								v);

					}
				},
				{
					header : "职务",
					dataIndex : "duty",
					width : 100,
					editor : {
						maxLength : 20,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "联系人地址",
					dataIndex : "addr",
					width : 250,
					editor : {
						maxLength : 200,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "手机号码",
					dataIndex : "mobile",
					width : 90,
					editor : {
						regex : /^(1\d{10})$/,
						regexText : "请输入正确的电话号码",
						xtype : 'numberfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "固定号码",
					dataIndex : "phone",
					width : 100,
					editor : {
						regex : /^((0\d{2,3}-\d{7,8})|(1\d{10}))$/,
						regexText : "请输入正确的电话号码",
						xtype : 'textfield'
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "生日",
					dataIndex : "birthday",
					width : 90,
					editor : {
						format : 'Y-m-d',
						xtype : 'datefield',
						editable : false
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return Q.common.dateRenderer(v);
					}
				},
				{
					header : "QQ",
					dataIndex : "qq",
					width : 90,
					editor : {
						maxLength : 20,
						xtype : 'numberfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "微信",
					dataIndex : "weChat",
					width : 100,
					editor : {
						maxLength : 40,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "邮箱",
					dataIndex : "mail",
					width : 120,
					editor : {
						regex : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
						regexText : "请输入正确的邮箱",
						maxLength : 40,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "关联联系人",
					dataIndex : "rleman",
					width : 100,
					editor : {
						maxLength : 50,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "爱好/禁忌",
					dataIndex : "hobby",
					width : 150,
					editor : {
						maxLength : 100,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				},
				{
					header : "是否发送短信",
					dataIndex : "isSms",
					width : 90,
					editor : {
						editable : false,
						xtype : "uxcombo",
						store : Q.common.yesOrNotStore
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return Q.common.getSelText(Q.common.yesOrNotStore, v,
								'text', 'value');
					}
				},
				{
					header : "备注",
					dataIndex : "remark",
					width : 200,
					editor : {
						maxLength : 200,
						xtype : 'textfield',
						editable : true
					},
					renderer : function(v, m, r) {
						vp.setBgColor(m, r, this.dataIndex, this.rendererColor);
						return v;
					}
				} ]
	},
	listeners : {
		"beforeedit" : function(e) {
			return vp.editWin.beforeedit();
		}
	},
	pageSize : 0,
	store : {
		idProperty : "contactId",
		url : dealUrl + "_getRel.action",
		sort : "contactId",
		autoLoad : false,
		dir : "desc"
	},
	tbar : [ {
		text : "添加",
		iconCls : "icon-add",
		handler : function() {
			var grid = this.findParentByType(Ext.grid.GridPanel);
			vp.editWin.addDetail(grid);
		}
	}, {
		text : "删除",
		iconCls : "icon-delete",
		handler : function() {
			var grid = this.findParentByType(Ext.grid.GridPanel);
			vp.editWin.deleteDetail(grid);
		}
	} ]
};

var gridDtl2 = {
	tabTitle : "合同信息",
	xtype : 'uxeditorgrid',
	tabClassName : "contract",
	cm : {
		defaults : {
			menuDisabled : true
		},
		columns : [ {
			dataIndex : "contractId",
			hidden : true
		}, {
			header : "合同编号",
			dataIndex : "contractNum",
			width : 80
		}, {
			header : "合同名称",
			dataIndex : "contractName",
			width : 110
		}, {
			header : "签约日期",
			renderer : Q.common.dateRenderer,
			dataIndex : "signDate",
			width : 80
		}, {
			header : "生效日期",
			renderer : Q.common.dateRenderer,
			dataIndex : "effDate",
			width : 80
		}, {
			header : "失效日期",
			renderer : Q.common.dateRenderer,
			dataIndex : "invDate",
			width : 80
		}, {
			header : "签约地址",
			dataIndex : "addr",
			width : 200
		} ]
	},
	pageSize : 20,
	store : {
		idProperty : "contractId",
		url : path + "/Contract_getJson.action",
		sort : "contractId",
		autoLoad : false,
		dir : "desc"
	},
	vpAfterRender : function() {
		return 'view';
	}
};

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

var cfg = {
	isAudit : false,
	dealUrl : dealUrl,
	moduleName : '供应商管理',
	playListMode : playListMode.normal,
	vp : {
		hideBtn : [ "delete" ],
		hideSubTab : true,
		column : gridColumn,
		subTab : [],
		activeTab : 0,
		store : {
			idProperty : 'supplierid',
			url : dealUrl + '_getJson.action',
			sort : "supplierid",
			dir : "desc"
		},
		listEditStateFn : [],
		viewAfter : function(grid, selectids, win) {
			vp.editWin.showTabItem("contract");
		},
		editAfter : function(grid, selectids, win) {
			vp.editWin.hideTabItem("contract");
			vp.editWin.setActiveTab([ "contract" ]);
		}
	},
	editWin : {
		createEditWin : eval("Q.comm.CommModelEditWin"),
		nextBillState : 'New',
		maximized : true,
		form : {
			items : editFormItems,
			columnWidth : 0.5,
			height : 300,
			setFormValueAfter : function(formPanel) {
				var _store = vp.editWin.getCompByTabClassName("contract").getStore();
				_store.baseParams = {
					"filter_EQ_partyNum" : formPanel.getForm().findField("model.supplierno").getValue()
				};
				_store.load();
			}
		},
		centerTab : {
			items : [ gridDtl1, gridDtl2 ]
		}
	},
	searchWin : {
		createSearchWin : eval("Q.comm.CommSearchWin"),
		height : 180,
		width : 330,
		form : {
			labelWidth : 80,
			columnWidth : 1,
			items : [ {
				fieldLabel : '供应商编码',
				name : "filter_LIKE_supplierno",
				xtype : "textfield"

			}, {
				fieldLabel : '供应商名称',
				name : "filter_LIKE_suppliername",
				xtype : "textfield"
			}, {
				fieldLabel : '供应商简称',
				name : "filter_LIKE_name",
				xtype : "textfield"
			} ]
		}
	},
	vpInstanceAfert : function() {
		vp.editWin.on("show", function() {
			var tbar = vp.editWin.formPanel.getTopToolbar().find("name","submit")[0];
			tbar.hide();
		});
	}
};