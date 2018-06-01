package com.huiju.report.salestarget.logic;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.salestarget.eao.SalesTargetEaoLocal;

@Stateless(mappedName = "SalesTargetBean")
@SuppressWarnings("rawtypes")
public class SalesTargetBean extends GenericLogicImpl<Sql, Long> implements SalesTargetRemote {
    @EJB
    private SalesTargetEaoLocal salestargetEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return salestargetEao;
    }

    @Override
    public Map report(Map searchParam, Integer ifPage) {
        return salestargetEao.report(searchParam, ifPage);
    }

}