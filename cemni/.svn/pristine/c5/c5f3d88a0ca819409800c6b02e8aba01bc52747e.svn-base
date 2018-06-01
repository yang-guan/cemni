package com.huiju.integral.gradeadj.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "GradeAdjHisEaoBean")
public class GradeAdjHisEaoBean extends GenericEaoImpl<GradeAdjHis, Long> implements GradeAdjHisEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}