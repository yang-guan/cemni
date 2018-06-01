// 加盟商等级指标
Ext.ns('Q.custGradeStandard');
var dealUrl = path + '/CustGradeStandard';
Q.loadJs(path + "/js/archive/franchisee/Q.archive.chooseFranWin.js");

// 列表
var gridColumn = [ {
    header : "加盟商名称",
    width : 256,
    sortable : false,
    dataIndex : "franame"
}, {
    header : "加盟商类型",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "fratype"
}, {
    header : "有无担保",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "guarantee"
}, {
    header : "是否股东",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "shares"
}, {
    header : "首批欠款记录",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "firstarrears"
}, {
    header : "补货欠款记录",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "addarrears"
}, {
    header : "稽查问题整改率",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "auditprobnum"
}, {
    header : "整体参与占比",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "brand_grade"
}, {
    header : "整体参与占比",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "zhangshang_grade"
}, {
    header : "整体参与占比",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "nahuo_grade"
}, {
    header : "整体参与占比",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "peixun_grade"
}, {
    header : "当月新店首批拿货",
    width : 120,
    sortable : false,
    align : "center",
    dataIndex : "fpickman"
}, {
    header : "当月业绩达成率",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "month_goods"
}, {
    header : "累计年度整体业绩达成率",
    width : 160,
    sortable : false,
    align : "center",
    dataIndex : "total_goods"
}, {
    header : "年度累计开店数量",
    width : 120,
    sortable : false,
    align : "center",
    dataIndex : "month_shops"
}, {
    header : "总分",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "total_grade"
}, {
    header : "级别",
    width : 100,
    sortable : false,
    align : "center",
    dataIndex : "level"
} ];

var rows = new Ext.ux.grid.ColumnHeaderGroup({
    rows : [ [ {
        header : "",
        sortable : false,
        colspan : 4
    }, {
        header : "客户信用",
        sortable : false,
        align : "center",
        colspan : 5
    }, {
        header : "品牌活动",
        sortable : false,
        align : "center",
        colspan : 1
    }, {
        header : "招商活动",
        sortable : false,
        align : "center",
        colspan : 1
    }, {
        header : "拿货会",
        sortable : false,
        align : "center",
        colspan : 1
    }, {
        header : "培训会",
        sortable : false,
        align : "center",
        colspan : 1
    }, {
        header : "业绩指标",
        sortable : false,
        align : "center",
        colspan : 4
    }, {
        header : "",
        sortable : false,
        colspan : 2
    } ] ]
});

