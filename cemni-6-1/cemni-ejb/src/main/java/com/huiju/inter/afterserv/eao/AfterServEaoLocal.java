package com.huiju.inter.afterserv.eao;

import javax.ejb.Local;

import com.huiju.inter.afterserv.entity.AfterServ;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface AfterServEaoLocal extends GenericEao<AfterServ, Long> {
}