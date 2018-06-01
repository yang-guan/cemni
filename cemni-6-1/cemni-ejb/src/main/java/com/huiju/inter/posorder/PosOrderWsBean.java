package com.huiju.inter.posorder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.StringUtils;

import com.huiju.archive.individcust.eao.ActiveStatusEaoLocal;
import com.huiju.archive.individcust.entity.ActiveStatus;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.console.dict.entity.Dict;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.gradeadj.logic.GradeAdjHisRemote;
import com.huiju.integral.graderule.entity.GradeRule;
import com.huiju.integral.graderule.logic.GradeRuleRemote;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.integral.integraladj.logic.IntegralAdjHisRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.inter.posorder.entity.JewelSegment;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.inter.posorder.logic.JewelSegmentRemote;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * POS单
 * 
 * <pre>
 * {
 *     "actualSaleAmount": 2500,
 *     "anniversaryEvent": "纪念日双倍积分2",
 *     "anniversaryIntegral": 2,
 *     "billType": "1",
 *     "cardNo": "C800501014",
 *     "consumeIntegral": 0,
 *     "custType": "1",
 *     "ncNo": "C1281111116",
 *     "posBillDate": "2017-04-25 08:14:59",
 *     "posNo": "TJ311704260002",
 *     "sales": [
 *         {
 *             "actualSaleAmount": 2500,
 *             "assistantAmount": 0,
 *             "assistantName": "贾方方",
 *             "assistantPercent": 0,
 *             "certificateNo": 1,
 *             "discount": 100,
 *             "flargess": "N",
 *             "goodsBar": "02222899",
 *             "goodsClassHighestNo": "TG",
 *             "goodsCnt": 1,
 *             "goodsName": "Au750钻石戒指",
 *             "goodsNo": "100000A0",
 *             "goodsPrice": 5000,
 *             "jewelDiscountAmount": 625,
 *             "jewelWeight": 0.086,
 *             "mainClerkAmount": 625,
 *             "mainClerkName": "徐丽娜",
 *             "mainClerkPercent": 100,
 *             "stockType": "S20304"
 *         },
 *         {
 *             "actualSaleAmount": 160,
 *             "assistantAmount": 0,
 *             "assistantName": "贾方方",
 *             "assistantPercent": 0,
 *             "certificateNo": 1,
 *             "discount": 100,
 *             "flargess": "Y",
 *             "goodsCnt": 1,
 *             "goodsName": "心型餐具",
 *             "goodsNo": "W303020006",
 *             "goodsPrice": 160,
 *             "jewelDiscountAmount": 0,
 *             "jewelWeight": 0,
 *             "mainClerkAmount": 0,
 *             "mainClerkName": "徐丽娜",
 *             "mainClerkPercent": 100,
 *             "stockType": "W30302"
 *         }
 *     ],
 *     "storeName": "赣榆金阳店",
 *     "storeNo": "032"
 * }
 * </pre>
 * 
 * @author：yuhb
 * @date：2017年2月16日 下午1:46:49
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class PosOrderWsBean implements PosOrderWs {
    @Resource
    private UserTransaction ux;
    @EJB
    private PosOrderRemote posOrderLogic;
    @EJB
    private ActiveStatusEaoLocal activeStatusEao;
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private GradeAdjHisRemote gradeAdjHisLogic;
    @EJB
    private GradeRuleRemote gradeRuleLogic;
    @EJB
    private IntegralAdjHisRemote integralLogic;
    @EJB
    private JewelSegmentRemote jewelLogic;

    @Override
    @WebMethod
    @WebResult(name = "message")
    public String nc2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        try {
            Map errMap = new HashMap();

            Map json = Json.parseMap(message);
            Integer custType = GlobalConst.CUST_TYPE_CUST;
            Double anniversaryIntegral = InterJsonParseUtils.parseDouble(json, "anniversaryIntegral", errMap);// 纪念日积分倍率
            String anniversaryEvent = InterJsonParseUtils.parseString(json, "anniversaryEvent", errMap);// 纪念日积分活动
            Integer billType = InterJsonParseUtils.parseInteger(json, "billType", "单据类型", errMap, false);

            String posNo = InterJsonParseUtils.parseString(json, "posNo", "pos单号", errMap, false);
            Calendar posBillDate = InterJsonParseUtils.parseCalendar(json, "posBillDate", "pos单据日期", "yyyy-MM-dd HH:mm:ss", errMap, false);
            String storeNo = InterJsonParseUtils.parseString(json, "storeNo", "门店编码", errMap, false);
            String storeName = InterJsonParseUtils.parseString(json, "storeName", "门店名称", errMap, false);
            String cardNo = InterJsonParseUtils.parseString(json, "cardNo", "crm卡号", errMap, false);
            Double actualSaleAmount = InterJsonParseUtils.parseDouble(json, "actualSaleAmount", "实际消费总金额", errMap, false);
            Double consumeIntegral = InterJsonParseUtils.parseDouble(json, "consumeIntegral", errMap);// 消费获取积分
            Double chargeIntegral = InterJsonParseUtils.parseDouble(json, "chargeIntegral", errMap);// 抵现积分
            String refCardNo = InterJsonParseUtils.parseString(json, "refCardNo", errMap);// 推荐人crm卡号
            String actNo = InterJsonParseUtils.parseString(json, "actNo", errMap);// 活动编码
            String subject = InterJsonParseUtils.parseString(json, "subject", errMap);// 活动主题
            String couponNo = InterJsonParseUtils.parseString(json, "couponNo", errMap);// 卡券编码
            String mainClerkName = InterJsonParseUtils.parseString(json, "mainClerkName", errMap);// 主营业员姓名
            String mainClerkPercent = InterJsonParseUtils.parseString(json, "mainClerkPercent", errMap);// 主营业员比例
            Double mainClerkAmount = InterJsonParseUtils.parseDouble(json, "mainClerkAmount", errMap);// 主营业员金额
            String assistantName = InterJsonParseUtils.parseString(json, "assistantName", errMap);// 副营业员姓名
            String assistantPercent = InterJsonParseUtils.parseString(json, "assistantPercent", errMap);// 副营业员比例
            Double assistantAmount = InterJsonParseUtils.parseDouble(json, "assistantAmount", errMap);// 副营业额金额

            List<PosOrder> posOrderList = new ArrayList<PosOrder>();

            double jewelDiscountAmount = 0;// 累计珠宝折算额
            List<Map> sales = (List<Map>) json.get("sales");
            for (Map sale : sales) {
                PosOrder dt = new PosOrder();

                String flargess = InterJsonParseUtils.parseString(sale, "flargess", "是否赠品", errMap, false);
                int flargessI = (StringUtils.isNotBlank(flargess) ? ("N".equals(flargess) ? GlobalConst.NO : GlobalConst.YES) : GlobalConst.NO);
                dt.setFlargess(flargessI);

                dt.setGoodsNo(InterJsonParseUtils.parseString(sale, "goodsNo", "商品编码", errMap, false));
                dt.setGoodsBar(InterJsonParseUtils.parseString(sale, "goodsBar", errMap));
                dt.setGoodsCnt(InterJsonParseUtils.parseLong(sale, "goodsCnt", "数量", errMap, false));
                dt.setGoodsName(InterJsonParseUtils.parseString(sale, "goodsName", errMap));// 商品名称
                dt.setGoodsPrice(InterJsonParseUtils.parseDouble(sale, "goodsPrice", "单价", errMap, false));
                dt.setGoodsClassHighestNo(InterJsonParseUtils.parseString(sale, "goodsClassHighestNo", errMap));// 商品所属分类最高级编码
                dt.setCertificateNo(InterJsonParseUtils.parseString(sale, "certificateNo", "证书号码", errMap, (flargessI == GlobalConst.NO ? false : true)));
                dt.setJewelWeight(InterJsonParseUtils.parseDouble(sale, "jewelWeight", errMap));// 钻石分数值（重量）
                // 系列分类（文字）：设计师款
                dt.setSeriesTypeName(InterJsonParseUtils.parseString(sale, "seriesTypeName", errMap));
                dt.setDesignerStyle(DataDict.getDictValue(DataDict.SALESMENT_DESIGNERSTYLE, dt.getSeriesTypeName()));
                dt.setDesignerStyleName(dt.getSeriesTypeName());
                dt.setStockTypeName(InterJsonParseUtils.parseString(sale, "stockType", "存货分类", errMap, false));
                dt.setActualSaleAmount(InterJsonParseUtils.parseDouble(sale, "actualSaleAmount", "实际销售金额", errMap, false));
                dt.setJewelDiscountAmount(InterJsonParseUtils.parseDouble(sale, "jewelDiscountAmount", errMap));// 珠宝折算额
                posOrderList.add(dt);

                jewelDiscountAmount += (dt.getJewelDiscountAmount() != null ? dt.getJewelDiscountAmount() : 0);
            }

            Map custParam = new HashMap();
            custParam.put("LIKE_cardNo", cardNo);
            IndividCust cust = individCustLogic.find(custParam);
            if (cust == null) {
                errMap.put("crm卡号", cardNo + "在crm系统不存在");
            } else {
                if (chargeIntegral != null && chargeIntegral > 0 && cust.getCreditStatus() != null && cust.getCreditStatus() == GlobalConst.CREDIT_STATUS_2) {
                    errMap.put("积分状态", " 积分已冻结不允许积分抵现");
                }
            }
            if (CollectionUtils.isEmpty(errMap)) {
                ux.begin();

                IndividCust oldDt = cust.clone();
                String cardName = cust.getName();
                long mobile = cust.getMobile();
                // 积分
                this.chgIntegral(cust, billType, consumeIntegral, chargeIntegral);
                // 等级
                boolean chgLvFlag = this.checkLv(cust, jewelDiscountAmount);
                // 活跃状态：记录日志
                if (cust.getActive() != GlobalConst.ACTIVE_POSTIVE) {
                    ActiveStatus as = new ActiveStatus();
                    as.setIndividCust(cust);
                    as.setBeforeStatus(cust.getActive());
                    as.setAfterStatus(GlobalConst.ACTIVE_POSTIVE);
                    as.setReason(billType == 1 ? ((chargeIntegral != null && chargeIntegral > 0) ? "积分抵现扣减积分" : "pos单") : ((chargeIntegral != null && chargeIntegral > 0) ? "兑换礼品扣减积分" : "礼品单"));
                    as.setMdate(Calendar.getInstance());
                    activeStatusEao.persist(as);

                    cust.setActive(GlobalConst.ACTIVE_POSTIVE);
                }
                // 回填门店信息
                Map posParams = new HashMap();
                posParams.put("EQ_cardNo", cardNo);
                // 首次消费pos单
                if (posOrderLogic.count(posParams) == 0) {
                    cust.setFristStoreNo(storeNo);
                    cust.setFristStoreName(storeName);
                    cust.setFristBuyTime(posBillDate);
                    // 推荐人
                    this.chgRefIndividCust(cust, refCardNo, consumeIntegral);
                }
                cust.setLastStoreNo(storeNo);
                cust.setLastStoreName(storeName);
                cust.setLastBuyTime(posBillDate);
                cust.setBelongStoreNo(storeNo);
                cust.setBelongStoreName(storeName);
                cust.setCreditStatus(GlobalConst.CREDIT_STATUS_1);
                individCustLogic.merge(cust);

                List<Dict> stockTypeList = DataDict.getSubDict(DataDict.STOCK_TYPE);
                for (PosOrder dt : posOrderList) {
                    if (dt.getJewelWeight() != null) {
                        Map jewelParam = new HashMap();
                        jewelParam.put("EQ_jewelWeight", dt.getJewelWeight());
                        JewelSegment jewel = jewelLogic.find(jewelParam);
                        dt.setScoreSegment(jewel == null ? null : jewel.getDictVal());// 钻石分数值
                    }

                    if (dt.getGoodsPrice() != null && dt.getGoodsCnt() != null && dt.getGoodsPrice() * dt.getGoodsCnt() != 0) {
                        dt.setDiscount(dt.getActualSaleAmount() / (dt.getGoodsPrice() * dt.getGoodsCnt()));// 折扣率
                    }
                    if (actualSaleAmount != null && actualSaleAmount != 0) {
                        dt.setConsumeIntegral(consumeIntegral * dt.getActualSaleAmount() / actualSaleAmount);// “按比例”分配积分
                        // 抵现积分
                        if (chargeIntegral != null) {
                            dt.setChargeIntegral(chargeIntegral * dt.getActualSaleAmount() / actualSaleAmount);// “按比例”分配积分
                        } else {
                            dt.setChargeIntegral(GlobalConst.D_ONE);
                        }
                    } else {
                        dt.setConsumeIntegral(GlobalConst.D_ONE);
                        dt.setChargeIntegral(GlobalConst.D_ONE);
                    }

                    // 存货分类（字典表：9801）
                    String stockType = dt.getStockTypeName();
                    if (stockType != null) {
                        for (Dict dict : stockTypeList) {
                            String[] arr = dict.getName().split("-");
                            if (arr.length == 2) {
                                if (stockType.equals(arr[0])) {
                                    dt.setStockType(dict.getValue());
                                }
                            } else {
                                if (stockType.equals(arr[2])) {
                                    dt.setStockType(dict.getValue());
                                }
                            }
                        }
                    }
                    dt.setCustType(custType);
                    dt.setPosNo(posNo);
                    dt.setPosbillDate(posBillDate);
                    dt.setStoreNo(storeNo);
                    dt.setStoreName(storeName);
                    dt.setNcNo(cust.getNcNo());
                    dt.setCardNo(cardNo);
                    dt.setCardName(cardName);
                    dt.setMobile(mobile);
                    dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, cust.getLv()));
                    dt.setActive(cust.getActive());
                    dt.setActNo(actNo);
                    dt.setSubject(subject);
                    dt.setMainclerkName(mainClerkName);
                    dt.setMainclerkPercent(mainClerkPercent);
                    dt.setMainclerkAmount(mainClerkAmount);
                    dt.setAssistantName(assistantName);
                    dt.setAssistantPercent(assistantPercent);
                    dt.setAssistantAmount(assistantAmount);
                    dt.setCouponNo(couponNo);
                    dt.setAnniversaryIntegral(anniversaryIntegral);
                    dt.setAnniversaryEvent(anniversaryEvent);
                    dt.setBillType(billType);
                    dt.setIntegralSrc(GlobalConst.SYS_SRC_NC);
                    dt.setIndividCust(cust);
                    posOrderLogic.persist(dt);
                }

                // 3.短信-消费感谢
                smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_6, GlobalConst.SYS_SRC_NC, posNo);
                // 4.短信-等级变化
                if (chgLvFlag) {
                    smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_8, GlobalConst.SYS_SRC_NC, cust.getMobile());
                }
                // 5.短信-积分变化
                smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_9, GlobalConst.SYS_SRC_NC, cust.getMobile());
                // 6.短信-大件销售
                smsLogic.bigSaleSendSms(posNo);

                // 客户档案-同步到外系统
                individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_YW, oldDt, cust);
                individCustLogic.synIndividCust(GlobalConst.SYS_SRC_NC, GlobalConst.SYS_SRC_WECHAR, cust);

                ux.commit();
                flag = GlobalConst.SUCCESS;
                msg = GlobalConst.TIP_SUCCESS;
            } else {
                msg = DataUtils.toJson(errMap);
            }
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            msg = NeuUtils.getStackTraceStr(e);

            e.printStackTrace();
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("PosOrderWsBean.nc2crm");
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

    /**
     * 消费活动积分历史
     */
    private void chgIntegral(IndividCust cust, int billType, Double consumeIntegral, Double chargeIntegral) {
        Double integralBefore = (cust.getCredit() == null ? 0 : cust.getCredit());// 修改前积分

        consumeIntegral = (consumeIntegral != null ? consumeIntegral : 0);// 消费获取积分
        Double consumelAfter = integralBefore + consumeIntegral;// 消费后积分
        if (consumeIntegral != 0) {
            IntegralAdjHis his = new IntegralAdjHis();
            his.setCreditBefore(integralBefore);
            his.setCreditAfter(consumelAfter);
            his.setConvertedCredits(cust.getConvertedCredits());
            his.setCreditStatus(cust.getCreditStatus());
            his.setMdate(Calendar.getInstance());
            his.setModReason("消费获得积分");
            his.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
            his.setCreditOrigin(GlobalConst.SYS_SRC_NC);
            his.setCustType(GlobalConst.CUST_TYPE_CUST);
            his.setIndividCust(cust);
            integralLogic.persist(his);// 历史表
        }

        chargeIntegral = (chargeIntegral != null ? chargeIntegral : GlobalConst.D_ZERO);
        Double chargeAfter = consumelAfter - chargeIntegral;// 抵现后积分
        if (chargeIntegral != 0) {
            IntegralAdjHis his2 = new IntegralAdjHis();
            his2.setCreditBefore(consumelAfter);
            his2.setCreditAfter(chargeAfter);
            his2.setConvertedCredits(cust.getConvertedCredits());
            his2.setCreditStatus(cust.getCreditStatus());
            his2.setMdate(Calendar.getInstance());
            his2.setModReason(billType == 1 ? "积分抵现扣减积分" : "兑换礼品扣减积分");
            his2.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
            his2.setCreditOrigin(GlobalConst.SYS_SRC_NC);
            his2.setCustType(GlobalConst.CUST_TYPE_CUST);
            his2.setIndividCust(cust);
            integralLogic.persist(his2);// 历史表
        }
        cust.setCredit(chargeAfter);// 回填-会员积分
    }

    /**
     * 个人档案：根据珠宝折算额判断是否需要调整用户等级
     */
    private boolean checkLv(IndividCust cust, Double jewelDiscountAmount) {
        boolean chgLvFlag = false;
        int beforeLv = (cust.getLv() == null ? GlobalConst.CUST_LV_FANS : cust.getLv());
        Double jewerlyAmount = (cust.getJewerlyAmount() == null ? 0 : cust.getJewerlyAmount()) + jewelDiscountAmount;

        String[] sorts = { "lv,desc" };
        List<GradeRule> ruleList = gradeRuleLogic.findAll(sorts);
        int lv = GlobalConst.CUST_LV_FANS;
        for (GradeRule dt : ruleList) {
            if (jewerlyAmount >= dt.getJewerlyAmount()) {
                lv = dt.getLv();
            } else {
                break;
            }
        }
        if (beforeLv > lv) {// 向高级别调整
            GradeAdjHis his = new GradeAdjHis();
            his.setIndividCust(cust);
            his.setLvBefore(beforeLv);
            his.setLvAfter(lv);
            his.setJewerlyAmount(cust.getJewerlyAmount());
            his.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
            his.setMdate(Calendar.getInstance());
            his.setModReason("pos单触发");
            his.setCustType(GlobalConst.CUST_TYPE_CUST);
            gradeAdjHisLogic.persist(his);// 历史表

            chgLvFlag = true;
            cust.setLv(lv);// 重置会员等级
        }
        cust.setJewerlyAmount(jewerlyAmount);// 重置珠宝折算额
        return chgLvFlag;
    }

    /**
     * 首次消费才增加推荐人积分：积分调整历史及数据同步
     */
    private void chgRefIndividCust(IndividCust cust, String refCardNo, Double refGetIntegral) {
        if (refCardNo != null && refGetIntegral != null) {
            Map refCustParam = new HashMap();
            refCustParam.put("EQ_cardNo", refCardNo);
            IndividCust refCust = individCustLogic.find(refCustParam);
            if (refCust != null) {
                Double creditBefore = (refCust.getCredit() == null ? 0 : refCust.getCredit());
                Double creditAfter = creditBefore + refGetIntegral;

                refCust.setCredit(creditAfter);
                refCust.setActive(integralLogic.getCustActive(GlobalConst.CUST_TYPE_CUST, refCust.getIndividCustId()));
                // 状态变化-日志
                if (refCust.getActive() != GlobalConst.ACTIVE_POSTIVE) {
                    ActiveStatus as = new ActiveStatus();
                    as.setIndividCust(refCust);
                    as.setBeforeStatus(refCust.getActive());
                    as.setAfterStatus(GlobalConst.ACTIVE_POSTIVE);
                    as.setReason("pos推荐人-活跃状态");
                    as.setMdate(Calendar.getInstance());
                    activeStatusEao.persist(as);

                    refCust.setActive(GlobalConst.ACTIVE_POSTIVE);
                }
                individCustLogic.merge(refCust);

                // 积分变化-日志
                IntegralAdjHis his = new IntegralAdjHis();
                his.setCreditBefore(creditBefore);
                his.setCreditAfter(creditAfter);
                his.setConvertedCredits(refCust.getConvertedCredits());
                his.setCreditStatus(refCust.getCreditStatus());
                his.setMdate(Calendar.getInstance());
                his.setModReason("pos推荐人获得积分");
                his.setModType(GlobalConst.INTEGRAL_CHG_AUTO);
                his.setCreditOrigin(GlobalConst.SYS_SRC_NC);
                his.setCustType(GlobalConst.CUST_TYPE_CUST);
                his.setIndividCust(refCust);
                integralLogic.persist(his);// 历史表

                // 短信-推荐人积分变化
                smsLogic.immediateSendSms(GlobalConst.SMS_TYPE_9, GlobalConst.SYS_SRC_NC, refCust.getMobile());
                // 推荐人客户档案-同步到外系统
                individCustLogic.synIndividCustToEx(refCust);
                // 回填消费客户的推荐人信息
                cust.setReferrer(refCust.getName() + "（" + refCust.getCardNo() + "）");
            }
        }
    }

}