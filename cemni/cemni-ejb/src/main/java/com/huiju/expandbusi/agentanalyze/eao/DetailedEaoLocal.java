package com.huiju.expandbusi.agentanalyze.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.agentanalyze.entity.Detailed;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface DetailedEaoLocal extends GenericEao<Detailed, Long> {
}