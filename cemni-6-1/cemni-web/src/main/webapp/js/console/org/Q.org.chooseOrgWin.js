/**
 * 选择组织机构
 * 
 * @author：yuhb
 * @date：2017年3月25日 下午10:25:34
 */
Ext.ns('Q.org');
Q.org.chooseOrgWin = function(config) {
    Ext.getBody().loadMask();
    var _gridPanel = this.gridPanel = this.createGridPanel(config);

    var _config = this.config = Ext.apply({
        title : "组织机构-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 600,
        height : 450
    }, config);
    Q.org.chooseOrgWin.superclass.constructor.call(this, _config);
    this.init(config.orgCodes);
};

Ext.extend(Q.org.chooseOrgWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            pageSize : 0,
            sm : {
                singleSelect : false
            },
            cm : {
                columns : [ {
                    dataIndex : "orgId",
                    hidden : true
                }, {
                    dataIndex : "type",
                    hidden : true
                }, {
                    dataIndex : "deptCode",
                    hidden : true
                }, {
                    dataIndex : "bigAreaCode",
                    hidden : true
                }, {
                    dataIndex : "areaCode",
                    hidden : true
                }, {
                    dataIndex : "name",
                    hidden : true
                }, {
                    header : "机构名称",
                    width : 7,
                    dataIndex : "otherName"
                }, {
                    header : "机构编码",
                    width : 3,
                    dataIndex : "orgCode"
                } ]
            },
            store : {
                url : cfg.url,
                autoLoad : false
            },
            listeners : {
                rowclick : function(g, i, e) {
                    var sm = g.getSelectionModel();
                    var store = g.getStore();
                    var json = store.getAt(i).data;
                    var _type = json.type;

                    // 门店
                    if (_type == 5) {
                        return;
                    }
                    var cnt = store.getCount();
                    for (var j = 0; j < cnt; j++) {
                        var r = store.getAt(j);
                        if (_type == 2 && json.deptCode == r.data.deptCode) {// 部门
                            sm.isSelected(i) ? sm.selectRow(j, true) : sm.deselectRow(j);
                        }
                        if (_type == 3 && json.bigAreaCode == r.data.bigAreaCode) {// 大区
                            sm.isSelected(i) ? sm.selectRow(j, true) : sm.deselectRow(j);
                        }
                        if (_type == 4 && json.areaCode == r.data.areaCode) {// 区域
                            sm.isSelected(i) ? sm.selectRow(j, true) : sm.deselectRow(j);
                        }
                    }
                }
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
                emptyText : "机构名称",
                name : "orgName",
                width : 150
            }, {
                text : "查询",
                iconCls : "icon-search",
                handler : function() {
                    var _store = _win.gridPanel.getStore();
                    _store.baseParams.filter_LIKE_name = _win.gridPanel.getTopToolbar().find("name", 'orgName')[0].getValue();
                    _store.load();
                }
            } ]
        });
    },
    init : function(orgCodes) {
        var sm = this.gridPanel.getSelectionModel();
        var _store = this.gridPanel.getStore();
        _store.load({
            callback : function() {
                if (orgCodes) {
                    var _orgCodeArr = orgCodes.split(",");
                    var cnt = _store.getCount();
                    for (var i = 0; i < cnt; i++) {
                        var r = _store.getAt(i);
                        for (var j = 0; j < cnt; j++) {
                            if (_orgCodeArr[j] == r.data.orgCode) {
                                sm.selectRow(i, true);
                            }
                        }
                    }
                }
                Ext.getBody().unmask();
            }
        });
    },
    doSelect : function() {
        var sms = this.gridPanel.getSelectionModel().getSelections();
        this.fireEvent("submit", sms);
        this.hide();
    }
});