package com.huiju.inter.integral;

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

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.utils.DESUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 积分变更
 * 
 * <pre>
 * {
 *     "integralSrc": 4,
 *     "cardNo": "abc123",
 *     "mobile": 13675112734,
 *     "integralChgVal": 200.54
 * }
 * </pre>
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class IntegralWsBean implements IntegralWs {
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private IntegralAdjHisRemote integralAdjHisLogic;

    @Override
    @WebMethod
    @WebResult(name = "message")
    public String integral2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        Integer integralSrc = null;
        message = DESUtils.getDesString(message);
        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            integralSrc = InterJsonParseUtils.parseInteger(json, "integralSrc", "积分来源", errMap, false);
            String cardNo = InterJsonParseUtils.parseString(json, "cardNo", "会员卡号", errMap, false);
            Double integralChgVal = InterJsonParseUtils.parseDouble(json, "integralChgVal", "积分增减值", errMap, false);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("EQ_cardNo", cardNo);
            IndividCust dt = individCustLogic.find(params);
            if (dt == null) {
                errMap.put("cardNo", cardNo + "在crm系统不存在");
            }

            if (CollectionUtils.isEmpty(errMap)) {
                IndividCust oldDt = dt.clone();
                Double oldCredit = (dt.getCredit() == null ? GlobalConst.ZERO : dt.getCredit());
                Double curCredit = oldCredit + integralChgVal;

                // 原已兑换积分
                Double oldConvertedCredits = dt.getConvertedCredits() == null ? GlobalConst.ZERO : dt.getConvertedCredits();
                if (integralChgVal < 0) {
                    dt.setConvertedCredits(oldConvertedCredits - integralChgVal);
                }
                dt.setCredit(curCredit);
                dt.setActive(integralAdjHisLogic.getCustActive(GlobalConst.CUST_TYPE_CUST, dt.getIndividCustId()));
                individCustLogic.merge(dt);

                // 积分变化短信
                smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_9, integralSrc, dt.getMobile());

                IntegralAdjHis his = new IntegralAdjHis();
                his.setCreditBefore(oldCredit);
                his.setCreditAfter(curCredit);
                his.setConvertedCredits(dt.getConvertedCredits());
                his.setCreditStatus(1);
                his.setMdate(Calendar.getInstance());
                his.setModReason(integralChgVal >= 0 ? "耀我网-活动" : "耀我网-积分兑换礼品");
                his.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
                his.setCreditOrigin(GlobalConst.SYS_SRC_YW);
                his.setIndividCust(dt);
                his.setCustType(GlobalConst.CUST_TYPE_CUST);
                integralAdjHisLogic.persist(his);// 历史表

                // 同步客户档案到外系统
                if (integralSrc == GlobalConst.SYS_SRC_YW) {
                    individCustLogic.synIndividCust(integralSrc, GlobalConst.SYS_SRC_WECHAR, oldDt, dt);
                } else if (integralSrc == GlobalConst.SYS_SRC_WECHAR) {
                    individCustLogic.synIndividCust(integralSrc, GlobalConst.SYS_SRC_YW, oldDt, dt);
                }
                individCustLogic.synIndividCust(integralSrc, GlobalConst.SYS_SRC_NC, dt);

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
            interLog.setCrmClassMethod("IntegralWsBean.integral2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setStatus(flag);
            interLog.setSrc(integralSrc);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

}