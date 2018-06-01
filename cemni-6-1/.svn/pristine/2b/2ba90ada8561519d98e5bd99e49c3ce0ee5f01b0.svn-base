package com.huiju.archive.groupcust;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huiju.archive.groupcust.entity.CompetitorProduct;
import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.groupcust.entity.Product;
import com.huiju.archive.groupcust.logic.CompetitorProductRemote;
import com.huiju.archive.groupcust.logic.GroupCustRemote;
import com.huiju.archive.groupcust.logic.ProductRemote;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.archive.individcust.logic.IndividCustRemote;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.store.logic.StoreRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

public class GroupCustAction extends BaseAction<GroupCust, Long> {
    private static final long serialVersionUID = 1L;

    @EJB(mappedName = "GroupCustBean")
    private GroupCustRemote groupCustLogic;

    @EJB(mappedName = "ProductBean")
    private ProductRemote productLogic;

    @EJB(mappedName = "CompetitorProductBean")
    private CompetitorProductRemote competitorLogic;

    @EJB(mappedName = "IndividCustBean")
    private IndividCustRemote individCustLogic;

    @EJB(mappedName = "SqlBean")
    private SqlRemote sqlLogic;

    @EJB(mappedName = "StoreBean")
    private StoreRemote storeLogic;

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
        jsPath.add("/js/archive/groupcust/Q.archive.groupcust.js");

