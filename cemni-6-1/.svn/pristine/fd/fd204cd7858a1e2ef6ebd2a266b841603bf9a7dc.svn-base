package com.huiju.report.salestarget;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.salestarget.logic.SalesTargetRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SalesTargetAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private SalesTargetRemote salesTargetlogic;

    public String init() throws Exception {
        jsPath.add("/js/report/salestarget/Q.report.salestarget.js");
        String[] authorities = { "D_SALESTARGET_LIST", "D_SALESTARGET_EXPORT", "D_SALESTARGET_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void query() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsMap = salesTargetlogic.report(searchParam, 1);
        renderJson(rsMap);
    }

    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=salesTargetReport.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("销售指标完成情况百分比");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(12);// 默认宽度

            CellStyle headStyle = wb.createCellStyle();
            headStyle.setDataFormat(wb.createDataFormat().getFormat("@"));
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            sheet.setColumnWidth(2, 25 * 256);
            HSSFRow row0 = sheet.createRow(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));
            Cell cell_0_4 = row0.createCell(4);
            cell_0_4.setCellValue("本期");
            cell_0_4.setCellStyle(headStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));
            Cell cell_0_7 = row0.createCell(7);
            cell_0_7.setCellValue("同期");
            cell_0_7.setCellStyle(headStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 11));
            Cell cell_0_10 = row0.createCell(10);
            cell_0_10.setCellValue("差额");
            cell_0_10.setCellStyle(headStyle);

            HSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("大区");
            row1.createCell(1).setCellValue("区域");
            row1.createCell(2).setCellValue("门店");
            row1.createCell(3).setCellValue("门店性质");
            row1.createCell(4).setCellValue("销售指标");
            row1.createCell(5).setCellValue("珠宝折算额");
            row1.createCell(6).setCellValue("完成率");
            row1.createCell(7).setCellValue("销售指标");
            row1.createCell(8).setCellValue("珠宝折算额");
            row1.createCell(9).setCellValue("完成率");
            row1.createCell(10).setCellValue("折算增长额");
            row1.createCell(11).setCellValue("折算增长率");
            row1.createCell(12).setCellValue("排名");

            Map searchParam = WebUtils.getParametersStartingWith(request);
            Map reMap = salesTargetlogic.report(searchParam, 2);
            List<Map> rsList = (List<Map>) reMap.get("records");
            for (int i = 0; i < rsList.size(); i++) {
                HSSFRow r = sheet.createRow(i + 2);
                Map dt = rsList.get(i);
                r.createCell(0).setCellValue(dt.get("bigAreaName").toString());
                r.createCell(1).setCellValue(dt.get("areaName").toString());
                r.createCell(2).setCellValue(dt.get("name").toString());
                r.createCell(3).setCellValue(dt.get("attrName").toString());
                r.createCell(4).setCellValue(dt.get("bzj").toString());
                r.createCell(5).setCellValue(dt.get("bje").toString());
                r.createCell(6).setCellValue(dt.get("bpc").toString());
                r.createCell(7).setCellValue(dt.get("czj").toString());
                r.createCell(8).setCellValue(dt.get("cje").toString());
                r.createCell(9).setCellValue(dt.get("cpc").toString());
                r.createCell(10).setCellValue(dt.get("jje").toString());
                r.createCell(11).setCellValue(dt.get("jpc").toString());
                r.createCell(12).setCellValue(dt.get("rank").toString());
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