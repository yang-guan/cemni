package com.huiju.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.core.sys.entity.User;
import com.huiju.core.sys.logic.UserRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.log.annotation.Logging;
import com.huiju.module.log.annotation.Logging.LogType;
import com.huiju.module.log.annotation.Module;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.entity.AuthGroup;
import com.huiju.permission.entity.AuthGroupDetail;
import com.huiju.permission.entity.AuthGroupPK;
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;
import com.huiju.permission.logic.AuthGroupRemote;
import com.huiju.permission.logic.UserAuthGroupRemote;

/**
 * 资源组Action
 * 
 * @author Administrator
 */
@Module(value = "AuthGroup")
public class AuthGroupAction<K, V> extends BaseAction<AuthGroup, AuthGroupPK> {

    private static final long serialVersionUID = -1350303615792197643L;

    @EJB
    private AuthGroupRemote authGroupLogic;

    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    @EJB(mappedName = "UserBean")
    private UserRemote userLogic;

    private String userCode;
    private String agrCode;
    private List<String> authCodes;
    private List<String> fieldOrders;
    private List<String> authValues;
    private List<String> saveUsersKey;
    private boolean filterFlag = false;
    private List<UserAuthGroupPK> userAuthGroups;

    /**
     * 跳转到列表界面
     * 
     * @return
     */
    public String list() {
        return LIST;
    }

