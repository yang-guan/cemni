package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.SzNewStylesEaoLocal;
import com.huiju.competitor.entity.SzNewStyles;

@Stateless(mappedName = "SzNewStylesBean")
public class SzNewStylesBean extends GenericLogicImpl<SzNewStyles, Long> implements SzNewStylesRemote {
    @EJB(mappedName = "SzNewStylesEaoBean")
    private SzNewStylesEaoLocal competitorSZNSEao;

    @Override
    protected GenericEao<SzNewStyles, Long> getGenericEao() {
        return competitorSZNSEao;
    }
}