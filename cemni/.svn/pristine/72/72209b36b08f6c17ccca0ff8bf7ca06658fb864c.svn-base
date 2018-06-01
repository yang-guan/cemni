package com.huiju.archive.groupcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.groupcust.entity.CompetitorProduct;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "CompetitorProductEaoBean")
public class CompetitorProductEaoBean extends GenericEaoImpl<CompetitorProduct, Long> implements CompetitorProductEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}