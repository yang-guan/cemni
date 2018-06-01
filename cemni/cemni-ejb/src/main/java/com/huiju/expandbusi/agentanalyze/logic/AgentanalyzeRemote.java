package com.huiju.expandbusi.agentanalyze.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.agentanalyze.entity.Agentanalyze;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface AgentanalyzeRemote extends GenericLogic<Agentanalyze, Long> {
}