package com.huiju.expandbusi.salesmananalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.salesmananalyze.entity.Storedetail;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "StoredetailEaoBean")
public class StoredetailEaoBean extends GenericEaoImpl<Storedetail, Long> implements StoredetailEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}