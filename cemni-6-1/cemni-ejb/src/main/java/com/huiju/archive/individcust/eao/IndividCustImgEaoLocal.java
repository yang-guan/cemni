package com.huiju.archive.individcust.eao;

import javax.ejb.Local;

import com.huiju.archive.individcust.entity.IndividCustImg;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface IndividCustImgEaoLocal extends GenericEao<IndividCustImg, Long> {
}