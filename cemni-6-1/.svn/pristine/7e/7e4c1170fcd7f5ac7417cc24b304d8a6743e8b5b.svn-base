package com.huiju.archive.groupcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.groupcust.eao.CompetitorProductEaoLocal;
import com.huiju.archive.groupcust.entity.CompetitorProduct;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "CompetitorProductBean")
public class CompetitorProductBean extends GenericLogicImpl<CompetitorProduct, Long> implements CompetitorProductRemote {
    @EJB(mappedName = "CompetitorProductEaoBean")
    private CompetitorProductEaoLocal competitorProductEao;

    @Override
    protected GenericEao<CompetitorProduct, Long> getGenericEao() {
        return competitorProductEao;
    }

}