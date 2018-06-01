package com.huiju.salesment.scaletarget.eao;

import javax.ejb.Local;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.salesment.scaletarget.entity.ScaleTarget;

@Local
public interface ScaleTargetEaoLocal extends GenericEao<ScaleTarget, Long> {
}