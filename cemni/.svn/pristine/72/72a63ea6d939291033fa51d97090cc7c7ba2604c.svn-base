package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.GoodStatusEaoLocal;
import com.huiju.competitor.entity.GoodStatus;

@Stateless(mappedName = "GoodStatusBean")
public class GoodStatusBean extends GenericLogicImpl<GoodStatus, Long> implements GoodStatusRemote {
    @EJB(mappedName = "GoodStatusEaoBean")
    private GoodStatusEaoLocal competitorGSEao;

    @Override
    protected GenericEao<GoodStatus, Long> getGenericEao() {
        return competitorGSEao;
    }
}