package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.ActiveStatusRemote;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.module.weixin.common.util.StringUtils;
import com.huiju.sms.sms.logic.SmsRemote;

/**
 * 个人客户-积分调整
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午10:23:43
 */
public class CustInteAdjAction extends BaseAction<IntegralAdjHis, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private IntegralAdjHisRemote appLogic;
    @EJB
    private ActiveStatusRemote activeStatusLogic;
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private IndividCustRemote individCustLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.cust.integraladj.js");

        String[] authorities = { "D_CUSTINTEADJ_LIST", "D_CUSTINTEADJ_FREEZING", "D_CUSTINTEADJ_ADJ", "D_CUSTINTEADJ_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    // 积分调整
    public void update() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_individCustId", model.getIndividCust().getIndividCustId());
        IndividCust dt = individCustLogic.find(searchParams);

        // 状态变化-日志
        if (dt.getActive() != GlobalConst.ACTIVE_POSTIVE) {
            ActiveStatus as = new ActiveStatus();
            as.setIndividCust(dt);
            as.setBeforeStatus(dt.getActive());
            as.setAfterStatus(GlobalConst.ACTIVE_POSTIVE);
            as.setReason("手动-积分调整");
            as.setMdate(Calendar.getInstance());
            activeStatusLogic.persist(as);

            dt.setActive(GlobalConst.ACTIVE_POSTIVE);
        }
        model.setCreditBefore(dt.getCredit());
        model.setConvertedCredits(dt.getConvertedCredits());
        model.setCreditStatus(dt.getCreditStatus());
        model.setMuser(WebUtils.getUserName());
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_CUST);
        model.setIndividCust(dt);
        appLogic.persist(model);// 历史表

        dt.setCredit(model.getCreditAfter());
        individCustLogic.merge(dt);

        // 积分变化短信
        smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_9, GlobalConst.SYS_SRC_CRM, dt.getMobile());

        // crm发起同步个人档案信息到外系统
        String msg = individCustLogic.synIndividCustToEx(dt);
        if (StringUtils.isNotBlank(msg)) {
            dealJson(false, msg + GlobalConst.TIP_LINK_ADMIN);
        } else {
            dealJson(true, GlobalConst.TIP_SUCCESS);
        }
    }

    // 冻结解冻积分
    public void integralAdjFreezing() {
        IndividCust dt = individCustLogic.findById(id);

        model.setCreditBefore(dt.getCredit());
        model.setCreditAfter(dt.getCredit());
        model.setConvertedCredits(dt.getConvertedCredits());
        model.setCreditStatus(dt.getCreditStatus());
        model.setMuser(WebUtils.getUserName());
        model.setModReason("冻结/解冻积分");
        model.setModType(GlobalConst.INTEGRAL_CHG_HANDLE);
        model.setCreditOrigin(GlobalConst.SYS_SRC_CRM);
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_CUST);
        model.setIndividCust(dt);
        appLogic.persist(model);// 历史表

        dt.setCreditStatus(dt.getCreditStatus() == 2 ? 1 : 2);
        individCustLogic.merge(dt);

        // crm发起同步个人档案信息到外系统
        String msg = individCustLogic.synIndividCustToEx(dt);
        if (StringUtils.isNotBlank(msg)) {
            dealJson(false, msg + GlobalConst.TIP_LINK_ADMIN);
        } else {
            dealJson(true, GlobalConst.TIP_SUCCESS);
        }
    }

}