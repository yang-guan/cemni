package com.huiju.afterservice.undovisit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.afterservice.telvisitrecord.logic.TelVisitRecordRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.module.data.Page;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UndoVisitAction extends BaseAction<TelVisitRecord, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private TelVisitRecordRemote telVisitRecordLogic;
    @EJB
    private User2orgRemote user2orgLogic;
    @EJB
    private PosOrderRemote poslogic;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        jsPath.add("/js/afterservice/undovisit/Q.afterservice.undovisit.js");
        jsPath.add("/js/console/store/Q.store.chooseStoreWin.js");

        String[] authorities = { "D_UNDOVIST_LIST", "D_UNDOVISIT_BACK", "D_UNDOVISIT_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        params.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), TelVisitRecord.class));
        params.put("IS_telVisit_telVisitNo", "NOTNULL");// 在“回访任务维护”功能中，点击“发布”按钮生成的回访单
        params.put("EQ_expiredtype", GlobalConst.NO);
        params.put("EQ_backzt", GlobalConst.NO);
        params.put("GT_endrq", Calendar.getInstance().getTime());

        // 回访结束日期在区间查询(优先回访记录单有值,回访开始日期无值)
        Object LE_returnRecord = params.get("GE_returnRecord");
        if (LE_returnRecord != null) {
            if (Integer.valueOf(LE_returnRecord.toString()) == 1) {
                returnRecord(params, 2);
            } else if (Integer.valueOf(LE_returnRecord.toString()) == 2) {
                returnRecord(params, 6);
            } else {
                returnRecord(params, 14);
            }
        }

        Page<TelVisitRecord> page = new Page<TelVisitRecord>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = telVisitRecordLogic.findAll(page, params);
        IndividCust cust;
        for (TelVisitRecord tr : page) {
            tr.setBackfsName(DataDict.getDictName(DataDict.TELVISIT_BACKFS, tr.getBackfs()));
            tr.setBacktypeName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, tr.getBacktype()));
            tr.setTaskTypeName(DataDict.getDictName(DataDict.TELVISIT_TASKTYPE, tr.getTaskType()));
            cust = tr.getIndividCust();
            cust.setLvName(DataDict.getDictName(DataDict.LV_TYPE, cust.getLv()));// 会员等级
            cust.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, cust.getType()));// 客户类型
        }
        renderJson(page);
    }

    private void returnRecord(Map<String, Object> params, int returnRecord) {
        String endDate = params.get("LE_endrq").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - returnRecord);//让日期减回访天数 

        params.put("GE_endrq", calendar.getTime()); // 回访结束日期减去天数之后的日期
        params.remove("GE_returnRecord");
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
        model.setTelVisit(dt.getTelVisit());
        model.setIndividCust(dt.getIndividCust());
        model.setTelVisitRecordNo(dt.getTelVisitRecordNo());
        model.setExpiredtype(dt.getExpiredtype());
        model.setBackzt(GlobalConst.YES);

        model.setCuserCode(dt.getCuserCode());
        model.setCdate(dt.getCdate());
        model.setMdate(Calendar.getInstance());
        model.setMuserCode(WebUtils.getUserCode());
        telVisitRecordLogic.merge(model);
        dealJson(true);
    }

    // 电话回访
    public void qryTelVisit() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        List<TelVisitRecord> rsList = telVisitRecordLogic.findAll(searchParam);

        for (TelVisitRecord dt : rsList) {
            dt.setBackfsName(DataDict.getDictName(DataDict.TELVISIT_BACKFS, dt.getBackfs()));
            dt.setBacktypeName(DataDict.getDictName(DataDict.INDIVIDCUST_FRESH, dt.getBacktype()));
            dt.setBackztName(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getBackzt()));
            dt.setExpiredtypeName(DataDict.getDictName(DataDict.YES_OR_NOT, dt.getExpiredtype()));
        }
        renderJson(rsList);
    }

}