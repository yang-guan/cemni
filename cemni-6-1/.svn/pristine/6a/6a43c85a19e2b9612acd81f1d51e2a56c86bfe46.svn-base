package com.huiju.common.sql.logic;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.huiju.afterservice.rightmaint.eao.RecordInfoEaoLocal;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "SqlBean")
@SuppressWarnings({ "rawtypes" })
public class SqlBean extends GenericLogicImpl<Sql, Long> implements SqlRemote {
    @EJB
    private SqlEaoLocal sqlEao;
    @EJB
    private RecordInfoEaoLocal recordInfoEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return sqlEao;
    }

    @Override
    public String getCnNum(int numCode) {
        return sqlEao.getCnNum(numCode);
    }

    @Override
    public String getSeq(String seq, String prefix, String dateformat) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select ");

        if (StringUtils.isNotBlank(prefix)) {
            jpql.append("'" + prefix + "'||");
        }
        if (StringUtils.isNotBlank(dateformat)) {
            jpql.append("to_char(sysdate, '" + dateformat + "')||");
        }
        jpql.append(seq + ".nextval from dual");

        return sqlEao.executeSQLQueryOne(jpql.toString()).toString();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Map queryRightMaintAudit(Map searchParam) {
        return sqlEao.queryRightMaintAudit(searchParam);
    }

    @Override
    public Map queryActivity(Map searchParam) {
        return sqlEao.queryActivity(searchParam);
    }

    @Override
    public Map queryContract(Map searchParam) {
        return sqlEao.queryContract(searchParam);
    }

    @Override
    public Map queryIndividcust(Map searchParam) {
        return sqlEao.queryIndividcust(searchParam);
    }

}