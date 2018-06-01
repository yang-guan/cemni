package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.groupcust.logic.GroupCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 团体客户-积分调整
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午10:24:38
 */
public class GroupInteAdjAction extends BaseAction<IntegralAdjHis, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private IntegralAdjHisRemote appLogic;
    @EJB
    private GroupCustRemote groupCustLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.group.integraladj.js");

        String[] authorities = { "D_GROUPINTEADJ_LIST", "D_GROUPINTEADJ_FREEZING", "D_GROUPINTEADJ_ADJ", "D_GROUPINTEADJ_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    // 积分调整
    public void update() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_groupCustId", model.getGroupCust().getGroupCustId());
        GroupCust dt = groupCustLogic.find(searchParams);
        dt.setActive(appLogic.getCustActive(GlobalConst.CUST_TYPE_GROUP, dt.getGroupCustId()));

        model.setCreditBefore(dt.getCredit());
        model.setConvertedCredits(dt.getConvertedCredits());
        model.setCreditStatus(dt.getCreditStatus());
        model.setMuser(WebUtils.getUserName());
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_GROUP);
        model.setGroupCust(dt);
        appLogic.persist(model);// 历史表

        dt.setCredit(model.getCreditAfter());
        groupCustLogic.merge(dt);

        dealJson(true);
    }

    // 冻结解冻积分
    public void integralAdjFreezing() {
        GroupCust dt = groupCustLogic.findById(id);
        Integer creditStatus = dt.getCreditStatus();

        model.setCreditBefore(dt.getCredit());
        model.setCreditAfter(dt.getCredit());
        model.setConvertedCredits(dt.getConvertedCredits());
        model.setCreditStatus(creditStatus);
        model.setMuser(WebUtils.getUserName());
        model.setModReason("冻结/解冻积分");
        model.setModType(GlobalConst.INTEGRAL_CHG_HANDLE);
        model.setCreditOrigin(GlobalConst.SYS_SRC_CRM);
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_GROUP);
        model.setGroupCust(dt);
        appLogic.persist(model);// 历史表

        dt.setCreditStatus(creditStatus == 2 ? 1 : 2);
        groupCustLogic.merge(dt);
        dealJson(true);
    }

}