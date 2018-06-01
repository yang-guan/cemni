package com.huiju.report.membergrade;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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

import com.huiju.module.util.CollectionUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.report.membergrade.logic.MemberGradeRemote;

/**
 * 会员等级数据整理
 * 
 * @author：WangYuanJun
 * @date：2017年2月23日 下午3:36:45
 */
@SuppressWarnings({ "rawtypes" })
public class MemberGradeAction extends BaseAction<Object, String> {
    private static final long serialVersionUID = 1L;
    @EJB
    private MemberGradeRemote appLogic;

    public String init() throws Exception {
        jsPath.add("/js/report/membergrade/Q.report.membergrade.js");

        String[] authorities = { "D_MEMBERGRADE_LIST", "D_MEMBERGRADE_EXPORT", "D_MEMBERGRADE_SEARCH" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void query() {
        List<Map> rsList = new ArrayList<Map>();
        List<Map> qryList = appLogic.report(WebUtils.getParametersStartingWith(request));
        int cnt = qryList.size();

        if (!CollectionUtils.isEmpty(qryList)) {
            int end = start + limit;
            end = (end > cnt ? cnt : end);

            if (start == 0) {
                DecimalFormat dbf = new DecimalFormat("#.##");
                NumberFormat df = NumberFormat.getPercentInstance();
                df.setMinimumFractionDigits(2);

                int sumActivePerson = 0;
                double sumActiveMoney = 0;
                int sumSleepPerson = 0;
                double sumSleepMoney = 0;
                int sumHistoryPerson = 0;
                double sumHistoryMoney = 0;
                int sumInvalidPerson = 0;
                double sumInvalidMoney = 0;

                for (Map map : qryList) {
                    // 活跃
                    sumActivePerson += Integer.valueOf(map.get("activePerson").toString());
                    sumActiveMoney += Double.valueOf(map.get("activeMoney").toString());
                    // 沉睡
                    sumSleepPerson += Integer.valueOf(map.get("sleepPerson").toString());
                    sumSleepMoney += Double.valueOf(map.get("sleepMoney").toString());
                    // 历史
                    sumHistoryPerson += Integer.valueOf(map.get("historyPerson").toString());
                    sumHistoryMoney += Double.valueOf(map.get("historyMoney").toString());
                    // 无效
                    sumInvalidPerson += Integer.valueOf(map.get("invalidPerson").toString());
                    sumInvalidMoney += Double.valueOf(map.get("invalidMoney").toString());
                }

                // 合计
                Map<String, Object> cntMap = new HashMap<String, Object>();
                cntMap.put("storeName", "合计");
                // 总人数
                int totalPerson = sumActivePerson + sumSleepPerson + sumHistoryPerson + sumInvalidPerson;
                if (totalPerson > 0) {
                    cntMap.put("percentActivePerson", df.format((double) sumActivePerson / totalPerson));
                    cntMap.put("percentSleepPerson", df.format((double) sumSleepPerson / totalPerson));
                    cntMap.put("percentHistoryPerson", df.format((double) sumHistoryPerson / totalPerson));
                    cntMap.put("percentInvalidPerson", df.format((double) sumInvalidPerson / totalPerson));
                }
                // 总金额
                double totalMoney = sumActiveMoney + sumSleepMoney + sumHistoryMoney + sumInvalidMoney;
                if (totalMoney > 0) {
                    cntMap.put("percentActiveMoney", df.format((double) sumActiveMoney / totalMoney));
                    cntMap.put("percentSleepMoney", df.format((double) sumSleepMoney / totalMoney));
                    cntMap.put("percentHistoryMoney", df.format((double) sumHistoryMoney / totalMoney));
                    cntMap.put("percentInvalidMoney", df.format((double) sumInvalidMoney / totalMoney));
                }
                cntMap.put("activePerson", sumActivePerson);
                cntMap.put("activeMoney", dbf.format(sumActiveMoney));
                cntMap.put("sleepPerson", sumSleepPerson);
                cntMap.put("sleepMoney", dbf.format(sumSleepMoney));
                cntMap.put("historyPerson", sumHistoryPerson);
                cntMap.put("historyMoney", dbf.format(sumHistoryMoney));
                cntMap.put("invalidPerson", sumInvalidPerson);
                cntMap.put("invalidMoney", dbf.format(sumInvalidMoney));
                cntMap.put("sumPerson", totalPerson);
                cntMap.put("sumMoney", dbf.format(totalMoney));

                rsList = qryList.subList(start, (end > 19 ? 19 : end));
                rsList.add(0, cntMap);
            } else {
                rsList = qryList.subList(start - 1, end - 1);
            }
            cnt += 1;
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("records", rsList);
        rsMap.put("totalCount", cnt);
        renderJson(rsMap);
    }

    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=MemberGrade.xls");
        HSSFWorkbook wb = null;

        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("会员等级数据整理");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(13);// 默认宽度

            CellStyle headStyle = wb.createCellStyle();
            headStyle.setDataFormat(wb.createDataFormat().getFormat("@"));// 文本格式
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            sheet.setColumnWidth(1, 26 * 256);

            HSSFRow row0 = sheet.createRow(0); // 第一行
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));// 第一行,0-2列合并

            String[] statusStrings = { "活跃", "历史", "沉睡", "无效", "合计" };
            for (int i = 3; i < 23; i = i + 4) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, i, i + 3));
                Cell cell_0_i = row0.createCell(i);
                cell_0_i.setCellValue(statusStrings[(i - 3) / 4]);
                cell_0_i.setCellStyle(headStyle);
            }

            HSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("大区");
            row1.createCell(1).setCellValue("门店");
            row1.createCell(2).setCellValue("会员等级");
            row1.createCell(3).setCellValue("人数");
            row1.createCell(4).setCellValue("人数比");
            row1.createCell(5).setCellValue("金额");
            row1.createCell(6).setCellValue("金额比");
            row1.createCell(7).setCellValue("人数");
            row1.createCell(8).setCellValue("人数比");
            row1.createCell(9).setCellValue("金额");
            row1.createCell(10).setCellValue("金额比");
            row1.createCell(11).setCellValue("人数");
            row1.createCell(12).setCellValue("人数比");
            row1.createCell(13).setCellValue("金额");
            row1.createCell(14).setCellValue("金额比");
            row1.createCell(15).setCellValue("人数");
            row1.createCell(16).setCellValue("人数比");
            row1.createCell(17).setCellValue("金额");
            row1.createCell(18).setCellValue("金额比");
            row1.createCell(19).setCellValue("人数");
            row1.createCell(20).setCellValue("人数比");
            row1.createCell(21).setCellValue("金额");
            row1.createCell(22).setCellValue("金额比");

            List<Map> rsList = appLogic.report(WebUtils.getParametersStartingWith(request));
            if (!CollectionUtils.isEmpty(rsList)) {
                DecimalFormat dbf = new DecimalFormat("#.##");
                NumberFormat df = NumberFormat.getPercentInstance();
                df.setMinimumFractionDigits(2);

                int sumActivePerson = 0;
                double sumActiveMoney = 0;
                int sumSleepPerson = 0;
                double sumSleepMoney = 0;
                int sumHistoryPerson = 0;
                double sumHistoryMoney = 0;
                int sumInvalidPerson = 0;
                double sumInvalidMoney = 0;

                for (Map map : rsList) {
                    // 活跃
                    sumActivePerson += Integer.valueOf(map.get("activePerson").toString());
                    sumActiveMoney += Double.valueOf(map.get("activeMoney").toString());
                    // 沉睡
                    sumSleepPerson += Integer.valueOf(map.get("sleepPerson").toString());
                    sumSleepMoney += Double.valueOf(map.get("sleepMoney").toString());
                    // 历史
                    sumHistoryPerson += Integer.valueOf(map.get("historyPerson").toString());
                    sumHistoryMoney += Double.valueOf(map.get("historyMoney").toString());
                    // 无效
                    sumInvalidPerson += Integer.valueOf(map.get("invalidPerson").toString());
                    sumInvalidMoney += Double.valueOf(map.get("invalidMoney").toString());
                }

                // 合计
                Map<String, Object> cntMap = new HashMap<String, Object>();
                cntMap.put("storeName", "合计");
                // 总人数
                int totalPerson = sumActivePerson + sumSleepPerson + sumHistoryPerson + sumInvalidPerson;
                if (totalPerson > 0) {
                    cntMap.put("percentActivePerson", df.format((double) sumActivePerson / totalPerson));
                    cntMap.put("percentSleepPerson", df.format((double) sumSleepPerson / totalPerson));
                    cntMap.put("percentHistoryPerson", df.format((double) sumHistoryPerson / totalPerson));
                    cntMap.put("percentInvalidPerson", df.format((double) sumInvalidPerson / totalPerson));
                }
                // 总金额
                double totalMoney = sumActiveMoney + sumSleepMoney + sumHistoryMoney + sumInvalidMoney;
                if (totalMoney > 0) {
                    cntMap.put("percentActiveMoney", df.format((double) sumActiveMoney / totalMoney));
                    cntMap.put("percentSleepMoney", df.format((double) sumSleepMoney / totalMoney));
                    cntMap.put("percentHistoryMoney", df.format((double) sumHistoryMoney / totalMoney));
                    cntMap.put("percentInvalidMoney", df.format((double) sumInvalidMoney / totalMoney));
                }
                cntMap.put("activePerson", sumActivePerson);
                cntMap.put("activeMoney", dbf.format(sumActiveMoney));
                cntMap.put("sleepPerson", sumSleepPerson);
                cntMap.put("sleepMoney", dbf.format(sumSleepMoney));
                cntMap.put("historyPerson", sumHistoryPerson);
                cntMap.put("historyMoney", dbf.format(sumHistoryMoney));
                cntMap.put("invalidPerson", sumInvalidPerson);
                cntMap.put("invalidMoney", dbf.format(sumInvalidMoney));
                cntMap.put("sumPerson", totalPerson);
                cntMap.put("sumMoney", dbf.format(totalMoney));

                rsList.add(0, cntMap);
                for (int i = 1; i <= rsList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map map = rsList.get(i - 1);// i必须

                    // 合计 合并单元格
                    if (i == 1) {
                        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));// 第一行,0-2列合并
                        Cell cell_0_i = row.createCell(0);
                        cell_0_i.setCellValue("合计");
                        cell_0_i.setCellStyle(headStyle);
                    } else {
                        row.createCell(0).setCellValue(map.get("bigAreaName") == null ? "" : map.get("bigAreaName").toString());
                        row.createCell(1).setCellValue(map.get("storeName").toString());
                        row.createCell(2).setCellValue(map.get("lvName").toString());
                    }

                    row.createCell(3).setCellValue(map.get("activePerson").toString());
                    row.createCell(4).setCellValue(map.get("percentActivePerson").toString());
                    row.createCell(5).setCellValue(map.get("activeMoney").toString());
                    row.createCell(6).setCellValue(map.get("percentActiveMoney").toString());

                    row.createCell(7).setCellValue(map.get("sleepPerson").toString());
                    row.createCell(8).setCellValue(map.get("percentSleepPerson").toString());
                    row.createCell(9).setCellValue(map.get("sleepMoney").toString());
                    row.createCell(10).setCellValue(map.get("percentSleepMoney").toString());

                    row.createCell(11).setCellValue(map.get("historyPerson").toString());
                    row.createCell(12).setCellValue(map.get("percentHistoryPerson").toString());
                    row.createCell(13).setCellValue(map.get("historyMoney").toString());
                    row.createCell(14).setCellValue(map.get("percentHistoryMoney").toString());

                    row.createCell(15).setCellValue(map.get("invalidPerson").toString());
                    row.createCell(16).setCellValue(map.get("percentInvalidPerson").toString());
                    row.createCell(17).setCellValue(map.get("invalidMoney").toString());
                    row.createCell(18).setCellValue(map.get("percentInvalidMoney").toString());

                    row.createCell(19).setCellValue(map.get("sumPerson").toString());
                    row.createCell(20).setCellValue(map.get("percentSumPerson") == null ? "" : map.get("percentSumPerson").toString());
                    row.createCell(21).setCellValue(map.get("sumMoney").toString());
                    row.createCell(22).setCellValue(map.get("percentSumMoney") == null ? "" : map.get("percentSumMoney").toString());
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