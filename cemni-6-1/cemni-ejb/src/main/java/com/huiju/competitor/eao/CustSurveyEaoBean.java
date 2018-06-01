package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.CustSurvey;

@Stateless(mappedName = "CustSurveyEaoBean")
public class CustSurveyEaoBean extends GenericEaoImpl<CustSurvey, Long> implements CustSurveyEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}