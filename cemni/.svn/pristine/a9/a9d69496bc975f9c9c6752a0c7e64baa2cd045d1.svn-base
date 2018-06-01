package com.huiju.inter.busiman;

import java.util.Calendar;
import java.util.HashMap;
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

import com.huiju.archive.channel.eao.ChannelEaoLocal;
import com.huiju.archive.channel.entity.Channel;
import com.huiju.archive.franchisee.eao.FranchiseeEaoLocal;
import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.archive.supplier.eao.SupplierEaoLocal;
import com.huiju.archive.supplier.entity.Supplier;
import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.InterJsonParseUtils;
import com.huiju.utils.NeuUtils;

/**
 * 客商
 * 
 * <pre>
 * {
 *     "merchantType": "3",
 *     "merchantNo": "101",
 *     "fullName": "南京千年珠宝翠钻珠宝有限公司",
 *     "shortName": "南京千年珠宝翠钻珠宝有限公司",
 *     "isValid": "1",
 *     "createTime": "2015-06-17"
 * }
 * </pre>
 */
@Stateless
@WebService
@SuppressWarnings({ "unchecked", "rawtypes" })
@TransactionManagement(TransactionManagementType.BEAN)
public class BusiManWsBean implements BusiManWs {
    @Resource
    private UserTransaction ux;
    @EJB
    private InterLogRemote logLogic;
    @EJB
    private ChannelEaoLocal channelEao;
    @EJB
    private FranchiseeEaoLocal franchiseeEao;
    @EJB
    private SupplierEaoLocal supplierEao;

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
            String merchantNo = InterJsonParseUtils.parseString(json, "merchantNo", "客商编码", errMap, false);
            String fullName = InterJsonParseUtils.parseString(json, "fullName", "客商全称", errMap, false);
            String shortName = InterJsonParseUtils.parseString(json, "shortName", "客商简称", errMap, false);
            Integer merchantType = InterJsonParseUtils.parseInteger(json, "merchantType", "客商类型", errMap, false);
            Integer isValid = InterJsonParseUtils.parseInteger(json, "isValid", "是否有效", errMap, false);
            Calendar createTime = InterJsonParseUtils.parseCalendar(json, "createTime", "创建时间", "yyyy-MM-dd", errMap, false);

            if (CollectionUtils.isEmpty(errMap)) {
                ux.begin();
                // 先失效
                franchiseeEao.executeUpdate("update Franchisee s set s.isValid = 0, s.modifyUser = 'pos单', s.modifyDate = sysdate where s.fraCode = ?1", merchantNo);
                supplierEao.executeUpdate("update Supplier s set s.isValid = 0, s.muser = 'pos单', s.mdate = sysdate where s.supplierno = ?1", merchantNo);
                channelEao.executeUpdate("update Channel s set s.isValid = 0, s.muser = 'pos单', s.mdate = sysdate where s.channelno = ?1", merchantNo);

                if (merchantType == GlobalConst.BUSIMAN_TYPE_FRAN) {
                    Franchisee dt = franchiseeEao.executeQueryOne("select s from Franchisee s where s.fraCode = ?1", merchantNo);
                    if (dt == null) {
                        dt = new Franchisee();
                        dt.setFraCode(merchantNo);
                        dt.setFraName(fullName);
                        dt.setShortName(shortName);
                        dt.setCreateDate(createTime);
                        dt.setIsValid(isValid);
                        franchiseeEao.persist(dt);
                    } else {
                        dt.setFraName(fullName);
                        dt.setShortName(shortName);
                        dt.setIsValid(isValid);
                        franchiseeEao.merge(dt);
                    }
                } else if (merchantType == GlobalConst.BUSIMAN_TYPE_SUPP) {
                    Supplier dt = franchiseeEao.executeQueryOne("select s from Supplier s where s.supplierno = ?1", merchantNo);
                    if (dt == null) {
                        dt = new Supplier();
                        dt.setSupplierno(merchantNo);
                        dt.setSuppliername(fullName);
                        dt.setName(shortName);
                        dt.setCdate(createTime);
                        dt.setIsValid(isValid);
                        supplierEao.persist(dt);
                    } else {
                        dt.setSuppliername(fullName);
                        dt.setName(shortName);
                        dt.setIsValid(isValid);
                        supplierEao.merge(dt);
                    }
                } else if (merchantType == GlobalConst.BUSIMAN_TYPE_CHAN) {
                    Channel dt = franchiseeEao.executeQueryOne("select s from Channel s where s.channelno = ?1", merchantNo);
                    if (dt == null) {
                        dt = new Channel();
                        dt.setChannelno(merchantNo);
                        dt.setChannelname(fullName);
                        dt.setName(shortName);
                        dt.setCdate(createTime);
                        dt.setIsValid(isValid);
                        channelEao.persist(dt);
                    } else {
                        dt.setChannelname(fullName);
                        dt.setName(shortName);
                        dt.setIsValid(isValid);
                        channelEao.merge(dt);
                    }
                }
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
        } finally {
            // 拼接返回报文
            Map data = new HashMap();
            data.put("flag", flag);
            data.put("msg", msg);
            respContent = DataUtils.toJson(data);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setCrmClassMethod("BusiManWsBean.nc2crm");
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