package com.huiju.console.store.logic;

import java.util.List;

import javax.ejb.Remote;

import com.huiju.console.store.entity.Store;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface StoreRemote extends GenericLogic<Store, Long> {

    /**
     * 指标-载入门店
     * 
     * @return
     * 
     * @author：yuhb
     * @date：2017年3月4日 下午4:41:34
     */
    List qryBigAreaStore();
}