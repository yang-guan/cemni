
 /**
 * @class Q.base.CommCreateWin 
 * @extends Ext.ux.Window
 *
 * Q.base.CommCreateWin 其它参数、属性、方法、事件等参考 Ext.ux.Window
 *
<pre><code>
	//查询功能实例化时需要的配置参数
   	var cfg = {
        	searchWin:{
        		createSearchWin:eval('Q.base.CommCreateWin'+version), // 查询窗口类名
        		checkboxgroup:checkboxgroup, //单据状态复选框数据
        		checkboxgroup1:checkboxgroup1,
        		isShowStatus:true, //是否显示单据状态
        		form:{ 
            		items:searchFormItems, //查询表单字段
            		columnWith:'0.5' //配置表单有几列 1为一列独占
        		}
            }
   	 };
   	 
   	 //结合列表页面的查询功能使用，通常情况可拷贝以下代码直接使用
     {
		Qtext:"查询",
		text:"查询",
        iconCls:"icon-search",
        handler:function(){
			var grid = this.findParentByType(Ext.grid.GridPanel); //查找出所属的父Grid
			var clearsearch= grid.getTopToolbar().find("name","clearsearch")[0];
			console.info(cfg.searchWin)
			var win = new cfg.searchWin.createSearchWin(cfg), store = grid.getStore();
            win.on("search", function(data){
	            if(!Ext.isEmpty(clearsearch)){
	            	clearsearch.show();
	            }
                Q.each(data,function(v,p){
                    if(p.indexOf("LIKE")>-1 && !Ext.isEmpty(v)){data[p]="%"+v+"%"};
                })                
                Ext.apply(store.baseParams, data);
                store.load({params:{start:0, limit:store.lastOptions.params.limit}});
            });
            win.show();
            this.handler=function(){
                win.show();
            }
        }
    } 
</code></pre>
*/
Ext.ns("Q.comm");
/**
 * <p>Description: 简单表单窗口创建</p>
 *
 * @author zhengjf 
 *
 * 时间 2015/7/31 
 */
Q.comm.CommCreateWin = function(config){
    //创建简单的表单窗口必填项提示
	/*if(Ext.isEmpty(config.url)){
		Q.error($("comm.errorMsg"));//请指定action处理方法!
	} */
	var items = [];
    if(Ext.isEmpty(config.createObject) ||  config.createObject.indexOf('form') != -1){ 
        var formPanel = this.createFormPanel(config);
        this.formPanel = formPanel;
        items = [formPanel];
    }

    if(!Ext.isEmpty(config.createObject) && config.createObject.indexOf('grid') != -1 ){ 
        if(!Ext.isEmpty(config.grid) && !Ext.isEmpty(config.model) && config.model == 'search'){ 
        	if(!config.grid.columns){
        		Q.error($("comm.errorColumnsMsg"));//"请指定columns配置!"
        		return;
        	}
        	if(!config.grid.store){
        		Q.error($("comm.errorStoreMsg"));//"请指定store配置!"
        		return;
        	}
            var gridPanel = this.gridPanel = this.createGrid(config);
            items.push(gridPanel);
        }
    } 
    
    
    config = Ext.apply({
		title:config.winName+$("label.winDescribe"), //xxx的窗口
        layout:"border",
        items: items, 
        width: 700, 
        closeAction:'close', 
        height:200 , 
    },config);
    
    this.cfg = config;
     
    
    Q.comm.CommCreateWin.superclass.constructor.call(this,config);
    /**
     * @event select  选中事件
     * @param {object} grid 当前窗口grid
     * @return {object}  selections 选中记录
     */ 
    this.addEvents("select");
    this.addEvents("submit");
}

