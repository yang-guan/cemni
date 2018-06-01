package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.groupcust.logic.GroupCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.gradeadj.logic.GradeAdjHisRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 团体客户-等级调整
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午10:24:44
 */
public class GroupGradeAdjAction extends BaseAction<GradeAdjHis, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private GradeAdjHisRemote appLogic;
    @EJB
    private GroupCustRemote groupCustLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.group.gradeadj.js");

        String[] authorities = { "D_GROUPGRADEADJ_LIST", "D_GROUPGRADEADJ_EDIT", "D_GROUPGRADEADJ_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void update() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_groupCustId", model.getGroupCust().getGroupCustId());
        GroupCust dt = groupCustLogic.find(searchParams);

        model.setLvBefore(dt.getLv());
        model.setJewerlyAmount(dt.getJewerlyAmount());
        model.setMuser(WebUtils.getUserName());
        model.setMdate(Calendar.getInstance());
        model.setCustType(GlobalConst.CUST_TYPE_GROUP);
        model.setGroupCust(dt);
        appLogic.persist(model);// 历史表

        dt.setLv(model.getLvAfter());
        groupCustLogic.merge(dt);
        dealJson(true);
    }

}