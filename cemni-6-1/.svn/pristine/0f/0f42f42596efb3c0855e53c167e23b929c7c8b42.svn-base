package com.huiju.report.membergrade.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.report.membergrade.eao.MemberGradeEaoLocal;

@Stateless(mappedName = "MemberGradeBean")
@SuppressWarnings("rawtypes")
public class MemberGradeBean extends GenericLogicImpl<Sql, Long> implements MemberGradeRemote {
    @EJB
    private MemberGradeEaoLocal membergradeEao;

    @Override
    protected GenericEao<Sql, Long> getGenericEao() {
        return membergradeEao;
    }

    @Override
    public List<Map> report(Map searchParam) {
        return membergradeEao.report(searchParam);
    }

}