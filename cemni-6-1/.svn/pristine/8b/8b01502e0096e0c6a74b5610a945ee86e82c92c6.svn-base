package com.huiju.archive.franchisee.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.archive.franchisee.eao.FranchiseeEaoLocal;
import com.huiju.archive.franchisee.entity.Franchisee;

@Stateless(mappedName = "FranchiseeBean")
public class FranchiseeBean extends GenericLogicImpl<Franchisee, Long> implements FranchiseeRemote {
    @EJB
    private FranchiseeEaoLocal franchiseeEao;

    @Override
    protected GenericEao<Franchisee, Long> getGenericEao() {
        return franchiseeEao;
    }
}