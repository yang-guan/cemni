package com.huiju.afterservice.rightmaint.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.afterservice.rightmaint.entity.RecordInfo;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "RecordInfoEaoBean")
public class RecordInfoEaoBean extends GenericEaoImpl<RecordInfo, Long> implements RecordInfoEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}