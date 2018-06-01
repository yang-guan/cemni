package com.huiju.inter.saleorder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.inter.saleorder.entity.SaleOrder;
import com.huiju.inter.saleorder.logic.SaleOrderRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 销售订单
 * 
 * <pre>
 * {
 *     "type": 2,
 *     "orderNo": "SO1612050989",
 *     "orderDate": "2017-01-06",
 *     "fraCode": "001685",
 *     "fraName": "浙江永康加盟商",
 *     "storeNo": "T0579103",
 *     "storeName": "浙江永康特许店",
 *     "busiFlow": "普通销售",
 *     "totalAmount": 1000.59
 * }
 * </pre>
 * 
 * @author zzy
 * @date 2017年1月6日 下午4:05:54
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class SaleOrderWsBean implements SaleOrderWs {
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private SaleOrderRemote saleOrderLogic;

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
            Integer type = InterJsonParseUtils.parseInteger(json, "type", "订单类型", errMap, false);
            String orderNo = InterJsonParseUtils.parseString(json, "orderNo", "订单号码", errMap, false);
            Calendar orderDate = InterJsonParseUtils.parseCalendar(json, "orderDate", "订单日期", "yyyy-MM-dd", errMap, false);
            String fraCode = InterJsonParseUtils.parseString(json, "fraCode", "加盟商编码", errMap, false);
            String fraName = InterJsonParseUtils.parseString(json, "fraName", "加盟商名称", errMap, false);
            String storeNo = InterJsonParseUtils.parseString(json, "storeNo", errMap);
            String storeName = InterJsonParseUtils.parseString(json, "storeName", errMap);
            String busiFlow = InterJsonParseUtils.parseString(json, "busiFlow", errMap);
            Double totalAmount = InterJsonParseUtils.parseDouble(json, "totalAmount", errMap);

            if (CollectionUtils.isEmpty(errMap)) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_orderNo", orderNo);
                SaleOrder so = saleOrderLogic.find(searchParam);
                if (so == null) {
                    so = new SaleOrder();
                    so.setBusiFlow(busiFlow);
                    so.setFraCode(fraCode);
                    so.setFraName(fraName);
                    so.setOrderDate(orderDate);
                    so.setOrderNo(orderNo);
                    so.setStoreName(storeName);
                    so.setStoreNo(storeNo);
                    so.setTotalAmount(totalAmount);
                    so.setType(type);
                    saleOrderLogic.persist(so);
                } else {
                    so.setBusiFlow(busiFlow);
                    so.setFraCode(fraCode);
                    so.setFraName(fraName);
                    so.setOrderDate(orderDate);
                    so.setStoreName(storeName);
                    so.setStoreNo(storeNo);
                    so.setTotalAmount(totalAmount);
                    so.setType(type);
                    saleOrderLogic.merge(so);
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
            interLog.setCrmClassMethod("SaleOrderWsBean.nc2crm");
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