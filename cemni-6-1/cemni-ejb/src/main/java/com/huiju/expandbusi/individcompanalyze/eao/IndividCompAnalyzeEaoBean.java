package com.huiju.expandbusi.individcompanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "IndividCompAnalyzeEaoBean")
public class IndividCompAnalyzeEaoBean extends GenericEaoImpl<IndividCompAnalyze, Long> implements IndividCompAnalyzeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}