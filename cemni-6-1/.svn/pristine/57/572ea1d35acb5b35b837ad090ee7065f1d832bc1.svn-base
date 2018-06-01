package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractFeeEaoLocal;
import com.huiju.contract.entity.ContractFee;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractFeeBean")
public class ContractFeeBean extends GenericLogicImpl<ContractFee, Long> implements ContractFeeRemote {
    @EJB(mappedName = "ContractFeeEaoBean")
    private ContractFeeEaoLocal contractFeeEao;

    @Override
    protected GenericEao<ContractFee, Long> getGenericEao() {
        return contractFeeEao;
    }
    
    
}