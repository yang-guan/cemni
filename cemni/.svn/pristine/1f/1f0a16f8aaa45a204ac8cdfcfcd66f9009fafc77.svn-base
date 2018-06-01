package com.huiju.expandbusi.franchiseeprofit.revenue.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.revenue.eao.RevenueEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.revenue.entity.Revenue;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "RevenueBean")
public class RevenueBean extends GenericLogicImpl<Revenue, Long> implements RevenueRemote {
    @EJB(mappedName = "RevenueEaoBean")
    private RevenueEaoLocal revenueEao;

    @Override
    protected GenericEao<Revenue, Long> getGenericEao() {
        return revenueEao;
    }
}