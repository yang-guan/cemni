package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.ExpectCost;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ExpectCostEaoBean")
public class ExpectCostEaoBean extends GenericEaoImpl<ExpectCost, Long> implements ExpectCostEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}