Ext.extend(Q.comm.CommCreateWin, Ext.ux.Window, {
	  /**清空formPanel private
     * resetWin Function 
     */
    resetWin:function(){
    	var win = this; 
    	win.formPanel.getForm().reset();
    	win.formPanel.destroy();
    	if(!Ext.isEmpty(win.gridPanel)){
    		win.gridPanel.destroy();
    	}
        win.close();  
    }, 
	
	//private
	/** 提交表单数据，将表单数据验证无误，文本域字段会做换行处理，隐藏的时间/日期字段编辑提交时必须时间格式化处理
     * isValidWin Function
     * @param {String} s  为 save/audit/submit 提交后action 会根据不同类别做对应处理
     */
    submitWin:function(flag){
        var win =this;
        var isValid = this.formPanel.form.isValid(); 
        if(!isValid){
            return false;
        } 
        var cfg = win.cfg;
        var url = win.cfg.url; 
        Q.confirm(Ext.isEmpty(cfg.messageName)?$("message.save.confirm"):$("message."+cfg.messageName+".confirm"), {//确定保存吗？
            ok: function(){ 
                var params = {};    
                var datefield = win.formPanel.findByType(Ext.form.DateField);
                Q.each(datefield,function(o,i){  
                    if(o.lateNight===true){ //日期字段后面要加23:59:59的做法 lateNight:true;
                        params[o.name]=(o.getValue().format('Y-m-d')+" 23:59:59");
                    }else if(!Ext.isEmpty(o.getValue()) && o.format == "Y-m" ){  
                        params[o.name] = o.getValue().format('Y-m-d') + " 00:00:00";  
	   				}else if((o.disabled || o.readOnly) && !Ext.isEmpty(o.getValue())){ 
	   				    params[o.name] = o.getValue().format('Y-m-d');  
	   				}   
                })
                var timefield = win.formPanel.findByType(Ext.form.TimeField);
                Q.each(timefield,function(o,i){
                    params[o.name]="1970-01-01 "+o.getValue();//对时间字段的维护
                })
                var textareas = win.formPanel.findByType(Ext.form.TextArea);
                Q.each(textareas,function(o,i){
                    o.setValue(o.getValue().replace(/\n/g, "<br/>"));
                })
                var ff = win.formPanel.findByType(Ext.form.Field);  
                var formParams = win.formPanel.getForm().getValues(); 
                var uxcombo = win.formPanel.findByType(Ext.form.ComboBox); 
                Q.each(uxcombo,function(o,i){  
                	if(Ext.isEmpty(formParams[o.name])){ 
                      	 if(o.readOnly === true ){
                      		 params[o.name] =  o.getValue(); 
                      	 }else if(o.disabled === true){
                      		 params[o.name] =  o.getValue(); 
                      	 } 
                	}
                });
                  
                var trigger = win.formPanel.findByType(Ext.form.TriggerField);  
                Q.each(trigger,function(o,i){ 
                	if(Ext.isEmpty(formParams[o.name])){ 
		               	 if((o.disabled || o.readOnly) && o.xtype == 'uxtrigger'){
		               		 params[o.name] =  o.getValue(); 
		               	 }
                	}
                }); 
                 
                //提交验证后事件
                var fn = typeof cfg.submitBefore  !="undefined" && typeof cfg.submitBefore  !="undefined" && win.submitBefore;
        		if(Ext.isFunction(fn)){
        			//params提交的数据,表单
        			fn(params,win.formPanel);
        		}    
                
                win.formPanel.getForm().submit({
                    waitTitle: $("message.submit.data"),//数据提交
                    waitMsg: $("message.submit.wait"),//正在提交数据，请稍候。。。
                    url: url,
                    method: 'POST',
                    params: Ext.encode(params) == '{}'? params:Q.parseParams(params),//如查params为空对像时不用Q.parseParams转换
                    
	        	    success:function(form, action){
	                      var result = action.result, msg = result.info;
	                      //提交验证后事件
	                      var fn = typeof cfg.submitAfter  !="undefined" && typeof cfg.submitAfter  !="undefined" && cfg.submitAfter;
	                	  if(Ext.isFunction(fn)){
	                	     //params提交的数据,表单
	                		 fn(form,action,params);
	                	  }      
	                      win.fireEvent("submit");   
	                      win.resetWin();
	                      Q.tips("<font color='blue'>"+$("message.save.success")+"</font>");//保存成功！
	                },
	                failure: function(form, action){
	                      if(action && action.result){
	                          Q.error(action.result.info || $("message.submit.failure"));//数据提交失败！
	                      }else{
	                          Q.error($("message.submit.failure")+"<br/><br/>"+$("message.system.disconnect"));//数据提交失败！请检查与服务器的连接是否正常，或稍候再试。
	                      }
	                } 		 
                });
            }
        });
    },
    
    //private
	//查询方法
    searchData: function(){
    	var win = this;
        var form = win.formPanel.form;
        if(!form.isValid()){return;} 
        var grid = win.gridPanel;
        var store = grid.getStore();
        
        var dataSearch = form.getValues();
        Q.each(dataSearch,function(v,p){
            if(p.indexOf("LIKE")>-1 && !Ext.isEmpty(v)){dataSearch[p]="%"+v+"%"};
        });             
        
        Ext.apply(store.baseParams, dataSearch);
            
        if(!Ext.isEmpty(store.lastOptions)){
        	 store.load({params:{start:0, limit:store.lastOptions.params.limit}});
        }else{
        	 store.load({params:{start:0, limit:20}});
        } 
        //this.hide();
    }, 
    //private
    //处理通过配置传入的表单字段
    dealFormItems:function(cfg){ 
    	Ext.applyIf(cfg.form,{
    		items:[],
    		columnWith:0.5
    	}); 
    	//暂存变量
    	var itemsTemp = [];
    	//初始化时表单字段
    	var formItems = cfg.form.items || [];  
        var formColumnWidth = this.formColumnWidth = cfg.columnWidth || 0.5; 
        //是否显示单据状态
        if(cfg.isShowStatus){
        	  var isShowStatus = cfg.isShowStatus == false ? true : false; 
              if(formItems){ 
	              	if(!isShowStatus){
	              		if(!Ext.isEmpty(cfg.checkboxgroup)){
	                  		formItems.push({
	               	            fieldLabel:$("label.billStatus"),
	               	            anchor: "100%",
	               	            xtype:"checkboxgroup",
	               	            items:cfg.checkboxgroup
	               	        });
	              		}
	              		if(!Ext.isEmpty(cfg.checkboxgroup1)){
	      	            	formItems.push({
	               	            fieldLabel:!Ext.isEmpty(cfg.checkboxgroup)?'':$("label.billStatus"),
	      	     	            anchor: "100%",
	      	     	            xtype:"checkboxgroup",
	      	     	            hidden:cfg.checkboxgroup1.length>0?false:true,
	      	     	            items:cfg.checkboxgroup1.length>0?cfg.checkboxgroup1:[{}]
	      	     	        });
	              		}
	              	} 
              }  
        }

      	Q.each(formItems,function(o,i){ 
      		var newItem = {
				defaults:{xtype:"textfield",anchor: "95%"},
  				columnWidth:o.xtype == "checkboxgroup"? 1:o.columnWidth || formColumnWidth,
				items:[o]
		    } 
      		itemsTemp.push(newItem);
    	}); 
        return itemsTemp;
    },

	//private
	/** 
	 * 按钮处理方法 
     * dealButton Function
     * @param {Object} cfg 为 panel 配置项
     */
    dealButton:function(cfg){
    	var win = this;
    	var buttonOld = [{
                    text: Ext.isEmpty(cfg.buttonText)?$("button.sendMail"):cfg.buttonText, 
                    name:'Btn1',
                    handler: function(){
                    	if(!Ext.isEmpty(cfg.model) && cfg.model == 'search'){ 
                            win.searchData();
                    	}else{ 
                            win.submitWin();
                    	}
                    }
                },{
                	text: $("button.return"),
                	name:'return',
                    Qtext: "返回",
                    handler: function(){
                        win.resetWin();  
                    }
                }];
    	var btnNew = [];
    	//如有额外按钮添加
    	if(Ext.isEmpty(cfg.btns)){
    		btnNew = buttonOld;
    	}else{ 
    		Q.each(cfg.btns,function(r,i){
    			btnNew.push(r);
    		}); 
    		//隐藏btn 
    		Q.each(buttonOld,function(r,i){
    			if(!Ext.isEmpty(cfg.btnHiddens)){ 
            		if(cfg.btnHiddens.indexOf(r.name) == -1){
            			btnNew.push(r);
            		}
            	}else{ 
        			btnNew.push(r);
            	}
    		});
    	} 
    	
    	
    	return btnNew;
    },
    //private
    //创建维护表单
    createFormPanel: function(cfg){
        var win = this; 
        var formPanel = new Ext.form.FormPanel({
            region: Ext.isEmpty(cfg.grid)?"center":"north", 
            labelWidth:cfg.labelWidth || 100,
            layout:"column",
            frame:false,
	 		autoScroll:true,
            height:Ext.isEmpty(cfg.form.height)?'':cfg.form.height,
            border:true,
            bodyStyle:"padding:10px", 
            defaults:{columnWidth:1,layout:"form",border:false},
            items:win.dealFormItems(cfg),
            buttons:win.dealButton(cfg)
        });
        return formPanel;
    } ,
    
    createGrid : function(cfg){
    	var win = this; 
    	var sm = cfg.grid.sm || {singleSelect:true};
    	var singleSelect = sm.singleSelect;
        var grid = new Ext.ux.grid.GridPanel({
            border:true,
            style:"padding:2px 2px 2px 0",
            frame:true,
            region:"center",   
            viewConfig:cfg.grid.viewConfig || {forceFit:false},
            sm:sm,
    		cm:{
    			defaults:{width:100}, 
    			columns: cfg.grid.columns
    		},
            store:cfg.grid.store , 
            listeners:{/*
                "rowdblclick": function(g, i){
                    if(g.doSelect(win, singleSelect)){
                        win.close(); //双击选择时，选择完成后自动隐藏
                    }
                }*/
            },
            tbar:cfg.grid.tbar || [{
                text: $("button.select"),//"选择"
                iconCls:"icon-save",
                handler: function(){
                    var selectFlag = grid.doSelect(win, singleSelect);
                    if(selectFlag && singleSelect){
                        win.close(); //单选时，选择完成后自动隐藏
                    }
                }
            }],
            //private 自定义
            //选择
            doSelect: function(win, singleSelect){
                var grid = this, sm = grid.getSelectionModel(), selections = sm.getSelections();
                if(selections.length < 1){
                    Q.tips("<font color='red'>"+$("comm.pleaseSelect")+"</font>");//"请选择一条信息!"
                    return false;
                }
                if(singleSelect){
                    win.fireEvent("select", grid, selections[0]);
                }else{
                    win.fireEvent("select", grid, selections);
                }s
                Q.tips("<font color='blue'>"+$('message.selectSuccess')+"</font>"); //选择成功!
                win.close();
                return true;
            }
        
        });
        return grid;
    }
    
});

