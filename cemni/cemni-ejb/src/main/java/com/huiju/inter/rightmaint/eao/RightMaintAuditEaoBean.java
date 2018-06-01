package com.huiju.inter.rightmaint.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.rightmaint.entity.RightMaintAudit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "RightMaintAuditEaoBean")
public class RightMaintAuditEaoBean extends GenericEaoImpl<RightMaintAudit, Long> implements RightMaintAuditEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}