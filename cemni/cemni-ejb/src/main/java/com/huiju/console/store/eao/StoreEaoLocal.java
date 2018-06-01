package com.huiju.console.store.eao;

import javax.ejb.Local;

import com.huiju.console.store.entity.Store;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface StoreEaoLocal extends GenericEao<Store, Long> {
}