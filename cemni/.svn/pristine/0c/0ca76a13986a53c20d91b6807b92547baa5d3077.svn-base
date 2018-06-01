package com.huiju.integral.gradeadj.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.integral.gradeadj.eao.GradeAdjHisEaoLocal;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "GradeAdjHisBean")
public class GradeAdjHisBean extends GenericLogicImpl<GradeAdjHis, Long> implements GradeAdjHisRemote {
    @EJB(mappedName = "GradeAdjHisEaoBean")
    private GradeAdjHisEaoLocal gradeadjhisEao;

    @Override
    protected GenericEao<GradeAdjHis, Long> getGenericEao() {
        return gradeadjhisEao;
    }
}