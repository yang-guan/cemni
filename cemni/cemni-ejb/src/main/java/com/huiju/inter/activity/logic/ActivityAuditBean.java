package com.huiju.inter.activity.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.activity.eao.ActivityAuditEaoLocal;
import com.huiju.inter.activity.entity.ActivityAudit;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ActivityAuditBean")
public class ActivityAuditBean extends GenericLogicImpl<ActivityAudit, Long> implements ActivityAuditRemote {

    @EJB(mappedName = "ActivityAuditEaoBean")
    private ActivityAuditEaoLocal activityAuditEaoLocal;

    @Override
    protected GenericEao<ActivityAudit, Long> getGenericEao() {
        return activityAuditEaoLocal;
    }
}