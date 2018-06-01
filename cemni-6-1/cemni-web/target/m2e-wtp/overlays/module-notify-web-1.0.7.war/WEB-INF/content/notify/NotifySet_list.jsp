<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.huiju.module.web.util.WebUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibsComm.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="application.title"/></title>

<script type="text/javascript">
    var path='${ctx}'; //全局绝对路径
</script>

<script type="text/javascript">
/* Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif"; */
Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget = 'qtip';
<%  //取session保存的权限, 生成相应的power, 在相应的js中取power进行生成
String s_authorities = WebUtils.getAuthorities();
%>
Q.power = {
	NotifySet:{	/* 按钮权限 */
        list:<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_LIST|")> -1)%>,
        add:<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_ADD|")> -1)%>,
        edit:<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_EDIT|")> -1)%>,
        'delete':<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_DELETE|")> -1)%>,
        view:<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_VIEW|")> -1)%>,
        search:<%=(s_authorities.indexOf("|S_SM_NOTIFYSET_SEARCH|")> -1)%>
    }
};

var moduleName=$("materialInfo.title");
var power = Q.power.NotifySet;
Q.power.NotifySet = null;
//单位
var allUnits = new Ext.data.JsonStore({
    url: path+"/bp/businessparams/Unit_getAll.action",
    fields: [{name:'unitCode'},{name:"unitName"}],
    autoLoad:true
});

//通用
var comStore =	new Ext.data.JsonStore({
	    	fields:[{name:"name",type:"string"},{name:"code",type:"int"}],
	    	data:[{name:$("dict.no"),code:'0'},{name:$("dict.yes"),code:'1'}]
});

//轮次store
var templateStore = new Ext.data.Store({
    proxy: new Ext.data.HttpProxy({
        url: path+"/notify/NotifySet_findTemplateAll.action"
    }),
    autoLoad: false
});

Ext.require([
		'Ext.ux.form.FormPanel'
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
   	   ,'Ext.ux.data.JsonStore'
   	], function(){
		/* Ext.BLANK_IMAGE_URL = "http://localhost:7001/plugins/ext3/resources/images/default/s.gif"; 
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'qtip';*/
		
		
		Q.power = {
		    LogOperation:{
		        list:true
		    }
		};
		Ext.Loader.loadScripts({url:[path + "/js/content/notify/Q.sm.NotifySetEditWin.js",
		path + "/js/content/notify/Q.sm.NotifySetSearchWin.js",
		path + "/js/content/notify/Q.sm.UserSelectWin.js",
		]});
		
		var power = Q.power.LogOperation;
		Q.power.LogOperation = null;
		var searchdata = {};
	}
);


