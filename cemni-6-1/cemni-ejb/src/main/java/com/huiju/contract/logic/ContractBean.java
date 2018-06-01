package com.huiju.contract.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.GlobalConst;
import com.huiju.contract.eao.ContractEaoLocal;
import com.huiju.contract.entity.Contract;
import com.huiju.inter.sms.YunpianSmsRemote;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.util.CollectionUtils;

@Stateless(mappedName = "ContractBean")
@SuppressWarnings("rawtypes")
public class ContractBean extends GenericLogicImpl<Contract, Long> implements ContractRemote {
    @EJB
    private ContractEaoLocal contractEao;

    @EJB(mappedName = "YunpianSms")
    private YunpianSmsRemote yunpianSms;

    @Override
    protected GenericEao<Contract, Long> getGenericEao() {
        return contractEao;
    }

    @Override
    public void contractWarning() {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select b.mobile,                                 ");
        jpql.append("       a.contractnum,                            ");
        jpql.append("       a.contractname,                           ");
        jpql.append("       to_char(a.invdate, 'yyyy-mm-dd')          ");
        jpql.append("  from d_contract a, d_contract_contacts b       ");
        jpql.append(" where a.contractid = b.contractid               ");
        jpql.append("   and b.mobile is not null                      ");
        jpql.append("   and a.invdate between sysdate and sysdate + 91");
        jpql.append("   and (trunc(a.invdate - sysdate) = 90 or       ");
        jpql.append("       (b.type = 1 and mod(trunc(a.invdate - sysdate), 7) = 0) or");
        jpql.append("       (b.type = 2 and mod(trunc(a.invdate - sysdate), 15) = 0))");

        List<Object[]> rsList = contractEao.executeSQLQuery(jpql.toString());
        if (!CollectionUtils.isEmpty(rsList)) {
            List<Long> mobile = new ArrayList<Long>();
            List<String> text = new ArrayList<String>();
            for (Object[] rs : rsList) {
                mobile.add(((BigDecimal) rs[0]).longValue());
                text.add("尊敬的用户您好，与您相关的“" + rs[2].toString() + "（" + rs[1].toString() + "）”合同将于" + rs[3].toString() + "日到期，敬请关注。");
            }
            yunpianSms.multiSends(null, GlobalConst.SMS_TYPE_14, mobile, text);
        }
    }

    @Override
    public Set<Integer> getContractType(List rsList) {
        Set<Integer> set = new HashSet<Integer>();
        set.add(6);// 所有用户均可查看服务类合同

        for (int i = 0; i < rsList.size(); i++) {
            Map map = (Map) rsList.get(i);
            Integer type = map.get("type") == null ? null : Integer.parseInt(map.get("type").toString());
            String orgcode = map.get("orgcode") == null ? null : map.get("orgcode").toString();

            if (type == GlobalConst.ORG_TYPE_2) {
                if (null != orgcode) {
                    if (orgcode.equals(GlobalConst.DEP_ORGCODE_SP)) {
                        set.add(GlobalConst.CONTRACT_TYPE_GY);
                    } else if (orgcode.equals(GlobalConst.DEP_ORGCODE_LZ1)) {
                        set.add(GlobalConst.CONTRACT_TYPE_QD);
                    } else if (orgcode.equals(GlobalConst.DEP_ORGCODE_LZ2)) {
                        set.add(GlobalConst.CONTRACT_TYPE_QD);
                    } else if (orgcode.equals(GlobalConst.DEP_ORGCODE_PP)) {
                        set.add(GlobalConst.CONTRACT_TYPE_YY);
                    } else if (orgcode.equals(GlobalConst.DEP_ORGCODE_XZ)) {
                        set.add(GlobalConst.CONTRACT_TYPE_XZ);
                    } else if (orgcode.equals(GlobalConst.DEP_ORGCODE_YY)) {
                        set.add(GlobalConst.CONTRACT_TYPE_JM);
                    }
                }
            } 
        }
        return set;
    }

}