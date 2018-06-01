package com.huiju.report.fanstrans.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.fanstrans.eao.FansTransEaoLocal;

@Stateless(mappedName = "FansTransBean")
@SuppressWarnings("rawtypes")
public class FansTransBean extends GenericLogicImpl<Sql, Long> implements FansTransRemote {
    @EJB
    private FansTransEaoLocal fansTransEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return fansTransEao;
    }

    @Override
    public Map report(Map searchParam) {
        return fansTransEao.report(searchParam);
    }

    @Override
    public List qryFansTrans(Map searchParam) {
        return fansTransEao.qryFansTrans(searchParam);
    }

}