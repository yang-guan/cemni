package com.huiju.expandbusi.franchiseeprofit.profit.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.franchiseeprofit.profit.entity.Profit;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ProfitEaoLocal extends GenericEao<Profit, Long> {
}