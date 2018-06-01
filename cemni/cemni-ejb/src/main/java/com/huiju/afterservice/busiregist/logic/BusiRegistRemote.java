package com.huiju.afterservice.busiregist.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface BusiRegistRemote extends GenericLogic<BusiRegist, Long> {

    Map queryPageList(Map searchParam);

    Map queryOrgStoreList(Map searchParam);
}