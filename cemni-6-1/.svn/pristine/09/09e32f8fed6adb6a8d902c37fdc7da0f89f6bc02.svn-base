package com.huiju.integral.integraladj.logic;

import javax.ejb.Remote;

import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface IntegralAdjHisRemote extends GenericLogic<IntegralAdjHis, Long> {

    /**
     * 获取需要更新到的“会员活跃状态”
     * 
     * @param custType
     * @param custId
     * @return 会员活跃状态
     * 
     * @author：yuhb
     * @date：2017年2月5日 下午4:39:50
     */
    int getCustActive(int custType, Long custId);
}