        String[] authorities = { "D_GROUPCUST_LIST", "D_GROUPCUST_ADD", "D_GROUPCUST_EDIT", "D_GROUPCUST_SEARCH", "D_GROUPCUST_BATCHSEARCH", "D_GROUPCUST_CHANGECODE", "D_GROUPCUST_CHANGECODE2", "D_GROUPCUST_DOWNLOAD", "D_GROUPCUST_IMPORT", "D_GROUPCUST_EXPORT" };
        permissions = this.checkPermissions(authorities);
        return LIST;
    }

    public void getJson() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        Page<GroupCust> page = new Page<GroupCust>(start, limit, NeuUtils.chgQrySort(sort, "groupName,dmName"), dir);
        page = groupCustLogic.findAll(page, params);

        List<GroupCust> rsList = page.getResult();
        for (GroupCust dt : rsList) {
            dt.setCreditStatusName(DataDict.getDictName(DataDict.INTEGRALADJHIS_CREDITSTATUS, dt.getCreditStatus()));
            dt.setTypeName(DataDict.getDictName(DataDict.INDIVIDCUST_TYPE, dt.getType()));
            dt.setCategoryName(DataDict.getDictName(DataDict.GROUPCUST_CATEGORY, dt.getCategory()));
            dt.setNatureName(DataDict.getDictName(DataDict.GROUPCUST_NATURE, dt.getNature()));
            dt.setLvName(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
            dt.setEnableName(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
            dt.setActiveName(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));
        }
        renderJson(page);
    }

    public String save() {
        this.setOneToManyValue();
        model.setActive(1);// 活跃
        model.setEnable(1);// 启用
        model.setCreateTime(Calendar.getInstance());
        model.setLv(GlobalConst.CUST_LV_FANS);
        model.setCredit(0D);
        model.setCreditStatus(1);
        model.setCardNo(sqlLogic.getCnNum(GlobalConst.NUM_GROUP));
        model = groupCustLogic.persist(model);
        return dealJson(true, model);
    }

    public void edit() {
        model = groupCustLogic.findById(id);
        dealJson(true, model);
    }

    public void update() {
        groupCustLogic.merge(model);
        dealJson(true);
    }

    public void excel() {
        Map<String, Object> rsMap = null;
        try {
            FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
            rsMap = groupCustLogic.excel(fileInfoLogic.convert(fileInfo).getFile());
        } catch (Exception e) {
            rsMap = new HashMap<String, Object>();
            rsMap.put("success", false);
            rsMap.put("msg", e.getMessage());

            e.printStackTrace();
        }
        renderHtml(DataUtils.toJson(rsMap));
    }

    public void export() {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=GroupCust.xls");

        Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
        String[] sort = { "groupCustId,desc" };

        List<GroupCust> rsList = new ArrayList<GroupCust>();
        if (searchParam.size() > 0) {
            rsList = groupCustLogic.findAll(searchParam, sort);
        } else {
            rsList = groupCustLogic.findAll(sort);
        }

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("团体客户档案");
            sheet.createFreezePane(0, 1);// 冻结首行
            sheet.setDefaultColumnWidth(10);// 默认宽度

            HSSFCellStyle timeCellStyle = wb.createCellStyle();
            timeCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));
            sheet.setDefaultColumnStyle(10, timeCellStyle);
            sheet.setColumnWidth(10, 13 * 256);
            sheet.setColumnWidth(4, 15 * 256);
            sheet.setColumnWidth(1, 35 * 256);
            sheet.setColumnWidth(9, 25 * 256);

            HSSFRow headRow = sheet.createRow(0);
            headRow.createCell(0).setCellValue("会员卡号");
            headRow.createCell(1).setCellValue("单位名称");
            headRow.createCell(2).setCellValue("单位性质");
            headRow.createCell(3).setCellValue("行业类别");
            headRow.createCell(4).setCellValue("城市");
            headRow.createCell(5).setCellValue("会员等级");
            headRow.createCell(6).setCellValue("当前可用积分");
            headRow.createCell(7).setCellValue("使用状态");
            headRow.createCell(8).setCellValue("活跃状态");
            headRow.createCell(9).setCellValue("创建日期");

            GroupCust dt;
            for (int i = 0; i < rsList.size(); i++) {
                dt = rsList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(dt.getCardNo());
                row.createCell(1).setCellValue(dt.getGroupName());
                row.createCell(2).setCellValue(DataDict.getDictName(DataDict.GROUPCUST_NATURE, dt.getNature()));
                row.createCell(3).setCellValue(DataDict.getDictName(DataDict.GROUPCUST_CATEGORY, dt.getCategory()));
                row.createCell(5).setCellValue(DataDict.getDictName(DataDict.LV_TYPE, dt.getLv()));
                row.createCell(7).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_ENABLE, dt.getEnable()));
                row.createCell(8).setCellValue(DataDict.getDictName(DataDict.INDIVIDCUST_ACTIVESTATUS, dt.getActive()));

                if (dt.getCity() != null) {
                    row.createCell(4).setCellValue(dt.getCity());
                }
                if (dt.getCredit() != null) {
                    row.createCell(6).setCellValue(dt.getCredit());
                }
                if (dt.getCreateTime() != null) {
                    row.createCell(9).setCellValue(dt.getCreateTime());
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

    // 冻结/解冻
    public void chgEnable() {
        model = groupCustLogic.findById(id);
        model.setEnable(model.getEnable() == 1 ? 2 : 1);
        groupCustLogic.merge(model);
        dealJson(true);
    }

    // 失效
    public void custInvalid() {
        model = groupCustLogic.findById(id);
        model.setActive(4);
        groupCustLogic.merge(model);
        dealJson(true);
    }

    private void setOneToManyValue() {
        if (null != this.model.getProduct()) {
            for (Product product : this.model.getProduct()) {
                product.setGroupCust(model);
            }
        }
        if (null != this.model.getCompetitorProduct()) {
            for (CompetitorProduct competitor : this.model.getCompetitorProduct()) {
                competitor.setGroupCust(model);
            }
        }
    }

    public void getRel() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<Product> rsList = productLogic.findAll(params);
        renderJson(rsList);
    }

    public void getRel2() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<CompetitorProduct> rsList = competitorLogic.findAll(params);
        renderJson(rsList);
    }

    public void getRel3() {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request);
        List<IndividCust> rsList = individCustLogic.findAll(params);
        renderJson(rsList);
    }

}