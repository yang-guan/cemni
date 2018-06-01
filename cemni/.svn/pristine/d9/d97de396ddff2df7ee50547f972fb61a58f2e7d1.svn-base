package com.huiju.permission;

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
import com.huiju.permission.entity.UserAuthGroup;
import com.huiju.permission.entity.UserAuthGroupPK;
import com.huiju.permission.logic.UserAuthGroupRemote;

/**
 * 用户与资源组关系Action
 * 
 * @author zhangxj
 */
@Module("用户资源组关系")
public class UserAuthGroupAction<K, V> extends BaseAction<UserAuthGroup, UserAuthGroupPK> {
    private static final long serialVersionUID = 4809897592668319684L;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    private List<UserAuthGroup> userAuthGroups;

    private List<UserAuthGroupPK> ids;

    /**
     * 加载列表
     * 
     * @return
     */
    public String list() {
        return LIST;
    }

    /**
     * 获取有分页的Json数据
     * 
     * @return
     */
    public String getJson() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            //在创建page之前替换sort的内容，如：sorgcode -> sorgcode。
            //目的：使得联合主键字段可以排序
            boolean haveSort = true;
            if (StringUtils.isNotBlank(sort)) {
                sort = sort.replaceAll("\\[\"", "_");
                sort = sort.replaceAll("\"\\]", "");
                haveSort = false;
            }

            searchParams.put("EQ_clientcode", WebUtils.getClientCode());
            Page<UserAuthGroup> page = new Page<UserAuthGroup>(start, limit, sort, dir);
            if (haveSort) {//前端未传递排序则默认指定一个字段排序
                page.asc("usercode");
            }
            page = userAuthGroupLogic.findAll(page, searchParams);
            renderJson(DataUtils.toJson(page));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 获取没有分页的Json数据
     * 
     * @return
     */
    public String getList() {
        try {
            Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
            List<UserAuthGroup> userAuthGroups = userAuthGroupLogic.findAll(searchParams);
            renderJson(DataUtils.toJson(userAuthGroups, new String[] { "authGroupDetails" }));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }

    /**
     * 新建
     * 
     * @return
     */
    public String save() {
        try {
            if (userAuthGroups != null && userAuthGroups.size() > 0) {
                for (UserAuthGroup userAuthGroup : userAuthGroups) {
                    addUserAuthGroup(userAuthGroup);
                }
            }
            return dealJson(true);
        } catch (Exception e) {
            return ERROR;
        }
    }

    /**
     * 编辑获取单条记录数据
     * 
     * @return
     */
    public String edit() {
        try {
            UserAuthGroup userAuthGroup = userAuthGroupLogic.findById(id);
            return dealJson(true, DataUtils.toJson(userAuthGroup));
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 更新数据
     * 
     * @return
     */
    public String update() {
        try {
            userAuthGroupLogic.merge(model);
            return dealJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 删除数据
     * 
     * @return
     */
    public String delete() {
        try {
            StringBuilder sb = new StringBuilder();
            if (ids != null) {
                for (UserAuthGroupPK pk : ids) {
                    if (sb.length() > 0) {
                        sb.append(";");
                    }
                    sb.append("[usercode=").append(pk.getUserCode()).append(",agrcode=").append(pk.getAgrCode());
                    sb.append("]");
                }
            }
            userAuthGroupLogic.removePKs(ids);
            //日志
        } catch (Exception e) {
            return ERROR;
        }
        return NONE;
    }

    public List<UserAuthGroup> getUserAuthGroups() {
        return userAuthGroups;
    }

    public void setUserAuthGroups(List<UserAuthGroup> userAuthGroups) {
        this.userAuthGroups = userAuthGroups;
    }

    public List<UserAuthGroupPK> getIds() {
        return ids;
    }

    public void setIds(List<UserAuthGroupPK> ids) {
        this.ids = ids;
    }

    @Logging(action = "添加用户资源组关系", message = "添加用户资源组关系:{$.userAuthGroup}", type = LogType.OPERATION)
    protected void addUserAuthGroup(UserAuthGroup userAuthGroup) {
        userAuthGroup.setClientCode(WebUtils.getClientCode());
        userAuthGroup = userAuthGroupLogic.persist(userAuthGroup);
    }

}
