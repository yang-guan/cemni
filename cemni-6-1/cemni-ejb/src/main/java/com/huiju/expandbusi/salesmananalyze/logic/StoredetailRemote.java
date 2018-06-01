package com.huiju.expandbusi.salesmananalyze.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.salesmananalyze.entity.Storedetail;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface StoredetailRemote extends GenericLogic<Storedetail, Long> {
}