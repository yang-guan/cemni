package com.huiju.afterservice.telvisit.logic;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings("rawtypes")
public interface TelVisitRemote extends GenericLogic<TelVisit, Long> {

    void delete(Long id);

    /**
     * 任务单发布
     * 
     * @author：yuhb
     * @date：2017年2月18日 下午1:07:27
     */
    void telVisitPublish(Long id, String cuserCode);

    /**
     * 会员信息：导入
     * 
     * @author：yuhb
     * @date：2017年2月18日 下午5:50:24
     */
    Map uploadExcel(File file) throws Exception;

    /**
     * 保存回访任务单
     */
    void saveTelVisit(TelVisit model, Map<String, Object> searchParams);

    /**
     * 保存回访任务单(编辑时使用)
     */
    void updateTelVisit(TelVisit model, Map<String, Object> searchParams);

    /**
     * 更新回访任务单
     */
    void updateTelVisit(TelVisit model, List<TelVisitCust> custList);

    void saveTelVisit(TelVisit model, List<Map<String, Object>> paramsList);

    void updateTelVisitExcel(TelVisit model, List<Map<String, Object>> paramsList);
}