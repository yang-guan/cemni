package com.huiju.inter.activity.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.activity.entity.ActivityAudit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "ActivityAuditEaoBean")
public class ActivityAuditEaoBean extends GenericEaoImpl<ActivityAudit, Long> implements ActivityAuditEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}