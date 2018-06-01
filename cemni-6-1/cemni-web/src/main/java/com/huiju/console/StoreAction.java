package com.huiju.console;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.Sort;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

/**
 * 门店
 * 
 * @author：yuhb
 * @date：2016年11月25日 上午12:42:21
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StoreAction extends BaseAction<Store, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private StoreRemote appLogic;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        jsPath.add("/js/console/store/Q.store.js");

        String[] authorities = { "D_STORE_LIST", "D_ADDSTORE2FRANCH", "D_DELSTORE2FRANCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Page<Store> page = new Page(start, limit);
        // 多字段排序
        String[] sortArr = sort.split(",");
        String[] dirArr = (dir == null ? new String[sortArr.length] : dir.split(","));
        for (int i = 0; i < sortArr.length; ++i) {
            // 默认降序
            if (dirArr[i] == null || Sort.Direction.fromString(dirArr[i]).equals(Sort.Direction.DESC)) {
                page.desc(NeuUtils.chgQrySort(sortArr[i]));
            } else {
                page.asc(NeuUtils.chgQrySort(sortArr[i]));
            }
        }
        Map searchParam = WebUtils.getParametersStartingWith(request);
        page = appLogic.findAll(page, searchParam);
        for (Store dt : page) {
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
            dt.setFormName(DataDict.getDictName(DataDict.STORE_FORM, dt.getForm()));
        }
        renderJson(page);
    }

    // 指标-载入门店
    public void qryBigAreaStore() {
        List rsList = appLogic.qryBigAreaStore();
        String[] excludes = { "org", "franchiseeArea", "franchisee" };
        renderJson(rsList, excludes);
    }

    public void edit() {
        model = appLogic.findById(Long.parseLong(id));
        model.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, model.getAttr()));
        model.setFormName(DataDict.getDictName(DataDict.STORE_FORM, model.getForm()));

        String[] excludes = { "org.store", "franchisee.franchiseeProfit", "franchisee.franchiseeAudit", "franchisee.franchiseeValue" };
        dealJson(true, DataUtils.toJson(model, excludes));
    }

}