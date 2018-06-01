package com.huiju.expandbusi.agentanalyze.eao;

import javax.ejb.Local;

import com.huiju.expandbusi.agentanalyze.entity.Agentanalyze;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface AgentanalyzeEaoLocal extends GenericEao<Agentanalyze, Long> {
}