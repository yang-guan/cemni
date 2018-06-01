package com.huiju.interlog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
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

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.inter.interLog.entity.InterLog;
import com.huiju.inter.interLog.logic.InterLogRemote;
import com.huiju.module.data.Page;
import com.huiju.module.json.Json;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.DESUtils;
import com.huiju.utils.NeuUtils;

/**
 * 接口日志
 * 
 * @author：WangYuanJun
 * @date：2017年1月13日 上午10:23:27
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class InterLogAction extends BaseAction<InterLog, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private InterLogRemote appLogic;
    @EJB
    private IndividCustRemote individCustLogic;

    public String init() throws Exception {
        jsPath.add("/js/interLog/Q.interLog.js");

        String[] authorities = { "D_INTERLOG_LIST", "D_INTERLOG_SEARCH", "D_INTERLOG_RESENDINTER" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);

        Object LE_reqTime = searchParam.get("LE_reqTime");
        if (LE_reqTime != null && !StringUtils.isEmpty(LE_reqTime.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_reqTime.toString());
            cl.add(Calendar.DATE, 1);
            searchParam.put("LE_reqTime", NeuUtils.parseStringFromCalendar(cl));
        }
        Page<InterLog> page = new Page<InterLog>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = appLogic.findAll(page, searchParam);
        for (InterLog interLog : page) {
            interLog.setSrcName(DataDict.getDictName(DataDict.SYS_SCR, interLog.getSrc()));
        }
        renderJson(page);
    }

    public void edit() {
        model = appLogic.findById(id);
        dealJson(true, model);
    }

    /**
     * 接口重发
     * 
     * @author：yuhb
     * @date：2017年3月14日 下午6:30:03
     */
    public void rePost() {
        InterLog dt = appLogic.findById(model.getInterfaceLogId());
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        int flag = GlobalConst.FAIL;
        String msg = "";
        String ncUrl = NeuUtils.getProperty("crm2nc");
        String reqContent = dt.getReqContent();

        // 非nc时加密传输
        if (!ncUrl.equals(dt.getUrl())) {
            reqContent = DESUtils.getEncString(reqContent);
        }

        try {
            HttpPost method = new HttpPost(dt.getUrl());
            method.setEntity(new StringEntity(reqContent, ContentType.APPLICATION_JSON));

            response = client.execute(method);
            HttpEntity entity = response.getEntity();

            msg = (entity == null ? null : EntityUtils.toString(entity));
            Map rsMap = Json.parseMap(msg);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                flag = Integer.parseInt(rsMap.get("flag").toString());
            }
            // 回填ncNo
            if (ncUrl.equals(dt.getUrl()) && flag == GlobalConst.SUCCESS) {
                Map msgMap = Json.parseMap(reqContent);
                String action = msgMap.get("action").toString();
                String cardNo = msgMap.get("cardNo").toString();

                Map rsdata = (Map) rsMap.get("rsdata");
                Object resp_ncNo = rsdata.get("ncNo");

                if (msgMap.get("ncNo") == null && "ncmember".equals(action) && resp_ncNo != null) {
                    Map searchParam = new HashMap();
                    searchParam.put("EQ_cardNo", cardNo);
                    IndividCust cust = individCustLogic.find(searchParam);

                    cust.setNcNo(resp_ncNo.toString());
                    individCustLogic.merge(cust);
                }
            }
        } catch (Exception e) {
            msg = NeuUtils.getStackTraceStr(e);
        } finally {
            HttpClientUtils.closeQuietly(client);
            HttpClientUtils.closeQuietly(response);

            dt.setStatus(flag);
            dt.setRespContent(msg);
            dt.setMuser(WebUtils.getUserName());
            dt.setMdate(Calendar.getInstance());
            appLogic.merge(dt);
        }
        dealJson(true);
    }

}