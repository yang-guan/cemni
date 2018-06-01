package com.huiju.salesment.yeartarget;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.salesment.yeartarget.entity.YearDetails;
import com.huiju.salesment.yeartarget.entity.YearTarget;
import com.huiju.salesment.yeartarget.logic.YearDetailsRemote;
import com.huiju.salesment.yeartarget.logic.YearTargetRemote;
import com.huiju.utils.NeuUtils;

/**
 * 年度销售指标
 * 
 * @author：yuhb
 * @date：2017年1月10日 下午10:33:08
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class YearTargetAction extends BaseAction<YearTarget, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private YearTargetRemote appLogic;
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

    public String init() {
        jsPath.add("/js/salesment/yeartarget/Q.salesment.yeartarget.js");

        String[] authorities = { "D_YEARTARGET_LIST", "D_YEARTARGET_ADD", "D_YEARTARGET_DELETE", "D_YEARTARGET_EDIT" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        Page<YearTarget> page = new Page<YearTarget>(start, limit, NeuUtils.chgQrySort(sort), dir);

        Object LE_createDate = searchParam.get("LE_createDate");
        if (LE_createDate != null && !StringUtils.isEmpty(LE_createDate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_createDate.toString());
            cl.add(Calendar.DATE, 1);
            searchParam.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));
        }
        page = appLogic.findAll(page, searchParam);
        for (YearTarget dt : page) {
            dt.setTypeName(DataDict.getDictName(DataDict.SALESMENT_YEAR, dt.getType()));
        }
        renderJson(page, "yearDetails");
    }

    public void save() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_type", model.getType());
        searchParam.put("EQ_partYear", model.getPartYear());
        long cnt = appLogic.count(searchParam);
        if (cnt > 0) {
            dealJson(false, "“指标类别、指标年份”信息已存在！");
            return;
        }
        if (null != this.model.getYearDetails()) {
            for (YearDetails yd : this.model.getYearDetails()) {
                yd.setYearTarget(model);
            }
        }
        this.getAllAmount();
        model.setYearNum(sqlLogic.getCnNum(GlobalConst.NUM_YEARTARGET));
        model.setCreateUser(WebUtils.getUserName());
        model.setCreateDate(Calendar.getInstance());
        appLogic.persist(model);
        dealJson(true);
    }

    public void delete() {
        for (Long id : ids) {
            appLogic.removeById(id);
        }
        dealJson(true);
    }

    public void edit() {
        model = appLogic.findById(id);
        dealJson(true, DataUtils.toJson(model, "yearDetails"));
    }

    public void update() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_yearTarget_yearId", model.getId());
        List<YearDetails> rsList = yearDetailsLogic.findAll(searchParam);

        List<YearDetails> tempList = model.getYearDetails();

        for (YearDetails dt : rsList) {
            for (YearDetails dt2 : tempList) {
                if (dt.getStoreId().longValue() == dt2.getStoreId().longValue()) {
                    dt.setJan(dt2.getJan());
                    dt.setFeb(dt2.getFeb());
                    dt.setMar(dt2.getMar());
                    dt.setApr(dt2.getApr());
                    dt.setMay(dt2.getMay());
                    dt.setJun(dt2.getJun());
                    dt.setJul(dt2.getJul());
                    dt.setAug(dt2.getAug());
                    dt.setSep(dt2.getSep());
                    dt.setOct(dt2.getOct());
                    dt.setNov(dt2.getNov());
                    dt.setDec(dt2.getDec());
                    break;
                }
            }
        }
        model.setYearDetails(rsList);
        this.getAllAmount();
        appLogic.updYearTargetIndividComp(model);
        dealJson(true);
    }

    /**
     * 获取销售总金额
     */
    private void getAllAmount() {
        Double allAmount = 0D;
        for (YearDetails dt : model.getYearDetails()) {
            allAmount += (dt.getJan() != null ? dt.getJan() : 0);
            allAmount += (dt.getFeb() != null ? dt.getFeb() : 0);
            allAmount += (dt.getMar() != null ? dt.getMar() : 0);
            allAmount += (dt.getApr() != null ? dt.getApr() : 0);
            allAmount += (dt.getMay() != null ? dt.getMay() : 0);
            allAmount += (dt.getJun() != null ? dt.getJun() : 0);
            allAmount += (dt.getJul() != null ? dt.getJul() : 0);
            allAmount += (dt.getAug() != null ? dt.getAug() : 0);
            allAmount += (dt.getSep() != null ? dt.getSep() : 0);
            allAmount += (dt.getOct() != null ? dt.getOct() : 0);
            allAmount += (dt.getDec() != null ? dt.getDec() : 0);
            allAmount += (dt.getNov() != null ? dt.getNov() : 0);
        }
        model.setAllAmount(allAmount);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // 模版下载
    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=YearTarget.xls");

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("年度销售指标");
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
            sheet.setColumnWidth(16, 15 * 256);
            sheet.setColumnWidth(17, 15 * 256);
            sheet.setColumnWidth(18, 15 * 256);
            sheet.setColumnWidth(19, 15 * 256);
            sheet.setColumnWidth(20, 15 * 256);
            sheet.setColumnWidth(21, 15 * 256);
            sheet.setColumnWidth(22, 15 * 256);

            CellStyle textCellStyle = wb.createCellStyle();
            textCellStyle.setDataFormat(wb.createDataFormat().getFormat("@"));// 文本格式
            sheet.setDefaultColumnStyle(11, textCellStyle);
            sheet.setDefaultColumnStyle(12, textCellStyle);
            sheet.setDefaultColumnStyle(13, textCellStyle);
            sheet.setDefaultColumnStyle(14, textCellStyle);
            sheet.setDefaultColumnStyle(15, textCellStyle);
            sheet.setDefaultColumnStyle(16, textCellStyle);
            sheet.setDefaultColumnStyle(17, textCellStyle);
            sheet.setDefaultColumnStyle(18, textCellStyle);
            sheet.setDefaultColumnStyle(19, textCellStyle);
            sheet.setDefaultColumnStyle(20, textCellStyle);
            sheet.setDefaultColumnStyle(21, textCellStyle);
            sheet.setDefaultColumnStyle(22, textCellStyle);

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

            row0.createCell(11).setCellValue("1月");
            row0.createCell(12).setCellValue("2月");
            row0.createCell(13).setCellValue("3月");
            row0.createCell(14).setCellValue("4月");
            row0.createCell(15).setCellValue("5月");
            row0.createCell(16).setCellValue("6月");
            row0.createCell(17).setCellValue("7月");
            row0.createCell(18).setCellValue("8月");
            row0.createCell(19).setCellValue("9月");
            row0.createCell(20).setCellValue("10月");
            row0.createCell(21).setCellValue("11月");
            row0.createCell(22).setCellValue("12月");

            if (id != null) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_yearTarget_yearId", id);
                String[] sort = { "yearDetailsId,asc" };
                List<YearDetails> rsList = yearDetailsLogic.findAll(searchParam, sort);
                HSSFRow row;
                YearDetails dt;
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

                    row.createCell(11).setCellValue(NeuUtils.formatMath(dt.getJan()));
                    row.createCell(12).setCellValue(NeuUtils.formatMath(dt.getFeb()));
                    row.createCell(13).setCellValue(NeuUtils.formatMath(dt.getMar()));
                    row.createCell(14).setCellValue(NeuUtils.formatMath(dt.getApr()));
                    row.createCell(15).setCellValue(NeuUtils.formatMath(dt.getMay()));
                    row.createCell(16).setCellValue(NeuUtils.formatMath(dt.getJun()));
                    row.createCell(17).setCellValue(NeuUtils.formatMath(dt.getJul()));
                    row.createCell(18).setCellValue(NeuUtils.formatMath(dt.getAug()));
                    row.createCell(19).setCellValue(NeuUtils.formatMath(dt.getSep()));
                    row.createCell(20).setCellValue(NeuUtils.formatMath(dt.getOct()));
                    row.createCell(21).setCellValue(NeuUtils.formatMath(dt.getNov()));
                    row.createCell(22).setCellValue(NeuUtils.formatMath(dt.getDec()));
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

    // 导入
    public void excel() {
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

    public void getAllDetails() {
        Map searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), YearDetails.class));
        Page<YearDetails> page = new Page(start, limit, "yearDetailsId", "asc");
        page = yearDetailsLogic.findAll(page, searchParam);
        for (YearDetails dt : page) {
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
        }
        renderJson(page, "yearTarget");
    }

}