package com.huiju.expandbusi.franchiseeprofit.profit.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.profit.eao.ProfitEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.profit.entity.Profit;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ProfitBean")
public class ProfitBean extends GenericLogicImpl<Profit, Long> implements ProfitRemote {
    @EJB(mappedName = "ProfitEaoBean")
    private ProfitEaoLocal profitEao;

    @Override
    protected GenericEao<Profit, Long> getGenericEao() {
        return profitEao;
    }
}