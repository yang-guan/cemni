package com.huiju.integral;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.integral.graderule.entity.GradeRule;
import com.huiju.integral.graderule.logic.GradeRuleRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 积分规则
 * 
 * @author：yuhb
 * @date：2016年12月28日 下午10:25:19
 */
public class GradeRuleAction extends BaseAction<GradeRule, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private GradeRuleRemote gradeRuleLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.graderule.js");

        String[] authorities = { "D_GRADERULE_LIST" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<GradeRule> page = new Page<GradeRule>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = gradeRuleLogic.findAll(page, params);

        List<GradeRule> rsList = page.getResult();
        for (GradeRule dt : rsList) {
            dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
        }
        renderJson(page);
    }

}