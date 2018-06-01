package com.huiju.inter.afterserv.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.afterserv.entity.AfterServ;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "AfterServEaoBean")
public class AfterServEaoBean extends GenericEaoImpl<AfterServ, Long> implements AfterServEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}