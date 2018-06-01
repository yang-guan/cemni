package com.huiju.report.fanstrans.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface FansTransRemote extends GenericLogic<Sql, Long> {

    Map report(Map searchParam);

    List qryFansTrans(Map searchParam);
}