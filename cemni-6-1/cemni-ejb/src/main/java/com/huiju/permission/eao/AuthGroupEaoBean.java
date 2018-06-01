package com.huiju.permission.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupPK;

/**
 * 资源组Eao实现类
 * 
 * @author Linjx
 */
@Stateless(mappedName = "AuthGroupEaoBean")
public class AuthGroupEaoBean extends GenericEaoImpl<AuthGroup,AuthGroupPK> implements AuthGroupEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}
