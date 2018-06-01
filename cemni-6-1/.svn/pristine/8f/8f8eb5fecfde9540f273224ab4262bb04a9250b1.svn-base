package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractPay;
import com.huiju.module.data.eao.GenericEaoImpl;
@Stateless(mappedName = "ContractPayEaoBean")
public class ContractPayEaoBean extends GenericEaoImpl<ContractPay, Long> implements ContractPayEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}