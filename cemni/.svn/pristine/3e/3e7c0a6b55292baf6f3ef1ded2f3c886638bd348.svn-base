package com.huiju.salesment.yeartarget.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.salesment.yeartarget.entity.YearTarget;

@Stateless(mappedName = "YearTargetEaoBean")
public class YearTargetEaoBean extends GenericEaoImpl<YearTarget, Long> implements YearTargetEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}