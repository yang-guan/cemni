package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "OperationLogEaoBean")
public class OperationLogEaoBean extends GenericEaoImpl<OperationLog, Long> implements OperationLogEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}