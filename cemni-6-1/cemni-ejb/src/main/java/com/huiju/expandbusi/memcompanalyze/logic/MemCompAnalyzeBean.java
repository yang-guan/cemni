package com.huiju.expandbusi.memcompanalyze.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.expandbusi.memcompanalyze.eao.MemCompAnalyzeEaoLocal;
import com.huiju.expandbusi.memcompanalyze.entity.MemCompAnalyze;
import com.huiju.expandbusi.memcompanalyze.entity.Memdetail;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "MemCompAnalyzeBean")
@SuppressWarnings({ "rawtypes" })
public class MemCompAnalyzeBean extends GenericLogicImpl<MemCompAnalyze, Long> implements MemCompAnalyzeRemote {
    @EJB(mappedName = "MemCompAnalyzeEaoBean")
    private MemCompAnalyzeEaoLocal memcompanalyzeEao;

    @EJB(mappedName = "MemdetailBean")
    private MemdetailRemote memdetailLogic;

    @Override
    protected GenericEao<MemCompAnalyze, Long> getGenericEao() {
        return memcompanalyzeEao;
    }

    @Override
    public Map excel(File file) {
        List<Memdetail> dataList = new ArrayList<Memdetail>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            Memdetail dt;
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new Memdetail();
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

                        dt.setJanipeople(NeuUtils.cellFormatInteger(row.getCell(11)));
                        dt.setJaniprice(NeuUtils.cellFormatDouble(row.getCell(12)));
                        dt.setJanfpeople(NeuUtils.cellFormatInteger(row.getCell(13)));
                        dt.setJanfprice(NeuUtils.cellFormatDouble(row.getCell(14)));
                        dt.setFebipeople(NeuUtils.cellFormatInteger(row.getCell(15)));
                        dt.setFebiprice(NeuUtils.cellFormatDouble(row.getCell(16)));
                        dt.setFebfpeople(NeuUtils.cellFormatInteger(row.getCell(17)));
                        dt.setFebfprice(NeuUtils.cellFormatDouble(row.getCell(18)));
                        dt.setMaripeople(NeuUtils.cellFormatInteger(row.getCell(19)));
                        dt.setMariprice(NeuUtils.cellFormatDouble(row.getCell(20)));
                        dt.setMarfpeople(NeuUtils.cellFormatInteger(row.getCell(21)));
                        dt.setMarfprice(NeuUtils.cellFormatDouble(row.getCell(22)));
                        dt.setApripeople(NeuUtils.cellFormatInteger(row.getCell(23)));
                        dt.setApriprice(NeuUtils.cellFormatDouble(row.getCell(24)));
                        dt.setAprfpeople(NeuUtils.cellFormatInteger(row.getCell(25)));
                        dt.setAprfprice(NeuUtils.cellFormatDouble(row.getCell(26)));
                        dt.setMayipeople(NeuUtils.cellFormatInteger(row.getCell(27)));
                        dt.setMayiprice(NeuUtils.cellFormatDouble(row.getCell(28)));
                        dt.setMayfpeople(NeuUtils.cellFormatInteger(row.getCell(29)));
                        dt.setMayfprice(NeuUtils.cellFormatDouble(row.getCell(30)));
                        dt.setJunipeople(NeuUtils.cellFormatInteger(row.getCell(31)));
                        dt.setJuniprice(NeuUtils.cellFormatDouble(row.getCell(32)));
                        dt.setJunfpeople(NeuUtils.cellFormatInteger(row.getCell(33)));
                        dt.setJunfprice(NeuUtils.cellFormatDouble(row.getCell(34)));
                        dt.setJulipeople(NeuUtils.cellFormatInteger(row.getCell(35)));
                        dt.setJuliprice(NeuUtils.cellFormatDouble(row.getCell(36)));
                        dt.setJulfpeople(NeuUtils.cellFormatInteger(row.getCell(37)));
                        dt.setJulfprice(NeuUtils.cellFormatDouble(row.getCell(38)));
                        dt.setAugipeople(NeuUtils.cellFormatInteger(row.getCell(39)));
                        dt.setAugiprice(NeuUtils.cellFormatDouble(row.getCell(40)));
                        dt.setAugfpeople(NeuUtils.cellFormatInteger(row.getCell(41)));
                        dt.setAugfprice(NeuUtils.cellFormatDouble(row.getCell(42)));
                        dt.setSepipeople(NeuUtils.cellFormatInteger(row.getCell(43)));
                        dt.setSepiprice(NeuUtils.cellFormatDouble(row.getCell(44)));
                        dt.setSepfpeople(NeuUtils.cellFormatInteger(row.getCell(45)));
                        dt.setSepfprice(NeuUtils.cellFormatDouble(row.getCell(46)));
                        dt.setOctipeople(NeuUtils.cellFormatInteger(row.getCell(47)));
                        dt.setOctiprice(NeuUtils.cellFormatDouble(row.getCell(48)));
                        dt.setOctfpeople(NeuUtils.cellFormatInteger(row.getCell(49)));
                        dt.setOctfprice(NeuUtils.cellFormatDouble(row.getCell(50)));
                        dt.setNovipeople(NeuUtils.cellFormatInteger(row.getCell(51)));
                        dt.setNoviprice(NeuUtils.cellFormatDouble(row.getCell(52)));
                        dt.setNovfpeople(NeuUtils.cellFormatInteger(row.getCell(53)));
                        dt.setNovfprice(NeuUtils.cellFormatDouble(row.getCell(54)));
                        dt.setDecipeople(NeuUtils.cellFormatInteger(row.getCell(55)));
                        dt.setDeciprice(NeuUtils.cellFormatDouble(row.getCell(56)));
                        dt.setDecfpeople(NeuUtils.cellFormatInteger(row.getCell(57)));
                        dt.setDecfprice(NeuUtils.cellFormatDouble(row.getCell(58)));

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