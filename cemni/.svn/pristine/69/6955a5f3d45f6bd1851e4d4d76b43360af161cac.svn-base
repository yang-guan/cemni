var dealUrl = path + '/GradeRule';
var cfg = {
    isAudit : false,
    dealUrl : dealUrl,
    moduleName : '会员等级规则',
    playListMode : playListMode.normal,
    vp : {
        hideSubTab : true,
        subTab : [],
        hideBtn : [],
        listEditStateFn : [],
        store : {
            idProperty : "gradeRuleId",
            url : dealUrl + '_getJson.action',
            sort : "jewerlyAmount",
            dir : "desc"
        },
        column : [ {
            dataIndex : "gradeRuleId",
            hidden : true
        }, {
            header : "会员等级",
            dataIndex : "lvName"
        }, {
            header : "条件",
            dataIndex : "requirement"
        }, {
            header : "珠宝折算额",
            dataIndex : "jewerlyAmount"
        }, {
            header : "折扣",
            dataIndex : "discount"
        }, {
            header : "免费调换次数",
            dataIndex : "exchangeChance"
        }, {
            header : "免费改款次数",
            dataIndex : "restructureChance"
        } ]
    }
};