package com.huiju.inter.saleorder.logic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.saleorder.eao.SaleOrderEaoLocal;
import com.huiju.inter.saleorder.entity.SaleOrder;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "SaleOrderBean")
public class SaleOrderBean extends GenericLogicImpl<SaleOrder, Long> implements SaleOrderRemote {
    @EJB
    private SaleOrderEaoLocal SaleOrderEao;

    @Override
    protected GenericEao<SaleOrder, Long> getGenericEao() {
        return SaleOrderEao;
    }

    @Override
    public List loadOrder(Long activityId, Integer actType) {
        return SaleOrderEao.loadOrder(activityId, actType);
    }

    @Override
    public int loadFra(Long franchiseeId, Long activityId) {
        return SaleOrderEao.loadFra(franchiseeId, activityId);
    }

}