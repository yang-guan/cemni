package com.huiju.expandbusi.individcompanalyze.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.expandbusi.individcompanalyze.eao.IndividCompAnalyzeEaoLocal;
import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "IndividCompAnalyzeBean")
@SuppressWarnings({ "rawtypes" })
public class IndividCompAnalyzeBean extends GenericLogicImpl<IndividCompAnalyze, Long> implements IndividCompAnalyzeRemote {
    @EJB
    private IndividCompAnalyzeEaoLocal individcompanalyzeEao;
    @EJB
    private IndicatorsRemote indicatorsLogic;

    @Override
    protected GenericEao<IndividCompAnalyze, Long> getGenericEao() {
        return individcompanalyzeEao;
    }

    @Override
    public Map excel(File file) {
        List<Indicators> dataList = new ArrayList<Indicators>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            Indicators dt;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new Indicators();
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

                        dt.setWorknumber(NeuUtils.cellFormatString(row.getCell(11)));
                        dt.setWorkName(NeuUtils.cellFormatString(row.getCell(12)));
                        dt.setPosition(NeuUtils.cellFormatString(row.getCell(13)));
                        dt.setLevelr(NeuUtils.cellFormatString(row.getCell(14)));
                        dt.setBaseRadix(NeuUtils.cellFormatDouble(row.getCell(15)));

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