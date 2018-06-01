package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.ChannelSurveyEaoLocal;
import com.huiju.competitor.entity.ChannelSurvey;

@Stateless(mappedName = "ChannelSurveyBean")
public class ChannelSurveyBean extends GenericLogicImpl<ChannelSurvey, Long> implements ChannelSurveyRemote {
    @EJB(mappedName = "ChannelSurveyEaoBean")
    private ChannelSurveyEaoLocal competitorCNREao;

    @Override
    protected GenericEao<ChannelSurvey, Long> getGenericEao() {
        return competitorCNREao;
    }
}