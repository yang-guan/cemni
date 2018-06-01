package com.huiju.inter.interLog.logic;

import javax.ejb.Remote;

import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface InterLogRemote extends GenericLogic<InterLog, Long> {
}