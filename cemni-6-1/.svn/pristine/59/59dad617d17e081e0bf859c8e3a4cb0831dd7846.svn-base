package com.huiju.permission.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.contract.entity.Contract;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.permission.eao.AuthFieldEaoLocal;
import com.huiju.permission.entity.AuthField;

/**
 * 权限字段LogicBean
 * 
 * @author Linjx
 */
@Stateless(mappedName = "AuthFieldBean")
public class AuthFieldBean extends GenericLogicImpl<AuthField, String>
		implements AuthFieldLocal, AuthFieldRemote {

	@EJB(mappedName = "AuthFieldEaoBean")
	private AuthFieldEaoLocal authFieldEao;

	@Override
	protected GenericEao<AuthField, String> getGenericEao() {
		return authFieldEao;
	}
}
