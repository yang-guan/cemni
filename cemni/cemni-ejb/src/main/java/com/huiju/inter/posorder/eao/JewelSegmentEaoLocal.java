package com.huiju.inter.posorder.eao;

import javax.ejb.Local;

import com.huiju.inter.posorder.entity.JewelSegment;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface JewelSegmentEaoLocal extends GenericEao<JewelSegment, Long> {
}