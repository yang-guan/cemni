package com.huiju.expandbusi.individcompanalyze.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface IndividCompAnalyzeEaoLocal extends GenericEao<IndividCompAnalyze, Long> {
}