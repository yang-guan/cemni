package com.huiju.archive.individcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface OperationLogRemote extends GenericLogic<OperationLog, Long> {
}