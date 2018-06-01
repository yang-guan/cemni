package com.huiju.afterservice.busiregist.eao;

import java.util.Map;

import javax.ejb.Local;

import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings("rawtypes")
public interface BusiRegistEaoLocal extends GenericEao<BusiRegist, Long> {

    Map queryPageList(Map searchParam);

    Map queryOrgStoreList(Map searchParam);
}