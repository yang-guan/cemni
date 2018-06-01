package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.IndividCustFamily;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "IndividCustFamilyEaoBean")
public class IndividCustFamilyEaoBean extends GenericEaoImpl<IndividCustFamily, Long> implements IndividCustFamilyEaoLocal {
	
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}