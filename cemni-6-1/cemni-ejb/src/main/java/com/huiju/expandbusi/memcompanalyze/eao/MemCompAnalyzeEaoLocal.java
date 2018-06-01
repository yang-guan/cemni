package com.huiju.expandbusi.memcompanalyze.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.memcompanalyze.entity.MemCompAnalyze;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface MemCompAnalyzeEaoLocal extends GenericEao<MemCompAnalyze, Long> {
}