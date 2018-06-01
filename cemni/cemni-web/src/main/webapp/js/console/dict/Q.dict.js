/**
 * 字典表
 * 
 * @author：yuhb
 * @date：2016年11月23日 上午10:07:48
 */
Ext.ns('Q.dict');
var dealUrl = path + "/Dict";
Q.loadJs(path + "/js/console/dict/Q.dict.editWin.js");

// 列表
Q.dict.gridColumn = [ {
    header : "节点Id",
    dataIndex : "dictId",
    width : 10
}, {
    header : "节点编码",
    dataIndex : "dictCode",
    width : 10
}, {
    header : "节点名称",
    dataIndex : "name",
    width : 23
}, {
    header : "值",
    dataIndex : "value",
    width : 7
}, {
    header : "父节点Id",
    dataIndex : "parentId",
    width : 10
}, {
    header : "序号",
    dataIndex : "orderNo",
    width : 10
}, {
    header : "是否有效",
    dataIndex : "isValid",
    renderer : Q.common.yesOrNotRenderer,
    width : 10
}, {
    header : "节点类型名称",
    dataIndex : "remark",
    width : 20
} ];

// 按钮
Q.dict.tbarBnt = [ {
    text : "查询",
    iconCls : "icon-search",
    build : power.list,
    handler : function() {
        var _store = vp.grid.getStore();
        _store.baseParams.filter_LIKE_dictCode_OR_LIKE_remark = Ext.getCmp("dictCodeOrRemark").getValue();
        _store.load();
    }
}, {
    xtype : "textfield",
    emptyText : "节点编码/节点类型名称",
    id : "dictCodeOrRemark",
    width : 180
}, "->", {
    text : "编辑",
    iconCls : "icon-edit",
    build : power.edit,
    handler : function() {
        var _grid = vp.grid;
        var sm = _grid.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        Ext.getBody().loadMask();
        var _win = new Q.dict.editWin({
            operUrl : "_update.action"
        });
        _win.setFormValue(sm.get("dictId"));
        _win.on("submit", function() {
            _grid.getStore().reload();
        });
        _win.show();
    }
}, {
    text : "新增",
    iconCls : "icon-add",
    build : power.add,
    handler : function() {
        var _win = new Q.dict.editWin({
            operUrl : "_save.action"
        });
        _win.on("submit", function() {
            vp.grid.getStore().reload();
        });
        _win.show();
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '字典表',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.dict.tbarBnt,
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        listEditStateFn : [],
        store : {
            idProperty : "dictId",
            url : dealUrl + '_getJson.action',
            sort : "dictCode",
            dir : "asc"
        },
        column : Q.dict.gridColumn
    }
};