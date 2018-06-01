package com.huiju.inter;

import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * POS单
 * 
 * @author：yuhb
 * @date：2016年12月2日 上午11:38:42
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PosOrderAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private PosOrderRemote appLogic;

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Page<PosOrder> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = appLogic.findAll(page, searchParam);
        for (PosOrder dt : page) {
            dt.setStockTypeName(DataDict.getDictName(DataDict.STOCK_TYPE, dt.getStockType()));
            dt.setIntegralSrcName(DataDict.getDictName(DataDict.SYS_SCR, dt.getIntegralSrc()));
            dt.setScoreSegmentName(DataDict.getDictName(DataDict.SCORE_SEGMENT, dt.getScoreSegment()));
        }
        renderJson(page);
    }

}