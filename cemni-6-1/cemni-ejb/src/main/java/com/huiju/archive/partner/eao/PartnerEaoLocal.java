package com.huiju.archive.partner.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.archive.partner.entity.Partner;

@Local
public interface PartnerEaoLocal extends GenericEao<Partner, Long> {
}