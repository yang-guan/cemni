package com.huiju.inter.interLog.eao;

import javax.ejb.Local;

import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface InterLogEaoLocal extends GenericEao<InterLog, Long> {
}