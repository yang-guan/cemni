package com.huiju.common.sql.eao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "SqlEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SqlEaoBean extends GenericEaoImpl<Sql, Long> implements SqlEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    /**
     * 生成系统编号
     * 
     * @author：yuhb
     * @date：2016年12月17日 下午22:40:41
     */
    @Override
    public String getCnNum(int numCode) {
        if (numCode == GlobalConst.NUM_INDIVID) {
            return this.executeSQLQueryOne("select 'C' || seq_individcust_cardno.nextval from dual");
        } else {
            Query query = em.createNamedQuery("getCnNum").setParameter("v_in_numCode", numCode);
            Object[] obj = (Object[]) query.getSingleResult();
            em.clear();
            return obj[0].toString();
        }
    }

    /**
     * @author：zzy 站内信-客户权益单
     */
    @Override
    public Map queryRightMaintAudit(Map searchParam) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select *                                                 ");
        jpql.append("  from (select a.*, rownum rnum                          ");
        jpql.append("          from (select t.complaintno,                    ");
        jpql.append("                       t.reviewstate,                    ");
        jpql.append("                       c.checkusername,                  ");
        jpql.append("                       c.checkresult,                    ");
        jpql.append("                       r.status,                         ");
        jpql.append("                       to_char(r.audittime, 'yyyy-mm-dd')");
        jpql.append("                  from d_afterservice_rightmaint      t, ");
        jpql.append("                       d_afterservice_rightmaintaudit r, ");
        jpql.append("                       d_recordinfo                   c  ");
        jpql.append("                 where t.complaintno = r.complaintno     ");
        jpql.append("                   and r.complaintno = c.cardnumber      ");
        jpql.append("                   and t.reviewstate in (3, 4)           ");
        jpql.append("                   and c.checkresult = 3                 ");
        jpql.append("                   and r.status in (3, 4)                ");
        jpql.append("                   and r.audittime >= trunc(sysdate) - 6 ");
        jpql.append("                 order by r.audittime desc) a            ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));

        StringBuffer cntJpql = new StringBuffer();
        cntJpql.append("select count(1)                         ");
        cntJpql.append("  from d_afterservice_rightmaint      t,");
        cntJpql.append("       d_afterservice_rightmaintaudit r,");
        cntJpql.append("       d_recordinfo                   c ");
        cntJpql.append(" where t.complaintno = r.complaintno    ");
        cntJpql.append("   and r.complaintno = c.cardnumber     ");
        cntJpql.append("   and t.reviewstate in (3, 4)          ");
        cntJpql.append("   and c.checkresult = 3                ");
        cntJpql.append("   and r.status in (3, 4)               ");
        cntJpql.append("   and r.audittime >= trunc(sysdate) - 6");

        List<Object[]> qryList = query.getResultList();
        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("text", rs[2].toString() + DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, ((BigDecimal) rs[3]).intValue()) + "的客户权益单号【" + rs[0].toString() + "】于" + rs[5].toString() + "【" + DataDict.getDictName(DataDict.RIGHTMAINT_REVIEWSTATE, ((BigDecimal) rs[1]).intValue()) + "】");
            rsList.add(map);
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("totalCount", this.executeSQLQueryOne(cntJpql.toString()));
        rsMap.put("records", rsList);
        return rsMap;
    }

    /**
     * @author：zzy 站内信-活动管理
     */
    @Override
    public Map queryActivity(Map searchParam) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select *                                                               ");
        jpql.append("  from (select r.*, rownum rnum                                        ");
        jpql.append("          from (select t.activityno,                                   ");
        jpql.append("                       t.auditstatus,                                  ");
        jpql.append("                       c.checkusername,                                ");
        jpql.append("                       c.checkresult,                                  ");
        jpql.append("                       a.status,                                       ");
        jpql.append("                       to_char(a.audittime, 'yyyy-mm-dd')              ");
        jpql.append("                  from d_activity t, d_activity_audit a, d_recordinfo c");
        jpql.append("                 where t.activityno = a.activityno                     ");
        jpql.append("                   and a.activityno = c.cardnumber                     ");
        jpql.append("                   and t.auditstatus in (2, 3)                         ");
        jpql.append("                   and c.checkresult = 3                               ");
        jpql.append("                   and a.status in (2, 3)                              ");
        jpql.append("                   and a.audittime >= trunc(sysdate) - 6               ");
        jpql.append("                 order by a.audittime desc) r                          ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));

        StringBuffer cntJpql = new StringBuffer();
        cntJpql.append("select count(1)                                        ");
        cntJpql.append("  from d_activity t, d_activity_audit a, d_recordinfo c");
        cntJpql.append(" where t.activityno = a.activityno                     ");
        cntJpql.append("   and a.activityno = c.cardnumber                     ");
        cntJpql.append("   and t.auditstatus in (2, 3)                         ");
        cntJpql.append("   and c.checkresult = 3                               ");
        cntJpql.append("   and a.status in (2, 3)                              ");
        cntJpql.append("   and a.audittime >= trunc(sysdate) - 6               ");

        List<Object[]> qryList = query.getResultList();
        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("text", rs[2].toString() + DataDict.getDictName(DataDict.RIGHTMAINT_RESULT, ((BigDecimal) rs[3]).intValue()) + "的活动单号【" + rs[0].toString() + "】于" + rs[5].toString() + "【" + DataDict.getDictName(DataDict.ACTIVITY_AUDIT_STATUS, ((BigDecimal) rs[1]).intValue()) + "】");
            rsList.add(map);
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("totalCount", this.executeSQLQueryOne(cntJpql.toString()));
        rsMap.put("records", rsList);
        return rsMap;
    }

    /**
     * @author：zzy 站内信-合同管理
     */
    @Override
    public Map queryContract(Map searchParam) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT *                                                                                 ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum                                                          ");
        jpql.append("          FROM (select t.contractnum, to_char(t.invdate,'yyyy-mm-dd'), t.createuser      ");
        jpql.append("                from d_contract t                                                        ");
        jpql.append("                where sysdate + 89 >= t.invdate                                          ");
        jpql.append("                and t.invdate>= trunc(sysdate)                                           ");
        jpql.append("                order by t.invdate asc )a                                                ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));

        List<Object[]> qryList = query.getResultList();
        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("text", rs[2].toString() + "创建的合同编号【" + rs[0].toString() + "】于" + rs[1].toString() + "【失效】");
            rsList.add(map);
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("totalCount", this.executeSQLQueryOne("select count(1) from d_contract t where trunc(sysdate) + 89 >= t.invdate and t.invdate >= trunc(sysdate)"));
        rsMap.put("records", rsList);
        return rsMap;
    }

    /**
     * @author：zzy 站内信-个人客户
     */
    @Override
    public Map queryIndividcust(Map searchParam) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select *                                                         ");
        jpql.append("  from (select a.*, rownum rnum                                  ");
        jpql.append("          from (select count(1),                                 ");
        jpql.append("                       t.cuser,                                  ");
        jpql.append("                       to_char(t.cdate, 'yyyy-mm-dd')            ");
        jpql.append("                  from d_archive_individcust t                   ");
        jpql.append("                 where t.cdate >= trunc(sysdate) - 2             ");
        jpql.append("                   and t.cuser is not null                       ");
        jpql.append("                 group by t.cuser, to_char(t.cdate, 'yyyy-mm-dd')");
        jpql.append("                 order by to_char(t.cdate, 'yyyy-mm-dd') desc) a ");
        jpql.append(" WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));

        StringBuffer cntJpql = new StringBuffer();
        cntJpql.append("select count(distinct t.cuser || trunc(t.cdate))");
        cntJpql.append("  from d_archive_individcust t                  ");
        cntJpql.append(" where t.cdate >= trunc(sysdate) - 2            ");
        cntJpql.append("   and t.cuser is not null                      ");

        List<Object[]> qryList = query.getResultList();
        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("text", rs[1].toString() + "于" + rs[2].toString() + "创建【" + rs[0].toString() + "】个客户");
            rsList.add(map);
        }
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("totalCount", this.executeSQLQueryOne(cntJpql.toString()));
        rsMap.put("records", rsList);
        return rsMap;
    }

}