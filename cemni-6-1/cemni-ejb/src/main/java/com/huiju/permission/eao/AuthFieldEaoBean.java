package com.huiju.permission.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.permission.entity.AuthField;

/**
 * 权限字段EaoBean
 * 
 * @author Linjx
 */
@Stateless(mappedName = "AuthFieldEaoBean")
public class AuthFieldEaoBean extends GenericEaoImpl<AuthField, String>
		implements AuthFieldEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}
}
