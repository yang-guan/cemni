package com.huiju.permission.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.core.sys.entity.User;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.AuthGroupPK;

/**
 * 资源组远程逻辑接口类
 * 
 * @author Linjx
 */
@Remote
public interface AuthGroupRemote extends GenericLogic<AuthGroup,AuthGroupPK> {
	
	/**
	 * 保存权限字段明细
	 * @param authGroup 资源组
	 * @param authCodes 权限字段
	 * @param fieldOrders 字段排序
	 * @param authValues 权限字段值
	 */
    public void persistDetails(AuthGroup authGroup, List<String> authCodes, List<String> fieldOrders,List<String> authValues, User user);
	
	/**
	 * 查找资源组所分配的权限字段
	 * @param searchParams 查询条件
	 * @param sort 排序
	 * @return
	 */
	public List<AuthGroupDetail> findAllDetails(Map<String, Object> searchParams, String sort);  
	
} 
