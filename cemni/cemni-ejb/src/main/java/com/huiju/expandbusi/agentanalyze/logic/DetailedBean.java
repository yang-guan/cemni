package com.huiju.expandbusi.agentanalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.agentanalyze.eao.DetailedEaoLocal;
import com.huiju.expandbusi.agentanalyze.entity.Detailed;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "DetailedBean")
public class DetailedBean extends GenericLogicImpl<Detailed, Long> implements DetailedRemote {
    @EJB(mappedName = "DetailedBeanEaoBean")
    private DetailedEaoLocal DetailedEao;

    @Override
    protected GenericEao<Detailed, Long> getGenericEao() {
        return DetailedEao;
    }
}