package com.huiju.expandbusi.salesmananalyze.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.salesmananalyze.entity.Storedetail;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface StoredetailEaoLocal extends GenericEao<Storedetail, Long> {
}