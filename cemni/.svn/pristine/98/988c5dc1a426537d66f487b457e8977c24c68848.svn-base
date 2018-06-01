package com.huiju.contract.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.contract.entity.ContractFee;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ContractFeeEaoBean")
public class ContractFeeEaoBean extends GenericEaoImpl<ContractFee, Long> implements ContractFeeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}