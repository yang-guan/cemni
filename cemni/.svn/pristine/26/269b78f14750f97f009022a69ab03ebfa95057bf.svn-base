package com.huiju.inter.posorder.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.posorder.entity.JewelSegment;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "JewelSegmentEaoBean")
public class JewelSegmentEaoBean extends GenericEaoImpl<JewelSegment, Long> implements JewelSegmentEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}