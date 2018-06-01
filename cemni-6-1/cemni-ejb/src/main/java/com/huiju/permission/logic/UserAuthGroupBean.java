package com.huiju.permission.logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.huiju.module.data.BaseEntity;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.permission.eao.UserAuthGroupEaoLocal;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;
import com.huiju.utils.EntityReflectionUtils;

/**
 * 用户与资源组关系逻辑实现类
 * 
 * @author chenyx
 */
@Stateless(mappedName="UserAuthGroupBean")
public class UserAuthGroupBean extends GenericLogicImpl<UserAuthGroup, UserAuthGroupPK>
        implements UserAuthGroupRemote, UserAuthGroupLocal {

    @EJB
    private UserAuthGroupEaoLocal userAuthGroupEao;

    @Override
    protected GenericEao<UserAuthGroup, UserAuthGroupPK> getGenericEao() {
        return userAuthGroupEao;
    }

    @Override
    public void removePKs(List<UserAuthGroupPK> ids) {
        if (ids != null) {
            userAuthGroupEao.remove(ids);
        }
    }
    
    @Override
    public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode,Class<? extends BaseEntity<?>> entityClass,String...excludes) {
        List<String> fieldNames = EntityReflectionUtils.getAllFieldNames(entityClass,excludes);
        String entityName = entityClass.getSimpleName();
        return buildAuthFieldParams(clientCode, userCode, entityName, fieldNames);
    }

    @Override
    public Map<String, Object> buildAuthFieldParamsDetail(String clientCode, String userCode,Class<? extends BaseEntity<?>> entityClass,String foreignName,String...excludes) {
        List<String> fieldNames = EntityReflectionUtils.getAllFieldNames(entityClass,excludes);
        String entityName = entityClass.getSimpleName();
        return buildAuthFieldParams(clientCode, userCode, entityName, fieldNames,new String[]{foreignName});
    }
    
    @Override
    public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode, String entityName,List<String> fieldNames,String...foreignName) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        List<AuthGroupDetail> authGroupDetails = userAuthGroupEao.findAuthGroupDetails(clientCode, userCode);
        if (authGroupDetails.size() == 0) {// 账号没有任何资源组时，不能查看需要过滤的数据
            //params.put("IS_" + fieldNames.get(0), "NULL");
            return params;
        }
        // 与实体字段进行对比，取出该实体需要过滤的字段
        List<AuthGroupDetail> result = new ArrayList<AuthGroupDetail>();
        for (String fieldName : fieldNames) {
            for (AuthGroupDetail agd : authGroupDetails) {
                if (agd == null) {
                    continue;
                }
                if (agd.getAuthField() == null)
                    continue;
                // 如果查询的是本表，使用fieldCode
                if (agd.getAuthField().getTableName().equals(entityName)) {
                    if (fieldName.equalsIgnoreCase(agd.getAuthField().getFieldCode())) {
                        result.add(agd);
                    }
                } else {
                    // 如果查询的是其他表，使用authCode
                    if (fieldName.equalsIgnoreCase(agd.getAuthField().getAuthCode())) {
                        result.add(agd);
                    }
                }
            }
        }
        // 生成参数
        for (AuthGroupDetail authGroupDetail : result) {
            String fieldCode = "";
            String foreignCode = "";
            if(foreignName!=null&&foreignName.length>0){
                for(String s:foreignName){
                    foreignCode += s;
                    foreignCode += "_";
                }
                params.put("DISTINCT", true);
            }
            // 如果查询的是本表，使用fieldCode
            if (authGroupDetail.getAuthField().getTableName().equals(entityName)) {
                fieldCode = authGroupDetail.getAuthField().getFieldCode();
            } else {// 如果查询的是其他表，使用authCode
                fieldCode = authGroupDetail.getAuthField().getAuthCode();
            }
            // 字段名或值为空时，不添加查询参数
            String value = authGroupDetail.getAuthValue();
            if (StringUtils.isBlank(fieldCode) || StringUtils.isBlank(value) || "*".equals(value)) {
                continue;
            }
            if (StringUtils.isNotBlank(foreignCode)) {
                fieldCode = foreignCode + fieldCode;
            }
            if (!params.containsKey("IN_" + fieldCode)) {
                params.put("IN_" + fieldCode, value);
            } else {
                Object object = params.get("IN_" + fieldCode);
                if (!("," + object + ",").contains("," + value + ",")) {
                    params.put("IN_" + fieldCode, object + "," + value);
                }
            }
        }
        return params;
    }

