package com.huiju.report.reviewtask.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface ReviewTaskRemote extends GenericLogic<Sql, Long> {

    Map report(Map searchParam);

    List qryForExcel(Map searchParam);
}