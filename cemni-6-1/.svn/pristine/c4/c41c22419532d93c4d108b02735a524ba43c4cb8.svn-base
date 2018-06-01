Ext.ns('Q.afterservice');
Q.afterservice.chooseOrderWin = function(config) {
    var _gridPanel = this.gridPanel = this.createGridPanel(config);
    this.addEvents("submit");

    var _config = this.config = Ext.apply({
        title : "POS单-选择",
        layout : "fit",
        items : [ _gridPanel ],
        width : 700,
        height : 450
    }, config || {});
    Q.afterservice.chooseOrderWin.superclass.constructor.call(this, _config);
};

Ext.extend(Q.afterservice.chooseOrderWin, Ext.ux.Window, {
    listeners : {
        hide : function() {
            this.gridPanel.getSelectionModel().clearSelections();
        }
    },
    createGridPanel : function(cfg) {
        var _win = this;
        return new Ext.ux.grid.GridPanel({
            sm : {
                singleSelect : true
            },
            viewConfig : {
                forceFit : false
            },
            cm : {
                columns : [ {
                    dataIndex : "posId",
                    hidden : true
                }, {
                    header : "POS单号",
                    dataIndex : "posNo"
                }, {
                    header : "POS日期",
                    dataIndex : "posbillDate",
                    renderer : Q.common.dateRenderer
                }, {
                    header : "购买产品",
                    dataIndex : "goodsName"
                }, {
                    header : "商品条码",
                    dataIndex : "goodsBar"
                }, {
                    header : "商品编码",
                    dataIndex : "goodsNo"
                }, {
                    header : "购买数量",
                    dataIndex : "goodsCnt"
                }, {
                    header : "购买单价",
                    dataIndex : "goodsPrice"
                }, {
                    header : "折扣",
                    dataIndex : "discount"
                }, {
                    header : "购买金额",
                    dataIndex : "actualSaleAmount"
                }, {
                    header : "珠宝折算额",
                    dataIndex : "jewelDiscountAmount"
                }, {
                    header : "商品所属分类最高级编码",
                    dataIndex : "goodsClassHighestNo",
                    width : 150
                }, {
                    header : "设计师款",
                    dataIndex : "designerStyleName"
                }, {
                    header : "产品分数",
                    dataIndex : "scoreSegmentName"
                }, {
                    header : "系列分类",
                    dataIndex : "seriesTypeName"
                }, {
                    header : "存货分类",
                    dataIndex : "stockTypeName",
                    width : 200
                }, {
                    header : "门店名称",
                    dataIndex : "storeName",
                    width : 200
                }, {
                    header : "门店编码",
                    dataIndex : "storeNo"
                }, {
                    header : "主营业员姓名",
                    dataIndex : "mainclerkName"
                }, {
                    header : "主营业员比例",
                    dataIndex : "mainclerkPercent"
                }, {
                    header : "主营业员金额",
                    dataIndex : "mainclerkAmount"
                }, {
                    header : "副营业员姓名",
                    dataIndex : "assistantName"
                }, {
                    header : "副营业员比例",
                    dataIndex : "assistantPercent"
                }, {
                    header : "副营业额金额",
                    dataIndex : "assistantAmount"
                }, {
                    header : "消费获取积分",
                    dataIndex : "consumeIntegral"
                }, {
                    header : "推荐人获取积分",
                    dataIndex : "refGetIntegral"
                }, {
                    header : "推荐人CRM卡号",
                    dataIndex : "refCrmCardNo"
                }, {
                    header : "推荐人NC卡号",
                    dataIndex : "refNcCardNo"
                }, {
                    header : "推荐人手机号码",
                    dataIndex : "refMobile"
                }, {
                    header : "积分来源",
                    dataIndex : "integralSrcName"
                }, {
                    header : "活动编码",
                    dataIndex : "actNo"
                }, {
                    header : "活动主题",
                    dataIndex : "subject"
                } ]
            },
            store : {
                url : path + "/PosOrder_getJson.action?filter_EQ_cardNo=" + cfg.cardNo,
                sort : "posId",
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
            } ]
        });
    },
    doSelect : function() {
        var sms = this.gridPanel.getSelectionModel().getSelections();
        if (sms.length == 0) {
            Q.tips(Q.color($("message.pleaseSelectFirst"), "red"));
            return;
        }
        this.hide();
        this.fireEvent("submit", sms[0]);
    }
});