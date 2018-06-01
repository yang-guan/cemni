Ext.ns("Q.permission");
/**
 * @param {object} config
 *      {boolean} singleSelect 是否单选，缺省为多选
 *      {function} select 单击[选择]按钮时触发的回调函数，该函数有两个参数：grid与"+labels.Selected+"记录
 *               单选时参数：(grid, record)
 *               多选时参数：(grid, selections)
 */
Q.permission.AuthGroupAddUserWin = function(config){
	var win =this;
	config=config||{};
    Ext.applyIf(config,{
        dealUrl : path + "/permission/AuthGroup"
    });
    var grid = this.createGrid(false == config.singleSelect);
    
    Q.permission.AuthGroupAddUserWin.superclass.constructor.call(this, {
        title:$("user.addUser"),//添加用户
        layout:"border",
        width:450,
        height:400,
        items:[grid],
        listeners:{
            hide: function(){
                grid.getSelectionModel().clearSelections();
            }
        }
    });
    this.addEvents("submit");
    this.addEvents("select");
    
    if(Ext.isFunction(config.select)){
        this.on("select", config.select);
    }
};

Ext.extend(Q.permission.AuthGroupAddUserWin, Ext.ux.Window, {
    createGrid : function(){
        var win = this;
        var grid = new Ext.ux.grid.GridPanel({
            border:true,
            style:"padding:2px 2px 2px 0",
            sm: {singleSelect: false},
            viewConfig:{
                forceFit: true
            },
            store:{
            	url:path+'/permission/AuthGroup_getUncheckedUsers.action'
            },
            cm:{
                defaultSortable:true,
                columns:[
                    {header:$("user.account"),sortable:true,dataIndex:'userCode'},
                    {header:$("user.name"),sortable:true,dataIndex:'userName'}
                ]
            },
            tbar: [{
                name:"save",
                text:$("button.save"),
                iconCls:"icon-save",
                handler:function(){
                	var grid = this.findParentByType(Ext.grid.GridPanel); //查找出所属的父Grid
	                var selections = grid.getSelectionModel().getSelections();
	                var keys = [];
	                if(selections.length<1){
	                    return;
	                }
	                Q.each(selections, function(){
                        keys.push(this.data.userCode);
                    });
                    Ext.Ajax.request({
                        url: "AuthGroup_saveAuthGroupUser.action",
                        method: 'POST',
                        params: { "saveUsersKey": keys,"agrCode":topSelected.get('agrCode')},
                        success: function(result){
                            var json = Ext.decode(result.responseText), flag = json.success;
                            if(flag){
                                win.fireEvent("submit");
                                Q.tips("<font color='blue'>"+$("message.operate.success")+"</font>");//操作成功！
                            }else{
                                Q.tips("<font color='red'>"+$("message.operate.failure")+"</font>");//操作失败！
                            }
                        },
                        failure: function(result){
                        }
                    });
	               win.hide();
	            }
            },{
                text:$("button.return"),
                iconCls:"icon-return",
                handler: function(){
                    win.hide();
                }
            },"->",{
                id:"searchParams",
                xtype:"textfield",
                //查询权限字段， 字段名或 表名
	            emptyText:$("user.account")+"/"+$("user.name"),
                width:100
                },{
                text:$("button.search"),
                iconCls:"icon-search",
                handler:function(){
	                var store = grid.getStore();
	                searchValue = Ext.getCmp("searchParams").getValue();
	                store.baseParams.filter_LIKE_userCode_OR_userName=searchValue;
	                store.load({params:{start:0, limit:20}});
	            }
            }],
            listeners:{
                "afterrender": function(g){
                    var sm = g.getSelectionModel();
                    g.getStore().on("load", function(){
                        sm.clearSelections();
                        var tbar =grid.getTopToolbar();
                        tbar.getObj = function(name){
                            return this.find("name",name)[0];
                        };
                        tbar.getObj("save").setDisabled(true);
                    });
                },
                "click":function(){
                    var record = grid.getSelectionModel().getSelections();
                    var tbar =grid.getTopToolbar();
                     tbar.getObj = function(name){
                            return this.find("name",name)[0];
                        };
                    if(Ext.isEmpty(record)){//选择行为空时，给他设置隐藏
                        tbar.getObj("save").setDisabled(true);
                    }else{
                        tbar.getObj("save").setDisabled(false);
                    }
                }
            }
        });
        return grid;
    }
});