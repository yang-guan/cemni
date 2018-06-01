/**
 * 加载外部JS
 * 
 * @type
 */
var jsArr = [path + "/js/content/notify/Q.notify.ModuleSelectWin.js"];
Q.loadJs(jsArr);

/**
 * 主要url
 * 
 * @type
 */
var dealUrl = path + '/notify/Notify';

/**
 * 公共方法和对象
 * 
 * @type
 */
Q.notify = {
	renderFlag:function(v) {
		return v == 1 ? '是' : '否'
	}
};

/**
 * 公共store
 * 
 * @type
 */
Q.notify.store = {
	/**
	 * 列表store
	 * 
	 * @type
	 */
	gridStore:{
		idProperty:'notifyCode',
		url:dealUrl + '_getJson.action'
	}
};

// 列表
var gridColumn = [{
		header:"通知编码",
		width:100,
		dataIndex:"notifyCode"
	}, {
		header:"通知名称",
		width:150,
		dataIndex:"notifyName"
	}, {
		header:"标题模版",
		width:150,
		dataIndex:"title"
	}, {
		header:"内容模版",
		width:200,
		dataIndex:"template",
		tpl:'asdfasfdasdf'
	}, {
		header:"站内消息",
		width:80,
		dataIndex:"messageFlag",
		renderer:Q.notify.renderFlag
	}, {
		header:"邮件",
		width:80,
		dataIndex:"emailFlag",
		renderer:Q.notify.renderFlag
	}, {
		header:"微信",
		width:80,
		dataIndex:"weixinFlag",
		renderer:Q.notify.renderFlag
	}, {
		header:"短信",
		width:80,
		dataIndex:"smsFlag",
		renderer:Q.notify.renderFlag
	}, {
		header:"APP",
		width:80,
		dataIndex:"appFlag",
		renderer:Q.notify.renderFlag
	}, {
		xtype:'actioncolumn',
		header:'测试',
		width:35,
		items:[{
				icon:path + '/images/icon-area.gif',
				tooltip:'测试',
				handler:function(grid, rowIndex, colIndex) {
					var win = new Ext.ux.Window({
						title:'测试窗口',
						layout:'fit',
						width:400,
						height:200,
						items:[{
								name:'testform',
								xtype:'form',
								layout:'form',
								labelWidth:80,
								padding:'10px 0 0 10px',
								items:[{
										name:'receivers',
										xtype:'textarea',
										anchor:'95%',
										height:100,
										fieldLabel:'接收用户编码',
										emptyText:'多个用逗号隔开'
									}]
							}],
						buttons:[{
								text:'发送',
								handler:function() {
									var receivers = this.ownerCt.ownerCt.find('name', 'testform')[0].getForm().getValues().receivers;
									var notifyCode = vp.grid.getStore().getAt(rowIndex).get('notifyCode');
									Ext.Ajax.request({
										url:path + '/notify/Notify_test.action',
										params:{
											id:notifyCode,
											receivers:receivers
										},
										success:function(data) {
											Q.tips(Q.color('消息已发送！'));
										},
										failure:function(data) {
											Q.error('消息发送失败！');
										}
									});
								}
							}, {
								text:'返回',
								handler:function() {
									this.ownerCt.ownerCt.destroy();
								}
							}]
					});
					win.show();
				}
			}]
	}];

// 表单
var editFormItems = [{
		xtype:'textfield',
		fieldLabel:'通知编码' + Q.color('*'),
		allowBlank:false,
		name:'model.notifyCode'
	}, {
		xtype:'textfield',
		fieldLabel:'通知名称' + Q.color('*'),
		allowBlank:false,
		name:'model.notifyName'
	}, {
		xtype:'textfield',
		fieldLabel:'标题模版' + Q.color('*'),
		allowBlank:false,
		name:'model.title'
	}, {
		fieldLabel:'内容模版' + Q.color('*'),
		allowBlank:false,
		xtype:'textarea',
		name:'model.template'
	}, {
		xtype:'textfield',
		fieldLabel:'微信链接',
		name:'model.weixinUrl'
	}, {
		xtype:'uxtrigger',
		fieldLabel:'对应菜单',
		editable:false,
		clearable:true,
		name:'model.moduleName',
		listeners:{
			trigger:function() {
				var win = new Q.notify.ModuleSelectWin({
					select:function(moduleId, moduleName) {
						var form = vp.editWin.formPanel.getForm();
						form.findField('model.moduleId').setValue(moduleId);
						form.findField('model.moduleName').setValue(moduleName);
						this.hide();
					}
				});
				win.show();
			}
		}
	}, {
		xtype:'uxtrigger',
		hidden:true,
		name:'model.moduleId'
	}, {
		fieldLabel:'通知方式',
		xtype:'checkboxgroup',
		items:[{
				boxLabel:'站内消息',
				name:'model.messageFlag',
				inputValue:1
			}, {
				boxLabel:'邮件',
				name:'model.emailFlag',
				inputValue:1
			}, {
				boxLabel:'微信',
				name:'model.weixinFlag',
				inputValue:1
			}, {
				boxLabel:'短信',
				name:'model.smsFlag',
				inputValue:1
			}, {
				boxLabel:'APP',
				name:'model.appFlag',
				inputValue:1
			}]
	}];

// 查询表单
var searchFormItems = [{
		xtype:'textfield',
		fieldLabel:'通知编码',
		name:"filter_EQ_notifyCode"
	}, {
		xtype:'textfield',
		fieldLabel:'通知名称',
		name:"filter_LIKE_notifyName"
	}, {
		xtype:'textfield',
		fieldLabel:'标题模版',
		name:"filter_LIKE_title"
	}, {
		xtype:'textfield',
		fieldLabel:'内容模版',
		name:"filter_LIKE_template"
	}];

// ---------------------------editWin-------------------------

var cfg = {
	isAudit:false,// 是否需要审核右键
	dealUrl:dealUrl,// 各种操作的url地址
	moduleName:'消息通知配置',// 模块名称
	playListMode:'normal', // normal/audit/undeal //三种列表模式
	vp:{
		column:gridColumn,// 初始化列表
		viewConfig:{
			forceFit:false
		},
		hideSubTab:true,
		subTab:[],
		store:Q.notify.store.gridStore, // 初始化列表store
		// 按钮是否可用及编辑查看窗口字段是否可编辑权限控制
		listEditStateFn:[{
				'edit':true
			}, {
				'view':true
			}, {
				'delete':true
			}]
	},
	editWin:{
		createEditWin:eval("Q.comm.CommModelEditWin"), // 编辑窗口类名称
		nextBillState:'New',// 提交后下一步的状态
		width:550,
		height:320,
		form:{
			labelWidth:60,
			items:editFormItems,// 编辑表单字段
			columnWidth:1, // 配置表单有几列
			height:335,
			setFormValueAfter:function(formPanel) {
				formPanel.getForm().findField('model.notifyCode').setReadOnly(true);
			}
		},
		// 添加tab时设置表单字段只读
		addTabSetFormReadOnly:[]
	},
	searchWin:{
		createSearchWin:eval("Q.comm.CommSearchWin"), // 查询窗口类名称
		title:'查询',
		width:600,
		height:150,
		form:{
			labelWidth:60,
			items:searchFormItems,// 查询表单字段
			columnWidth:'0.5' // 配置表单有几列
		}
	}
};
