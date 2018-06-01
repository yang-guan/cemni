package com.huiju.expandbusi.franchiseeaudit.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.expandbusi.franchiseeaudit.eao.FranchiseeAuditEaoLocal;
import com.huiju.expandbusi.franchiseeaudit.entity.FranchiseeAudit;

@Stateless(mappedName = "FranchiseeAuditBean")
public class FranchiseeAuditBean extends GenericLogicImpl<FranchiseeAudit, Long> implements FranchiseeAuditRemote {
    @EJB(mappedName = "FranchiseeAuditEaoBean")
    private FranchiseeAuditEaoLocal franchiseeauditEao;

    @Override
    protected GenericEao<FranchiseeAudit, Long> getGenericEao() {
        return franchiseeauditEao;
    }
}