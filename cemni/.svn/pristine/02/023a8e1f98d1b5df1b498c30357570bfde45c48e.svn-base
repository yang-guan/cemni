package com.huiju.expandbusi.memcompanalyze;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.store.entity.Store;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.expandbusi.memcompanalyze.entity.MemCompAnalyze;
import com.huiju.expandbusi.memcompanalyze.entity.Memdetail;
import com.huiju.expandbusi.memcompanalyze.logic.MemCompAnalyzeRemote;
import com.huiju.expandbusi.memcompanalyze.logic.MemdetailRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.utils.NeuUtils;

/**
 * 会员完成指标
 * 
 * @author：yuhb
 * @date：2017年1月10日 下午10:33:56
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MemCompAnalyzeAction extends BaseAction<MemCompAnalyze, Long> {
    private static final long serialVersionUID = 1L;
    @EJB
    private MemCompAnalyzeRemote appLogic;
    @EJB
    private MemdetailRemote memdetailLogic;
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
        jsPath.add("/js/expandbusi/memcompanalyze/Q.memcompanalyze.js");

        String[] authorities = { "D_MEMCOMPANALYZE_LIST", "D_MEMCOMPANALYZE_ADD", "D_MEMCOMPANALYZE_EDIT", "D_MEMCOMPANALYZE_DELETE" };
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

        Page<MemCompAnalyze> page = new Page<MemCompAnalyze>(start, limit, NeuUtils.chgQrySort(sort), dir);
        page = appLogic.findAll(page, searchParam);
        for (MemCompAnalyze dt : page) {
            dt.setTypeName(DataDict.getDictName(DataDict.MEMCOMPANALYZE_TYPE, dt.getType()));
        }
        renderJson(page, "memdetailist");
    }

    public void save() throws ParseException {
        Map searchParam = new HashMap();
        searchParam.put("EQ_type", model.getType());
        searchParam.put("EQ_year", model.getYear());
        long cnt = appLogic.count(searchParam);
        if (cnt > 0) {
            dealJson(false, "“指标类别、指标年份”信息已存在！");
            return;
        }
        if (null != model.getMemdetailist()) {
            for (Memdetail dt : model.getMemdetailist()) {
                dt.setMemCompAnalyze(model);
            }
        }
        model.setMemcompanalyzenNo(sqlLogic.getCnNum(GlobalConst.NUM_MEMCOMPANALYZE));
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
        dealJson(true, model);
    }

    public void update() {
        Map searchParam = new HashMap();
        searchParam.put("EQ_memCompAnalyze_memcompanalyzeId", model.getMemcompanalyzeId());
        List<Memdetail> rsList = memdetailLogic.findAll(searchParam);

        List<Memdetail> tempList = model.getMemdetailist();

        for (Memdetail dt : rsList) {
            for (Memdetail dt2 : tempList) {
                if (dt.getStoreId().longValue() == dt2.getStoreId().longValue()) {
                    dt.setJanipeople(dt2.getJanipeople());
                    dt.setJaniprice(dt2.getJaniprice());
                    dt.setJanfpeople(dt2.getJanfpeople());
                    dt.setJanfprice(dt2.getJanfprice());

                    dt.setFebipeople(dt2.getFebipeople());
                    dt.setFebiprice(dt2.getFebiprice());
                    dt.setFebfpeople(dt2.getFebfpeople());
                    dt.setFebfprice(dt2.getFebfprice());

                    dt.setMaripeople(dt2.getMaripeople());
                    dt.setMariprice(dt2.getMariprice());
                    dt.setMarfpeople(dt2.getMarfpeople());
                    dt.setMarfprice(dt2.getMarfprice());

                    dt.setApripeople(dt2.getApripeople());
                    dt.setApriprice(dt2.getApriprice());
                    dt.setAprfpeople(dt2.getAprfpeople());
                    dt.setAprfprice(dt2.getAprfprice());

                    dt.setMayipeople(dt2.getMayipeople());
                    dt.setMayiprice(dt2.getMayiprice());
                    dt.setMayfpeople(dt2.getMayfpeople());
                    dt.setMayfprice(dt2.getMayfprice());

                    dt.setJunipeople(dt2.getJunipeople());
                    dt.setJuniprice(dt2.getJuniprice());
                    dt.setJunfpeople(dt2.getJunfpeople());
                    dt.setJunfprice(dt2.getJunfprice());

                    dt.setJulipeople(dt2.getJulipeople());
                    dt.setJuliprice(dt2.getJuliprice());
                    dt.setJulfpeople(dt2.getJulfpeople());
                    dt.setJulfprice(dt2.getJulfprice());

                    dt.setAugipeople(dt2.getAugipeople());
                    dt.setAugiprice(dt2.getAugiprice());
                    dt.setAugfpeople(dt2.getAugfpeople());
                    dt.setAugfprice(dt2.getAugfprice());

                    dt.setSepipeople(dt2.getSepipeople());
                    dt.setSepiprice(dt2.getSepiprice());
                    dt.setSepfpeople(dt2.getSepfpeople());
                    dt.setSepfprice(dt2.getSepfprice());

                    dt.setOctipeople(dt2.getOctipeople());
                    dt.setOctiprice(dt2.getOctiprice());
                    dt.setOctfpeople(dt2.getOctfpeople());
                    dt.setOctfprice(dt2.getOctfprice());

                    dt.setNovipeople(dt2.getNovipeople());
                    dt.setNoviprice(dt2.getNoviprice());
                    dt.setNovfpeople(dt2.getNovfpeople());
                    dt.setNovfprice(dt2.getNovfprice());

                    dt.setDecipeople(dt2.getDecipeople());
                    dt.setDeciprice(dt2.getDeciprice());
                    dt.setDecfpeople(dt2.getDecfpeople());
                    dt.setDecfprice(dt2.getDecfprice());
                    break;
                }
            }
        }
        model.setMemdetailist(rsList);
        appLogic.merge(model);
        dealJson(true);
    }

    public void downloadTemplate() throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=MemCompAnalyze.xls");

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("会员完成指标分解");
            sheet.createFreezePane(11, 3);// 冻结首行前10列
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
            sheet.setColumnWidth(23, 15 * 256);
            sheet.setColumnWidth(24, 15 * 256);
            sheet.setColumnWidth(25, 15 * 256);
            sheet.setColumnWidth(26, 15 * 256);
            sheet.setColumnWidth(27, 15 * 256);
            sheet.setColumnWidth(28, 15 * 256);
            sheet.setColumnWidth(29, 15 * 256);
            sheet.setColumnWidth(30, 15 * 256);
            sheet.setColumnWidth(31, 15 * 256);
            sheet.setColumnWidth(32, 15 * 256);
            sheet.setColumnWidth(33, 15 * 256);
            sheet.setColumnWidth(34, 15 * 256);
            sheet.setColumnWidth(35, 15 * 256);
            sheet.setColumnWidth(36, 15 * 256);
            sheet.setColumnWidth(37, 15 * 256);
            sheet.setColumnWidth(38, 15 * 256);
            sheet.setColumnWidth(39, 15 * 256);
            sheet.setColumnWidth(40, 15 * 256);
            sheet.setColumnWidth(41, 15 * 256);
            sheet.setColumnWidth(42, 15 * 256);
            sheet.setColumnWidth(43, 15 * 256);
            sheet.setColumnWidth(44, 15 * 256);
            sheet.setColumnWidth(45, 15 * 256);
            sheet.setColumnWidth(46, 15 * 256);
            sheet.setColumnWidth(47, 15 * 256);
            sheet.setColumnWidth(48, 15 * 256);
            sheet.setColumnWidth(49, 15 * 256);
            sheet.setColumnWidth(50, 15 * 256);
            sheet.setColumnWidth(51, 15 * 256);
            sheet.setColumnWidth(52, 15 * 256);
            sheet.setColumnWidth(53, 15 * 256);
            sheet.setColumnWidth(54, 15 * 256);
            sheet.setColumnWidth(55, 15 * 256);
            sheet.setColumnWidth(56, 15 * 256);
            sheet.setColumnWidth(57, 15 * 256);
            sheet.setColumnWidth(58, 15 * 256);

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
            sheet.setDefaultColumnStyle(23, textCellStyle);
            sheet.setDefaultColumnStyle(24, textCellStyle);
            sheet.setDefaultColumnStyle(25, textCellStyle);
            sheet.setDefaultColumnStyle(26, textCellStyle);
            sheet.setDefaultColumnStyle(27, textCellStyle);
            sheet.setDefaultColumnStyle(28, textCellStyle);
            sheet.setDefaultColumnStyle(29, textCellStyle);
            sheet.setDefaultColumnStyle(30, textCellStyle);
            sheet.setDefaultColumnStyle(31, textCellStyle);
            sheet.setDefaultColumnStyle(32, textCellStyle);
            sheet.setDefaultColumnStyle(33, textCellStyle);
            sheet.setDefaultColumnStyle(34, textCellStyle);
            sheet.setDefaultColumnStyle(35, textCellStyle);
            sheet.setDefaultColumnStyle(36, textCellStyle);
            sheet.setDefaultColumnStyle(37, textCellStyle);
            sheet.setDefaultColumnStyle(38, textCellStyle);
            sheet.setDefaultColumnStyle(39, textCellStyle);
            sheet.setDefaultColumnStyle(40, textCellStyle);
            sheet.setDefaultColumnStyle(41, textCellStyle);
            sheet.setDefaultColumnStyle(42, textCellStyle);
            sheet.setDefaultColumnStyle(43, textCellStyle);
            sheet.setDefaultColumnStyle(44, textCellStyle);
            sheet.setDefaultColumnStyle(45, textCellStyle);
            sheet.setDefaultColumnStyle(46, textCellStyle);
            sheet.setDefaultColumnStyle(47, textCellStyle);
            sheet.setDefaultColumnStyle(48, textCellStyle);
            sheet.setDefaultColumnStyle(49, textCellStyle);
            sheet.setDefaultColumnStyle(50, textCellStyle);
            sheet.setDefaultColumnStyle(51, textCellStyle);
            sheet.setDefaultColumnStyle(52, textCellStyle);
            sheet.setDefaultColumnStyle(53, textCellStyle);
            sheet.setDefaultColumnStyle(54, textCellStyle);
            sheet.setDefaultColumnStyle(55, textCellStyle);
            sheet.setDefaultColumnStyle(56, textCellStyle);
            sheet.setDefaultColumnStyle(57, textCellStyle);
            sheet.setDefaultColumnStyle(58, textCellStyle);

            CellStyle headStyle = wb.createCellStyle();
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 合并头
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 2));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 8, 8));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 10, 10));

            HSSFRow row0 = sheet.createRow(0);

            row0.createCell(0).setCellValue("大区ID");
            row0.createCell(1).setCellValue("大区编码");
            Cell cell_2 = row0.createCell(2);
            cell_2.setCellValue("大区");
            cell_2.setCellStyle(headStyle);

            row0.createCell(3).setCellValue("区域ID");
            row0.createCell(4).setCellValue("区域编码");
            Cell cell_5 = row0.createCell(5);
            cell_5.setCellValue("区域");
            cell_5.setCellStyle(headStyle);

            row0.createCell(6).setCellValue("门店");
            row0.createCell(7).setCellValue("门店编码");
            Cell cell_8 = row0.createCell(8);
            cell_8.setCellValue("门店");
            cell_8.setCellStyle(headStyle);

            row0.createCell(9).setCellValue("门店属性编码");
            Cell cell_10 = row0.createCell(10);
            cell_10.setCellValue("门店属性");
            cell_10.setCellStyle(headStyle);

            // 第二行
            HSSFRow row1 = sheet.createRow(1);
            for (int i = 11; i < 58; i = i + 4) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, i, i + 3));

                Cell cell_0_i = row0.createCell(i);
                cell_0_i.setCellValue((i - 7) / 4 + "月");
                cell_0_i.setCellStyle(headStyle);

                Cell cell_i = row1.createCell(i);
                cell_i.setCellValue("新会员");
                cell_i.setCellStyle(headStyle);

                Cell cell_i_2 = row1.createCell(i + 2);
                cell_i_2.setCellValue("老会员");
                cell_i_2.setCellStyle(headStyle);
            }

            // 第三行
            HSSFRow row2 = sheet.createRow(2);
            for (int i = 11; i < 58; i = i + 2) {
                sheet.addMergedRegion(new CellRangeAddress(1, 1, i, i + 1));

                Cell cell_i = row2.createCell(i);
                cell_i.setCellValue("人数");
                cell_i.setCellStyle(headStyle);

                Cell cell_i_2 = row2.createCell(i + 1);
                cell_i_2.setCellValue("客单价");
                cell_i_2.setCellStyle(headStyle);
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////

            if (id != null) {
                Map searchParam = new HashMap();
                searchParam.put("EQ_memCompAnalyze_memcompanalyzeId", id);
                String[] sort = { "memdetailId,asc" };
                List<Memdetail> rsList = memdetailLogic.findAll(searchParam, sort);

                HSSFRow row;
                Memdetail dt;
                for (int i = 0; i < rsList.size(); i++) {
                    row = sheet.createRow(i + 3);
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

                    row.createCell(11).setCellValue(NeuUtils.formatMath(dt.getJanipeople()));
                    row.createCell(12).setCellValue(NeuUtils.formatMath(dt.getJaniprice()));
                    row.createCell(13).setCellValue(NeuUtils.formatMath(dt.getJanfpeople()));
                    row.createCell(14).setCellValue(NeuUtils.formatMath(dt.getJanfprice()));

                    row.createCell(15).setCellValue(NeuUtils.formatMath(dt.getFebipeople()));
                    row.createCell(16).setCellValue(NeuUtils.formatMath(dt.getFebiprice()));
                    row.createCell(17).setCellValue(NeuUtils.formatMath(dt.getFebfpeople()));
                    row.createCell(18).setCellValue(NeuUtils.formatMath(dt.getFebfprice()));

                    row.createCell(19).setCellValue(NeuUtils.formatMath(dt.getMaripeople()));
                    row.createCell(20).setCellValue(NeuUtils.formatMath(dt.getMariprice()));
                    row.createCell(21).setCellValue(NeuUtils.formatMath(dt.getMarfpeople()));
                    row.createCell(22).setCellValue(NeuUtils.formatMath(dt.getMarfprice()));

                    row.createCell(23).setCellValue(NeuUtils.formatMath(dt.getApripeople()));
                    row.createCell(24).setCellValue(NeuUtils.formatMath(dt.getApriprice()));
                    row.createCell(25).setCellValue(NeuUtils.formatMath(dt.getAprfpeople()));
                    row.createCell(26).setCellValue(NeuUtils.formatMath(dt.getAprfprice()));

                    row.createCell(27).setCellValue(NeuUtils.formatMath(dt.getMayipeople()));
                    row.createCell(28).setCellValue(NeuUtils.formatMath(dt.getMayiprice()));
                    row.createCell(29).setCellValue(NeuUtils.formatMath(dt.getMayfpeople()));
                    row.createCell(30).setCellValue(NeuUtils.formatMath(dt.getMayfprice()));

                    row.createCell(31).setCellValue(NeuUtils.formatMath(dt.getJunipeople()));
                    row.createCell(32).setCellValue(NeuUtils.formatMath(dt.getJuniprice()));
                    row.createCell(33).setCellValue(NeuUtils.formatMath(dt.getJunfpeople()));
                    row.createCell(34).setCellValue(NeuUtils.formatMath(dt.getJunfprice()));

                    row.createCell(35).setCellValue(NeuUtils.formatMath(dt.getJulipeople()));
                    row.createCell(36).setCellValue(NeuUtils.formatMath(dt.getJuliprice()));
                    row.createCell(37).setCellValue(NeuUtils.formatMath(dt.getJulfpeople()));
                    row.createCell(38).setCellValue(NeuUtils.formatMath(dt.getJulfprice()));

                    row.createCell(39).setCellValue(NeuUtils.formatMath(dt.getAugipeople()));
                    row.createCell(40).setCellValue(NeuUtils.formatMath(dt.getAugiprice()));
                    row.createCell(41).setCellValue(NeuUtils.formatMath(dt.getAugfpeople()));
                    row.createCell(42).setCellValue(NeuUtils.formatMath(dt.getAugfprice()));

                    row.createCell(43).setCellValue(NeuUtils.formatMath(dt.getSepipeople()));
                    row.createCell(44).setCellValue(NeuUtils.formatMath(dt.getSepiprice()));
                    row.createCell(45).setCellValue(NeuUtils.formatMath(dt.getSepfpeople()));
                    row.createCell(46).setCellValue(NeuUtils.formatMath(dt.getSepfprice()));

                    row.createCell(47).setCellValue(NeuUtils.formatMath(dt.getOctipeople()));
                    row.createCell(48).setCellValue(NeuUtils.formatMath(dt.getOctiprice()));
                    row.createCell(49).setCellValue(NeuUtils.formatMath(dt.getOctfpeople()));
                    row.createCell(50).setCellValue(NeuUtils.formatMath(dt.getOctfprice()));

                    row.createCell(51).setCellValue(NeuUtils.formatMath(dt.getNovipeople()));
                    row.createCell(52).setCellValue(NeuUtils.formatMath(dt.getNoviprice()));
                    row.createCell(53).setCellValue(NeuUtils.formatMath(dt.getNovfpeople()));
                    row.createCell(54).setCellValue(NeuUtils.formatMath(dt.getNovfprice()));

                    row.createCell(55).setCellValue(NeuUtils.formatMath(dt.getDecipeople()));
                    row.createCell(56).setCellValue(NeuUtils.formatMath(dt.getDeciprice()));
                    row.createCell(57).setCellValue(NeuUtils.formatMath(dt.getDecfpeople()));
                    row.createCell(58).setCellValue(NeuUtils.formatMath(dt.getDecfprice()));
                }
            } else {
                List<Store> rsList = storeLogic.qryBigAreaStore();
                HSSFRow row;
                Store dt;
                for (int i = 0; i < rsList.size(); i++) {
                    row = sheet.createRow(i + 3);
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
        searchParam.putAll(userAuthGroupLogic.buildAuthFieldParams(WebUtils.getClientCode(), WebUtils.getUserCode(), Memdetail.class));
        Page<Memdetail> page = new Page(start, limit, "memdetailId", "asc");
        page = memdetailLogic.findAll(page, searchParam);
        for (Memdetail dt : page) {
            dt.setAttrName(DataDict.getDictName(DataDict.STORE_ATTR, dt.getAttr()));
        }
        renderJson(page, "memCompAnalyze");
    }

}