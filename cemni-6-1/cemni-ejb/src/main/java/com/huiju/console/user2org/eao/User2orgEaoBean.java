package com.huiju.console.user2org.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.common.GlobalConst;
import com.huiju.console.user2org.entity.User2org;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.CollectionUtils;
import com.huiju.module.util.StringUtils;

@Stateless(mappedName = "User2orgEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class User2orgEaoBean extends GenericEaoImpl<User2org, Long> implements User2orgEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public Map queryUser(Map searchParam) {
        String subJpql = "";
        Object userCodeOrName = searchParam.get("userCodeOrName");
        if (userCodeOrName != null) {
            String userCodeOrNameStr = userCodeOrName.toString();
            if (!userCodeOrNameStr.isEmpty()) {
                subJpql = " and (a.userCode like '%" + userCodeOrName + "%' or a.userName like '%" + userCodeOrName + "%') ";
            }
        }

        String cntJpql = "select count(1) from s_user a where 1 = 1 " + subJpql;
        StringBuffer jpql = new StringBuffer();

        jpql.append("SELECT *                                                             ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum                                      ");
        jpql.append("          FROM (select a.userId,                                     ");
        jpql.append("                       a.clientCode,                                 ");
        jpql.append("                       a.userCode,                                   ");
        jpql.append("                       a.userName,                                   ");
        jpql.append("                       a.mobile,                                     ");
        jpql.append("                       a.email,                                      ");
        jpql.append("                       decode(a.status, 1, '启用', '停用') as statusName");
        jpql.append("                  from s_user a where 1 = 1                          ").append(subJpql);
        jpql.append("                 order by a.userCode) a                              ");
        jpql.append("         WHERE ROWNUM <= ?" + GlobalConst.SQL_PLACEHOLDER_END + ") WHERE rnum > ?" + GlobalConst.SQL_PLACEHOLDER_START);

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_START, searchParam.get("start"));
        query.setParameter(GlobalConst.SQL_PLACEHOLDER_END, Integer.parseInt(searchParam.get("start").toString()) + Integer.parseInt(searchParam.get("limit").toString()));
        List<Object[]> qryList = query.getResultList();

        Query role_query = em.createNativeQuery("select b.rolename from s_user_role a, s_role b where a.roleid = b.roleid and a.userid = ?1");
        Query org_query = em.createNativeQuery("select c.orgCode, c.name from d_cn_user2org b, d_cn_org c where b.orgid = c.orgid and b.userid = ?1 order by c.orgid ");

        List rsList = new ArrayList();
        Map map;
        List tempList;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("userId", rs[0]);
            map.put("clientCode", rs[1]);
            map.put("userCode", rs[2]);
            map.put("userName", rs[3]);
            map.put("mobile", rs[4]);
            map.put("email", rs[5]);
            map.put("statusName", rs[6]);
            // 角色
            role_query.setParameter(1, rs[0]);
            tempList = role_query.getResultList();
            map.put("roleNames", CollectionUtils.isEmpty(tempList) ? "" : StringUtils.join(tempList.toArray(), "、"));

            // 组织机构
            org_query.setParameter(1, rs[0]);
            tempList = org_query.getResultList();
            String orgCodes = "";
            String orgNames = "";
            Object[] rs2;
            for (int i = 0; i < tempList.size(); i++) {
                rs2 = (Object[]) tempList.get(i);
                orgCodes += "," + rs2[0].toString();
                orgNames += "、" + rs2[1].toString();
            }
            map.put("orgCodes", "".equals(orgCodes) ? null : orgCodes.substring(1));
            map.put("orgNames", "".equals(orgNames) ? null : orgNames.substring(1));
            rsList.add(map);
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("totalCount", this.executeSQLQueryOne(cntJpql));
        retMap.put("records", rsList);
        em.clear();
        return retMap;
    }

    @Override
    public List qryOrgByUserId(Long userId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select b.user2orgid,                        ");
        jpql.append("       c.orgcode,                           ");
        jpql.append("       c.name,                              ");
        jpql.append("       c.orgid,                             ");
        jpql.append("       c.type,                              ");
        jpql.append("       (select a.name                       ");
        jpql.append("          from d_data_dict a                ");
        jpql.append("         where a.dictcode = '8100'          ");
        jpql.append("           and a.value = c.type) as typeName");
        jpql.append("  from d_cn_user2org b, d_cn_org c          ");
        jpql.append(" where b.orgid = c.orgid                    ");
        jpql.append("   and b.userid = ?1                        ");
        jpql.append("  order by c.orgid                          ");

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString(), userId);

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("user2orgid", rs[0]);
            map.put("orgcode", rs[1]);
            map.put("name", rs[2]);
            map.put("orgid", rs[3]);
            map.put("type", rs[4]);
            map.put("typeName", rs[5]);
            rsList.add(map);
        }
        return rsList;
    }

    @Override
    public List qryOrgByUserIdAndParms(Map searchParam) {
        StringBuffer subJpql = new StringBuffer();
        Object orgCodeOrNameOrTypeName = searchParam.get("EQ_orgCode_OR_LIKE_name_OR_LIKE_typeName");
        if (orgCodeOrNameOrTypeName != null) {
            String orgCodeOrNameOrTypeNameStr = orgCodeOrNameOrTypeName.toString();
            if (!orgCodeOrNameOrTypeNameStr.isEmpty()) {
                subJpql.append(" and (t.orgCode = '" + orgCodeOrNameOrTypeName + "' or t.name like '%" + orgCodeOrNameOrTypeName + "%'" + " or t.typeName like '%" + orgCodeOrNameOrTypeName + "%')");
            }
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append(" select user2orgid, orgcode, name, orgid, type, typeName, userid   ");
        jpql.append("   from (select b.user2orgid,                                      ");
        jpql.append("                c.orgcode,                                         ");
        jpql.append("                c.name,                                            ");
        jpql.append("                c.orgid,                                           ");
        jpql.append("                c.type,                                            ");
        jpql.append("                (select a.name                                     ");
        jpql.append("                   from d_data_dict a                              ");
        jpql.append("                  where a.dictcode = '8100'                        ");
        jpql.append("                    and a.value = c.type) as typeName,             ");
        jpql.append("                b.userid                                           ");
        jpql.append("           from d_cn_user2org b, d_cn_org c                        ");
        jpql.append("          where b.orgid = c.orgid order by c.orgid) t              ");
        jpql.append("   where t.userid = ?1                                             ").append(subJpql.toString());

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString(), searchParam.get("EQ_userId").toString());

        List rsList = new ArrayList();
        for (Object[] rs : qryList) {
            Map map = new HashMap();
            map.put("user2orgid", rs[0]);
            map.put("orgcode", rs[1]);
            map.put("name", rs[2]);
            map.put("orgid", rs[3]);
            map.put("type", rs[4]);
            map.put("typeName", rs[5]);
            rsList.add(map);
        }
        return rsList;
    }

}