package com.huiju.afterservice.callregist.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.afterservice.callregist.eao.CallRegistEaoLocal;
import com.huiju.afterservice.callregist.entity.CallRegist;

@Stateless(mappedName = "CallRegistBean")
public class CallRegistBean extends GenericLogicImpl<CallRegist, Long> implements CallRegistRemote {
    @EJB(mappedName = "CallRegistEaoBean")
    private CallRegistEaoLocal callregistEao;

    @Override
    protected GenericEao<CallRegist, Long> getGenericEao() {
        return callregistEao;
    }
}