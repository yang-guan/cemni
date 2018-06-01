package com.huiju.permission.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;

/**
 * 用户与资源组关系Eao实现类
 * 
 * @author zhangxj
 */
@Stateless(mappedName = "UserAuthGroupEaoBean")
public class UserAuthGroupEaoBean extends
		GenericEaoImpl<UserAuthGroup, UserAuthGroupPK> implements
		UserAuthGroupEaoLocal {
	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}

	@Override
	public List<AuthGroup> findAuthGroups(String clientCode, String userCode) {
		String jpql = "select a from UserAuthGroup o  join o.authGroup a"// join
																			// fetch
																			// a.authGroupDetails
				+ " where o.clientCode=?1 and o.userCode=?2";
		List<AuthGroup> authGroups = executeQuery(jpql, clientCode, userCode);
		return authGroups;
	}

	@Override
	public List<AuthGroupDetail> findAuthGroupDetails(String clientCode,
			String userCode) {
		String jpql = "select a.authGroupDetails from UserAuthGroup o join o.authGroup a join fetch a.authGroupDetails"
				+ " where o.clientCode=?1 and o.userCode=?2";
		List<AuthGroupDetail> authGroupDetails = executeQuery(jpql, clientCode,
				userCode);
		return authGroupDetails;
	}

}
