package com.huiju.report.activity;

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
import com.huiju.report.activity.logic.RepActivityRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RepActivityAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private RepActivityRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/activity/Q.report.activity.js");

        String[] authorities = { "D_REPACTIVITY_LIST", "D_REPACTIVITY_EXPORT", "D_REPACTIVITY_SEARCH" };
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
        response.setHeader("content-disposition", "attachment;filename=ActSummary.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("活动汇总查询");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));
            sheet.setDefaultColumnStyle(5, timeCellStyle);
            sheet.setDefaultColumnStyle(6, timeCellStyle);

            sheet.setColumnWidth(0, 20 * 256);
            sheet.setColumnWidth(1, 30 * 256);
            sheet.setColumnWidth(2, 20 * 256);
            sheet.setColumnWidth(3, 20 * 256);
            sheet.setColumnWidth(4, 30 * 256);
            sheet.setColumnWidth(5, 15 * 256);
            sheet.setColumnWidth(6, 15 * 256);

            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("活动单号");
            row0.createCell(1).setCellValue("活动主题");
            row0.createCell(2).setCellValue("活动类型");
            row0.createCell(3).setCellValue("活动形式");
            row0.createCell(4).setCellValue("发起部门（门店）");
            row0.createCell(5).setCellValue("开始日期");
            row0.createCell(6).setCellValue("结束日期");
            row0.createCell(7).setCellValue("费用");
            row0.createCell(8).setCellValue("参与人数");
            row0.createCell(9).setCellValue("邀请人数");
            row0.createCell(10).setCellValue("累计珠宝折算额");
            row0.createCell(11).setCellValue("活动产出总金额");
            row0.createCell(12).setCellValue("费用率");
            row0.createCell(13).setCellValue("消费人数");

            List<Map> rsList = appLogic.qryRepAct(WebUtils.getParametersStartingWith(request));
            if (!CollectionUtils.isEmpty(rsList)) {
                for (int i = 0; i < rsList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map map = rsList.get(i);
                    row.createCell(0).setCellValue(map.get("activityNo").toString());
                    row.createCell(1).setCellValue(map.get("activityTheme").toString());
                    row.createCell(2).setCellValue(map.get("activityTypeName").toString());
                    row.createCell(3).setCellValue(map.get("activityFormName").toString());
                    row.createCell(4).setCellValue(map.get("orgName").toString());
                    row.createCell(5).setCellValue(map.get("beginTime").toString());
                    row.createCell(6).setCellValue(map.get("endTime").toString());
                    row.createCell(7).setCellValue(map.get("auditCost") == null ? "" : map.get("auditCost").toString());
                    row.createCell(8).setCellValue(map.get("joinCount").toString());
                    row.createCell(9).setCellValue(map.get("totalCount").toString());
                    row.createCell(10).setCellValue(map.get("jewelDiscountAmount").toString());
                    row.createCell(11).setCellValue(map.get("actualCost") == null ? "" : map.get("actualCost").toString());
                    row.createCell(12).setCellValue(map.get("costPercent") == null ? "" : map.get("costPercent").toString());
                    row.createCell(13).setCellValue(map.get("xfCount").toString());
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