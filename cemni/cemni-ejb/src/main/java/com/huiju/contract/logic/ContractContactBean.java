package com.huiju.contract.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiju.contract.eao.ContractContactEaoLocal;
import com.huiju.contract.entity.ContractContact;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.excel.ExcelUtil;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "ContractContactBean")
public class ContractContactBean extends GenericLogicImpl<ContractContact, Long> implements ContractContactRemote {
    @EJB(mappedName = "ContractContactEaoBean")
    private ContractContactEaoLocal ContractContactEao;

    @Override
    protected GenericEao<ContractContact, Long> getGenericEao() {
        return ContractContactEao;
    }

    @Override
    public Map<String, Object> excel(File file, Long contractId) {
        List<ContractContact> dataList = new ArrayList<ContractContact>();
        String msg = "";

        try {
            Sheet sheet = ExcelUtil.getFirstSheet(file);
            Row row;
            ContractContact dt;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    dt = new ContractContact();
                    try {
                        dt.setContractId(contractId);
                        dt.setName(NeuUtils.cellFormatString(row.getCell(0)));
                        dt.setMobile(NeuUtils.cellFormatLong(row.getCell(1)));
                        dt.setType(NeuUtils.cellFormatInteger(row.getCell(2)));

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
                for (int i = 0; i < dataList.size(); i++) {
                    try {
                        this.persist(dataList.get(i));
                    } catch (Exception e) {
                        msg += "," + i;
                        e.printStackTrace();
                    }
                }
                msg = "".equals(msg) ? msg : "数据异常的行号：" + msg.substring(1);
            }
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