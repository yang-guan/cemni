package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.Contract;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractEaoBean")
public class ContractEaoBean extends GenericEaoImpl<Contract, Long> implements ContractEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}