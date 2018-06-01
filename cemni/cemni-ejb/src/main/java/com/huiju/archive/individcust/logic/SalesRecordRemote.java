package com.huiju.archive.individcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.SalesRecord;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface SalesRecordRemote extends GenericLogic<SalesRecord, Long>{
}