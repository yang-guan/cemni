package com.huiju.report.activity.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface RepActivityEaoLocal extends GenericEao<Sql, Long> {

    Map report(Map searchParam);

    List qryRepAct(Map searchParam);
}