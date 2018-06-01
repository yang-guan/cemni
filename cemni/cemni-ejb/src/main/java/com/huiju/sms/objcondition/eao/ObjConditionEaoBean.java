package com.huiju.sms.objcondition.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.sms.objcondition.entity.ObjCondition;

@Stateless(mappedName = "ObjConditionEaoBean")
public class ObjConditionEaoBean extends GenericEaoImpl<ObjCondition, Long> implements ObjConditionEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}