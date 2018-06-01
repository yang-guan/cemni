package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.MarActivittyEaoLocal;
import com.huiju.competitor.entity.MarActivitty;

@Stateless(mappedName = "MarActivittyBean")
public class MarActivittyBean extends GenericLogicImpl<MarActivitty, Long> implements MarActivittyRemote {
    @EJB(mappedName = "MarActivittyEaoBean")
    private MarActivittyEaoLocal competitorTMEao;

    @Override
    protected GenericEao<MarActivitty, Long> getGenericEao() {
        return competitorTMEao;
    }
}