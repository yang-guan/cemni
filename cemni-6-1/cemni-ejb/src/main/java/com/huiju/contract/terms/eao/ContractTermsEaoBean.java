package com.huiju.contract.terms.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.contract.terms.entity.ContractTerms;

@Stateless(mappedName = "ContractTermsEaoBean")
public class ContractTermsEaoBean extends GenericEaoImpl<ContractTerms, Long> implements ContractTermsEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}