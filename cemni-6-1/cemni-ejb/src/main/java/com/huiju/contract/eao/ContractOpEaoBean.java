package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractOp;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractOpEaoBean")
public class ContractOpEaoBean extends GenericEaoImpl<ContractOp, Long> implements ContractOpEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}