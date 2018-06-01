package com.huiju.console.store.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.console.store.entity.Store;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "StoreEaoBean")
public class StoreEaoBean extends GenericEaoImpl<Store, Long> implements StoreEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}