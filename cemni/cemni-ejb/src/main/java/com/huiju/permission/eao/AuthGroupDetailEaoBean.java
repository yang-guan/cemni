package com.huiju.permission.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.AuthGroupDetailPK;

/**
 * 资源组分配权限字段Eao实现类
 * 
 * @author Linjx
 */
@Stateless(mappedName = "AuthGroupDetailEaoBean")
public class AuthGroupDetailEaoBean extends GenericEaoImpl<AuthGroupDetail, AuthGroupDetailPK> implements
		AuthGroupDetailEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}
