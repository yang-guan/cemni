package com.huiju.actment.activity.eao;

import javax.ejb.Local;

import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface IndiPartInEaoLocal extends GenericEao<IndiPartIn, Long> {

}