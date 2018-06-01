package com.huiju.report.membergrade.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "MemberGradeEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MemberGradeEaoBean extends GenericEaoImpl<Sql, Long> implements MemberGradeEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public List<Map> report(Map searchParam) {
        StringBuffer subJpql = new StringBuffer();
        Map<Integer, String> valueMap = new HashMap<Integer, String>();

        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("EQ_bigAreaId")) {
                subJpql.append(" and n.bigAreaId = ?1");
                valueMap.put(1, searchParam.get(key).toString());
            } else if (key.equals("EQ_areaId")) {
                subJpql.append(" and n.areaId = ?2");
                valueMap.put(2, searchParam.get(key).toString());
            } else if (key.equals("EQ_storeNo")) {
                subJpql.append(" and n.storeNo in ('" + searchParam.get(key).toString().replaceAll(",", "','") + "')");
            } else if (key.equals("EQ_lvName")) {
                subJpql.append(" and m.lvName = ?3");
                valueMap.put(3, searchParam.get(key).toString());
            } else if (key.equals("GE_posbilldate")) {
                subJpql.append(" and m.posbilldate >=  to_date(?4,'yyyy-mm-dd')");
                valueMap.put(4, searchParam.get(key).toString());
            } else if (key.equals("LE_posbilldate")) {
                subJpql.append(" and m.posbilldate <=  to_date(?5,'yyyy-mm-dd') + 1");
                valueMap.put(5, searchParam.get(key).toString());
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("select (select a.name from d_cn_org a where a.orgcode = y.bigareano),           ");
        jpql.append("       y.storename,                                                             ");
        jpql.append("       y.lvName,                                                                ");
        jpql.append("       y.activeperson,                                                          ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.activeperson) over() * 100, 2), 0), '990.99') || '%', ");
        jpql.append("       y.activemoney,                                                                    ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.activemoney) over() * 100, 2), 0), '990.99') || '%',  ");
        jpql.append("       y.sleepperson,                                                                    ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.sleepperson) over() * 100, 2), 0), '990.99') || '%',  ");
        jpql.append("       y.sleepmoney,                                                                     ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.sleepmoney) over() * 100, 2), 0), '990.99') || '%',   ");
        jpql.append("       y.historyperson,                                                                  ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.historyperson) over() * 100, 2), 0), '990.99') || '%',");
        jpql.append("       y.historymoney,                                                                   ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.historymoney) over() * 100, 2), 0), '990.99') || '%', ");
        jpql.append("       y.invalidperson,                                                                  ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.invalidperson) over() * 100, 2), 0), '990.99') || '%',");
        jpql.append("       y.invalidmoney,                                                                   ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.invalidmoney) over() * 100, 2), 0), '990.99') || '%', ");
        jpql.append("       sumperson,                                                                        ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.sumperson) over() * 100, 2), 0), '990.99') || '%',    ");
        jpql.append("       summoney,                                                                         ");
        jpql.append("       to_char(nvl(round(ratio_to_report(y.summoney) over() * 100, 2), 0), '990.99') || '%'      ");
        jpql.append("  from (select x.bigareano,                                                       ");
        jpql.append("               x.storeno,                                                         ");
        jpql.append("               x.storename,                                                       ");
        jpql.append("               x.lvName,                                                          ");
        jpql.append("               sum(decode(x.active, 1, 1, 0)) as activeperson,                    ");
        jpql.append("               sum(decode(x.active, 1, x.jeweldiscountamount, 0)) as activemoney, ");
        jpql.append("               sum(decode(x.active, 2, 1, 0)) as sleepperson,                     ");
        jpql.append("               sum(decode(x.active, 2, x.jeweldiscountamount, 0)) as sleepmoney,  ");
        jpql.append("               sum(decode(x.active, 3, 1, 0)) as historyperson,                   ");
        jpql.append("               sum(decode(x.active, 3, x.jeweldiscountamount, 0)) as historymoney,");
        jpql.append("               sum(decode(x.active, 4, 1, 0)) as invalidperson,                   ");
        jpql.append("               sum(decode(x.active, 4, x.jeweldiscountamount, 0)) as invalidmoney,");
        jpql.append("               count(x.individcustid) sumperson,                                  ");
        jpql.append("               sum(nvl(x.jeweldiscountamount, 0)) summoney                        ");
        jpql.append("          from (select m.individcustid,                                           ");
        jpql.append("                       n.bigareano,                                               ");
        jpql.append("                       m.storeno,                                                 ");
        jpql.append("                       m.storename,                                               ");
        jpql.append("                       m.lvName,                                                  ");
        jpql.append("                       m.jeweldiscountamount,                                     ");
        jpql.append("                       m.active                                                   ");
        jpql.append("                  from d_pos_order m, d_cn_store n                                ");
        jpql.append("                 where m.storeno = n.storeno(+)                                   ").append(subJpql.toString());
        jpql.append("                   and m.storeno is not null                                      ");
        jpql.append("                   and m.lvName is not null                                       ");
        jpql.append("                   and m.active is not null) x                                    ");
        jpql.append("         group by x.bigareano, x.storeno, x.storename, x.lvName) y                ");
        jpql.append(" order by y.bigareano, y.storeno, y.lvName                                        ");

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        List<Object[]> qryList = qry.getResultList();

        List<Map> rsList = new ArrayList<Map>();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("bigAreaName", rs[0]);
            map.put("storeName", rs[1]);
            map.put("lvName", rs[2]);
            map.put("activePerson", rs[3]);
            map.put("percentActivePerson", rs[4]);
            map.put("activeMoney", rs[5]);
            map.put("percentActiveMoney", rs[6]);
            map.put("sleepPerson", rs[7]);
            map.put("percentSleepPerson", rs[8]);
            map.put("sleepMoney", rs[9]);
            map.put("percentSleepMoney", rs[10]);
            map.put("historyPerson", rs[11]);
            map.put("percentHistoryPerson", rs[12]);
            map.put("historyMoney", rs[13]);
            map.put("percentHistoryMoney", rs[14]);
            map.put("invalidPerson", rs[15]);
            map.put("percentInvalidPerson", rs[16]);
            map.put("invalidMoney", rs[17]);
            map.put("percentInvalidMoney", rs[18]);
            map.put("sumPerson", rs[19]);
            map.put("percentSumPerson", rs[20]);
            map.put("sumMoney", rs[21]);
            map.put("percentSumMoney", rs[22]);
            rsList.add(map);
        }
        return rsList;
    }

}