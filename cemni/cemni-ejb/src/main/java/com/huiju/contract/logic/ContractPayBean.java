package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractPayEaoLocal;
import com.huiju.contract.entity.ContractPay;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractPayBean")
public class ContractPayBean extends GenericLogicImpl<ContractPay, Long> implements ContractPayRemote {
    @EJB(mappedName = "ContractPayEaoBean")
    private ContractPayEaoLocal contractPayEao;

    @Override
    protected GenericEao<ContractPay, Long> getGenericEao() {
        return contractPayEao;
    }
    
    
}