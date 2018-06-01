package com.huiju.inter.interLog.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "InterLogEaoBean")
public class InterLogEaoBean extends GenericEaoImpl<InterLog, Long> implements InterLogEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}