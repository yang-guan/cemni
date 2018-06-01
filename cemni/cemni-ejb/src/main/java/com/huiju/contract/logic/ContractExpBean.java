package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractExpEaoLocal;
import com.huiju.contract.entity.ContractExp;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractExpBean")
public class ContractExpBean extends GenericLogicImpl<ContractExp, Long> implements ContractExpRemote {
    @EJB(mappedName = "ContractExpEaoBean")
    private ContractExpEaoLocal contractExpEao;

    @Override
    protected GenericEao<ContractExp, Long> getGenericEao() {
        return contractExpEao;
    }
}