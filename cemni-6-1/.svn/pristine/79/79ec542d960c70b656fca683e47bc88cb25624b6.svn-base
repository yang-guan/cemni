package com.huiju.salesment.scaletarget.logic;

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
import com.huiju.salesment.scaletarget.eao.ScaleTargetEaoLocal;
import com.huiju.salesment.scaletarget.entity.ScaleDetails;
import com.huiju.salesment.scaletarget.entity.ScaleTarget;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "ScaleTargetBean")
@SuppressWarnings({ "rawtypes" })
public class ScaleTargetBean extends GenericLogicImpl<ScaleTarget, Long> implements ScaleTargetRemote {
    @EJB(mappedName = "ScaleTargetEaoBean")
    private ScaleTargetEaoLocal scaletargetEao;

    @Override
    protected GenericEao<ScaleTarget, Long> getGenericEao() {
        return scaletargetEao;
    }

    @Override
    public Map excel(File file) {
        List<ScaleDetails> dataList = new ArrayList<ScaleDetails>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            ScaleDetails dt;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new ScaleDetails();
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

                        dt.setScaleType(NeuUtils.cellFormatInteger(row.getCell(11)));
                        dt.setScaleTypeName(row.getCell(12).getStringCellValue());

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