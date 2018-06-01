package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractExp;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractExpEaoBean")
public class ContractExpEaoBean extends GenericEaoImpl<ContractExp, Long> implements ContractExpEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}