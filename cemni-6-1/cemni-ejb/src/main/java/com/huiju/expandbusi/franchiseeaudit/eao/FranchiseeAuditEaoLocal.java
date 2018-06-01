package com.huiju.expandbusi.franchiseeaudit.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.expandbusi.franchiseeaudit.entity.FranchiseeAudit;

@Local
public interface FranchiseeAuditEaoLocal extends GenericEao<FranchiseeAudit, Long> {
}