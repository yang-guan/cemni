package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "IndiPartInEaoBean")
public class IndiPartInEaoBean extends GenericEaoImpl<IndiPartIn, Long> implements IndiPartInEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}