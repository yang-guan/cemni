package com.huiju.permission.eao;

import java.util.List;

import javax.ejb.Local;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;

/**
 * 用户与资源组关系Eao本地接口
 * 
 * @author zhangxj
 */
@Local
public interface UserAuthGroupEaoLocal extends GenericEao<UserAuthGroup, UserAuthGroupPK> {

	/**
	 * 查找资源组列表
	 * 
	 * @param clientCode 客户端编码
	 * @param userCode	用户编码
	 * @return
	 */
	public List<AuthGroup> findAuthGroups(String clientCode, String userCode);

	/**
	 * 查找资源组明细列表
	 * 
	 * @param clientCode	客户端编码
	 * @param userCode	用户编码
	 * @return
	 */
	public List<AuthGroupDetail> findAuthGroupDetails(String clientCode, String userCode);

}
