package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.JudgeActEaoLocal;
import com.huiju.actment.activity.entity.JudgeAct;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "JudgeActBean")
public class JudgeActBean extends GenericLogicImpl<JudgeAct, Long> implements JudgeActRemote {
    @EJB
    private JudgeActEaoLocal judgeActEao;

    @Override
    protected GenericEao<JudgeAct, Long> getGenericEao() {
        return judgeActEao;
    }
}