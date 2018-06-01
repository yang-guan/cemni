package com.huiju.archive.franchisee.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.franchisee.eao.CreditEaoLocal;
import com.huiju.archive.franchisee.entity.Credit;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "CreditBean")
public class CreditBean extends GenericLogicImpl<Credit, Long> implements CreditRemote {
    @EJB
    private CreditEaoLocal CreditEao;

    @Override
    protected GenericEao<Credit, Long> getGenericEao() {
        return CreditEao;
    }
}
