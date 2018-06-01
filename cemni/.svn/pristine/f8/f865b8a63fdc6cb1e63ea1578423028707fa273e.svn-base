package com.huiju.archive.partner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.actment.activity.entity.ParPartIn;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.actment.activity.logic.ParPartInRemote;
import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.archive.franchisee.logic.ContactRemote;
import com.huiju.archive.partner.entity.Partner;
import com.huiju.archive.partner.logic.PartnerRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

// 异业合作伙伴
public class PartnerAction extends BaseAction<Partner, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "PartnerBean")
    private PartnerRemote partnerLogic;

    @EJB(mappedName = "ContactBean")
    private ContactRemote contactLogic;

    @EJB(mappedName = "SqlBean")
    private SqlRemote sqlLogic;

    @EJB(mappedName = "ParPartInBean")
    private ParPartInRemote parPartInLogic;

    @EJB(mappedName = "ActivityBean")
    private ActivityRemote activityLogic;

    public String init() throws Exception {
        jsPath.add("/js/archive/partner/Q.archive.partner.js");

        String[] authorities = { "D_PARTNER_LIST", "D_PARTNER_ADD",  "D_PARTNER_EDIT", "D_PARTNER_UPDATE", "D_PARTNER_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public String getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Partner> page = new Page<Partner>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = partnerLogic.findAll(page, params);
        List<Partner> parList = page.getResult();

        for (Partner pt : parList) {
            pt.setTypeName(DataDict.getDictName(DataDict.PARTNER_TYPE, pt.getType()));
        }
        return renderJson(page);
    }

    public String save() {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_partnerno", model.getPartnerno());
        Partner pt = partnerLogic.find(searchParams);
        if (pt != null) {
            return dealJson(false, "编码已重复,请检查编码");
        }
        this.setOneToManyValue();
        String partnerno = sqlLogic.getCnNum(GlobalConst.NUM_PARTNER);
        model.setPartnerno(partnerno);
        model.setCdate(Calendar.getInstance());
        model = partnerLogic.persist(model);
        return dealJson(true, model);

    }

    public void edit() {
        model = partnerLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        Partner pt = partnerLogic.findById(model.getPartnerid());
        model.setCdate(pt.getCdate());
        model.setCuser(pt.getCuser());
        model.setMuser(WebUtils.getUserCode());
        model.setMdate(Calendar.getInstance());
        partnerLogic.merge(model);
        dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            partnerLogic.removeById(id);
        }
        dealJson(true);
    }

    // 联系人信息
    public void getRel() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<Contact> rsList = contactLogic.findAll(params);
        renderJson(rsList);
    }

    // 异业联盟活动
    public void getRel3() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<ParPartIn> rsList = parPartInLogic.findAll(params);
        for (ParPartIn pp : rsList) {
            pp.getActivity().setActivityTypeName(DataDict.getDictName(DataDict.ACTIVITY_TYPE, pp.getActivity().getActivityType()));
        }
        renderJson(rsList);
    }

    private void setOneToManyValue() {
        if (null != this.model.getContact()) {
            for (Contact ct : this.model.getContact()) {
                ct.setPartner(model);
            }
        }
    }

}