    /**
     * 获取分页列表
     * 
     * @return
     */
    public void getJson() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        Page<AuthGroup> page = new Page<AuthGroup>(start, limit, sort, dir);
        searchParams.put("EQ_clientCode", WebUtils.getClientCode());
        page.asc("agrCode");
        if (filterFlag) {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put("EQ_userCode", userCode);
            List<UserAuthGroup> userAuthGroups = userAuthGroupLogic.findAll(searchParam);
            String agrCodes = "";
            if (userAuthGroups != null && userAuthGroups.size() > 0) {
                for (UserAuthGroup userAuthGroup : userAuthGroups) {
                    agrCodes = agrCodes + "," + userAuthGroup.getAgrCode();
                }
            }
            if (StringUtils.isNotBlank(agrCodes)) {
                searchParams.put("NOTIN_agrCode", agrCodes.substring(1));
            }
        }
        page = authGroupLogic.findAll(page, searchParams);
        renderJson(page, "authGroupDetails");
    }

    /**
     * 获取资源组分配资源组分页列表
     * 
     * @return
     */
    public String getDetailsJson() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            List<AuthGroupDetail> authGroupDetails = new ArrayList<AuthGroupDetail>();
            authGroupDetails = authGroupLogic.findAllDetails(searchParams, "agrCode");
            renderJson(DataUtils.toJson(authGroupDetails, new String[] { "authGroup.authGroupDetails" }));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 查询已分配给当前资源组的用户
     * 
     * @return
     */
    public String getRelatedUserJson() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            List<UserAuthGroup> userAuthGroups = new ArrayList<UserAuthGroup>();
            searchParams.put("EQ_clientCode", WebUtils.getClientCode());
            userAuthGroups = userAuthGroupLogic.findAll(searchParams);
            renderJson(DataUtils.toJson(userAuthGroups, new String[] { "authGroup.authGroupDetails" }));
        } catch (Exception ex) {
            ex.printStackTrace();
            return dealJson(false);
        }
        return NONE;
    }

    /**
     * 查询未分配的用户
     * 
     * @return
     */
    public String getUncheckedUsers() {
        try {
            // 接受一个资源组agrCode参数 过滤数据
            Map<String, Object> searchParams = new HashMap<String, Object>();
            searchParams.put("EQ_agrCode", agrCode);
            searchParams.put("EQ_clientCode", WebUtils.getClientCode());
            List<UserAuthGroup> userAuthGroups = userAuthGroupLogic.findAll(searchParams);
            Map<String, Object> searchMap = WebUtils.getParametersStartingWith(request);
            if (userAuthGroups.size() > 0) {
                String str = "";
                for (UserAuthGroup userAuthGroup : userAuthGroups) {
                    str += "," + userAuthGroup.getUserCode();
                }
                str = str.substring(1);
                searchMap.put("NOTIN_userCode", str);
            }
            Page<User> page = new Page<User>(start, limit, sort, dir);
            page = userLogic.findAll(page, searchMap);
            renderJson(DataUtils.toJson(page, new String[] { "roles" }));
        } catch (Exception e) {
            e.printStackTrace();
            return dealJson(false);
        }
        return NONE;
    }

    /**
     * 保存资源组和用户关系
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "保存用户资源组关系", message = "保存用户资源组关系:{$.userCode},{$.saveUsersKey}", type = LogType.OPERATION)
    public String saveAuthGroupUser() {
        try {
            for (String userCode : saveUsersKey) {
                UserAuthGroup userAuthGroup = new UserAuthGroup();
                userAuthGroup.setClientCode(WebUtils.getClientCode());
                userAuthGroup.setAgrCode(agrCode);
                userAuthGroup.setUserCode(userCode);
                userAuthGroupLogic.persist(userAuthGroup);
            }
            dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return dealJson(false);
        }
        return NONE;
    }

    /**
     * 删除资源组和用户关系
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "删除用户资源组关系", message = "删除用户资源组关系:{$.userAuthGroups}", type = LogType.OPERATION)
    public String deleteAuthGroupUser() {
        try {
            if (userAuthGroups != null) {
                for (UserAuthGroupPK pk : userAuthGroups) {
                    userAuthGroupLogic.removeById(pk);
                }
            }
            dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return dealJson(false);
        }
        return NONE;
    }

    /**
     * 获取列表
     * 
     * @return
     */
    public String getList() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            searchParams.put("EQ_clientCode", WebUtils.getClientCode());
            List<AuthGroup> authGroups = authGroupLogic.findAll(searchParams);
            renderJson(DataUtils.toJson(authGroups));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 保存
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "新建资源组", message = "新建资源组信息:{$.model}", type = LogType.OPERATION)
    public String save() {
        try {
            model.setClientCode(WebUtils.getClientCode());
            if (isExist(model)) {
                return dealJson(false, getText("message.data.exist"));
            }
            authGroupLogic.persist(model);
            return dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 保存资源组分配资源组列表
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "资源组分配权限字段", message = "资源组:{$.model.agrCode},分配权限字段:{$.authCodes}", type = LogType.OPERATION)
    public String saveDetails() {
        try {
            // 获取创建者信息
            User user = new User();
            user.setUserId(WebUtils.getUserId());
            user.setUserName(WebUtils.getUserName());
            model.setClientCode(WebUtils.getClientCode());
            authGroupLogic.persistDetails(model, authCodes, fieldOrders, authValues, user);
            return dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     * 
     * @return
     */
    public String edit() {
        try {
            AuthGroup authGroup = authGroupLogic.findById(id);
            return dealJson(true, DataUtils.toJson(authGroup));
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 修改
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "修改资源组", message = "修改资源组信息:{$.model}", type = LogType.OPERATION)
    public String update() {
        try {
            authGroupLogic.merge(model);
            return dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 删除
     * 
     * @return
     */
    @Logging(module = "AuthGroup", action = "删除资源组", message = "删除资源组信息:{$.id}", type = LogType.OPERATION)
    public String delete() {
        try {
            // 验证是否有被引用
            Map<String, Object> searchParams = new LinkedHashMap<String, Object>();
            searchParams.put("EQ_clientCode", id.getClientCode());
            searchParams.put("IN_agrCode", id.getAgrCode());
            long count = userAuthGroupLogic.count(searchParams);
            if (count == 0) {
                authGroupLogic.removeById(id);
            } else {
                return dealJson(false, getText("authGroup.refered"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 实体是否存在
     * 
     * @param authGroup
     * @return ture存在 false不存在
     */
    protected boolean isExist(AuthGroup authGroup) {
        AuthGroupPK authGroupPK = new AuthGroupPK(authGroup.getClientCode(), authGroup.getAgrCode());
        AuthGroup dv = authGroupLogic.findById(authGroupPK);
        if (dv != null) {
            return true;
        }
        return false;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAgrCode() {
        return agrCode;
    }

    public void setAgrCode(String agrCode) {
        this.agrCode = agrCode;
    }

    public List<String> getAuthCodes() {
        return authCodes;
    }

    public void setAuthCodes(List<String> authCodes) {
        this.authCodes = authCodes;
    }

    public List<String> getFieldOrders() {
        return fieldOrders;
    }

    public void setFieldOrders(List<String> fieldOrders) {
        this.fieldOrders = fieldOrders;
    }

    public List<String> getAuthValues() {
        return authValues;
    }

    public void setAuthValues(List<String> authValues) {
        this.authValues = authValues;
    }

    public List<String> getSaveUsersKey() {
        return saveUsersKey;
    }

    public void setSaveUsersKey(List<String> saveUsersKey) {
        this.saveUsersKey = saveUsersKey;
    }

    public boolean isFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(boolean filterFlag) {
        this.filterFlag = filterFlag;
    }

    public List<UserAuthGroupPK> getUserAuthGroups() {
        return userAuthGroups;
    }

    public void setUserAuthGroups(List<UserAuthGroupPK> userAuthGroups) {
        this.userAuthGroups = userAuthGroups;
    }

}
