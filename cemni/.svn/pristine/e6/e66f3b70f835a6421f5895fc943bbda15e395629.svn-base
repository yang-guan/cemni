package com.huiju.report.membergrade.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface MemberGradeEaoLocal extends GenericEao<Sql, Long> {

    List<Map> report(Map searchParam);
}