package com.huiju.expandbusi.memcompanalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.memcompanalyze.eao.MemdetailEaoLocal;
import com.huiju.expandbusi.memcompanalyze.entity.Memdetail;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "MemdetailBean")
public class MemdetailBean extends GenericLogicImpl<Memdetail, Long> implements MemdetailRemote {
    @EJB(mappedName = "MemdetailEaoBean")
    private MemdetailEaoLocal memdetailEao;

    @Override
    protected GenericEao<Memdetail, Long> getGenericEao() {
        return memdetailEao;
    }
}