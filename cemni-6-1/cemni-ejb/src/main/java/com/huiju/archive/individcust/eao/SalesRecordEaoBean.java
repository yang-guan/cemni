package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.SalesRecord;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "SalesRecordEaoBean")
public class SalesRecordEaoBean extends GenericEaoImpl<SalesRecord, Long> implements SalesRecordEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}