package com.huiju.afterservice.busiregist.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "BusiRegistEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BusiRegistEaoBean extends GenericEaoImpl<BusiRegist, Long> implements BusiRegistEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    public Map queryPageList(Map searchParam) {
        StringBuffer subJpql = new StringBuffer();
        // 拼接查询条件
        String key;
        Map<Integer, String> paramValueMap = new HashMap<Integer, String>();
        for (Object keyObj : searchParam.keySet()) {
            key = keyObj.toString();
            if (key.equals("LIKE_registerNo")) {
                subJpql.append(" and t.registerno like ?1");
                paramValueMap.put(1, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("LIKE_individCust_cardNo")) {
                subJpql.append(" and t.cardno like ?2");
                paramValueMap.put(2, "%" + searchParam.get(key).toString());
            } else if (key.equals("LIKE_individCust_name_OR_LIKE_custname")) {
                subJpql.append(" and t.custname like ?3 ");
                paramValueMap.put(3, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("LIKE_individCust_mobile_OR_LIKE_custMobile")) {
                subJpql.append(" and t.custMobile like ?4 ");
                paramValueMap.put(4, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("LIKE_store_name_OR_LIKE_org_name")) {
                subJpql.append(" and t.handleStoreorgname like ?5 ");
                paramValueMap.put(5, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("GE_registerDate")) {
                subJpql.append(" and t.registerDate >= to_date(?6,'yyyy-mm-dd') ");
                paramValueMap.put(6, searchParam.get(key).toString());
            } else if (key.equals("LE_registerDate")) {
                subJpql.append(" and t.registerDate <= to_date(?7,'yyyy-mm-dd') ");
                paramValueMap.put(7, searchParam.get(key).toString());
            } else if (key.equals("EQ_acceptState")) {
                subJpql.append(" and t.acceptstate = ?8 ");
                paramValueMap.put(8, searchParam.get(key).toString());
            } else if (key.equals("EQ_businessType")) {
                subJpql.append(" and t.businessType = ?8 ");
                paramValueMap.put(8, searchParam.get(key).toString());
            } else if (key.equals("LIKE_acceptStore_name_OR_LIKE_acceptOrg_name")) {
                subJpql.append(" and t.acceptStoreorgname like ?9 ");
                paramValueMap.put(9, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("IN_storeNo_OR_IN_orgCode")) {
                String[] arrStoreOrgParam = (String[]) searchParam.get(key);
                subJpql.append(" and ((t.storeno in ('" + arrStoreOrgParam[0].replaceAll(",", "','") + "')) or (t.orgcode in ('" + arrStoreOrgParam[1].replaceAll(",", "','") + "')) or (t.acceptstoreno in ('" + arrStoreOrgParam[0].replaceAll(",", "','") + "')) or (t.acceptorgcode in ('" + arrStoreOrgParam[1].replaceAll(",", "','") + "')) )");
            }
        }

        // 不分页的sql
        StringBuffer jpqlCommon = new StringBuffer();
        jpqlCommon.append(" select registerno,                                                               ");
        jpqlCommon.append("        registerDate,                                                             ");
        jpqlCommon.append("        cardno,                                                                   ");
        jpqlCommon.append("        custname,                                                                 ");
        jpqlCommon.append("        custMobile,                                                               ");
        jpqlCommon.append("        handleStoreorgname,                                                       ");
        jpqlCommon.append("        acceptStoreorgname,                                                       ");
        jpqlCommon.append("        acceptperson,                                                             ");
        jpqlCommon.append("        (select a.name                                                            ");
        jpqlCommon.append("           from d_data_dict a                                                     ");
        jpqlCommon.append("          where a.dictcode = " + DataDict.BUSIREGIST_ACCEPTSTATE + "              ");
        jpqlCommon.append("            and a.value = t.acceptstate) as acceptStateName,                      ");
        jpqlCommon.append("        (select a.name                                                            ");
        jpqlCommon.append("           from d_data_dict a                                                     ");
        jpqlCommon.append("          where a.dictcode = " + DataDict.BUSIREGIST_BUSINESSTYPE + "             ");
        jpqlCommon.append("            and a.value = t.businessType) as businessTypeName,                    ");
        jpqlCommon.append("        busiRegistId,                                                             ");
        jpqlCommon.append("        storeno,                                                                  ");
        jpqlCommon.append("        orgcode,                                                                  ");
        jpqlCommon.append("        acceptstoreno,                                                            ");
        jpqlCommon.append("        acceptorgcode                                                             ");
        jpqlCommon.append("   from (select t1.registerno,                                                    ");
        jpqlCommon.append("                t1.busiRegistId,                                                  ");
        jpqlCommon.append("                t1.registerDate,                                                  ");
        jpqlCommon.append("                t2.cardno,                                                        ");
        jpqlCommon.append("                t2.name as custname,                                              ");
        jpqlCommon.append("                t2.mobile as custMobile,                                          ");
        jpqlCommon.append("                case                                                              ");
        jpqlCommon.append("                  when t1.orgid is null then                                      ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_store t3                                          ");
        jpqlCommon.append("                     where t3.storeid = t1.storeid)                               ");
        jpqlCommon.append("                  else                                                            ");
        jpqlCommon.append("                   (select t3.name from d_cn_org t3 where t3.orgid = t1.orgid)    ");
        jpqlCommon.append("                end handleStoreorgname,                                           ");
        jpqlCommon.append("                case                                                              ");
        jpqlCommon.append("                  when t1.acceptorgid is null then                                ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_store t3                                          ");
        jpqlCommon.append("                     where t3.storeid = t1.acceptstoreid)                         ");
        jpqlCommon.append("                  else                                                            ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_org t3                                            ");
        jpqlCommon.append("                     where t3.orgid = t1.acceptorgid)                             ");
        jpqlCommon.append("                end acceptStoreorgname,                                           ");
        jpqlCommon.append("                t1.acceptperson,                                                  ");
        jpqlCommon.append("                t1.acceptState,                                                   ");
        jpqlCommon.append("                t1.businessType,                                                  ");
        jpqlCommon.append("                t1.storeno,                                                       ");
        jpqlCommon.append("                t1.orgcode,                                                       ");
        jpqlCommon.append("                t1.acceptstoreno,                                                 ");
        jpqlCommon.append("                t1.acceptorgcode                                                  ");
        jpqlCommon.append("           from d_afterservice_busiregist t1, d_archive_individcust t2            ");
        jpqlCommon.append("          where t1.individcustid = t2.individcustid                               ");
        jpqlCommon.append("            and t1.individcustid is not null                                      ");
        jpqlCommon.append("         union                                                                    ");
        jpqlCommon.append("         select t1.registerno,                                                    ");
        jpqlCommon.append("                t1.busiRegistId,                                                  ");
        jpqlCommon.append("                t1.registerDate,                                                  ");
        jpqlCommon.append("                null as cardno,                                                   ");
        jpqlCommon.append("                t1.custname as custname,                                          ");
        jpqlCommon.append("                t1.custMobile as custMobile,                                      ");
        jpqlCommon.append("                case                                                              ");
        jpqlCommon.append("                  when t1.orgid is null then                                      ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_store t3                                          ");
        jpqlCommon.append("                     where t3.storeid = t1.storeid)                               ");
        jpqlCommon.append("                  else                                                            ");
        jpqlCommon.append("                   (select t3.name from d_cn_org t3 where t3.orgid = t1.orgid)    ");
        jpqlCommon.append("                end handleStoreorgname,                                           ");
        jpqlCommon.append("                case                                                              ");
        jpqlCommon.append("                  when t1.acceptorgid is null then                                ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_store t3                                          ");
        jpqlCommon.append("                     where t3.storeid = t1.acceptstoreid)                         ");
        jpqlCommon.append("                  else                                                            ");
        jpqlCommon.append("                   (select t3.name                                                ");
        jpqlCommon.append("                      from d_cn_org t3                                            ");
        jpqlCommon.append("                     where t3.orgid = t1.acceptorgid)                             ");
        jpqlCommon.append("                end acceptStoreorgname,                                           ");
        jpqlCommon.append("                t1.acceptperson,                                                  ");
        jpqlCommon.append("                t1.acceptState,                                                   ");
        jpqlCommon.append("                t1.businessType,                                                  ");
        jpqlCommon.append("                t1.storeno,                                                       ");
        jpqlCommon.append("                t1.orgcode,                                                       ");
        jpqlCommon.append("                t1.acceptstoreno,                                                 ");
        jpqlCommon.append("                t1.acceptorgcode                                                  ");
        jpqlCommon.append("           from d_afterservice_busiregist t1                                      ");
        jpqlCommon.append("          where t1.individcustid is null) t                                       ");
        jpqlCommon.append("  where 1 = 1                                                                     ").append(subJpql.toString());
        jpqlCommon.append("  order by t.busiRegistId desc                                                    ");

        //计算总条数
        StringBuffer cntJpql = new StringBuffer();
        cntJpql.append(" select count(1) from ( ").append(jpqlCommon.toString()).append(" )");
        Query cntQry = em.createNativeQuery(cntJpql.toString());
        for (Integer k : paramValueMap.keySet()) {
            cntQry.setParameter(k, paramValueMap.get(k));
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT *                        ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum ");
        jpql.append("          FROM (                ").append(jpqlCommon.toString());
        jpql.append(" ) a ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        for (Integer k : paramValueMap.keySet()) {
            query.setParameter(k, paramValueMap.get(k));
        }
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
        List<Object[]> qryList = query.getResultList();
        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("registerNo", rs[0]);
            map.put("registerDate", rs[1]);
            map.put("cardNo", rs[2]);
            map.put("custName", rs[3]);
            map.put("custMobile", rs[4]);
            map.put("handleStoreOrgName", rs[5]);
            map.put("acceptStoreorgName", rs[6]);
            map.put("acceptPerson", rs[7]);
            map.put("acceptStateName", rs[8]);
            map.put("businessTypeName", rs[9]);
            map.put("busiRegistId", rs[10]);
            rsList.add(map);
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("totalCount", cntQry.getSingleResult());
        retMap.put("records", rsList);
        em.clear();
        return retMap;

    }

    @Override
    public Map queryOrgStoreList(Map searchParam) {
        StringBuffer subJpql = new StringBuffer();
        // 拼接查询条件
        String key;
        Map<Integer, String> paramValueMap = new HashMap<Integer, String>();
        for (Object keyObj : searchParam.keySet()) {
            key = keyObj.toString();
            if (key.equals("LIKE_orgStoreNo_OR_LIKE_orgStoreName")) {
                subJpql.append(" and ( t.orgStoreCode like ?1 or t.orgStoreName like ?1) ");
                paramValueMap.put(1, "%" + searchParam.get(key).toString() + "%");
            } else if (key.equals("EQ_isValid")) {
                subJpql.append(" and t.isvalid = ?2 ");
                paramValueMap.put(2, searchParam.get(key).toString());
            } else if (key.equals("IN_orgStoreCode")) {
                String orgStoreCode = searchParam.get(key).toString();
                String[] arrOrgStoreCode = orgStoreCode.split(",");
                for (int i = 0; i < arrOrgStoreCode.length; i++) {
                    arrOrgStoreCode[i] = "\'" + arrOrgStoreCode[i] + "\'";
                }
                subJpql.append(" and t.orgStoreCode in (" + StringUtils.join(arrOrgStoreCode, ",") + ") ");
            }
        }

        StringBuffer jpqlCommon = new StringBuffer();
        jpqlCommon.append(" select *                                                      ");
        jpqlCommon.append("   from (select t.orgid as orgStoreId,                         ");
        jpqlCommon.append("                t.orgcode as orgStoreCode,                     ");
        jpqlCommon.append("                t.name as orgStoreName,                        ");
        jpqlCommon.append("                (select a.name                                 ");
        jpqlCommon.append("                   from d_data_dict a                          ");
        jpqlCommon.append("                  where a.dictcode = " + DataDict.ORG_TYPE + "     ");
        jpqlCommon.append("                    and a.value = t.type) as orgStoreTypeName, ");
        jpqlCommon.append("                t.isvalid                                      ");
        jpqlCommon.append("           from D_CN_ORG t                                     ");
        jpqlCommon.append("          where t.orgcode = '" + GlobalConst.DEP_ORGCODE_KF + "'   ");
        jpqlCommon.append("         union all                                             ");
        jpqlCommon.append("         select t.storeid as orgStoreId,                       ");
        jpqlCommon.append("                t.storeno as orgStoreCode,                     ");
        jpqlCommon.append("                t.name as orgStoreName,                        ");
        jpqlCommon.append("                '门店'   as orgStoreTypeName,                   ");
        jpqlCommon.append("                t.isvalid                                      ");
        jpqlCommon.append("           from d_cn_store t) t                                ");
        jpqlCommon.append("  where 1 = 1                                                  ").append(subJpql.toString());

        // 分页的sql
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT *                        ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum ");
        jpql.append("          FROM (                ").append(jpqlCommon.toString());
        jpql.append(" ) a ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        //总数
        StringBuffer cntJpql = new StringBuffer();
        cntJpql.append("select count(1) from ( ").append(jpqlCommon.toString()).append(" ) ");

        Query cntQry = em.createNativeQuery(cntJpql.toString());
        for (Integer k : paramValueMap.keySet()) {
            cntQry.setParameter(k, paramValueMap.get(k));
        }

        Query query = em.createNativeQuery(jpql.toString());
        for (Integer k : paramValueMap.keySet()) {
            query.setParameter(k, paramValueMap.get(k));
        }
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
        List<Object[]> qryList = query.getResultList();

        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("orgStoreId", rs[0]);
            map.put("orgStoreCode", rs[1]);
            map.put("orgStoreName", rs[2]);
            map.put("orgStoreTypeName", rs[3]);
            map.put("isValid", rs[4]);
            rsList.add(map);
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("totalCount", cntQry.getSingleResult());
        retMap.put("records", rsList);
        em.clear();
        return retMap;
    }

}