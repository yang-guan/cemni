package com.huiju.inter.httpclient;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.eao.InterLogEaoLocal;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.module.json.Json;
import com.huiju.utils.DESUtils;
import com.huiju.utils.NeuUtils;

/**
 * http请求工具类
 * 
 * @author：yuhb
 * @date：2017年1月9日 下午10:29:15
 */
@Stateless(mappedName = "HttpClientBean")
@SuppressWarnings({ "rawtypes" })
public class HttpClientBean implements HttpClientRemote {
    @EJB
    private InterLogEaoLocal interLogEao;

    @Override
    public Map post(String url, String crmClassMethod, int src, String json) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        Calendar reqTime = Calendar.getInstance();

        Map rsMap = new HashMap();
        Integer flag = GlobalConst.FAIL;
        String msg = null;

        try {
            HttpPost method = new HttpPost(url);
            method.setEntity(new StringEntity((url.equals(NeuUtils.getProperty("crm2nc")) ? json : DESUtils.getEncString(json)), ContentType.APPLICATION_JSON));

            response = client.execute(method);
            HttpEntity entity = response.getEntity();

            // 正常返回的日志
            msg = (entity == null ? null : EntityUtils.toString(entity));
            rsMap = Json.parseMap(msg);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                flag = Integer.parseInt(rsMap.get("flag").toString());// 转换错误进入catch语句
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            HttpClientUtils.closeQuietly(client);
            HttpClientUtils.closeQuietly(response);

            // 接口日志
            InterLog interLog = new InterLog();
            interLog.setUrl(url);
            interLog.setCrmClassMethod(crmClassMethod + "：" + url);
            interLog.setReqTime(reqTime);
            interLog.setReqContent(json);
            interLog.setSrc(src);
            interLog.setStatus(flag);
            interLog.setRespContent(msg);
            interLog.setRespTime(Calendar.getInstance());
            interLog = interLogEao.persist(interLog);
        }
        return rsMap;
    }

}