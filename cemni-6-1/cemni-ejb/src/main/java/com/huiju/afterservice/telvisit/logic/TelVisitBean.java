package com.huiju.afterservice.telvisit.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.afterservice.telvisit.eao.TelVisitCustEaoLocal;
import com.huiju.afterservice.telvisit.eao.TelVisitEaoLocal;
import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.afterservice.telvisitrecord.eao.TelVisitRecordEaoLocal;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "TelVisitBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TelVisitBean extends GenericLogicImpl<TelVisit, Long> implements TelVisitRemote {
    @EJB
    private TelVisitEaoLocal telVisitEao;
    @EJB
    private TelVisitCustEaoLocal telVisitCustEao;
    @EJB
    private TelVisitRecordEaoLocal telVisitRecordEao;
    @EJB
    private IndividCustEaoLocal individCustEao;
    @EJB
    private SqlEaoLocal sqlEao;

    @Override
    protected GenericEao<TelVisit, Long> getGenericEao() {
        return telVisitEao;
    }

    @Override
    public void delete(Long id) {
        telVisitEao.executeSQLUpdate("delete from d_afterservice_telvisit_cust t where t.telvisitid = ?1", id);
        telVisitEao.executeSQLUpdate("delete from d_afterservice_telvisit t where t.telvisitid = ?1", id);
    }

    @Override
    public void telVisitPublish(Long id, String cuserCode) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into d_afterservice_telvisitrecord                                     ");
        jpql.append("  (telvisitrecordid,                                                          ");
        jpql.append("   backfs,                                                                    ");
        jpql.append("   backtype,                                                                  ");
        jpql.append("   backzt,                                                                    ");
        jpql.append("   cdate,                                                                     ");
        jpql.append("   cusercode,                                                                 ");
        jpql.append("   endrq,                                                                     ");
        jpql.append("   expiredtype,                                                               ");
        jpql.append("   startrq,                                                                   ");
        jpql.append("   storename,                                                                 ");
        jpql.append("   storeno,                                                                   ");
        jpql.append("   tasktype,                                                                  ");
        jpql.append("   telvisitrecordno,                                                          ");
        jpql.append("   individcustid,                                                             ");
        jpql.append("   telvisitid)                                                                ");
        jpql.append("  select seq_afterservice_telvisit_cust.nextval,                              ");
        jpql.append("         b.backfs,                                                            ");
        jpql.append("         c.fresh,                                                             ");
        jpql.append("         0,                                                                   ");
        jpql.append("         sysdate,                                                             ");
        jpql.append("         ?2,                                                                  ");
        jpql.append("         b.endrq,                                                             ");
        jpql.append("         0,                                                                   ");
        jpql.append("         b.startrq,                                                           ");
        jpql.append("         c.belongstorename,                                                   ");
        jpql.append("         c.belongstoreno,                                                     ");
        jpql.append("         b.tasktype,                                                          ");
        jpql.append("         'CL' || to_char(sysdate, 'yyyymmdd') || seq_telvisitrecordno.nextval,");
        jpql.append("         a.individcustid,                                                     ");
        jpql.append("         a.telvisitid                                                         ");
        jpql.append("    from d_afterservice_telvisit_cust a,                                      ");
        jpql.append("         d_afterservice_telvisit      b,                                      ");
        jpql.append("         d_archive_individcust        c                                       ");
        jpql.append("   where a.telvisitid = b.telvisitid                                          ");
        jpql.append("     and a.individcustid = c.individcustid                                    ");
        jpql.append("     and a.telvisitid = ?1                                                    ");

        telVisitEao.executeSQLUpdate(jpql.toString(), new Object[] { id, cuserCode });
        telVisitEao.executeSQLUpdate("update d_afterservice_telvisit set publishzt = 1 where telvisitid = ?1", id);
    }

    @Override
    public Map uploadExcel(File file) throws Exception {
        List<Map> rsList = new ArrayList<Map>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            int rownum = sheet.getLastRowNum();
            if (rownum <= 1000) {
                List<Map> rowList = new ArrayList<Map>();
                Map<String, Object> rowMap = null;

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        rowMap = new HashMap<String, Object>();
                        try {
                            rowMap.put("cardNo", NeuUtils.cellFormatString(row.getCell(0)));
                            rowMap.put("mobile", NeuUtils.cellFormatLong(row.getCell(1)));
                            rowList.add(rowMap);
                        } catch (Exception e) {
                            msg += "," + i;
                        }
                    }
                }
                if (StringUtils.isNotEmpty(msg)) {
                    msg = "数据异常的行号：" + msg.substring(1);
                } else if (CollectionUtils.isEmpty(rowList)) {
                    msg = "excel文件为空";
                } else {
                    String jpql = "select s from IndividCust s where s.cardNo = ?1 or s.mobile = ?2";
                    String cardNo;
                    Long mobile;
                    List<IndividCust> custList;
                    for (int i = 0; i < rowList.size(); i++) {
                        rowMap = rowList.get(i);
                        cardNo = String.valueOf(rowMap.get("cardNo"));
                        mobile = (null == rowMap.get("mobile") ? null : (Long) rowMap.get("mobile"));
                        if (StringUtils.isEmpty(cardNo) && mobile == null) {
                            msg += "," + (i + 1);
                        } else {
                            custList = individCustEao.executeQuery(jpql, new Object[] { cardNo, mobile });
                            if (CollectionUtils.isEmpty(custList)) {
                                msg += "," + (i + 1);
                            } else {
                                Map rMap;
                                for (IndividCust dt : custList) {
                                    rMap = new HashMap();
                                    rMap.put("individCustId", dt.getIndividCustId());
                                    rMap.put("cardNo", dt.getCardNo());
                                    rMap.put("name", dt.getName());
                                    rMap.put("active", dt.getActive());
                                    rMap.put("lv", dt.getLv());
                                    rMap.put("credit", dt.getCredit());
                                    rsList.add(rMap);
                                }
                            }
                        }
                    }
                    msg = "".equals(msg) ? msg : "数据异常的行号：" + msg.substring(1);
                }
            } else {
                msg = "Excel导入限制1000以内";
            }
        } catch (Exception e) {
            msg = e.toString();
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("dataList", DataUtils.toJson(rsList));
        rsMap.put("msg", msg);
        rsMap.put("success", StringUtils.isNotBlank(msg) ? false : true);
        return rsMap;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void saveTelVisit(TelVisit dt, Map<String, Object> searchParams) {
        Long seq = telVisitEao.getTelvisitSeq();
        telVisitEao.saveTelVisit(seq, dt);
        telVisitCustEao.saveTelVisitCust(seq, searchParams);
    }

    @Override
    public void updateTelVisit(TelVisit dt, Map<String, Object> searchParams) {
        telVisitEao.updateTelVisit(dt);
        telVisitCustEao.updateTelVisitCust(dt.getId(), searchParams);
    }

    @Override
    public void updateTelVisit(TelVisit dt, List<TelVisitCust> custList) {
        telVisitEao.updateTelVisit(dt);
        telVisitCustEao.saveTelVisitCust3(dt.getId(), custList);
    }

    @Override
    public void saveTelVisit(TelVisit dt, List<Map<String, Object>> paramsList) {
        Long seq = telVisitEao.getTelvisitSeq();
        telVisitEao.saveTelVisit(seq, dt);
        telVisitCustEao.saveIndividCustByExcel(seq, paramsList);
    }

    @Override
    public void updateTelVisitExcel(TelVisit dt, List<Map<String, Object>> paramsList) {
        telVisitEao.updateTelVisit(dt);
        telVisitCustEao.updateIndividCustByExcel(dt.getId(), paramsList);
    }

}