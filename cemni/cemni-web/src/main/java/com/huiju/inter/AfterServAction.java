package com.huiju.inter;

import java.util.Map;

import javax.ejb.EJB;

import com.huiju.inter.afterserv.logic.AfterServRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.log.annotation.Logging;
import com.huiju.module.log.annotation.Logging.LogType;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AfterServAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "AfterServBean")
    private AfterServRemote appLogic;

    @Logging(module = "CONSOLE", action = "getJson", message = "售后服务-首页-list", type = LogType.SYSTEM)
    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        Page page = new Page(start, limit, sort, dir);
        page = appLogic.findAll(page, searchParam);
        renderJson(DataUtils.toJson(page));
    }

}
