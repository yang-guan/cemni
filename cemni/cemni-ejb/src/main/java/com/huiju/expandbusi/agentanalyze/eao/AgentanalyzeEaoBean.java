package com.huiju.expandbusi.agentanalyze.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.expandbusi.agentanalyze.entity.Agentanalyze;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "AgentanalyzeEaoBean")
public class AgentanalyzeEaoBean extends GenericEaoImpl<Agentanalyze, Long> implements AgentanalyzeEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}