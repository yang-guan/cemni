package com.huiju.inter.interLog.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.interLog.eao.InterLogEaoLocal;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "InterLogBean")
public class InterLogBean extends GenericLogicImpl<InterLog, Long> implements InterLogRemote {
    @EJB(mappedName = "InterLogEaoBean")
    private InterLogEaoLocal interLogEao;

    @Override
    protected GenericEao<InterLog, Long> getGenericEao() {
        return interLogEao;
    }
}