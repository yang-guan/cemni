package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractComEaoLocal;
import com.huiju.contract.entity.ContractCom;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractComBean")
public class ContractComBean extends GenericLogicImpl<ContractCom, Long> implements ContractComRemote {
    @EJB(mappedName = "ContractComEaoBean")
    private ContractComEaoLocal contractComEao;

    @Override
    protected GenericEao<ContractCom, Long> getGenericEao() {
        return contractComEao;
    }
}