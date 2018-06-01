package com.huiju.afterservice.telvisitrecord.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "TelVisitRecordEaoBean")
public class TelVisitRecordEaoBean extends GenericEaoImpl<TelVisitRecord, Long> implements TelVisitRecordEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}