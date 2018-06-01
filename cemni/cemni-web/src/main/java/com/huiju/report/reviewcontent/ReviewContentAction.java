package com.huiju.report.reviewcontent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huiju.afterservice.telvisitrecord.logic.TelVisitRecordRemote;
import com.huiju.inter.posorder.logic.PosOrderRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.reviewcontent.logic.ReviewContentRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReviewContentAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ReviewContentRemote reviewContentLogic;
    @EJB
    private TelVisitRecordRemote telvisitrecordlogic;
    @EJB
    private PosOrderRemote posOrderLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/reviewcontent/Q.report.reviewcontent.js");

        String[] authorities = { "D_REVIEWCONTENT_INIT", "D_REVIEWCONTENT_EXPORT", "D_REVIEWCONTENT_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void query() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsList = reviewContentLogic.report(searchParam, 1);
        renderJson(rsList);
    }

    public void export() {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=reviewcontent.xls");
        HSSFWorkbook wb = null;

        Map searchParam = WebUtils.getParametersStartingWith(request);
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("回访任务内容分析");
            sheet.setDefaultColumnWidth(13);// 默认宽度
            sheet.setColumnWidth(0, 15 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            sheet.setColumnWidth(2, 15 * 256);
            sheet.setColumnWidth(3, 25 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(6, 13 * 256);
            sheet.setColumnWidth(7, 13 * 256);

            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("回访日期");
            row.createCell(1).setCellValue("记录单号");
            row.createCell(2).setCellValue("大区");
            row.createCell(3).setCellValue("门店");
            row.createCell(4).setCellValue("会员卡号");
            row.createCell(5).setCellValue("会员姓名");
            row.createCell(6).setCellValue("回访类型");
            row.createCell(7).setCellValue("任务类型");

            Object EQ_isPos = searchParam.get("EQ_isPos");
            if (EQ_isPos.equals("2")) {// 已消费
                sheet.setColumnWidth(10, 20 * 256);
                sheet.setColumnWidth(11, 20 * 256);
                sheet.setColumnWidth(13, 20 * 256);
                sheet.setColumnWidth(16, 20 * 256);
                sheet.setColumnWidth(21, 40 * 256);
                row.createCell(8).setCellValue("店面服务");
                row.createCell(9).setCellValue("饰品佩戴");
                row.createCell(10).setCellValue("客户建议");
                row.createCell(11).setCellValue("客户咨询");
                row.createCell(12).setCellValue("专业知识");
                row.createCell(13).setCellValue("已告知讯息");
                row.createCell(14).setCellValue("购物环境");
                row.createCell(15).setCellValue("赠品发放");
                row.createCell(16).setCellValue("反馈意见");
                row.createCell(17).setCellValue("佩戴后维护");
                row.createCell(18).setCellValue("赠品满意度");
                row.createCell(19).setCellValue("保养维修");
                row.createCell(20).setCellValue("总体满意度");
                row.createCell(21).setCellValue("备注");
            } else {// 未消费
                sheet.setColumnWidth(18, 40 * 256);
                sheet.setColumnWidth(9, 20 * 256);
                sheet.setColumnWidth(10, 20 * 256);
                sheet.setColumnWidth(11, 20 * 256);
                sheet.setColumnWidth(13, 20 * 256);
                sheet.setColumnWidth(14, 20 * 256);
                sheet.setColumnWidth(15, 20 * 256);
                sheet.setColumnWidth(16, 20 * 256);
                row.createCell(8).setCellValue("店面服务");
                row.createCell(9).setCellValue("客户建议");
                row.createCell(10).setCellValue("客户咨询");
                row.createCell(11).setCellValue("专业知识");
                row.createCell(12).setCellValue("已告知讯息");
                row.createCell(13).setCellValue("购物环境");
                row.createCell(14).setCellValue("反馈意见");
                row.createCell(15).setCellValue("意向产品");
                row.createCell(16).setCellValue("新品推荐");
                row.createCell(17).setCellValue("总体满意度");
                row.createCell(18).setCellValue("备注");
            }

            Map reMap = reviewContentLogic.report(searchParam, 2);
            List<Map> list = (List<Map>) reMap.get("records");
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row1 = sheet.createRow(i + 1);
                Map dt = new HashMap();
                dt = list.get(i);
                row1.createCell(0).setCellValue(dt.get("backtime") == null ? "" : dt.get("backtime").toString().substring(0, 10));
                row1.createCell(1).setCellValue(dt.get("telvisitrecordno") == null ? "" : dt.get("telvisitrecordno").toString());
                row1.createCell(2).setCellValue(dt.get("bigAreaName") == null ? "" : dt.get("bigAreaName").toString());
                row1.createCell(3).setCellValue(dt.get("storeName") == null ? "" : dt.get("storeName").toString());
                row1.createCell(4).setCellValue(dt.get("cardno") == null ? "" : dt.get("cardno").toString());
                row1.createCell(5).setCellValue(dt.get("name") == null ? "" : dt.get("name").toString());
                row1.createCell(6).setCellValue(dt.get("fresh") == null ? "" : dt.get("fresh").toString());
                row1.createCell(7).setCellValue(dt.get("taskType") == null ? "" : dt.get("taskType").toString());

                if (EQ_isPos.equals("2")) {// 已消费
                    row1.createCell(8).setCellValue(dt.get("shopservice") == null ? "" : dt.get("shopservice").toString());
                    row1.createCell(9).setCellValue(dt.get("ornamentwear") == null ? "" : dt.get("ornamentwear").toString());
                    row1.createCell(10).setCellValue(dt.get("khadvice") == null ? "" : dt.get("khadvice").toString());
                    row1.createCell(11).setCellValue(dt.get("khtalk") == null ? "" : dt.get("khtalk").toString());
                    row1.createCell(12).setCellValue(dt.get("professorknow") == null ? "" : dt.get("professorknow").toString());
                    row1.createCell(13).setCellValue(dt.get("infoknowed") == null ? "" : dt.get("infoknowed").toString());
                    row1.createCell(14).setCellValue(dt.get("shopenvi") == null ? "" : dt.get("shopenvi").toString());
                    row1.createCell(15).setCellValue(dt.get("parentgant") == null ? "" : dt.get("parentgant").toString());
                    row1.createCell(16).setCellValue(dt.get("feedadvice") == null ? "" : dt.get("feedadvice").toString());
                    row1.createCell(17).setCellValue(dt.get("wearupdate") == null ? "" : dt.get("wearupdate").toString());
                    row1.createCell(18).setCellValue(dt.get("parentmanyi") == null ? "" : dt.get("parentmanyi").toString());
                    row1.createCell(19).setCellValue(dt.get("careupdate") == null ? "" : dt.get("careupdate").toString());
                    row1.createCell(20).setCellValue(null == dt.get("satisfaction") ? "" : dt.get("satisfaction").toString());
                    row1.createCell(21).setCellValue(dt.get("saleremark") == null ? "" : dt.get("saleremark").toString());
                } else {// 未消费
                    row1.createCell(8).setCellValue(dt.get("notshopservice") == null ? "" : dt.get("notshopservice").toString());
                    row1.createCell(9).setCellValue(dt.get("notkhadvice") == null ? "" : dt.get("notkhadvice").toString());
                    row1.createCell(10).setCellValue(dt.get("notkhtalk") == null ? "" : dt.get("notkhtalk").toString());
                    row1.createCell(11).setCellValue(dt.get("notinfoknowed") == null ? "" : dt.get("notinfoknowed").toString());
                    row1.createCell(12).setCellValue(dt.get("notprofessorknow") == null ? "" : dt.get("notprofessorknow").toString());
                    row1.createCell(13).setCellValue(dt.get("notintentioncp") == null ? "" : dt.get("notintentioncp").toString());
                    row1.createCell(14).setCellValue(dt.get("notshopenvi") == null ? "" : dt.get("notshopenvi").toString());
                    row1.createCell(15).setCellValue(dt.get("notfeedadvice") == null ? "" : dt.get("notfeedadvice").toString());
                    row1.createCell(16).setCellValue(dt.get("notnewrecoment") == null ? "" : dt.get("notnewrecoment").toString());
                    row1.createCell(17).setCellValue(dt.get("notsatisfaction") == null ? "" : dt.get("notsatisfaction").toString());
                    row1.createCell(18).setCellValue(dt.get("notsaleremark") == null ? "" : dt.get("notsaleremark").toString());
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