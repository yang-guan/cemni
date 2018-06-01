package com.huiju.inter.rightmaint.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.rightmaint.eao.RightMaintAuditEaoLocal;
import com.huiju.inter.rightmaint.entity.RightMaintAudit;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "RightMaintAuditBean")
public class RightMaintAuditBean extends GenericLogicImpl<RightMaintAudit, Long> implements RightMaintAuditRemote {
    @EJB(mappedName = "RightMaintAuditEaoBean")
    private RightMaintAuditEaoLocal rightMaintAuditEao;

    @Override
    protected GenericEao<RightMaintAudit, Long> getGenericEao() {
        return rightMaintAuditEao;
    }
}