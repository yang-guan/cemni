package com.huiju.archive.individcust.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.individcust.eao.OperationLogEaoLocal;
import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "OperationLogBean")
public class OperationLogBean extends GenericLogicImpl<OperationLog, Long> implements OperationLogRemote {
    @EJB
    private OperationLogEaoLocal operationLogEao;

    @Override
    protected GenericEao<OperationLog, Long> getGenericEao() {
        return operationLogEao;
    }
}