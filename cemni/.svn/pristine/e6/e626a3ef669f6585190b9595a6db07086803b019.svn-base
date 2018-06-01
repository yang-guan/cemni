package com.huiju.expandbusi.franchiseeaudit;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.common.DataDict;
import com.huiju.expandbusi.franchiseeaudit.entity.FranchiseeAudit;
import com.huiju.expandbusi.franchiseeaudit.logic.FranchiseeAuditRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 加盟商稽核管理
 * 
 * @author：WangYuanJun
 * @date：2016年12月12日 下午2:47:13
 */
public class FranchiseeAuditAction extends BaseAction<FranchiseeAudit, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "FranchiseeAuditBean")
    private FranchiseeAuditRemote franchiseeAuditLogic;

    public String init() throws Exception {
        jsPath.add("/js/expandbusi/franchiseeaudit/Q.expandbusi.franchiseeaudit.js");

        String[] authorities = { "D_FRANCHISEEAUDIT_LIST", "D_FRANCHISEEAUDIT_ADD", "D_FRANCHISEEAUDIT_EDIT", "D_FRANCHISEEAUDIT_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Object LE_cdate = params.get("LE_createDate");
        if (LE_cdate != null && !StringUtils.isEmpty(LE_cdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_cdate.toString());
            cl.add(Calendar.DATE, 1);
            params.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));

        }
        Page<FranchiseeAudit> page = new Page<FranchiseeAudit>(start, limit, sort, dir);
        page = franchiseeAuditLogic.findAll(page, params);
        List<FranchiseeAudit> franchiseeAudits = page.getResult();

        for (FranchiseeAudit dt : franchiseeAudits) {
            dt.setAuditProbNumName(DataDict.getDictName(DataDict.FRANCHISEEAUDIT_AUDITPROBNUM, dt.getAuditProbNum()));
            if (dt.getFranchisee() != null) {
                dt.getFranchisee().setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFranchisee().getFraType()));
                dt.getFranchisee().setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, dt.getFranchisee().getSources()));
            }
        }
        renderJson(page);
    }

    public void save() {
        if (model.getStore().getStoreId() == null) {
            model.setStore(null);
        }
        if (model.getFranchisee().getFranchiseeId() == null) {
            model.setFranchisee(null);
        }
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model = franchiseeAuditLogic.persist(model);
        dealJson(true, model);
    }

    public void edit() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_franchiseeAuditId", id);
        model = franchiseeAuditLogic.find(searchParams);

        Franchisee dt = model.getFranchisee();
        if (dt != null) {
            dt.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFraType()));
            dt.setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, dt.getSources()));
        }
        dealJson(true, model);
    }

    public void update() {
        if (model.getStore().getStoreId() == null) {
            model.setStore(null);
        }
        if (model.getFranchisee().getFranchiseeId() == null) {
            model.setFranchisee(null);
        }
        FranchiseeAudit franchiseeAudit = franchiseeAuditLogic.findById(model.getFranchiseeAuditId());
        model.setCreateDate(franchiseeAudit.getCreateDate());
        model.setCreateUser(franchiseeAudit.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        franchiseeAuditLogic.merge(model);
        dealJson(true);
    }

}