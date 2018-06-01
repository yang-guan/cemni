package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.IndiPartInEaoLocal;
import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "IndiPartInBean")
public class IndiPartInBean extends GenericLogicImpl<IndiPartIn, Long> implements IndiPartInRemote {
    @EJB
    private IndiPartInEaoLocal indiPartInEao;

    @Override
    protected GenericEao<IndiPartIn, Long> getGenericEao() {
        return indiPartInEao;
    }
}