package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.CustSurveyEaoLocal;
import com.huiju.competitor.entity.CustSurvey;

@Stateless(mappedName = "CustSurveyBean")
public class CustSurveyBean extends GenericLogicImpl<CustSurvey, Long> implements CustSurveyRemote {
    @EJB(mappedName = "CustSurveyEaoBean")
    private CustSurveyEaoLocal competitorCUREao;

    @Override
    protected GenericEao<CustSurvey, Long> getGenericEao() {
        return competitorCUREao;
    }
}