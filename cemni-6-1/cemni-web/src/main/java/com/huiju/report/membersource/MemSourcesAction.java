package com.huiju.report.membersource;

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
import com.huiju.report.membersource.logic.MemSourcesRemote;;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MemSourcesAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private MemSourcesRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/membersource/Q.report.membersources.js");

        String[] authorities = { "D_MEMBERSOURCES_LIST", "D_MEMBERSOURCES_EXPORT", "D_MEMBERSOURCES_SEARCH" };
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
        response.setHeader("content-disposition", "attachment;filename=MemberSources.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("会员来源");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));

            sheet.setColumnWidth(0, 30 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 15 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(5, 15 * 256);
            sheet.setColumnWidth(6, 15 * 256);

            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("归属门店");
            row0.createCell(1).setCellValue("创建人数");
            row0.createCell(2).setCellValue("消费人数");
            row0.createCell(3).setCellValue("NC");
            row0.createCell(4).setCellValue("NC占比");
            row0.createCell(5).setCellValue("CRM");
            row0.createCell(6).setCellValue("CRM占比");
            row0.createCell(7).setCellValue("微信");
            row0.createCell(8).setCellValue("微信占比");
            row0.createCell(9).setCellValue("耀我网");
            row0.createCell(10).setCellValue("耀我网占比");
            row0.createCell(11).setCellValue("市场活动");
            row0.createCell(12).setCellValue("市场活动占比");
            row0.createCell(13).setCellValue("异业联盟");
            row0.createCell(14).setCellValue("异业联盟占比");
            row0.createCell(15).setCellValue("客户推荐");
            row0.createCell(16).setCellValue("客户推荐占比");
            row0.createCell(17).setCellValue("其他");
            row0.createCell(18).setCellValue("其他占比");
            
            List<Map> rsList = appLogic.qryMemSour(WebUtils.getParametersStartingWith(request));
            if (!CollectionUtils.isEmpty(rsList)) {
                for (int i = 0; i < rsList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map map = rsList.get(i);
                    row.createCell(0).setCellValue(map.get("belongstorename") == null ? "" : map.get("belongstorename").toString());
                    row.createCell(1).setCellValue(map.get("totalcnt") == null ? "" : map.get("totalcnt").toString());
                    row.createCell(2).setCellValue(map.get("ConsumerCnt") == null ? "" : map.get("ConsumerCnt").toString());
                    row.createCell(3).setCellValue(map.get("NC") == null ? "" : map.get("NC").toString());
                    row.createCell(4).setCellValue(map.get("Ratio1") == null ? "" : map.get("Ratio1").toString());
                    row.createCell(5).setCellValue(map.get("CRM") == null ? "" : map.get("CRM").toString());
                    row.createCell(6).setCellValue(map.get("Ratio2") == null ? "" : map.get("Ratio2").toString());
                    row.createCell(7).setCellValue(map.get("webchat") == null ? "" : map.get("webchat").toString());
                    row.createCell(8).setCellValue(map.get("Ratio3") == null ? "" : map.get("Ratio3").toString());
                    row.createCell(9).setCellValue(map.get("YAOWO") == null ? "" : map.get("YAOWO").toString());
                    row.createCell(10).setCellValue(map.get("Ratio4") == null ? "" : map.get("Ratio4").toString());
                    row.createCell(11).setCellValue(map.get("MarketActivity")== null ? "" : map.get("MarketActivity").toString());
                    row.createCell(12).setCellValue(map.get("Ratio5") == null ? "" : map.get("Ratio5").toString());
                    row.createCell(13).setCellValue(map.get("alliance") == null ? "" : map.get("alliance").toString());
                    row.createCell(14).setCellValue(map.get("Ratio6") == null ? "" : map.get("Ratio6").toString());
                    row.createCell(15).setCellValue(map.get("CustRecommend") == null ? "" : map.get("CustRecommend").toString());
                    row.createCell(16).setCellValue(map.get("Ratio7") == null ? "" : map.get("Ratio7").toString());
                    row.createCell(17).setCellValue(map.get("Other") == null ? "" : map.get("Other").toString());
                    row.createCell(18).setCellValue(map.get("Ratio8") == null ? "" : map.get("Ratio8").toString());
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