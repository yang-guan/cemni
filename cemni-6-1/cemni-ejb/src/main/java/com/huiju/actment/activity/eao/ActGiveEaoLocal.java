package com.huiju.actment.activity.eao;

import javax.ejb.Local;

import com.huiju.actment.activity.entity.ActGive;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ActGiveEaoLocal extends GenericEao<ActGive, Long>{

}
