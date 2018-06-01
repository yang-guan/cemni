package com.huiju.expandbusi.franchiseevalue.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseevalue.eao.StoreCostEaoLocal;
import com.huiju.expandbusi.franchiseevalue.entity.StoreCost;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "StoreCostBean")
public class StoreCostBean extends GenericLogicImpl<StoreCost, Long> implements StoreCostRemote {
    @EJB(mappedName = "StoreCostEaoBean")
    private StoreCostEaoLocal storeCostEao;

    @Override
    protected GenericEao<StoreCost, Long> getGenericEao() {
        return storeCostEao;
    }
}