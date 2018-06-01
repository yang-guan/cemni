package com.huiju.afterservice.telvisitrecord.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.afterservice.telvisitrecord.eao.TelVisitRecordEaoLocal;
import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "TelVisitRecordBean")
public class TelVisitRecordBean extends GenericLogicImpl<TelVisitRecord, Long> implements TelVisitRecordRemote {
    @EJB
    private TelVisitRecordEaoLocal telVisitRecordEao;

    @Override
    protected GenericEao<TelVisitRecord, Long> getGenericEao() {
        return telVisitRecordEao;
    }

}