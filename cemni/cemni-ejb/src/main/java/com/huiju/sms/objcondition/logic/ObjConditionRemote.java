package com.huiju.sms.objcondition.logic;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.sms.objcondition.entity.ObjCondition;

@Remote
public interface ObjConditionRemote extends GenericLogic<ObjCondition, Long> {
}