Ext.onReady(function(){
    //---------------------------gridPanel start------------------------------------------
    var grid = new Ext.ux.grid.GridPanel({
    	border:true,
        style:"padding:2px 2px 2px 0",
        sm:{singleSelect:true},
        cm:{
            defaults:{width:100,align:"left"},
            columns:[
/*                 {Qheader: "消息通知设置id",header: $("notifySet.notifySetId"), dataIndex: "notifySetId", disabled: true},
                {Qheader: "单据类型编码",header: $("notifySet.billTypeCode"), dataIndex: "billTypeCode"},
                {Qheader: "单据类型名称",header: $("notifySet.billTypeName"), dataIndex: "billTypeName"},
                {Qheader: "通知设置编码",header: $("notifySet.notifySetCode"), dataIndex: "notifySetCode"},
                {Qheader: "通知设置名称",header: $("notifySet.notifySetName"), dataIndex: "notifySetName"},
                {Qheader: "站内消息提醒",header: $("notifySet.messageFlag"),dataIndex:"messageFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "邮件提醒",header: $("notifySet.emailFlag"),dataIndex:"emailFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "短信提醒",header: $("notifySet.smsFlag"), dataIndex:"smsFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "微信提醒",header: $("notifySet.weixinFlag"), dataIndex: "weixinFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "app提醒",header: $("notifySet.appFlag"), dataIndex: "appFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "制单人提醒标识",header: $("notifySet.createUserFlag"), dataIndex: "createUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "流程审批人提醒标识",header: $("notifySet.bpmUserFlag"), dataIndex: "bpmUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "固定用户提醒标识",header: $("notifySet.fixedUserFlag"), dataIndex: "fixedUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "供应商提醒标识",header: $("notifySet.vendorUserFlag"), dataIndex: "vendorUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "使用状态",header: $("label.usingState"), dataIndex: "status", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {header: $("label.createUser"), dataIndex: "createUserName", disabled: true},
                {header: $("label.createTime"), dataIndex: "createTime", disabled: true},
                {header: $("label.modifyUser"), dataIndex: "modifyUserName", disabled: true},
                {header: $("label.modifyTime"), dataIndex: "modifyTime", disabled: true} */
                {Qheader: "消息通知设置id",header: "消息通知设置id", dataIndex: "notifySetId", disabled: true},
                {Qheader: "单据类型编码",header: "单据类型编码", dataIndex: "billTypeCode"},
                {Qheader: "单据类型名称",header: "单据类型名称", dataIndex: "billTypeName"},
                {Qheader: "通知设置编码",header: "通知设置编码", dataIndex: "notifySetCode"},
                {Qheader: "通知设置名称",header: "通知设置名称", dataIndex: "notifySetName"},
                {Qheader: "站内消息提醒",header: "站内消息提醒",dataIndex:"messageFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "邮件提醒",header: "邮件提醒",dataIndex:"emailFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "短信提醒",header: "短信提醒", dataIndex:"smsFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "微信提醒",header: "微信提醒", dataIndex: "weixinFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                /* {Qheader: "app提醒",header: "app提醒", dataIndex: "appFlag", renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }}, */
                {Qheader: "制单人提醒标识",header: "制单人提醒标识", dataIndex: "createUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "流程审批人提醒标识",header: "流程审批人提醒标识", dataIndex: "bpmUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "流程告知者提醒标识",header: "流程告知者提醒标识", dataIndex: "fixedUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "供应商提醒标识",header: "供应商提醒标识", dataIndex: "vendorUserFlag", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {Qheader: "使用状态",header: "使用状态", dataIndex: "status", disabled: true, renderer: function (v) {
                    return v == 1? $("dict.yes"):$("dict.no");
                }},
                {header: $("label.createUser"), dataIndex: "createUserName", disabled: true},
                {header: $("label.createTime"), dataIndex: "createTime", disabled: true}, 
                {header: $("label.modifyUser"), dataIndex: "modifyUserName", disabled: true},
               	{header: $("label.modifyTime"), dataIndex: "modifyTime", disabled: true} 
            ]
        },
        store:{
            url:path+"/notify/NotifySet_getJson.action"
        },
        //构造新建、编辑窗口
        getEditWin:function(editflag){
            var win  = Ext.create("sm.NotifySetEditWin",{
            	editflag:editflag
            })//config);
            win.on("submit",function(){
                grid.getStore().reload();//刷新列表
            })
            return win;
        },
        getSearchWin:function(){
        	var win = Ext.create("sm.NotifySetSearchWin",{
        		grid:grid
        	})
        	/* var win = new Q.sm.NotifySetSearchWin({grid:grid}); */
            var store = grid.getStore();
            win.on("search", function(data){
            	searchdata={};
                Q.each(data,function(v,p){
                	if(!Ext.isEmpty(v)){//条件为空的就不传到查询条件中去
                		searchdata[p]=v;
                	}
                });
                var billTypeCode = data.filter_LIKE_billTypeCode;
                var billTypeName = data.filter_LIKE_billTypeName;
                var notifySetCode = data.filter_LIKE_notifySetCode;
                var notifySetName = data.filter_LIKE_notifySetName;
                store.proxy.extraParams.filter_LIKE_billTypeCode=billTypeCode;
                store.proxy.extraParams.filter_LIKE_billTypeName=billTypeName;
                store.proxy.extraParams.filter_LIKE_notifySetCode=notifySetCode;
                store.proxy.extraParams.filter_LIKE_notifySetName=notifySetName;
                store.baseParams = searchdata;
                store.load({params:{start:0, limit:20}});
            });
            return win;
        },
        tbar:["",{
            name:"add",
            text:$("button.new"),//新建
            build:power.add,
            iconCls:"icon-add",
            handler: function(g){
                var grid = this.findParentByType(Ext.ux.grid.GridPanel); //查找出所属的父Grid
                var win = grid.getEditWin(true);
                win.show();
                win.setEditState(true);
             /* win.gridPanel.hide(); */
                win.formPanel.form.findField("model.billTypeCode").setValue(billTypeCode);
                win.formPanel.form.findField("model.billTypeName").setValue(billTypeName);
            }
        },{
            name:"edit",
            text:$("button.edit"),
            build:power.edit,
            iconCls:"icon-edit",
            handler: function(_self){
                var grid = this.findParentByType(Ext.grid.GridPanel); //查找出所属的父Grid
                var r = grid.getSelectionModel().getSelected();
                if(!r){
                	Q.tips("<font color='red''>"+$("message.edit.select")+"</font>");
                	return;
                }
                var win = grid.getEditWin(true);
                win.show();
                /* win.gridPanel.hide(); */
                win.setFormValue(r.items[0].data.notifySetId);//取id
                win.setEditState(true);
            }
        },{
            name:"delete",
            text:$("button.delete"),//删除
            build:power['delete'],
            iconCls:"icon-delete",
            handler: function(){
                var grid = this.findParentByType(Ext.grid.GridPanel); //查找出所属的父Grid
                var r = grid.getSelectionModel().getSelected();
                if(!r){
                	Q.tips("<font color='red''>"+$("message.delete.select")+"</font>");
                	return;
                }
                Q.confirm($("message.delete.confirm"), {
                    ok: function(){
                        Ext.getBody().submitMask();
                        Ext.Ajax.request({
                            url: path+"/notify/NotifySet_delete.action",
                            params:{ "id" : r.items[0].data.notifySetId },
                            success: function(response){
                                var json = Ext.decode(response.responseText);
                                if(false === json.success){
                                    Q.error(json.info||"<font color=''>"+$("message.delete.failure")+"<br/><br/>"+$("message.system.error")+"</font>");
                                    return;
                                }
                                Q.tips("<font color='blue'>"+$("message.delete.success")+"</font>");
                                grid.getStore().reload();
                            },
                            failure: function(response){
                                Q.error("<font color=''>"+$("message.delete.failure")+"<br/><br/>"+$("message.system.disconnect")+"</font>");
                            },
                            callback: function(){
                                Ext.getBody().unmask();
                            }
                        });
                    }
                });
            }
        },{
            name:"view",
            text:$("button.view"),
            build:power.view,
            iconCls:"icon-view",
            handler: function(_self){
                var grid = this.findParentByType(Ext.grid.GridPanel);
                var r = grid.getSelectionModel().getSelected();
                if(!r){
                	Q.tips("<font color='red''>"+$("message.view.select")+"</font>");
                	return;
                }
                var win = grid.getEditWin(false);
                win.show();
                win.setFormValue(r.items[0].data.notifySetId);
                win.setEditState(false);
            }
        },{
            name:"search",
            text:$("button.search"),
            build:power["search"],
            iconCls:"icon-search",
            handler: function(_self){
                var win = grid.getSearchWin();
                win.show();
            }
        },{
            name:"clear",
            text:$("button.clearSearch"),
            build:power.list,
            iconCls:"icon-toCancel",
            hidden:true,
            handler: function(_self){
            	_self.hide();
            	var store = grid.getStore();
            	store.baseParams={};
            	searchdata={};
                
                var billTypeCode = "";
                var billTypeName = "";
                var notifySetCode = "";
                var notifySetName = "";
                store.proxy.extraParams.filter_LIKE_billTypeCode=billTypeCode;
                store.proxy.extraParams.filter_LIKE_billTypeName=billTypeName;
                store.proxy.extraParams.filter_LIKE_notifySetCode=notifySetCode;
                store.proxy.extraParams.filter_LIKE_notifySetName=notifySetName;
                store.baseParams = searchdata;
                
            	store.load({params:{start:0, limit:20}});
            }
        }],
        setObj:function(name,disabled){
        	var gird = this;
        	if(power[name]){
        		grid.getTopToolbar().find("name",name)[0].setDisabled(disabled);
        	}
        },
        listeners:{
            "afterrender": function(g){
                g.setObj("delete",true);
                g.setObj("edit",true);
                g.setObj("view",true);
                g.setObj("search",false);
                g.setObj("clear",false);
                
                var sm = g.getSelectionModel();

                sm.on("selectionchange",function(){
                	if(sm.hasSelection()){
                		g.setObj("delete",false);
                        g.setObj("edit",false);
                        g.setObj("view",false);
                	}
                });
                
                g.getStore().on("load", function(){
                    sm.clearSelections();
                });
            },
            "rowdblclick": function(g,i){
                var tbar = g.getTopToolbar();
                if (power.edit && tbar) {
                    tbar.find('name','edit')[0].handler();
                } else { //有权限时进行操作
                    tbar.find('name','view')[0].handler();
                }
            }
        }
    })

/*     var tree = new Ext.ux.tree.TreePanel({
        id:"-1",
        text:$("billtype"),
        rootVisible:true,
        width: 200,
        url:path+"/sm/billset/BillType_getTree.action",
        treeType : TreeType.INFO, //树类型 - 位于tree.js
        nodeName:"filter_EQ_billTypeCode",
        grid: grid,
        listeners:{
            "afterrender": function(){
                var tbar =grid.getTopToolbar();
                for(var i = 0; i < tbar.items.items.length; i++) {
                	tbar.items.items[i].setDisabled(tbar.items.items[i].name != "clear" && tbar.items.items[i].name != "search");
                }
            },
            "click":function(node){
            	billTypeCode = node.id;
            	billTypeName=node.text;
                grid.setObj("add",node.id == "-1");
            }
        }
    }); */

    var vp = new Ext.Viewport({
        layout:"border",
        items:[ grid]
    });
});
   //--------------------------onReady end------------------------------------
</script>
</head>
<body>
</body>
</html>