package com.huiju.expandbusi.franchiseevalue.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseevalue.entity.StoreCost;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "StoreCostEaoBean")
public class StoreCostEaoBean extends GenericEaoImpl<StoreCost, Long> implements StoreCostEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}