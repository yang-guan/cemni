package com.huiju.report.activityvalue.eao;

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

@Stateless(mappedName = "ActivityValueEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ActivityValueEaoBean extends GenericEaoImpl<Sql, Long> implements ActivityValueEaoLocal {
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
        jpql.append("SELECT * FROM (SELECT k.*, ROWNUM rnum FROM (").append(prefixJpql).append(suffixJpql).append(" order by x.storename) k WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

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
            map.put("storeName", rs[0]);
            map.put("period", rs[1]);
            map.put("goodsCnt", rs[2]);
            map.put("actuaSaleAmount", rs[3]);
            map.put("newMemberNum", rs[4]);
            map.put("oldMemberNum", rs[5]);
            map.put("fansMemberNum", rs[6]);
            map.put("totalNum", rs[7]);
            map.put("actualCost", rs[8]);
            map.put("ratio", rs[9]);
            map.put("jewelDiscountAmount", rs[10]);
            map.put("costTotalNum", rs[11]);
            map.put("fansTotalNum", rs[12]);
            rsList.add(map);
        }

        Query cntQry = em.createNativeQuery("select count(1) from(" + prefixJpql + suffixJpql + ")");
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
    public List qryActVal(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        String jpql = prefixJpql + suffixJpql;

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("storeName", rs[0]);
            map.put("period", rs[1]);
            map.put("goodsCnt", rs[2]);
            map.put("actuaSaleAmount", rs[3]);
            map.put("newMemberNum", rs[4]);
            map.put("oldMemberNum", rs[5]);
            map.put("fansMemberNum", rs[6]);
            map.put("totalNum", rs[7]);
            map.put("actualCost", rs[8]);
            map.put("ratio", rs[9]);
            map.put("jewelDiscountAmount", rs[10]);
            map.put("costTotalNum", rs[11]);
            map.put("fansTotalNum", rs[12]);
            rsList.add(map);
        }
        return rsList;
    }

    private String getPrefixJpql(Map searchParam, Map valueMap) {
        StringBuffer subJpql = new StringBuffer();
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("LIKE_goodsCasshighestNo")) {
                subJpql.append(" and ( d.goodsclasshighestno like ?1");
                subJpql.append(" or o.designerstylename =(");
                subJpql.append(" select to_char(d.orderno) from d_data_dict d");
                subJpql.append(" where d.dictcode=2200 ");
                subJpql.append(" and d.name like?1  ) ");
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
                subJpql.append(" and c.orgcode in ('" + IN_orgCode.replaceAll(",", "','") + "')");
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("  with x as                                                                    ");
        jpql.append("  (select storename,                                                           ");
        jpql.append("          begintime,                                                           ");
        jpql.append("          endtime,                                                             ");
        jpql.append("          period,                                                              ");
        jpql.append("          sum(goodscnt) goodscnt,                                              ");
        jpql.append("          sum(actualsaleamount) actualsaleamount,                              ");
        jpql.append("          sum(jeweldiscountamount) jeweldiscountamount,                        ");
        jpql.append("          actualcost                                                           ");
        jpql.append("     from (select distinct d.storename,                                        ");
        jpql.append("                           a.activityid,                                       ");
        jpql.append("                           a.begintime,                                        ");
        jpql.append("                           a.endtime,                                          ");
        jpql.append("                           to_char(a.begintime, 'YYYY-MM-DD') || '~' ||        ");
        jpql.append("                           to_char(a.endtime, 'YYYY-MM-DD') period,            ");
        jpql.append("                           b.individcustid,                                    ");
        jpql.append("                           d.goodsname,                                        ");
        jpql.append("                           d.posbilldate,                                      ");
        jpql.append("                           sum(nvl(actualcost, 0)) actualcost,                 ");
        jpql.append("                           nvl(goodscnt, 0) goodscnt,                          ");
        jpql.append("                           nvl(actualsaleamount, 0) actualsaleamount,          ");
        jpql.append("                           nvl(jeweldiscountamount, 0) jeweldiscountamount     ");
        jpql.append("             from d_activity            a,                                     ");
        jpql.append("                  d_activity_indipartin b,                                     ");
        jpql.append("                  d_activity_scope      c,                                     ");
        jpql.append("                  d_activity_expectcost e,                                     ");
        jpql.append("                  d_pos_order           d                                      ");
        jpql.append("            where a.activityid = b.activityid                                  ");
        jpql.append("              and a.activityid = c.activityid                                  ");
        jpql.append("              and a.activityid = e.activityid                                  ");
        jpql.append("              and b.individcustid = d.individcustid                            ");
        jpql.append("              and c.orgcode = d.storeno                                        ");
        jpql.append("              and d.posbilldate between a.begintime and a.begintime + 90       ").append(subJpql.toString());
        jpql.append("            group by d.storename,                                              ");
        jpql.append("                     a.activityid,                                             ");
        jpql.append("                     a.begintime,                                              ");
        jpql.append("                     a.endtime,                                                ");
        jpql.append("                     b.individcustid,                                          ");
        jpql.append("                     d.goodsname,                                              ");
        jpql.append("                     d.posbilldate,                                            ");
        jpql.append("                     goodscnt,                                                 ");
        jpql.append("                     actualsaleamount,                                         ");
        jpql.append("                     jeweldiscountamount) s                                    ");
        jpql.append("    group by storename, begintime, endtime, period, actualcost)                ");
        return jpql.toString();
    } 
    
    private String getSuffixJpql(Map searchParam, Map valueMap) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("       select x.storename,                                                                  ");
        jpql.append("       x.period,                                                                            ");
        jpql.append("       x.goodscnt,                                                                          ");
        jpql.append("       x.actualsaleamount,                                                                  ");
        jpql.append("       sum(decode(y.fresh, 1, 1, 0)) newmembernum,                                          ");
        jpql.append("       sum(decode(y.fresh, 2, 1, 0)) oldmembernum,                                          ");
        jpql.append("       sum(decode(y.lv, 7, 1, 0)) fansmembernum,                                            ");
        jpql.append("       sum(decode(y.fresh, 1, 1, 0)) + sum(decode(y.fresh, 2, 1, 0)) totalnum,              ");
        jpql.append("       x.actualcost,                                                                        ");
        jpql.append("       (case                                                                                ");
        jpql.append("         when x.actualsaleamount = 0 then                                                   ");
        jpql.append("          0                                                                                 ");
        jpql.append("         when x.actualcost = 0 then                                                         ");
        jpql.append("          0                                                                                 ");
        jpql.append("         else                                                                               ");
        jpql.append("          round(x.actualcost / x.actualsaleamount * 100, 2)                                 ");
        jpql.append("       end) || '%' ratio,                                                                   ");
        jpql.append("       x.jeweldiscountamount,                                                               ");
        jpql.append("       (case                                                                                ");
        jpql.append("         when x.actualcost = 0 then                                                         ");
        jpql.append("          0                                                                                 ");
        jpql.append("         when (sum(decode(y.fresh, 1, 1, 0)) + sum(decode(y.fresh, 2, 1, 0))) = 0 then      ");
        jpql.append("          0                                                                                 ");
        jpql.append("         else                                                                               ");
        jpql.append("          round(x.actualcost / (sum(decode(y.fresh, 1, 1, 0)) +                             ");
        jpql.append("                sum(decode(y.fresh, 2, 1, 0))),                                             ");
        jpql.append("                2)                                                                          ");
        jpql.append("       end) costtotalnum,                                                                   ");
        jpql.append("       (case                                                                                ");
        jpql.append("         when x.actualcost = 0 then                                                         ");
        jpql.append("          0                                                                                 ");
        jpql.append("         when sum(decode(y.lv, 7, 1, 0)) = 0 then                                           ");
        jpql.append("          0                                                                                 ");
        jpql.append("         else                                                                               ");
        jpql.append("          round(x.actualcost / sum(decode(y.lv, 7, 1, 0)), 2)                               ");
        jpql.append("       end) fanstotalnum                                                                    ");
        jpql.append("   from x, d_archive_individcust y                                                          ");
        jpql.append("   where y.cdate between x.begintime and x.endtime + 1                                      ");
        jpql.append("   and y.srcstorename = x.storename                                                         ");
        jpql.append("   group by x.storename,                                                                    ");
        jpql.append("            x.period,                                                                       ");
        jpql.append("            x.goodscnt,                                                                     ");
        jpql.append("            x.actualsaleamount,                                                             ");
        jpql.append("            x.actualcost,                                                                   ");
        jpql.append("            x.jeweldiscountamount                                                           ");

        return jpql.toString();
    }
    
}