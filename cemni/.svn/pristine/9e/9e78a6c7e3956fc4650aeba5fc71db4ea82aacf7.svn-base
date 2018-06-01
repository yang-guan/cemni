package com.huiju.inter.afterserv;

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

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.GlobalConst;
import com.huiju.inter.afterserv.entity.AfterServ;
import com.huiju.inter.afterserv.logic.AfterServRemote;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 售后服务
 * 
 * <pre>
 * {
 *     "id": "1001A7100000001SZMTY",
 *     "cardNo": "C800500057",
 *     "storeNo": "040",
 *     "storeName": "南京金鹰天地专柜",
 *     "startDay": "2016-04-24 00:00:00",
 *     "endDay": "2016-04-26 00:00:00",
 *     "arr": [
 *         {
 *             "goodsBar": "05238292",
 *             "goodsName": "01000001",
 *             "servicePro": "改规格,清洗,抛光,电镀",
 *             "newNo": "13",
 *             "oldNo": "11"
 *         }
 *     ]
 * }
 * </pre>
 * 
 * @author：yuhb
 * @date：2017年2月28日 下午2:13:14
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class AfterSerWsBean implements AfterSerWs {
    @EJB
    private AfterServRemote afterSerLogic;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private IndividCustRemote individCustLogic;

    @Override
    @WebMethod
    @WebResult(name = "message")
    public String nc2crm(@WebParam(name = "message") String message) {
        String respContent = null;
        Calendar reqTime = Calendar.getInstance();

        Integer flag = GlobalConst.FAIL;
        String msg = null;

        try {
            List<AfterServ> rsList = new ArrayList<AfterServ>();
            List errList = new ArrayList();

            Map msgJson = Json.parseMap(message);
            List<Map> jsonArr = (List<Map>) msgJson.get("arr");

            Map msgErrMap = new HashMap();
            String cardNo = InterJsonParseUtils.parseString(msgJson, "cardNo", "会员卡号", msgErrMap, false);
            String storeNo = InterJsonParseUtils.parseString(msgJson, "storeNo", "门店编码", msgErrMap, false);
            String storeName = InterJsonParseUtils.parseString(msgJson, "storeName", "门店名称", msgErrMap, false);

            if (!CollectionUtils.isEmpty(msgErrMap)) {
                msg = DataUtils.toJson(msgErrMap);
            } else {
                for (Map json : jsonArr) {
                    Map errMap = new HashMap();
                    String goodsBar = InterJsonParseUtils.parseString(json, "goodsBar", "商品条码", errMap, false);
                    String goodsName = InterJsonParseUtils.parseString(json, "goodsName", "商品名称", errMap, false);
                    String servicePro = InterJsonParseUtils.parseString(json, "servicePro", "服务项目", errMap, false);
                    String oldNo = InterJsonParseUtils.parseString(json, "oldNo", errMap);
                    String newNo = InterJsonParseUtils.parseString(json, "newNo", errMap);
                    Calendar startDay = InterJsonParseUtils.parseCalendar(msgJson, "startDay", "服务开始时间", "yyyy-MM-dd HH:mm:ss", errMap, false);
                    Calendar endDay = InterJsonParseUtils.parseCalendar(msgJson, "endDay", "服务结束时间", "yyyy-MM-dd HH:mm:ss", errMap, false);

                    Map custParam = new HashMap();
                    custParam.put("EQ_cardNo", cardNo);
                    IndividCust cust = individCustLogic.find(custParam);
                    if (cust == null) {
                        errMap.put("cardNo", cardNo + "在crm系统不存在");
                    }

                    if (CollectionUtils.isEmpty(errMap)) {
                        Map afterServParam = new HashMap();
                        afterServParam.put("EQ_goodsBar", goodsBar);
                        AfterServ dt = afterSerLogic.find(afterServParam);

                        if (dt == null) {
                            dt = new AfterServ();
                            dt.setCardNo(cardNo);
                            dt.setGoodsBar(goodsBar);
                            dt.setGoodsName(goodsName);
                            dt.setServicePro(servicePro);
                            dt.setOldNo(oldNo);
                            dt.setNewNo(newNo);
                            dt.setStartDay(startDay);
                            dt.setEndDay(endDay);
                            dt.setStoreNo(storeNo);
                            dt.setStoreName(storeName);
                            dt.setIndividCust(cust);
                        } else {
                            dt.setCardNo(cardNo);
                            dt.setGoodsBar(goodsBar);
                            dt.setGoodsName(goodsName);
                            dt.setServicePro(servicePro);
                            dt.setOldNo(oldNo);
                            dt.setNewNo(newNo);
                            dt.setStartDay(startDay);
                            dt.setEndDay(endDay);
                            dt.setStoreNo(storeNo);
                            dt.setStoreName(storeName);
                            dt.setIndividCust(cust);
                        }
                        rsList.add(dt);
                    } else {
                        errList.add(errMap);
                    }

                    if (CollectionUtils.isEmpty(errList)) {
                        for (AfterServ dt : rsList) {
                            if (dt.getAfterservId() == null) {
                                afterSerLogic.persist(dt);
                            } else {
                                afterSerLogic.merge(dt);
                            }
                        }
                        flag = GlobalConst.SUCCESS;
                        msg = GlobalConst.TIP_SUCCESS;
                    } else {
                        msg = DataUtils.toJson(errList);
                    }
                }
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
            interLog.setCrmClassMethod("AfterSerWsBean.nc2crm");
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