package com.huiju.expandbusi.individcompanalyze;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.expandbusi.individcompanalyze.logic.IndicatorsRemote;
import com.huiju.expandbusi.individcompanalyze.logic.IndividCompAnalyzeRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.salesment.yeartarget.entity.YearDetails;
import com.huiju.salesment.yeartarget.logic.YearDetailsRemote;
import com.huiju.utils.NeuUtils;

/**
 * 个人业绩完成指标
 * 
 * @author：yuhb
 * @date：2017年1月10日 下午10:34:13
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IndividCompAnalyzeAction extends BaseAction<IndividCompAnalyze, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private IndividCompAnalyzeRemote appLogic;
    @EJB
    private IndicatorsRemote indicatorsLogic;
    @EJB
    private YearDetailsRemote yearDetailsLogic;
    @EJB
    private StoreRemote storeLogic;
    @EJB
    private SqlRemote sqlLogic;
    @EJB
    private UserAuthGroupRemote userAuthGroupLogic;
    @EJB
    private FileInfoRemote fileInfoLogic;

    private File file;
    private String fileName;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String init() throws Exception {
        jsPath.add("/js/expandbusi/individcompanalyze/Q.individcompanalyze.js");

        String[] authorities = { "D_INDIVIDCOMPANALYZE_LIST", "D_INDIVIDCOMPANALYZE_ADD", "D_INDIVIDCOMPANALYZE_EDIT", "D_INDIVIDCOMPANALYZE_DELETE" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);

        Object LE_cdate = searchParam.get("LE_cdate");
        if (LE_cdate != null && !StringUtils.isEmpty(LE_cdate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_cdate.toString());
            cl.add(Calendar.DATE, 1);
            searchParam.put("LE_cdate", NeuUtils.parseStringFromCalendar(cl));
        }
        Page<IndividCompAnalyze> page = new Page<IndividCompAnalyze>(start, limit, sort, dir);
        page = appLogic.findAll(page, searchParam);
        renderJson(page, "indicators");
    }

    public void save() throws ParseException {
        Map searchParam = new HashMap();
        searchParam.put("EQ_year", model.getYear());
        searchParam.put("EQ_month", model.getMonth());
        long cnt = appLogic.count(searchParam);
        if (cnt > 0) {
            dealJson(false, "“指标年份、指标月份”信息已存在！");
            return;
        }

        this.setBaseRadixMoneyAmount();
        model.setIndividCompanalyzeNo(sqlLogic.getCnNum(GlobalConst.NUM_INDIVIDCOMPANALYZE));
        model.setCdate(Calendar.getInstance());
        model.setCuser(WebUtils.getUserName());
        appLogic.persist(model);
        dealJson(true);
    }

    // 级联删除
    public void delete() {
        for (Long id : ids) {
            appLogic.removeById(id);
        }
        dealJson(true);
    }

    public void edit() {
        model = appLogic.findById(id);
        dealJson(true, DataUtils.toJson(model, "indicators"));
    }

    public void update() {
        if (model.getExcelFlag() == 0) {
            Map searchParam = new HashMap();
            searchParam.put("EQ_individCompAnalyze_individCompanalyzeId", model.getId());
            List<Indicators> rsList = indicatorsLogic.findAll(searchParam);

            List<Indicators> tempList = model.getIndicators();

            for (Indicators dt : rsList) {
                for (Indicators dt2 : tempList) {
                    if (dt.getIndicatorsId().longValue() == dt2.getIndicatorsId().longValue()) {
                        dt.setWorknumber(dt2.getWorknumber());
                        dt.setName(dt2.getName());
                        dt.setPosition(dt2.getPosition());
                        dt.setLevelr(dt2.getLevelr());
                        dt.setBaseRadix(dt2.getBaseRadix());
                        break;
                    }
                }
            }
            model.setIndicators(rsList);
        } else {
            for (Indicators dt : model.getIndicators()) {
                dt.setIndividCompAnalyze(model);
            }
        }
        this.setBaseRadixMoneyAmount();
        appLogic.merge(model);
        dealJson(true);
    }

    /**
     * 设置门店员工的“基数、金额”
     */
    private void setBaseRadixMoneyAmount() {
        Map params = new HashMap();
        params.put("EQ_yearTarget_type", 1);// 销售指标
        params.put("EQ_yearTarget_partYear", model.getYear());
        List<YearDetails> rsList = yearDetailsLogic.findAll(params);

        Map<Long, Double> moneyMap = new HashMap<Long, Double>();
        for (YearDetails dt : rsList) {
            Double moneyAmount = null;
            switch (model.getMonth()) {
            case 1:
                moneyAmount = dt.getJan();
                break;
            case 2:
                moneyAmount = dt.getFeb();
                break;
            case 3:
                moneyAmount = dt.getMar();
                break;
            case 4:
                moneyAmount = dt.getApr();
                break;
            case 5:
                moneyAmount = dt.getMay();
                break;
            case 6:
                moneyAmount = dt.getJun();
                break;
            case 7:
                moneyAmount = dt.getJul();
                break;
            case 8:
                moneyAmount = dt.getAug();
                break;
            case 9:
                moneyAmount = dt.getSep();
                break;
            case 10:
                moneyAmount = dt.getOct();
                break;
            case 11:
                moneyAmount = dt.getDec();
                break;
            case 12:
                moneyAmount = dt.getNov();
                break;
            }
            moneyMap.put(dt.getStoreId(), moneyAmount);
        }

        // 店员占比
        Map<Long, Double> baseRadixMap = new HashMap<Long, Double>();
        Long storeId;
        Double baseRadix;
        for (Indicators dt : model.getIndicators()) {
            if (dt.getPosition() != null && dt.getPosition().equals(GlobalConst.TARGET_POS_STOREMGR)) {
                continue;
            }
            storeId = dt.getStoreId();
            baseRadix = dt.getBaseRadix();
            if (baseRadix != null) {
                if (baseRadixMap.get(storeId) == null) {
                    baseRadixMap.put(storeId, baseRadix);
                } else {
                    baseRadixMap.put(storeId, baseRadixMap.get(storeId) + baseRadix);
                }
            }
        }

        // 计算额度
        Double total;
        for (Indicators dt : model.getIndicators()) {
            dt.setIndividCompAnalyze(model);
            total = moneyMap.get(dt.getStoreId());

            // 店长特殊处理
            if (dt.getPosition() != null && dt.getPosition().equals(GlobalConst.TARGET_POS_STOREMGR)) {
                dt.setBaseRadix(baseRadixMap.get(dt.getStoreId()));
                dt.setMoneyAmount(total);
            } else {
                if (dt.getBaseRadix() != null && total != null) {
                    dt.setMoneyAmount(NeuUtils.formatDouble(dt.getBaseRadix() / baseRadixMap.get(dt.getStoreId()) * total));
                } else {
                    dt.setMoneyAmount(null);
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void downloadTemplate() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=IndividCompAnalyze.xls");

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("个人业绩完成指标分解");
            sheet.createFreezePane(11, 1);// 冻结首行前10列
            sheet.setColumnHidden(0, true);
            sheet.setColumnHidden(1, true);
            sheet.setColumnHidden(3, true);
            sheet.setColumnHidden(4, true);
            sheet.setColumnHidden(6, true);
            sheet.setColumnHidden(7, true);
            sheet.setColumnHidden(9, true);

            sheet.setColumnWidth(0, 10 * 256);
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 20 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 10 * 256);
            sheet.setColumnWidth(7, 15 * 256);
            sheet.setColumnWidth(8, 30 * 256);
            sheet.setColumnWidth(9, 10 * 256);
            sheet.setColumnWidth(10, 15 * 256);
            sheet.setColumnWidth(11, 15 * 256);
            sheet.setColumnWidth(12, 15 * 256);
            sheet.setColumnWidth(13, 15 * 256);
            sheet.setColumnWidth(14, 15 * 256);
            sheet.setColumnWidth(15, 15 * 256);

            CellStyle textCellStyle = wb.createCellStyle();
            textCellStyle.setDataFormat(wb.createDataFormat().getFormat("@"));// 文本格式
            sheet.setDefaultColumnStyle(11, textCellStyle);
            sheet.setDefaultColumnStyle(12, textCellStyle);
            sheet.setDefaultColumnStyle(13, textCellStyle);
            sheet.setDefaultColumnStyle(14, textCellStyle);
            sheet.setDefaultColumnStyle(15, textCellStyle);

            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("大区ID");
            row0.createCell(1).setCellValue("大区编码");
            row0.createCell(2).setCellValue("大区");
            row0.createCell(3).setCellValue("区域ID");
            row0.createCell(4).setCellValue("区域编码");
            row0.createCell(5).setCellValue("区域");
            row0.createCell(6).setCellValue("门店ID");
            row0.createCell(7).setCellValue("门店编码");
            row0.createCell(8).setCellValue("门店");
            row0.createCell(9).setCellValue("门店属性编码");
            row0.createCell(10).setCellValue("门店属性");

            row0.createCell(11).setCellValue("工号");
            row0.createCell(12).setCellValue("姓名");
            row0.createCell(13).setCellValue("职位");
            row0.createCell(14).setCellValue("岗位级别");
            row0.createCell(15).setCellValue("人员基数");

            if (id != null) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_individCompAnalyze_individCompanalyzeId", id);
                String[] sort = { "indicatorsId,asc" };
                List<Indicators> rsList = indicatorsLogic.findAll(searchParam, sort);
                HSSFRow row;
                Indicators dt;
                for (int i = 0; i < rsList.size(); i++) {
                    row = sheet.createRow(i + 1);
                    dt = rsList.get(i);

                    row.createCell(0).setCellValue(dt.getBigAreaId());
                    row.createCell(1).setCellValue(dt.getBigAreaNo());
                    row.createCell(2).setCellValue(dt.getBigAreaName());
                    row.createCell(3).setCellValue(dt.getAreaId());
                    row.createCell(4).setCellValue(dt.getAreaNo());
                    row.createCell(5).setCellValue(dt.getAreaName());
                    row.createCell(6).setCellValue(dt.getStoreId());
                    row.createCell(7).setCellValue(dt.getStoreNo());
                    row.createCell(8).setCellValue(dt.getName());
                    row.createCell(9).setCellValue(dt.getAttr());
                    row.createCell(10).setCellValue(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));

                    row.createCell(11).setCellValue(dt.getWorknumber());
                    row.createCell(12).setCellValue(dt.getWorkName());
                    row.createCell(13).setCellValue(dt.getPosition());
                    row.createCell(14).setCellValue(dt.getLevelr());
                    row.createCell(15).setCellValue(NeuUtils.formatMath(dt.getBaseRadix()));
                }
            } else {
                List<Store> rsList = storeLogic.qryBigAreaStore();
                HSSFRow row;
                Store dt;
                for (int i = 0; i < rsList.size(); i++) {
                    row = sheet.createRow(i + 1);
                    dt = rsList.get(i);

                    row.createCell(0).setCellValue(dt.getBigAreaId());
                    row.createCell(1).setCellValue(dt.getBigAreaNo());
                    row.createCell(2).setCellValue(dt.getBigAreaName());
                    row.createCell(3).setCellValue(dt.getAreaId());
                    row.createCell(4).setCellValue(dt.getAreaNo());
                    row.createCell(5).setCellValue(dt.getAreaName());
                    row.createCell(6).setCellValue(dt.getStoreId());
                    row.createCell(7).setCellValue(dt.getStoreNo());
                    row.createCell(8).setCellValue(dt.getName());
                    row.createCell(9).setCellValue(dt.getAttr());
                    row.createCell(10).setCellValue(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
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

    public void excel() throws ParseException {
        Map<String, Object> retMap = null;
        try {
            FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
            retMap = appLogic.excel(fileInfoLogic.convert(fileInfo).getFile());
        } catch (Exception e) {
            retMap = new HashMap<String, Object>();
            retMap.put("success", false);
            retMap.put("msg", e.getMessage());
        }
        renderHtml(DataUtils.toJson(retMap));
    }

    public void getDetail() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), YearDetails.class));
        Page<Indicators> page = new Page(start, limit, "indicatorsId", "asc");
        page = indicatorsLogic.findAll(page, searchParam);
        for (Indicators dt : page) {
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
        }
        renderJson(page, "individCompAnalyze");
    }

}