package com.huiju.salesment.scaletarget.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.salesment.scaletarget.entity.ScaleTarget;

@Stateless(mappedName = "ScaleTargetEaoBean")
public class ScaleTargetEaoBean extends GenericEaoImpl<ScaleTarget, Long> implements ScaleTargetEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}