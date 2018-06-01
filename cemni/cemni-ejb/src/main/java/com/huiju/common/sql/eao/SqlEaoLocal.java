package com.huiju.common.sql.eao;

import java.util.Map;

import javax.ejb.Local;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings({ "rawtypes" })
public interface SqlEaoLocal extends GenericEao<Sql, Long> {

    String getCnNum(int numCode);
    
    Map queryRightMaintAudit(Map searchParam);
    
    Map queryActivity(Map searchParam);
    
    Map queryContract(Map searchParam);
    
    Map queryIndividcust(Map searchParam);
}