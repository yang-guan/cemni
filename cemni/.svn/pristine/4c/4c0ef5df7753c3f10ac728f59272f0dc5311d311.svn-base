package com.huiju.sms.smslog.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.sms.smslog.eao.SmsLogEaoLocal;
import com.huiju.sms.smslog.entity.SmsLog;

@Stateless(mappedName = "SmsLogBean")
public class SmsLogBean extends GenericLogicImpl<SmsLog, Long> implements SmsLogRemote {
    @EJB(mappedName = "SmsLogEaoBean")
    private SmsLogEaoLocal smslogEao;

    @Override
    protected GenericEao<SmsLog, Long> getGenericEao() {
        return smslogEao;
    }

}