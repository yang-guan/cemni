package com.huiju.actment.activity.logic;

import javax.ejb.Remote;

import com.huiju.actment.activity.entity.ActGive;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ActGiveRemote extends GenericLogic<ActGive, Long> {

}
