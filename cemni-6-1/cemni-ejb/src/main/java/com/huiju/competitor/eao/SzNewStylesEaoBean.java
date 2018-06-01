package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.SzNewStyles;

@Stateless(mappedName = "SzNewStylesEaoBean")
public class SzNewStylesEaoBean extends GenericEaoImpl<SzNewStyles, Long> implements SzNewStylesEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}