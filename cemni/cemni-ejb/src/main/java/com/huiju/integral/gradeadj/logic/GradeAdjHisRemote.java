package com.huiju.integral.gradeadj.logic;

import javax.ejb.Remote;

import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface GradeAdjHisRemote extends GenericLogic<GradeAdjHis, Long> {
}