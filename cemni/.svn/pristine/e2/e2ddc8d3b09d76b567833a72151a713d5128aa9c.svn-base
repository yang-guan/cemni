package com.huiju.expandbusi.franchiseeprofit.costsupport.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.costsupport.eao.CostSupportEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.costsupport.entity.CostSupport;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "CostSupportBean")
public class CostSupportBean extends GenericLogicImpl<CostSupport, Long> implements CostSupportRemote {
    @EJB(mappedName = "CostSupportEaoBean")
    private CostSupportEaoLocal costsupportEao;

    @Override
    protected GenericEao<CostSupport, Long> getGenericEao() {
        return costsupportEao;
    }
}