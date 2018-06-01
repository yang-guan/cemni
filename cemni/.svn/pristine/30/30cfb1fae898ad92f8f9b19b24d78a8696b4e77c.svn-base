package com.huiju.inter;

import java.util.Map;

import javax.ejb.EJB;

import com.huiju.inter.saleorder.logic.SaleOrderRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 销售订单
 * 
 * @author：yuhb
 * @date：2016年12月2日 上午11:38:54
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SaleOrderAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "SaleOrderBean")
    private SaleOrderRemote saleOrderLogic;

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Page page = new Page(start, limit, sort, dir);
        page = saleOrderLogic.findAll(page, searchParam);
        renderJson(page);
    }
}
