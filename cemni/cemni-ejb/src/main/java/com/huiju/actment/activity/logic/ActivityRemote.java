package com.huiju.actment.activity.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.actment.activity.entity.Activity;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ActivityRemote extends GenericLogic<Activity, Long> {

    public String getSeqActCouponNo();

    /**
     * @author lp 保存活动信息
     */
    public void saveActivityByExcel(Activity model, List<Map<String, Object>> paramsList);

    /**
     * @author lp 保存活动信息
     */
    public void saveActivityByQuery(Activity model, Map<String, Object> searchParams);

    void saveIndiPartInByQuery(Activity activity, Map<String, Object> searchParams);

    void saveIndiPartInExcel(Activity activity, List<Map<String, Object>> paramsList);

    /**
     * 更新活动信息(excel)
     */
    public void updateActivityByExcel(Activity model, List<Map<String, Object>> paramsList);

    /**
     * 更新活动信息（query）
     */
    public void updateActivityByQuery(Activity model, Map<String, Object> paramsMap);
}