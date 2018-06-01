package com.huiju.archive.partner.logic;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.archive.partner.entity.Partner;

@Remote
public interface PartnerRemote extends GenericLogic<Partner, Long> {
}