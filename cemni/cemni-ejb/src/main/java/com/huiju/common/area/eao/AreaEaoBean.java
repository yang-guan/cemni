package com.huiju.common.area.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.common.area.entity.Area;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "AreaEaoBean")
public class AreaEaoBean extends GenericEaoImpl<Area, Long> implements AreaEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}