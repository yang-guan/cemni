package com.huiju.expandbusi.franchiseeprofit.costsupport.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.franchiseeprofit.costsupport.entity.CostSupport;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface CostSupportRemote extends GenericLogic<CostSupport, Long> {
}