package com.huiju.actment.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.FraPartInEaoLocal;
import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "FraPartInBean")
public class FraPartInBean extends GenericLogicImpl<FraPartIn, Long> implements FraPartInRemote {
    @EJB
    private FraPartInEaoLocal fraPartInEao;

    @Override
    protected GenericEao<FraPartIn, Long> getGenericEao() {
        return fraPartInEao;
    }
}