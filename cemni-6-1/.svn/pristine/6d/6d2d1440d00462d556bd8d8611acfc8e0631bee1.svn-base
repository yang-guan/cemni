package com.huiju.archive.partner.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.archive.partner.eao.PartnerEaoLocal;
import com.huiju.archive.partner.entity.Partner;

@Stateless(mappedName = "PartnerBean")
public class PartnerBean extends GenericLogicImpl<Partner, Long> implements PartnerRemote {
    @EJB(mappedName = "PartnerEaoBean")
    private PartnerEaoLocal partnerEao;

    @Override
    protected GenericEao<Partner, Long> getGenericEao() {
        return partnerEao;
    }
}