/*    
 public Map<String, Object> buildAuthFieldParams(String clientCode, String userCode,
            Class<? extends BaseEntity<?>> entityClass) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        List<AuthGroupDetail> authGroupDetails = userAuthGroupEao.findAuthGroupDetails(clientCode, userCode);
        if (authGroupDetails.size() == 0) {// 账号没有任何资源组时，不能查看需要过滤的数据
            String idName = "";
            Field[] fields = entityClass.getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Id.class) != null || f.getAnnotation(EmbeddedId.class) != null) {
                    idName = f.getName();
                    break;
                }
            }
            params.put("IS_" + idName, "NULL");
            return params;
        }
        // 与实体字段进行对比，取出该实体需要过滤的字段
        List<AuthGroupDetail> result = new ArrayList<AuthGroupDetail>();
        List<String> fieldNameList = BeanUtils.getValidFields(entityClass, null);
        for (String fieldName : fieldNameList) {
            for (AuthGroupDetail agd : authGroupDetails) {
                if (agd == null) {
                    continue;
                }
                if (agd.getAuthField() == null)
                    continue;
                // 如果是业务表，使用权限字段编码
                if (agd.getAuthField().getTableName().equals(entityClass.getSimpleName())) {
                    if (fieldName.equalsIgnoreCase(agd.getAuthField().getFieldCode())) {
                        result.add(agd);
                    }
                } else {
                    // 如果是自身主数据表，使用权限字段名称(主键_字段名)
                    if (fieldName.equalsIgnoreCase(agd.getAuthField().getAuthCode())) {
                        result.add(agd);
                    }
                }
            }
        }
        // 生成参数
        for (AuthGroupDetail authGroupDetail : result) {
            String fieldCode = "";
            // 如果查询的是本表，使用fieldCode
            if (authGroupDetail.getAuthField().getTableName().equals(entityClass.getSimpleName())) {
                fieldCode = authGroupDetail.getAuthField().getFieldCode();
            } else {// 如果查询的是其他表，使用authCode
                fieldCode = authGroupDetail.getAuthField().getAuthCode();
            }
            // 字段名或值为空时，不添加查询参数
            String value = authGroupDetail.getAuthValue();
            if (StringUtils.isBlank(fieldCode) || StringUtils.isBlank(value) || "*".equals(value)) {
                continue;
            }
            if (!params.containsKey("IN_" + fieldCode)) {
                params.put("IN_" + fieldCode, value);
            } else {
                Object object = params.get("IN_" + fieldCode);
                if (!("," + object + ",").contains("," + value + ",")) {
                    params.put("IN_" + fieldCode, object + "," + value);
                }
            }
        }
        return params;
    }

    @Override
    public List<String> getPurchasingOrgCodes(String clientCode, String userCode) {
        List<String> purchasingOrgCodes = new ArrayList<String>();
        List<AuthGroupDetail> authGroupDetails = userAuthGroupEao.findAuthGroupDetails(clientCode, userCode);
        for (AuthGroupDetail detail : authGroupDetails) {
            if (detail.getAuthField() == null)
                continue;
            if ("purchasingOrgCode".equalsIgnoreCase(detail.getAuthField().getAuthCode())) {
                if ("*".equals(detail.getAuthValue())) {// 查找所有
                    Map<String, Object> searchParams = new LinkedHashMap<String, Object>();
                    searchParams.put("EQ_clientCode", clientCode);
                    //Specification<PurchasingOrganization> spec = QueryUtils.newSpecification(searchParams);
                    List<PurchasingOrganization> list = purchasingOrgBean.findAll(searchParams);
                    for (PurchasingOrganization porg : list) {
                        purchasingOrgCodes.add(porg.getPurchasingOrgCode());
                    }
                    break;
                } else if (!purchasingOrgCodes.contains(detail.getAuthValue())) {
                    purchasingOrgCodes.addAll(Arrays.asList(detail.getAuthValue().split(",")));
                }
            }
        }
        return purchasingOrgCodes;
    }

    @Override
    public List<String> getCompanyCodes(String clientCode, String userCode) {
        List<String> companyCodes = new ArrayList<String>();
        List<AuthGroupDetail> authGroupDetails = userAuthGroupEao.findAuthGroupDetails(clientCode, userCode);
        for (AuthGroupDetail detail : authGroupDetails) {
            if (detail.getAuthField() == null)
                continue;
            if ("companyCode".equalsIgnoreCase(detail.getAuthField().getAuthCode())) {
                if ("*".equals(detail.getAuthValue())) {// 查找所有
                    Map<String, Object> searchParams = new LinkedHashMap<String, Object>();
                    searchParams.put("EQ_clientCode", clientCode);
                    //Specification<Company> spec = QueryUtils.newSpecification(searchParams);
                    List<Company> list = companyBean.findAll(searchParams);
                    for (Company company : list) {
                        companyCodes.add(company.getCompanyCode());
                    }
                    break;
                } else if (!companyCodes.contains(detail.getAuthValue())) {
                    companyCodes.addAll(Arrays.asList(detail.getAuthValue().split(",")));
                }
            }
        }
        return companyCodes;
    }
*/
}
