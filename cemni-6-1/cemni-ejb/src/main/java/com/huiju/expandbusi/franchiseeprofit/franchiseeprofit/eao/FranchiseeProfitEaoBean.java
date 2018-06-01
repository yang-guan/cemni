package com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity.FranchiseeProfit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "FranchiseeProfitEaoBean")
public class FranchiseeProfitEaoBean extends GenericEaoImpl<FranchiseeProfit, Long> implements FranchiseeProfitEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}