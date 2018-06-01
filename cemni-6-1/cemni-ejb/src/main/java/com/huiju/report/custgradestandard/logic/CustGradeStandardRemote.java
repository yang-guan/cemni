package com.huiju.report.custgradestandard.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface CustGradeStandardRemote extends GenericLogic<Sql, Long> {
	
	@SuppressWarnings("rawtypes")
	Map report(Map searchParam,Integer ifPage);
}