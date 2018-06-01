package com.huiju.sms.sms.eao;

import java.util.List;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.sms.sms.entity.Sms;

@Local
public interface SmsEaoLocal extends GenericEao<Sms, Long> {

    List<IndividCust> qryObjCust(Long smsId);

    List<String> qrySms_ObjCond(Long smsId, Integer type);
}