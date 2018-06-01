package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.ParPartInEaoLocal;
import com.huiju.actment.activity.entity.ParPartIn;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ParPartInBean")
public class ParPartInBean extends GenericLogicImpl<ParPartIn, Long> implements ParPartInRemote {
    @EJB
    private ParPartInEaoLocal ParPartInEao;

    @Override
    protected GenericEao<ParPartIn, Long> getGenericEao() {
        return ParPartInEao;
    }
}