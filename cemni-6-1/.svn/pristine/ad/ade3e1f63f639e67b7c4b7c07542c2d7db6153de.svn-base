package com.huiju.inter.posorder.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.inter.posorder.eao.PosOrderEaoLocal;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

/**
 * pos单
 * 
 * @author：yuhb
 * @date：2017年3月3日 上午9:16:56
 */
@Stateless(mappedName = "PosOrderBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PosOrderBean extends GenericLogicImpl<PosOrder, Long> implements PosOrderRemote {
    @EJB
    private PosOrderEaoLocal posOrderEao;

    @Override
    protected GenericEao<PosOrder, Long> getGenericEao() {
        return posOrderEao;
    }

    @Override
    public List loadPos(Long activityId) {
        return posOrderEao.loadPos(activityId);
    }

    @Override
    public boolean isPosByIndividCustId(Long individCustId) {
        boolean flag = false;

        Map searchParams = new HashMap();
        searchParams.put("EQ_individCust_individCustId", individCustId);
        List<PosOrder> rsList = this.findAll(searchParams);
        for (PosOrder pos : rsList) {
            if (pos.getGoodsNo() != null && !pos.getGoodsNo().substring(0, 2).toUpperCase().equals("W3")) {// 商品编码开头不是以W3开头的是已消费客户
                flag = true;
                break;
            }
        }
        return flag;
    }

}