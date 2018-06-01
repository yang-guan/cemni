package com.huiju.salesment.yeartarget.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.salesment.yeartarget.eao.YearDetailsEaoLocal;
import com.huiju.salesment.yeartarget.entity.YearDetails;

@Stateless(mappedName = "YearDetailsBean")
public class YearDetailsBean extends GenericLogicImpl<YearDetails, Long> implements YearDetailsRemote {
    @EJB(mappedName = "YearDetailsEaoBean")
    private YearDetailsEaoLocal yeardetailsEao;

    @Override
    protected GenericEao<YearDetails, Long> getGenericEao() {
        return yeardetailsEao;
    }
}