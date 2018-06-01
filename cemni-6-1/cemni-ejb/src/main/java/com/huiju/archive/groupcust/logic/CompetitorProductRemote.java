package com.huiju.archive.groupcust.logic;

import javax.ejb.Remote;

import com.huiju.archive.groupcust.entity.CompetitorProduct;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface CompetitorProductRemote extends GenericLogic<CompetitorProduct, Long> {
}