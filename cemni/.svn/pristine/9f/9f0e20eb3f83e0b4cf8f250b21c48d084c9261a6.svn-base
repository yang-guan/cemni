package com.huiju.report.reviewcontent.logic;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.reviewcontent.eao.ReviewContentEaoLocal;

@Stateless(mappedName = "ReviewContentBean")
public class ReviewContentBean extends GenericLogicImpl<Sql, Long> implements ReviewContentRemote {
    @EJB(mappedName = "ReviewContentEaoBean")
    private ReviewContentEaoLocal reviewcontentEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return reviewcontentEao;
    }
    
    @SuppressWarnings("rawtypes")
	public Map report(Map searchParam, Integer ifPage){
    	return reviewcontentEao.report(searchParam, ifPage);
    }
}