package com.huiju.actment.judge;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.entity.JudgeAct;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.actment.activity.logic.JudgeActRemote;
import com.huiju.common.GlobalConst;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

public class JudgeActAction extends BaseAction<JudgeAct, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "JudgeActBean")
    private JudgeActRemote judgeActLogic;

    @EJB(mappedName = "ActivityBean")
    private ActivityRemote activityLogic;

    public void getJudge() {
        DecimalFormat df = new DecimalFormat("##.##%");

        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<JudgeAct> rsList = judgeActLogic.findAll(params);
        for (JudgeAct dt : rsList) {
            dt.setTransRate(String.valueOf(df.format(dt.getSigningFra() / dt.getPotentialFra())));
        }
        renderJson(rsList, "activity.judgeAct");
    }

    public void edit() {
        model = judgeActLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        Activity act = activityLogic.findById(id);
        if (act.getJudgeAct() == null) {
            model.setActivity(act);
            judgeActLogic.persist(model);
        } else {
            act.setStatus(GlobalConst.ACT_STATUS_4);
            activityLogic.merge(act);
            judgeActLogic.merge(model);
        }
        dealJson(true);
    }

}