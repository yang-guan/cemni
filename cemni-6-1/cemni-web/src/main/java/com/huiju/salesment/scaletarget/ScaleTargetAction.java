package com.huiju.salesment.scaletarget;

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
import com.huiju.console.org.logic.OrgRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.salesment.scaletarget.entity.ScaleDetails;
import com.huiju.salesment.scaletarget.entity.ScaleTarget;
import com.huiju.salesment.scaletarget.logic.ScaleDetailsRemote;
import com.huiju.salesment.scaletarget.logic.ScaleTargetRemote;
import com.huiju.utils.NeuUtils;

/**
 * 分数段指标
 * 
 * @author：yuhb
 * @date：2017年1月10日 下午10:33:22
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ScaleTargetAction extends BaseAction<ScaleTarget, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ScaleTargetRemote appLogic;
    @EJB
    private ScaleDetailsRemote scaleDetailsLogic;
    @EJB
    private OrgRemote orgLogic;
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
        jsPath.add("/js/salesment/scaletarget/Q.salesment.scaletarget.js");

        String[] authorities = { "D_SCALETARGET_LIST", "D_SCALETARGET_ADD", "D_SCALETARGET_DELETE", "D_SCALETARGET_EDIT" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<ScaleTarget> page = new Page<ScaleTarget>(start, limit, sort, dir);

        Object LE_createDate = params.get("LE_createDate");
        if (LE_createDate != null && !StringUtils.isEmpty(LE_createDate.toString())) {
            Calendar cl = NeuUtils.parseCalendar(LE_createDate.toString());
            cl.add(Calendar.DATE, 1);
            params.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));
        }
        page = appLogic.findAll(page, params);
        renderJson(page, "scaleDetails");
    }

    public void save() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_partYear", model.getPartYear());
        long cnt = appLogic.count(searchParam);
        if (cnt > 0) {
            dealJson(false, "“指标年份”信息已存在！");
            return;
        }
        if (null != this.model.getScaleDetails()) {
            for (ScaleDetails dt : this.model.getScaleDetails()) {
                dt.setScaleTarget(model);
            }
        }
        model.setScaleNum(sqlLogic.getCnNum(GlobalConst.NUM_SCALETARGET));
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
        dealJson(true, DataUtils.toJson(model, new String[] { "scaleDetails" }));
    }

    public void update() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_scaleTarget_scaleId", model.getId());
        List<ScaleDetails> rsList = scaleDetailsLogic.findAll(searchParam);

        List<ScaleDetails> tempList = model.getScaleDetails();

        for (ScaleDetails dt : rsList) {
            for (ScaleDetails dt2 : tempList) {
                if (dt.getStoreId().longValue() == dt2.getStoreId().longValue() && dt.getScaleType().equals(dt2.getScaleType())) {
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
        model.setScaleDetails(rsList);
        appLogic.merge(model);
        dealJson(true);
    }

    // 载入门店
    public void loadStore() {
        List<Map> rsList = orgLogic.qryBigAreaStore_dict(DataDict.SALESMENT_SCALE);
        renderJson(rsList);
    }

    // 模版下载
    public void export() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=ScaleTarget.xls");

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("分数段销售指标");
            sheet.createFreezePane(11, 1);// 冻结首行前10列
            sheet.setColumnHidden(0, true);
            sheet.setColumnHidden(1, true);
            sheet.setColumnHidden(3, true);
            sheet.setColumnHidden(4, true);
            sheet.setColumnHidden(6, true);
            sheet.setColumnHidden(7, true);
            sheet.setColumnHidden(9, true);
            sheet.setColumnHidden(11, true);

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
            sheet.setColumnWidth(23, 15 * 256);
            sheet.setColumnWidth(24, 15 * 256);

            CellStyle textCellStyle = wb.createCellStyle();
            textCellStyle.setDataFormat(wb.createDataFormat().getFormat("@"));// 文本格式
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
            sheet.setDefaultColumnStyle(23, textCellStyle);
            sheet.setDefaultColumnStyle(24, textCellStyle);

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

            row0.createCell(11).setCellValue("分数段类型值");
            row0.createCell(12).setCellValue("分数段类型");

            row0.createCell(13).setCellValue("1月");
            row0.createCell(14).setCellValue("2月");
            row0.createCell(15).setCellValue("3月");
            row0.createCell(16).setCellValue("4月");
            row0.createCell(17).setCellValue("5月");
            row0.createCell(18).setCellValue("6月");
            row0.createCell(19).setCellValue("7月");
            row0.createCell(20).setCellValue("8月");
            row0.createCell(21).setCellValue("9月");
            row0.createCell(22).setCellValue("10月");
            row0.createCell(23).setCellValue("11月");
            row0.createCell(24).setCellValue("12月");

            if (id != null) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_scaleTarget_scaleId", id);
                String[] sort = { "scaleDetailsId,asc" };
                List<ScaleDetails> rsList = scaleDetailsLogic.findAll(searchParam, sort);

                HSSFRow row;
                ScaleDetails dt;
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

                    row.createCell(11).setCellValue(dt.getScaleType());
                    row.createCell(12).setCellValue(DataDict.getDictName(DataDict.SALESMENT_SCALE, dt.getScaleType()));

                    row.createCell(13).setCellValue(NeuUtils.formatMath(dt.getJan()));
                    row.createCell(14).setCellValue(NeuUtils.formatMath(dt.getFeb()));
                    row.createCell(15).setCellValue(NeuUtils.formatMath(dt.getMar()));
                    row.createCell(16).setCellValue(NeuUtils.formatMath(dt.getApr()));
                    row.createCell(17).setCellValue(NeuUtils.formatMath(dt.getMay()));
                    row.createCell(18).setCellValue(NeuUtils.formatMath(dt.getJun()));
                    row.createCell(19).setCellValue(NeuUtils.formatMath(dt.getJul()));
                    row.createCell(20).setCellValue(NeuUtils.formatMath(dt.getAug()));
                    row.createCell(21).setCellValue(NeuUtils.formatMath(dt.getSep()));
                    row.createCell(22).setCellValue(NeuUtils.formatMath(dt.getOct()));
                    row.createCell(23).setCellValue(NeuUtils.formatMath(dt.getNov()));
                    row.createCell(24).setCellValue(NeuUtils.formatMath(dt.getDec()));
                }
            } else {
                List<Map> rsList = orgLogic.qryBigAreaStore_dict(DataDict.SALESMENT_SCALE);
                HSSFRow row;
                Map map;
                for (int i = 0; i < rsList.size(); i++) {
                    row = sheet.createRow(i + 1);
                    map = (Map) rsList.get(i);

                    row.createCell(0).setCellValue(map.get("bigAreaId").toString());
                    row.createCell(1).setCellValue(map.get("bigAreaNo").toString());
                    row.createCell(2).setCellValue(map.get("bigAreaName").toString());
                    row.createCell(3).setCellValue(map.get("areaId").toString());
                    row.createCell(4).setCellValue(map.get("areaNo").toString());
                    row.createCell(5).setCellValue(map.get("areaName").toString());
                    row.createCell(6).setCellValue(map.get("storeId").toString());
                    row.createCell(7).setCellValue(map.get("storeNo").toString());
                    row.createCell(8).setCellValue(map.get("name").toString());
                    row.createCell(9).setCellValue(map.get("attr").toString());
                    row.createCell(10).setCellValue(map.get("attrName").toString());
                    row.createCell(11).setCellValue(map.get("dictValue").toString());
                    row.createCell(12).setCellValue(map.get("dictName").toString());
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
        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), ScaleDetails.class));
        Page<ScaleDetails> page = new Page(start, limit, "scaleDetailsId", "asc");
        page = scaleDetailsLogic.findAll(page, searchParam);
        for (ScaleDetails dt : page) {
            dt.setScaleTypeName(DataDict.getDictName(DataDict.SALESMENT_SCALE, dt.getScaleType()));
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
        }
        renderJson(page, "scaleTarget");
    }

}