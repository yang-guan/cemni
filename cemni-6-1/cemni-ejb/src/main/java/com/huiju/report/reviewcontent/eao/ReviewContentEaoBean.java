package com.huiju.report.reviewcontent.eao;

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

@Stateless(mappedName = "ReviewContentEaoBean")
public class ReviewContentEaoBean extends GenericEaoImpl<Sql, Long> implements ReviewContentEaoLocal {
	EntityManager em;

	@Override
	@PersistenceContext(unitName = "showcase")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
		this.em = em;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map report(Map searchParam, Integer ifPage) {
		StringBuffer subJpql = new StringBuffer();
		StringBuffer cntJpql = new StringBuffer();
		Object EQ_storeNo = searchParam.get("EQ_storeNo");
		Object EQ_isPos = searchParam.get("EQ_isPos");
		Object EQ_sdate = searchParam.get("EQ_sdate");
		Object EQ_edate = searchParam.get("EQ_edate");
		Object EQ_bigAreaName = searchParam.get("EQ_bigAreaName");
		Object EQ_areaName = searchParam.get("EQ_areaName");
		Object EQ_fresh = searchParam.get("EQ_fresh");
		Object EQ_taskType = searchParam.get("EQ_taskType");
		if (null != EQ_storeNo)
			subJpql.append(" and storeNo in ('").append(EQ_storeNo.toString().replaceAll(",", "','")).append("')");
		if (null != searchParam.get("EQ_isPos")) {
			subJpql.append(" and ispos	=").append(EQ_isPos.toString());
		}
		if (null != searchParam.get("EQ_fresh")) {
			subJpql.append(" and fresh	=").append(EQ_fresh.toString());
		}
		if (null != searchParam.get("EQ_taskType")) {
			subJpql.append(" and taskType	=").append(EQ_taskType.toString());
		}
		if (null != searchParam.get("EQ_sdate")) {
			subJpql.append("      and backtime >= to_date('").append(EQ_sdate.toString()).append("', 'yyyy-mm-dd')  ");
		}
		if (null != searchParam.get("EQ_edate")) {
			subJpql.append("       and backtime <= to_date('").append(EQ_edate.toString()).append("', 'yyyy-mm-dd')  ");
		}
		if (null != EQ_bigAreaName) {
			subJpql.append(" and storeNo  in                         ");
			subJpql.append("    (select c.orgcode                    ");
			subJpql.append("       from d_cn_org c                   ");
			subJpql.append("      where c.isvalid = 1                ");
			subJpql.append("        and c.type = 5                   ");
			subJpql.append("      start with c.orgId = " + EQ_bigAreaName.toString());
			subJpql.append("     connect by c.parentid = prior c.orgid)");
		}
		if (null != EQ_areaName) {
			subJpql.append(" and storeNo  in                            ");
			subJpql.append("    (select c.orgcode                       ");
			subJpql.append("       from d_cn_org c                      ");
			subJpql.append("      where c.isvalid = 1                   ");
			subJpql.append("        and c.type = 5                      ");
			subJpql.append("      start with c.orgId = " + EQ_areaName.toString());
			subJpql.append("     connect by c.parentid = prior c.orgid)");
		}
		if (ifPage == 1) {
			cntJpql.append("select count(1) from (                                               ");
			cntJpql.append("select c.backtime, c.taskType,i.fresh  ,                             ");
			cntJpql.append("       decode((select count(1)                                       ");
			cntJpql.append("          from d_pos_order d                                         ");
			cntJpql.append("           where d.individcustid = c.individcustid),0,1,2) as ispos, ");
			cntJpql.append("           storeno                                                   ");
			cntJpql.append("  from D_AFTERSERVICE_TELVISITRECORD c                               ");
			cntJpql.append("  left join D_ARCHIVE_INDIVIDCUST i                                  ");
			cntJpql.append("  on c.individcustid = i.individcustid where c.backzt =1)            ");
			cntJpql.append(" where 1=1         ").append(subJpql);
		}
		StringBuffer jpql = new StringBuffer();
		if (ifPage == 1) {
			jpql.append("SELECT *                                                                                                                          ");
			jpql.append("  FROM (SELECT h.*, ROWNUM rnum  FROM (                                                                                           ");
		}
		jpql.append(" select *                                                                                                                             ");
		jpql.append("   from (select c.backtime,                                                                                                           ");
		jpql.append("                c.telvisitrecordno,                                                                                                   ");
		jpql.append("                (select f.name                                                                                                        ");
		jpql.append("                   from d_cn_org f                                                                                                    ");
		jpql.append("                  where f.type = 3                                                                                                    ");
		jpql.append("                    and f.Isvalid = 1                                                                                                 ");
		jpql.append("                  start with f.orgcode = c.storeno                                                                                    ");
		jpql.append("                 connect by f.orgid = prior f.parentid) as bigareaname,                                                               ");
		jpql.append("                storename,                                                                                                            ");
		jpql.append("                i.cardNo,                                                                                                             ");
		jpql.append("                i.name,                                                                                                               ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.SHOPSERVICE) as SHOPSERVICE,                ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.ORNAMENTWEAR) as ORNAMENTWEAR,              ");
		jpql.append("                c.KHADVICE,                                                                                                           ");
		jpql.append("                c.KHTALK,                                                                                                             ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.PROFESSORKNOW) as PROFESSORKNOW,            ");
		jpql.append("                c.INFOKNOWED,                                                                                                         ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.SHOPENVI) as SHOPENVI,                      ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.PARENTGANT) as PARENTGANT,                  ");
		jpql.append("                c.FEEDADVICE,                                                                                                         ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.WEARUPDATE) as WEARUPDATE,                  ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.PARENTMANYI) as PARENTMANYI,                ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.CAREUPDATE) as CAREUPDATE,                  ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.NOTSHOPSERVICE) as NOTSHOPSERVICE,          ");
		jpql.append("                c.NOTKHADVICE,                                                                                                        ");
		jpql.append("                c.NOTKHTALK,                                                                                                          ");
		jpql.append("                c.NOTINFOKNOWED,                                                                                                      ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.NOTPROFESSORKNOW) as NOTPROFESSORKNOW,      ");
		jpql.append("                c.NOTINTENTIONCP,                                                                                                     ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2104 and f.value = c.NOTSHOPENVI) as NOTSHOPENVI,                ");
		jpql.append("                c.NOTFEEDADVICE,                                                                                                      ");
		jpql.append("                c.NOTNEWRECOMENT,                                                                                                     ");
		jpql.append("                c.SALEREMARK,                                                                                                         ");
		jpql.append("                c.NOTSALEREMARK,                                                                                                      ");
		jpql.append("                decode((select count(1)                                                                                               ");
		jpql.append("                         from d_pos_order d                                                                                           ");
		jpql.append("                        where d.individcustid = c.individcustid),                                                                     ");
		jpql.append("                       0,                                                                                                             ");
		jpql.append("                       1,                                                                                                             ");
		jpql.append("                       2) as ispos,                                                                                                   ");
		jpql.append("                round((nvl(shopservice, 0) + nvl(ornamentwear, 0) +                                                                   ");
		jpql.append("                      nvl(professorknow, 0) + nvl(shopenvi, 0) +                                                                      ");
		jpql.append("                      nvl(parentgant, 0) + nvl(wearupdate, 0) +                                                                       ");
		jpql.append("                      nvl(parentmanyi, 0) + nvl(careupdate, 0)) / (6 * 8) * 100) || '%' as satisfaction,                              ");
		jpql.append("                round((nvl(notshopservice, 0) + nvl(notprofessorknow, 0) +                                                            ");
		jpql.append("                      nvl(notshopenvi, 0)) / (6 * 3) * 100) || '%' as notsatisfaction,                                                ");
		jpql.append("                c.STORENO,                                                                                                            ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 1114 and f.value = i.fresh) as fresh,                            ");
		jpql.append("                (select f.name from d_data_dict f where f.dictcode = 2107 and f.value = c.taskType) as taskType                       ");
		jpql.append("           from D_AFTERSERVICE_TELVISITRECORD c                                                                                       ");
		jpql.append("           left join D_ARCHIVE_INDIVIDCUST i                                                                                          ");
		jpql.append("             on c.individcustid = i.individcustid                                                                                     ");
		jpql.append("          where c.backzt = 1                                                                                                          ");
		jpql.append("          order by telvisitrecordno desc)                                                                                             ");
		jpql.append("         where 1 = 1    ").append(subJpql);
		if (ifPage == 1) {
			jpql.append(" ) h WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ")  WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);
		}
		Query query = em.createNativeQuery(jpql.toString());
		if (ifPage == 1) {
			query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
			query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
		}
		List<Object[]> qryList = query.getResultList();
		List rsList = new ArrayList();
		Map map;
		for (Object[] rs : qryList) {
			map = new HashMap();
			map.put("backtime", rs[0]);
			map.put("telvisitrecordno", rs[1]);
			map.put("bigAreaName", rs[2]);
			map.put("storeName", rs[3]);
			map.put("cardno", rs[4]);
			map.put("name", rs[5]);
			map.put("shopservice", rs[6]);
			map.put("ornamentwear", rs[7]);
			map.put("khadvice", rs[8]);
			map.put("khtalk", rs[9]);
			map.put("professorknow", rs[10]);
			map.put("infoknowed", rs[11]);
			map.put("shopenvi", rs[12]);
			map.put("parentgant", rs[13]);
			map.put("feedadvice", rs[14]);
			map.put("wearupdate", rs[15]);
			map.put("parentmanyi", rs[16]);
			map.put("careupdate", rs[17]);
			map.put("notshopservice", rs[18]);
			map.put("notkhadvice", rs[19]);
			map.put("notkhtalk", rs[20]);
			map.put("notinfoknowed", rs[21]);
			map.put("notprofessorknow", rs[22]);
			map.put("notintentioncp", rs[23]);
			map.put("notshopenvi", rs[24]);
			map.put("notfeedadvice", rs[25]);
			map.put("notnewrecoment", rs[26]);
			map.put("saleremark", rs[27]);
			map.put("notsaleremark", rs[28]);
			map.put("ispos", rs[29]);
			map.put("satisfaction", rs[30]);
			map.put("notsatisfaction", rs[31]);
			map.put("storeno", rs[32]);
			map.put("fresh", rs[33]);
			map.put("taskType", rs[34]);
			rsList.add(map);
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("totalCount", ifPage == 1 ? em.createNativeQuery(cntJpql.toString()).getSingleResult() : 0);
		retMap.put("records", rsList);
		em.clear();
		return retMap;
	}
}