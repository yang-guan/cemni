package com.huiju.actment.activity.logic;

import javax.ejb.Remote;

import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface IndiPartInRemote extends GenericLogic<IndiPartIn, Long> {
}