package com.huiju.afterservice.rightmaint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.afterservice.rightmaint.entity.RecordInfo;
import com.huiju.afterservice.rightmaint.entity.RightMaint;
import com.huiju.afterservice.rightmaint.entity.RightStatus;
import com.huiju.afterservice.rightmaint.logic.RecordInfoRemote;
import com.huiju.afterservice.rightmaint.logic.RightMaintRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.inter.httpclient.HttpClientRemote;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.inter.rightmaint.entity.RightMaintAudit;
import com.huiju.inter.rightmaint.logic.RightMaintAuditRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.Sort;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

/**
 * 客户权益
 * 
 * @author：WangYuanJun
 * @date：2016年12月7日 下午4:35:49
 */
@SuppressWarnings({ "rawtypes" })
public class RightMaintAction extends BaseAction<RightMaint, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "RightMaintBean")
    private RightMaintRemote rightMaintLogic;

    @EJB(mappedName = "RightMaintAuditBean")
    private RightMaintAuditRemote rightMaintAuditLogic;

    @EJB(mappedName = "SqlBean")
    private SqlRemote sqlLogic;

    @EJB(mappedName = "HttpClientBean")
    private HttpClientRemote httpLogic;

    @EJB(mappedName = "RecordInfoBean")
    private RecordInfoRemote recordInfoLogic;

    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        List<String> jsArr = new ArrayList<String>();
        jsArr.add("/js/afterservice/rightmaint/Q.afterservice.rightmaint.js");
        jsArr.add("/js/afterservice/busiregist/Q.afterservice.chooseOrgStoreWin.js");
        jsArr.add("/js/archive/individcust/Q.archive.chooseIndiWin.js");
        jsArr.add("/js/afterservice/rightmaint/Q.afterservice.chooseOrderWin.js");
        jsArr.add("/plugins/UploadDialog/Neu.ux.UploadDialog.js");
        jsPath.addAll(jsArr);

        String[] authorities = { "D_RIGHTMAINT_LIST", "D_RIGHTMAINT_ADD", "D_RIGHTMAINT_EDIT", "D_RIGHTMAINT_SEARCH", "D_RIGHTMAINT_CONFIRM" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), RightMaint.class));

        Object IN_storeNo = searchParam.get("IN_storeNo");
        Object IN_orgCode = searchParam.get("IN_orgCode");
        // 如果都不为空则合并成OR的关系
        if (IN_storeNo != null && IN_orgCode != null) {
            String[] storeOrgCodeArr = { IN_storeNo.toString(), IN_orgCode.toString() };
            searchParam.put("IN_storeNo_OR_IN_orgCode", storeOrgCodeArr);
            searchParam.remove("IN_storeNo");
            searchParam.remove("IN_orgCode");
        }
        searchParam.put("JOIN", "store_LEFT,org_LEFT");
        Page<RightMaint> page = new Page<RightMaint>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = rightMaintLogic.findAll(page, searchParam);
        for (RightMaint dt : page) {
            dt.setHandleStateName(DataDict.getDictName(DataDict.RIGHTMAINT_HANDLESTATE, dt.getHandleState()));
            dt.setComplaintTypeName(DataDict.getDictName(DataDict.RIGHTMAINT_COMPLAINTTYPE, dt.getComplaintType()));
            dt.setUrgencyLevelName(DataDict.getDictName(DataDict.RIGHTMAINT_URGENCYLEVEL, dt.getUrgencyLevel()));
            dt.setComplaintLevelName(DataDict.getDictName(DataDict.RIGHTMAINT_COMPLAINTLEVEL, dt.getComplaintLevel()));
            dt.setReviewStateName(DataDict.getDictName(DataDict.RIGHTMAINT_REVIEWSTATE, dt.getReviewState()));
            dt.setProbTypeName(DataDict.getDictName(DataDict.RIGHTMAINT_PROBTYPE, dt.getProbType()));
            dt.setOrgStoreName(dt.getOrg() == null ? dt.getStore().getName() : dt.getOrg().getName());
        }
        renderJson(page);
    }

    public void save() {
        saveCommon();
        model.setComplaintNo(sqlLogic.getCnNum(GlobalConst.NUM_RIGHTMAINT));
        model.setCreateUser(WebUtils.getUserName());
        model.setCreateDate(Calendar.getInstance());
        model = rightMaintLogic.persist(model);

        // 记录新建信息
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.setCardNumber(model.getComplaintNo());
        recordInfo.setCheckDate(model.getCreateDate());
        recordInfo.setCheckUserId(WebUtils.getUserId());
        recordInfo.setCheckUserName(WebUtils.getUserName());
        recordInfo.setCheckResult(RightStatus.NEW.ordinal());
        recordInfoLogic.persist(recordInfo);
        dealJson(true, model);
    }

    public void edit() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("EQ_rightMaintId", id);
        model = rightMaintLogic.find(searchParam);

        dealJson(true, model);
    }

    public void update() {
        RightMaint rightMaint = rightMaintLogic.findById(model.getRightMaintId());
        saveCommon();
        model.setCreateDate(rightMaint.getCreateDate());
        model.setCreateUser(rightMaint.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        rightMaintLogic.merge(model);

        dealJson(true);
    }

    private void saveCommon() {
        if (model.getIndividCust().getIndividCustId() == null) {
            model.setIndividCust(null);
        }
        if (model.getPosOrder().getPosId() == null) {
            model.setPosOrder(null);
        }
        if (model.getStore().getStoreId() == null) {
            model.setStore(null);
        }
        if (model.getOrg().getOrgId() == null) {
            model.setOrg(null);
        }
    }

    /**
     * 审核情况
     * 
     * 
     * @author：WangYuanJun
     * @date：2017年1月6日 上午9:47:35
     */
    public void getRightMaintAudit() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Long rightMaintId = Long.valueOf(params.get("EQ_rightMaint_rightMaintId").toString());
        RightMaint rightMaint = rightMaintLogic.findById(rightMaintId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("EQ_complaintNo", rightMaint.getComplaintNo());
        List<RightMaintAudit> rsList = rightMaintAuditLogic.findAll(map);

        for (RightMaintAudit dt : rsList) {
            dt.setResultName(DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, dt.getResult()));
        }

        Map<String, Object> map2 = new HashMap<String, Object>();
        String[] sort = { "recordInfoId" + "," + Sort.Direction.DESC };
        map2.put("EQ_cardNumber", rightMaint.getComplaintNo());
        List<RecordInfo> recordInfos = recordInfoLogic.findAll(map2, sort);

        List<RightMaintAudit> auditList = new ArrayList<RightMaintAudit>();// “审核情况”页签显示新建人和提交人
        RightMaintAudit dt;
        for (RecordInfo recordInfo : recordInfos) {
            dt = new RightMaintAudit();
            dt.setAuditTime(recordInfo.getCheckDate());
            dt.setAuditor(recordInfo.getCheckUserName());
            dt.setResultName(DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, recordInfo.getCheckResult()));
            auditList.add(dt);
        }
        rsList.addAll(auditList);
        renderJson(rsList);
    }

    /**
     * 右击处理状态
     * 
     * 
     * @author：WangYuanJun
     * @date：2017年1月20日 下午2:31:03
     */
    public String getEvents() {
        try {
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
            buf = new StringBuffer("[");

            List<String> events = new ArrayList<String>();
            RightMaint entity = rightMaintLogic.findById(id);
            if (entity.getReviewState() == 1) {
                events.add(RightStatus.CONFIRM.name());// “新建”时显示“提交”按钮
            }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * 处理流程状态
     */
    public void dealStatus() {
        try {
            long userId = WebUtils.getUserId();
            String userName = WebUtils.getUserName();

            RightMaint dt = rightMaintLogic.findById(id);
            dt.setReviewState(GlobalConst.RIGHTMAINT_SUBMIT);

            // 点击提交(oa审核)：crm->oa
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("complaintNo", dt.getComplaintNo());
            params.put("complaintDate", NeuUtils.parseStringFromCalendar(dt.getComplaintDate()));
            params.put("handleState", dt.getHandleState());
            params.put("cardNo", dt.getIndividCust().getCardNo());
            params.put("name", dt.getIndividCust().getName());
            params.put("mobile", dt.getIndividCust().getMobile());

            PosOrder posOrder = dt.getPosOrder();
            params.put("posbillDate", NeuUtils.parseStringFromCalendar(posOrder.getPosbillDate()));
            params.put("storeName", posOrder.getStoreName());
            params.put("goodsBar", NeuUtils.parseString(posOrder.getGoodsBar()));
            params.put("goodsNo", NeuUtils.parseString(posOrder.getGoodsNo()));
            params.put("actualSaleAmount", posOrder.getActualSaleAmount() == null ? "" : posOrder.getActualSaleAmount());
            params.put("urgencyLevel", dt.getUrgencyLevel());
            params.put("complaintType", dt.getComplaintType());
            params.put("probType", dt.getProbType());
            params.put("complaintLevel", dt.getComplaintLevel());
            params.put("handleStoreNo", dt.getStore() != null ? NeuUtils.parseString(dt.getStore().getName()) : NeuUtils.parseString(dt.getOrg().getName()));
            params.put("handleDate", NeuUtils.parseStringFromCalendar(dt.getHandleDate()));
            params.put("reviewState", NeuUtils.parseString(dt.getReviewState()));
            params.put("probExplain", NeuUtils.parseString(dt.getProbExplain()));
            params.put("customerAdvice", NeuUtils.parseString(dt.getCustomerAdvice()));
            params.put("handleTypeResult", NeuUtils.parseString(dt.getHandleTypeResult()));
            params.put("submitUserNo", WebUtils.getUserCode());

            Map rsMap = httpLogic.post(NeuUtils.getProperty("rightmaint_crm2oa"), "RightMaintAction.dealStatus", GlobalConst.SYS_SRC_CRM, DataUtils.toJson(params));
            int flag = Integer.parseInt(rsMap.get("flag").toString());
            String msg = rsMap.get("msg").toString();
            // oa返回成功后才能保存并提交
            if (flag == 1) {
                // 提交
                rightMaintLogic.merge(dt);
                // 日志
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setCardNumber(dt.getComplaintNo());
                recordInfo.setCheckUserId(userId);
                recordInfo.setCheckUserName(userName);
                recordInfo.setCheckResult(RightStatus.CONFIRM.ordinal());
                recordInfo.setCheckDate(Calendar.getInstance());
                recordInfoLogic.persist(recordInfo);

                dealJson(true);
            } else {
                dealJson(false, "调用OA接口异常：" + msg);
            }
        } catch (Exception e) {
            dealJson(false, e.toString());
            e.printStackTrace();
        }
    }

}