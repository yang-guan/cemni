package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.contract.entity.ContractContact;

@Stateless(mappedName = "ContractContactEaoBean")
public class ContractContactEaoBean extends GenericEaoImpl<ContractContact, Long> implements ContractContactEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}