package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.GoodStatus;

@Stateless(mappedName = "GoodStatusEaoBean")
public class GoodStatusEaoBean extends GenericEaoImpl<GoodStatus, Long> implements GoodStatusEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}