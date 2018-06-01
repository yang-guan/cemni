package com.huiju.console.dict.logic;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.DataDict;
import com.huiju.console.dict.eao.DictEaoLocal;
import com.huiju.console.dict.entity.Dict;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "DictBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DictBean extends GenericLogicImpl<Dict, Long> implements DictRemote {
    @EJB(mappedName = "DictEaoBean")
    private DictEaoLocal dictEao;

    @Override
    protected GenericEao<Dict, Long> getGenericEao() {
        return dictEao;
    }

    /**
     * 加载数据字典
     */
    @Override
    public void loadDict() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_isValid", 1);

        String[] sort = { "dictCode,asc", "orderNo,asc" };
        DataDict.loadDict(this.findAll(searchParam, sort));
    }

}