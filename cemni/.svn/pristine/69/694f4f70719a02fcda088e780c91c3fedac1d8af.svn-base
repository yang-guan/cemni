package com.huiju.afterservice.callregist;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.afterservice.callregist.entity.CallRegist;
import com.huiju.afterservice.callregist.logic.CallRegistRemote;
import com.huiju.archive.franchisee.logic.FranchiseeRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.area.entity.Area;
import com.huiju.common.area.logic.AreaRemote;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

/**
 * 客户拜访登记Action
 * 
 * @author：WangYuanJun
 * @date：2016年12月7日 下午1:47:45
 */
public class CallRegistAction extends BaseAction<CallRegist, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "CallRegistBean")
    private CallRegistRemote callregistLogic;

    @EJB(mappedName = "IndividCustBean")
    private IndividCustRemote individCustLogic;

    @EJB(mappedName = "SqlBean")
    private SqlRemote sqlLogic;

    @EJB(mappedName = "AreaBean")
    private AreaRemote areaLogic;

    @EJB(mappedName = "StoreBean")
    private StoreRemote storelogic;

    @EJB(mappedName = "FranchiseeBean")
    private FranchiseeRemote franchiseelogic;

    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        jsPath.add("/js/afterservice/callregist/Q.afterservice.callregist.js");

        String[] authorities = { "D_CALLREGIST_LIST", "D_CALLREGIST_ADD", "D_CALLREGIST_EDIT", "D_CALLREGIST_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        params.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), CallRegist.class));
        Object IN_storeNo = params.get("IN_storeNo");
        Object IN_orgCode = params.get("IN_orgCode");
        // 如果都不为空则合并成OR的关系
        if (IN_storeNo != null && IN_orgCode != null) {
            String[] storeOrgCodeArr = { IN_storeNo.toString(), IN_orgCode.toString() };
            params.put("IN_storeNo_OR_IN_orgCode", storeOrgCodeArr);
            params.remove("IN_storeNo");
            params.remove("IN_orgCode");
        }
        params.put("JOIN", "store_LEFT,org_LEFT");
        Page<CallRegist> page = new Page<CallRegist>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = callregistLogic.findAll(page, params);
        List<CallRegist> rsList = page.getResult();

        for (CallRegist dt : rsList) {
            dt.setVisitFormName(DataDict.getDictName(DataDict.CALLREGIST_VISITFORM, dt.getVisitForm()));
            dt.setOrgStoreName(dt.getOrg() == null ? dt.getStore().getName() : dt.getOrg().getName());
        }
        renderJson(page);
    }

    public void save() {
        if (model.getIndividCust().getIndividCustId() == null) {
            model.setIndividCust(null);
        }
        if (model.getStore().getStoreId() == null) {
            model.setStore(null);
        }
        if (model.getOrg().getOrgId() == null) {
            model.setOrg(null);
        }
        model.setVisitNo(sqlLogic.getCnNum(GlobalConst.NUM_CALLREGIST));
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model = callregistLogic.persist(model);
        dealJson(true);
    }

    public void edit() {
        model = callregistLogic.findById(id);
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        dealJson(true, model);
    }

    public void update() {
        if (model.getIndividCust().getIndividCustId() == null) {
            model.setIndividCust(null);
        }
        if (model.getStore().getStoreId() == null) {
            model.setStore(null);
        }
        if (model.getOrg().getOrgId() == null) {
            model.setOrg(null);
        }
        CallRegist callRegist = callregistLogic.findById(model.getCallRegistId());
        model.setCreateDate(callRegist.getCreateDate());
        model.setCreateUser(callRegist.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        callregistLogic.merge(model);
        dealJson(true);
    }

    /**
     * 个人客户信息
     * 
     * @author：WangYuanJun
     * @date：2016年12月8日 下午5:30:29
     */
    public void getIndividCustInfo() {
        IndividCust cust = individCustLogic.findById(model.getIndividCust().getIndividCustId());

        // 省市县+地址
        String orgAddName = null;
        String[] sort = { "areacode,asc" };
        List<Area> rsList = areaLogic.findAll(sort);
        for (Area dt : rsList) {
            if (cust.getProvince() != null && cust.getProvince().intValue() == dt.getAreacode()) {
                orgAddName = dt.getName();
            }
            if (cust.getCity() != null && cust.getCity().intValue() == dt.getAreacode()) {
                orgAddName += dt.getName();
            }
            if (cust.getCounty() != null && cust.getCounty().intValue() == dt.getAreacode()) {
                orgAddName += dt.getName();
            }
        }
        if (StringUtils.isNotBlank(cust.getAddress())) {
            orgAddName += cust.getAddress();
        }
        cust.setFullAddress(orgAddName);

        cust.setJobName(DataDict.getDictName(DataDict.CUST_JOB, cust.getJob()));
        cust.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, cust.getEnable()));
        cust.setPurposePriceName(DataDict.getDictName(DataDict.CUST_PURPOSE_PRICE, cust.getPurposePrice()));
        cust.setPurposeProductName(DataDict.getDictName(DataDict.CUST_PURPOSE_PRODUCT, cust.getPurposeProduct()));
        cust.setPurposeCategoryName(DataDict.getDictName(DataDict.CUST_PURPOSE_CATEGORY, cust.getPurposeCategory()));
        dealJson(true, cust);
    }

    /**
     * 客户拜访
     * 
     * @author：WangYuanJun
     * @date：2016年12月8日 下午5:28:19
     */
    public void qryCallRegist() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        String[] sorts = { "callRegistId,desc" };
        List<CallRegist> rsList = callregistLogic.findAll(params, sorts);

        for (CallRegist dt : rsList) {
            dt.setVisitFormName(DataDict.getDictName(DataDict.CALLREGIST_VISITFORM, dt.getVisitForm()));
        }
        renderJson(rsList);
    }

}