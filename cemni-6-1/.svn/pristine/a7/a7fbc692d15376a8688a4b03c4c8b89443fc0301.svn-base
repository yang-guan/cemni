package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractCom;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractComEaoBean")
public class ContractComEaoBean extends GenericEaoImpl<ContractCom, Long> implements ContractComEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}