package com.huiju.expandbusi.franchiseeprofit.shopcost.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.franchiseeprofit.shopcost.entity.ShopCost;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ShopCostEaoBean")
public class ShopCostEaoBean extends GenericEaoImpl<ShopCost, Long> implements ShopCostEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}