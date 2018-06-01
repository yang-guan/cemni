package com.huiju.actment.activity.logic;

import javax.ejb.Remote;

import com.huiju.actment.activity.entity.Scope;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ScopeRemote extends GenericLogic<Scope, Long> {

}