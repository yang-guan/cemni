package com.huiju.afterservice.rightmaint.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.afterservice.rightmaint.eao.RightMaintEaoLocal;
import com.huiju.afterservice.rightmaint.entity.RightMaint;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "RightMaintBean")
public class RightMaintBean extends GenericLogicImpl<RightMaint, Long> implements RightMaintRemote {
    @EJB
    private RightMaintEaoLocal rightmaintEao;

    @Override
    protected GenericEao<RightMaint, Long> getGenericEao() {
        return rightmaintEao;
    }
}