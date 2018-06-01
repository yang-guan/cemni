package com.huiju.expandbusi.memcompanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.memcompanalyze.entity.Memdetail;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "MemdetailEaoBean")
public class MemdetailEaoBean extends GenericEaoImpl<Memdetail, Long> implements MemdetailEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}