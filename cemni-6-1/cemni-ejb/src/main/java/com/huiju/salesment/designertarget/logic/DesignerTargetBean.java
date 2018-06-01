package com.huiju.salesment.designertarget.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.salesment.designertarget.eao.DesignerTargetEaoLocal;
import com.huiju.salesment.designertarget.entity.DesignerDetails;
import com.huiju.salesment.designertarget.entity.DesignerTarget;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "DesignerTargetBean")
@SuppressWarnings({ "rawtypes" })
public class DesignerTargetBean extends GenericLogicImpl<DesignerTarget, Long> implements DesignerTargetRemote {
    @EJB(mappedName = "DesignerTargetEaoBean")
    private DesignerTargetEaoLocal designertargetEao;

    @Override
    protected GenericEao<DesignerTarget, Long> getGenericEao() {
        return designertargetEao;
    }

    @Override
    public Map excel(File file) {
        List<DesignerDetails> dataList = new ArrayList<DesignerDetails>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            DesignerDetails dt;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new DesignerDetails();
                    try {
                        dt.setBigAreaId(NeuUtils.cellFormatLong(row.getCell(0)));
                        dt.setBigAreaNo(row.getCell(1).getStringCellValue());
                        dt.setBigAreaName(row.getCell(2).getStringCellValue());
                        dt.setAreaId(NeuUtils.cellFormatLong(row.getCell(3)));
                        dt.setAreaNo(row.getCell(4).getStringCellValue());
                        dt.setAreaName(row.getCell(5).getStringCellValue());
                        dt.setStoreId(NeuUtils.cellFormatLong(row.getCell(6)));
                        dt.setStoreNo(row.getCell(7).getStringCellValue());
                        dt.setName(row.getCell(8).getStringCellValue());
                        dt.setAttr(NeuUtils.cellFormatInteger(row.getCell(9)));
                        dt.setAttrName(row.getCell(10).getStringCellValue());

                        dt.setDesType(NeuUtils.cellFormatInteger(row.getCell(11)));
                        dt.setDesTypeName(row.getCell(12).getStringCellValue());

                        dt.setJan(NeuUtils.cellFormatDouble(row.getCell(13)));
                        dt.setFeb(NeuUtils.cellFormatDouble(row.getCell(14)));
                        dt.setMar(NeuUtils.cellFormatDouble(row.getCell(15)));
                        dt.setApr(NeuUtils.cellFormatDouble(row.getCell(16)));
                        dt.setMay(NeuUtils.cellFormatDouble(row.getCell(17)));
                        dt.setJun(NeuUtils.cellFormatDouble(row.getCell(18)));
                        dt.setJul(NeuUtils.cellFormatDouble(row.getCell(19)));
                        dt.setAug(NeuUtils.cellFormatDouble(row.getCell(20)));
                        dt.setSep(NeuUtils.cellFormatDouble(row.getCell(21)));
                        dt.setOct(NeuUtils.cellFormatDouble(row.getCell(22)));
                        dt.setNov(NeuUtils.cellFormatDouble(row.getCell(23)));
                        dt.setDec(NeuUtils.cellFormatDouble(row.getCell(24)));

                        dataList.add(dt);
                    } catch (Exception e) {
                        msg += "," + i;
                        e.printStackTrace();
                    }
                }
            }
            msg = ("".equals(msg) ? msg : "数据异常的行号：" + msg.substring(1));
        } catch (Exception e) {
            msg = e.toString();
            e.printStackTrace();
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("dataList", dataList);
        rsMap.put("msg", msg);
        rsMap.put("success", "".equals(msg) ? true : false);
        return rsMap;
    }

}