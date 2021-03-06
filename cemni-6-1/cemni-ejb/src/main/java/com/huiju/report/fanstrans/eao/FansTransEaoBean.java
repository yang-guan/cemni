package com.huiju.report.fanstrans.eao;

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

@Stateless(mappedName = "FansTransEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FansTransEaoBean extends GenericEaoImpl<Sql, Long> implements FansTransEaoLocal {
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
        jpql.append("SELECT * FROM (SELECT k.*, ROWNUM rnum FROM (").append(prefixJpql).append(suffixJpql).append(") k WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

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
            map.put("belongstorename", rs[0]);
            map.put("attr", rs[1]);
            map.put("totalcnt", rs[2]);
            map.put("fans", rs[3]);
            map.put("trans", rs[4]);
            map.put("transRatio", rs[5]);
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
    public List qryFansTrans(Map searchParam) {
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
            map.put("belongstorename", rs[0]);
            map.put("attr", rs[1]);
            map.put("totalcnt", rs[2]);
            map.put("fans", rs[3]);
            map.put("trans", rs[4]);
            map.put("transRatio", rs[5]);
            rsList.add(map);
        }
        return rsList;
    }

    private String getPrefixJpql(Map searchParam, Map valueMap) {
        
        StringBuffer jpql = new StringBuffer();                                        
        jpql.append("   select belongstorename,                                      ");
        jpql.append("          attr,                                                 ");
        jpql.append("          totalcnt,                                             ");
        jpql.append("          fans,                                                 ");
        jpql.append("          trans,                                                ");
        jpql.append("          round(trans / totalcnt, 4) * 100 || '%' transRatio    ");
        jpql.append("   from (                                                       ");
        return jpql.toString();  
    }

    private String getSuffixJpql(Map searchParam, Map valueMap) {
        StringBuffer subJpql = new StringBuffer();
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if(key.equals("GE_beginTime")) {
                subJpql.append(" and t.cdate >= to_date(?1, 'yyyy-mm-dd')");
                valueMap.put(1, searchParam.get(key).toString());
            }else if (key.equals("LE_beginTime")) {
                subJpql.append(" and t.cdate <= to_date(?2, 'yyyy-mm-dd') + 1");
                valueMap.put(2, searchParam.get(key).toString());
            }else if (key.equals("EQ_attr")) {
                subJpql.append(" and s.attr= ?3");
                valueMap.put(3, searchParam.get(key).toString());
            }else if (key.equals("EQ_sources")) {
                subJpql.append(" and t.sources= ?4");
                valueMap.put(4, searchParam.get(key).toString());
            }else if (key.equals("EQ_storeNo")) {
                Object IN_storeNoObj = searchParam.get(key);
                if (IN_storeNoObj != null) {
                    String IN_storeNo = IN_storeNoObj.toString();
                    if (StringUtils.isNotBlank(IN_storeNo)) {
                        subJpql.append(" and t.belongstoreno in ('" + IN_storeNo.replaceAll(",", "','") + "')");
                    }
                }
            }
        }
//        Object IN_orgCodeObj = searchParam.get("IN_orgCode");
//        if (IN_orgCodeObj != null) {
//            String IN_orgCode = IN_orgCodeObj.toString();
//            if (StringUtils.isNotBlank(IN_orgCode)) {
//                subJpql.append(" and t.belongstoreno in ('" + IN_orgCode.replaceAll(",", "','") + "')");
//            }
//    }
        StringBuffer jpql = new StringBuffer();
        jpql.append("    select t.belongstorename,                         ");
        jpql.append("    count(1) as totalcnt,                             ");
        jpql.append("    sum(decode(t.lv,7,1,0)) as fans,                  ");
        jpql.append("    sum(decode(t.lv,7,0,1)) as trans,                 ");
        jpql.append("    max(decode(s.attr,1,'加盟店',2,'直营店')) as attr     ");
        jpql.append("    from d_archive_individcust t ,d_cn_store s        ");
        jpql.append("    where t.belongstoreno=s.storeno                   ").append(subJpql.toString());
        jpql.append("          and s.isvalid=1                             ");
        jpql.append("          group by rollup(t.belongstorename))         ");  
        return jpql.toString();
    }
}