package com.huiju.archive.franchisee.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.archive.franchisee.eao.TeamEaoLocal;
import com.huiju.archive.franchisee.entity.Team;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "TeamBean")
public class TeamBean extends GenericLogicImpl<Team, Long> implements TeamRemote {
    @EJB
    private TeamEaoLocal TeamtEao;

    @Override
    protected GenericEao<Team, Long> getGenericEao() {
        return TeamtEao;
    }
}