package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractBrandEaoLocal;
import com.huiju.contract.entity.ContractBrand;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractBrandBean")
public class ContractBrandBean extends GenericLogicImpl<ContractBrand, Long> implements ContractBrandRemote {
    @EJB(mappedName = "ContractBrandEaoBean")
    private ContractBrandEaoLocal contractBrandEao;

    @Override
    protected GenericEao<ContractBrand, Long> getGenericEao() {
        return contractBrandEao;
    }
}