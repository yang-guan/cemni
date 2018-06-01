package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.gradeadj.logic.GradeAdjHisRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.module.weixin.common.util.StringUtils;
import com.huiju.sms.sms.logic.SmsRemote;

/**
 * 个人客户-等级调整
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午10:23:20
 */
public class CustGradeAdjAction extends BaseAction<GradeAdjHis, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private GradeAdjHisRemote appLogic;
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private IndividCustRemote individCustLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.cust.gradeadj.js");

        String[] authorities = { "D_CUSTGRADEADJ_LIST", "D_CUSTGRADEADJ_EDIT", "D_CUSTGRADEADJ_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void update() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_individCustId", model.getIndividCust().getIndividCustId());
        IndividCust dt = individCustLogic.find(searchParams);

        model.setLvBefore(dt.getLv());
        model.setJewerlyAmount(dt.getJewerlyAmount());
        model.setMuser(WebUtils.getUserName());
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_CUST);
        model.setIndividCust(dt);
        appLogic.persist(model);// 历史表

        dt.setLv(model.getLvAfter());
        individCustLogic.merge(dt);

        // 等级变化短信
        smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_8, GlobalConst.SYS_SRC_CRM, dt.getMobile());

        // crm发起同步个人档案信息到外系统
        String msg = individCustLogic.synIndividCustToEx(dt);
        if (StringUtils.isNotBlank(msg)) {
            dealJson(false, msg + GlobalConst.TIP_LINK_ADMIN);
        } else {
            dealJson(true, GlobalConst.TIP_SUCCESS);
        }
    }

}