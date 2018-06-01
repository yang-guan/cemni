//Ext.ns("Q.sm");
Ext.Loader.loadScripts({
	url:[path + "/js/content/notify/Q.sm.BillSelectWin.js"]
});

Ext.define("sm.NotifySetEditWin",{
	extend:'Ext.ux.Window',
	requries:['Ext.ux.form.FormPanel'
	   	   ,'Ext.ux.form.ComboBox'
	   	   ,'Ext.ux.form.ComboBoxTree'
	   	   ,'Ext.ux.form.TriggerField'
	   	   ,'Ext.ux.form.CheckboxGroup'
	   	   ,'Ext.ux.TipsWindow'
	   	   ,'Ext.ux.Window'
	   	   ,'Ext.ux.util'
	   	   ,'Ext.ux.Msg'
	   	   ,'Ext.ux.Q'
	   	   ,'Ext.ux.grid.GridPanel' 
	   	   ,'Ext.ux.grid.EditorGridPanel'
	   	   ,'Ext.ux.data.JsonStore'],
	alias:"notifySetEditWin0",
	constructor : function(config){
		var win =this;
		config=config||{};
		Ext.applyIf(config,{
			dealUrl:path+"/notify/NotifySet",
//			title:$("nofitySet")
			title:"消息通知"
		});
		var editFlag = config.editflag;
		var formPanel = this.formPanel = this.createFormPanel(editFlag);
//		var gridPanel = this.gridPanel = this.createGridPanel();
//		var templateWin = this.templateWin = this.createTemplateWin();
		var templateJson = this.templateJson = {};
		config = Ext.apply({
			width: 720,
			height:500,
			layout:"border",
			items:[
				formPanel
				/*,
				{
				region:"center",
				layout:"border",
				border:false,
				items:[{
					xtype:"tabpanel",
					region:"center",
					border:false,
					activeTab:0,
					items:[gridPanel]
				}]
				}*/
			],
			tbar: [{
				text: $("button.save"),
				iconCls:"icon-save",
				name:"notifySetEditWinSaveBtn",
				hidden:!win.editFlag,
				handler: function(){
					win.submitWin();
				}
			}, {
				text: $("button.return"),
				iconCls:"icon-return",
//				hidden:!editFlag,
				handler: function(text){
					win.hide();
				}
			}]
		},config);
		
		this.callParent([config]);//构造父类

	},
		//win 的事件监听
		listeners:{
			"hide": function(){
				this.resetWin();
			}
		},
		resetWin:function(){
			
			this.formPanel.getForm().reset();
//			this.gridPanel.getStore().baseParams={};
//			this.gridPanel.getStore().removeAll();
			//this.gridPanel.hide();
			this.templateJson={};
			this.url="";
			if(!this.hidden){
				this.hide()
			}
		},
		isValidWin:function(){
			var isValid = this.formPanel.form.isValid();
			if(!isValid){
				return false;
			}
			return true;
		},
		setEditState: function(editFlag){//设置页面只读
			var win = this;
			win.editFlag=editFlag;
			if(editFlag){
				win.query("button[name=notifySetEditWinSaveBtn]")[0].show();
				
			}else{
				win.query("button[name=notifySetEditWinSaveBtn]")[0].hide();
				win.formPanel.query("button")[0].hide();
				win.formPanel.query("button")[1].hide();
				win.formPanel.query("button")[2].hide();
				win.formPanel.query("button")[3].hide();
			}
			var fld = win.formPanel.findByType(Ext.form.Field);
			Ext.each(fld,function(item){
				if(item.name == "model.billTypeCode" || item.name=="model.billTypeName"){
					return;
				}
				if(editFlag /*&& !win.readOnlyFiledArr.contains(item)*/){
					if(item.getXType() == "checkbox"){
						item.setDisabled(false);	
					}
					item.setReadOnly(false);
				}else{
					if(item.getXType() == "checkbox"){
						item.setDisabled(true);
					}
					item.setReadOnly(true);
				}
			});
		},
		createFormPanel:function(editFlag){
			var win = this;
			var formPanel = new Ext.form.FormPanel({
				border:true,
				region:"center",
				labelWidth:120,
				autoScroll:false,
				layout:"column",
				bodyStyle:"padding:5px;",
				height:300,
				defaults:{columnWidth:1,layout:"form",border:false},
				items:[{
//					defaults:{xtype:"uxtrigger",anchor: "-20"},
					columnWidth:.5,
					items:[{
						xtype:"uxtrigger",//弹出框
						fieldLabel:"单据类型编码"+"<font color='red'>*</font>",//单据类型编码$("notifySet.billTypeCode")
						name:"model.billTypeCode",
						readOnly:!editFlag,
						triggerAction:"all", 
						disable:true,
						allowBlank:false,
						editable:false,
						getSelectWin:function(){
							var selectWin = new Q.sm.BillSelectWin({
								singleSelect : true,
								baseParams : {
									"filter_EQ_status": 1,
									"filter_EQ_roles_roleType": "B"
								},
								heigh:360,
								width:240,
								select:function(g,r){
									var form = win.formPanel.getForm();
									form.findField("model.billTypeCode").setValue(r.get("billTypeCode"));
									form.findField("model.billTypeName").setValue(r.get("billTypeName"));
								}
							});
							return selectWin;
						},
						listeners:{
								trigger:function(field){
									if(field.disabled==false){
			   	                     this.getSelectWin().show();
			   	                 }}
							}
					},{
						xtype:"textfield",
						fieldLabel:"通知设置编码"+"<font color='red'>*</font>",//通知设置编码$("notifySet.notifySetCode")
						name:"model.notifySetCode",
						allowBlank:false
					}]
				},{
					defaults:{xtype:"textfield",anchor: "-20"},
					columnWidth:.5,
					items:[{
						fieldLabel:"单据类型名称"+"<font color='red'>*</font>",//单据类型名称$("notifySet.billTypeName")
						name:"model.billTypeName",
						readOnly:true,
						allowBlank:false
					},{
						fieldLabel:"通知设置名称"+"<font color='red'>*</font>",//$("notifySet.notifySetName")
						name:"model.notifySetName",
						allowBlank:false
					}]
				},{
					defaults:{xtype:"checkbox",anchor: "-20"},
					columnWidth:.5,
					items:[{
						fieldLabel:"提醒方式",
						name: 'model.messageFlag',
//						boxLabel:$("notifySet.messageFlag"),
						boxLabel:"站内消息提醒",
						inputValue: 1,
						listeners:{
							"itemclick":function(checkbox,checked){
								if(checked){
									win.formPanel.findByType("button")[0].enable();
								}else{
									win.formPanel.findByType("button")[0].disable();
								}
							}
						}
					},{
//						boxLabel:$("notifySet.emailFlag"), 
						boxLabel:"邮件提醒",
						name:"model.emailFlag",
						inputValue:1,
						listeners:{
							"itemclick":function(checkbox,checked){
								if(checked){
									win.formPanel.findByType("button")[1].enable();
								}else{
									win.formPanel.findByType("button")[1].disable();
								}
							}
						}
					},{
//						boxLabel:$("notifySet.smsFlag"), 
						boxLabel:"手机短信提醒",
						name:"model.smsFlag",
						inputValue:1,
						listeners:{
							"check":function(checkbox,checked){
								if(checked){
									win.formPanel.findByType("button")[2].enable();
								}else{
									win.formPanel.findByType("button")[2].disable();
								}
							}
						}
					},{
//						boxLabel:$("notifySet.weixinFlag"), 
						boxLabel:"微信提醒",	
						name:"model.weixinFlag",
						inputValue:1,
						listeners:{
							"check":function(checkbox,checked){
								if(checked){
									win.formPanel.findByType("button")[3].enable();
								}else{
									win.formPanel.findByType("button")[3].disable();
								}
							}
						}
					}]
				},{
					defaults:{xtype:"button",anchor: "-20",submitValue:false,margin:'5.5,5,0,5'},
					columnWidth:.1,
					items:[{
						text:"配置",
						height:25,
						disabled:!editFlag,
						name:"messagebtn",
						iconCls:"icon-edit",
						handler: function(_self){
							//messageFlag
							win.createTemplateWin("message").show();
						}
					},{
						text:"配置",
						height:25,
						disabled:!editFlag,
						name:"emailbtn",
						iconCls:"icon-edit",
						handler: function(_self){
							//emailFlag
							win.createTemplateWin("email").show();
						}
					},{
						text:"配置",
						height:25,
						disabled:!editFlag,
						name:"smsbtn",
						iconCls:"icon-edit",
						handler: function(_self){
							//smsFlag
							win.createTemplateWin("sms").show();
						}
					},{
						text:"配置",
						height:25,
						disabled:!editFlag,
						name:"weixinbtn",
						iconCls:"icon-edit",
						handler: function(_self){
							//weixinFlag
							win.createTemplateWin("weixin").show();
						}
					}]
				},{
					defaults:{xtype:"textfield",anchor: "-20"},
					columnWidth:1,
					items:[]
				},{
					defaults:{xtype:"checkbox",anchor: "-20"},
					columnWidth:.3,
					items:[{
						fieldLabel:"提醒对象",
						name: 'model.createUserFlag',
						boxLabel:"单据创建者",
//						boxLabel:$("notifySet.createUserFlag"),
						inputValue: 1
					}]
				},{
					defaults:{xtype:"checkbox",anchor: "-20"},
					columnWidth:.2,
					labelWidth:10,
					items:[{
						name: 'model.bpmUserFlag',
						boxLabel:"流程审批人",
//						boxLabel:$("notifySet.bpmUserFlag"),
						inputValue: 1
					}]
				},{
					defaults:{xtype:"checkbox",anchor: "-20"},
					columnWidth:.2,
					labelWidth:10,
					items:[{ 
						name: 'model.vendorUserFlag',
						boxLabel:"供应商",
//						boxLabel:$("notifySet.vendorUserFlag"),
						inputValue: 1
					}]
				},{
					defaults:{xtype:"checkbox",anchor: "-20"},
					columnWidth:.2,
					labelWidth:10,
					items:[{
						name: 'model.fixedUserFlag',
						boxLabel:"流程告知者",
//						boxLabel:"固定用户",
//						boxLabel:$("notifySet.fixedUserFlag"),
						inputValue: 1,
						/*listeners:{
							"check":function(checkbox,checked){
								if(checked){
									win.gridPanel.show();
									//win.gridPanel.setVisible(true);
								}else{
									//win.formPanel.findByType("button")[3].disable();
									win.gridPanel.hide();
									win.gridPanel.getStore().removeAll();
								}
							}
						}*/
					}]
				},{
					defaults:{xtype:"hidden"},
					columnWidth:.5,
					items:[{
						fieldLabel:"notifySetId",
						name:"model.notifySetId"
					},{
						fieldLabel:"创建人Id",
						name:"model.createUserId"
					},{
						fieldLabel:"创建人",
						name:"model.createUserName"
					},{
						fieldLabel:"创建时间",
						name:"model.createTime"
					},{
						fieldLabel:"修改人Id",
						name:"model.modifyUserId"
					},{
						fieldLabel:"修改人",
						name:"model.modifyUserName"
					},{
						fieldLabel:"修改时间",
						name:"model.modifyTime"
					}]
				}]
			});
			return formPanel;
		},
		createTemplateWin:function(templateType){
			var win = this;
			var templateWin = new Ext.ux.Window({
				closeAction: 'close', //close 关闭  hide  隐藏 
				width: 480,
				height:400,
				layout:"border",
				title:"模版配置",
				
				items:[{
					xtype:"form",
//					id:"templateform",
					name:"templateform",
					border:true,
					region:"center",
					labelWidth:80,
					autoScroll:false,
					layout:"column",
					bodyStyle:"padding:5px;",
					defaults:{columnWidth:1,layout:"form",border:false},
					items:[{
						defaults:{xtype:'textfield',anchor: "-20"},
						items:[{
							fieldLabel:"模版ID",
							xtype:"hidden",
							readOnly:true,
							name:"notifySetTemplateId"
						},{
							fieldLabel:"类型",
							readOnly:true,
							name:'templateType'
						},{
							fieldLabel:"标题",
							readOnly:!win.editFlag,
							name:'title',
							hidden:(templateType=="sms" || templateType=="weixin")
						},{
							fieldLabel:"微信模板ID",
							readOnly:!win.editFlag,
							name:'winxintemplateid',
							hidden:!(templateType=="weixin")
						},{
							fieldLabel:"内容",
							name:"template",
							readOnly:!win.editFlag,
							xtype:"textarea",
							height:80
						},{
							fieldLabel:"备注",
							readOnly:!win.editFlag,
							name:"remark"
						}]
					}],
					tbar: [{
						text: $("button.save"),
						hidden:!win.editFlag,
						iconCls:"icon-save",
						handler: function(){
							//var form = this.form;
							var templateform = templateWin.query("form[name=templateform]")[0];
							var addjson = {
									"notifySetTemplateId":templateform.getForm().findField("notifySetTemplateId").getValue(),
									"templateType":templateform.getForm().findField("templateType").getValue(),
									"title":templateform.getForm().findField("title").getValue(),
									"template":templateform.getForm().findField("template").getValue(),
									"remark":templateform.getForm().findField("remark").getValue()
							};

							win.templateJson[templateform.getForm().findField("templateType").getValue()] = addjson;
							templateWin.close();
						}
					}, {
						text: $("button.return"),
						iconCls:"icon-return",
						handler: function(text){
							templateWin.close();
						}
					}]
				}],
				listeners:{
					"show":function(){
						var templateform = this.query("form[name=templateform]")[0];
						templateform.getForm().findField("templateType").setValue(templateType);
						var tjson = win.templateJson[templateType];
						if(tjson){
							templateform.getForm().findField("notifySetTemplateId").setValue(tjson.notifySetTemplateId);
							templateform.getForm().findField("templateType").setValue(tjson.templateType);
							//templateform.findField("notifySet.notifySetId").setValue(win.formPanel.form.findField("model.notifySetId").getValue());//主单Id
							templateform.getForm().findField("title").setValue(tjson.title);
							templateform.getForm().findField("template").setValue(tjson.template);
							templateform.getForm().findField("remark").setValue(tjson.remark);
						}
					}
				}
			});
			return templateWin;
		},
		submitWin:function(){
			var win =this;
			if (!win.isValidWin()){
				return;
			}
			var url = (this.url||this.dealUrl+"_save.action?");
			Q.confirm($("message.save.confirm"), {
				ok: function(){
					var params=win.assemblyParams();
					var datefield = win.formPanel.findByType(Ext.form.DateField);
					Q.each(datefield,function(o,i){
						if(o.lateNight===true){ //日期字段后面要加23:59:59的做法 lateNight:true;
							params[o.name]=(o.getValue().format('Y-m-d')+" 23:59:59");
						}
					});
					var timefield = win.formPanel.findByType(Ext.form.TimeField);
					Q.each(timefield,function(o,i){
						params[o.name]="1970-01-01 "+o.getValue();//对时间字段的维护
					});
					var textareas = win.formPanel.findByType(Ext.form.TextArea);
					Q.each(textareas,function(o,i){
						o.setValue(o.getValue().replace(/\n/g, "<br/>"));
					});

					win.formPanel.getForm().submit({
						waitTitle: $("message.submit.data"),//数据提交
						waitMsg: $("message.submit.wait"),//正在提交数据，请稍候...
						url: url,
						method: 'POST',
						params: Ext.encode(params) == '{}'? params:Q.parseParams(params),
								success:function(form, action){
									var result = action.result, msg = result.info;
									win.hide();
									win.fireEvent("submit");
									Q.tips("<font color='blue'>"+$("message.save.success")+"</font>");//保存成功
								},
								failure: function(form, action){
									var result = action.result,msg=result.info;
									if(action && action.result){
										Q.error(msg||$("message.submit.failure"));//数据提交失败！
									}else{
										Q.error($("message.submit.failure")+"<br/><br/>"+$("message.system.disconnect"));
									}
								}
					});
				}
			});
		},
		/**
		 * 参数拼装
		 * @return {}
		 */
		assemblyParams:function(){
			var win = this;
			var params={
					model:{
						notifySetUser:[],
						notifySetTemplate:[]
					}
			};
			//消息通知用户
			var userFields = ['userId','userCode','userName'];
//			this.gridPanel.getStore().each(function(record){
//				var r = {};
//				Q.each(userFields,function(p,i){
//					r[p] = (Ext.isDate(record.get(p))&&record.get(p).format('Y-m-d H:i:s'))||record.get(p)||"";
//				});
//				params.model.notifySetUser.push(r);
//			});
			//通知模版
			Q.each(win.templateJson,function(p,i){
				var tjson = {};
				if(p.templateType == "message"){
					tjson = win.templateJson.message;
				}
				if(p.templateType == "email"){
					tjson = win.templateJson.email;
				}
				if(p.templateType == "sms"){
					tjson = win.templateJson.sms;
				}
				if(p.templateType == "weixin"){
					tjson = win.templateJson.weixin;
				}
				if(p.templateType == "app"){
					tjson = win.templateJson.app;
				}
				params.model.notifySetTemplate.push(tjson);
			})
			return params;
		},
		/**
		 * 
		 * @param {} id
		 */
		loadTemplateData:function(id){
			var win = this;
			Ext.Ajax.request({
				url: this.dealUrl+"_findTemplateAll.action",
				params:{ "id" :id},
				success: function(response){
					var json = Ext.decode(response.responseText);
					win.templateJson = json;
				},
				failure: function(response){
					win.templateJson = {};
					Q.error("<font color=''>"+$("message.delete.failure")+"<br/><br/>"+$("message.system.disconnect")+"</font>");
				},
				callback: function(){
					Ext.getBody().unmask();
				}
			});
		},
		setFormValue:function(id){
			var win = this;
			this.url = this.dealUrl+"_update.action?";
			Ext.getBody().submitMask();
			win.formPanel.getForm().load({
				url : this.dealUrl+"_edit.action?",
				params : { "id" : id },
				waitTitle : $("message.load.data"),//数据加载
				waitMsg : $("message.load.wait"),//正在加载数据，请稍候...
				success : function(form, action){
					var data = action.result.data;
					//目前只做到两层结构 多层子结构的需自己另作处理
//					console.log(data);
					var createModel = function(data){
						var model = {};
						for(var name in data){
							if(typeof data[name] == "object"){
								for(var p in data[name]){
									model[name+"."+p]=data[name][p];
								}
							}else{
								model[name] = data[name];
							}
						}
						return model;
					};
					//重新组织data
					var model = createModel(createModel(data));
					Q.each(model,function(v,p){
						var pro ="model."+p.replace(/\_/g, ".");
						var field = form.findField(pro);
						if(!Ext.isEmpty(field)){
							//这里不能全部转换成日期对象，datefield 字段下拉框可以通过format:"Y-m-d"转成字符串，可hidden 字段就不行，导致提交时提交对象了actin不能自动转。
							if(/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(v)&&field.getXType()!="hidden"){//是否时间格式的字段
								if(field.getXType()=="timefield"){
									var sdate = v.substring(0,v.indexOf("."));//这时的格式为,"Y-m-d H:i:s.0"这种格式字符串转Date有问题,去除".0"
									var dt = Date.parseDate(sdate, "Y-m-d H:i:s");
									field.setValue(dt);//设给字段时间格式
								} else{
									field.setValue(v);//因为在列表页store中日期已被格式化,所以这里可直接设值
								}
							}else if(field.getXType()=="textarea"){
								field.setValue(v.replace(/<br\/>/g, "\n")); //如果是文本域时要把html格式换行转成js 格式以实现换行
							}else if(field.getXType()=="checkbox"){
								field.setValue(v==1);
							}else{
								field.setValue(v);
							}
						}
					});
					win.loadTemplateData(id);
				},
				failure : function(form, action){
					if(action && action.result){
						//数据加载失败！
						Q.error($("message.load.failure"));
					}else{
						//数据加载失败！请检查与服务器的连接是否正常，或稍候再试。
						Q.error($("message.load.failure")+"<br/><br>"+$("message.system.disconnect"));
					}
					Ext.getBody().unmask();
				}
			});
		}
//	});
});