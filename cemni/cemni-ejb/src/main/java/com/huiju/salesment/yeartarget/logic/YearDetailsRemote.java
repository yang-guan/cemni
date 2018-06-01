package com.huiju.salesment.yeartarget.logic;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.salesment.yeartarget.entity.YearDetails;

@Remote
public interface YearDetailsRemote extends GenericLogic<YearDetails, Long> {
}