Q.custGradeStandard.tbarBnt = [ "-", {
    index : 6,
    id : "EQ_fraNo",
    xtype : 'hidden'
}, {
    id : "EQ_fraName",
    index : 7,
    xtype : "textfield",
    emptyText : "加盟商名称",
    width : 150,
    listeners : {
        render : function(p) {
            p.getEl().on('mouseover', function(p1) {
                if (!p.getValue()) {
                    Ext.QuickTips.unregister(p.el);
                } else {
                    Ext.QuickTips.register({
                        target : p.el,
                        text : p.getValue()
                    });
                }
            });
            p.getEl().on('click', function(p) {
                var _win = new Q.archive.chooseFranWin({
                    singleSelect : false
                });
                _win.on("submit", function(r) {
                    var codeArr = [];
                    var nameArr = [];
                    Q.each(r, function() {
                        codeArr.push(this.data.fraCode);
                        nameArr.push(this.data.fraName);
                    });
                    Ext.getCmp('EQ_fraName').setValue(nameArr.join(","));
                    Ext.getCmp('EQ_fraNo').setValue(codeArr.join(","));
                });
                _win.show();
            });
        }
    }
}, {
    index : 8,
    xtype : "datefield",
    emptyText : "开始时间",
    id : "sdate",
    editable : false,
    width : 100,
    value : Ext.util.Format.date(new Date(), "Y-m") + "-01",
    format : 'Y-m-d',
    listeners : {
        select : function() {
            var sdate = Ext.getCmp('sdate').getValue();
            var edate = Ext.getCmp("edate").getValue();

            var syear = sdate.getFullYear();
            var smonth = sdate.getMonth();

            var eyear = edate.getFullYear();
            var emonth = edate.getMonth();
            if (syear != eyear) {
                Q.tips(Q.color(("日期不能跨年"), "red"));
            }
            if (smonth != emonth) {
                Q.tips(Q.color(("日期不能跨月"), "red"));

            }
            if (sdate && edate) {
                if (sdate > edate) {
                    Q.warning("开始日期不能大于结束日期");
                }
            }
        }
    }
}, {
    index : 9,
    xtype : "datefield",
    emptyText : "至",
    id : "edate",
    editable : false,
    width : 100,
    value : new Date(),
    format : 'Y-m-d',
    listeners : {
        select : function() {
            var sdate = Ext.getCmp('sdate').getValue();
            var edate = Ext.getCmp("edate").getValue();

            var syear = sdate.getFullYear();
            var smonth = sdate.getMonth();

            var eyear = edate.getFullYear();
            var emonth = edate.getMonth();
            if (syear != eyear) {
                Q.tips(Q.color(("日期不能跨年"), "red"));
            }
            if (smonth != emonth) {
                Q.tips(Q.color(("日期不能跨月"), "red"));
            }
            if (sdate && edate) {
                if (sdate > edate) {
                    Q.warning("结束日期不能小于开始日期");
                    Ext.getCmp('edate').setValue("");
                }
            }
        }
    }
}, {
    index : 10,
    text : "查询",
    iconCls : "icon-search",
    handler : function() {
        var _store = vp.grid.getStore();

        var sdate = Ext.getCmp('sdate').getValue();
        var edate = Ext.getCmp("edate").getValue();

        if ("" == sdate) {
            Q.tips(Q.color(("请选择开始日期"), "red"));
            return;
        }

        if ("" == edate) {
            Q.tips(Q.color(("请选择结束日期"), "red"));
            return;
        }

        if (sdate && edate) {
            if (sdate > edate) {
                Q.tips(Q.color(("结束日期不能小于开始日期"), "red"));
                return;
            }
        }

        var syear = sdate.getFullYear();
        var smonth = sdate.getMonth();

        var eyear = edate.getFullYear();
        var emonth = edate.getMonth();
        if (syear != eyear) {
            Q.tips(Q.color(("日期不能跨年"), "red"));
            return;
        }
        if (smonth != emonth) {
            Q.tips(Q.color(("日期不能跨月"), "red"));
            return;
        }
        _store.baseParams = {
            filter_EQ_sdate : Ext.util.Format.date(Ext.getCmp('sdate').getValue(), 'Y-m-d'),
            filter_EQ_edate : Ext.util.Format.date(Ext.getCmp('edate').getValue(), 'Y-m-d'),
            filter_EQ_fraNo : Ext.getCmp('EQ_fraNo').getValue()
        };
        _store.load();
    }
}, {
    index : 11,
    text : "导出",
    iconCls : "icon-excel",
    build : power['export'],
    handler : function() {
        var _store = vp.grid.getStore();
        var totalCount = _store.getTotalCount();

        if (totalCount == 0) {
            Q.tips(Q.color("请先查询出结果在进行导出", "red"));
            return;
        } else if (totalCount > 5000) {
            Q.tips(Q.color("每次导出上限为5000", "red"));
            return;
        }
        window.open(dealUrl + "_export.action?jasperFile=PerforamtdtlTrend&reportFileType=XLSX&" + Ext.urlEncode(_store.baseParams));
    }
} ];

var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '销售指标',
    playListMode : playListMode.normal,
    vp : {
        addOtherBtn : Q.custGradeStandard.tbarBnt,
        viewConfig : {
            forceFit : false
        },
        hideSubTab : true,
        plugins : rows,
        column : gridColumn,
        subTab : [],
        listEditStateFn : [],
        store : {
            url : dealUrl + '_query.action',
            autoLoad : false
        }
    }
};