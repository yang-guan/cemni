package com.huiju.expandbusi.individcompanalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.individcompanalyze.eao.IndicatorsEaoLocal;
import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "IndicatorsBean")
public class IndicatorsBean extends GenericLogicImpl<Indicators, Long> implements IndicatorsRemote {
    @EJB(mappedName = "IndicatorsEaoBean")
    private IndicatorsEaoLocal indicatorsEao;

    @Override
    protected GenericEao<Indicators, Long> getGenericEao() {
        return indicatorsEao;
    }
}