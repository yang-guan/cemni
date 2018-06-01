package com.huiju.archive.franchisee;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.actment.activity.logic.FraPartInRemote;
import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.archive.franchisee.entity.Credit;
import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.archive.franchisee.entity.Team;
import com.huiju.archive.franchisee.logic.ContactRemote;
import com.huiju.archive.franchisee.logic.CreditRemote;
import com.huiju.archive.franchisee.logic.FranchiseeRemote;
import com.huiju.archive.franchisee.logic.TeamRemote;
import com.huiju.common.DataDict;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "rawtypes" })
public class FranchiseeAction extends BaseAction<Franchisee, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "FranchiseeBean")
    private FranchiseeRemote franchiseeLogic;

    @EJB(mappedName = "ContactBean")
    private ContactRemote contactLogic;

    @EJB(mappedName = "CreditBean")
    private CreditRemote creditLogic;

    @EJB(mappedName = "TeamBean")
    private TeamRemote teamLogic;

    @EJB(mappedName = "StoreBean")
    private StoreRemote storeLogic;

    @EJB(mappedName = "FraPartInBean")
    private FraPartInRemote fraPartInLogic;

    // 加盟商列表
    public String init() throws Exception {
        jsPath.add("/js/archive/franchisee/Q.archive.franchisee.js");

        String[] authorities = { "D_FRANCHISEE_LIST", "D_FRANCHISEE_SEARCH", "D_FRANCHISEE_EDIT" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Franchisee> page = new Page<Franchisee>(start, limit, NeuUtils.chgQrySort(sort, "fraName,shortName"), dir);
        page = franchiseeLogic.findAll(page, params);

        List<Franchisee> franchiseeList = page.getResult();
        for (Franchisee fra : franchiseeList) {
            fra.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, fra.getFraType()));
            fra.setFraStatusName(DataDict.getDictName(DataDict.FRANCHISEE_STATE, fra.getFraStatus()));
            fra.setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, fra.getSources()));
        }
        renderJson(page);
    }

    public void edit() {
        model = franchiseeLogic.findById(id);
        dealJson(true, model);
    }

    // 编辑
    public void update() {
        Franchisee fc = franchiseeLogic.findById(model.getFranchiseeId());
        model.setCreateDate(fc.getCreateDate());
        model.setCreateUser(fc.getCreateUser());
        model.setIsValid(fc.getIsValid());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());

        this.setOneToManyValue();
        franchiseeLogic.merge(model);
        dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            franchiseeLogic.removeById(id);
        }
        dealJson(true);
    }

    private void setOneToManyValue() {
        if (null != this.model.getContact()) {
            for (Contact dt : this.model.getContact()) {
                dt.setFranchisee(model);
            }
        }
        if (null != this.model.getCredit()) {
            for (Credit dt : this.model.getCredit()) {
                dt.setFranchisee(model);
            }
        }
        if (null != this.model.getTeam()) {
            for (Team dt : this.model.getTeam()) {
                dt.setFranchisee(model);
            }
        }
    }

    // 联系人页签
    public void getRel() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List rsList = contactLogic.findAll(params);
        renderJson(rsList);
    }

    // 信用档案页签
    public void getRel2() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List creditList = creditLogic.findAll(params);
        renderJson(creditList, "franchisee");
    }

    // 团队档案页签
    public void getRel3() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List rsList = teamLogic.findAll(params);
        renderJson(rsList);

    }

    // 所辖门店页签
    public void getRel4() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<Store> rsList = storeLogic.findAll(params);
        for (Store st : rsList) {
            st.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, st.getAttr()));
            st.setFormName(DataDict.getDictName(DataDict.STORE_FORM, st.getForm()));
        }
        renderJson(rsList, "franchisee");
    }

    // 互动档案
    public void getRel5() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<FraPartIn> rsList = fraPartInLogic.findAll(params);

        for (FraPartIn fp : rsList) {
            fp.getActivity().setActivityTypeName(DataDict.getDictName(DataDict.ACTIVITY_TYPE, fp.getActivity().getActivityType()));
        }
        renderJson(rsList, "franchisee, franchiseeProfit");
    }

}