package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.IndividCustFamilyEaoLocal;
import com.huiju.archive.individcust.entity.IndividCustFamily;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "IndividCustFamilyBean")
public class IndividCustFamilyBean extends GenericLogicImpl<IndividCustFamily, Long> implements IndividCustFamilyRemote {
    @EJB
    private IndividCustFamilyEaoLocal individCustFamilyEao;

    @Override
    protected GenericEao<IndividCustFamily, Long> getGenericEao() {
        return individCustFamilyEao;
    }
}