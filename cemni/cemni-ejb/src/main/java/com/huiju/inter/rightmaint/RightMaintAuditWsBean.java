package com.huiju.inter.rightmaint;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.huiju.afterservice.rightmaint.entity.RightMaint;
import com.huiju.afterservice.rightmaint.logic.RightMaintRemote;
import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.inter.rightmaint.entity.RightMaintAudit;
import com.huiju.inter.rightmaint.logic.RightMaintAuditRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.DESUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 客户权益审核
 * 
 * <pre>
 * {
 *     "complaintNo": "EQ2016121302",
 *     "result": 1,
 *     "status": 3,
 *     "auditor": "李明",
 *     "suggest": "审核结果通过",
 *     "auditTime": "2016-12-11 11:11:11"
 * }
 * </pre>
 * 
 * @author：WangYuanJun
 * @date：2017年1月4日 下午5:29:48
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class RightMaintAuditWsBean implements RightMaintAuditWs {
    @EJB
    private RightMaintAuditRemote rightMaintAuditLogic;
    @EJB
    private RightMaintRemote rightMaintLogic;
    @EJB
    private InterLogRemote logLogic;

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
            String complaintNo = InterJsonParseUtils.parseString(json, "complaintNo", "权益单号", errMap, false);
            String auditor = InterJsonParseUtils.parseString(json, "auditor", "审核人", errMap, false);
            Integer result = InterJsonParseUtils.parseInteger(json, "result", "审核结果", errMap, false);
            String suggest = InterJsonParseUtils.parseString(json, "suggest", "审核意见", errMap, false);
            Calendar auditTime = InterJsonParseUtils.parseCalendar(json, "auditTime", "审核时间", "yyyy-MM-dd HH:mm:ss", errMap, false);
            Integer status = InterJsonParseUtils.parseInteger(json, "status", "审核状态", errMap, false);

            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("EQ_complaintNo", complaintNo);
            RightMaint rightMaint = rightMaintLogic.find(searchMap);
            if (rightMaint == null) {
                errMap.put("complaintNo", complaintNo + "在crm系统不存在");
            }

            if (CollectionUtils.isEmpty(errMap)) {
                RightMaintAudit dt = new RightMaintAudit();
                dt.setComplaintNo(complaintNo);
                dt.setAuditor(auditor);
                dt.setResult(result);
                dt.setSuggest(suggest);
                dt.setAuditTime(auditTime);
                dt.setStatus(status);
                rightMaintAuditLogic.persist(dt);// 保存审核情况

                // 处理状态->已处理
                if (status.equals(GlobalConst.RIGHTMAINT_PASS) || status.equals(GlobalConst.RIGHTMAINT_NOPASS)) {
                    rightMaint.setHandleState(GlobalConst.RIGHTMAINT_HANDLE);
                }

                rightMaint.setReviewState(status);
                rightMaintLogic.merge(rightMaint);

                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
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
            interLog.setCrmClassMethod("RightMaintAuditWsBean.oa2crm");
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

}