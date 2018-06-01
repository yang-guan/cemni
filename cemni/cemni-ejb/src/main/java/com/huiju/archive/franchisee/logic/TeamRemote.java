package com.huiju.archive.franchisee.logic;

import javax.ejb.Remote;

import com.huiju.archive.franchisee.entity.Team;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface TeamRemote extends GenericLogic<Team, Long> {

}
