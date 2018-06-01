package com.huiju.sms.objcondition.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.sms.objcondition.eao.ObjConditionEaoLocal;
import com.huiju.sms.objcondition.entity.ObjCondition;

@Stateless(mappedName = "ObjConditionBean")
public class ObjConditionBean extends GenericLogicImpl<ObjCondition, Long> implements ObjConditionRemote {
    @EJB(mappedName = "ObjConditionEaoBean")
    private ObjConditionEaoLocal objconditionEao;

    @Override
    protected GenericEao<ObjCondition, Long> getGenericEao() {
        return objconditionEao;
    }
}