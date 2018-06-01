package com.huiju.permission.eao;

import javax.ejb.Local;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupPK;

/**
 * 资源组Eao本地接口类
 * 
 * @author Linjx
 */
@Local
public interface AuthGroupEaoLocal extends GenericEao<AuthGroup, AuthGroupPK> {
}
