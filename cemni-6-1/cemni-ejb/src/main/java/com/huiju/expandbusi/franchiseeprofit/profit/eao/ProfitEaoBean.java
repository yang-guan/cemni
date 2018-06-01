package com.huiju.expandbusi.franchiseeprofit.profit.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.profit.entity.Profit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ProfitEaoBean")
public class ProfitEaoBean extends GenericEaoImpl<Profit, Long> implements ProfitEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}