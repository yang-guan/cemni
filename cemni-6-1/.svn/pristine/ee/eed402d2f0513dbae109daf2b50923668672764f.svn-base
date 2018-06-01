package com.huiju.actment.activity;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huiju.actment.activity.entity.ActGive;
import com.huiju.actment.activity.entity.ActStatus;
import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.entity.ExpectCost;
import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.actment.activity.entity.ParPartIn;
import com.huiju.actment.activity.entity.Scope;
import com.huiju.actment.activity.logic.ActGiveRemote;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.actment.activity.logic.ExpectCostRemote;
import com.huiju.actment.activity.logic.FraPartInRemote;
import com.huiju.actment.activity.logic.IndiPartInRemote;
import com.huiju.actment.activity.logic.JudgeActRemote;
import com.huiju.actment.activity.logic.ParPartInRemote;
import com.huiju.actment.activity.logic.ScopeRemote;
import com.huiju.afterservice.rightmaint.entity.RecordInfo;
import com.huiju.afterservice.rightmaint.entity.RightStatus;
import com.huiju.afterservice.rightmaint.logic.RecordInfoRemote;
import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.archive.franchisee.logic.FranchiseeRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.archive.partner.entity.Partner;
import com.huiju.archive.partner.logic.PartnerRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.dict.entity.Dict;
import com.huiju.console.org.entity.Org;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.inter.activity.entity.ActivityAudit;
import com.huiju.inter.activity.logic.ActivityAuditRemote;
import com.huiju.inter.httpclient.HttpClientRemote;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.inter.saleorder.logic.SaleOrderRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.Sort;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ActivityAction extends BaseAction<Activity, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;
    @EJB
    private ActivityRemote activityLogic;
    @EJB
    private FraPartInRemote fraPartInLogic;
    @EJB
    private ParPartInRemote parPartInLogic;
    @EJB
    private FranchiseeRemote franchiseeLogic;
    @EJB
    private PartnerRemote partnerLogic;
    @EJB
    private ExpectCostRemote expectCostLogic;
    @EJB
    private IndiPartInRemote indiPartInLogic;
    @EJB
    private SaleOrderRemote saleOrderLogic;
    @EJB
    private PosOrderRemote posOrderLogic;
    @EJB
    private ActivityAuditRemote activityAuditLogic;
    @EJB
    private JudgeActRemote judgeActLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private HttpClientRemote httpLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private OrgRemote orgLogic;
    @EJB
    private RecordInfoRemote recordInfoLogic;
    @EJB
    private User2orgRemote user2orgLogic;
    @EJB
    private FileInfoRemote fileInfoLogic;
    @EJB
    private ScopeRemote scopeLogic;
    @EJB
    private ActGiveRemote actGiveLogic;

    private File file;
    private String fileName;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String init() throws Exception {
        List<String> jsArr = new ArrayList<String>();
        jsArr.add("/js/actment/activity/Q.deptSelect.js");
        jsArr.add("/js/actment/activity/Q.orgSelect.js");
        jsArr.add("/js/actment/activity/Q.actment.judgeActWin.js");
        jsArr.add("/js/actment/activity/Q.actment.actualCostWin.js");
        jsArr.add("/js/common/Q.excel.uploadWin.js");
        jsArr.add("/js/console/dict/Q.dict.chooseDictWin.js");
        jsArr.add("/js/archive/franchisee/Q.archive.chooseFranWin.js");
        jsArr.add("/js/actment/activity/Q.actment.choosePartWin.js");
        jsArr.add("/js/console/org/Q.org.chooseOrgWin.js");
        jsArr.add("/plugins/UploadDialog/Neu.ux.UploadDialog.js");
        jsArr.add("/js/actment/activity/Q.actment.custBatchSearchWin.js");
        jsArr.add("/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js");
        jsArr.add("/js/actment/activity/Q.actment.searchWin.js");
        jsArr.add("/js/actment/activity/Q.actment.activity.js");
        jsPath.addAll(jsArr);

        String[] authorities = { "D_ACTIVITY_LIST", "D_ACTIVITY_ADD", "D_ACTIVITY_EDIT", "D_ACTIVITY_SEARCH", "D_ACTIVITY_JUDGE", "D_ACTIVITY_TEMPLATE" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), Activity.class));
        Object IN_orgCode = searchParam.get("IN_orgCode");
        // 如果都不为空则合并成OR的关系
        if (IN_orgCode != null) {
            String activityOrgCodeArr = IN_orgCode.toString();
            searchParam.put("IN_scope_orgCode_OR_IN_orgCode", activityOrgCodeArr);
            searchParam.remove("IN_orgCode");
            searchParam.put("DISTINCT", true);
        }
        Object auditStatusObj = searchParam.get("IN_auditStatus");
        if (null != auditStatusObj) {
            List<ActStatus> actStatusList = new ArrayList<ActStatus>();
            String[] IN_status_arr = auditStatusObj.toString().split(",");
            for (String str : IN_status_arr) {
                switch (Integer.parseInt(str)) {
                case 0:
                    actStatusList.add(ActStatus.NEW);
                    break;
                case 1:
                    actStatusList.add(ActStatus.CONFIRM);
                    break;
                case 2:
                    actStatusList.add(ActStatus.PASS);
                    break;
                case 3:
                    actStatusList.add(ActStatus.NOPASS);
                    break;
                }
            }
            searchParam.put("IN_auditStatus", actStatusList);
        }
        Page<Activity> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = activityLogic.findAll(page, searchParam);
        for (Activity dt : page) {
            dt.setActivityTypeName(DataDict.getDictName(DataDict.ACTIVITY_TYPE, dt.getActivityType()));
            dt.setActivityFormName(DataDict.getDictName(DataDict.ACTIVITY_FORM, dt.getActivityForm()));
            dt.setAuditStatusName(DataDict.getDictName(DataDict.ACTIVITY_AUDIT_STATUS, dt.getAuditStatus().getIndex()));
            dt.setStatusName(DataDict.getDictName(DataDict.ACTIVITY_STATUS, dt.getStatus()));
        }
        String excludes[] = { "judgeAct", "fraPartIn", "parPartIn", "indiPartIn", "expectCost" };
        renderJson(page, excludes);
    }

    public void getJsonAll() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Object auditStatusObj = searchParam.get("IN_auditStatus");
        if (null != auditStatusObj) {
            List<ActStatus> actStatusList = new ArrayList<ActStatus>();
            String[] IN_status_arr = auditStatusObj.toString().split(",");
            for (String str : IN_status_arr) {
                switch (Integer.parseInt(str)) {
                case 0:
                    actStatusList.add(ActStatus.NEW);
                    break;
                case 1:
                    actStatusList.add(ActStatus.CONFIRM);
                    break;
                case 2:
                    actStatusList.add(ActStatus.PASS);
                    break;
                case 3:
                    actStatusList.add(ActStatus.NOPASS);
                    break;
                }
            }
            searchParam.put("IN_auditStatus", actStatusList);
        }
        Page<Activity> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = activityLogic.findAll(page, searchParam);
        for (Activity dt : page) {
            dt.setActivityTypeName(DataDict.getDictName(DataDict.ACTIVITY_TYPE, dt.getActivityType()));
            dt.setActivityFormName(DataDict.getDictName(DataDict.ACTIVITY_FORM, dt.getActivityForm()));
            dt.setAuditStatusName(DataDict.getDictName(DataDict.ACTIVITY_AUDIT_STATUS, dt.getAuditStatus().getIndex()));
            dt.setStatusName(DataDict.getDictName(DataDict.ACTIVITY_STATUS, dt.getStatus()));
        }
        String excludes[] = { "judgeAct", "fraPartIn", "parPartIn", "indiPartIn", "expectCost" };
        renderJson(page, excludes);
    }

    public void save() {
        this.setOneToManyValue();
        model.setActivityNo(sqlLogic.getCnNum(GlobalConst.NUM_ACTIVITY));
        model.setTemplate(GlobalConst.NO);
        model.setAuditStatus(ActStatus.NEW);
        model.setJudgeAct(null);
        model.setCreateUser(WebUtils.getUserName());
        model.setCreateDate(Calendar.getInstance());
        model.setLaunchor(WebUtils.getUserName());
        model.setLaunchorCode(WebUtils.getUserCode());

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

        if (StringUtils.isBlank(indiPartInParam)) {
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

                while (ite.hasNext()) {
                    String key = (String) ite.next();
                    if (key.contains("filter_")) {
                        if (null != searchParams.get(key) && !"".equals(searchParams.get(key))) {
                            paramsMap.put(key.replace("filter_", ""), searchParams.get(key));
                        }

                    }
                }
                if (null == paramsMap.get("IN_belongStoreNo") || "".equals(paramsMap.get("IN_belongStoreNo"))) {
                    paramsMap.put("IN_belongStoreNo", String.valueOf(model.getActivityScope()));
                }
                activityLogic.saveActivityByQuery(model, paramsMap);
            }
        }

        // 记录日志
        RecordInfo dt = new RecordInfo();
        dt.setCardNumber(model.getActivityNo());
        dt.setCheckDate(model.getCreateDate());
        dt.setCheckUserId(WebUtils.getUserId());
        dt.setCheckUserName(WebUtils.getUserName());
        dt.setCheckResult(RightStatus.NEW.ordinal());
        recordInfoLogic.persist(dt);

        dealJson(true);
    }

    // 发起部门
    public void getOrgJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        params.put("EQ_userId", WebUtils.getUserId());
        List rsList = user2orgLogic.qryOrgByUserIdAndParms(params);
        renderJson(rsList);
    }

    // 组织机构-首页
    public void getOrgJsonForAct() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        if (StringUtils.isEmpty(sort)) {
            sort = "orderNo";
        }
        if (StringUtils.isEmpty(dir)) {
            dir = "asc";
        }
        String[] sorts = { sort + "," + dir };

        List<Org> rsList = orgLogic.findAll(searchParam, sorts);
        for (Org org : rsList) {
            org.setTypeName(DataDict.getDictName(DataDict.ORG_TYPE, org.getType()));
        }
        renderJson(rsList);
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
        List<ActGive> actGive = this.model.getActGive();

        if (!CollectionUtils.isEmpty(indiList)) {
            for (IndiPartIn dt : indiList) {
                dt.setActivity(model);
            }
        }
        if (!CollectionUtils.isEmpty(actGive)) {
            for (ActGive dt : actGive) {
                dt.setActivity(model);
            }
        }
        if (!CollectionUtils.isEmpty(fraList)) {
            for (FraPartIn dt : fraList) {
                dt.setActivity(model);
            }
        }
        if (!CollectionUtils.isEmpty(parList)) {
            for (ParPartIn dt : parList) {
                dt.setActivity(model);
            }
        }
        if (!CollectionUtils.isEmpty(exCost)) {
            for (ExpectCost dt : exCost) {
                dt.setActivity(model);
            }
        }

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
        this.saveActivityScope();
        this.setOneToManyValue();
        Activity act = activityLogic.findById(model.getActivityId());
        model.setLaunchor(act.getLaunchor());
        model.setLaunchorCode(WebUtils.getUserCode());
        model.setJudgeAct(null);
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        saveActivityScope();
        //编辑时只更新活动信息的情况
        if (null == model.getIndiPartInParam() || "".equals(model.getIndiPartInParam())) {

            //            List<IndiPartIn> indiList = this.model.getIndiPartIn();
            //
            //            if (!CollectionUtils.isEmpty(indiList)) {
            //                for (IndiPartIn dt : indiList) {
            //                    dt.setActivity(model);
            //                    dt.setIsPartIn(GlobalConst.NO);
            //                    dt.setIsUsed(GlobalConst.NO);
            //
            //                    // 编辑参与会员判断是否有卡券编码，没有卡券编码就是刚添加的，生成编码 && 是否生成卡券
            //                    if (StringUtils.isBlank(dt.getCouponNo()) && model.getIsCreateCard().intValue() == GlobalConst.YES) {
            //                        dt.setCouponNo(model.getActivityNo() + activityLogic.getSeqActCouponNo());// 生成卡券编码
            //                    }
            //                }
            //                if (model.getIsCreateCard().intValue() == GlobalConst.YES) {
            //                    model.setCardAmount((long) (indiList.size()));
            //                } else {
            //                    model.setCardAmount(0l);
            //                }
            //            } else {
            //                model.setCardAmount(0l);
            //            }
            model.setIndiPartIn(act.getIndiPartIn());
            activityLogic.merge(model);
        } else {
            String indiPartInParam = model.getIndiPartInParam();
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
                model.setIndiPartIn(null);
                activityLogic.updateActivityByExcel(model, paramsList);
            } else {
                Map<String, Object> searchParams = JSONArray.parseObject(indiPartInParam, Map.class);

                Map<String, Object> paramsMap = new HashMap<String, Object>();
                Iterator ite = searchParams.keySet().iterator();
                while (ite.hasNext()) {
                    String key = (String) ite.next();
                    if (key.contains("filter_")) {
                        if (null != searchParams.get(key) && !"".equals(searchParams.get(key))) {
                            paramsMap.put(key.replace("filter_", ""), searchParams.get(key));
                        }
                    }
                }

                if (null == paramsMap.get("IN_belongStoreNo") || "".equals(paramsMap.get("IN_belongStoreNo"))) {
                    paramsMap.put("IN_belongStoreNo", String.valueOf(model.getActivityScope()));
                }
                model.setIndiPartIn(null);
                activityLogic.updateActivityByQuery(model, paramsMap);
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

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            activityLogic.removeById(id);
        }
        dealJson(true);
    }

    // 另存模版
    public void changeCode() {
        model = activityLogic.findById(id);
        model.setTemplate(GlobalConst.YES);
        activityLogic.merge(model);
        dealJson(true);
    }

    // 参与会员
    public void getIndi() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        Page<IndiPartIn> page = new Page<IndiPartIn>(start, limit, "indiPartInId", "asc");
        Page<IndividCust> custPage = new Page<IndividCust>(start, limit, "individCustId", "asc");

        // 正常查询
        Object activityIdObj = searchParams.get("EQ_activity_activityId");
        if (activityIdObj != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("EQ_activity_activityId", activityIdObj);
            page = new Page<IndiPartIn>(start, limit, "indiPartInId", "asc");
            page = indiPartInLogic.findAll(page, map);
            for (IndiPartIn dt : page) {
                IndividCust cust = dt.getIndividCust();
                cust.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, cust.getActive()));
                cust.setLvName(DataDict.getDictName(DataDict.LV_TYPE, cust.getLv()));
            }
            renderJson(page);
        } else {
            String add = String.valueOf(request.getParameter("add"));
            //单个添加操作
            if (!"null".equals(add)) {
                List<IndividCust> individCustList = new ArrayList<IndividCust>();
                individCustList = individCustLogic.findAll(searchParams);
                for (IndividCust dt : individCustList) {
                    dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
                    dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                    dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
                    dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                    dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                    dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
                }
                dealJson(true, individCustList);
            } else {
                List<IndiPartIn> IndiPartInList = new ArrayList<IndiPartIn>();
                String indiPartInParam = String.valueOf(request.getParameter("indiPartInParam"));
                //查询窗口查询
                if (!indiPartInParam.contains("excel")) {
                    Map<String, Object> indiPartInParamMap = JSONArray.parseObject(indiPartInParam, Map.class);
                    Iterator ite = indiPartInParamMap.keySet().iterator();
                    Map<String, Object> paramsMap = new HashMap<String, Object>();
                    while (ite.hasNext()) {
                        String key = (String) ite.next();
                        if (key.contains("filter_")) {
                            if (null != indiPartInParamMap.get(key) && !"".equals(indiPartInParamMap.get(key))) {
                                paramsMap.put(key.replace("filter_", ""), indiPartInParamMap.get(key));
                            }
                        }
                    }
                    if (null == paramsMap.get("IN_belongStoreNo")) {
                        paramsMap.put("IN_belongStoreNo", String.valueOf(request.getParameter("activityScope")));
                    }
                    custPage = individCustLogic.findAllExcludeBolb(custPage, paramsMap);
                } else {
                    List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
                    List<JSONObject> jsonList = JSONArray.parseObject(indiPartInParam.substring(5), List.class);
                    for (JSONObject object : jsonList) {
                        paramsList.add(JSONArray.parseObject(object.toString(), Map.class));
                    }
                    custPage = individCustLogic.findCustPage(custPage, paramsList);
                }
                for (IndividCust dt : custPage.getResult()) {
                    dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                    dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
                    dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                    dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                    dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
                    dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));

                    IndiPartIn indiPartIn = new IndiPartIn();
                    indiPartIn.setIndividCust(dt);
                    IndiPartInList.add(indiPartIn);
                }
                page.setResult(IndiPartInList);
                page.setTotalCount(custPage.getTotalCount());
                renderJson(page);
            }
        }
    }

    // 根据适用范围查询会员
    public void qryCustByActScope() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);

        // 添加会员或者筛选会员
        if ("search".equals(request.getParameter("addOrSearch")) && StringUtils.isNotBlank(request.getParameter("belongStoreNo"))) {
            searchParam.put("IN_belongStoreNo", request.getParameter("belongStoreNo"));
        }
        Page<IndividCust> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);

        Page<IndividCust> indi = individCustLogic.findAll(page, searchParam);
        for (IndividCust act : indi) {
            act.setLvName(DataDict.getDictName(DataDict.LV_TYPE, act.getLv()));
            act.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, act.getActive()));
        }
        renderJson(indi);
    }

    // 会员是否全选
    public void chooseAllOrNot() {
        String isAllSelected = request.getParameter("isAllSelected");
        String ids = request.getParameter("ids");
        if (isAllSelected.equals("1")) {
            Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
            List<IndividCust> rsList = null;
            if (CollectionUtils.isEmpty(searchParam)) {
                rsList = individCustLogic.findAll();
            } else {
                rsList = individCustLogic.findAll(searchParam);
            }
            for (IndividCust act : rsList) {
                act.setLvName(DataDict.getDictName(DataDict.LV_TYPE, act.getLv()));
                act.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, act.getActive()));
            }
            renderJson(rsList);
        } else {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put("IN_individCustId", ids);
            List<IndividCust> list = individCustLogic.findAll(searchParam);
            for (IndividCust act : list) {
                act.setLvName(DataDict.getDictName(DataDict.LV_TYPE, act.getLv()));
                act.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, act.getActive()));
            }
            renderJson(list);
        }
    }

    // 加盟商是否全选
    public void chooseAllOrNot2() {
        String isAllSelected = request.getParameter("isAllSelected");
        String ids = request.getParameter("ids");
        if (isAllSelected.equals("1")) {
            Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
            List<Franchisee> rsList = null;
            if (CollectionUtils.isEmpty(searchParam)) {
                rsList = franchiseeLogic.findAll();
            } else {
                rsList = franchiseeLogic.findAll(searchParam);
            }
            for (Franchisee fra : rsList) {
                fra.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, fra.getFraType()));
                fra.setFraStatusName(DataDict.getDictName(DataDict.FRANCHISEE_STATE, fra.getFraStatus()));
            }
            renderJson(rsList);
        } else {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put("IN_franchiseeId", ids);
            List<Franchisee> list = franchiseeLogic.findAll(searchParam);
            for (Franchisee fra : list) {
                fra.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, fra.getFraType()));
                fra.setFraStatusName(DataDict.getDictName(DataDict.FRANCHISEE_STATE, fra.getFraStatus()));
            }
            renderJson(list);
        }
    }

    // 异业伙伴是否全选
    public void chooseAllOrNot3() {
        String isAllSelected = request.getParameter("isAllSelected");
        String ids = request.getParameter("ids");
        if (isAllSelected.equals("1")) {
            Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
            List<Partner> rsList = null;
            if (CollectionUtils.isEmpty(searchParam)) {
                rsList = partnerLogic.findAll();
            } else {
                rsList = partnerLogic.findAll(searchParam);
            }
            for (Partner par : rsList) {
                par.setTypeName(DataDict.getDictName(DataDict.PARTNER_TYPE, par.getType()));
            }
            renderJson(rsList);
        } else {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put("IN_partnerid", ids);
            List<Partner> list = partnerLogic.findAll(searchParam);
            for (Partner par : list) {
                par.setTypeName(DataDict.getDictName(DataDict.PARTNER_TYPE, par.getType()));
            }
            renderJson(list);
        }
    }

    // 导出参与会员
    public void export3() throws Exception {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "individCust,desc" };
        List<IndiPartIn> list = indiPartInLogic.findAll(searchParam, sorts);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=indiPartIn.xls");

        OutputStream out = response.getOutputStream();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("参与会员");
        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(5, 25 * 256);
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("会员卡号");
        row0.createCell(1).setCellValue("客户名称");
        row0.createCell(2).setCellValue("活跃状态");
        row0.createCell(3).setCellValue("会员等级");
        row0.createCell(4).setCellValue("可用积分");
        row0.createCell(5).setCellValue("卡券编码");
        row0.createCell(6).setCellValue("使用人");
        row0.createCell(7).setCellValue("是否参与");
        row0.createCell(8).setCellValue("是否使用");

        IndiPartIn dt;
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            dt = list.get(i);

            row.createCell(0).setCellValue(dt.getIndividCust().getCardNo() == null ? "" : dt.getIndividCust().getCardNo());
            row.createCell(1).setCellValue(dt.getIndividCust().getName() == null ? "" : dt.getIndividCust().getName());
            row.createCell(2).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getIndividCust().getActive()));
            row.createCell(3).setCellValue(DataDict.getDictName(DataDict.LV_TYPE, dt.getIndividCust().getLv()));
            row.createCell(4).setCellValue(dt.getIndividCust().getCredit() == null ? "" : dt.getIndividCust().getCredit().toString());
            row.createCell(5).setCellValue(dt.getCouponNo() == null ? "" : dt.getCouponNo());
            row.createCell(6).setCellValue(dt.getActUser() == null ? "" : dt.getActUser());
            row.createCell(7).setCellValue(dt.getIsPartIn() == null ? "" : dt.getIsPartIn() == 0 ? "否" : "是");
            row.createCell(8).setCellValue(dt.getIsUsed() == null ? "" : dt.getIsUsed() == 0 ? "否" : "是");
        }
        wb.write(out);
        wb.close();
    }

    // 参与加盟商
    public void getFra() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<FraPartIn> rsList = fraPartInLogic.findAll(searchParam);

        Franchisee dt;
        for (FraPartIn fp : rsList) {
            dt = fp.getFranchisee();
            dt.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFraType()));
            dt.setFraStatusName(DataDict.getDictName(DataDict.FRANCHISEE_STATE, dt.getFraStatus()));

            // 判断是否参与并回填值
            if ((fp.getIsPartIn() == null || fp.getIsPartIn() == GlobalConst.NO) && fp.getActivity() != null) {
                int cnt = saleOrderLogic.loadFra(dt.getFranchiseeId(), fp.getActivity().getActivityId());
                if (cnt > 0) {
                    fp.setIsPartIn(GlobalConst.YES);
                    fraPartInLogic.merge(fp);
                }
            }
        }
        renderJson(rsList);
    }

    // 修改加盟商是否参与
    public void changeIsPartIn() {
        String choosefra = request.getParameter("fraPartInId");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("IN_fraPartInId", choosefra);
        List<FraPartIn> list = fraPartInLogic.findAll(searchParam);
        for (FraPartIn fp : list) {
            if (fp.getIsPartIn() == GlobalConst.NO) {
                fp.setIsPartIn(GlobalConst.YES);
                fraPartInLogic.merge(fp);
            }
        }
        dealJson(true);
    }

    // 修改异业伙伴是否参与
    public void changeIsPartIn2() {
        String choosepar = request.getParameter("parPartInId");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("IN_parPartInId", choosepar);
        List<ParPartIn> list = parPartInLogic.findAll(searchParam);
        for (ParPartIn pp : list) {
            if (pp.getIsPartIn() == GlobalConst.NO) {
                pp.setIsPartIn(GlobalConst.YES);
                parPartInLogic.merge(pp);
            }
        }
        dealJson(true);
    }

    // 修改实际花费
    public void actualCost() {
        String actual = request.getParameter("expectCostId");
        Double actualCost = Double.parseDouble(request.getParameter("actualCost"));
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("IN_expectCostId", actual);
        List<ExpectCost> list = expectCostLogic.findAll(searchParam);
        for (ExpectCost pp : list) {
            pp.setActualCost(actualCost);
            expectCostLogic.merge(pp);
        }
        dealJson(true);
    }

    // 导出参与加盟商
    public void export() throws Exception {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "franchisee,asc" };
        List<FraPartIn> list = fraPartInLogic.findAll(searchParam, sorts);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=franchiseePartIn.xls");

        OutputStream out = response.getOutputStream();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("参与加盟商");
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("加盟商编码");
        row0.createCell(1).setCellValue("加盟商名称");
        row0.createCell(2).setCellValue("加盟商简称");
        row0.createCell(3).setCellValue("加盟商类别");
        row0.createCell(4).setCellValue("加盟商状态");
        row0.createCell(5).setCellValue("实际控制人");
        row0.createCell(6).setCellValue("是否参与");

        if (!CollectionUtils.isEmpty(list)) {
            FraPartIn dt;
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                dt = list.get(i);

                row.createCell(0).setCellValue(dt.getFranchisee().getFraCode() == null ? "" : dt.getFranchisee().getFraCode());
                row.createCell(1).setCellValue(dt.getFranchisee().getFraName() == null ? "" : dt.getFranchisee().getFraName());
                row.createCell(2).setCellValue(dt.getFranchisee().getShortName() == null ? "" : dt.getFranchisee().getShortName());
                row.createCell(3).setCellValue(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFranchisee().getFraType()));
                row.createCell(4).setCellValue(DataDict.getDictName(DataDict.FRANCHISEE_STATE, dt.getFranchisee().getFraStatus()));
                row.createCell(5).setCellValue(dt.getFranchisee().getActualCon() == null ? "" : dt.getFranchisee().getActualCon());
                row.createCell(6).setCellValue(dt.getIsPartIn() == null ? "" : dt.getIsPartIn() == 0 ? "否" : "是");
            }
        }
        wb.write(out);
        wb.close();
    }

    // 参与异业伙伴
    public void getPar() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<ParPartIn> rsList = parPartInLogic.findAll(searchParam);
        for (ParPartIn pp : rsList) {
            pp.getPartner().setTypeName(DataDict.getDictName(DataDict.PARTNER_TYPE, pp.getPartner().getType()));
        }
        renderJson(rsList, "activity");
    }

    // 导出参与异业伙伴
    public void export2() throws Exception {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "partner,asc" };
        List<ParPartIn> list = parPartInLogic.findAll(searchParam, sorts);

        if (!CollectionUtils.isEmpty(list)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=partnerPartIn.xls");

            OutputStream out = response.getOutputStream();

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("参与异业伙伴");
            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("异业伙伴编码");
            row0.createCell(1).setCellValue("异业伙伴名称");
            row0.createCell(2).setCellValue("异业伙伴简称");
            row0.createCell(3).setCellValue("异业伙伴类型");
            row0.createCell(4).setCellValue("实际控制人");
            row0.createCell(5).setCellValue("是否参与");

            ParPartIn dt;
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                dt = list.get(i);

                row.createCell(0).setCellValue(dt.getPartner().getPartnerno() == null ? "" : dt.getPartner().getPartnerno());
                row.createCell(1).setCellValue(dt.getPartner().getPartnername() == null ? "" : dt.getPartner().getPartnername());
                row.createCell(2).setCellValue(dt.getPartner().getName() == null ? "" : dt.getPartner().getName());
                row.createCell(3).setCellValue(DataDict.getDictName(DataDict.PARTNER_TYPE, dt.getPartner().getType()));
                row.createCell(4).setCellValue(dt.getPartner().getPerson() == null ? "" : dt.getPartner().getPerson());
                row.createCell(5).setCellValue(dt.getIsPartIn() == null ? "" : dt.getIsPartIn() == 0 ? "否" : "是");
            }
            wb.write(out);
            wb.close();
        }
    }

    // 费用预估
    public void getCost() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<ExpectCost> rsList = expectCostLogic.findAll(searchParam);
        renderJson(rsList);
    }

    // 会员活动产出
    public void getPos() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        Long actId = Long.valueOf(searchParam.get("EQ_activity_activityId").toString());
        List<Map> rsList = posOrderLogic.loadPos(actId);
        renderJson(rsList);
    }

    // 加盟商活动产出
    public void getOrder() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        Long actId = Long.valueOf(searchParam.get("EQ_activity_activityId").toString());
        Activity dt = activityLogic.findById(actId);
        List<Map> rsList = saleOrderLogic.loadOrder(actId, dt.getActivityType());
        for (Map map : rsList) {
            map.put("typeName", DataDict.getDictName(DataDict.SALEORDER_TYPE, (map.get("type") == null ? null : Integer.parseInt(map.get("type").toString()))));
        }
        renderJson(rsList);
    }

    // 审核情况
    public void getAudit() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        Long activityId = Long.valueOf(searchParam.get("EQ_activity_activityId").toString());
        Activity activity = activityLogic.findById(activityId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("EQ_activityNo", activity.getActivityNo());
        String[] sort = { "activityAuditId" + "," + Sort.Direction.DESC };
        List<ActivityAudit> rsList = activityAuditLogic.findAll(map, sort);
        for (ActivityAudit dt : rsList) {
            dt.setResultName(DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, dt.getResult()));
        }

        // 审核情况页签显示新建人和提交人信息
        List<ActivityAudit> activityAudits = new ArrayList<ActivityAudit>();

        Map<String, Object> map2 = new HashMap<String, Object>();
        String[] sortRecordInfo = { "recordInfoId" + "," + Sort.Direction.DESC };
        map2.put("EQ_cardNumber", activity.getActivityNo());
        List<RecordInfo> recordInfos = recordInfoLogic.findAll(map2, sortRecordInfo);
        for (RecordInfo recordInfo : recordInfos) {
            ActivityAudit dt = new ActivityAudit();
            dt.setAuditTime(recordInfo.getCheckDate());
            dt.setAuditor(recordInfo.getCheckUserName());
            dt.setResultName(DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, recordInfo.getCheckResult()));
            activityAudits.add(dt);
        }
        rsList.addAll(activityAudits);
        renderJson(rsList);
    }

    // 买赠活动
    public void getActGive() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<ActGive> rsList = actGiveLogic.findAll(params);
        renderJson(rsList);
    }

    // 审核流
    public void getEvents() {
        String s_authorities = "|" + WebUtils.getAuthorities() + "|";
        String[] events4Authorities = { "D_ACONFIRM", "D_APASS", "D_ANOPASS" };

        StringBuffer buf = new StringBuffer();
        for (String auth : events4Authorities) {
            if (s_authorities.indexOf("|" + auth + "|") > -1) {
                if (buf.length() > 0) {
                    buf.append(",");
                }
                buf.append("'" + auth + "'");
            }
        }
        String eventAuth = buf.toString();

        List<String> events = new ArrayList<String>();
        Activity entity = activityLogic.findById(id);
        ActStatus status = entity.getAuditStatus();
        switch (status) {
        case NEW:
            events.add(ActStatus.CONFIRM.name());
            break;
        case CONFIRM:
            events.add(ActStatus.PASS.name());
            events.add(ActStatus.NOPASS.name());
            break;
        default:
            break;
        }
        buf = new StringBuffer("[");
        for (String event : events) {
            if (event != null) {
                if (eventAuth.indexOf("'D_A" + event.toUpperCase() + "'") > -1) {
                    buf.append("'" + event + "',");
                }
            }
        }
        if (buf.length() > 1) {
            buf.append("'@'");
        }
        buf.append("]");
        renderJson(buf.toString());
    }

    // ///////////////////////////////////////////////////////处理流程状态/////////////////////////////////////////////////////

    public void dealStatus() {
        try {
            long userId = WebUtils.getUserId();
            String userName = WebUtils.getUserName();

            model = activityLogic.findById(id);
            model.setAuditStatus(ActStatus.CONFIRM);

            // 点击提交(oa审核)：crm->oa
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("activityNo", model.getActivityNo());
            params.put("activityType", model.getActivityType());
            params.put("activityForm", NeuUtils.parseString(model.getActivityForm()));
            params.put("activityTheme", model.getActivityTheme());
            params.put("activityScope", NeuUtils.parseString(model.getActivityScope()));
            params.put("launchor", NeuUtils.parseString(model.getLaunchor()));
            params.put("launchDept", model.getOrg().getName());
            params.put("beginTime", NeuUtils.parseStringFromCalendar(model.getBeginTime()));
            params.put("endTime", NeuUtils.parseStringFromCalendar(model.getEndTime()));
            params.put("auditStatus", model.getAuditStatus().ordinal());
            params.put("promise", NeuUtils.parseString(model.getPromise()));
            params.put("overview", NeuUtils.parseString(model.getOverview()));
            params.put("background", NeuUtils.parseString(model.getBackground()));
            params.put("aim", NeuUtils.parseString(model.getAim()));
            params.put("plan", NeuUtils.parseString(model.getPlan()));
            params.put("assist", NeuUtils.parseString(model.getAssist()));
            params.put("content", NeuUtils.parseString(model.getContent()));
            params.put("remark", NeuUtils.parseString(model.getRemark()));
            params.put("submitUserNo", NeuUtils.parseString(WebUtils.getUserCode()));
            params.put("assistDept", NeuUtils.parseString(model.getAssistDept()));

            if (StringUtils.isNotBlank(model.getAssistDept())) {
                List<String> assistDeptNameList = new ArrayList<String>();
                Map searchParam1 = new HashMap();
                searchParam1.put("IN_orgCode", model.getAssistDept());
                List<Org> orgList1 = orgLogic.findAll(searchParam1);
                for (Org dt : orgList1) {
                    assistDeptNameList.add(dt.getName());
                }
                params.put("assistDeptName", StringUtils.join(assistDeptNameList.toArray(), ","));
            } else {
                params.put("assistDeptName", "");
            }

            Map<String, Object> costParams = new HashMap<String, Object>();
            costParams.put("EQ_activity_activityId", id);
            List<ExpectCost> costList = expectCostLogic.findAll(costParams);

            List rsList = new ArrayList();
            Map tempMap;
            double applyTotalCost = 0.0;// 申请费用总金额
            for (ExpectCost dt : costList) {
                tempMap = new HashMap();
                tempMap.put("activityNo", dt.getActivity().getActivityNo());
                tempMap.put("project", dt.getActItems());
                tempMap.put("spec", dt.getActSize());
                tempMap.put("price", dt.getActPrice());
                tempMap.put("amount", dt.getActCount());
                tempMap.put("budgetCost", dt.getBudget());
                tempMap.put("actualexp", NeuUtils.parseString(dt.getActualCost()));
                tempMap.put("remark", NeuUtils.parseString(dt.getRemark()));
                applyTotalCost += dt.getBudget();
                rsList.add(tempMap);
            }
            params.put("applyTotalCost", applyTotalCost);
            params.put("expectCostDetail", rsList);

            Map rsMap = httpLogic.post(NeuUtils.getProperty("activity_crm2oa"), "ActivityAction.dealStatus", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(params));
            int flag = Integer.parseInt(rsMap.get("flag").toString());
            String msg = rsMap.get("msg").toString();
            // oa返回成功后才能保存并提交
            if (flag == 1) {
                // 提交
                activityLogic.merge(model);
                // 日志
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setCardNumber(model.getActivityNo());
                recordInfo.setCheckUserId(userId);
                recordInfo.setCheckUserName(userName);
                recordInfo.setCheckResult(RightStatus.CONFIRM.ordinal());
                recordInfo.setCheckDate(Calendar.getInstance());
                recordInfoLogic.persist(recordInfo);

                // 查询费用预估
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("EQ_activity_activityId", model.getActivityId());
                model.setExpectCost(expectCostLogic.findAll(map));

                dealJson(true);
            } else {
                dealJson(false, "调用OA接口异常：" + msg);
            }
        } catch (Exception e) {
            dealJson(false, e.toString());
            e.printStackTrace();
        }
    }

    // 会员信息-查询
    public void queryIndividCustParam() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        Map map = new HashMap();
        map.put("queryParamsStr", Json.toJson(searchParams));
        dealJson(true, map);
    }

}