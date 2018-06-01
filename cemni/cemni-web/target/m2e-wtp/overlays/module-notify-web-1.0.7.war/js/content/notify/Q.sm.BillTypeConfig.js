Ext.ns("Q.sm");

	//页面store 公共类
	Q.sm.billset ={
		renderFlag : function(v) {
			if(Ext.isEmpty(v)){
				return '';
			}
			return v == '1' ? $("dict.yes") : $("dict.no");
		}
	};
	/**
	 * 页面store 公共类
	 * @type 
	 */
	Q.sm.billset.store ={
		statusStore:new Ext.data.JsonStore({
	    	fields:[{name:"useStatue",type:"string"},{name:"useStatueValue",type:"int"}],
	    	data:[{useStatue:$("dict.no"),useStatueValue:'0'},{useStatue:$("dict.yes"),useStatueValue:'1'}]
	    }),
	    sysModuleStore:new Ext.data.JsonStore({
			url: path_core+"/sys/SysModule_getJson4Combobox.action",
			baseParams:{
		        "filter_EQ_stopFlag":0
		    },
		    autoLoad:true,
		    fields:["sysModuleCode","sysModuleName"]
		}),
		artifactStore :new Ext.data.JsonStore({
		    url: path_core+"/sys/Module_getArifactList.action",
		    autoLoad:false,
		    fields:["artifactCode", "artifactName", "sysModuleCode"]
		})
	};
	var dealUrl=path+"/sm/billset/BillType";
	var moduleName =$("billtype");
	var triggerField = 'billTypeCode';

	//列表
	var gridColumn = [
		{Qheader:"单据类型编码",header:$("billtype.billTypeCode"),dataIndex:"billTypeCode"},
		{Qheader:"单据类型名称",header:$("billtype.billTypeName"),dataIndex:"billTypeName"},
		{Qheader:"系统模块",header:$("sysModule.sysModuleCode"),dataIndex:"sysModuleCode"},
		{Qheader:"状态",header:$("label.usingState"),dataIndex:"status",renderer:function(v){
				return Q.sm.billset.renderFlag(v);
			}
		},
	    {Qheader:"组件编码",header:$("billtype.artifactCode"),dataIndex:"artifactCode"},
		{Qheader:"编码规则",header:$("billtype.codeRuleFlag"),dataIndex:"codeRuleFlag",renderer:function(v){
				return Q.sm.billset.renderFlag(v);
			}
		},
		{Qheader:"流程标识",header:$("billtype.flowFlag"),dataIndex:"flowFlag",renderer:function(v){
				return Q.sm.billset.renderFlag(v);
			}
		}
	];
	var gridStore = {
	     idProperty:"billTypeCode",
	     url:dealUrl+"_getJson.action",
	     sort:"billTypeCode",
	     dir:"asc",
		 baseParams: {
			 initStates:initStatesStr
		 }
	 };
 
	 	//按钮
	var addBtn = [{  
		 xtype:'textfield',
		 name:"filter_LIKE_billTypeCode_OR_billTypeName",
	     index:11,
	     emptyText:$("label.code")+"/"+$("label.name")
	 },{ 
         name:"newSearch",
		 Qtext:"查询",
		 text:$('button.search'),  
	     index:12,
	     iconCls:"icon-search",
	     handler: function(_self){
	    	 var grid = this.findParentByType(Ext.grid.GridPanel); //查找出所属的父Grid
	    	 var store = grid.getStore();
	    	 var tbar = grid.getTopToolbar();  
	    	 var searchValue = tbar.find("name","filter_LIKE_billTypeCode_OR_billTypeName")[0];	    	  
	    	 store.baseParams.filter_LIKE_billTypeCode_OR_billTypeName = searchValue.getValue();
	    	 if(!Ext.isEmpty(store.lastOptions)){
             	 store.load({params:{start:0, limit:store.lastOptions.params.limit}});
             }else{
             	 store.load({params:{start:0, limit:20}});
             }
	     }
	 }];
	 
	var editFormItems = [
		{
			QfieldLabel:"单据类型编码",
			fieldLabel:$("billtype.billTypeCode")+"<font color='red'>*</font>",
			allowBlank:false,
			regex:/^[a-zA-Z]+$/,
			name:"model.billTypeCode"
		},		
		{
			QfieldLabel:"单据类型名称",
			fieldLabel:$("billtype.billTypeName")+"<font color='red'>*</font>",
			allowBlank:false,
			name:"model.billTypeName"
		},		
		{
			QfieldLabel:"系统模块编码",
			fieldLabel:$("sysModule.sysModuleCode")+"<font color='red'>*</font>",
			allowBlank:false,
			xtype:'uxcombo',
	        hiddenName:"model.sysModuleCode",
	        valueField:'sysModuleCode',
	        displayField:'sysModuleName',
	        store:Q.sm.billset.store.sysModuleStore,
            listeners:{
				"select":function(c){
					vp.editWin.formPanel.form.findField("model.artifactCode").reset();
					Q.sm.billset.store.artifactStore.baseParams={"EQ_sysModuleCode":c.getValue()};
					Q.sm.billset.store.artifactStore.load();
				}
			}
	    },
		{
			QfieldLabel:"组件编码",
			fieldLabel:$("billtype.artifactCode")+"<font color='red'>*</font>",
			allowBlank:false,
			xtype:'uxcombo',
			name:"model.artifactCode",
	        hiddenName:"model.artifactCode",
	        valueField:'artifactCode',
	        displayField:'artifactName',
	        store:Q.sm.billset.store.artifactStore
	    },
	    {
			QfieldLabel:"使用状态",
	    	xtype:'uxcombo',
	        fieldLabel:$("label.usingState")+"<font color='red'>*</font>",
	        hiddenName:"model.status",
	        allowBlank:false,
	        valueField:'useStatueValue',
	        displayField:'useStatue',
	        value:'1',
	        store:Q.sm.billset.store.statusStore
	    },
	    {
			QfieldLabel:"编码规则标识",
	    	xtype:'uxcombo',
	        fieldLabel:$("billtype.codeRuleFlag")+"<font color='red'>*</font>",
	        hiddenName:"model.codeRuleFlag",
	        allowBlank:false,
	        valueField:'useStatueValue',
	        displayField:'useStatue',
	        value:'1',
	        store:Q.sm.billset.store.statusStore
	    },
	    {
			QfieldLabel:"流程标识",
	    	xtype:'uxcombo',
	        fieldLabel:$("billtype.flowFlag")+"<font color='red'>*</font>",
	        hiddenName:"model.flowFlag",
	        allowBlank:false,
	        valueField:'useStatueValue',
	        displayField:'useStatue',
	        value:'1',
	        store:Q.sm.billset.store.statusStore
	    }
];    

	//查询表单
	var searchFormItems = [		
		{
			QfieldLabel:"单据类型编码",
			fieldLabel:$("billtype.billTypeCode"),
			name:"filter_LIKE_billTypeCode"
		},		
		{
			QfieldLabel:"单据类型名称",
			fieldLabel:$("billtype.billTypeName"),
			name:"filter_LIKE_billTypeName"
		}
];
	
	 cfg = {
    		isAudit:false,//是否需要审核右键
            dealUrl:dealUrl,//各种操作的url地址
            moduleName:moduleName,//模块名称
            playListMode:playListMode.normal, // normal/audit/undeal/panel //三种列表模式
            //重写审核通过和审核不过按钮
           /* menuOverride:[{text:"测试", name:'TOPASS',iconCls:"icon-toPass",hidden:true,handler:function(){vp.dealstate(this.name,this.text)}},
         	      {text:"测试1", name:'TONOPASS',iconCls:"icon-toNoPass",hidden:true,handler:function(){vp.dealstate(this.name,this.text)}}],*/
           //  displayTbar:true,
           // accessControl:accessControl, //可以实现不同角色是表单和列表的字段，是否显示/是否可编辑/是否必填等的初始化配置；
    		vp:{
    			sm: {singleSelect:false},//列表是否单选
       			addOtherBtn:addBtn, //在固化的按钮基础上追加按钮
       			//hideBtn:["delete"),//隐藏固化的按钮
       			column:gridColumn,//初始化列表
       			store:gridStore, //初始化 列表store
       			//disabledColumn:disabledColumn, //隐藏的列表项
       			//addOtherColumn:addOtherColumn,//追加列表项
       			hideSubTab:true,//隐藏的tab列表项
       			subTab:[],// subTab第一层数据为细单，数组中的第二层数组为有带细单的细单，(且第一个为细单,后面几个为细单的细单)
    			activeTab:0,//默认展示哪儿tab
    			//tabHeight:350,//tab 的高度设置
    			//triggerField:triggerField,//点击列表的哪一列显示对应的tab列表
    			logModuleCode:"BiddingJAction",
    			billTypeCode:"TZB",//模块类型编码
    			baseParams:{
       				//initStates:initStatesStr
       			},//列表数据初始化状态过滤
       			listEditStateFn:[{
						"edit":true
           			},{
						"view":true
           			},{
						"delete":true
           			},{
           				"search":false
           			}
       			],//在添加固有的方法上再追加操作，参数：当前grid列表，win 当前编辑窗口
       			addAfter:function(grid,win){
       				
       			},//在修改固有的方法上再追加操作，参数：grid当前列表，selectids 当前选记录，win 当前编辑窗口
       			editAfter:function(grid,selectids,win){
       				win.formPanel.form.findField("model.billTypeCode").setReadOnly(true);
       			},//在查看固有的方法上再追加操作，参数：grid当前列表，selectids 当前选记录，win 当前编辑窗口
       			viewAfter:function(grid,selectids,win){
       			}
        	},//按钮是否可用及编辑查看窗口字段是否可编辑权限控制
        	editWin:{
        		//addOtherBtn:addbtn1, //在固化的按钮基础上追加按钮
        		nextBillState:"New",//提交后下一步的状态
        		maximized:false, //是否最大化窗口，默认为否
        		height:300,
            	width:420,
        		form:{ 
            		items:editFormItems,//编辑表单字段
            		columnWidth:1 //配置表单有几列
            		//height:250
        		},
        		centerTab:{
        			items:[]
        		},/*
        		westTab:{
        			width:400,
        			//collapsible:true,
        			items:[formDtl1]
        		},
        		eastTab:{
        			width:400,
        			height:300,
        			items:[grid2]
        		},
        		southTab:{
        			height:200,
        			//collapsible:true,
        			items:[grid3]
        		},*/
        		//formPanel 表单
        		submitBefore:function(formPanel){
        			//return false
        		},//params组装的参数值,formPanel 表单
        		submitAfter:function(params,formPanel){
        			
        		},//params提交的数据,表单
        		submitSuccessAfter:function(form,action){
        			//alert("submitSuccessAfter")
        		}
            },
        	searchWin:{
        		checkboxgroup:checkboxgroup,//查询单据状态参数
        		checkboxgroup1:checkboxgroup1,
        		isShowStatus:false,//是否显示查询状态
        		height:180,
            	width:420,
        		form:{ 
            		items:searchFormItems,//查询表单字段
            		columnWidth:1 //配置表单有几列
        		},
       			searchLoadBefore:function(){
       				return true;
       			},
       			searchReportSubmitBefore:function(cfg1,data){
           				//Q.tips(Q.color("123123123","red"))
           		}
            },
            vpInstanceAfert:function(){
            	//隐藏提交按钮
                vp.editWin.on("show",function(){
                	var btn = vp.editWin.buttons[1];
                	btn.hide();
            	});            	
           	    //var searchBtn = vp.grid.getTopToolbar().find("name","search")[0];
    		   // searchBtn.handler();
            },// editWin 编辑窗口 gridVp 列表grid
   			// 使用说明 返回值 为 站内链接 使用 用于创建查看 编辑窗, 返回字符串"view" 为创建查看窗口,"edit”为创建编辑窗口
   			vpAfterRender:function(){ 
   				return 'view';
   			}
   	 };