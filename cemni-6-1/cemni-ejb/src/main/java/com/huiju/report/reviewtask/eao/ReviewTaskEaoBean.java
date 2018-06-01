package com.huiju.report.reviewtask.eao;

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

@Stateless(mappedName = "ReviewTaskEaoBean")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReviewTaskEaoBean extends GenericEaoImpl<Sql, Long> implements ReviewTaskEaoLocal {
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
        jpql.append("SELECT * FROM (SELECT k.*, ROWNUM rnum FROM (").append(prefixJpql).append(suffixJpql).append(" order by telvisitno) k WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

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
            map.put("telvisitNo", rs[0]);
            map.put("storeName", rs[1]);
            map.put("backfsName", rs[2]);
            map.put("tasktypeName", rs[3]);
            map.put("visitSatus", rs[4]);
            map.put("unhasCnt", rs[5]);
            map.put("hasCnt", rs[6]);
            map.put("totalCnt", rs[7]);
            map.put("succPercent", rs[8]);
            map.put("cdate", rs[9]);
            rsList.add(map);
        }

        Query cntQry = em.createNativeQuery("select count(1) " + suffixJpql);
        for (Integer k : valueMap.keySet()) {
            cntQry.setParameter(k, valueMap.get(k));
        }
        //合计
        StringBuffer totalSql = new StringBuffer();
        totalSql.append(this.getPrefixTotal()).append(this.getSuffixJpql(searchParam, valueMap));
        Map totalMap = queryTotalMap(totalSql.toString(),valueMap);
        if(0 == (Integer)searchParam.get("start")){
        	rsList.add(0,totalMap);
        }
        
        
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("records", rsList);
        rsMap.put("totalCount", ((BigDecimal)cntQry.getSingleResult()).intValue()+1);
        return rsMap;
    }

    @Override
    public List qryForExcel(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        String jpql = prefixJpql + suffixJpql + " order by telvisitno desc";

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("telvisitNo", rs[0]);
            map.put("storeName", rs[1]);
            map.put("backfsName", rs[2]);
            map.put("tasktypeName", rs[3]);
            map.put("visitSatus", rs[4]);
            map.put("unhasCnt", rs[5]);
            map.put("hasCnt", rs[6]);
            map.put("totalCnt", rs[7]);
            map.put("succPercent", rs[8]);
            map.put("cdate", rs[9]);
            rsList.add(map);
        }
        //合计
        StringBuffer totalSql = new StringBuffer();
        totalSql.append(this.getPrefixTotal()).append(this.getSuffixJpql(searchParam, valueMap));
        Map totalMap = queryTotalMap(totalSql.toString(),valueMap);
        rsList.add(0,totalMap);
        
        return rsList;
    }

    private String getPrefixJpql(Map searchParam, Map valueMap) {
        StringBuffer prefix = new StringBuffer();
        prefix.append("select a.telvisitno,                                                                                                                    ");
        prefix.append("       c.storename,                                                                                                                     ");
        prefix.append("       (select f.name from d_data_dict f where f.dictcode = 2101 and f.value = a.backfs) as backfsname,                                 ");
        prefix.append("       (select f.name from d_data_dict f where f.dictcode = 2107 and f.value = a.tasktype) as tasktypename,                             ");
        prefix.append("       (case when a.startrq > sysdate then '未进行' when sysdate between a.startrq and a.endrq then '进行中' else '已完成' end) as visitsatus,");
        prefix.append("       c.unhascnt,                                                                                                                      ");
        prefix.append("       c.hascnt,                                                                                                                        ");
        prefix.append("       c.totalcnt,                                                                                                                      ");
        prefix.append("       (to_char(round(c.hascnt / c.totalcnt, 4) * 100, '990.99') || '%'),                                                               ");
        prefix.append("       to_char(a.cdate, 'yyyy-mm-dd')                                                                                                   ");
        return prefix.toString();
    }

    private String getSuffixJpql(Map searchParam, Map valueMap) {
        StringBuffer subJpql = new StringBuffer();
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("EQ_telvisit_telvisitNo")) {
                subJpql.append(" and a.telvisitno = ?1");
                valueMap.put(1, searchParam.get(key).toString());
            } else if (key.equals("EQ_storeNo")) {
                Object IN_storeNoObj = searchParam.get(key);
                if (IN_storeNoObj != null) {
                    String IN_storeNo = IN_storeNoObj.toString();
                    if (StringUtils.isNotBlank(IN_storeNo)) {
                        subJpql.append(" and c.storeno in ('" + IN_storeNo.replaceAll(",", "','") + "')");
                    }
                }
            } else if (key.equals("EQ_telvisit_backfs")) {
                subJpql.append(" and a.backfs = ?3");
                valueMap.put(3, searchParam.get(key).toString());
            } else if (key.equals("EQ_taskType")) {
                subJpql.append(" and a.taskType = ?4");
                valueMap.put(4, searchParam.get(key).toString());
            } else if (key.equals("EQ_sdate")) {
                subJpql.append(" and a.cdate >=  to_date(?5, 'yyyy-mm-dd')");
                valueMap.put(5, searchParam.get(key).toString());
            } else if (key.equals("EQ_edate")) {
                subJpql.append(" and a.cdate <= to_date(?6, 'yyyy-mm-dd') + 1");
                valueMap.put(6, searchParam.get(key).toString());
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("  from d_afterservice_telvisit a,                         ");
        jpql.append("       (select b.telvisitid,                              ");
        jpql.append("               b.storeno,                                 ");
        jpql.append("               b.storename,                               ");
        jpql.append("               sum(decode(b.backzt, 1, 0, 1)) as unhascnt,");
        jpql.append("               sum(decode(b.backzt, 1, 1, 0)) as hascnt,  ");
        jpql.append("               count(1) as totalcnt                       ");
        jpql.append("          from d_afterservice_telvisitrecord b            ");
        jpql.append("         group by b.telvisitid, b.storeno, b.storename) c ");
        jpql.append(" where a.telvisitid = c.telvisitid                        ").append(subJpql.toString());

        return jpql.toString();
    }
    
    private String getPrefixTotal() {
        StringBuffer prefix = new StringBuffer();
        prefix.append("select nvl(sum(c.unhascnt), 0),                             						 ");
        prefix.append("       nvl(sum(c.hascnt), 0),                            						 ");
        prefix.append("       nvl(sum(c.totalcnt), 0),                          						 ");
        prefix.append("       (to_char(nvl(round(sum(c.hascnt) / sum(c.totalcnt), 4),0) * 100, '990.99') || '%') ");
        return prefix.toString();
    }
    
    private Map<String,Object> queryTotalMap(String sql,Map<Integer, String> paramsMap){
    	Query query = em.createNativeQuery(sql);
        for (Integer k : paramsMap.keySet()) {
        	query.setParameter(k, paramsMap.get(k));
        }
    	Map<String,Object> map = new HashMap<String,Object>();
    	Object[] obj = (Object[]) query.getSingleResult();
    	map.put("telvisitNo", "");
        map.put("storeName", "");
        map.put("backfsName", "合计");
        map.put("tasktypeName", "");
        map.put("visitSatus", "");
        map.put("unhasCnt", obj[0]);
        map.put("hasCnt", obj[1]);
        map.put("totalCnt", obj[2]);
        map.put("succPercent", obj[3]);
        map.put("cdate", "");
    	return map;
    	
    }

}