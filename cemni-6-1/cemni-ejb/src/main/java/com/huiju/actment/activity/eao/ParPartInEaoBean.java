package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.ParPartIn;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ParPartInEaoBean")
public class ParPartInEaoBean extends GenericEaoImpl<ParPartIn, Long> implements ParPartInEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}