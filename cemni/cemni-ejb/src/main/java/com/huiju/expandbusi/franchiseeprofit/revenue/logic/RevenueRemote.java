package com.huiju.expandbusi.franchiseeprofit.revenue.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.franchiseeprofit.revenue.entity.Revenue;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface RevenueRemote extends GenericLogic<Revenue, Long> {
}