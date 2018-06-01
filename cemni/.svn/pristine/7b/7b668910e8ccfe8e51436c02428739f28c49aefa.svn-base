package com.huiju.integral.graderule.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.integral.graderule.entity.GradeRule;

@Stateless(mappedName = "GradeRuleEaoBean")
public class GradeRuleEaoBean extends GenericEaoImpl<GradeRule, Long> implements GradeRuleEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}