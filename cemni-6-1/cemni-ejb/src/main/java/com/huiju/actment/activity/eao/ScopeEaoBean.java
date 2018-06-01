package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.Scope;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ScopeEaoBean")
public class ScopeEaoBean extends GenericEaoImpl<Scope, Long> implements ScopeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}