package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.ActiveStatusEaoLocal;
import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ActiveStatusBean")
public class ActiveStatusBean extends GenericLogicImpl<ActiveStatus, Long> implements ActiveStatusRemote {
    @EJB(mappedName = "ActiveStatusEaoBean")
    private ActiveStatusEaoLocal activeStatusEao;

    @Override
    protected GenericEao<ActiveStatus, Long> getGenericEao() {
        return activeStatusEao;
    }
}