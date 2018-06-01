package com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity.FranchiseeProfit;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface FranchiseeProfitRemote extends GenericLogic<FranchiseeProfit, Long> {
}