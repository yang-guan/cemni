package com.huiju.expandbusi.franchiseeprofit.expandcost.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.expandcost.entity.ExpandCost;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ExpandCostEaoBean")
public class ExpandCostEaoBean extends GenericEaoImpl<ExpandCost, Long> implements ExpandCostEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}