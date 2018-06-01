package com.huiju.competitor.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.competitor.entity.ChannelSurvey;

@Stateless(mappedName = "ChannelSurveyEaoBean")
public class ChannelSurveyEaoBean extends GenericEaoImpl<ChannelSurvey, Long> implements ChannelSurveyEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}