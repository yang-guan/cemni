package com.huiju.integral;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.DataDict;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 个人积分历史
 * 
 * @author zzy
 * @date 2017年4月10日 下午3:04:01
 */
public class CustAdjHisAction extends BaseAction<IntegralAdjHis, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private IntegralAdjHisRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/integral/Q.cust.AdjHis.js");

        String[] authorities = { "D_CUST_ADJHIS_LIST", "D_CUST_ADJHIS_SEARCH" };
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
        Page<IntegralAdjHis> page = new Page<IntegralAdjHis>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page.desc("mdate");
        page = appLogic.findAll(page, searchParam);
        for (IntegralAdjHis dt : page) {
            dt.setCreditStatusName(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
        }
        renderJson(page);
    }

}