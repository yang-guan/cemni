package com.huiju.afterservice.telvisit.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.afterservice.telvisit.eao.TelVisitCustEaoLocal;
import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "TelVisitCustBean")
public class TelVisitCustBean extends GenericLogicImpl<TelVisitCust, Long> implements TelVisitCustRemote {
    @EJB
    private TelVisitCustEaoLocal telVisitCustEao;

    @Override
    protected GenericEao<TelVisitCust, Long> getGenericEao() {
        return telVisitCustEao;
    }
}