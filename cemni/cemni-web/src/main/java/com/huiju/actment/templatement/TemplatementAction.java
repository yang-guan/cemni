package com.huiju.actment.templatement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huiju.actment.activity.entity.ActStatus;
import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.entity.ExpectCost;
import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.actment.activity.entity.ParPartIn;
import com.huiju.actment.activity.entity.Scope;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.afterservice.rightmaint.entity.RecordInfo;
import com.huiju.afterservice.rightmaint.entity.RightStatus;
import com.huiju.afterservice.rightmaint.logic.RecordInfoRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.dict.entity.Dict;
import com.huiju.console.org.entity.Org;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TemplatementAction extends BaseAction<Activity, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ActivityRemote activityLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private OrgRemote orgLogic;
    @EJB
    private RecordInfoRemote recordInfoLogic;

    public String init() throws Exception {
        List<String> jsArr = new ArrayList<String>();
        jsArr.add("/js/actment/activity/Q.deptSelect.js");
        jsArr.add("/js/actment/activity/Q.orgSelect.js");
        jsArr.add("/js/actment/activity/Q.actment.judgeActWin.js");
        jsArr.add("/js/common/Q.excel.uploadWin.js");
        jsArr.add("/js/archive/franchisee/Q.archive.chooseFranWin.js");
        jsArr.add("/js/actment/activity/Q.actment.choosePartWin.js");
        jsArr.add("/plugins/UploadDialog/Neu.ux.UploadDialog.js");
        jsArr.add("/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js");
        jsArr.add("/js/console/org/Q.org.chooseOrgWin.js");
        jsArr.add("/js/actment/activity/Q.actment.custBatchSearchWin.js");
        jsArr.add("/js/console/dict/Q.dict.chooseDictWin.js");
        jsArr.add("/js/actment/activity/Q.actment.searchWin.js");
        jsPath.add("/js/actment/templatement/Q.actment.templatement.js");
        jsPath.addAll(jsArr);

        String[] authorities = { "D_TEMPLATEMENT_LIST", "D_TEMPLATEMENT_SEARCH", "D_TEMPLATEMENT_LAUNCHACT" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void edit() {
        model = activityLogic.findById(id);

        // 使用范围
        List<String> scopeNameList = new ArrayList<String>();
        Map searchParam = new HashMap();
        searchParam.put("IN_orgCode", model.getActivityScope());
        List<Org> orgList = orgLogic.findAll(searchParam);
        for (Org dt : orgList) {
            scopeNameList.add(dt.getName());
        }
        model.setActivityScopeName(StringUtils.join(scopeNameList.toArray(), ","));

        // 协助部门
        List<String> assistDeptNameList = new ArrayList<String>();
        Map searchParam1 = new HashMap();
        searchParam1.put("IN_orgCode", model.getAssistDept());
        List<Org> orgList1 = orgLogic.findAll(searchParam1);
        for (Org dt : orgList1) {
            assistDeptNameList.add(dt.getName());
        }
        model.setAssistDeptName(StringUtils.join(assistDeptNameList.toArray(), ","));

        // 存货分类
        String varietyStr = model.getVariety();
        if (StringUtils.isNotBlank(varietyStr)) {
            List<String> varietyNameList = new ArrayList<String>();
            List<Dict> stockTypeList = DataDict.getSubDict(DataDict.STOCK_TYPE);

            String[] varietyArr = varietyStr.split(",");
            for (String variety : varietyArr) {
                for (Dict dt : stockTypeList) {
                    if (dt.getValue() == Integer.parseInt(variety)) {
                        varietyNameList.add(dt.getName());
                    }
                }
            }
            model.setVarietyName(StringUtils.join(varietyNameList.toArray(), ","));
        }

        // 设计师款
        String seriesStr = model.getSeries();
        if (StringUtils.isNotBlank(seriesStr)) {
            List<String> seriesNameList = new ArrayList<String>();
            List<Dict> seriesList = DataDict.getSubDict(DataDict.SALESMENT_DESIGNERSTYLE);
            String[] seriesArr = seriesStr.split(",");
            for (String series : seriesArr) {
                for (Dict dt : seriesList) {
                    if (dt.getValue() == Integer.parseInt(series)) {
                        seriesNameList.add(dt.getName());
                    }
                }
            }
            model.setSeriesName(StringUtils.join(seriesNameList.toArray(), ","));
        }
        dealJson(true, model);
    }

    public void update() {
        saveActivityScope();
        model.setActivityNo(sqlLogic.getCnNum(GlobalConst.NUM_ACTIVITY));
        model.setActivityId(null);
        model.setLaunchor(WebUtils.getUserName());
        model.setLaunchorCode(WebUtils.getUserCode());
        if ("audit".equals(this.submitFlag)) {
            model.setAuditStatus(ActStatus.CONFIRM);

            // 记录提交信息
            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setCardNumber(model.getActivityNo());
            recordInfo.setCheckDate(Calendar.getInstance());
            recordInfo.setCheckUserId(WebUtils.getUserId());
            recordInfo.setCheckUserName(WebUtils.getUserName());
            recordInfo.setCheckResult(RightStatus.CONFIRM.ordinal());
            recordInfoLogic.persist(recordInfo);
        } else {
            model.setAuditStatus(ActStatus.NEW);

            // 记录新建信息
            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setCardNumber(model.getActivityNo());
            recordInfo.setCheckDate(model.getCreateDate());
            recordInfo.setCheckUserId(WebUtils.getUserId());
            recordInfo.setCheckUserName(WebUtils.getUserName());
            recordInfo.setCheckResult(RightStatus.NEW.ordinal());
            recordInfoLogic.persist(recordInfo);
        }
        this.setOneToManyValue();

        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model.setTemplate(GlobalConst.NO);
        //        saveActivityScope();
        model.setJudgeAct(null);

        //        // 参与会员 
        //        List<IndiPartIn> list1 = model.getIndiPartIn();
        //        if (!CollectionUtils.isEmpty(list1)) {
        //            // 生成卡券编码
        //            if (model.getIsCreateCard().intValue() == GlobalConst.YES) {
        //                for (IndiPartIn dt : list1) {
        //                    dt.setIsPartIn(GlobalConst.NO);
        //                    // 生成卡券编码
        //                    dt.setCouponNo(model.getActivityNo() + activityLogic.getNextSequence().toString());
        //                }
        //                model.setCardAmount(Long.valueOf(list1.size()));
        //            } else {
        //                model.setCardAmount(null);
        //            }
        //        }

        List<FraPartIn> list2 = model.getFraPartIn();
        if (!CollectionUtils.isEmpty(list2)) {
            for (FraPartIn dt : list2) {
                dt.setIsPartIn(GlobalConst.NO);
            }
        }
        List<ParPartIn> list3 = model.getParPartIn();
        if (!CollectionUtils.isEmpty(list3)) {
            for (ParPartIn dt : list3) {
                dt.setIsPartIn(GlobalConst.NO);
            }
        }
        //请求参数
        String indiPartInParam = String.valueOf(model.getIndiPartInParam());

        if (null == indiPartInParam || "".equals(indiPartInParam)) {

            List<IndiPartIn> list1 = model.getIndiPartIn();
            if (!CollectionUtils.isEmpty(list1)) {

                if (model.getIsCreateCard().intValue() == GlobalConst.YES) {
                    for (IndiPartIn dt : list1) {
                        dt.setIsPartIn(GlobalConst.NO);
                        dt.setCouponNo(model.getActivityNo() + activityLogic.getSeqActCouponNo());// 生成卡券编码
                        dt.setIsUsed(GlobalConst.NO);
                    }
                    model.setCardAmount(Long.valueOf(list1.size()));
                } else {
                    for (IndiPartIn dt : list1) {
                        dt.setIsPartIn(GlobalConst.NO);
                        dt.setIsUsed(GlobalConst.NO);
                    }
                    model.setCardAmount(null);
                }
            }
            saveActivityScope();
            activityLogic.persist(model);
        } else {
            model.setIndiPartIn(null);
            saveActivityScope();
            if (indiPartInParam.contains("excel")) {
                List<JSONObject> jsonList = null;
                List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
                Map<String, Object> map = null;

                indiPartInParam = indiPartInParam.substring(5);

                jsonList = JSONArray.parseObject(indiPartInParam, List.class);

                for (JSONObject object : jsonList) {
                    map = JSONArray.parseObject(object.toString(), Map.class);
                    paramsList.add(map);

                }

                activityLogic.saveActivityByExcel(model, paramsList);

            } else {
                Map<String, Object> searchParams = JSONArray.parseObject(indiPartInParam, Map.class);

                Map<String, Object> paramsMap = new HashMap<String, Object>();
                Iterator ite = searchParams.keySet().iterator();
                String key = "";
                while (ite.hasNext()) {
                    key = (String) ite.next();
                    if (key.contains("filter_")) {
                        if (null != searchParams.get(key) && !"".equals(searchParams.get(key))) {
                            paramsMap.put(key.replace("filter_", ""), searchParams.get(key));
                        }
                    }

                }
                if (null == paramsMap.get("IN_belongStoreNo") || "".equals(paramsMap.get("IN_belongStoreNo"))) {
                    paramsMap.put("IN_belongStoreNo", String.valueOf(model.getActivityScope()));
                }

                Object GE_lastBuyTime = paramsMap.get("GE_lastBuyTime");
                if (GE_lastBuyTime != null && !StringUtils.isEmpty(GE_lastBuyTime.toString())) {
                    Calendar cl = NeuUtils.parseCalendar(GE_lastBuyTime.toString());
                    paramsMap.put("GE_lastBuyTime", NeuUtils.parseStringFromCalendar(cl));
                }
                Object LE_lastBuyTime = paramsMap.get("LE_lastBuyTime");
                if (LE_lastBuyTime != null && !StringUtils.isEmpty(LE_lastBuyTime.toString())) {
                    Calendar cl2 = NeuUtils.parseCalendar(LE_lastBuyTime.toString());
                    cl2.add(Calendar.DATE, 1);
                    paramsMap.put("LE_lastBuyTime", NeuUtils.parseStringFromCalendar(cl2));
                }
                Object GE_birthday = paramsMap.get("GE_birthday");
                if (GE_birthday != null && !StringUtils.isEmpty(GE_birthday.toString())) {
                    Calendar cl = NeuUtils.parseCalendar(GE_birthday.toString());
                    paramsMap.put("GE_birthday", NeuUtils.parseStringFromCalendar(cl));
                }
                Object LE_birthday = paramsMap.get("LE_birthday");
                if (LE_birthday != null && !StringUtils.isEmpty(LE_birthday.toString())) {
                    Calendar cl2 = NeuUtils.parseCalendar(LE_birthday.toString());
                    paramsMap.put("LE_birthday", NeuUtils.parseStringFromCalendar(cl2));
                }

                activityLogic.saveActivityByQuery(model, paramsMap);
            }

        }

        dealJson(true);
    }

    private void saveActivityScope() {
        String activityScope = model.getActivityScope();
        if (StringUtils.isNotBlank(activityScope)) {
            String[] arrActivityScope = activityScope.split(",");
            List<Scope> scopes = new ArrayList<Scope>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (String orgCode : arrActivityScope) {
                Scope scope = new Scope();
                scope.setActivity(model);
                map.put("EQ_orgCode", orgCode);
                Org org = orgLogic.find(map);
                org.setOrgCode(orgCode);
                scope.setOrg(org);
                scope.setOrgCode(org.getOrgCode());
                scopes.add(scope);
            }
            model.setScope(scopes);
        } else {
            model.setScope(null);
        }
    }

    private void setOneToManyValue() {
        // 当前时间和“开始时间、结束时间”比较判断状态
        Calendar beginTime = model.getBeginTime();
        Calendar endTime = (Calendar) model.getEndTime().clone();// 克隆防止修改model值
        endTime.add(Calendar.DATE, 1);
        Calendar curTime = Calendar.getInstance();
        if (curTime.before(beginTime)) {
            model.setStatus(GlobalConst.ACT_STATUS_1);
        } else if (curTime.after(beginTime) && curTime.before(endTime)) {
            model.setStatus(GlobalConst.ACT_STATUS_2);
        } else if (curTime.after(endTime)) {
            model.setStatus(GlobalConst.ACT_STATUS_3);
        }

        List<FraPartIn> fraList = this.model.getFraPartIn();
        List<ParPartIn> parList = this.model.getParPartIn();
        List<IndiPartIn> indiList = this.model.getIndiPartIn();
        List<ExpectCost> exCost = this.model.getExpectCost();

        if (!CollectionUtils.isEmpty(indiList)) {
            for (IndiPartIn dt : indiList) {
                dt.setActivity(model);
                dt.setIndiPartInId(null);
                if (dt.getIsPartIn() == null) {
                    dt.setIsPartIn(GlobalConst.NO);
                }
            }
        }
        if (!CollectionUtils.isEmpty(fraList)) {
            for (FraPartIn dt : fraList) {
                dt.setActivity(model);
                dt.setFraPartInId(null);
                if (dt.getIsPartIn() == null) {
                    dt.setIsPartIn(GlobalConst.NO);
                }
            }
        }
        if (!CollectionUtils.isEmpty(parList)) {
            for (ParPartIn dt : parList) {
                dt.setActivity(model);
                dt.setParPartInId(null);
                if (dt.getIsPartIn() == null) {
                    dt.setIsPartIn(GlobalConst.NO);
                }
            }
        }
        if (!CollectionUtils.isEmpty(exCost)) {
            for (ExpectCost dt : exCost) {
                dt.setActivity(model);
                dt.setExpectCostId(null);
            }
        }
    }

}