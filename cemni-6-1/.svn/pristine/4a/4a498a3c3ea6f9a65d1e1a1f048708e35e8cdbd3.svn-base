package com.huiju.inter.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;

import com.huiju.actment.activity.entity.ActGive;
import com.huiju.actment.activity.entity.ActStatus;
import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.actment.activity.logic.ActGiveRemote;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.actment.activity.logic.IndiPartInRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.console.dict.entity.Dict;
import com.huiju.console.org.entity.Org;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.inter.activity.entity.ActivityAudit;
import com.huiju.inter.activity.logic.ActivityAuditRemote;
import com.huiju.inter.httpclient.HttpClientRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.Sort;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.DESUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 活动主题审核
 * 
 * <pre>
 * {
 *     "activityNo": "AC20161230001",
 *     "amount": 200.33,
 *     "result": 1,
 *     "status": 2,
 *     "auditor": "李明",
 *     "auditTime": "2016-12-11 11:11:11",
 *     "suggest": "同意这个活动"
 * }
 * </pre>
 * 
 * @author：WangYuanJun
 * @date：2017年1月6日 下午1:20:24
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class ActivityAuditWsBean implements ActivityAuditWs {
    @EJB
    private ActivityRemote activityLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private ActivityAuditRemote activityAuditLogic;
    @EJB
    private HttpClientRemote httpLogic;
    @EJB
    private IndiPartInRemote indiPartInLogic;
    @EJB
    private ActGiveRemote ActGiveLogic;
    @EJB
    private OrgRemote appLogic;

    @Override
    @WebMethod
    @WebResult(name = "message")
    public String oa2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        message = DESUtils.getDesString(message);
        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            String activityNo = InterJsonParseUtils.parseString(json, "activityNo", "活动单号", errMap, false);
            String auditor = InterJsonParseUtils.parseString(json, "auditor", "审核人", errMap, false);
            Integer result = InterJsonParseUtils.parseInteger(json, "result", "审核结果", errMap, false);
            String suggest = InterJsonParseUtils.parseString(json, "suggest", "审核意见", errMap, false);
            Calendar auditTime = InterJsonParseUtils.parseCalendar(json, "auditTime", "审核时间", "yyyy-MM-dd HH:mm:ss", errMap, false);
            Double amount = InterJsonParseUtils.parseDouble(json, "amount", "审核费用总金额", errMap, false);
            Integer status = InterJsonParseUtils.parseInteger(json, "status", "审核状态", errMap, false);

            // 保存审核情况
            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("EQ_activityNo", activityNo);
            Activity dt = activityLogic.find(searchMap);
            if (dt == null) {
                errMap.put("activityNo", activityNo + "在crm系统不存在");
            }

            if (CollectionUtils.isEmpty(errMap)) {
                // 保存活动主题(审核状态改变)
                dt.setAuditStatus(ActStatus.values()[status]);

                ActivityAudit activityAudit = new ActivityAudit();
                activityAudit.setActivityNo(activityNo);
                activityAudit.setAuditor(auditor);
                activityAudit.setResult(result);
                activityAudit.setSuggest(suggest);
                activityAudit.setAuditTime(auditTime);
                activityAudit.setAmount(amount);
                activityAudit.setStatus(status);
                activityAuditLogic.persist(activityAudit);

                // 审核通过
                if (dt.getAuditStatus().ordinal() == ActStatus.PASS.ordinal()) {
                    dt.setAuditCost(amount);
                    Map<String, Object> map = null;

                    // 同步到wechar
                    Map actMap = new HashMap();
                    actMap.put("activityNo", dt.getActivityNo());
                    actMap.put("activityType", dt.getActivityType());
                    actMap.put("activityTheme", dt.getActivityTheme());
                    actMap.put("beginTime", NeuUtils.parseStringFromCalendar(dt.getBeginTime()));
                    actMap.put("endTime", NeuUtils.parseStringFromCalendar(dt.getEndTime()));
                    map = httpLogic.post(NeuUtils.getProperty("act_crm2wechar"), "Activity-crm@wechar", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(actMap));
                    if (Integer.parseInt(map.get("flag").toString()) == GlobalConst.SUCCESS) {
                        dt.setWecharCodeAddr(map.get("msg").toString());

                        // 同步到OA
                        int act_form = dt.getActivityForm();
                        if (act_form == GlobalConst.ACTIVITY_FORM_MJ) {
                            map = this.addevent(dt);
                        } else if (act_form == GlobalConst.ACTIVITY_FORM_MZ) {
                            map = this.addbuyevent(dt);
                        } else if (act_form == GlobalConst.ACTIVITY_FORM_JF) {
                            map = this.addjfbsevent(dt);
                        } else {
                            map = this.addotherevent(dt);
                        }
                        if (Integer.parseInt(map.get("flag").toString()) == GlobalConst.SUCCESS) {
                            flag = GlobalConst.SUCCESS;
                            msg = GlobalConst.TIP_SUCCESS;
                        } else {
                            // NC报错
                            flag = GlobalConst.FAIL;
                            msg = map.get("msg").toString();
                        }
                    } else {
                        // wechar报错
                        flag = GlobalConst.FAIL;
                        msg = map.get("msg").toString();
                    }
                } else {
                    flag = GlobalConst.SUCCESS;
                    msg = GlobalConst.TIP_SUCCESS;
                }
                // 修改活动信息
                activityLogic.merge(dt);
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("ActivityAuditWsBean.oa2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_OA);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

    private void variethorSeries(Activity dt, Map<String, Object> map) {
        List<String> list = new ArrayList<String>();
        // 适用品类
        if (StringUtils.isNotBlank(dt.getVariety()) && StringUtils.isBlank(dt.getSeries())) {
            String[] arrVariety = dt.getVariety().split(",");
            List<Dict> stockTypeList = DataDict.getSubDict(DataDict.STOCK_TYPE);

            for (String variety : arrVariety) {
                for (Dict dict : stockTypeList) {
                    if (dict.getValue() == Integer.parseInt(variety)) {
                        String[] arr = dict.getName().split("-");
                        if (arr.length == 2) {
                            list.add(arr[0]);
                        } else {
                            list.add(arr[2]);
                        }
                    }
                }
            }
            map.put("invclasscode", StringUtils.join(list.toArray(), ","));
            map.put("xlflname", "");
        } else if (StringUtils.isBlank(dt.getVariety()) && StringUtils.isNotBlank(dt.getSeries())) {
            // 适用系列
            String[] arrSeries = dt.getSeries().split(",");
            List<Dict> seriesList = DataDict.getSubDict(DataDict.SALESMENT_DESIGNERSTYLE);
            for (String series : arrSeries) {
                for (Dict dict : seriesList) {
                    if (dict.getValue() == Integer.parseInt(series)) {
                        list.add(dict.getName());
                    }
                }
            }
            map.put("xlflname", StringUtils.join(list.toArray(), ","));
            map.put("invclasscode", "");
        } else {
            map.put("invclasscode", "");
            map.put("xlflname", "");
        }
    }

    /**
     * 满减
     */
    private Map<String, Object> addevent(Activity dt) {
        Map map = new HashMap();
        map.put("action", "addevent");
        map.put("vdiscountcode", dt.getActivityNo());
        map.put("vdiscountname", dt.getActivityTheme());
        map.put("nfootlevelnum", dt.getLowerLimit());
        map.put("dbegindate", NeuUtils.parseStringFromCalendar(dt.getBeginTime()));
        map.put("denddate", NeuUtils.parseStringFromCalendar(dt.getEndTime()));
        map.put("ncheapnum", dt.getPreferential());

        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("EQ_activity_activityId", dt.getActivityId());
        String[] sorts = { "indiPartInId" + "," + Sort.Direction.ASC.name() };
        List<IndiPartIn> Inlist = indiPartInLogic.findAll(searchParam, sorts);
        if (!CollectionUtils.isEmpty(Inlist) && dt.getIsCreateCard() == GlobalConst.I_ONE) {
            map.put("beginkqno", Inlist.get(0).getCouponNo());
            map.put("endkqno", Inlist.get(Inlist.size() - 1).getCouponNo());
        } else {
            map.put("beginkqno", "");
            map.put("endkqno", "");
        }
        map.put("kqnum", dt.getCardAmount() == null ? 0 : dt.getCardAmount());
        Map<String, Object> searchParam1 = new HashMap<String, Object>();
        searchParam1.put("EQ_type", GlobalConst.ORG_TYPE_5);
        List<String> rslist = new ArrayList<String>();
        List<Org> orgList = appLogic.findAll(searchParam1);
        for (Org org : orgList) {
            rslist.add(org.getOrgCode());
        }
        List list = new ArrayList();
        String[] arrscope = dt.getActivityScope().split(",");
        for (String s : arrscope) {
            if (rslist.contains(s)) {
                list.add(s);
            }
        }
        map.put("csite", StringUtils.join(list.toArray(), ","));
        map.put("activitycontent", dt.getContent());
        variethorSeries(dt, map);

        return httpLogic.post(NeuUtils.getProperty("crm2nc"), "ActivityAuditWsBean.addevent", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(map));
    }

    /**
     * 买赠
     */
    private Map<String, Object> addbuyevent(Activity dt) {
        Map map = new HashMap();
        map.put("action", "addbuyevent");
        map.put("vdiscountcode", dt.getActivityNo());
        map.put("vdiscountname", dt.getActivityTheme());
        map.put("floormny", dt.getIntegralReward());
        map.put("dbegindate", NeuUtils.parseStringFromCalendar(dt.getBeginTime()));
        map.put("denddate", NeuUtils.parseStringFromCalendar(dt.getEndTime()));

        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("EQ_activity_activityId", dt.getActivityId());
        String[] sorts = { "indiPartInId" + "," + Sort.Direction.ASC.name() };
        List<IndiPartIn> Inlist = indiPartInLogic.findAll(searchParam, sorts);
        if (!CollectionUtils.isEmpty(Inlist) && dt.getIsCreateCard() == GlobalConst.I_ONE) {
            map.put("beginkqno", Inlist.get(0).getCouponNo());
            map.put("endkqno", Inlist.get(Inlist.size() - 1).getCouponNo());
        } else {
            map.put("beginkqno", "");
            map.put("endkqno", "");
        }
        map.put("kqnum", dt.getCardAmount() == null ? 0 : dt.getCardAmount());

        Map<String, Object> searchParam1 = new HashMap<String, Object>();
        searchParam1.put("EQ_type", GlobalConst.ORG_TYPE_5);
        List<String> rslist = new ArrayList<String>();
        List<Org> orgList = appLogic.findAll(searchParam1);
        for (Org org : orgList) {
            rslist.add(org.getOrgCode());
        }
        List list = new ArrayList();
        String[] arrscope = dt.getActivityScope().split(",");
        for (String s : arrscope) {
            if (rslist.contains(s)) {
                list.add(s);
            }
        }
        map.put("csite", StringUtils.join(list.toArray(), ","));
        map.put("activitycontent", dt.getContent());
        variethorSeries(dt, map);

        Map<String, Object> searchParamact = new HashMap<String, Object>();
        searchParamact.put("EQ_activity_activityId", dt.getActivityId());
        List<ActGive> agList = ActGiveLogic.findAll(searchParamact);
        if (!CollectionUtils.isEmpty(agList)) {
            List bodyvos = new ArrayList();
            for (ActGive actGive : agList) {
                Map<String, Object> mp = new HashMap<String, Object>();
                mp.put("invcode", actGive.getInvCode());
                mp.put("invname", actGive.getInvName());
                mp.put("nnumber", actGive.getGiveNum());
                mp.put("nprice", actGive.getNprice());
                bodyvos.add(mp);
            }
            map.put("bodyvos", bodyvos);
        }
        return httpLogic.post(NeuUtils.getProperty("crm2nc"), "ActivityAuditWsBean.addbuyevent", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(map));
    }

    /**
     * 其他活动
     */
    private Map<String, Object> addotherevent(Activity dt) {
        Map map = new HashMap();
        map.put("action", "addotherevent");
        map.put("vdiscountcode", dt.getActivityNo());
        map.put("vdiscountname", dt.getActivityTheme());
        map.put("dbegindate", NeuUtils.parseStringFromCalendar(dt.getBeginTime()));
        map.put("denddate", NeuUtils.parseStringFromCalendar(dt.getEndTime()));

        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("EQ_activity_activityId", dt.getActivityId());
        String[] sorts = { "indiPartInId" + "," + Sort.Direction.ASC.name() };
        List<IndiPartIn> Inlist = indiPartInLogic.findAll(searchParam, sorts);
        if (!CollectionUtils.isEmpty(Inlist) && dt.getIsCreateCard() == GlobalConst.I_ONE) {
            map.put("beginkqno", Inlist.get(0).getCouponNo());
            map.put("endkqno", Inlist.get(Inlist.size() - 1).getCouponNo());
        } else {
            map.put("beginkqno", "");
            map.put("endkqno", "");
        }
        map.put("kqnum", dt.getCardAmount() == null ? 0 : dt.getCardAmount());

        Map<String, Object> searchParam1 = new HashMap<String, Object>();
        searchParam1.put("EQ_type", GlobalConst.ORG_TYPE_5);
        List<String> rslist = new ArrayList<String>();
        List<Org> orgList = appLogic.findAll(searchParam1);
        for (Org org : orgList) {
            rslist.add(org.getOrgCode());
        }
        List list = new ArrayList();
        String[] arrscope = dt.getActivityScope().split(",");
        for (String s : arrscope) {
            if (rslist.contains(s)) {
                list.add(s);
            }
        }
        map.put("csite", StringUtils.join(list.toArray(), ","));
        map.put("activitycontent", dt.getContent());
        variethorSeries(dt, map);

        return httpLogic.post(NeuUtils.getProperty("crm2nc"), "ActivityAuditWsBean.addotherevent", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(map));
    }

    /**
     * 积分倍率
     */
    private Map<String, Object> addjfbsevent(Activity dt) {
        Map<String, Object> rmap = null;
        if (dt.getIntegralReward() > 2) {
            Map map = new HashMap();
            map.put("action", "addjfbsevent");
            map.put("jfbs", 2);
            rmap = httpLogic.post(NeuUtils.getProperty("crm2nc"), "ActivityAuditWsBean.addjfbsevent", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(map));
        } else if (dt.getIntegralReward() > 0) {
            Map map = new HashMap();
            map.put("action", "addjfbsevent");
            map.put("jfbs", dt.getIntegralReward());
            rmap = httpLogic.post(NeuUtils.getProperty("crm2nc"), "ActivityAuditWsBean.addjfbsevent", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(map));
        }
        return rmap;

    }

}