package com.huiju.archive.individcust.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "AnniversaryEaoBean")
public class AnniversaryEaoBean extends GenericEaoImpl<Anniversary, Long> implements AnniversaryEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}