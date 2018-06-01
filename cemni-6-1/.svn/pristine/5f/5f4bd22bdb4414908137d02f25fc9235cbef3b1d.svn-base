package com.huiju.afterservice.telvisit.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface TelVisitCustEaoLocal extends GenericEao<TelVisitCust, Long> {

    /**
     * 保存回访任务访单-会员信息
     */
    void saveTelVisitCust(Long seq, Map<String, Object> searchParams);

    /**
     * 更新回访任务访单-会员信息（先删后加）
     */
    void updateTelVisitCust(Long id, Map<String, Object> searchParams);

    /**
     * 保存回访任务访单-会员信息（先删后加）
     */
    void saveTelVisitCust3(Long id, List<TelVisitCust> custList);

    /**
     * 保存回访任务单-会员信息
     */
    void saveIndividCustByExcel(Long id, List<Map<String, Object>> paramsList);

    /**
     * 更新回访任务单-会员信息
     */
    void updateIndividCustByExcel(Long id, List<Map<String, Object>> paramsList);
}