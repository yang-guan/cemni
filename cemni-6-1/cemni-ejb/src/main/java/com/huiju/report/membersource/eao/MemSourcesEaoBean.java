package com.huiju.report.membersource.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.common.GlobalConst;
import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "MemSourcesEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MemSourcesEaoBean extends GenericEaoImpl<Sql, Long> implements MemSourcesEaoLocal {
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
            map.put("ConsumerCnt", rs[3]);
            map.put("NC", rs[4]);
            map.put("Ratio1", rs[5]);
            map.put("CRM", rs[6]);
            map.put("Ratio2", rs[7]);
            map.put("webchat", rs[8]);
            map.put("Ratio3", rs[9]);
            map.put("YAOWO", rs[10]);
            map.put("Ratio4", rs[11]);
            map.put("MarketActivity", rs[12]);
            map.put("Ratio5", rs[13]);
            map.put("alliance", rs[14]);
            map.put("Ratio6", rs[15]);
            map.put("CustRecommend", rs[16]);
            map.put("Ratio7", rs[17]);
            map.put("Other", rs[18]);
            map.put("Ratio8", rs[19]);
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
    public List qryMemSour(Map searchParam) {
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
            map.put("ConsumerCnt", rs[3]);
            map.put("NC", rs[4]);
            map.put("Ratio1", rs[5]);
            map.put("CRM", rs[6]);
            map.put("Ratio2", rs[7]);
            map.put("webchat", rs[8]);
            map.put("Ratio3", rs[9]);
            map.put("YAOWO", rs[10]);
            map.put("Ratio4", rs[11]);
            map.put("MarketActivity", rs[12]);
            map.put("Ratio5", rs[13]);
            map.put("alliance", rs[14]);
            map.put("Ratio6", rs[15]);
            map.put("CustRecommend", rs[16]);
            map.put("Ratio7", rs[17]);
            map.put("Other", rs[18]);
            map.put("Ratio8", rs[19]);
            rsList.add(map);
        }
        return rsList;
    }

    private String getPrefixJpql(Map searchParam, Map valueMap) {
        
        StringBuffer jpql = new StringBuffer();                                        
        jpql.append("   select distinct belongstorename,                                ");
        jpql.append("          decode(c.attr,1,'加盟店',2,'直营店') attr,                   ");
        jpql.append("          totalcnt,                                                ");
        jpql.append("          ConsumerCnt,                                             ");
        jpql.append("          NC,                                                      ");
        jpql.append("          round(NC / totalcnt, 4) * 100 || '%' Ratio1,             ");
        jpql.append("          CRM,                                                     ");
        jpql.append("          round(CRM / totalcnt, 4) * 100 || '%' Ratio2,            ");
        jpql.append("          webchat,                                                 ");
        jpql.append("          round(webchat / totalcnt, 4) * 100 || '%' Ratio3,        ");
        jpql.append("          YAOWO,                                                   ");
        jpql.append("          round(YAOWO / totalcnt, 4) * 100 || '%' Ratio4,          ");
        jpql.append("          MarketActivity,                                          ");
        jpql.append("          round(MarketActivity / totalcnt, 4) * 100 || '%' Ratio5, ");
        jpql.append("          alliance,                                                ");
        jpql.append("          round(alliance / totalcnt, 4) * 100 || '%' Ratio6,       ");
        jpql.append("          CustRecommend,                                           ");
        jpql.append("          round(CustRecommend / totalcnt, 4) * 100 || '%' Ratio7,  ");
        jpql.append("          Other,                                                   ");
        jpql.append("          round(Other / totalcnt, 4) * 100 || '%' Ratio8           ");
        jpql.append("     from (                                                        ");
        return jpql.toString();
       
    }

    private String getSuffixJpql(Map searchParam, Map valueMap) {
        StringBuffer subJpql = new StringBuffer();
        StringBuffer subJpql1 = new StringBuffer();
        StringBuffer subJpql2 = new StringBuffer();
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("LIKE_belongStoreName")) {
                subJpql.append(" and belongstorename like ?3");
                valueMap.put(3, "%" + searchParam.get(key).toString() + "%");
            } else if(key.equals("s_Cdate")) {
                subJpql.append(" and t.cdate >= to_date(?4, 'yyyy-mm-dd')");
                valueMap.put(4, searchParam.get(key).toString());
            }else if (key.equals("e_Cdate")) {
                subJpql.append(" and t.cdate <= to_date(?5, 'yyyy-mm-dd') + 1");
                valueMap.put(5, searchParam.get(key).toString());
            }
        }
        if(searchParam.toString().charAt(1)=='e'){
          subJpql1.append("case when t.lastbuytime <='' ");
        }
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
            if (key.equals("s_Cdate")) {
                subJpql1.append("case when t.lastbuytime >= to_date(?1,'yyyy-mm-dd') ");
                valueMap.put(1, searchParam.get(key).toString());
            } else if (key.equals("e_Cdate")) {
                subJpql1.append(" and t.lastbuytime<= to_date(?2, 'yyyy-mm-dd') + 1");
                valueMap.put(2, searchParam.get(key).toString());
            }
        }
        //除每页[20,0]之外，至少有一个字段传输，时间选择长度固定：39，那么三个字段传输除了时间即为门店传输，字段长度>43
//        if(searchParam.size()==3&&searchParam.toString().length()>39){
        //传值只有门店名称
        if(searchParam.toString().charAt(1)=='L'||searchParam.toString().charAt(1)=='E'){
            subJpql1.append("'consumers'");                   //只输入门店名称
        }else{
            subJpql1.append(" then 'consumers' end");
        }
        
        for (Object keyObj : searchParam.keySet()) {
            String key = keyObj.toString();
        if (key.equals("EQ_attr")) {
            subJpql2.append(" and c.attr= ?6");
            valueMap.put(6, searchParam.get(key).toString());
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("       select t.belongstorename, t.belongstoreno,                    ");
        jpql.append("           count(                                                    ").append(subJpql1.toString());
        jpql.append("                  ) as ConsumerCnt,                                  ");
        jpql.append("                  sum(decode(t.sources, 1, 1, 0)) as NC,             ");
        jpql.append("                  sum(decode(t.sources, 2, 1, 0)) as CRM,            ");
        jpql.append("                  sum(decode(t.sources, 3, 1, 0)) as webchat,        ");
        jpql.append("                  sum(decode(t.sources, 4, 1, 0)) as YAOWO,          ");
        jpql.append("                  sum(decode(t.sources, 7, 1, 0)) as MarketActivity, ");
        jpql.append("                  sum(decode(t.sources, 8, 1, 0)) as alliance,       ");  
        jpql.append("                  sum(decode(t.sources, 9, 1, 0)) as CustRecommend,  ");  
        jpql.append("                  sum(decode(t.sources,10, 1, 0)) as Other,          ");  
        jpql.append("                  count(1) as totalcnt                               ");  
        jpql.append("             from d_archive_individcust t                            ");  
        jpql.append("            where t.belongstorename != '客服部'                        ").append(subJpql.toString());  
        jpql.append("            group by rollup(t.belongstorename,t.belongstoreno)       ");
        jpql.append("           )s ,d_cn_store c                                          ");
        jpql.append("           where (s.belongstorename=c.name or s.belongstoreno=c.storeno)");
        jpql.append("           and c.isvalid=1                                           ").append(subJpql2.toString());
        return jpql.toString();
    }
}