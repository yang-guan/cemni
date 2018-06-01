Ext.ns("Q.scm");
/*******************************************************************************
 * grid:"grid”单据列表 logModuleCode:"srm.po" 日志类型 billTypeCode:"JXD" 单据类型
 * 
 ******************************************************************************/
Q.ViewLogItemOrmessageComm = function(conf) {
	var win = this;
	this.key = "";
	conf = conf || {};
	Ext.applyIf(conf, {
		logItemType:"data"
	});
	var grid = conf.grid;
	grid.on("click", function(g) {
		if (grid.getSelectionModel()) {
			if (grid.getSelectionModel().getSelected()) {
				// win.key =
				// grid.getSelectionModel().getSelected().get(conf.billPK);
				win.key = grid.getSelectionModel().getSelected().id;
			}

		}
	});
	this.messageGrid = function() {
		return new Ext.grid.GridPanel({
			id:"messageGridId",
			title:$("flowInfo.dealComment"),
			stripeRows:true,
			store:new Ext.data.JsonStore({
				url:path_core + "/module-bpm/ex/message/" + conf.billTypeCode + "/" + win.key + "/getJson",
				sortInfo:{
					field:'dealTime',
					direction:"desc"
				},
				dir:"desc",
				fields:[{
						name:'role.roleName',
						type:'string'
					}, {
						name:'userName',
						type:'string'
					}, {
						name:'dealName',
						type:'string'
					}, {
						name:'dealComment',
						type:'string'
					}, {
						name:'dealTime',
						type:'string'
					}]
			}),
			autoScroll:true,
			border:false,
			viewConfig:{
				forceFit:true
			},
			loadMask:true,
			columns:[new Ext.grid.RowNumberer(), {
					header:$("flowInfo.roleName"),
					dataIndex:"role.roleName"
				}, {
					header:$("flowInfo.userName"),
					dataIndex:"userName"
				}, {
					header:$("flowInfo.dealName"),
					dataIndex:"dealName"
				}, {
					header:$("flowInfo.dealComment"),
					dataIndex:"dealComment"
				}, {
					header:$("flowInfo.dealTime"),
					dataIndex:"dealTime"
				}],
			listeners:{
				"activate":function() {
					var store = this.getStore();
					store.proxy = new Ext.data.HttpProxy({
						url:path_core + "/module-bpm/ex/message/" + conf.billTypeCode + "/" + win.key + "/getJson"
					});
					if (!Ext.isEmpty(win.key)) {
						store.load();
					}
				}
			}
		});
	};
	this.setKey = function(key) {
		this.key = key;
	};
	this.logItemGrid = function() {
		return new Ext.ux.grid.GridPanel({
			sm:{
				singleSelect:true
			},
			title:$("logOperation.logOperation"),
			view:new Ext.ux.grid.RowBodyView({
				forceFit:true,
				enableRowBody:true,
				showPreview:true,
				getRowClass:function(record, rowIndex, p, store) {
					if (this.showPreview) {
						// p.body = '<div
						// style="padding:4px;color:blue;border:1px solid
						// #ccc;">'+record.get("systemOut").replace(/^<br\/>/,
						// "")+'</div>';
						return 'x-grid3-row-expanded';
					}
					return 'x-grid3-row-collapsed';
				}
			}),
			viewConfig:{
				forceFit:true,
				autoScroll:true
			},
			cm:{
				defaults:{
					width:100
				},
				columns:[{
						header:$("logOperation.operatorName"),
						dataIndex:"operatorName"
					}, {
						header:$("logOperation.opResult"),
						dataIndex:"message"
					}, {
						header:$("logOperation.createTime"),
						dataIndex:"createTime"
					}]
			},
			store:{
				sortInfo:{
					field:'createTime',
					direction:"desc"
				},
				idProperty:"logItemId",
				url:path_core + "/sys/LogOperation_getJson.action",
				autoLoad:false
			},
			pageSize:20,
			listeners:{
				"activate":function() {
					var store = this.getStore();
					store.baseParams = {
						"filter_EQ_key":win.key,
						"filter_EQ_module":conf.logModuleCode
					};
					if (!Ext.isEmpty(win.key)) {
						store.reload();
					}
				}
			}
		});
	};

	this.processPicPanel = function() {
		return new Ext.Panel({
			tabTitle:'流程图片',
			xtype:'panel',
			name:'processPicPanel',
			html:'',
			listeners:{
				activate:function() {
					var rec = vp.grid.getSelectionModel().getSelections()[0];
					var processKey = vp.billTypeCode;
					var businessKey = rec.data.purchaseOrderId;
					this.update('<iframe name="adPageFrame" src=' + path_core + '"/module-bpm/diagram-viewer/process.html?processKey=' + processKey + '&amp;businessKey='
						+ businessKey + '" width="100%" height="100%"></iframe>');
				}
			}
		});
	}

	this.logItem = this.logItemGrid();
	this.msgItem = this.messageGrid();
	this.processPic = this.processPicPanel();

};