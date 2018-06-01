package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractOpEaoLocal;
import com.huiju.contract.entity.ContractOp;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractOpBean")
public class ContractOpBean extends GenericLogicImpl<ContractOp, Long> implements ContractOpRemote {
    @EJB(mappedName = "ContractOpEaoBean")
    private ContractOpEaoLocal contractOpEao;

    @Override
    protected GenericEao<ContractOp, Long> getGenericEao() {
        return contractOpEao;
    }
}