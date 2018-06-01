package com.huiju.competitor.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.competitor.entity.CustSurvey;

@Local
public interface CustSurveyEaoLocal extends GenericEao<CustSurvey, Long> {
}
