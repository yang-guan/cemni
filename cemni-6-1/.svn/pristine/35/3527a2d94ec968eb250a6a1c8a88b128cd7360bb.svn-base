package com.huiju.salesment.scaletarget.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.salesment.scaletarget.entity.ScaleDetails;

@Stateless(mappedName = "ScaleDetailsEaoBean")
public class ScaleDetailsEaoBean extends GenericEaoImpl<ScaleDetails, Long> implements ScaleDetailsEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}