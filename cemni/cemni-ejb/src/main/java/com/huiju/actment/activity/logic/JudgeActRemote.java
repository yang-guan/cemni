package com.huiju.actment.activity.logic;

import javax.ejb.Remote;

import com.huiju.actment.activity.entity.JudgeAct;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface JudgeActRemote extends GenericLogic<JudgeAct, Long> {

}