package com.huiju.salesment.yeartarget.eao;

import javax.ejb.Local;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.salesment.yeartarget.entity.YearDetails;

@Local
public interface YearDetailsEaoLocal extends GenericEao<YearDetails, Long> {
}