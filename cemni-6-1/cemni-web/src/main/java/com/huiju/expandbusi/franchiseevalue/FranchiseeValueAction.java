package com.huiju.expandbusi.franchiseevalue;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.common.DataDict;
import com.huiju.expandbusi.franchiseevalue.entity.FranchiseeValue;
import com.huiju.expandbusi.franchiseevalue.entity.StoreCost;
import com.huiju.expandbusi.franchiseevalue.logic.FranchiseeValueRemote;
import com.huiju.expandbusi.franchiseevalue.logic.StoreCostRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 加盟商价值管理
 * 
 * @author：WangYuanJun
 * @date：2016年12月28日 上午10:01:17
 */
public class FranchiseeValueAction extends BaseAction<FranchiseeValue, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "FranchiseeValueBean")
    private FranchiseeValueRemote franchiseeValueLogic;

    @EJB(mappedName = "StoreCostBean")
    private StoreCostRemote storeCostLogic;

    public String init() throws Exception {
        jsPath.add("/js/expandbusi/franchiseevalue/Q.expandbusi.franchiseevalue.js");

        String[] authorities = { "D_FRANCHISEEVALUE_LIST", "D_FRANCHISEEVALUE_ADD", "D_FRANCHISEEVALUE_EDIT", "D_FRANCHISEEVALUE_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public String getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Object LE_cdate = params.get("LE_createDate");
        if (LE_cdate != null && !StringUtils.isEmpty(LE_cdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_cdate.toString());
            cl.add(Calendar.DATE, 1);
            params.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));

        }
        Page<FranchiseeValue> page = new Page<FranchiseeValue>(start, limit, sort, dir);
        page = franchiseeValueLogic.findAll(page, params);
        List<FranchiseeValue> franchiseeValues = page.getResult();
        for (FranchiseeValue dt : franchiseeValues) {
            if (dt.getFranchisee() != null) {
                dt.getFranchisee().setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFranchisee().getFraType()));
                dt.getFranchisee().setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, dt.getFranchisee().getSources()));
            }
        }
        return renderJson(page);
    }

    public void save() {
        this.setOneToManyValue();
        model.setCreateUser(WebUtils.getUserCode());
        model.setCreateDate(Calendar.getInstance());
        model = franchiseeValueLogic.persist(model);
        dealJson(true, model);
    }

    public void edit() {
        model = franchiseeValueLogic.findById(id);

        Franchisee dt = model.getFranchisee();
        if (dt != null) {
            dt.setFraTypeName(DataDict.getDictName(DataDict.FRANCHISEE_TYPE, dt.getFraType()));
            dt.setSourcesName(DataDict.getDictName(DataDict.FRANCHISEE_SOURCE, dt.getSources()));
        }
        dealJson(true, model);
    }

    public String update() {
        FranchiseeValue dt = franchiseeValueLogic.findById(model.getFranchiseeValueId());

        model.setCreateDate(dt.getCreateDate());
        model.setCreateUser(dt.getCreateUser());
        model.setModifyUser(WebUtils.getUserCode());
        model.setModifyDate(Calendar.getInstance());

        if (model.getFranchisee().getFranchiseeId() == null) {
            model.setFranchisee(null);
        }
        franchiseeValueLogic.merge(model);
        return dealJson(true);
    }

    /**
     * 开店成本
     * 
     * 
     * @author：WangYuanJun
     * @date：2016年12月28日 上午10:11:47
     */
    public String getStoreCost() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<StoreCost> list = storeCostLogic.findAll(params);
        return renderJson(list);
    }

    private void setOneToManyValue() {
        if (null != this.model.getStoreCost()) {
            for (StoreCost storeCost : this.model.getStoreCost()) {
                storeCost.setFranchiseeValue(model);
                storeCost.setCreateUser(WebUtils.getUserCode());
                storeCost.setCreateDate(Calendar.getInstance());
            }
        }
    }

}