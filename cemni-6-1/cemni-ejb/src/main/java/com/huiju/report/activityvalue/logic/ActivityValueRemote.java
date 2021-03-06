package com.huiju.report.activityvalue.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface ActivityValueRemote extends GenericLogic<Sql, Long> {

    Map report(Map searchParam);

    List qryActVal(Map searchParam);
}