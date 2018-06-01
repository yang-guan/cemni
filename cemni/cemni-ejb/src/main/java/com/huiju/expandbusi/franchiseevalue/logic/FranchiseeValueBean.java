package com.huiju.expandbusi.franchiseevalue.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.expandbusi.franchiseevalue.eao.FranchiseeValueEaoLocal;
import com.huiju.expandbusi.franchiseevalue.entity.FranchiseeValue;

@Stateless(mappedName = "FranchiseeValueBean")
public class FranchiseeValueBean extends GenericLogicImpl<FranchiseeValue, Long> implements FranchiseeValueRemote {
    @EJB(mappedName = "FranchiseeValueEaoBean")
    private FranchiseeValueEaoLocal franchiseevalueEao;

    @Override
    protected GenericEao<FranchiseeValue, Long> getGenericEao() {
        return franchiseevalueEao;
    }
}