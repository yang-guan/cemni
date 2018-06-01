package com.huiju.contract.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.eao.ContractAdminEaoLocal;
import com.huiju.contract.entity.ContractAdmin;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ContractAdminBean")
public class ContractAdminBean extends GenericLogicImpl<ContractAdmin, Long> implements ContractAdminRemote {
    @EJB(mappedName = "ContractAdminEaoBean")
    private ContractAdminEaoLocal contractAdminEao;

    @Override
    protected GenericEao<ContractAdmin, Long> getGenericEao() {
        return contractAdminEao;
    }
}