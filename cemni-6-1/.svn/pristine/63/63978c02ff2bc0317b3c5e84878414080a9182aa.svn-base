package com.huiju.report.custgradestandard;

import java.io.IOException;
import java.util.HashMap;
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

import com.huiju.module.data.util.DataUtils;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.custgradestandard.logic.CustGradeStandardRemote;

public class CustGradeStandardAction extends BaseAction<Object, String> {
	private static final long serialVersionUID = 1L;
	@EJB(mappedName = "CustGradeStandardBean")
	private CustGradeStandardRemote appLogic;

	public String init() throws Exception {
		jsPath.add("/js/report/custgradestandard/Q.report.custgradestandard.js");
		String[] authorities = { "D_CUSTGRADESTANDARD_LIST", "D_CUSTGRADESTANDARD_EXPORT", "D_CUSTGRADESTANDARD_SEARCH" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void query() {
		Map searchParam = WebUtils.getParametersStartingWith(request);
		searchParam.put("start", start);
		searchParam.put("limit", limit);
		Map mapList = appLogic.report(searchParam, 1);
		renderJson(DataUtils.toJson(mapList));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void export() throws Exception {
		Map searchParam = WebUtils.getParametersStartingWith(request);
		Map reMap = appLogic.report(searchParam, 2);
		if (!CollectionUtils.isEmpty(reMap)) {
			HSSFWorkbook wb = null;
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename=custgradestandardreport.xls");
			try {
				wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("加盟商客户分级标准");
				sheet.setDefaultColumnWidth(13);// 默认宽度
				sheet.createFreezePane(0, 2);// 冻结首行
				CellStyle headStyle = wb.createCellStyle();
				headStyle.setDataFormat(wb.createDataFormat().getFormat("@"));// 文本格式
				headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				sheet.setColumnWidth(0, 15 * 500);
				sheet.setColumnWidth(1, 15 * 299);
				sheet.setColumnWidth(2, 15 * 256);
				sheet.setColumnWidth(3, 15 * 256);
				sheet.setColumnWidth(4, 15 * 256);
				sheet.setColumnWidth(5, 15 * 256);
				sheet.setColumnWidth(6, 15 * 256);
				sheet.setColumnWidth(7, 15 * 256);
				sheet.setColumnWidth(8, 15 * 256);
				sheet.setColumnWidth(9, 15 * 256);
				sheet.setColumnWidth(10, 15 * 256);
				sheet.setColumnWidth(11, 15 * 356);
				sheet.setColumnWidth(12, 15 * 356);
				sheet.setColumnWidth(13, 15 * 380);
				sheet.setColumnWidth(14, 15 * 356);
				sheet.setColumnWidth(15, 15 * 256);
				sheet.setColumnWidth(16, 15 * 256);

				sheet.setDefaultColumnStyle(0, headStyle);
				sheet.setDefaultColumnStyle(1, headStyle);
				sheet.setDefaultColumnStyle(2, headStyle);
				sheet.setDefaultColumnStyle(3, headStyle);
				sheet.setDefaultColumnStyle(4, headStyle);
				sheet.setDefaultColumnStyle(5, headStyle);
				sheet.setDefaultColumnStyle(6, headStyle);
				sheet.setDefaultColumnStyle(7, headStyle);
				sheet.setDefaultColumnStyle(8, headStyle);
				sheet.setDefaultColumnStyle(9, headStyle);
				sheet.setDefaultColumnStyle(10, headStyle);
				sheet.setDefaultColumnStyle(11, headStyle);
				sheet.setDefaultColumnStyle(12, headStyle);
				sheet.setDefaultColumnStyle(13, headStyle);
				sheet.setDefaultColumnStyle(14, headStyle);
				sheet.setDefaultColumnStyle(15, headStyle);
				sheet.setDefaultColumnStyle(16, headStyle);
				HSSFRow row0 = sheet.createRow(0); // 第一行
				// 合并单元格
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 14));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 15, 16));

				Cell cell_0 = row0.createCell(0);
				cell_0.setCellValue("");
				cell_0.setCellStyle(headStyle);

				Cell cell_1 = row0.createCell(2);
				cell_1.setCellValue("客户信用");
				cell_1.setCellStyle(headStyle);

				Cell cell_2 = row0.createCell(7);
				cell_2.setCellValue("品牌活动");
				cell_2.setCellStyle(headStyle);

				Cell cell_3 = row0.createCell(8);
				cell_3.setCellValue("招商活动");
				cell_3.setCellStyle(headStyle);
				
				Cell cell_4 = row0.createCell(9);
				cell_4.setCellValue("拿货会");
				cell_4.setCellStyle(headStyle);

				Cell cell_5 = row0.createCell(10);
				cell_5.setCellValue("培训会");
				cell_5.setCellStyle(headStyle);

				Cell cell_6 = row0.createCell(11);
				cell_6.setCellValue("业绩指标");
				cell_6.setCellStyle(headStyle);
				
				Cell cell_7 = row0.createCell(15);
				cell_7.setCellValue("");
				cell_7.setCellStyle(headStyle);

				HSSFRow row1 = sheet.createRow(1); // 第二行

				row1.createCell(0).setCellValue("加盟商名称");
				row1.createCell(1).setCellValue("加盟商类型");
				row1.createCell(2).setCellValue("有无担保");
				row1.createCell(3).setCellValue("是否股东");
				row1.createCell(4).setCellValue("首批欠款记录");
				row1.createCell(5).setCellValue("补货欠款记录");
				row1.createCell(6).setCellValue("稽核问题整改率");
				row1.createCell(7).setCellValue("整体参与占比");
				row1.createCell(8).setCellValue("整体参与占比");
				row1.createCell(9).setCellValue("整体参与占比");
				row1.createCell(10).setCellValue("整体参与占比");
				row1.createCell(11).setCellValue("当月新店首批拿货");
				row1.createCell(12).setCellValue("当月业绩达成率");
				row1.createCell(13).setCellValue("累计年度整体业绩达成率");
				row1.createCell(14).setCellValue("累计年度整体业绩达成率");
				row1.createCell(15).setCellValue("总分");
				row1.createCell(16).setCellValue("等级");
				
				List<Map> list = (List<Map>) reMap.get("records");
				for (int i = 0; i < list.size(); i++) {
					HSSFRow row = sheet.createRow(i + 2);
					Map dt = new HashMap();
					dt = list.get(i);
					row.createCell(0).setCellValue(dt.get("franame") == null ? "" : dt.get("franame").toString());
					row.createCell(1).setCellValue(dt.get("fratype") == null ? "" : dt.get("fratype").toString());
					row.createCell(2).setCellValue(dt.get("guarantee") == null ? "" : dt.get("guarantee").toString());
					row.createCell(3).setCellValue(dt.get("shares") == null ? "" : dt.get("shares").toString());
					row.createCell(4).setCellValue(dt.get("firstarrears") == null ? "" : dt.get("firstarrears").toString());
					row.createCell(5).setCellValue(dt.get("addarrears") == null ? "" : dt.get("addarrears").toString());
					row.createCell(6).setCellValue(dt.get("auditprobnum") == null ? "" : dt.get("auditprobnum").toString());
					row.createCell(7).setCellValue(dt.get("brand_grade") == null ? "" : dt.get("brand_grade").toString());
					row.createCell(8).setCellValue(dt.get("zhangshang_grade") == null ? "" : dt.get("zhangshang_grade").toString());
					row.createCell(9).setCellValue(dt.get("nahuo_grade") == null ? "" : dt.get("nahuo_grade").toString());
					row.createCell(10).setCellValue(dt.get("peixun_grade") == null ? "" : dt.get("peixun_grade").toString());
					row.createCell(11).setCellValue(dt.get("fpickman") == null ? "" : dt.get("fpickman").toString());
					row.createCell(12).setCellValue(dt.get("month_goods") == null ? "" : dt.get("month_goods").toString());
					row.createCell(13).setCellValue(dt.get("total_goods") == null ? "" : dt.get("total_goods").toString());
					row.createCell(14).setCellValue(dt.get("month_shops") == null ? "" : dt.get("month_shops").toString());
					row.createCell(15).setCellValue(dt.get("total_grade") == null ? "" : dt.get("total_grade").toString());
					row.createCell(16).setCellValue(dt.get("level") == null ? "" : dt.get("level").toString());
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
}