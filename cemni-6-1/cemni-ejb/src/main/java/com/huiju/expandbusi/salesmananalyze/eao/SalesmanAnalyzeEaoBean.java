package com.huiju.expandbusi.salesmananalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.expandbusi.salesmananalyze.entity.SalesmanAnalyze;

@Stateless(mappedName = "SalesmanAnalyzeEaoBean")
public class SalesmanAnalyzeEaoBean extends GenericEaoImpl<SalesmanAnalyze, Long> implements SalesmanAnalyzeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}