package com.huiju.actment.activity.eao;

import javax.ejb.Local;

import com.huiju.actment.activity.entity.Scope;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ScopeEaoLocal extends GenericEao<Scope, Long>{

}
