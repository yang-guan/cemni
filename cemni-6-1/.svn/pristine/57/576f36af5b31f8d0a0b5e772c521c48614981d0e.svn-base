package com.huiju.sms;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.huiju.actment.activity.entity.Activity;
import com.huiju.actment.activity.logic.ActivityRemote;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.console.dict.entity.Dict;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.json.Json;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.sms.objcondition.entity.ObjCondition;
import com.huiju.sms.objcondition.logic.ObjConditionRemote;
import com.huiju.sms.sms.entity.ObjCust;
import com.huiju.sms.sms.entity.Sms;
import com.huiju.sms.sms.entity.SmsParamVar;
import com.huiju.sms.sms.entity.SmsVar;
import com.huiju.sms.sms.entity.TypeCond;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.utils.NeuUtils;
import com.yunpian.sdk.common.Config;

/**
 * 短信管理
 * 
 * @author：yuhb
 * @date：2016年12月28日 上午10:37:16
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SmsAction extends BaseAction<Sms, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private SmsRemote smsLogic;
    @EJB
    private ObjConditionRemote objCondLogic;
    @EJB
    private IndividCustRemote individCustLogic;
    @EJB
    private ActivityRemote activityLogic;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @EJB
    private FileInfoRemote fileInfoLogic;

    private File file;
    private String fileName;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String init() throws Exception {
        jsPath.add("/js/sms/Q.sms.js");

        String[] authorities = { "D_SMS_LIST", "D_SMS_ADD", "D_SMS_EDIT", "D_SMS_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Page<Sms> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = smsLogic.findAll(page, searchParam);

        String sendTimeStr;
        for (Sms dt : page) {
            sendTimeStr = "";
            if (dt.getSendType() == GlobalConst.SMS_SENDTYPE_FIXED) {
                if (dt.getTimedSend() != null) {
                    sendTimeStr = "：" + NeuUtils.parseStringFromCalendar(dt.getTimedSend());
                }
            } else if (dt.getSendType() == GlobalConst.SMS_SENDTYPE_CYCLE) {
                Integer sms_cycle = DataDict.SMS_SEND_CYCLE_TYPE;
                if (dt.getType() == GlobalConst.SMS_TYPE_3) {// 客情维护
                    sms_cycle = DataDict.SMS_CYCLE_CUSTREL;
                } else if (dt.getType() == GlobalConst.SMS_TYPE_7) {// 保养短信
                    sms_cycle = DataDict.SMS_CYCLE_MAINTAIN;
                }
                sendTimeStr = "：" + DataDict.getDictName(sms_cycle, dt.getCycleType());
            }
            dt.setSendTimeStr(DataDict.getDictName(DataDict.SMS_SEND_TYPE, dt.getSendType()) + sendTimeStr);
            dt.setTypeName(DataDict.getDictName(DataDict.SMS_TYPE, dt.getType()));
        }
        renderJson(page);
    }

    // 短信类型-下拉框
    public void selSmsTypeStore() {
        List<Dict> rsList = null;
        if (request.getParameter("bntType").equals("add")) {// 新增时只展示可以增加的短信类型
            rsList = smsLogic.selSmsTypeStore();
        } else {
            rsList = new ArrayList<Dict>();
            List<Dict> tempList = DataDict.getSubDict(DataDict.SMS_TYPE);
            // 过滤短信验证码：无法clone只能重新声明list执行add，使用remove会删除缓存数据
            for (Dict dt : tempList) {
                if (dt.getValue() == GlobalConst.SMS_TYPE_13 || dt.getValue() == GlobalConst.SMS_TYPE_14) {
                    // TODO
                } else {
                    rsList.add(dt);
                }
            }
        }
        renderJson(rsList);
    }

    // 可变变量-下拉框
    public void selParamVar() {
        List<SmsParamVar> rsList = smsLogic.selParamVar(model.getType());
        renderJson(rsList);
    }

    // 对象条件-比较字段-下拉框
    public void selTypeCond() {
        List<TypeCond> rsList = smsLogic.selTypeCond(model.getType());
        renderJson(rsList);
    }

    // 发送条件
    public void qryObjCond() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        List<ObjCondition> rsList = objCondLogic.findAll(searchParam);
        for (ObjCondition dt : rsList) {
            dt.setCompValName(DataDict.getDictName(dt.getTypeCond().getCompColumVal(), dt.getCompVal()));
        }
        renderJson(rsList);
    }

    // 短信-发送对象
    public void qryObjCust() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        List rsList = smsLogic.qryObjCust(Long.parseLong(searchParam.get("EQ_sms_smsId").toString()));
        renderJson(rsList);
    }

    // 短信-发送对象-精确查询“会员”
    public void exactQryObjCust() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        List rsList = individCustLogic.findAll(searchParam);
        dealJson(true, rsList);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void save() {
        String tpl_content = setOneToMany();

        model.setCuser(WebUtils.getUserName());
        model.setCdate(Calendar.getInstance());
        model.setMdate(Calendar.getInstance());
        model.setMuser(WebUtils.getUserName());

        // 调用云片网：生成短信模版
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", NeuUtils.getProperty(model.getType() == GlobalConst.SMS_TYPE_12 ? "yx_smsapikey" : "smsapikey"));
        params.put("tpl_content", "【千年珠宝】" + tpl_content);
        Map rsYunpianMap = this.yunpianPost(Config.URI_ADD_TPL_SMS, params);

        Integer statusCode = (Integer) rsYunpianMap.get("statusCode");
        String msg = rsYunpianMap.get("msg").toString();

        Map json = Json.parseMap(msg);
        if (statusCode == HttpStatus.SC_OK) {// 成功
            model.setTplId(Long.parseLong(json.get("tpl_id").toString()));
            smsLogic.persist(model);
            dealJson(true);
        } else {
            dealJson(false, "调用云片网接口失败：" + msg);
        }
    }

    public void edit() {
        model = smsLogic.findById(id);
        if (model.getSendType() == GlobalConst.SMS_SENDTYPE_FIXED) {
            if (model.getTimedSend() != null) {
                model.setTimedSendStr(NeuUtils.parseStringFromCalendar(model.getTimedSend()));// 时间特殊处理
            }
        }

        // 活动信息
        Long activityId = model.getActivityId();
        if (activityId != null) {
            Activity dt = activityLogic.findById(activityId);
            if (dt != null) {
                model.setActivityTheme(dt.getActivityTheme());
            }
        }
        dealJson(true, DataUtils.toJson(model));
    }

    public void update() {
        String tpl_content = setOneToMany();

        // 回填信息
        Sms sms = smsLogic.findById(model.getSmsId());
        model.setCdate(sms.getCdate());
        model.setCuser(sms.getCuser());
        model.setMdate(Calendar.getInstance());
        model.setMuser(WebUtils.getUserName());
        model.setTplId(sms.getTplId());

        // 调用云片网：修改短信模版
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", NeuUtils.getProperty(model.getType() == GlobalConst.SMS_TYPE_12 ? "yx_smsapikey" : "smsapikey"));
        params.put("tpl_id", sms.getTplId().toString());
        params.put("tpl_content", "【千年珠宝】" + tpl_content);
        Map rsYunpianMap = this.yunpianPost(Config.URI_UPD_TPL_SMS, params);

        Integer statusCode = (Integer) rsYunpianMap.get("statusCode");
        String msg = rsYunpianMap.get("msg").toString();

        if (statusCode == HttpStatus.SC_OK || msg.indexOf("无需提交") > -1 || msg.indexOf("模板内容已存在") > -1) {
            smsLogic.merge(model);
            dealJson(true);
        } else {
            dealJson(false, "调用云片网接口失败：" + msg);
        }
    }

    private String setOneToMany() {
        // 发送条件
        List<ObjCondition> objCondtionList = model.getObjCondtionList();
        if (!CollectionUtils.isEmpty(objCondtionList)) {
            for (ObjCondition dt : objCondtionList) {
                dt.setSms(model);
            }
        }
        // 发送对象
        List<ObjCust> objCustList = model.getObjCustList();
        if (!CollectionUtils.isEmpty(objCustList)) {
            for (ObjCust dt : objCustList) {
                dt.setSms(model);
            }
        }

        // 设置定时任务“类型、发送时间”
        if (model.getSendType() == null) {
            if (model.getType() == GlobalConst.SMS_TYPE_1 || model.getType() == GlobalConst.SMS_TYPE_11) {
                model.setSendType(GlobalConst.SMS_SENDTYPE_FIXED);
            } else {
                model.setSendType(GlobalConst.SMS_SENDTYPE_NOW);
            }
        } else if (model.getSendType() == GlobalConst.SMS_SENDTYPE_FIXED) {
            model.setTimedSend(NeuUtils.parseCalendar(model.getTimedSendStr()));
        }

        // 已选变量
        String tpl_content = model.getContent();
        List<SmsVar> smsVarList = new ArrayList<SmsVar>();
        List<SmsParamVar> rsList = smsLogic.selParamVar(model.getType());
        Pattern p = Pattern.compile("#(.*?)#");
        Matcher m = p.matcher(model.getContent());
        while (m.find()) {
            for (SmsParamVar dt : rsList) {
                if (m.group(1).equals(dt.getName())) {
                    SmsVar smsVar = new SmsVar();
                    smsVar.setParamVarId(dt.getParamVarId());
                    smsVar.setSms(model);
                    smsVarList.add(smsVar);

                    tpl_content = tpl_content.replaceAll(dt.getName(), dt.getColName());// 把列名替换成表列名（云片网模版中使用）
                }
            }
        }
        model.setSmsVarList(smsVarList);
        return tpl_content;
    }

    /**
     * 发送短信模版到云片网
     */
    private Map yunpianPost(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        Map rsMap = new HashMap();
        int statusCode = 0;
        String msg = null;
        try {
            HttpPost method = new HttpPost(url);
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> param : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                paramList.add(pair);
            }
            method.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));

            response = client.execute(method);
            statusCode = response.getStatusLine().getStatusCode();
            msg = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            msg = e.toString();
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(client);
            HttpClientUtils.closeQuietly(response);
        }
        rsMap.put("statusCode", statusCode);
        rsMap.put("msg", msg);
        return rsMap;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 发送对象-excel导入
    public void uploadExcel() {
        Map<String, Object> retMap = null;
        try {
            FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
            retMap = smsLogic.uploadExcel(fileInfoLogic.convert(fileInfo).getFile());
        } catch (Exception e) {
            retMap = new HashMap<String, Object>();
            retMap.put("success", false);
            retMap.put("msg", e.getMessage());
        }
        renderHtml(DataUtils.toJson(retMap));
    }

}