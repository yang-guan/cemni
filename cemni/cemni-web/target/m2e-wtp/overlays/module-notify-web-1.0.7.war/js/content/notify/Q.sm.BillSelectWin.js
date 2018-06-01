Ext.ns("Q.sm");

Q.sm.BillSelectWin = function(cfg){
	cfg = cfg ||{};
	var singleSelect = false !== cfg.singleSelect;
    var grid = this.createGrid(cfg,singleSelect);
 
    Q.sm.BillSelectWin.superclass.constructor.call(this, {
//        title:$("UserList"),
    	title:"单据选择",
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

    if(Ext.isFunction(cfg.select)){
        this.on("select", cfg.select);
    }
};
Ext.extend(Q.sm.BillSelectWin, Ext.ux.Window, {
    createGrid : function(cfg,singleSelect){
        var win = this;
        var grid = this.gridPanel= new Ext.ux.grid.GridPanel({
            border:true,
            style:"padding:2px 2px 2px 0",
            sm: {singleSelect: singleSelect},
            viewConfig:{forceFit:true,stripeRows: true},
            store:{
            	url: path + "/bill/BillType_getJson.action",
//                baseParams:cfg.baseParams||{}
            },
            cm:{
                defaultSortable:false,
                columns:[
                    {Qheader:"单据类型编码",header:"单据类型编码",dataIndex:"billTypeCode"}, 
                    {Qheader:"单据类型名称",header:"单据类型名称",dataIndex:"billTypeName"}
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
                text:"单据类型编码"+"："
            },{
                name:"billTypeCode",
                xtype:"textfield",
                width:100
            },"-",{
                xtype:"label",
                text:"单据类型名称"+"："
            },{
            	 name:"billTypeName",
                xtype:"textfield",
                width:100
            },{
                text:$("button.search"),
                iconCls:"icon-search",
                handler:function(_self){
                    var store = grid.getStore();
                    var billTypeCode = _self.ownerCt.find("name","billTypeCode")[0].getValue();
                    var billTypeName = _self.ownerCt.find("name","billTypeName")[0].getValue();
                    store.proxy.extraParams.filter_LIKE_billTypeCode=billTypeCode;
                    store.proxy.extraParams.filter_LIKE_billTypeName=billTypeName;
                    store.load({params:{start:0, limit:20}}); 
                }
            }],
            listeners:{
                "itemdblclick": function(g, i){
                    if(g.grid.doSelect(win, singleSelect)){
                        win.hide(); 
                    }
                }
            },
            //private 自定义
            //选择
            doSelect: function(win, singleSelect){
                var grid = this, 
                sm = grid.getSelectionModel(), 
                selections = sm.getSelection();
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
});