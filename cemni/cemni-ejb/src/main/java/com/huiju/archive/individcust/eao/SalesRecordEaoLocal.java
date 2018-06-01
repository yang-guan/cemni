package com.huiju.archive.individcust.eao;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.SalesRecord;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface SalesRecordEaoLocal extends GenericEao<SalesRecord, Long> {
}