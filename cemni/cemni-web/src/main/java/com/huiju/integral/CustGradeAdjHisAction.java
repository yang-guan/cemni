package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.DataDict;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.gradeadj.logic.GradeAdjHisRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 个人等级历史
 * 
 * @author zzy
 * @date 2017年5月2日 下午3:04:01
 */
public class CustGradeAdjHisAction extends BaseAction<GradeAdjHis, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private GradeAdjHisRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.cust.GradeAdjHis.js");

        String[] authorities = { "D_GRADE_ADJHIS_LIST", "D_GRADE_ADJHIS_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        Object LE_mdate = searchParam.get("LE_mdate");
        if (LE_mdate != null && !StringUtils.isEmpty(LE_mdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_mdate.toString());
            cl.add(Calendar.DATE, 1);
            searchParam.put("LE_mdate", NeuUtils.parseStringFromCalendar(cl));
        }
        Page<GradeAdjHis> page = new Page<GradeAdjHis>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page.desc("mdate");
        page = appLogic.findAll(page, searchParam);
        for (GradeAdjHis dt : page) {
            if (null == dt.getIndividCust().getJewerlyAmount()) {
                dt.getIndividCust().setJewerlyAmount(0D);
            }
            dt.getIndividCust().setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getIndividCust().getLv()));
            dt.setLvAfterName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLvAfter()));
            dt.setLvBeforeName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLvBefore()));
        }
        renderJson(page);
    }

}