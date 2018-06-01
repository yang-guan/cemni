package com.huiju.expandbusi.franchiseeaudit.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.expandbusi.franchiseeaudit.entity.FranchiseeAudit;

@Stateless(mappedName = "FranchiseeAuditEaoBean")
public class FranchiseeAuditEaoBean extends GenericEaoImpl<FranchiseeAudit, Long> implements FranchiseeAuditEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}