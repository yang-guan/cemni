package com.huiju.permission.eao;

import javax.ejb.Local;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.AuthGroupDetailPK;

/**
 * 资源组分配权限字段Eao本地接口类
 * 
 * @author Linjx
 */
@Local
public interface AuthGroupDetailEaoLocal extends GenericEao<AuthGroupDetail, AuthGroupDetailPK> {

}
