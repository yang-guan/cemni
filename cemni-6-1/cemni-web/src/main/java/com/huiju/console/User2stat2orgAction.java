package com.huiju.console;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.console.user2org.entity.User2org;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 用户岗位组织机构
 * 
 * @author：yuhb
 * @date：2016年12月05日 下午10:22:16
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class User2stat2orgAction extends BaseAction<User2org, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private User2orgRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/console/user2stat2org/Q.user2stat2org.js");

        String[] authorities = { "D_USER2STAT2ORG_LIST", "D_ADDUSER2ORG" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    // 用户2岗位2组织-list
    public void queryUser() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsMap = appLogic.queryUser(searchParam);
        renderJson(rsMap);
    }

    // 用户2组织机构
    public void updUser2org() {
        appLogic.updUser2org(model.getUserId(), ids);
        dealJson(true);
    }

    // 用户2组织机构-移除-列表
    public void qryOrgByUserId() {
        List rsList = appLogic.qryOrgByUserId(Long.parseLong(request.getParameter("userId")));
        renderJson(rsList);
    }

}