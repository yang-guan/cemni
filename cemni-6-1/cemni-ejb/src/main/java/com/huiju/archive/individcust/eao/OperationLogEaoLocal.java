package com.huiju.archive.individcust.eao;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface OperationLogEaoLocal extends GenericEao<OperationLog, Long> {
}