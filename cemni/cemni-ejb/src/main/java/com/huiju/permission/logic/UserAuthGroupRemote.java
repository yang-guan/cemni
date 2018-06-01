package com.huiju.permission.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.module.data.BaseEntity;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;

/**
 * 用户与资源组关系逻辑远程接口
 * @author Administrator
 */
@Remote
public interface UserAuthGroupRemote extends GenericLogic<UserAuthGroup, UserAuthGroupPK> {
	
	/**
	 * 批量删除
	 * @param ids 联合主键list
	 */
	public void removePKs(List<UserAuthGroupPK> ids);
	
    /**
     * 按照用户配置的资源组创建过滤条件
     * 
     * @param clientCode 客户端编码
     * @param userCode 用户编码
     * @param entityClass 过滤的实体类
     * @param excludes 排除权限过滤字段
     * @return Map<key, value> 比如过滤采购组织 key: IN_purchasingOrgCode, value:1000,2000,3000
     */
    public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode, Class<? extends BaseEntity<?>> entityClass, String... excludes);

    /**
     * 按照用户配置的资源组创建过滤条件
     * 
     * @param clientCode 客户端编码
     * @param userCode 用户编码
     * @param entityClass 过滤的实体类
     * @param foreignName 细单对应主单实体属性名
     * @param excludes 排除权限过滤字段
     * @return Map<key, value> 比如过滤采购组织 key: IN_purchasingOrgCode, value:1000,2000,3000
     */
    public Map<String, Object> buildAuthFieldParamsDetail(String clientCode, String userCode, Class<? extends BaseEntity<?>> entityClass, String foreignName, String... excludes);

    /**
     * 按照用户配置的资源组创建过滤条件
     * 
     * @param clientCode
     * @param userCode
     * @param entityName
     * @param fieldNames
     * @param excludes 排除权限过滤字段
     * @return Map<key, value> 比如过滤采购组织 key: IN_purchasingOrgCode,  value:1000,2000,3000
     */
    public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode, String entityName, List<String> fieldNames,String...foreignName);

	/**
	 * 按照用户配置的资源组创建过滤条件
	 * @param clientCode
	 * @param userCode
	 * @param entityClass
	 * @return Map<key, value> 比如过滤采购组织 key: IN_purchasingOrgCode, value:1000,2000,3000
	 */
	//public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode,Class<? extends BaseEntity<?>> entityClass);

	/**
	 * 获取用户已分配的采购组织编码
	 * @param clientCode
	 * @param userCode
	 * @return
	 */
	//public List<String> getPurchasingOrgCodes(String clientCode, String userCode);

	/**
	 * 获取用户已分配的公司编码
	 * @param clientCode
	 * @param userCode
	 * @return
	 */
	//public List<String> getCompanyCodes(String clientCode, String userCode);
	
	/**
	 * 获取用户已分配的采购组编码
	 * @param clientCode
	 * @param userCode
	 * @return
	 */
	//public List<String> getPurchasingGroupCodes(String clientCode, String userCode);
	
}
