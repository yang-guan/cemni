package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.contract.entity.ContractBrand;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractBrandEaoBean")
public class ContractBrandEaoBean extends GenericEaoImpl<ContractBrand, Long> implements ContractBrandEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}