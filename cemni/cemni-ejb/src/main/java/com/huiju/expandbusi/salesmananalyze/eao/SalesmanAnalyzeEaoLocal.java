package com.huiju.expandbusi.salesmananalyze.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.expandbusi.salesmananalyze.entity.SalesmanAnalyze;

@Local
public interface SalesmanAnalyzeEaoLocal extends GenericEao<SalesmanAnalyze, Long> {
}