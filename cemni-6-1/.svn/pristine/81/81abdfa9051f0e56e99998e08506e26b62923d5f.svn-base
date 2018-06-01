package com.huiju.report.issuetrack;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.issuetrack.logic.IssueTrackRemote;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IssueTrackAction extends BaseAction<PosOrder, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private IssueTrackRemote issuetrackLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/issuetrack/Q.report.issuetrack.js");

        String[] authorities = { "D_ISSUETRACK_LIST", "D_ISSUETRACK_EXPORT", "D_ISSUETRACK_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void query() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.put("start", start);
        searchParam.put("limit", limit);
        Map rsMap = issuetrackLogic.Query(searchParam);
        renderJson(rsMap);
    }

    // 导出
    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=Promotional.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("促销品发放追踪");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度
            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            sheet.setDefaultColumnStyle(5, timeCellStyle);
            sheet.setColumnWidth(15, 40 * 256);
            sheet.setColumnWidth(0, 20 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            sheet.setColumnWidth(4, 25 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 20 * 256);
            sheet.setColumnWidth(10, 20 * 256);
            sheet.setColumnWidth(11, 20 * 256);
            sheet.setColumnWidth(14, 20 * 256);

            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("pos单号");
            row0.createCell(1).setCellValue("商品编码");
            row0.createCell(2).setCellValue("商品名称");
            row0.createCell(3).setCellValue("商品条形码");
            row0.createCell(4).setCellValue("是否有效（0：否、1：是）");
            row0.createCell(5).setCellValue("领取日期");
            row0.createCell(6).setCellValue("门店名称");
            row0.createCell(7).setCellValue("会员姓名");
            row0.createCell(8).setCellValue("手机号码");
            row0.createCell(9).setCellValue("会员卡号");
            row0.createCell(10).setCellValue("系列大类");
            row0.createCell(11).setCellValue("系列分类");
            row0.createCell(12).setCellValue("销售金额");
            row0.createCell(13).setCellValue("珠宝折算额");
            row0.createCell(14).setCellValue("活动编号");
            row0.createCell(15).setCellValue("活动主题");

            List<Map> rsList = issuetrackLogic.export(WebUtils.getParametersStartingWith(request));
            if (!CollectionUtils.isEmpty(rsList)) {
                for (int i = 0; i < rsList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map map = rsList.get(i);
                    row.createCell(0).setCellValue(map.get("posno").toString());
                    row.createCell(1).setCellValue(map.get("goodsno") == null ? "" : map.get("goodsno").toString());
                    row.createCell(2).setCellValue(map.get("goodsname") == null ? "" : map.get("goodsname").toString());
                    row.createCell(3).setCellValue(map.get("goodsbar") == null ? "" : map.get("goodsbar").toString());
                    row.createCell(4).setCellValue(map.get("flargess") == null ? "" : map.get("flargess").toString());
                    row.createCell(5).setCellValue(map.get("posbilldate").toString());
                    row.createCell(6).setCellValue(map.get("storename") == null ? "" : map.get("storename").toString());
                    row.createCell(7).setCellValue(map.get("cardname").toString());
                    row.createCell(8).setCellValue(map.get("mobile").toString());
                    row.createCell(9).setCellValue(map.get("cardno").toString());
                    row.createCell(10).setCellValue(map.get("goodsClassHighestNo") == null ? "" : map.get("goodsClassHighestNo").toString());
                    row.createCell(11).setCellValue(map.get("seriestypename") == null ? "" : map.get("seriestypename").toString());
                    row.createCell(12).setCellValue(map.get("actualsaleamount") == null ? "" : map.get("actualsaleamount").toString());
                    row.createCell(13).setCellValue(map.get("jeweldiscountamount") == null ? "" : map.get("actualsaleamount").toString());
                    row.createCell(14).setCellValue(map.get("actno") == null ? "" : map.get("actno").toString());
                    row.createCell(15).setCellValue(map.get("subject") == null ? "" : map.get("subject").toString());
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