package com.huiju.servlet;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.huiju.console.dict.logic.DictRemote;
import com.huiju.contract.logic.ContractRemote;
import com.huiju.module.log.Log;
import com.huiju.module.log.Logs;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.utils.NeuUtils;

/**
 * 启动加载的servlet
 * 
 * @author：yuhb
 * @cl：2016年12月08日 上午10:28:20
 */
@WebServlet(urlPatterns = { "/StartupServ" }, loadOnStartup = 1)
public class StartupServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log log = Logs.getLog(StartupServ.class);

    @EJB(mappedName = "DictBean")
    private DictRemote dictLogic;

    @EJB(mappedName = "SmsBean")
    private SmsRemote smsLogic;

    @EJB(mappedName = "ContractBean")
    private ContractRemote contractLogic;

    public void init() throws ServletException {
        log.debug("字典表-加载开始...");
        dictLogic.loadDict();
        log.debug("字典表-加载结束！");

        log.debug("application.properties-加载开始...");
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/application.properties");
            NeuUtils.props.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("application.properties-加载结束！");

        // 定时任务
        if ("1".equals(NeuUtils.getProperty("istimed"))) {
            Calendar cl = Calendar.getInstance();
            long timeInMillis = cl.getTimeInMillis();
            cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt(NeuUtils.getProperty("sms_hour")));
            cl.set(Calendar.MINUTE, Integer.parseInt(NeuUtils.getProperty("sms_minute")));
            cl.set(Calendar.SECOND, Integer.parseInt(NeuUtils.getProperty("sms_second")));
            // 发布时间大于10点时设置第二天开始执行
            if (timeInMillis > cl.getTimeInMillis()) {
                cl.add(Calendar.DAY_OF_MONTH, 1);
            }
            new Timer().schedule(new TimerTask() {
                public void run() {
                    smsLogic.scanSms();
                    contractLogic.contractWarning();
                }
            }, cl.getTime(), 24 * 60 * 60 * 1000);
        }
    }

}