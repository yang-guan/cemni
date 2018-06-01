package com.huiju.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.org.entity.Org;
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.module.data.TreeNode;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 组织架构
 * 
 * @author：yuhb
 * @date：2016年12月5日 上午10:11:04
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrgAction extends BaseAction<Org, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private OrgRemote appLogic;
    @EJB
    private SqlRemote sqlLogic;

    public String init() throws Exception {
        String[] authorities = { "D_ORG_LIST", "D_ORG_ADD", "D_ORG_EDIT", "D_ADDSTORE2ORG", "D_DELSTORE2ORG" };
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

        List<Org> rsList = appLogic.findAll(searchParam, sorts);
        for (Org org : rsList) {
            org.setTypeName(DataDict.getDictName(DataDict.ORG_TYPE, org.getType()));
        }
        renderJson(rsList);
    }

    public void getOrgTree() {
        String key = request.getParameter("key");
        String checkbox = request.getParameter("checkbox");

        Map searchParam = new HashMap();
        searchParam.put("EQ_parentId", key);
        String[] sort = { "orderNo,asc" };
        List<Org> rsList = appLogic.findAll(searchParam, sort);

        List rsTreeList = new ArrayList();
        TreeNode node;
        for (Org org : rsList) {
            if (StringUtils.isEmpty(org.getName())) {
                continue;
            }
            node = new TreeNode();
            node.setId(org.getId().toString());
            node.setText(org.getName());

            // 是否是叶子节点
            if (org.getType() == GlobalConst.ORG_TYPE_5) {
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
        appLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = appLogic.findById(id);
        dealJson(true, DataUtils.toJson(model));
    }

    public void update() {
        appLogic.merge(model);
        dealJson(true);
    }

    // 查询组织机构下所有的子节点
    public void getOrgTreeList() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Object LIKE_name = searchParam.get("LIKE_name");
        renderJson(appLogic.getOrgTreeList(LIKE_name == null ? null : LIKE_name.toString()));
    }

    //竞争对手--调查部门
    public void qryDeptStore() {
        Object[] orgCodeArr = { GlobalConst.DEP_ORGCODE_KF, GlobalConst.DEP_ORGCODE_PP, GlobalConst.DEP_ORGCODE_SP, GlobalConst.DEP_ORGCODE_LZ1, GlobalConst.DEP_ORGCODE_LZ2, GlobalConst.DEP_ORGCODE_RZ };

        Map searchParam = new HashMap();
        searchParam.put("IN_orgCode", orgCodeArr);
        renderJson(appLogic.findAll(searchParam));
    }

}