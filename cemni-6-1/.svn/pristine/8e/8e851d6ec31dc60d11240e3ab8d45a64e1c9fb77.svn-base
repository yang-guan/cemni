package com.huiju.archive.franchisee.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.franchisee.entity.Credit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "CreditEaoBean")
public class CreditEaoBean extends GenericEaoImpl<Credit, Long> implements CreditEaoLocal {
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
