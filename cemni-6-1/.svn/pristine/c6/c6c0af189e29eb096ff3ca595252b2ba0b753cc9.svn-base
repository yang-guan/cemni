package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.ScopeEaoLocal;
import com.huiju.actment.activity.entity.Scope;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ScopeBean")
public class ScopeBean extends GenericLogicImpl<Scope, Long> implements ScopeRemote {
    @EJB
    private ScopeEaoLocal scopeEao;

    @Override
    protected GenericEao<Scope, Long> getGenericEao() {
        return scopeEao;
    }
}
