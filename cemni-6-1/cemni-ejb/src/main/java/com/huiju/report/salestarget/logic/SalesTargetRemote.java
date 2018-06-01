package com.huiju.report.salestarget.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface SalesTargetRemote extends GenericLogic<Sql, Long> {
    Map report(Map searchParam, Integer ifPage);
}