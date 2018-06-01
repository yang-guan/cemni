package com.huiju.inter.afterserv.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.afterserv.eao.AfterServEaoLocal;
import com.huiju.inter.afterserv.entity.AfterServ;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "AfterServBean")
public class AfterServBean extends GenericLogicImpl<AfterServ, Long> implements AfterServRemote {
    @EJB(mappedName = "AfterServEaoBean")
    private AfterServEaoLocal afterServEao;

    @Override
    protected GenericEao<AfterServ, Long> getGenericEao() {
        return afterServEao;
    }

}