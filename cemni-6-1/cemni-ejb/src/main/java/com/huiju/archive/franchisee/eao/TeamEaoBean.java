package com.huiju.archive.franchisee.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.franchisee.entity.Team;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "TeamEaoBean")
public class TeamEaoBean extends GenericEaoImpl<Team, Long> implements TeamEaoLocal{
	@Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
