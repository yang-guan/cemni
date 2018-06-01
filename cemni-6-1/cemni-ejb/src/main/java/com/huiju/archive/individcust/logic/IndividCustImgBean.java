package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.IndividCustImgEaoLocal;
import com.huiju.archive.individcust.entity.IndividCustImg;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "IndividCustImgBean")
public class IndividCustImgBean extends GenericLogicImpl<IndividCustImg, Long> implements IndividCustImgRemote {
    @EJB
    private IndividCustImgEaoLocal individCustImgEao;

    @Override
    protected GenericEao<IndividCustImg, Long> getGenericEao() {
        return individCustImgEao;
    }
}