package com.huiju.afterservice.telvisitrecord.eao;

import javax.ejb.Local;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface TelVisitRecordEaoLocal extends GenericEao<TelVisitRecord, Long> {
}