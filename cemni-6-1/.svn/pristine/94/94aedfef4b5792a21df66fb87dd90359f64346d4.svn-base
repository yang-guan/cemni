package com.huiju.expandbusi.agentanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.agentanalyze.entity.Detailed;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "DetailedEaoBean")
public class DetailedEaoBean extends GenericEaoImpl<Detailed, Long> implements DetailedEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}