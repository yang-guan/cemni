package com.huiju.inter.posorder.logic;

import java.util.List;

import javax.ejb.Remote;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface PosOrderRemote extends GenericLogic<PosOrder, Long> {

    List loadPos(Long activityId);

    /**
     * 校验用户是否有pos消费
     * 
     * @param individCustId
     * @return
     * 
     * @author：yuhb
     * @date：2017年2月19日 下午4:44:17
     */
    boolean isPosByIndividCustId(Long individCustId);
}