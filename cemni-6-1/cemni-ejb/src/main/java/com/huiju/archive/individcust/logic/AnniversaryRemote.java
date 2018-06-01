package com.huiju.archive.individcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface AnniversaryRemote extends GenericLogic<Anniversary, Long>{
}