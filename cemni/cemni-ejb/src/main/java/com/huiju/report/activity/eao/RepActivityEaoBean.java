package com.huiju.report.activity.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.GlobalConst;
import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "RepActivityEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RepActivityEaoBean extends GenericEaoImpl<Sql, Long> implements RepActivityEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public Map report(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT * FROM (SELECT k.*, ROWNUM rnum FROM (").append(prefixJpql).append(suffixJpql).append(" order by activityno) k WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        qry.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        qry.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("activityNo", rs[0]);
            map.put("beginTime", rs[1]);
            map.put("endTime", rs[2]);
            map.put("activityType", rs[3]);
            map.put("activityTypeName", rs[4]);
            map.put("activityTheme", rs[5]);
            map.put("activityForm", rs[6]);
            map.put("activityFormName", rs[7]);
            map.put("orgName", rs[8]);
            map.put("auditCost", rs[9]);
            map.put("joinCount", rs[10]);
            map.put("totalCount", rs[11]);
            map.put("jewelDiscountAmount", rs[12]);
            map.put("actualCost", rs[13]);
            map.put("costPercent", rs[14]);
            map.put("xfCount", rs[15]);
            rsList.add(map);
        }

        Query cntQry = em.createNativeQuery("select count(1) " + suffixJpql);
        for (Integer k : valueMap.keySet()) {
            cntQry.setParameter(k, valueMap.get(k));
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("records", rsList);
        rsMap.put("totalCount", cntQry.getSingleResult());
        em.clear();
        return rsMap;
    }

    @Override
    public List qryRepAct(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        String jpql = prefixJpql + suffixJpql + " order by activityno";

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("activityNo", rs[0]);
            map.put("beginTime", rs[1]);
            map.put("endTime", rs[2]);
            map.put("activityType", rs[3]);
            map.put("activityTypeName", rs[4]);
            map.put("activityTheme", rs[5]);
            map.put("activityForm", rs[6]);
            map.put("activityFormName", rs[7]);
            map.put("orgName", rs[8]);
            map.put("auditCost", rs[9]);
            map.put("joinCount", rs[10]);
            map.put("totalCount", rs[11]);
            map.put("jewelDiscountAmount", rs[12]);
            map.put("actualCost", rs[13]);
            map.put("costPercent", rs[14]);
            map.put("xfCount", rs[15]);

            rsList.add(map);
        }
        return rsList;
    }

    private String getPrefixJpql(Map searchParam, Map valueMap) {
        StringBuffer prefix = new StringBuffer();
        prefix.append("select activityno,                                             ");
        prefix.append("       to_char(begintime, 'yyyy-mm-dd'),                       ");
        prefix.append("       to_char(endtime, 'yyyy-mm-dd'),                         ");
        prefix.append("       activitytype,                                           ");
        prefix.append("       (select x.name from d_data_dict x where x.dictcode = 6100 and x.value = activitytype),");
        prefix.append("       activitytheme,                                          ");
        prefix.append("       activityform,                                           ");
        prefix.append("       (select x.name from d_data_dict x where x.dictcode = 6101 and x.value = activityform),");
        prefix.append("       (select x.name from d_cn_org x where x.orgid = h.orgid),");
        prefix.append("       auditcost,                                              ");
        prefix.append("       joincount,                                              ");
        prefix.append("       totalcount,                                             ");
        prefix.append("       jeweldiscountamount,                                    ");
        prefix.append("       actualcost,                                             ");
        prefix.append("       to_char(decode(nvl(jeweldiscountamount, 0), 0, 0, round(nvl(auditcost, 0) / jeweldiscountamount, 4) * 100), '990.99') || '%',");
        prefix.append("       xfcount                                                 ");
        return prefix.toString();
    }

    private String getSuffixJpql(Map searchParam, Map valueMap) {
        StringBuffer subJpql = new StringBuffer();
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("LIKE_activityTheme")) {
                subJpql.append(" and a.activitytheme like ?1");
                valueMap.put(1, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("EQ_activityType")) {
                subJpql.append(" and a.activitytype = ?2");
                valueMap.put(2, searchParam.get(key).toString());
            } else if (key.equals("EQ_activityForm")) {
                subJpql.append(" and a.activityform = ?3");
                valueMap.put(3, searchParam.get(key).toString());
            } else if (key.equals("GE_beginTime")) {
                subJpql.append(" and a.begintime >=  to_date(?4, 'yyyy-mm-dd')");
                valueMap.put(4, searchParam.get(key).toString());
            } else if (key.equals("LE_beginTime")) {
                subJpql.append(" and a.begintime <= to_date(?5, 'yyyy-mm-dd') + 1");
                valueMap.put(5, searchParam.get(key).toString());
            }
        }
        Object IN_orgCodeObj = searchParam.get("IN_orgCode");
        if (IN_orgCodeObj != null) {
            String IN_orgCode = IN_orgCodeObj.toString();
            if (StringUtils.isNotBlank(IN_orgCode)) {
                subJpql.append(" and a.orgcode in ('" + IN_orgCode.replaceAll(",", "','") + "')");
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("  from (select a.activityno,                                                ");
        jpql.append("               a.begintime,                                                 ");
        jpql.append("               a.endtime,                                                   ");
        jpql.append("               a.activitytype,                                              ");
        jpql.append("               a.activitytheme,                                             ");
        jpql.append("               a.activityform,                                              ");
        jpql.append("               a.orgid,                                                     ");
        jpql.append("               a.auditcost,                                                 ");
        jpql.append("               count(distinct(decode(b.ispartin, 1, b.individcustid))) as joincount,");
        jpql.append("               (select count(1) from d_activity_indipartin d where d.activityid = a.activityid) totalcount,");
        jpql.append("               sum(nvl(c.jeweldiscountamount, 0)) as jeweldiscountamount,   ");
        jpql.append("               sum(c.actualsaleamount) as actualcost,                       ");
        jpql.append("               count(distinct c.individcustid) as xfcount                   ");
        jpql.append("          from d_activity a, d_activity_indipartin b, d_pos_order c         ");
        jpql.append("         where a.activityid = b.activityid                                  ");
        jpql.append("           and b.individcustid = c.individcustid                            ");
        jpql.append("           and a.auditstatus = 2                                            ");
        jpql.append("           and c.posbilldate between a.begintime and add_months(a.begintime, 3)").append(subJpql.toString());
        jpql.append("         group by a.activityno,                                             ");
        jpql.append("                  a.activityid,                                             ");
        jpql.append("                  a.begintime,                                              ");
        jpql.append("                  a.endtime,                                                ");
        jpql.append("                  a.activitytype,                                           ");
        jpql.append("                  a.activitytheme,                                          ");
        jpql.append("                  a.activityform,                                           ");
        jpql.append("                  a.auditcost,                                              ");
        jpql.append("                  a.orgid                                                   ");
        jpql.append("        union                                                               ");
        jpql.append("        select a.activityno,                                                ");
        jpql.append("               a.begintime,                                                 ");
        jpql.append("               a.endtime,                                                   ");
        jpql.append("               a.activitytype,                                              ");
        jpql.append("               a.activitytheme,                                             ");
        jpql.append("               a.activityform,                                              ");
        jpql.append("               a.orgid,                                                     ");
        jpql.append("               a.auditcost,                                                 ");
        jpql.append("               count(distinct(decode(b.ispartin, 1, b.franchiseeid))) as joincount,");
        jpql.append("               (select count(1) from d_activity_indipartin d where d.activityid = a.activityid) totalcount,");
        jpql.append("               null as jeweldiscountamount,                                 ");
        jpql.append("               sum(nvl(d.totalamount, 0)) as actualcost,                    ");
        jpql.append("               count(distinct b.frapartinid) as xfcount                     ");
        jpql.append("          from d_activity           a,                                      ");
        jpql.append("               d_activity_frapartin b,                                      ");
        jpql.append("               d_franchisee         c,                                      ");
        jpql.append("               d_sale_order         d                                       ");
        jpql.append("         where a.activityid = b.activityid                                  ");
        jpql.append("           and b.franchiseeid = c.franchiseeid                              ");
        jpql.append("           and c.fracode = d.fracode                                        ");
        jpql.append("           and a.auditstatus = 2                                            ");
        jpql.append("           and ((a.activitytype <> 9 and d.orderdate between a.begintime and add_months(a.begintime, 3)) or");
        jpql.append("               (a.activitytype = 9 and d.orderdate between a.begintime and a.endtime))").append(subJpql.toString());
        jpql.append("         group by a.activityno,                                             ");
        jpql.append("                  a.activityid,                                             ");
        jpql.append("                  a.begintime,                                              ");
        jpql.append("                  a.endtime,                                                ");
        jpql.append("                  a.activitytype,                                           ");
        jpql.append("                  a.activitytheme,                                          ");
        jpql.append("                  a.activityform,                                           ");
        jpql.append("                  a.auditcost,                                              ");
        jpql.append("                  a.orgid) h                                                ");

        return jpql.toString();
    }

}