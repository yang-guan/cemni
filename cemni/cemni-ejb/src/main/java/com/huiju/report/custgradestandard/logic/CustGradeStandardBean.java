package com.huiju.report.custgradestandard.logic;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.custgradestandard.eao.CustGradeStandardEaoLocal;

@Stateless(mappedName = "CustGradeStandardBean")
public class CustGradeStandardBean extends GenericLogicImpl<Sql, Long> implements CustGradeStandardRemote {
	@EJB(mappedName = "CustGradeStandardEaoBean")
	private CustGradeStandardEaoLocal custgradestandardEao;

	@Override
	protected GenericEao<Sql, Long> getGenericEao() {
		return custgradestandardEao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map report(Map searchParam, Integer ifPage) {
		return custgradestandardEao.report(searchParam, ifPage);
	}
}