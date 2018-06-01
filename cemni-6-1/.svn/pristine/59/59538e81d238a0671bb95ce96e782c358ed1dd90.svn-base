package com.huiju.report.reviewcontent.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ReviewContentRemote extends GenericLogic<Sql, Long> {
	
	@SuppressWarnings("rawtypes")
	Map report(Map searchParam, Integer ifPage);
}