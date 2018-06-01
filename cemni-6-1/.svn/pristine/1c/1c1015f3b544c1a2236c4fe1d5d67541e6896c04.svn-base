package com.huiju.console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.orgext.entity.OrgExt;
import com.huiju.console.orgext.logic.OrgExtRemote;
import com.huiju.module.data.TreeNode;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 对外组织架构
 * 
 * @author：yuhb
 * @date：2017年7月24日 下午5:04:48
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrgExtAction extends BaseAction<OrgExt, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private OrgExtRemote appLogic;
    @EJB
    private SqlRemote sqlLogic;

    public String init() throws Exception {
        String[] authorities = { "D_ORGEXT_LIST", "D_ORGEXT_ADD", "D_ORGEXT_EDIT", "D_ADDSTORE2ORG", "D_DELSTORE2ORG" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    // 组织机构-首页
    public void getOrgJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        if (StringUtils.isEmpty(sort)) {
            sort = "orderNo";
        }
        if (StringUtils.isEmpty(dir)) {
            dir = "asc";
        }
        String[] sorts = { sort + "," + dir };

        List<OrgExt> rsList = appLogic.findAll(searchParam, sorts);
        renderJson(rsList, "store");
    }

    public void getOrgTree() {
        String key = request.getParameter("key");
        String checkbox = request.getParameter("checkbox");

        Map searchParam = new HashMap();
        searchParam.put("EQ_parentId", key);
        String[] sort = { "orderNo,asc" };
        List<OrgExt> rsList = appLogic.findAll(searchParam, sort);

        List rsTreeList = new ArrayList();
        TreeNode node;
        for (OrgExt org : rsList) {
            if (StringUtils.isEmpty(org.getName())) {
                continue;
            }
            node = new TreeNode();
            node.setId(org.getId().toString());
            node.setText(org.getOrgCode() + org.getName());

            // 是否是叶子节点
            if (org.getType() == GlobalConst.ORGEXT_STORE) {
                node.setLeaf(true);
            } else {
                Map searchChildParam = new HashMap();
                searchChildParam.put("EQ_parentId", org.getId().toString());
                List rsChildList = appLogic.findAll(searchChildParam);
                node.setLeaf(CollectionUtils.isEmpty(rsChildList) ? true : false);
            }

            Map attr = new HashMap();
            attr.put("type", org.getType());
            node.setAttr(attr);

            // 带有复选框的树
            if (checkbox != null) {
                node.setChecked(false);
            }
            rsTreeList.add(node);
        }
        renderJson(rsTreeList);
    }

    public void save() {
        model.setCdate(Calendar.getInstance());
        appLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = appLogic.findById(id);
        dealJson(true, DataUtils.toJson(model, "store"));
    }

    public void update() {
        model.setMdate(Calendar.getInstance());
        appLogic.merge(model);
        dealJson(true);
    }

    // 门店2组织架构-关联
    public void org2store() {
        appLogic.org2store(model.getParentId(), ids);
        dealJson(true);
    }

    // 门店2组织架构-移除
    public void org2storeRemove() {
        appLogic.remove(ids);
        dealJson(true);
    }

}