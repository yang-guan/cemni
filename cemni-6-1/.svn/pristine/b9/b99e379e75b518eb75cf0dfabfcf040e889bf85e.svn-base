package com.huiju.permission.logic;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.core.sys.entity.User;
import com.huiju.module.data.Sort;
import com.huiju.module.data.Specification;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.data.util.QueryUtils;
import com.huiju.permission.eao.AuthGroupDetailEaoLocal;
import com.huiju.permission.eao.AuthGroupEaoLocal;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.AuthGroupPK;

/**
 * 资源组逻辑实现类
 * 
 * @author Linjx
 */
@Stateless(mappedName="AuthGroupBean")
public class AuthGroupBean extends GenericLogicImpl<AuthGroup, AuthGroupPK> implements AuthGroupLocal, AuthGroupRemote {

	@EJB
	private AuthGroupEaoLocal authGroupEao;

	@EJB
	private AuthGroupDetailEaoLocal authGroupDetailEao;

	@Override
	protected GenericEao<AuthGroup, AuthGroupPK> getGenericEao() {
		return authGroupEao;
	}

	@Override
	public void persistDetails(AuthGroup authGroup, List<String> authCodes, List<String> fieldOrders,
			List<String> authValues, User user) {
		Map<String, Object> searchParams = new LinkedHashMap<String, Object>();
		searchParams.put("EQ_authGroup_clientCode", authGroup.getClientCode());
		searchParams.put("EQ_authGroup_agrCode", authGroup.getAgrCode());
		Specification<AuthGroupDetail> spec = QueryUtils.newSpecification(searchParams);
		// 判断如果已存在分配的权限字段，则先删除
		if (authGroupDetailEao.findAll(spec).size() > 0) {
			authGroupDetailEao.executeUpdate("delete from AuthGroupDetail o where o.authGroup=?1", authGroup);
		}
		AuthGroupDetail authGroupDetail = null;
		for (int i = 0; i < authCodes.size(); i++) {
			if ("".equals(authCodes.get(i))) {
				continue;
			}
			authGroupDetail = new AuthGroupDetail();
			authGroupDetail.setClientCode(authGroup.getClientCode());
			authGroupDetail.setAgrCode(authGroup.getAgrCode());
			authGroupDetail.setAuthCode(authCodes.get(i));
			authGroupDetail.setFieldOrder(fieldOrders.get(i));
			authGroupDetail.setAuthValue(authValues.get(i));
			authGroupDetail.setAuthGroup(authGroup);
			// 设置创建信息
			authGroupDetail.setCreateUserId(user.getUserId());
			authGroupDetail.setCreateUserName(user.getUserName());
			authGroupDetail.setCreateTime(Calendar.getInstance());
			authGroupDetailEao.persist(authGroupDetail);
		}
	}

	@Override
	public List<AuthGroupDetail> findAllDetails(Map<String, Object> searchParams, String sortProperties) {
		Specification<AuthGroupDetail> spec = QueryUtils.newSpecification(searchParams);
		Sort _sort = new Sort(Sort.Direction.ASC, sortProperties);
		return authGroupDetailEao.findAll(spec, _sort);
	}

}
