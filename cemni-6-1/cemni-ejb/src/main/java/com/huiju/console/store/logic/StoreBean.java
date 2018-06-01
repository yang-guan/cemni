package com.huiju.console.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.DataDict;
import com.huiju.console.store.eao.StoreEaoLocal;
import com.huiju.console.store.entity.Store;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "StoreBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StoreBean extends GenericLogicImpl<Store, Long> implements StoreRemote {
    @EJB(mappedName = "StoreEaoBean")
    private StoreEaoLocal storeEao;

    @Override
    protected GenericEao<Store, Long> getGenericEao() {
        return storeEao;
    }

    @Override
    public List qryBigAreaStore() {
        Map searchParams = new HashMap();
        searchParams.put("IS_areaId", "NOTNULL");
        searchParams.put("EQ_isValid", 1);

        String[] sorts = { "bigAreaNo,asc", "areaNo,asc", "attr,asc", "form,asc", "storeId,asc" };
        List<Store> rsList = this.findAll(searchParams, sorts);
        for (Store dt : rsList) {
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
            dt.setFormName(DataDict.getDictName(DataDict.STORE_FORM, dt.getForm()));
        }
        return rsList;
    }

}