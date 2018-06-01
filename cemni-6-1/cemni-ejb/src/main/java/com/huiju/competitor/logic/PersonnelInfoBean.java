package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.PersonnelInfoEaoLocal;
import com.huiju.competitor.entity.PersonnelInfo;

@Stateless(mappedName = "PersonnelInfoBean")
public class PersonnelInfoBean extends GenericLogicImpl<PersonnelInfo, Long> implements PersonnelInfoRemote {
    @EJB(mappedName = "PersonnelInfoEaoBean")
    private PersonnelInfoEaoLocal competitorEPEao;

    @Override
    protected GenericEao<PersonnelInfo, Long> getGenericEao() {
        return competitorEPEao;
    }
}