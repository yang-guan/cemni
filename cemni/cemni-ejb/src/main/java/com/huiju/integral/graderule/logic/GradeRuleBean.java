package com.huiju.integral.graderule.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.integral.graderule.eao.GradeRuleEaoLocal;
import com.huiju.integral.graderule.entity.GradeRule;

@Stateless(mappedName = "GradeRuleBean")
public class GradeRuleBean extends GenericLogicImpl<GradeRule, Long> implements GradeRuleRemote {
    @EJB(mappedName = "GradeRuleEaoBean")
    private GradeRuleEaoLocal graderuleEao;

    @Override
    protected GenericEao<GradeRule, Long> getGenericEao() {
        return graderuleEao;
    }
}