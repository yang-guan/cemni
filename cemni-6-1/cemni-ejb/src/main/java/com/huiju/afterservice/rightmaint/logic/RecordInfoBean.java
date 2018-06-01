package com.huiju.afterservice.rightmaint.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.afterservice.rightmaint.eao.RecordInfoEaoLocal;
import com.huiju.afterservice.rightmaint.entity.RecordInfo;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "RecordInfoBean")
public class RecordInfoBean extends GenericLogicImpl<RecordInfo, Long> implements RecordInfoRemote {
    @EJB(mappedName = "RightMaintEaoBean")
    private RecordInfoEaoLocal recordInfoEao;

    @Override
    protected GenericEao<RecordInfo, Long> getGenericEao() {
        return recordInfoEao;
    }
}