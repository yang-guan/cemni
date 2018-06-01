package com.huiju.expandbusi.franchiseeprofit.revenue.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.revenue.entity.Revenue;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "RevenueEaoBean")
public class RevenueEaoBean extends GenericEaoImpl<Revenue, Long> implements RevenueEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}