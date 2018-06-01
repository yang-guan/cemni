package com.huiju.expandbusi.franchiseeprofit.expandcost.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.expandcost.eao.ExpandCostEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.expandcost.entity.ExpandCost;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ExpandCostBean")
public class ExpandCostBean extends GenericLogicImpl<ExpandCost, Long> implements ExpandCostRemote {
    @EJB(mappedName = "ExpandCostEaoBean")
    private ExpandCostEaoLocal expandcostEao;

    @Override
    protected GenericEao<ExpandCost, Long> getGenericEao() {
        return expandcostEao;
    }
}