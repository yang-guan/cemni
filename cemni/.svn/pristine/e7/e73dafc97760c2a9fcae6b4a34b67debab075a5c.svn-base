package com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.eao.FranchiseeProfitEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity.FranchiseeProfit;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "FranchiseeProfitBean")
public class FranchiseeProfitBean extends GenericLogicImpl<FranchiseeProfit, Long> implements FranchiseeProfitRemote {
    @EJB(mappedName = "FranchiseeProfitEaoBean")
    private FranchiseeProfitEaoLocal franchiseeprofitEao;

    @Override
    protected GenericEao<FranchiseeProfit, Long> getGenericEao() {
        return franchiseeprofitEao;
    }
}