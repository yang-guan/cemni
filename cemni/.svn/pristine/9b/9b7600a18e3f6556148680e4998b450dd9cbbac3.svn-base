package com.huiju.salesment.scaletarget.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.salesment.scaletarget.eao.ScaleDetailsEaoLocal;
import com.huiju.salesment.scaletarget.entity.ScaleDetails;

@Stateless(mappedName = "ScaleDetailsBean")
public class ScaleDetailsBean extends GenericLogicImpl<ScaleDetails, Long> implements ScaleDetailsRemote {
    @EJB(mappedName = "ScaleDetailsEaoBean")
    private ScaleDetailsEaoLocal scaledetailsEao;

    @Override
    protected GenericEao<ScaleDetails, Long> getGenericEao() {
        return scaledetailsEao;
    }
}