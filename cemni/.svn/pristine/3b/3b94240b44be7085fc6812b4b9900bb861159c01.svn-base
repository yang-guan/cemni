package com.huiju.report.issuetrack.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.common.GlobalConst;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.StringUtils;

@Stateless(mappedName = "IssueTrackEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IssueTrackEaoBean extends GenericEaoImpl<PosOrder, Long> implements IssueTrackEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public Map Query(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (").append(prefixJpql).append(suffixJpql).append(" order by p.cardno desc,p.flargess desc, p.posbilldate asc) a  WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

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
            map.put("actno", rs[0]);
            map.put("subject", rs[1]);
            map.put("posno", rs[2]);
            map.put("posbilldate", rs[3]);
            map.put("storename", rs[4]);
            map.put("cardname", rs[5]);
            map.put("mobile", rs[6]);
            map.put("cardno", rs[7]);
            map.put("goodsClassHighestNo", rs[8]);
            map.put("seriestypename", rs[9]);
            map.put("flargess", rs[10]);
            map.put("goodsno", rs[11]);
            map.put("goodsname", rs[12]);
            map.put("goodsbar", rs[13]);
            map.put("actualsaleamount", rs[14]);
            map.put("jeweldiscountamount", rs[15]);
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
    public List export(Map searchParam) {
        Map<Integer, String> valueMap = new HashMap<Integer, String>();
        String prefixJpql = this.getPrefixJpql(searchParam, valueMap);
        String suffixJpql = this.getSuffixJpql(searchParam, valueMap);

        String jpql = prefixJpql + suffixJpql + " order by p.cardno desc,p.flargess desc, p.posbilldate asc";

        Query qry = em.createNativeQuery(jpql.toString());
        for (Integer k : valueMap.keySet()) {
            qry.setParameter(k, valueMap.get(k));
        }
        List<Object[]> qryList = qry.getResultList();

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("actno", rs[0]);
            map.put("subject", rs[1]);
            map.put("posno", rs[2]);
            map.put("posbilldate", rs[3]);
            map.put("storename", rs[4]);
            map.put("cardname", rs[5]);
            map.put("mobile", rs[6]);
            map.put("cardno", rs[7]);
            map.put("goodsClassHighestNo", rs[8]);
            map.put("seriestypename", rs[9]);
            map.put("flargess", rs[10]);
            map.put("goodsno", rs[11]);
            map.put("goodsname", rs[12]);
            map.put("goodsbar", rs[13]);
            map.put("actualsaleamount", rs[14]);
            map.put("jeweldiscountamount", rs[15]);
            rsList.add(map);
        }
        return rsList;
    }
    
    private String getPrefixJpql(Map searchParam, Map valueMap) {
        StringBuffer prefix = new StringBuffer();
        prefix.append("                select p.actno,                                       ");
        prefix.append("                       p.subject,                                     ");
        prefix.append("                       p.posno,                                       ");
        prefix.append("                       to_char(p.posbilldate,'yyyy-mm-dd hh24:mi:ss'),");
        prefix.append("                       p.storename,                                   ");
        prefix.append("                       p.cardname,                                    ");
        prefix.append("                       p.mobile,                                      ");
        prefix.append("                       p.cardno,                                      ");
        prefix.append("                       p.goodsClassHighestNo,                         ");
        prefix.append("                       p.seriestypename,                              ");
        prefix.append("                       decode(p.flargess,1,'是','否'),                 ");
        prefix.append("                       p.goodsno,                                     ");
        prefix.append("                       p.goodsname,                                   ");
        prefix.append("                       p.goodsbar,                                    ");
        prefix.append("                       p.actualsaleamount,                            ");
        prefix.append("                       p.jeweldiscountamount                          ");
      
        return prefix.toString();
    }

    private String getSuffixJpql(Map searchParam, Map valueMap) {
    	 StringBuffer subJpql = new StringBuffer();
         for (Object keyObj : searchParam.keySet()) {
             String key = keyObj.toString();
              if (key.equals("LIKE_subject")) {
                  subJpql.append(" and p.subject like ?1");
                  valueMap.put(1, "%" + searchParam.get(key).toString() + "%");
              } else if (key.equals("LIKE_goodsname")) {
                  subJpql.append(" and p.goodsname like ?2");
                  valueMap.put(2, "%" + searchParam.get(key).toString() + "%");
              } else if (key.equals("GE_posbilldate")) {
                  subJpql.append(" and p.posbilldate >=  to_date(?3,'yyyy-MM-dd')");
                  valueMap.put(3, searchParam.get(key).toString());
              } else if (key.equals("LE_posbilldate")) {
                  subJpql.append(" and p.posbilldate <=  to_date(?4,'yyyy-MM-dd') + 1");
                  valueMap.put(4, searchParam.get(key).toString());
              }
          }

          Object EQ_storeNameObj = searchParam.get("EQ_storeNo");
          Object EQ_areaNameObj = searchParam.get("EQ_areaName");
          Object EQ_bigAreaNameObj = searchParam.get("EQ_bigAreaName");

          if (EQ_storeNameObj != null) {
              String EQ_storeName = EQ_storeNameObj.toString();
              if (StringUtils.isNotBlank(EQ_storeName)) {
                  subJpql.append(" and p.storeNo in ('" + EQ_storeName.replaceAll(",", "','") + "')");
              }
          } else if (EQ_areaNameObj != null) {
              String EQ_areaName = EQ_areaNameObj.toString();// 区域ID
              if (StringUtils.isNotBlank(EQ_areaName)) {
                  subJpql.append(" and p.storeNo in                        ");
                  subJpql.append("    (select c.orgcode                    ");
                  subJpql.append("       from d_cn_org c                   ");
                  subJpql.append("      where c.isvalid = 1                ");
                  subJpql.append("        and c.type = 5                   ");
                  subJpql.append("      start with c.orgId = " + EQ_areaName);
                  subJpql.append("     connect by c.parentid = prior c.orgid)");
              }
          } else if (EQ_bigAreaNameObj != null) {
              String EQ_bigAreaName = EQ_bigAreaNameObj.toString();// 大区的ID
              if (StringUtils.isNotBlank(EQ_bigAreaName)) {
                  subJpql.append(" and p.storeNo in                           ");
                  subJpql.append("    (select c.orgcode                       ");
                  subJpql.append("       from d_cn_org c                      ");
                  subJpql.append("      where c.isvalid = 1                   ");
                  subJpql.append("        and c.type = 5                      ");
                  subJpql.append("      start with c.orgId = " + EQ_bigAreaName);
                  subJpql.append("     connect by c.parentid = prior c.orgid)");
              }
          }


        StringBuffer jpql = new StringBuffer();
        jpql.append("                  from d_pos_order p                                 ");
        jpql.append(" where exists (select 1                                              "); 
        jpql.append("           from (select d.*                                          "); 
        jpql.append("                   from d_pos_order d                                "); 
        jpql.append("                  where d.flargess = 1                               "); 
        jpql.append("                    and not exists (select 1                         "); 
        jpql.append("                           from d_pos_order d2                       "); 
        jpql.append("                          where d2.posid <> d.posid                  "); 
        jpql.append("                            and d2.posno = d.posno                   "); 
        jpql.append("                            and d2.flargess = 0)) r                  "); 
		jpql.append("          where p.individcustid = r.individcustid                    ");
		jpql.append("            and p.posbilldate >= r.posbilldate)                      ").append(subJpql.toString());
		return jpql.toString();
	}
}