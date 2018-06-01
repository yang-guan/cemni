package com.huiju.common.area.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.area.eao.AreaEaoLocal;
import com.huiju.common.area.entity.Area;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "AreaBean")
public class AreaBean extends GenericLogicImpl<Area, Long> implements AreaRemote {
    @EJB(mappedName = "AreaEaoBean")
    private AreaEaoLocal areaEao;

    @Override
    protected GenericEao<Area, Long> getGenericEao() {
        return areaEao;
    }
}