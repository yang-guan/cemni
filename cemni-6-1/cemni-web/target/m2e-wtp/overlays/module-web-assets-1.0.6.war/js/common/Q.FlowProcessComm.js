Ext.ns("Q");
/**
 * grid:"grid”单据列表
 * menu:左键菜单
 * classPath 类的访问路径
 * eventMethod 获取右键菜单内容的方法
 * 
 * **/
Q.FlowProcess = function(conf){
    this.key="";
    conf=conf||{};
    var grid=conf.grid;
    var menu=conf.menu;
    this.getRightMenu = function(){
    	var win  = this;
    	win.eventmu =null;
    	    grid.on("rowcontextmenu",function(grid,rowIndex,e){
    	    	win.eventmu = e;
    	        var target = e.getTarget();
    	        e.preventDefault();
    	        if(rowIndex<0){
    	            return;
    	        }
    	        var view = grid.getView();
    	        var rowIndex = view.findRowIndex(target);
    	        if(rowIndex>=0){
    	            //如果鼠标在有效的grid 范围内，则把当前grid选中
    	            grid.getSelectionModel().selectRow(rowIndex);
    	        }
    	        else {
    	            //清空已经选中的行
    	            gridPanel.getSelectionModel().clearSelections();
    	        }
    	    })
    	    grid.getSelectionModel().on("rowselect",function(model, rowIndex, r){
    	    	if(!grid.isAudit) return false;
    	        var selected = grid.getSelectionModel().getSelected();
    	        var men2 = menu.items;
    	        men2.each(function(item){
    	        	item.hide();
    	        });

    	        if(!Ext.isEmpty(selected)){
    	            Ext.Ajax.request({
    	            	url:conf.classPath+"_"+conf.eventMethod+".action",
    	                method:"post",
    	                params:{"id":selected.id},
    	                success:function(result){
    	                    var json = Ext.decode(result.responseText);
    	                    var men = menu.items;
    	                    if(Ext.isEmpty(json)){
    	                        Ext.getCmp("unstate").show();
    	                        if(!Ext.isEmpty(win.eventmu)){
    	                            menu.showAt(win.eventmu.getPoint());
    	                            win.eventmu =null;
    	                        }
    	                        return;
    	                    }
    	                    
    	                    men.each(function(item){
    	                        Q.each(json,function(j,js){
    	                        	if(j.indexOf("Infoid#null")>0){
    	                                Ext.getCmp("unstate").show();
    	                                if(!Ext.isEmpty(win.eventmu)){
    	                                    menu.showAt(win.eventmu.getPoint());
    	                                    win.eventmu =null;
    	                                }
    	                                return;	
    	                        	}
    	                        	if(j.indexOf("#")>0){
    	                        		win.flowInfoId = j.split("#")[1];
    	                        	}
    	                            if(item.name==j){
    	                            	item.show();
    	                                return false;
    	                            }
    	                        })
    	                    });
    	                    
    	                    if(!Ext.isEmpty(win.eventmu)){
    	                        menu.showAt(win.eventmu.getPoint());
    	                        win.eventmu =null;
    	                    }
    	                    
    	                }
    	            });

    	        }
    	    })
    	    //在重新刷新订单的时候清除菜单

    	    grid.getStore().on("load",function(){
    	        var men1 = menu.items;
    	        men1.each(function(item){
    	        	item.hide();
    	        	/*if(item.name=="sel"){
    	        		item.show();
    	        	}*/
    	        });
    	    })
    	    grid.on('click', function(g,r,e){
    	        var selctem = grid.getSelectionModel().getSelected();
    	        var men1 = menu.items;
    	        men1.each(function(item){
    	        	item.hide();
    	        	/*if(item.name=="sel"){
    	        		item.show();
    	        	}*/
    	        });
    	     });
    },
    this.getDealstate = function (_state, _text, _megType, _msgNotAllowBlank){
    	 var winflow  = this;
    	 var fn = function (_state, _text,_megType, _msgNotAllowBlank){
    	        var url = conf.classPath
    	        /*switch(_state){
    	            case "TOPASS" : suggestion(_state,_text);
    	                break;
    	            case "TONOPASS" : suggestion(_state,_text);
    	                break; 
    	            default:defaultDeal(_state,_text);
    	        }*/
    	        //意见填写提示框
    	        if(_megType){
    	        	suggestion(_state,_text,_msgNotAllowBlank);
    	        }else{
    	        	//提示信息
    	        	defaultDeal(_state,_text);
    	        } 
    	        function suggestion(_state,_text,_msgNotAllowBlank){
    	            Ext.MessageBox.show({
    	                title: $("message.prompt"),
    	                msg: $("message.operator.confirm").replace("{0}",_text)+'<br/>'+$("message.canInputAuditOpinion"),
    	                width:450,
    	                buttons:  {ok:$("dict.yes"), cancel:$("dict.no")},
    	                multiline: true,
    	                icon: Ext.MessageBox.QUESTION,
    	                fn: function(btn, _message) {
    	                    if(btn=="ok"){
    	                    	if(_msgNotAllowBlank && Ext.isEmpty(_message)){
    	                    		Q.tips(Q.color($("message.auditOpinionIsNotNull")));
    	                    		fn(_state, _text, _megType, _msgNotAllowBlank);
    	                    		return ;
    	                    	}
    	                        submit(_state,_message);
    	                    }
    	                }
    	            });
    	        }

    	        function defaultDeal(_state,_text){
		        	Ext.MessageBox.confirm( $("message.prompt"), $("message.operator.confirm").replace("{0}",_text), function(btn){
		                if(btn=="yes"){
		                    submit(_state,"");
		                }
		            });
    	        }
    	        function submit(_state,_message){
    	        	var win = grid.getEditWin(grid.cfg);
    	            var message =_message||"";
    	           // url+="_"+_state+".action";
    	            url+="_dealStatus.action";
    	            var selected = grid.getSelectionModel().getSelected();
    	            Ext.MessageBox.wait($("message.submit.wait"), $("message.submit.data"));
    				Ext.getBody().mask();
    	            Ext.Ajax.request({
    	                url:url,
    	                params:{"id":selected.id,"billState":_state,"message":message,"flowInfoId":winflow.flowInfoId},
    	                method:"POST",
    	                success:function(result){
    	                	//-------------------遮罩关闭-----------------------------
    	                    Ext.MessageBox.hide();//关闭遮罩
    	                    if(!Ext.isEmpty(win.getEl())){
    	                    	win.getEl().unmask();
    	                    }	    
    	                    Ext.getBody().unmask();
    			          //-------------------遮罩关闭-----------------------------
    	                    var json = Ext.decode(result.responseText);
    	                    var flag = json.success;
    	                    if(flag){
    	                    	if(win.hidden==false){
    	                    		win.resetWin();
    	                    		win.hide();
    	                    	}
    	                    	grid.getStore().reload();
    	                    }else{
    	                        Q.error(json.info || $("message.submit.failure"));
    	                    }
    	                }
    	            })
    	        }
    	    };
    	 return fn 
     }
    this.getRightMenu();
};