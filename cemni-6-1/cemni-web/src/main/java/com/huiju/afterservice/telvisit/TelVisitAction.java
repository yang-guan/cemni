package com.huiju.afterservice.telvisit;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.afterservice.telvisit.logic.TelVisitCustRemote;
import com.huiju.afterservice.telvisit.logic.TelVisitRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.dict.logic.DictRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.json.Json;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TelVisitAction extends BaseAction<TelVisit, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private TelVisitRemote telVisitLogic;
    @EJB
    private TelVisitCustRemote telVisitCustLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private DictRemote dictLogic;

    @EJB
    private FileInfoRemote fileInfoLogic;
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
        jsArr.add("/js/afterservice/telvisit/Q.telvisit.searchWin.js");
        jsArr.add("/js/afterservice/telvisit/Q.telvisit.addWin.js");
        jsArr.add("/js/console/store/Q.store.chooseStoreWin.js");
        jsArr.add("/js/console/dict/Q.dict.chooseDictWin.js");
        jsArr.add("/js/common/Q.excel.uploadWin.js");
        jsPath.add("/js/archive/individcust/Q.archive.chooseIndiWin.js");
        jsArr.add("/js/afterservice/telvisit/Q.afterservice.telvisit.js");
        jsPath.addAll(jsArr);

        String[] authorities = { "D_TELVISIT_LIST", "D_TELVISIT_ADD", "D_TELVISIT_EDIT", "D_TELVISIT_DELETE", "D_TELVISIT_SEARCH", "D_TELVISIT_PUBLISH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        Page<TelVisit> page = new Page<TelVisit>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = telVisitLogic.findAll(page, searchParams);
        for (TelVisit dt : page) {
            dt.setBackfsName(DataDict.getDictName(DataDict.TELVISIT_BACKFS, dt.getBackfs()));
            dt.setTaskTypeName(DataDict.getDictName(DataDict.TELVISIT_TASKTYPE, dt.getTaskType()));
            dt.setPublishztName(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getPublishzt()));
        }
        renderJson(page);
    }

    // 已选中的会员信息-查询
    public void qryTelVisitCust() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        Page<TelVisitCust> page = new Page<TelVisitCust>(start, limit, "telVisitCustId", "asc");

        if (null == searchParams.get("EQ_telVisit_telVisitId") || "".equals(searchParams.get("EQ_telVisit_telVisitId"))) {
            Page<IndividCust> custPage = new Page<IndividCust>(start, limit, "individCustId", "asc");
            String queryParamsStr = request.getParameter("queryParamsStr");

            if (queryParamsStr != null && queryParamsStr.contains("excel")) {
                List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
                List<JSONObject> jsonList = JSONArray.parseObject(queryParamsStr.substring(5), List.class);
                for (JSONObject object : jsonList) {
                    Map<String, Object> map = JSONArray.parseObject(object.toString(), Map.class);
                    paramsList.add(map);
                }
                custPage = individCustLogic.findCustPage(custPage, paramsList);
            } else if (StringUtils.isNotBlank(queryParamsStr)) {
                Map<String, Object> paramsMap = JSONArray.parseObject(queryParamsStr, Map.class);
                custPage = individCustLogic.findAllExcludeBolb(custPage, paramsMap);
            }

            // 拼接返回结果
            List<TelVisitCust> rsList = new ArrayList<TelVisitCust>();
            for (IndividCust dt : custPage) {
                dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
                dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
                dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));

                TelVisitCust tc = new TelVisitCust();
                tc.setIndividCust(dt);
                rsList.add(tc);
            }
            page.setResult(rsList);
            page.setTotalCount(custPage.getTotalCount());
            renderJson(page);
        } else {
            Object LE_lastBuyTime = searchParams.get("LE_lastBuyTime");
            if (LE_lastBuyTime != null && StringUtils.isNotBlank(LE_lastBuyTime.toString())) {
                Calendar cl = NeuUtils.parseCalendar(LE_lastBuyTime.toString());
                cl.add(Calendar.DATE, 1);
                searchParams.put("LE_lastBuyTime", NeuUtils.parseStringFromCalendar(cl));
            }
            page = telVisitCustLogic.findAll(page, searchParams);
            for (TelVisitCust tc : page) {
                IndividCust dt = tc.getIndividCust();
                dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
                dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
                dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
            }
            renderJson(page);
        }
    }

    // 会员信息-查询
    public void qryTelVisitCust2() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        if ("true".equals(request.getParameter("add"))) {
            Page<IndividCust> page = new Page<IndividCust>();
            page = individCustLogic.findAll(page, searchParams);
            for (IndividCust dt : page) {
                dt.setFreshName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getFresh()));
                dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
                dt.setGenderName(DataDict.getDictName(DataDict.FRANCHISEE_SEX, dt.getGender()));
                dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
            }
            dealJson(true, page);
        } else {
            Map map = new HashMap();
            map.put("queryParamsStr", Json.toJson(searchParams));
            dealJson(true, map);
        }
    }

    public void save() {
        model.setTelVisitNo(sqlLogic.getCnNum(GlobalConst.NUM_TASK));
        model.setCuser(WebUtils.getUserName());
        model.setCdate(Calendar.getInstance());
        model.setMdate(Calendar.getInstance());
        model.setMuser(WebUtils.getUserName());

        if (StringUtils.isBlank(model.getQueryParamsStr())) {
            if (null != model.getTelVisitCustList()) {
                for (TelVisitCust dt : model.getTelVisitCustList()) {
                    dt.setTelVisit(model);
                }
            }
            telVisitLogic.persist(model);
        } else {
            String queryParamsStr = model.getQueryParamsStr();
            // excel导入
            if (queryParamsStr != null && queryParamsStr.contains("excel")) {
                List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
                List<JSONObject> jsonList = JSONArray.parseObject(queryParamsStr.substring(5), List.class);
                for (JSONObject object : jsonList) {
                    Map map = JSONArray.parseObject(object.toString(), Map.class);
                    paramsList.add(map);
                }
                telVisitLogic.saveTelVisit(model, paramsList);
            } else {
                // 弹出框查询
                Map<String, Object> searchParams = JSONArray.parseObject(model.getQueryParamsStr(), Map.class);
                telVisitLogic.saveTelVisit(model, searchParams);
            }
        }
        dealJson(true);
    }

    public void edit() {
        model = telVisitLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        TelVisit dt = telVisitLogic.findById(model.getTelVisitId());
        model.setCuser(dt.getCuser());
        model.setCdate(dt.getCdate());
        model.setMdate(Calendar.getInstance());
        model.setMuser(WebUtils.getUserName());

        if (StringUtils.isBlank(model.getQueryParamsStr())) {
            //修正没有查询或excel导入时，只保存当前页数据问题
            model.setTelVisitCustList(dt.getTelVisitCustList());
            telVisitLogic.merge(model);
        } else {
            String queryParamsStr = model.getQueryParamsStr();
            List<JSONObject> jsonList = null;
            List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
            if (queryParamsStr.contains("excel")) {
                jsonList = JSONArray.parseObject(queryParamsStr.substring(5), List.class);
                for (JSONObject object : jsonList) {
                    Map map = JSONArray.parseObject(object.toString(), Map.class);
                    paramsList.add(map);
                }
                telVisitLogic.updateTelVisitExcel(model, paramsList);
            } else {
                Map<String, Object> searchParams = JSONArray.parseObject(model.getQueryParamsStr(), Map.class);
                telVisitLogic.updateTelVisit(model, searchParams);
            }
        }
        dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            telVisitLogic.delete(id);
        }
        dealJson(true);
    }

    // 发布
    public void telVisitPublish() {
        telVisitLogic.telVisitPublish(id, WebUtils.getUserCode());
        dealJson(true);
    }

    // 会员信息：导入
    public void uploadExcel() throws ParseException {
        Map<String, Object> retMap = null;
        try {
            FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
            retMap = telVisitLogic.uploadExcel(fileInfoLogic.convert(fileInfo).getFile());
        } catch (Exception e) {
            retMap = new HashMap<String, Object>();
            retMap.put("success", false);
            retMap.put("msg", e.getMessage());
        }
        renderHtml(DataUtils.toJson(retMap));
    }

}