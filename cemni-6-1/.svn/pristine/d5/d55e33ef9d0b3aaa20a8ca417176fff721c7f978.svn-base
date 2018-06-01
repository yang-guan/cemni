package com.huiju.expandbusi.memcompanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.memcompanalyze.entity.MemCompAnalyze;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "MemCompAnalyzeEaoBean")
public class MemCompAnalyzeEaoBean extends GenericEaoImpl<MemCompAnalyze, Long> implements MemCompAnalyzeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}