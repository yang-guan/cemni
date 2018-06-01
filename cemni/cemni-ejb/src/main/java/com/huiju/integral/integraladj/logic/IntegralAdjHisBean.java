package com.huiju.integral.integraladj.logic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.GlobalConst;
import com.huiju.integral.integraladj.eao.IntegralAdjHisEaoLocal;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.util.CollectionUtils;

@Stateless(mappedName = "IntegralAdjHisBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IntegralAdjHisBean extends GenericLogicImpl<IntegralAdjHis, Long> implements IntegralAdjHisRemote {
    @EJB(mappedName = "IntegralAdjHisEaoBean")
    private IntegralAdjHisEaoLocal integraladjhisEao;

    @Override
    protected GenericEao<IntegralAdjHis, Long> getGenericEao() {
        return integraladjhisEao;
    }

    @Override
    public int getCustActive(int custType, Long custId) {
        Map searchParam = new HashMap();
        searchParam.put("EQ_custType", custType);

        if (custType == GlobalConst.CUST_TYPE_CUST) {
            searchParam.put("EQ_individCust_individCustId", custId);
        } else {
            searchParam.put("EQ_groupCust_groupCustId", custId);
        }
        String[] sort = { "integralAdjHisId,desc" };
        List<IntegralAdjHis> rsList = this.findAll(searchParam, sort);

        // 默认活跃
        int active = GlobalConst.ACTIVE_POSTIVE;
        if (!CollectionUtils.isEmpty(rsList)) {
            long betweenDays = (Calendar.getInstance().getTimeInMillis() - rsList.get(0).getMdate().getTimeInMillis()) / (1000 * 3600 * 24);
            if (betweenDays <= GlobalConst.DAYS_ACTIVE) {
                // TODO
            } else if (betweenDays <= GlobalConst.DAYS_SLEEP) {
                active = GlobalConst.ACTIVE_SLEEP;
            } else if (betweenDays > GlobalConst.DAYS_SLEEP) {
                active = GlobalConst.ACTIVE_HIS;
            }
        }
        return active;
    }

}