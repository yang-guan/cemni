package com.huiju.sms.smslog.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.sms.smslog.entity.SmsLog;

@Stateless(mappedName = "SmsLogEaoBean")
public class SmsLogEaoBean extends GenericEaoImpl<SmsLog, Long> implements SmsLogEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}