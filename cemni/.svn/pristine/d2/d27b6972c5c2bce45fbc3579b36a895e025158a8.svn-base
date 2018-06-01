package com.huiju.archive.groupcust.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.archive.groupcust.eao.GroupCustEaoLocal;
import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.eao.SqlEaoLocal;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "GroupCustBean")
public class GroupCustBean extends GenericLogicImpl<GroupCust, Long> implements GroupCustRemote {
    @EJB
    private GroupCustEaoLocal groupCustEao;
    @EJB
    private SqlEaoLocal sqlEao;

    @Override
    protected GenericEao<GroupCust, Long> getGenericEao() {
        return groupCustEao;
    }

    @Override
    public Map<String, Object> excel(File file) {
        String msg = "";
        boolean falg = false;
        try {
            List<GroupCust> dataList = new ArrayList<GroupCust>();
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    GroupCust dt = new GroupCust();
                    try {
                        dt.setGroupName(NeuUtils.cellFormatString(row.getCell(0)));
                        dt.setType(NeuUtils.cellFormatInteger(row.getCell(1)));
                        dt.setCategory(NeuUtils.cellFormatInteger(row.getCell(2)));
                        dt.setNature(NeuUtils.cellFormatInteger(row.getCell(3)));
                        dt.setRemark(NeuUtils.cellFormatString(row.getCell(4)));
                        dt.setVatNo(NeuUtils.cellFormatString(row.getCell(5)));
                        dt.setBusinessLicense(NeuUtils.cellFormatString(row.getCell(6)));
                        dt.setArtificialPerson(NeuUtils.cellFormatString(row.getCell(7)));
                        dt.setCity(NeuUtils.cellFormatString(row.getCell(8)));
                        dt.setDmName(NeuUtils.cellFormatString(row.getCell(9)));
                        dt.setDmMobile(NeuUtils.cellFormatLong(row.getCell(10)));
                        dt.setDmTitle(NeuUtils.cellFormatString(row.getCell(11)));
                        dt.setDmHobby(NeuUtils.cellFormatString(row.getCell(12)));
                        dt.setAddress(NeuUtils.cellFormatString(row.getCell(13)));
                        dt.setBusinessRequirement(NeuUtils.cellFormatString(row.getCell(14)));
                        dt.setNote(NeuUtils.cellFormatString(row.getCell(15)));
                        dt.setNcNo(NeuUtils.cellFormatString(row.getCell(16)));

                        dataList.add(dt);
                    } catch (Exception e) {
                        msg += "," + i;
                        e.printStackTrace();
                    }
                }
            }

            if (StringUtils.isNotBlank(msg)) {
                msg = "数据异常的行号：" + msg.substring(1);
            } else if (CollectionUtils.isEmpty(dataList)) {
                msg = "excel文件为空";
            } else {
                // 保存解析结果
                for (GroupCust dt : dataList) {
                    dt.setCardNo(sqlEao.getCnNum(GlobalConst.NUM_GROUP));
                    dt.setActive(GlobalConst.ACTIVE_POSTIVE);
                    dt.setEnable(1);
                    dt.setCreateTime(Calendar.getInstance());
                    dt.setLv(GlobalConst.CUST_LV_FANS);
                    dt.setCreditStatus(1);
                    this.persist(dt);
                }
                falg = true;
            }
        } catch (Exception e) {
            msg = "excel数据错误，请检查！";
            e.printStackTrace();
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("msg", msg);
        rsMap.put("success", falg);
        return rsMap;
    }

}