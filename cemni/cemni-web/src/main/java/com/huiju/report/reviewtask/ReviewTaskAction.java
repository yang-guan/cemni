package com.huiju.report.reviewtask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.reviewtask.logic.ReviewTaskRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReviewTaskAction extends BaseAction<TelVisitRecord, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ReviewTaskRemote reviewTasklogic;

    public String init() throws Exception {
        jsPath.add("/js/report/reviewtask/Q.report.task.js");

        String[] authorities = { "D_REVIEW_INIT", "D_REVIEW_EXPORT", "D_REVIEW_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void query() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        if (start == 0) {
            limit = 19;
        } else {
            start = start - 1;
        }
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsMap = reviewTasklogic.report(searchParam);
        renderJson(rsMap);
    }

    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=ReviewTask.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("回访任务分析汇总表");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));
            sheet.setDefaultColumnStyle(9, timeCellStyle);
            sheet.setColumnWidth(9, 13 * 256);

            sheet.setColumnWidth(0, 18 * 256);
            sheet.setColumnWidth(1, 30 * 256);
            HSSFRow row = sheet.createRow(0);

            row.createCell(0).setCellValue("回访任务单号");
            row.createCell(1).setCellValue("回访门店");
            row.createCell(2).setCellValue("回访方式");
            row.createCell(3).setCellValue("回访名称");
            row.createCell(4).setCellValue("状态");
            row.createCell(5).setCellValue("未回访数");
            row.createCell(6).setCellValue("已回访数");
            row.createCell(7).setCellValue("总回访数");
            row.createCell(8).setCellValue("回访成功率");
            row.createCell(9).setCellValue("创建日期");

            Map searchParams = WebUtils.getParametersStartingWith(request);
            List<Map> rsList = reviewTasklogic.qryForExcel(searchParams);
            for (int i = 0; i < rsList.size(); i++) {
                HSSFRow r = sheet.createRow(i + 1);
                Map map = new HashMap();
                map = rsList.get(i);
                r.createCell(0).setCellValue(String.valueOf(map.get("telvisitNo")));
                r.createCell(1).setCellValue(String.valueOf(map.get("storeName")));
                r.createCell(2).setCellValue(String.valueOf(map.get("backfsName")));
                r.createCell(3).setCellValue(String.valueOf(map.get("tasktypeName")));
                r.createCell(4).setCellValue(String.valueOf(map.get("visitSatus")));
                r.createCell(5).setCellValue(map.get("unhasCnt").toString());
                r.createCell(6).setCellValue(map.get("hasCnt").toString());
                r.createCell(7).setCellValue(map.get("totalCnt").toString());
                r.createCell(8).setCellValue(map.get("succPercent").toString());
                r.createCell(9).setCellValue(String.valueOf(map.get("cdate")));
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