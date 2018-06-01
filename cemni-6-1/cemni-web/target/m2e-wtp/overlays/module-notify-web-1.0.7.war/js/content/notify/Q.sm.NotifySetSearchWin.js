//Ext.ns('Q.sm');
/**
 * 消息通知查询
 *
 */
Ext.define("sm.NotifySetSearchWin",{
	requries:["Ext.ux.Window"],
	extend:'Ext.ux.Window',
	alias:"0",
	constructor : function(config){
    var win =this;
    config = config||{};
    Ext.applyIf(config,{
        dealUrl:path+ '/notify/NotifySet',
        moduleName:$("notifySet")
    });
    var formPanel=this.formPanel= this.createFormPanel();
    config = Ext.apply({
        title:config.moduleName,
        width:620,
        height:240,
        layout:'border',
        items:[formPanel],
        buttons:[{
            text:$("button.search"),
			Qtext:'查询',
            handler:function(){
                win.searchData();
            }
        }, {
			text:$('button.return'),
            Qtext:'返回',
            handler:function(){
                win.resetWin();
            }
        }]
    },config);
    this.callParent([config]);
    /**
     * @event submit 提交表单成功提示
     * @param {Ext.form.baseForm} form
     */
//    this.addEvents('submit');
    
//    Q.sm.NotifySetSearchWin.superclass.constructor.call(this,config);
},
//Ext.extend(Q.sm.NotifySetSearchWin,Ext.ux.Window,{
    //window  的事件监听
    listeners:{
        'hide':function(){
            this.resetWin();
        }
    },
    resetWin:function(){
       this.formPanel.getForm().reset();
       this.url='';
       if(!this.hidden){
           this.hide()
        }
    },
    searchData: function(){
    	var win = this;
        var form = win.formPanel.form;
        if(!form.isValid()){return;}
        if(false === this.fireEvent("search", form.getValues())){
            return;
        }
         var tbar = win.grid.getTopToolbar();
         var btn = tbar.find('name','clear')[0];
         if(btn){
               btn.show();
        }
        this.hide();
    },
    createFormPanel:function(){
    	var win = this;
    	var formPanel = new Ext.form.FormPanel({
            border:true,
            region:"center",
            labelWidth:100,
            autoScroll:false,
            layout:"column",
            bodyStyle:"padding:5px;",
            defaults:{columnWidth:1,layout:"form",border:false},
            items:[{
            	defaults:{xtype:'textfield',anchor: "-20"},
            	columnWidth:.5,
            	items:[{
//                    fieldLabel:$('notifySet.billTypeCode'),
            		fieldLabel:"单据类型编码",
                    name:'filter_LIKE_billTypeCode'
                },{
//                    fieldLabel:$("notifySet.notifySetCode"),
                	fieldLabel:"通知设置编码",
                    name:"filter_LIKE_notifySetCode"
                }]
            },{
                defaults:{xtype:'textfield' ,anchor: "-20"},
                columnWidth:.5,
                items:[{
//                    fieldLabel:$('notifySet.billTypeName'),
                	fieldLabel:"单据类型名称",
                    name:'filter_LIKE_billTypeName'
                },{
//                    fieldLabel:$("notifySet.notifySetName"),
                	fieldLabel:"通知设置名称",
                    name:"filter_LIKE_notifySetName"
                }]
            }]
            });
            return formPanel;
		}
//	});
});