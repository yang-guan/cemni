package com.huiju.afterservice.telvisitrecord;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.afterservice.telvisitrecord.logic.TelVisitRecordRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.module.data.Page;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TelVisitRecordAction extends BaseAction<TelVisitRecord, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private TelVisitRecordRemote telVisitRecordLogic;
    @EJB
    private PosOrderRemote poslogic;
    @EJB
    private IndividCustRemote indivicustlogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private User2orgRemote user2orgLogic;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        jsPath.add("/js/console/store/Q.store.chooseStoreWin.js");
        jsPath.add("/js/archive/individcust/Q.archive.chooseIndiWin.js");
        jsPath.add("/js/afterservice/telvisitrecord/Q.afterservice.telvisitrecord.js");

        String[] authorities = { "D_TELVISITRECORD_LIST", "D_TELVISITRECORD_ADD", "D_TELVISITRECORD_EDIT", "D_TELVISITRECORD_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), TelVisitRecord.class));
        Page<TelVisitRecord> page = new Page<TelVisitRecord>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = telVisitRecordLogic.findAll(page, searchParams);
        IndividCust cust;
        for (TelVisitRecord dt : page) {
            dt.setBackfsName(DataDict.getDictName(DataDict.TELVISIT_BACKFS, dt.getBackfs()));
            dt.setBacktypeName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getBacktype()));
            dt.setBackztName(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getBackzt()));
            dt.setExpiredtypeName(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getExpiredtype()));
            dt.setTaskTypeName(DataDict.getDictName(DataDict.TELVISIT_TASKTYPE, dt.getTaskType()));
            cust = dt.getIndividCust();
            cust.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, cust.getType()));// 客户类型
        }
        String[] excludes = { "individCust.telVisitCustList", "individCust.recordList" };
        renderJson(page, excludes);
    }

    public void save() {
        // 是否有改动
        String recodeString = this.compareTelVisitRecord(model);
        if (model.getBackzt().intValue() == GlobalConst.NO) {
            String[] backztArr = recodeString.split(",");
            for (String backztStr : backztArr) {
                if (backztStr.substring(backztStr.length() - 1).equals("=") || backztStr.substring(backztStr.length() - 4).equals("null")) {
                    // TODO
                } else {
                    model.setBackzt(GlobalConst.YES);
                }
            }
        }

        // 回访门店
        List<Map> orgList = user2orgLogic.qryOrgByUserId(WebUtils.getUserId());
        Map orgMap = orgList.get(0);
        model.setStoreNo(orgMap.get("orgcode").toString());
        model.setStoreName(orgMap.get("name").toString());
        model.setTelVisitRecordNo(sqlLogic.getSeq("seq_telvisitrecordno", "CL", "yyyymmdd"));
        model.setExpiredtype(GlobalConst.NO);

        model.setCuserCode(WebUtils.getUserCode());
        model.setCdate(Calendar.getInstance());
        model.setMdate(Calendar.getInstance());
        model.setMuserCode(WebUtils.getUserCode());
        telVisitRecordLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = telVisitRecordLogic.findById(id);
        // 是否已消费
        boolean saletype = poslogic.isPosByIndividCustId(model.getIndividCust().getIndividCustId());
        model.setSaletype(saletype ? 1 : 0);
        dealJson(true, model);
    }

    public void update() {
        // 回访门店
        List<Map> orgList = user2orgLogic.qryOrgByUserId(WebUtils.getUserId());
        if (CollectionUtils.isEmpty(orgList)) {
            dealJson(false, "登录用户还没有关联组织机构！");
            return;
        }
        Map orgMap = orgList.get(0);
        model.setStoreNo(orgMap.get("orgcode").toString());
        model.setStoreName(orgMap.get("name").toString());

        TelVisitRecord dt = telVisitRecordLogic.findById(model.getTelVisitRecordId());

        String oldRecodeString = this.compareTelVisitRecord(model);
        String newRecodeString = this.compareTelVisitRecord(dt);

        if (dt.getBackzt().intValue() == GlobalConst.NO && !oldRecodeString.equals(newRecodeString)) {
            model.setBackzt(GlobalConst.YES);
        }

        model.setTelVisit(dt.getTelVisit());
        model.setIndividCust(dt.getIndividCust());
        model.setTelVisitRecordNo(dt.getTelVisitRecordNo());
        model.setBacktype(dt.getBacktype());
        model.setExpiredtype(dt.getExpiredtype());
        model.setCuserCode(dt.getCuserCode());
        model.setCdate(dt.getCdate());
        model.setMdate(Calendar.getInstance());
        model.setMuserCode(WebUtils.getUserCode());
        telVisitRecordLogic.merge(model);

        dealJson(true);
    }

    // 店长新建回访记录单时，选择会员后验证是否消费
    public void isPosByIndividCustId() {
        boolean saletype = poslogic.isPosByIndividCustId(id);

        Map rsMap = new HashMap();
        rsMap.put("saletype", saletype ? 1 : 0);
        dealJson(true, rsMap);
    }

    /**
     * 获取实体类字符串判断回访单是否已回访
     * 
     * @author：WangYuanJun
     * @date：2017年3月4日 下午4:05:08
     */
    private String compareTelVisitRecord(TelVisitRecord dt) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("careupdate=").append(dt.getCareupdate());
        buffer.append(",feedadvice=").append(dt.getFeedadvice());
        buffer.append(",infoknowed=").append(dt.getInfoknowed());
        buffer.append(",intentioncp=").append(dt.getIntentioncp());
        buffer.append(",khadvice=").append(dt.getKhadvice());
        buffer.append(",khtalk=").append(dt.getKhtalk());
        buffer.append(",newrecoment=").append(dt.getNewrecoment());
        buffer.append(",notfeedadvice=").append(dt.getNotfeedadvice());
        buffer.append(",notinfoknowed=").append(dt.getNotinfoknowed());
        buffer.append(",notintentioncp=").append(dt.getNotintentioncp());
        buffer.append(",notkhadvice=").append(dt.getNotkhadvice());
        buffer.append(",notkhtalk=").append(dt.getNotkhtalk());
        buffer.append(",notnewrecoment=").append(dt.getNotnewrecoment());
        buffer.append(",notprofessorknow=").append(dt.getNotprofessorknow());
        buffer.append(",notsaleremark=").append(dt.getNotsaleremark());
        buffer.append(",notshopenvi=").append(dt.getNotshopenvi());
        buffer.append(",notshopservice=").append(dt.getNotshopservice());
        buffer.append(",ornamentwear=").append(dt.getOrnamentwear());
        buffer.append(",parentgant=").append(dt.getParentgant());
        buffer.append(",parentmanyi=").append(dt.getParentmanyi());
        buffer.append(",professorknow=").append(dt.getProfessorknow());
        buffer.append(",saleremark=").append(dt.getSaleremark());
        buffer.append(",shopenvi=").append(dt.getShopenvi());
        buffer.append(",shopservice=").append(dt.getShopservice());
        buffer.append("wearupdate=").append(dt.getWearupdate());
        return buffer.toString();
    }

}