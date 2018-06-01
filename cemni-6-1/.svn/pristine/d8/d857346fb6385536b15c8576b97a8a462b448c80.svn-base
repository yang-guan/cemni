package com.huiju.expandbusi.agentanalyze.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.expandbusi.agentanalyze.eao.AgentanalyzeEaoLocal;
import com.huiju.expandbusi.agentanalyze.entity.Agentanalyze;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "AgentanalyzeBean")
public class AgentanalyzeBean extends GenericLogicImpl<Agentanalyze, Long> implements AgentanalyzeRemote {
    @EJB(mappedName = "AgentanalyzeEaoBean")
    private AgentanalyzeEaoLocal agentanalyzeEao;

    @Override
    protected GenericEao<Agentanalyze, Long> getGenericEao() {
        return agentanalyzeEao;
    }
}