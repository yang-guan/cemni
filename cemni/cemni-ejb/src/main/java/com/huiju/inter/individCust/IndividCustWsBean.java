package com.huiju.inter.individCust;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.alibaba.fastjson.JSONObject;
import com.huiju.archive.individcust.eao.ActiveStatusEaoLocal;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.archive.individcust.entity.CustStatus;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.entity.OperationLog;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.archive.individcust.logic.OperationLogRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.DESUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * <pre>
 * NC：
 * {
 *     "address": "",
 *     "belongStoreName": "",
 *     "belongStoreNo": "037",
 *     "billtype": "",
 *     "birthday": "1965-06-24",
 *     "cardNo": "",
 *     "city": "淮安市",
 *     "county": "清河区",
 *     "credit": "0",
 *     "custType": "1",
 *     "fresh": "0",
 *     "gender": "",
 *     "id": "037000126",
 *     "lv": "",
 *     "mobile": "15905237573",
 *     "name": "王美华",
 *     "ncNo": "037000126",
 *     "province": "江苏省",
 *     "psnId": "",
 *     "wechatNo": ""
 * }
 * 
 * 
 * 
 * 微信：
{
    "cardNo": null,
    "name": "心石卒了无恨",
    "pwd": "165bc9e2a9b2dad4c040db473da3afcc",
    "mobile": 15051098333,
    "birthday": "1982-10-30",
    "gender": 1,
    "integral": 20,
    "wechat": ".",
    "wechatId": "oOJUJj8nXgD-OUtZJ2RqCr5hk2e0",
    "weddingDay": "2016-03-16"
    "brandChannel": 10,
    "job": 10,
    "motives": 7,
    "purchaseFactors": 4,
    "belongStoreName": "盐城大丰店",
    "belongStoreNo": "019",
    "province": 32,
    "city": 3209,
    "county": 320903,
    "address": "聚亨路",
}
 * </pre>
 * 
 * @author：yuhb
 * @date：2017年3月25日 下午6:25:34
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class IndividCustWsBean implements IndividCustWs {
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private IndividCustEaoLocal individCustEao;
    @EJB
    private IntegralAdjHisRemote inteHisLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private OperationLogRemote operLogLogic;
    @EJB
    private ActiveStatusEaoLocal activeStatusEao;
    @EJB
    private SqlEaoLocal sqlEao;

    @Override
    @WebResult(name = "message")
    @WebMethod(operationName = "nc2crm")
    public String nc2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        IndividCust dt = null;
        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            String ncNo = InterJsonParseUtils.parseString(json, "ncNo", "NC会员编码", errMap, false);
            String cardNo = InterJsonParseUtils.parseString(json, "cardNo", errMap);
            String name = InterJsonParseUtils.parseString(json, "name", "客户名称", errMap, false);
            Long mobile = InterJsonParseUtils.parseLong(json, "mobile", "手机号码", errMap, false);
            String idcard = InterJsonParseUtils.parseString(json, "psnId", errMap);
            Calendar birthday = InterJsonParseUtils.parseCalendar(json, "birthday", "生日", "yyyy-MM-dd", errMap, false);
            Integer gender = InterJsonParseUtils.parseInteger(json, "gender", errMap);
            String wechatNo = InterJsonParseUtils.parseString(json, "wechatno", errMap);
            Integer fresh = InterJsonParseUtils.parseInteger(json, "fresh", "新老会员", errMap, true);
            String belongStoreNo = InterJsonParseUtils.parseString(json, "belongStoreNo", errMap);
            String belongStoreName = InterJsonParseUtils.parseString(json, "belongStoreName", errMap);
            Integer province = InterJsonParseUtils.parseInteger(json, "province", errMap);
            Integer city = InterJsonParseUtils.parseInteger(json, "city", errMap);
            Integer county = InterJsonParseUtils.parseInteger(json, "county", errMap);
            String address = InterJsonParseUtils.parseString(json, "address", errMap);

            if (StringUtils.isNotBlank(cardNo)) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_cardNo", cardNo);
                dt = individCustLogic.find(searchParam);
                if (dt == null) {
                    errMap.put("cardNo", cardNo + "在crm系统不存在");
                } else {
                    Map mobileParam = new HashMap();
                    mobileParam.put("EQ_mobile", mobile);
                    IndividCust mobileDt = individCustLogic.find(mobileParam);
                    if (mobileDt != null && !cardNo.equals(mobileDt.getCardNo())) {
                        errMap.put("mobile", mobile + " 在crm系统已存在");
                    }
                }
            } else {
                Map mobileParam = new HashMap();
                mobileParam.put("EQ_mobile", mobile);
                dt = individCustLogic.find(mobileParam);
                if (dt != null) {
                    errMap.put("mobile", mobile + " 在crm系统已存在");
                }
            }

            if (CollectionUtils.isEmpty(errMap)) {
                if (dt == null) {
                    dt = new IndividCust();
                    dt.setCardNo(sqlEao.getCnNum(GlobalConst.NUM_INDIVID));// 新增时需要crm生成
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setIdcard(idcard);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechat(wechatNo);
                    dt.setSources(GlobalConst.SYS_SRC_NC);
                    dt.setFresh(fresh);

                    // 默认归属“客服部”
                    if (StringUtils.isEmpty(belongStoreNo)) {
                        dt.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
                        dt.setBelongStoreName(GlobalConst.DEP_ORGName_KF);
                        dt.setSrcStoreNo(GlobalConst.DEP_ORGCODE_KF);
                        dt.setSrcStoreName(GlobalConst.DEP_ORGName_KF);
                    } else {
                        dt.setBelongStoreNo(belongStoreNo);
                        dt.setBelongStoreName(belongStoreName);
                        dt.setSrcStoreNo(belongStoreNo);
                        dt.setSrcStoreName(belongStoreName);
                    }

                    // 设置默认值
                    dt.setType(GlobalConst.CUST_TYPE_CUST);
                    dt.setIsSendSms(GlobalConst.NO);
                    dt.setIsImport(GlobalConst.NO);
                    dt.setStatus(CustStatus.NEW);
                    dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                    dt.setEnable(GlobalConst.CUST_ENABLE_1);
                    dt.setCdate(Calendar.getInstance());
                    dt.setLv(GlobalConst.CUST_LV_FANS);
                    dt.setCredit(GlobalConst.D_ZERO);
                    dt.setCreditStatus(GlobalConst.I_ONE);
                    dt.setConvertedCredits(GlobalConst.D_ZERO);
                    dt.setJewerlyAmount(GlobalConst.D_ZERO);
                    dt.setProvince(province);
                    dt.setCity(city);
                    dt.setCounty(county);
                    dt.setAddress(address);
                    dt.setNcNo(ncNo);
                    dt = individCustLogic.persist(dt);

                    // 同步到外系统
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_YW, 0, DataDict.getDictName(DataDict.SYS_SCR, GlobalConst.SYS_SRC_NC), null, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_WECHAR, dt);
                } else {
                    IndividCust oldDt = dt.clone();
                    // 状态变化-日志
                    if (dt.getActive() == null || dt.getActive() != GlobalConst.ACTIVE_POSTIVE) {
                        ActiveStatus as = new ActiveStatus();
                        as.setIndividCust(dt);
                        as.setBeforeStatus(dt.getActive());
                        as.setAfterStatus(GlobalConst.ACTIVE_POSTIVE);
                        as.setReason("积分兑换礼品");
                        as.setMdate(Calendar.getInstance());
                        activeStatusEao.persist(as);

                        dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                    }
                    dt.setNcNo(ncNo);
                    dt.setCardNo(cardNo);
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechat(wechatNo);
                    dt.setFresh(fresh);
                    dt.setProvince(province);
                    dt.setCity(city);
                    dt.setCounty(county);
                    dt.setAddress(address);
                    individCustLogic.merge(dt);

                    // 同步到外系统
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_YW, oldDt, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_WECHAR, dt);
                }
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            data.put("cardNo", dt != null ? dt.getCardNo() : "");
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("IndividCustWsBean.nc2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_NC);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

    @Override
    @WebResult(name = "message")
    @WebMethod(operationName = "wechar2crm")
    public String wechar2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        IndividCust dt = null;
        message = DESUtils.getDesString(message);
        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            String cardNo = InterJsonParseUtils.parseString(json, "cardNo", errMap);
            String name = InterJsonParseUtils.parseString(json, "name", "客户名称", errMap, false);
            Long mobile = InterJsonParseUtils.parseLong(json, "mobile", "手机号码", errMap, false);
            Double integral = InterJsonParseUtils.parseDouble(json, "integral", errMap);
            String pwd = InterJsonParseUtils.parseString(json, "pwd", errMap);
            Calendar birthday = InterJsonParseUtils.parseCalendar(json, "birthday", "生日", "yyyy-MM-dd", errMap, false);
            Integer gender = InterJsonParseUtils.parseInteger(json, "gender", errMap);
            String wechat = InterJsonParseUtils.parseString(json, "wechat", errMap);
            String wechatId = InterJsonParseUtils.parseString(json, "wechatId", errMap);
            Calendar weddingDay = InterJsonParseUtils.parseCalendar(json, "weddingDay", "结婚纪念日", "yyyy-MM-dd", errMap, true);
            String email = InterJsonParseUtils.parseString(json, "email", errMap);
            Integer job = InterJsonParseUtils.parseInteger(json, "job", errMap);
            Integer income = InterJsonParseUtils.parseInteger(json, "income", errMap);
            Integer brandChannel = InterJsonParseUtils.parseInteger(json, "brandChannel", errMap);
            Integer motives = InterJsonParseUtils.parseInteger(json, "motives", errMap);
            Integer purchaseFactors = InterJsonParseUtils.parseInteger(json, "purchaseFactors", errMap);
            Integer belief = InterJsonParseUtils.parseInteger(json, "belief", errMap);
            Integer age = InterJsonParseUtils.parseInteger(json, "age", errMap);
            Integer province = InterJsonParseUtils.parseInteger(json, "province", "省", errMap, false);
            Integer city = InterJsonParseUtils.parseInteger(json, "city", "市", errMap, false);
            Integer county = InterJsonParseUtils.parseInteger(json, "county", "区/县", errMap, false);
            String address = InterJsonParseUtils.parseString(json, "address", "地址", errMap, false);
            String belongStoreNo = InterJsonParseUtils.parseString(json, "belongStoreNo", errMap);
            String belongStoreName = InterJsonParseUtils.parseString(json, "belongStoreName", errMap);

            String activityNo = InterJsonParseUtils.parseString(json, "activityNo", errMap);
            Integer purposePrice = InterJsonParseUtils.parseInteger(json, "purposePrice", errMap);
            Integer purposeProduct = InterJsonParseUtils.parseInteger(json, "purposeProduct", errMap);
            Integer purposeCategory = InterJsonParseUtils.parseInteger(json, "purposeCategory", errMap);
            Calendar purposeDay = InterJsonParseUtils.parseCalendar(json, "purposeDay", "需求时间", "yyyy-MM-dd", errMap, true);

            if (StringUtils.isNotBlank(cardNo)) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_cardNo", cardNo);
                dt = individCustLogic.find(searchParam);
                if (dt == null) {
                    errMap.put("cardNo", cardNo + "在crm系统不存在");
                } else {
                    Map mobileParam = new HashMap();
                    mobileParam.put("EQ_mobile", mobile);
                    IndividCust mobileDt = individCustLogic.find(mobileParam);
                    if (mobileDt != null && !cardNo.equals(mobileDt.getCardNo())) {
                        errMap.put("mobile", mobile + " 在crm系统已存在");
                    }
                }
            } else {
                Map searchParam = new HashMap();
                searchParam.put("EQ_mobile", mobile);
                dt = individCustLogic.find(searchParam);
                if (dt != null) {
                    errMap.put("mobile", mobile + " 在crm系统已存在");
                }
            }

            // 市场活动
            Long activityid = null;
            Integer activityType = null;
            if (StringUtils.isNotBlank(activityNo)) {
                String actJpql = "select t.activityid, t.activitytype from d_activity t where t.activityno = ?1 and t.status = 2 and trunc(sysdate) between t.begintime and t.endtime + 1";
                Object[] actObj = sqlEao.executeSQLQueryOne(actJpql, activityNo);
                if (actObj == null) {
                    errMap.put("activityNo", activityNo + " 活动在crm系统不存在或已过期");
                } else {
                    activityid = ((BigDecimal) actObj[0]).longValue();
                    activityType = ((BigDecimal) actObj[1]).intValue();
                }
            }

            if (CollectionUtils.isEmpty(errMap)) {
                if (dt == null) {
                    dt = new IndividCust();
                    dt.setCardNo(sqlEao.getCnNum(GlobalConst.NUM_INDIVID));// 新增时需要crm生成
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechatId(wechatId);
                    dt.setWechat(wechat);
                    dt.setWeddingDay(weddingDay);
                    dt.setEmail(email);
                    dt.setJob(job);
                    dt.setIncome(income);
                    dt.setBrandChannel(brandChannel);
                    dt.setMotives(motives);
                    dt.setPurchaseFactors(purchaseFactors);
                    dt.setBelief(belief);
                    dt.setAge(age);
                    dt.setPurposePrice(purposePrice);
                    dt.setPurposeProduct(purposeProduct);
                    dt.setPurposeCategory(purposeCategory);
                    dt.setPurposeDay(purposeDay);

                    // 默认归属“客服部”
                    if (StringUtils.isEmpty(belongStoreNo)) {
                        dt.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
                        dt.setBelongStoreName(GlobalConst.DEP_ORGName_KF);
                        dt.setSrcStoreNo(GlobalConst.DEP_ORGCODE_KF);
                        dt.setSrcStoreName(GlobalConst.DEP_ORGName_KF);
                    } else {
                        dt.setBelongStoreNo(belongStoreNo);
                        dt.setBelongStoreName(belongStoreName);
                        dt.setSrcStoreNo(belongStoreNo);
                        dt.setSrcStoreName(belongStoreName);
                    }
                    // 市场活动
                    if (activityid != null) {
                        if (activityType == GlobalConst.ACT_TYPE_MERCHANTS) {
                            dt.setType(GlobalConst.CUST_TYPE_ACTIVITY);
                        } else {
                            dt.setType(GlobalConst.CUST_TYPE_CUST);
                        }
                        dt.setSources(GlobalConst.SYS_SRC_ACTIVITY);
                        dt.setStatus(CustStatus.NEW);
                        dt.setPlanId(activityid);
                    } else {
                        dt.setType(GlobalConst.CUST_TYPE_CUST);
                        dt.setSources(GlobalConst.SYS_SRC_WECHAR);
                        dt.setStatus(CustStatus.PASS);
                    }
                    // 设置默认值
                    dt.setIsSendSms(GlobalConst.YES);
                    dt.setIsImport(GlobalConst.NO);
                    dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                    dt.setEnable(GlobalConst.CUST_ENABLE_1);
                    dt.setCdate(Calendar.getInstance());
                    dt.setFresh(GlobalConst.FRESH_0);
                    dt.setLv(GlobalConst.CUST_LV_FANS);
                    dt.setCredit(integral == null ? GlobalConst.D_ZERO : integral);
                    dt.setCreditStatus(GlobalConst.I_ONE);
                    dt.setConvertedCredits(GlobalConst.D_ZERO);
                    dt.setJewerlyAmount(GlobalConst.D_ZERO);
                    dt.setProvince(province);
                    dt.setCity(city);
                    dt.setCounty(county);
                    dt.setAddress(address);
                    dt = individCustLogic.persist(dt);

                    if (integral != null) {
                        IntegralAdjHis hisDt = new IntegralAdjHis();
                        hisDt.setCustType(GlobalConst.CUST_TYPE_CUST);
                        hisDt.setCreditBefore(GlobalConst.D_ZERO);
                        hisDt.setCreditAfter(dt.getCredit());
                        hisDt.setCreditStatus(GlobalConst.I_ONE);
                        hisDt.setModReason("注册新客户赠送积分");
                        hisDt.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
                        hisDt.setCreditOrigin(GlobalConst.SYS_SRC_WECHAR);
                        hisDt.setMdate(Calendar.getInstance());
                        hisDt.setIndividCust(dt);
                        inteHisLogic.persist(hisDt);// 历史表
                    }
                    // 核定记录
                    if (activityid != null) {
                        OperationLog operLog = new OperationLog();
                        operLog.setIndividCustId(dt.getIndividCustId());
                        operLog.setResult("已审核");
                        operLog.setCdate(Calendar.getInstance());
                        operLog.setType(GlobalConst.OPER_TYPE_1);
                        operLogLogic.persist(operLog);
                    }

                    // 同步到外系统
                    dt.setPwd(pwd);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_WECHAR, GlobalConst.SYS_SRC_YW, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_WECHAR, GlobalConst.SYS_SRC_NC, dt);
                } else {
                    IndividCust oldDt = dt.clone();

                    dt.setCardNo(cardNo);
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechatId(wechatId);
                    dt.setWechat(wechat);
                    dt.setWeddingDay(weddingDay);
                    dt.setEmail(email);
                    dt.setJob(job);
                    dt.setIncome(income);
                    dt.setBrandChannel(brandChannel);
                    dt.setMotives(motives);
                    dt.setPurchaseFactors(purchaseFactors);
                    dt.setBelief(belief);
                    dt.setAge(age);
                    dt.setProvince(province);
                    dt.setCity(city);
                    dt.setCounty(county);
                    dt.setAddress(address);
                    // 购买意向非空时回填，为空时保持原样
                    dt.setPurposePrice(purposePrice == null ? dt.getPurposePrice() : purposePrice);
                    dt.setPurposeProduct(purposeProduct == null ? dt.getPurposeProduct() : purposeProduct);
                    dt.setPurposeCategory(purposeCategory == null ? dt.getPurposeCategory() : purposeCategory);
                    dt.setPurposeDay(purposeDay == null ? dt.getPurposeDay() : purposeDay);
                    individCustLogic.merge(dt);

                    if (integral != null) {
                        String modReason = "完善客户信息赠送积分";

                        Map cntParam = new HashMap();
                        cntParam.put("EQ_modReason", modReason);
                        if (inteHisLogic.count(cntParam) == GlobalConst.ZERO) {
                            Double beforeCredit = dt.getCredit();
                            Double afterCredit = (beforeCredit != null ? beforeCredit.doubleValue() + integral : null);

                            IntegralAdjHis hisDt = new IntegralAdjHis();
                            hisDt.setCustType(GlobalConst.CUST_TYPE_CUST);
                            hisDt.setCreditBefore(beforeCredit);
                            hisDt.setCreditAfter(afterCredit);
                            hisDt.setCreditStatus(GlobalConst.I_ONE);
                            hisDt.setModReason(modReason);
                            hisDt.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
                            hisDt.setCreditOrigin(GlobalConst.SYS_SRC_WECHAR);
                            hisDt.setMdate(Calendar.getInstance());
                            hisDt.setIndividCust(dt);
                            inteHisLogic.persist(hisDt);// 历史表
                        }
                    }

                    // 同步到外系统
                    dt.setPwd(pwd);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_WECHAR, GlobalConst.SYS_SRC_YW, oldDt, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_WECHAR, GlobalConst.SYS_SRC_NC, dt);
                }
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            data.put("cardNo", dt != null ? dt.getCardNo() : "");
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("IndividCustWsBean.wechar2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_WECHAR);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

    @Override
    @WebResult(name = "message")
    @WebMethod(operationName = "yw2crm")
    public String yw2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        IndividCust dt = null;
        message = DESUtils.getDesString(message);
        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            String cardNo = InterJsonParseUtils.parseString(json, "cardNo", errMap);
            String name = InterJsonParseUtils.parseString(json, "name", "客户名称", errMap, false);
            Long mobile = InterJsonParseUtils.parseLong(json, "mobile", "手机号码", errMap, false);
            String pwd = InterJsonParseUtils.parseString(json, "pwd", errMap);
            Calendar birthday = InterJsonParseUtils.parseCalendar(json, "birthday", "生日", "yyyy-MM-dd", errMap, false);
            Integer gender = InterJsonParseUtils.parseInteger(json, "gender", errMap);
            String wechat = InterJsonParseUtils.parseString(json, "wechat", errMap);
            String wechatId = InterJsonParseUtils.parseString(json, "wechatId", errMap);
            String shippingAddr = InterJsonParseUtils.parseString(json, "shippingAddr", errMap);

            if (StringUtils.isNotBlank(cardNo)) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_cardNo", cardNo);
                dt = individCustLogic.find(searchParam);
                if (dt == null) {
                    errMap.put("cardNo", cardNo + "在crm系统不存在");
                } else {
                    Map mobileParam = new HashMap();
                    mobileParam.put("EQ_mobile", mobile);
                    IndividCust mobileDt = individCustLogic.find(mobileParam);
                    if (mobileDt != null && !cardNo.equals(mobileDt.getCardNo())) {
                        errMap.put("mobile", mobile + " 在crm系统已存在");
                    }
                }
            } else {
                Map searchParam = new HashMap();
                searchParam.put("EQ_mobile", mobile);
                dt = individCustLogic.find(searchParam);
                if (dt != null) {
                    errMap.put("mobile", mobile + " 在crm系统已存在");
                }
            }

            if (CollectionUtils.isEmpty(errMap)) {
                if (dt == null) {
                    dt = new IndividCust();
                    dt.setCardNo(sqlEao.getCnNum(GlobalConst.NUM_INDIVID));// 新增时需要crm生成
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechatId(wechatId);
                    dt.setWechat(wechat);
                    dt.setShippingAddr(shippingAddr);
                    dt.setSources(GlobalConst.SYS_SRC_YW);

                    // 默认归属“客服部”
                    dt.setBelongStoreNo(GlobalConst.DEP_ORGCODE_KF);
                    dt.setBelongStoreName(GlobalConst.DEP_ORGName_KF);

                    // 设置默认值
                    dt.setType(GlobalConst.CUST_TYPE_CUST);
                    dt.setIsSendSms(GlobalConst.NO);
                    dt.setIsImport(GlobalConst.NO);
                    dt.setStatus(CustStatus.NEW);
                    dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                    dt.setEnable(GlobalConst.CUST_ENABLE_1);
                    dt.setCdate(Calendar.getInstance());
                    dt.setFresh(GlobalConst.FRESH_0);
                    dt.setLv(GlobalConst.CUST_LV_FANS);
                    dt.setCredit(GlobalConst.D_ZERO);
                    dt.setCreditStatus(GlobalConst.I_ONE);
                    dt.setConvertedCredits(GlobalConst.D_ZERO);
                    dt.setJewerlyAmount(GlobalConst.D_ZERO);
                    dt = individCustLogic.persist(dt);

                    // 同步到外系统
                    dt.setPwd(pwd);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_YW, GlobalConst.SYS_SRC_WECHAR, 0, DataDict.getDictName(DataDict.SYS_SCR, GlobalConst.SYS_SRC_YW), null, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_YW, GlobalConst.SYS_SRC_NC, dt);
                } else {
                    IndividCust oldDt = dt.clone();

                    dt.setCardNo(cardNo);
                    dt.setName(name);
                    dt.setMobile(mobile);
                    dt.setBirthday(birthday);
                    dt.setBirthMonthday(NeuUtils.parseStringFromCalendar(birthday, "MM-dd"));
                    dt.setAge(individCustLogic.getIndividCustAge(dt.getBirthday()));
                    dt.setGender(gender == null ? GlobalConst.GENDER_WOMAN : gender);
                    dt.setWechatId(wechatId);
                    dt.setWechat(wechat);
                    dt.setShippingAddr(shippingAddr);
                    individCustLogic.merge(dt);

                    // 同步到外系统
                    dt.setPwd(pwd);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_YW, GlobalConst.SYS_SRC_WECHAR, oldDt, dt);
                    individCustLogic.synIndividCust(GlobalConst.SYS_SRC_YW, GlobalConst.SYS_SRC_NC, dt);
                }
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            data.put("cardNo", dt != null ? dt.getCardNo() : "");
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("IndividCustWsBean.yw2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_YW);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    @WebResult(name = "message")
    @WebMethod(operationName = "batchFresh_nc2crm")
    public String batchFresh_nc2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        List<IndividCust> rsList = new ArrayList<IndividCust>();
        try {
            Map errMap = new HashMap();
            List msgList = Json.parseArray(message);

            JSONObject obj;
            IndividCust dt = null;
            for (int i = 0; i < msgList.size(); i++) {
                obj = (JSONObject) msgList.get(i);
                String cardNo = InterJsonParseUtils.parseString(obj, "cardNo", "crm会员编码", errMap, false);
                Integer fresh = InterJsonParseUtils.parseInteger(obj, "fresh", "新老会员状态", errMap, false);
                Calendar freshChgTime = InterJsonParseUtils.parseCalendar(obj, "creattime", "创建时间", "yyyy-MM-dd HH:mm:ss", errMap, false);

                dt = new IndividCust();
                dt.setCardNo(cardNo);
                dt.setFresh(fresh);
                dt.setFreshChgTime(freshChgTime);
                rsList.add(dt);
            }

            if (CollectionUtils.isEmpty(errMap)) {
                String jpql = "update IndividCust t set t.fresh = ?1, t.freshChgTime = ?2 where t.cardNo = ?3";
                for (IndividCust cust : rsList) {
                    individCustEao.executeUpdate(jpql, new Object[] { cust.getFresh(), cust.getFreshChgTime(), cust.getCardNo() });
                }
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("IndividCustWsBean.batchFresh_nc2crm");
            interLog.setReqTime(reqTime);
            interLog.setReqContent(message);
            interLog.setSrc(GlobalConst.SYS_SRC_NC);
            interLog.setStatus(flag);
            interLog.setRespTime(Calendar.getInstance());
            interLog.setRespContent(respContent);
            logLogic.persist(interLog);
        }
        return respContent;
    }

}