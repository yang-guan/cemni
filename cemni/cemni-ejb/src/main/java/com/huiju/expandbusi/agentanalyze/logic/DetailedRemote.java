package com.huiju.expandbusi.agentanalyze.logic;

import javax.ejb.Remote;

import com.huiju.expandbusi.agentanalyze.entity.Detailed;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface DetailedRemote extends GenericLogic<Detailed, Long> {
}