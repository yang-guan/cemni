package com.huiju.competitor.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.competitor.entity.CustSurvey;

@Remote
public interface CustSurveyRemote extends GenericLogic<CustSurvey, Long> {
}