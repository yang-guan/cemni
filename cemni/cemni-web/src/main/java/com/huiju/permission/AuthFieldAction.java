package com.huiju.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.log.annotation.Logging;
import com.huiju.module.log.annotation.Logging.LogType;
import com.huiju.module.log.annotation.Module;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.entity.AuthField;
import com.huiju.permission.logic.AuthFieldRemote;

/**
 * 权限字段管理Action
 * 
 * @author Administrator
 */
@Module(value = "AuthField")
public class AuthFieldAction<K, V> extends BaseAction<AuthField, String> {
    private static final long serialVersionUID = 5197791365162078016L;
    @EJB
    private AuthFieldRemote authFieldLogic;

    private List<String> ids;

    private List<String> keys;

    /**
     * 跳转到列表界面
     * 
     * @return "LIST"
     */
    public String list() {
        jsPath.add("/js/permission/Q.permission.AuthFieldConfig.js");
        // 菜单对应的权限
        String[] authorities = { "B_PERMISSION_AUTHFIELD_LIST", "B_PERMISSION_AUTHFIELD_EDIT", "B_PERMISSION_AUTHFIELD_ADD", "B_PERMISSION_AUTHFIELD_DELETE" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    /**
     * 获取列表/查询数据（分页）
     * 
     * @return
     */
    public String getJson() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            Page<AuthField> page = new Page<AuthField>(start, limit, sort, dir);
            page.asc("authCode");
            page = authFieldLogic.findAll(page, searchParams);
            renderJson(DataUtils.toJson(page));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 获取列表/查询数据（全部）
     * 
     * @return
     */
    public String getList() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            // 过滤已选权限字段
            if (keys != null && keys.size() != 0) {
                String excludeFieldCodes = StringUtils.join(keys, ",");
                if (!"".equals(excludeFieldCodes.trim())) {
                    searchParams.put("NOTIN_authCode", excludeFieldCodes);
                }
            }
            Page<AuthField> page = new Page<AuthField>(start, limit, sort, dir);
            page.asc("authCode");
            page = authFieldLogic.findAll(page, searchParams);
            renderJson(DataUtils.toJson(page));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 保存表单
     * 
     * @return
     */
    @Logging(module = "AuthField", action = "新建权限字段", message = "新建权限字段:{$.model}", type = LogType.OPERATION)
    public String save() {
        try {
            // 判断该权限字段是否存在
            AuthField authField = authFieldLogic.findById(model.getAuthCode());
            if (authField == null) {
                // 判断字段名和表名是否唯一
                Map<String, Object> searchParams = new HashMap<String, Object>();
                searchParams.put("EQ_fieldCode", model.getFieldCode());
                searchParams.put("EQ_tableName", model.getTableName());
                List<AuthField> authFields = new ArrayList<AuthField>();
                authFields = authFieldLogic.findAll(searchParams);
                if (authFields == null || authFields.size() == 0) {
                    authFieldLogic.persist(model);
                    //日志
                    return dealJson(true);
                } else {
                    String info = getText("authField.fieldCodeAndTableNameIsUnique");// 字段名和表名必须唯一！
                    renderJson("{success:false,info:'" + info + "'}");
                    return null;
                }

            } else {
                String info = getText("authField.authCodeExists");// 该权限字段已经存在！
                renderJson("{success:false,info:'" + info + "'}");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 返回编辑表单数据对象
     * 
     * @return
     */
    public String edit() {
        try {
            AuthField authField = authFieldLogic.findById(id);
            return dealJson(true, DataUtils.toJson(authField));
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 更新表单
     * 
     * @return
     */
    @Logging(module = "AuthField", action = "修改权限字段", message = "修改权限字段:{$.model}", type = LogType.OPERATION)
    public String update() {
        try {
            authFieldLogic.merge(model);
            return dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 删除选中对象数据
     * 
     * @return
     */
    @Logging(module = "AuthField", action = "删除权限字段", message = "删除权限字段:{$.ids}", type = LogType.OPERATION)
    public String delete() {
        try {
            authFieldLogic.remove(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

}