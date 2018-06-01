package com.huiju.inter.rightmaint.eao;

import javax.ejb.Local;

import com.huiju.inter.rightmaint.entity.RightMaintAudit;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface RightMaintAuditEaoLocal extends GenericEao<RightMaintAudit, Long> {
}