package com.huiju.archive.individcust.logic;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.Page;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface IndividCustRemote extends GenericLogic<IndividCust, Long> {

    Page qryIndividCust(Page<IndividCust> page, Map<String, Object> searchParam);

    /**
     * excel导入
     * 
     * @author：yuhb
     * @date：2017年2月21日 上午2:41:40
     */
    Map<String, Object> excel(File file, String userName);

    /**
     * crm发起同步个人档案信息到外系统
     */
    String synIndividCustToEx(IndividCust dt);

    /**
     * 同步：记录日志
     */
    Map synIndividCust(int from, int to, IndividCust newDt);

    /**
     * 同步：不记录日志
     */
    Map synIndividCust(int from, int to, IndividCust oldDt, IndividCust newDt);

    /**
     * 同步
     * 
     * @param from
     *            数据来源：1NC、2CRM、3微信、4耀我网
     * @param to
     *            推送到：1NC、2CRM、3微信、4耀我网
     * @param flag
     *            记录日志：0新建、1修改、-1不记录
     * @param userName
     *            创建人
     * @param oldDt
     * @param newDt
     * 
     * @author：yuhb
     * @date：2017年3月22日 下午11:51:35
     */
    Map synIndividCust(int from, int to, int flag, String userName, IndividCust oldDt, IndividCust newDt);

    // ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 参与活动：根据会员id查询，并更新是否参与字段
     * 
     * @author：yuhb
     * @date：2017年3月1日 下午2:43:35
     */
    List qryActByIndividCustId(Long individCustId);

    void updateBelongStore(String isAllSelected, List<Long> individCustIds, Map searchParam, String belongStoreNo, String belongStoreName, String startPage, String endPage);

    void addCustOperationLog(String userName, IndividCust cust, IndividCust dt);

    /**
     * 查询会员信息 排除blob字段（特定情况下使用：回访任务单）
     */
    Page<IndividCust> findAllExcludeBolb(Page<IndividCust> custPage, Map<String, Object> paramsMap);

    /**
     * 查询会员信息
     */
    Page<IndividCust> findCustPage(Page<IndividCust> custPage, List<Map<String, Object>> paramsList);

    /**
     * 价值贡献
     * 
     * @author：yuhb
     * @date：2017年4月22日 下午7:56:18
     */
    List getValContrib(Map searchParam);

    /**
     * 推荐明细
     */
    List qryRefInfo(Long individCustId);

    /**
     * 根据生日计算年龄段
     * 
     * @author：yuhb
     * @date：2017年6月1日 下午1:36:52
     */
    int getIndividCustAge(Calendar birthday);
}