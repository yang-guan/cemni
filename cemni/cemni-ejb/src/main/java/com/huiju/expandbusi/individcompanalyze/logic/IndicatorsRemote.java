package com.huiju.expandbusi.individcompanalyze.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface IndicatorsRemote extends GenericLogic<Indicators, Long> {
}