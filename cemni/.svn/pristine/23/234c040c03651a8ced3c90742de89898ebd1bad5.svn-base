package com.huiju.inter.sms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.json.Json;
import com.huiju.module.util.StringUtils;
import com.huiju.sms.smslog.entity.SmsLog;
import com.huiju.sms.smslog.logic.SmsLogRemote;
import com.huiju.utils.NeuUtils;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendBatchSmsInfo;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

/**
 * 短信接口
 * 
 * @author：yuhb
 * @date：2016年12月27日 下午2:28:20
 */
@Stateless(mappedName = "YunpianSms")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class YunpianSms implements YunpianSmsRemote {
    @EJB
    private InterLogRemote interLogLogic;
    @EJB
    private SmsLogRemote smsLogLogic;

    @Override
    public void multiSends(Long smsId, Integer type, List<Long> mobile, List<String> text) {
        Map reqMap = new HashMap();
        reqMap.put("mobile", mobile);
        reqMap.put("text", text);

        // 转换类型
        List<String> mobiles = new ArrayList<String>();
        for (Long m : mobile) {
            mobiles.add(m.toString());
        }
        try {
            YunpianRestClient client = new YunpianRestClient(NeuUtils.getProperty(type == GlobalConst.SMS_TYPE_12 ? "yx_smsapikey" : "smsapikey"));
            SmsOperator sysOper = client.getSmsOperator();
            ResultDO<SendBatchSmsInfo> result = sysOper.multiSend(mobiles, text);
            String msg = DataUtils.toJson(result);

            Map json = Json.parseMap(msg);
            boolean success = (Boolean) json.get("success");
            if (success) {
                Map firstData = (Map) json.get("data");
                List<Map> data = (List<Map>) firstData.get("data");
                for (Map map : data) {
                    SmsLog dt = new SmsLog();
                    dt.setSmsId(smsId);
                    dt.setType(type);
                    dt.setCdate(Calendar.getInstance());

                    dt.setMobile(Long.parseLong(map.get("mobile").toString()));
                    dt.setRespContent(map.get("msg").toString());
                    dt.setStatus(map.get("code").toString().equals("0") ? GlobalConst.SUCCESS : GlobalConst.FAIL);

                    // 获取请求报文：失败的短信在页面手动触发重新发送时使用
                    for (int j = 0; j < mobile.size(); j++) {
                        if (dt.getMobile().longValue() == mobile.get(j)) {
                            dt.setReqContent(text.get(j));
                            break;
                        }
                    }
                    smsLogLogic.persist(dt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer singleSends(Long smsId, Integer type, Long mobile, String text) {
        Map reqMap = new HashMap();
        reqMap.put("mobile", mobile);
        reqMap.put("text", text);

        int status = GlobalConst.FAIL;
        Integer interFlag = GlobalConst.FAIL;
        String msg = null;

        SmsLog dt = new SmsLog();
        dt.setMobile(mobile);
        dt.setReqContent(text);
        dt.setSmsId(smsId);
        dt.setType(type);
        dt.setCdate(Calendar.getInstance());

        try {
            YunpianRestClient client = new YunpianRestClient(NeuUtils.getProperty(type == GlobalConst.SMS_TYPE_12 ? "yx_smsapikey" : "smsapikey"));
            SmsOperator sysOper = client.getSmsOperator();
            ResultDO<SendSingleSmsInfo> result = sysOper.singleSend(mobile.toString(), text);
            msg = DataUtils.toJson(result);

            Map json = Json.parseMap(msg);
            boolean success = (Boolean) json.get("success");
            if (success) {
                Map map = (Map) json.get("data");
                msg = map.get("msg").toString();
                status = (map.get("code").toString().equals("0") ? GlobalConst.SUCCESS : GlobalConst.FAIL);

                interFlag = GlobalConst.SUCCESS;
            }
        } catch (Exception e) {
            msg += "单个发送短信异常：" + NeuUtils.getStackTraceStr(e);
        } finally {
            dt.setRespContent(msg);
            dt.setStatus(status);
            smsLogLogic.persist(dt);
        }
        return interFlag;// 如果是短信验证码需要返回值
    }

    @Override
    public void reSendSms(List<Long> ids, String userName) {
        Map searchParam = new HashMap();
        searchParam.put("IN_smsLogId", StringUtils.join(ids.toArray(), ","));
        List<SmsLog> rsList = smsLogLogic.findAll(searchParam);

        for (SmsLog dt : rsList) {
            int status = GlobalConst.FAIL;
            String msg = null;
            try {
                YunpianRestClient client = new YunpianRestClient(NeuUtils.getProperty(dt.getType() == GlobalConst.SMS_TYPE_12 ? "yx_smsapikey" : "smsapikey"));
                SmsOperator sysOper = client.getSmsOperator();
                ResultDO<SendSingleSmsInfo> result = sysOper.singleSend(dt.getMobile().toString(), dt.getReqContent());
                msg = DataUtils.toJson(result);

                Map json = Json.parseMap(msg);
                boolean success = (Boolean) json.get("success");
                if (success) {
                    Map map = (Map) Json.parseMap(msg).get("data");
                    msg = map.get("msg").toString();
                    status = (map.get("code").toString().equals("0") ? GlobalConst.SUCCESS : GlobalConst.FAIL);
                }
            } catch (Exception e) {
                msg += "3.重新发送异常：" + NeuUtils.getStackTraceStr(e);
            } finally {
                dt.setMuser(userName);
                dt.setMdate(Calendar.getInstance());
                dt.setReSendCnt(dt.getReSendCnt() == null ? 1 : dt.getReSendCnt() + 1);
                dt.setRespContent(msg);
                dt.setStatus(status);
                smsLogLogic.merge(dt);
            }
        }
    }

}