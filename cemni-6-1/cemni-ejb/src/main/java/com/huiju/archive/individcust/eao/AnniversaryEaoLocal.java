package com.huiju.archive.individcust.eao;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.Anniversary;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface AnniversaryEaoLocal extends GenericEao<Anniversary, Long> {
}