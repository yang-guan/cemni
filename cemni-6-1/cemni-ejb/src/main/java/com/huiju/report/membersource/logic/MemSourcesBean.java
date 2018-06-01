package com.huiju.report.membersource.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.membersource.eao.MemSourcesEaoLocal;

@Stateless(mappedName = "MemSourcesBean")
@SuppressWarnings("rawtypes")
public class MemSourcesBean extends GenericLogicImpl<Sql, Long> implements MemSourcesRemote {
    @EJB
    private MemSourcesEaoLocal memSourcesEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return memSourcesEao;
    }

    @Override
    public Map report(Map searchParam) {
        return memSourcesEao.report(searchParam);
    }

    @Override
    public List qryMemSour(Map searchParam) {
        return memSourcesEao.qryMemSour(searchParam);
    }

}