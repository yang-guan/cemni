package com.huiju.archive.partner.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.archive.partner.entity.Partner;

@Stateless(mappedName = "PartnerEaoBean")
public class PartnerEaoBean extends GenericEaoImpl<Partner, Long> implements PartnerEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}