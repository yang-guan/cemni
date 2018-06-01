package com.huiju.actment.activity.eao;

import javax.ejb.Local;

import com.huiju.actment.activity.entity.ExpectCost;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ExpectCostEaoLocal extends GenericEao<ExpectCost, Long>{

}
