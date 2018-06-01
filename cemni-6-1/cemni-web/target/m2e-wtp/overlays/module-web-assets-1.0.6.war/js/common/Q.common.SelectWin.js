Ext.ns("Q.common");
/**
 * author zhangxj 2015-02-02
 * 使用方法如下例子：
 * new Q.common.SelectWin({
     singleSelect: true,  --非必填
     //params:{} , 公用窗体的查询功能在有传递参数的时候会失效，所以最好不要使用params：{}传递参数，需要把参数直接在action的url上面拼接
     myFormTitle:$("selectwin.salesOrganization"),//选择窗体标题  --非必填  默认值  数据选择窗体
     mySearchProperties:"filter_LIKE_sorgcode_OR_sorgname",//查询参数  例如 filter_LIKE_  + 查询字段名 -- 非必填
     myGridCode:"sorgcode",//窗体grid显示的编码字段dataIndex对应的值  sorgPK是 SalesOrganization 这个实体的联合主键-- 必填
     myGridName:"sorgname",//窗体grid显示的名称字段dataIndex对应的值  -- 必填
     myAction:"/org/SalesOrganization_getJson.action",//窗体加载数据url  -- 必填
     select: function(g, r){ //绑定事件 -- 必填
    	 //选中事件处理内容.....
    	 form.findField("model.sorgcode").setValue(r.json.sorgcode);
         form.findField("model.sorgname").setValue(r.get("sorgname"));
     }
   })
 * 
 * */
Q.common.SelectWin = function(cfg){
    var grid = this.gridPanel = this.createGrid(cfg,false !== cfg.singleSelect);
    
    Q.common.SelectWin.superclass.constructor.call(this, {
        title:cfg.myFormTitle||$("selectwin.selectTitle"),
        layout:"border",
        width:cfg.width || 450,
        height:cfg.height || 480,
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
};
Ext.extend(Q.common.SelectWin, Ext.ux.Window, {
    createGrid : function(cfg,singleSelect){
        var win = this;
        
        var baseParams =cfg.params||{};
        var pageSize = 20;
        if(cfg.pageSize != undefined && cfg.pageSize == 0) {
        	pageSize = cfg.pageSize ;
        }
        var grid = new Ext.ux.grid.GridPanel({
            border:true,
            pageSize:pageSize,
            style:"padding:2px 2px 2px 0",
            sm: {singleSelect: singleSelect},
            store:{
                url:cfg.myAction,
                baseParams:baseParams,
                autoLoad:cfg.autoLoad || true
            },
            cm:{
                defaultSortable:false,
                columns:[
					{header:$("label.code"),width:50,dataIndex:cfg.myGridCode},
					{header:$("label.name"), dataIndex:cfg.myGridName}
                ]
            },
            tbar: [{
                text:$("label.select"),
                iconCls:"icon-save",
                handler: function(){
                    var selectFlag = grid.doSelect(win, singleSelect);
                    if(selectFlag){
                        win.hide(); //单选时，选择完成后自动隐藏
                    }
                }
            },{
                text:$("button.return"),
                iconCls:"icon-return",
                handler: function(){
                    win.hide();
                }
            },"->",{
	            name:"codeorname",
	            id:"codeId",
	            hidden:cfg.mySearchProperties==undefined,
	            emptyText:$("label.code") + "/" + $("label.name"),
	            xtype:"textfield",
	            width:80
	        },{
                text:$("button.search"),
                iconCls:"icon-search",
                hidden:cfg.mySearchProperties==undefined,
                handler:function(_self){
                    var store = grid.getStore();
                    var codeorname = _self.ownerCt.find("name","codeorname")[0].getValue();
                    if(codeorname != "")
                    	store.baseParams= Ext.decode("{"+ cfg.mySearchProperties + " : '" + codeorname +"'}");
                    store.load({params:{start:0, limit:20}}); 
                    if(!Ext.isEmpty(Ext.getCmp("clearSearchId"))){
                    	Ext.getCmp("clearSearchId").show();
                    }
                }
        	},{
        		text:$("button.clearSearch"),
        		id:"clearSearchId",
        		iconCls:"icon-toCancel",
        		hidden:true,
        		handler:function(){
        			this.hide();
        			var store = grid.getStore();
        			store.baseParams=baseParams;
        			store.load();
        			Ext.getCmp("codeId").setValue("");
        		}
        	}],
            listeners:{
                "rowdblclick": function(g, i){
                    if(g.doSelect(win, singleSelect)){
                        win.hide(); //双击选择时，选择完成后自动隐藏
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
                return true;
            }
        });
        return grid;
    }
});