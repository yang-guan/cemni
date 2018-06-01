package com.huiju.expandbusi.salesmananalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.salesmananalyze.eao.StoredetailEaoLocal;
import com.huiju.expandbusi.salesmananalyze.entity.Storedetail;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "StoredetailBean")
public class StoredetailBean extends GenericLogicImpl<Storedetail, Long> implements StoredetailRemote {
    @EJB(mappedName = "StoredetailEaoBean")
    private StoredetailEaoLocal StoredetailEao;

    @Override
    protected GenericEao<Storedetail, Long> getGenericEao() {
        return StoredetailEao;
    }
}