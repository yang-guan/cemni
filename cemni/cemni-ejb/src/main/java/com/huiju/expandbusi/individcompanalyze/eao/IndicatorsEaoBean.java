package com.huiju.expandbusi.individcompanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "IndicatorsEaoBean")
public class IndicatorsEaoBean extends GenericEaoImpl<Indicators, Long> implements IndicatorsEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}