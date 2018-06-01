package com.huiju.afterservice.busiregist.logic;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.afterservice.busiregist.eao.BusiRegistEaoLocal;
import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "BusiRegistBean")
@SuppressWarnings("rawtypes")
public class BusiRegistBean extends GenericLogicImpl<BusiRegist, Long> implements BusiRegistRemote {
    @EJB
    private BusiRegistEaoLocal busiregistEao;

    @Override
    protected GenericEao<BusiRegist, Long> getGenericEao() {
        return busiregistEao;
    }

    @Override
    public Map queryPageList(Map searchParam) {
        return busiregistEao.queryPageList(searchParam);
    }

    @Override
    public Map queryOrgStoreList(Map searchParam) {
        return busiregistEao.queryOrgStoreList(searchParam);
    }

}