//Ext.ns("Q.sm");
/**
 * 航司选择窗口 - for Q版本(ext-3.3+)
 * //引用本选择列表无需为权限添加资源
 * @param {object} cfg
 *      {boolean} singleSelect 是否单选，缺省为多选
 *        {object} baseParams 查询条件
 *      {function} select 单击[选择]按钮时触发的回调函数，该函数有两个参数：grid与选中的记录
 *               单选时参数：(grid, record)
 *               多选时参数：(grid, selections)
 */
Ext.define("sm.UserSelectWin",{
	requries:["Ext.ux.Window"],
	extend:'Ext.ux.Window',
	alias:"0",
	constructor : function(cfg){
		cfg = cfg ||{};
		var singleSelect = false !== cfg.singleSelect;
		var grid = this.createGrid(cfg,singleSelect);

		Q.sm.UserSelectWin.superclass.constructor.call(this, {
			title:$("UserList"),
			layout:"border",
			width:750,
			height:450,
			items:[grid],
			listeners:{
				"hide": function(){
					grid.getSelectionModel().clearSelections();
				}
			}
		});
		this.addEvents("select");

		if(Ext.isFunction(cfg.select)){
			this.on("select", cfg.select);
		}
	},
//	Ext.extend(Q.sm.UserSelectWin, Ext.ux.Window, {
	createGrid : function(cfg,singleSelect){
		var win = this;
		var grid = this.gridPanel= new Ext.ux.grid.GridPanel({
			border:true,
			style:"padding:2px 2px 2px 0",
			sm: {singleSelect: singleSelect},
			viewConfig:{forceFit:true,stripeRows: true},
			store:{
				url: path_core + "/sys/User_getJson.action",
				baseParams:cfg.baseParams||{}
			},
			cm:{
				defaultSortable:false,
				columns:[
					{Qheader:"ID",header:$("user.id"),dataIndex:"id",disabled:true}, 
					{Qheader:"用户ID",header:$("user.userId"),dataIndex:"userId",disabled:true}, 
					{Qheader:"用户编码",header:$("user.userCode"),dataIndex:"userCode"},
					{Qheader:"用户名称",header:$("user.account"),dataIndex:"userName"},
					{Qheader:"联系电话",header:$("user.tel"),dataIndex:"tel",disabled:true},
					{Qheader:"邮箱",header:$("user.email"),dataIndex:"email",disabled:true},
					{Qheader:"身份证号",header:$("user.idcard"),dataIndex:"idCard",disabled:true}
					]
			},
			tbar: [{
				text:$("button.select"),
				iconCls:"icon-save",
				handler: function(){
					var selectFlag = grid.doSelect(win, singleSelect);
					if(selectFlag){
						win.hide(); 
					}
				}
			},{
				text:$("button.return"),
				iconCls:"icon-return",
				handler: function(){
					win.hide();
				}
			},"->",{
				xtype:"label",
				text:$("user.account")+"："
			},{
				name:"userName",
				xtype:"textfield",
				width:100
			},"-",{
				xtype:"label",
				text:$("user.userCode")+"："
			},{
				name:"userCode",
				xtype:"textfield",
				width:100
			},{
				text:$("button.search"),
				iconCls:"icon-search",
				handler:function(_self){
					var store = grid.getStore();
					var userName = _self.ownerCt.find("name","userName")[0].getValue();
					var userCode = _self.ownerCt.find("name","userCode")[0].getValue();
					store.baseParams.filter_LIKE_userName=userName;
					store.baseParams.filter_LIKE_userCode=userCode;
					store.load({params:{start:0, limit:20}}); 
				}
			}],
			listeners:{
				"rowdblclick": function(g, i){
					if(g.doSelect(win, singleSelect)){
						win.hide(); 
					}
				}
			},
			//private 自定义
			//选择
			doSelect: function(win, singleSelect){
				var grid = this, sm = grid.getSelectionModel(), selections = sm.getSelections();
				if(selections.length < 1){
					Q.tips("<font color='red'>"+$("message.pleaseSelect")+"</font>");
					return false;
				}
				if(singleSelect){
					win.fireEvent("select", grid, selections[0]);
				}else{
					win.fireEvent("select", grid, selections);
				}

				Q.tips("<font color='blue'>"+$("message.selectSuccess")+"</font>");
				return true;
			}
		});
		return grid;
	}
//	});
});