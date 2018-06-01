package com.huiju.report.membergrade.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface MemberGradeRemote extends GenericLogic<Sql, Long> {

    List<Map> report(Map searchParam);
}