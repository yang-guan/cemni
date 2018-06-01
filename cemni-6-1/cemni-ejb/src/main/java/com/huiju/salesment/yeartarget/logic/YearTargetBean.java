package com.huiju.salesment.yeartarget.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.common.GlobalConst;
import com.huiju.expandbusi.individcompanalyze.eao.IndicatorsEaoLocal;
import com.huiju.expandbusi.individcompanalyze.eao.IndividCompAnalyzeEaoLocal;
import com.huiju.expandbusi.individcompanalyze.entity.Indicators;
import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.salesment.yeartarget.eao.YearTargetEaoLocal;
import com.huiju.salesment.yeartarget.entity.YearDetails;
import com.huiju.salesment.yeartarget.entity.YearTarget;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "YearTargetBean")
@SuppressWarnings({ "rawtypes" })
public class YearTargetBean extends GenericLogicImpl<YearTarget, Long> implements YearTargetRemote {
    @EJB(mappedName = "YearTargetEaoBean")
    private YearTargetEaoLocal yeartargetEao;

    @EJB(mappedName = "IndividCompAnalyzeEaoBean")
    private IndividCompAnalyzeEaoLocal individcompanalyzeEao;

    @EJB(mappedName = "IndicatorsEaoBean")
    private IndicatorsEaoLocal indicatorsEao;

    @Override
    protected GenericEao<YearTarget, Long> getGenericEao() {
        return yeartargetEao;
    }

    @Override
    public void updYearTargetIndividComp(YearTarget yt) {
        List<YearDetails> yearDetailList = yt.getYearDetails();

        List<IndividCompAnalyze> indCompList = individcompanalyzeEao.executeQuery("select s from IndividCompAnalyze s where s.year = ?1", new Object[] { yt.getPartYear() });
        for (IndividCompAnalyze indComp : indCompList) {
            // 门店每月金额
            Map<Long, Double> storeMoneyMap = new HashMap<Long, Double>();
            for (YearDetails dt : yearDetailList) {
                Double moneyAmount = null;
                switch (indComp.getMonth()) {
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
                storeMoneyMap.put(dt.getStoreId(), moneyAmount);
            }

            List<Indicators> indList = indComp.getIndicators();
            // 店员占比
            Map<Long, Double> baseRadixMap = new HashMap<Long, Double>();
            Long storeId;
            Double baseRadix;
            for (Indicators dt : indList) {
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
            for (Indicators dt : indList) {
                Double total = storeMoneyMap.get(dt.getStoreId());
                // 店长特殊处理
                if (dt.getPosition() != null && dt.getPosition().equals(GlobalConst.TARGET_POS_STOREMGR)) {
                    dt.setMoneyAmount(total);
                } else {
                    if (dt.getBaseRadix() != null && total != null) {
                        dt.setMoneyAmount(NeuUtils.formatDouble(dt.getBaseRadix() / baseRadixMap.get(dt.getStoreId()) * total));
                    }
                }
                indicatorsEao.merge(dt);
            }
        }
        this.merge(yt);
    }

    @Override
    public Map excel(File file) {
        List<YearDetails> dataList = new ArrayList<YearDetails>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            YearDetails dt;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new YearDetails();
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

                        dt.setJan(NeuUtils.cellFormatDouble(row.getCell(11)));
                        dt.setFeb(NeuUtils.cellFormatDouble(row.getCell(12)));
                        dt.setMar(NeuUtils.cellFormatDouble(row.getCell(13)));
                        dt.setApr(NeuUtils.cellFormatDouble(row.getCell(14)));
                        dt.setMay(NeuUtils.cellFormatDouble(row.getCell(15)));
                        dt.setJun(NeuUtils.cellFormatDouble(row.getCell(16)));
                        dt.setJul(NeuUtils.cellFormatDouble(row.getCell(17)));
                        dt.setAug(NeuUtils.cellFormatDouble(row.getCell(18)));
                        dt.setSep(NeuUtils.cellFormatDouble(row.getCell(19)));
                        dt.setOct(NeuUtils.cellFormatDouble(row.getCell(20)));
                        dt.setNov(NeuUtils.cellFormatDouble(row.getCell(21)));
                        dt.setDec(NeuUtils.cellFormatDouble(row.getCell(22)));

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