package com.huiju.report.fanstrans;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.fanstrans.logic.FansTransRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FansTransAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private FansTransRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/fanstrans/Q.report.fanstrans.js");

        String[] authorities = { "D_FANSTRANS_LIST", "D_FANSTRANS_EXPORT", "D_FANSTRANS_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsMap = appLogic.report(searchParam);
        renderJson(rsMap);
    }

    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=FansTransRatio.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("fans转化率");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));

            sheet.setColumnWidth(0, 30 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 15 * 256);

            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("归属门店");
            row0.createCell(1).setCellValue("门店属性");
            row0.createCell(2).setCellValue("总人数");
            row0.createCell(3).setCellValue("Fans人数");
            row0.createCell(4).setCellValue("转化人数");
            row0.createCell(5).setCellValue("转化率");

            List<Map> rsList = appLogic.qryFansTrans(WebUtils.getParametersStartingWith(request));
            if (!CollectionUtils.isEmpty(rsList)) {
                for (int i = 0; i < rsList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map map = rsList.get(i);
                    row.createCell(0).setCellValue(map.get("belongstorename") == null ? "" : map.get("belongstorename").toString());
                    row.createCell(1).setCellValue(map.get("attr") == null ? "" : map.get("attr").toString());
                    row.createCell(2).setCellValue(map.get("totalcnt") == null ? "" : map.get("totalcnt").toString());
                    row.createCell(3).setCellValue(map.get("fans") == null ? "" : map.get("fans").toString());
                    row.createCell(4).setCellValue(map.get("trans") == null ? "" : map.get("trans").toString());
                    row.createCell(5).setCellValue(map.get("transRatio") == null ? "" : map.get("transRatio").toString());
                }
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}