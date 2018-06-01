package com.huiju.inter.saleorder.logic;

import java.util.List;

import javax.ejb.Remote;

import com.huiju.inter.saleorder.entity.SaleOrder;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface SaleOrderRemote extends GenericLogic<SaleOrder, Long> {

    List loadOrder(Long activityId, Integer actType);

    int loadFra(Long franchiseeId, Long activityId);
}