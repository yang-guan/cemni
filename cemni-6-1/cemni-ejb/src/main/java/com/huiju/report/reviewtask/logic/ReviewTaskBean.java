package com.huiju.report.reviewtask.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.reviewtask.eao.ReviewTaskEaoLocal;

@Stateless(mappedName = "ReviewTaskBean")
@SuppressWarnings("rawtypes")
public class ReviewTaskBean extends GenericLogicImpl<Sql, Long> implements ReviewTaskRemote {
    @EJB
    private ReviewTaskEaoLocal reviewtaskEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return reviewtaskEao;
    }

    @Override
    public Map report(Map searchParam) {
        return reviewtaskEao.report(searchParam);
    }

    @Override
    public List qryForExcel(Map searchParam) {
        return reviewtaskEao.qryForExcel(searchParam);
    }

}