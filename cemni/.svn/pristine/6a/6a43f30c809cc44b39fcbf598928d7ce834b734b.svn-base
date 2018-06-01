package com.huiju.report.salestarget.eao;

import java.math.BigDecimal;
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
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "SalesTargetEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SalesTargetEaoBean extends GenericEaoImpl<Sql, Long> implements SalesTargetEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public Map report(Map searchParam, Integer ifPage) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        StringBuffer subJpql = new StringBuffer();

        Object EQ_bigAreaId = searchParam.get("EQ_bigAreaId");
        Object EQ_areaId = searchParam.get("EQ_areaId");
        Object IN_storeNo = searchParam.get("IN_storeNo");
        if (IN_storeNo != null) {
            String storeNoStr = IN_storeNo.toString();
            if (StringUtils.isNotBlank(storeNoStr)) {
                subJpql.append(" and x.storeno in('" + storeNoStr.replaceAll(",", "','") + "')");
            }
        } else if (EQ_areaId != null) {
            subJpql.append(" and x.areaid = ?1");
            valueMap.put(1, EQ_areaId.toString());
        } else if (EQ_bigAreaId != null) {
            subJpql.append(" and x.bigareaid = ?2");
            valueMap.put(2, EQ_bigAreaId.toString());
        }
        Object EQ_attr = searchParam.get("EQ_attr");
        if (EQ_attr != null) {
            subJpql.append(" and x.attr = ?3");
            valueMap.put(3, EQ_attr.toString());
        }

        String EQ_date = searchParam.get("EQ_date").toString();
        String[] ds = EQ_date.split("-");
        valueMap.put(4, ds[0]);
        valueMap.put(5, ds[1]);
        valueMap.put(6, EQ_date);

        StringBuffer jpql = new StringBuffer();
        jpql.append("select f.bigareaname,                                                                               ");
        jpql.append("       f.areaname,                                                                                  ");
        jpql.append("       f.storeno,                                                                                   ");
        jpql.append("       f.name,                                                                                      ");
        jpql.append("       (select n.name from d_data_dict n where n.dictcode = 8400 and n.value = f.attr),             ");
        jpql.append("       f.cur_xszj,                                                                                  ");
        jpql.append("       f.cur_pos,                                                                                   ");
        jpql.append("       to_char(decode(f.cur_xszj, 0, 0, round(f.cur_pos / f.cur_xszj, 4) * 100), '9999990.99') || '%',");
        jpql.append("       f.last_xszj,                                                                                 ");
        jpql.append("       f.last_pos,                                                                                  ");
        jpql.append("       to_char(decode(f.last_xszj, 0, 0, round(f.last_pos / f.last_xszj, 4) * 100), '9999990.99') || '%'");
        jpql.append("  from (select x.bigareaname,                                                                       ");
        jpql.append("               x.areaname,                                                                          ");
        jpql.append("               x.storeno,                                                                           ");
        jpql.append("               x.name,                                                                              ");
        jpql.append("               x.attr,                                                                              ");
        jpql.append("                                                                                                    ");
        jpql.append("               nvl(cur_xszj, 0) as cur_xszj,                                                        ");
        jpql.append("               nvl((select sum(nvl(m.jeweldiscountamount, 0))                                       ");
        jpql.append("                  from d_pos_order m                                                                ");
        jpql.append("                 where m.storeno = x.storeno                                                        ");
        jpql.append("                   and m.posbilldate between trunc(to_date(?6, 'yyyy-mm-dd'), 'mm') and to_date(?6, 'yyyy-mm-dd')), 0) as cur_pos,");
        jpql.append("                                                                                                    ");
        jpql.append("               nvl(last_xszj, 0) as last_xszj,                                                      ");
        jpql.append("               nvl((select sum(nvl(m.jeweldiscountamount, 0))                                       ");
        jpql.append("                  from d_pos_order m                                                                ");
        jpql.append("                 where m.storeno = x.storeno                                                        ");
        jpql.append("                   and m.posbilldate between trunc(add_months(to_date(?6, 'yyyy-mm-dd'), -12), 'mm') and add_months(to_date(?6, 'yyyy-mm-dd'), -12)), 0) as last_pos");
        jpql.append("          from d_cn_store x,                                                                        ");
        jpql.append("               (select b.storeno,                                                                   ");
        jpql.append("                       decode(?5, '01', b.jan, '02', b.feb, '03', b.mar, '04', b.apr, '05', b.may, '06', b.jun, '07', b.jul, '08', b.aug, '09', b.sep, '10', b.oct, '11', b.nov, '12', b.dec) as cur_xszj");
        jpql.append("                  from d_sales_year a, d_sales_yedetail b                                           ");
        jpql.append("                 where a.yearid = b.yearid                                                          ");
        jpql.append("                   and a.partyear = ?4) y,                                                          ");
        jpql.append("               (select b.storeno,                                                                   ");
        jpql.append("                       decode(?5, '01', b.jan, '02', b.feb, '03', b.mar, '04', b.apr, '05', b.may, '06', b.jun, '07', b.jul, '08', b.aug, '09', b.sep, '10', b.oct, '11', b.nov, '12', b.dec) as last_xszj");
        jpql.append("                  from d_sales_year a, d_sales_yedetail b                                           ");
        jpql.append("                 where a.yearid = b.yearid                                                          ");
        jpql.append("                   and a.partyear = ?4 - 1) z                                                       ");
        jpql.append("         where x.storeno = y.storeno(+)                                                             ");
        jpql.append("           and x.storeno = z.storeno(+)                                                             ").append(subJpql.toString());
        jpql.append("           and x.isvalid = 1) f                                                                     ");
        jpql.append(" order by f.cur_pos desc                                                                            ");

        String sql = "SELECT k.*, ROWNUM rnum FROM (" + jpql.toString() + ") k ";
        if (ifPage == 1) {
            sql = "SELECT * FROM (" + sql + " WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START;
        }

        Query qry = em.createNativeQuery(sql);
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        if (ifPage == 1) {
            qry.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
            qry.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
        }
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("bigAreaName", rs[0]);
            map.put("areaName", rs[1]);
            map.put("storeNo", rs[2]);
            map.put("name", rs[3]);
            map.put("attrName", rs[4]);
            map.put("bzj", rs[5]);
            map.put("bje", rs[6]);
            map.put("bpc", rs[7]);
            map.put("czj", rs[8]);
            map.put("cje", rs[9]);
            map.put("cpc", rs[10]);

            double oldJw = ((BigDecimal) rs[9]).doubleValue();
            double jje = ((BigDecimal) rs[6]).doubleValue() - oldJw;
            map.put("jje", NeuUtils.formatMath(jje));
            map.put("jpc", (oldJw == 0 ? "0" : NeuUtils.formatMath(100 * jje / oldJw)) + "%");
            map.put("rank", rs[11]);
            rsList.add(map);
        }

        // 总数
        StringBuffer cntJpql = new StringBuffer("select count(1) from d_cn_store x where x.isvalid = 1");
        cntJpql.append(subJpql.toString());
        valueMap.remove(4);
        valueMap.remove(5);
        valueMap.remove(6);
        Query cntQry = em.createNativeQuery(cntJpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        BigDecimal cnt = (BigDecimal) cntQry.getSingleResult();

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("totalCount", cnt.longValue());
        retMap.put("records", rsList);
        return retMap;
    }
}