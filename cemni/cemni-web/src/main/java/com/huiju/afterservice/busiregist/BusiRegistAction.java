package com.huiju.afterservice.busiregist;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.afterservice.busiregist.logic.BusiRegistRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.core.sys.entity.Module;
import com.huiju.core.sys.logic.ModuleRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;

/**
 * 客户业务登记Action
 * 
 * @author：WangYuanJun
 * @date：2016年12月7日 下午4:36:34
 */
@SuppressWarnings({ "rawtypes" })
public class BusiRegistAction extends BaseAction<BusiRegist, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private BusiRegistRemote busiRegistLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private ModuleRemote moduleLogic;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;

    public String init() throws Exception {
        jsPath.add("/js/afterservice/busiregist/Q.afterservice.busiregist.js");

        String[] authorities = { "D_BUSIREGIST_LIST", "D_BUSIREGIST_ADD", "D_BUSIREGIST_EDIT", "D_BUSIREGIST_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        params.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), BusiRegist.class));
        Object IN_storeNo = params.get("IN_storeNo");
        Object IN_orgCode = params.get("IN_orgCode");
        // 如果都不为空则合并成OR的关系
        if (IN_storeNo != null && IN_orgCode != null) {
            String[] storeOrgCodeArr = { IN_storeNo.toString(), IN_orgCode.toString() };
            params.put("IN_storeNo_OR_IN_orgCode", storeOrgCodeArr);
            params.remove("IN_storeNo");
            params.remove("IN_orgCode");
        }
        params.put("start", start);
        params.put("limit", limit);
        Map map = busiRegistLogic.queryPageList(params);
        renderJson(map);
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
        if (model.getAcceptStore().getStoreId() == null) {
            model.setAcceptStore(null);
        }
        if (model.getAcceptOrg().getOrgId() == null) {
            model.setAcceptOrg(null);
        }
        model.setRegisterNo(sqlLogic.getCnNum(GlobalConst.NUM_BUSIREGIST));
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model = busiRegistLogic.persist(model);
        dealJson(true, model);
    }

    public void edit() {
        model = busiRegistLogic.findById(id);

        IndividCust dt = model.getIndividCust();
        if (dt != null) {
            model.setCustname(dt.getName());
            model.setCustMobile(dt.getMobile());
        }
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
        if (model.getAcceptStore().getStoreId() == null) {
            model.setAcceptStore(null);
        }
        if (model.getAcceptOrg().getOrgId() == null) {
            model.setAcceptOrg(null);
        }
        BusiRegist busiRegist = busiRegistLogic.findById(model.getBusiRegistId());
        model.setCreateDate(busiRegist.getCreateDate());
        model.setCreateUser(busiRegist.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());
        busiRegistLogic.merge(model);
        dealJson(true);
    }

    /**
     * 链接到个人客户
     * 
     * @author：WangYuanJun
     * @date：2017年1月25日 下午1:04:15
     */
    public String linkIndividCust() {
        Map<String, Object> arg0 = new HashMap<String, Object>();
        String contextPath = request.getContextPath().substring(1);
        arg0.put("EQ_link", contextPath + "/IndividCust_init.action");
        Module module = moduleLogic.find(arg0);
        return dealJson(true, module);
    }

    /**
     * 客服部和门店的列表
     * 
     * @author：WangYuanJun
     * @date：2017年3月15日 下午3:27:46
     */
    public void getOrgStoreList() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map map = busiRegistLogic.queryOrgStoreList(searchParam);
        renderJson(map);
    }

}