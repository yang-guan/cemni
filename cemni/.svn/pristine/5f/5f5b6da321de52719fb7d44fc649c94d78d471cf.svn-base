package com.huiju.expandbusi.franchiseeprofit.shopcost.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.franchiseeprofit.shopcost.eao.ShopCostEaoLocal;
import com.huiju.expandbusi.franchiseeprofit.shopcost.entity.ShopCost;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ShopCostBean")
public class ShopCostBean extends GenericLogicImpl<ShopCost, Long> implements ShopCostRemote {
    @EJB(mappedName = "ShopCostEaoBean")
    private ShopCostEaoLocal shopcostEao;

    @Override
    protected GenericEao<ShopCost, Long> getGenericEao() {
        return shopcostEao;
    }
}