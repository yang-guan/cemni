package com.huiju.afterservice.telvisitrecord.logic;

import javax.ejb.Remote;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface TelVisitRecordRemote extends GenericLogic<TelVisitRecord, Long> {
}