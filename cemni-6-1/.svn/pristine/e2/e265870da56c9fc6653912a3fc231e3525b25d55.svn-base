/**
 * 选择活动
 * 
 * @author：yuhb
 * @date：2017年3月20日 上午12:07:50
 */
Ext.ns('Q.actment');
Q.actment.chooseActWin = function(config) {
    var _gridPanel = this.gridPanel = this.createGridPanel();
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "活动-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config || {});

    Q.actment.chooseActWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.actment.chooseActWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function() {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : true
            },
            cm : {
                columns : [ {
                    dataIndex : "activityId",
                    hidden : true
                }, {
                    header : "活动单号",
                    dataIndex : "activityNo",
                    width : 13
                }, {
                    header : "活动主题",
                    dataIndex : "activityTheme",
                    width : 25
                }, {
                    header : "活动类型",
                    dataIndex : "activityTypeName",
                    width : 10
                }, {
                    header : "活动状态",
                    dataIndex : "statusName",
                    width : 10
                }, {
                    header : "开始日期",
                    dataIndex : "beginTime",
                    renderer : Q.common.dateRenderer,
                    width : 10
                }, {
                    header : "结束日期",
                    dataIndex : "endTime",
                    renderer : Q.common.dateRenderer,
                    width : 10
                } ]
            },
            store : {
                url : path + "/Activity_getJson.action?filter_IN_auditStatus=2",
                sort : "activityId",
                dir : "desc"
            },
            tbar : [ {
                text : "确定",
                iconCls : "icon-save",
                handler : function() {
                    _win.doSelect();
                }
            }, {
                text : "返回",
                iconCls : "icon-return",
                handler : function() {
                    _win.hide();
                }
            }, "->", {
                xtype : "textfield",
                emptyText : "活动编号/主题",
                name : "_activityTheme",
                width : 150
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    var tbar = _win.gridPanel.getTopToolbar();
                    _store.baseParams.filter_LIKE_activityNo_OR_LIKE_activityTheme = tbar.find("name", '_activityTheme')[0].getValue();
                    _store.load();
                }
            } ]
        });
    },
    doSelect : function() {
        var sm = this.gridPanel.getSelectionModel().getSelected();
        if (Ext.isEmpty(sm)) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        this.fireEvent("submit", sm);
        this.hide();
    }
});