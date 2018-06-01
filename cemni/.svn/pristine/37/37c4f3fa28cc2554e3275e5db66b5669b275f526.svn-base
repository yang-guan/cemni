package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ActiveStatusEaoBean")
public class ActiveStatusEaoBean extends GenericEaoImpl<ActiveStatus, Long> implements ActiveStatusEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}