package com.huiju.archive.supplier;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.archive.franchisee.logic.ContactRemote;
import com.huiju.archive.supplier.entity.Supplier;
import com.huiju.archive.supplier.logic.SupplierRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

public class SupplierAction extends BaseAction<Supplier, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private SupplierRemote supplierLogic;
    @EJB
    private ContactRemote contactLogic;

    public String init() throws Exception {
        jsPath.add("/js/archive/supplier/Q.archive.supplier.js");

        String[] authorities = { "D_SUPPLIER_LIST", "D_SUPPLIER_EDIT", "D_SUPPLIER_UPDATE", "D_SUPPLIER_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<Supplier> page = new Page<Supplier>(start, limit, sort, dir);
        page = supplierLogic.findAll(page, params);
        renderJson(page);
    }

    public void edit() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        searchParams.put("EQ_supplierid", id);
        model = supplierLogic.find(searchParams);
        dealJson(true, model);
    }

    public void update() {
        Supplier dt = supplierLogic.findById(model.getSupplierid());
        model.setCdate(dt.getCdate());
        model.setCuser(dt.getCuser());
        model.setIsValid(dt.getIsValid());
        model.setMuser(WebUtils.getUserCode());
        model.setMdate(Calendar.getInstance());
        supplierLogic.merge(model);
        dealJson(true);
    }

    // 联系人信息
    public void getRel() {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request);
        List rsList = contactLogic.findAll(searchParams);
        renderJson(rsList);
    }

}