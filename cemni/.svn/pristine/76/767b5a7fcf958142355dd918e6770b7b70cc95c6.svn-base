package com.huiju.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.huiju.console.dict.entity.Dict;

/**
 * 数据字典
 * 
 * @author：yuhb
 * @date：2016年12月08日 上午10:30:20
 */
public class DataDict {
    public static Integer YES_OR_NOT = 9000;// 是/否
    public static Integer NONE = -1;// 字典表不存在的值

    public static Integer FRANCHISEE_SEX = 1100;// 性别
    public static Integer INDIVIDCUST_TYPE = 1101;// 客户类型
    public static Integer INDIVIDCUST_ENABLE = 1102;// 使用状态
    public static Integer INDIVIDCUST_ACTIVESTATUS = 1103;// 活跃状态
    public static Integer CUST_JOB = 1105;// 职业
    public static Integer CUST_INCOME = 1106;// 收入
    public static Integer CUST_BRANK_CHANNEL = 1107;// 品牌获知渠道
    public static Integer CUST_MOTIVES = 1108;// 购买目的
    public static Integer CUST_FACTOR = 1109;// 购买因素
    public static Integer CUST_BELIEF = 1110;// 信仰
    public static Integer CUST_REF_REL = 1111;// 与推荐人关系
    public static Integer CUST_AUDIT_STATUS = 1112;// 审核状态
    public static Integer CUST_AGE = 1113;// 年龄段
    public static Integer INDIVIDCUST_FRESH = 1114;// 新老会员
    public static Integer CUST_SRC_CHANNEL = 1115;// 来源渠道
    public static Integer CUST_PURPOSE_PRICE = 1116;// 意向价位
    public static Integer CUST_PURPOSE_PRODUCT = 1117;// 意向产品类型
    public static Integer CUST_PURPOSE_CATEGORY = 1118;// 意向产品品类

    public static Integer GROUPCUST_NATURE = 1201;// 单位性质
    public static Integer GROUPCUST_CATEGORY = 1200;// 行业类别

    public static Integer FRANCHISEE_TYPE = 1300;// 加盟商类型
    public static Integer FRANCHISEE_STATE = 1301;// 加盟商状态
    public static Integer FRANCHISEE_SOURCE = 1302;// 来源渠道
    public static Integer FRANCHISEE_RECORD = 1304;// 不良记录

    public static Integer SUPPLIER_GRADEASSESS = 1400;// 年度等级评估
    public static Integer CHANNEL_TYPE = 1500;// 渠道商类型
    public static Integer PARTNER_TYPE = 1600;// 异业伙伴类型

    public static Integer TELVISIT_BACKFS = 2101;// 回访方式
    public static Integer TELVISIT_SATISFACTION = 2104;// 回访满意度
    public static Integer TELVISIT_TASKTYPE = 2107;// 任务类型
    public static Integer TELVISIT_RETURNRECORD = 2108;// 优先回访记录单

    public static Integer SALESMENT_DESIGNERSTYLE = 2200;// 设计师款

    public static Integer LV_TYPE = 3100;// 会员等级
    public static Integer INTEGRALADJHIS_CREDITSTATUS = 3101;// 积分状态
    public static Integer GRADEADJHIS_MODTYPE = 3102;// 等级变更类型
    public static Integer INTEGRALADJHIS_MODTYPE = 3103;// 积分变更类型

    public static Integer RIGHTMAINT_HANDLESTATE = 4100;// 处理状态
    public static Integer RIGHTMAINT_COMPLAINTTYPE = 4101;// 投诉类型
    public static Integer RIGHTMAINT_PROBTYPE = 4102;// 问题类型
    public static Integer RIGHTMAINT_COMPLAINTLEVEL = 4103;// 投诉等级
    public static Integer RIGHTMAINT_REVIEWSTATE = 4104;// 审核状态
    public static Integer RIGHTMAINT_URGENCYLEVEL = 4105;// 紧急程度
    public static Integer RIGHTMAINT_RESULT = 4106;// 审核结果
    public static Integer BUSIREGIST_ACCEPTSTATE = 4200;// 受理状态
    public static Integer BUSIREGIST_BUSINESSTYPE = 4201;// 业务类型
    public static Integer CALLREGIST_VISITFORM = 4300;// 拜访形式
    public static Integer SALESMENT_YEAR = 5100;// 年度指标类型
    public static Integer SALESMENT_SCALE = 5201;// 分数段指标类型
    public static Integer ACTIVITY_TYPE = 6100;// 活动类型
    public static Integer ACTIVITY_FORM = 6101;// 活动形式
    public static Integer ACTIVITY_AUDIT_STATUS = 6102;// 审核状态
    public static Integer ACTIVITY_STATUS = 6103;// 活动状态

