package com.huiju.report.activityvalue.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface ActivityValueEaoLocal extends GenericEao<Sql, Long> {

    Map report(Map searchParam);

    List qryActVal(Map searchParam);
}