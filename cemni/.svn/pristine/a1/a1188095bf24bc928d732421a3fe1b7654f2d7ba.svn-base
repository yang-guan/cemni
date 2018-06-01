package com.huiju.actment.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "FraPartInEaoBean")
public class FraPartInEaoBean extends GenericEaoImpl<FraPartIn, Long> implements FraPartInEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
