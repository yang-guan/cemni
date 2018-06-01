package com.huiju.afterservice.telvisit.eao;

import javax.ejb.Local;

import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface TelVisitEaoLocal extends GenericEao<TelVisit, Long> {

    long getTelvisitSeq();

    /**
     * 保存回访任务单
     */
    void saveTelVisit(Long seq, TelVisit model);

    /**
     * 更新回访任务单
     */
    void updateTelVisit(TelVisit model);
}