package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.Competitor;

@Stateless(mappedName = "CompetitorEaoBean")
public class CompetitorEaoBean extends GenericEaoImpl<Competitor, Long> implements CompetitorEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}