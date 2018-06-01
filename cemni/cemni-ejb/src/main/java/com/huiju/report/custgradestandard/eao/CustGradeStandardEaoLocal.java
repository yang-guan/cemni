package com.huiju.report.custgradestandard.eao;

import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface CustGradeStandardEaoLocal extends GenericEao<Sql, Long> {
	@SuppressWarnings("rawtypes")
	Map report(Map searchParam, Integer ifPage);
}