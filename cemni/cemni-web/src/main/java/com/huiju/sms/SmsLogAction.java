package com.huiju.sms;

import java.util.Calendar;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.DataDict;
import com.huiju.inter.sms.YunpianSmsRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.sms.smslog.entity.SmsLog;
import com.huiju.sms.smslog.logic.SmsLogRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SmsLogAction extends BaseAction<SmsLog, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private SmsLogRemote smsLogLogic;
    @EJB(mappedName = "YunpianSms")
    private YunpianSmsRemote yunpianSms;

    public String init() throws Exception {
        jsPath.add("/js/sms/Q.sms.log.js");

        String[] authorities = { "D_SMSLOG_LIST", "D_SMSLOG_RESENDSMS", "D_SMSLOG_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);

        Object LE_cdate = searchParam.get("LE_cdate");
        if (LE_cdate != null && !StringUtils.isEmpty(LE_cdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_cdate.toString());
            cl.add(Calendar.DATE, 1);
            searchParam.put("LE_cdate", NeuUtils.parseStringFromCalendar(cl));
        }

        Page<SmsLog> page = new Page(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = smsLogLogic.findAll(page, searchParam);
        for (SmsLog dt : page) {
            dt.setTypeName(DataDict.getDictName(DataDict.SMS_TYPE, dt.getType()));
        }
        renderJson(page);
    }

    public void edit() {
        model = smsLogLogic.findById(id);
        model.setTypeName(DataDict.getDictName(DataDict.SMS_TYPE, model.getType()));
        dealJson(true, model);
    }

}