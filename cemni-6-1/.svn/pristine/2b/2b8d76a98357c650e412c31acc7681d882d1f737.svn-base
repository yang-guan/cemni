package com.huiju.inter.saleorder.eao;

import java.util.List;

import javax.ejb.Local;

import com.huiju.inter.saleorder.entity.SaleOrder;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface SaleOrderEaoLocal extends GenericEao<SaleOrder, Long> {

    List loadOrder(Long activityId, Integer actType);

    int loadFra(Long franchiseeId, Long activityId);
}