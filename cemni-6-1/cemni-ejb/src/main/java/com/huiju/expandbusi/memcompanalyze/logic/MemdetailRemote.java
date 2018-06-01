package com.huiju.expandbusi.memcompanalyze.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.memcompanalyze.entity.Memdetail;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface MemdetailRemote extends GenericLogic<Memdetail, Long> {
}