package com.huiju.integral.integraladj.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;

@Stateless(mappedName = "IntegralAdjHisEaoBean")
public class IntegralAdjHisEaoBean extends GenericEaoImpl<IntegralAdjHis, Long> implements IntegralAdjHisEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}