package com.huiju.archive.individcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.IndividCustImg;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface IndividCustImgRemote extends GenericLogic<IndividCustImg, Long> {

}