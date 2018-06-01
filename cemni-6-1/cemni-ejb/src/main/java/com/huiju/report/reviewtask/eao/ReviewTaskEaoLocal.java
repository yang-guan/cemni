package com.huiju.report.reviewtask.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface ReviewTaskEaoLocal extends GenericEao<Sql, Long> {

    Map report(Map searchParam);

    List qryForExcel(Map searchParam);
}