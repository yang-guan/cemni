package com.huiju.report.activity.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.activity.eao.RepActivityEaoLocal;

@Stateless(mappedName = "RepActivityBean")
@SuppressWarnings("rawtypes")
public class RepActivityBean extends GenericLogicImpl<Sql, Long> implements RepActivityRemote {
    @EJB
    private RepActivityEaoLocal repActivityEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return repActivityEao;
    }

    @Override
    public Map report(Map searchParam) {
        return repActivityEao.report(searchParam);
    }

    @Override
    public List qryRepAct(Map searchParam) {
        return repActivityEao.qryRepAct(searchParam);
    }

}