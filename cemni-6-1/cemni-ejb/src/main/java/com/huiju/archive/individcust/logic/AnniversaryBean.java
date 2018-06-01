package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.AnniversaryEaoLocal;
import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "AnniversaryBean")
public class AnniversaryBean extends GenericLogicImpl<Anniversary, Long> implements AnniversaryRemote {
    @EJB
    private AnniversaryEaoLocal anniversaryEao;

    @Override
    protected GenericEao<Anniversary, Long> getGenericEao() {
        return anniversaryEao;
    }
}