    public static Integer SALESMANANALYZE_TYPE = 7100;// 业绩类别
    public static Integer AGENTANALYZE_TYPE = 7200;// 指标类别
    public static Integer MEMCOMPANALYZE_TYPE = 2700;// 指标选择
    public static Integer FRANCHISEEAUDIT_AUDITPROBNUM = 7500;// 稽核问题数量
    public static Integer CONTRACT_CONTRACT = 6400;// 合同管理
    public static Integer CONTRACT_LINKMAN_TYPE = 6410;// 合同联系人类型
    public static Integer ORG_TYPE = 8100;// 组织机构类型
    public static Integer STORE_ATTR = 8400;// 门店属性
    public static Integer STORE_FORM = 8401;// 门店形态

    public static Integer SYS_SCR = 9100;// 系统来源

    public static Integer SALEORDER_TYPE = 9500;// 订单类型

    public static Integer SCORE_SEGMENT = 9800;// 钻石-分数段
    public static Integer STOCK_TYPE = 9801;// 存货分类
    public static Integer POS_BILLTYPE = 9802;// pos单据类型

    public static Integer COMPETITOR_CPNAME = 2301;// 竞争对手

    /**
     * 短信类型
     */
    public static Integer SMS_TYPE = 8000;
    /**
     * 短信发送类型
     */
    public static Integer SMS_SEND_TYPE = 8001;
    /**
     * 短信发送-循环发送-类型
     */
    public static Integer SMS_SEND_CYCLE_TYPE = 8002;
    /**
     * 客情维护-循环发送时间
     */
    public static Integer SMS_CYCLE_CUSTREL = 8003;
    /**
     * 保养短信-循环发送时间
     */
    public static Integer SMS_CYCLE_MAINTAIN = 8005;

    // //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 存储数据字典对象
     */
    private static LinkedHashMap<Integer, List<Dict>> m_linkedHashMap = null;// 所有的dictCodeMap
    private static LinkedHashMap<Long, List<Dict>> p_linkedHashMap = null;// 父节点的parentIdMap

    /**
     * 加载数据字典
     */
    public static void loadDict(List<Dict> dictList) {
        m_linkedHashMap = new LinkedHashMap<Integer, List<Dict>>();
        p_linkedHashMap = new LinkedHashMap<Long, List<Dict>>();

        List<Dict> sub_list;
        List<Dict> p_list;
        if (null != dictList && !dictList.isEmpty()) {
            for (Dict dt : dictList) {
                sub_list = (List<Dict>) m_linkedHashMap.get(dt.getDictCode());
                if (null == sub_list) {
                    sub_list = new ArrayList<Dict>();
                }
                sub_list.add(dt);
                m_linkedHashMap.put(dt.getDictCode(), sub_list);

                if (null != dt.getParentId()) {
                    p_list = (List<Dict>) p_linkedHashMap.get(dt.getParentId());
                    if (null == p_list) {
                        p_list = new ArrayList<Dict>();
                    }
                    p_list.add(dt);
                    p_linkedHashMap.put(dt.getParentId(), p_list);
                }
            }
        }
    }

    /**
     * 根据 dictCode 获取字典类型的 list
     */
    public static List<Dict> getSubDict(Integer dictCode) {
        return m_linkedHashMap.get(dictCode);
    }

    /**
     * 根据 parentId 获取字典类型的 list（字典表级联使用）
     */
    public static List<Dict> getDictByParentId(Long parentId) {
        return p_linkedHashMap.get(parentId);
    }

    /**
     * 根据“字典类型、字典值”获取字典名称
     */
    public static String getDictName(Integer dictCode, Integer value) {
        return getDictName(dictCode, value, "");
    }

    /**
     * 根据“字典类型、字典值”获取字典名称，没有时返回默认值
     */
    public static String getDictName(Integer dictCode, Integer value, String defStr) {
        if (value != null) {
            List<Dict> sub_list = m_linkedHashMap.get(dictCode);
            if (null != sub_list) {
                for (Dict dt : sub_list) {
                    if (dt.getValue().intValue() == value) {
                        return dt.getName();
                    }
                }
            }
        }
        return defStr;
    }

    /**
     * 根据“字典类型、字典名称”获取字典value，没有时返回GlobalConst.ZERO
     */
    public static Integer getDictValue(Integer dictCode, String dictName) {
        List<Dict> sub_list = m_linkedHashMap.get(dictCode);
        if (null != sub_list) {
            for (Dict dt : sub_list) {
                if (dt.getName().equals(dictName)) {
                    return dt.getValue();
                }
            }
        }
        return GlobalConst.ZERO;
    }

}