package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractAdmin;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractAdminEaoBean")
public class ContractAdminEaoBean extends GenericEaoImpl<ContractAdmin, Long> implements ContractAdminEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}