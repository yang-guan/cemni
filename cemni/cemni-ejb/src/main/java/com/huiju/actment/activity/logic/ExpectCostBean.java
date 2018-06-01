package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.ExpectCostEaoLocal;
import com.huiju.actment.activity.entity.ExpectCost;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ExpectCostBean")
public class ExpectCostBean extends GenericLogicImpl<ExpectCost, Long> implements ExpectCostRemote {
    @EJB
    private ExpectCostEaoLocal ExpectCostEao;

    @Override
    protected GenericEao<ExpectCost, Long> getGenericEao() {
        return ExpectCostEao;
    }
}