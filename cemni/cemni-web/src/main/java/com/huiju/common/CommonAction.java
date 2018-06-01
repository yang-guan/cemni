package com.huiju.common;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.area.logic.AreaRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

/**
 * 公共模块
 * 
 * @author：yuhb
 * @date：2016年11月23日 下午4:10:48
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private AreaRemote areaLogic;

    /**
     * 地域
     */
    public void selArea() throws Exception {
        Map param = WebUtils.getParametersStartingWith(request);
        param.put("EQ_status", "1");
        String[] sort = { "areacode,asc" };

        List rsList = areaLogic.findAll(param, sort);
        renderJson(rsList);
    }

    /**
     * excel模版下载
     */
    public void downLoadExcelTpl() throws Exception {
        String fileName = request.getParameter("fileName");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=" + fileName);

        FileInputStream in = new FileInputStream(NeuUtils.getProperty("cemni_tpl_dir") + request.getParameter("fileName"));
        // FileInputStream in = new FileInputStream("E:/NeusoftWorkspaces/cemni/cemni-web/src/main/webapp/template/" + fileName);

        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

}