package com.huiju.integral.gradeadj.eao;

import javax.ejb.Local;

import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface GradeAdjHisEaoLocal extends GenericEao<GradeAdjHis, Long> {
}