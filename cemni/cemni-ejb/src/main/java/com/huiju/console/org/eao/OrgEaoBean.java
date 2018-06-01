package com.huiju.console.org.eao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.huiju.console.org.entity.Org;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "OrgEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrgEaoBean extends GenericEaoImpl<Org, Long> implements OrgEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public List selOrgByParent(Integer type, Long fromOrgId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select orgId, orgCode, name, type");
        jpql.append("  from d_cn_org                  ");
        jpql.append(" where type = ?1                 ");
        jpql.append("   and Isvalid = 1               ");
        jpql.append(" start with orgid = ?2           ");
        jpql.append("connect by prior orgid = parentid");
        jpql.append(" order siblings by orgid         ");

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(1, type);
        query.setParameter(2, fromOrgId);
        List<Object[]> qryList = query.getResultList();

        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("orgId", rs[0]);
            map.put("orgCode", rs[1]);
            map.put("name", rs[2]);
            map.put("type", rs[3]);
            rsList.add(map);
        }
        return rsList;
    }

    @Override
    public List getOrgTreeList(String orgName) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select t.orgid,                                  ");
        jpql.append("       t.type,                                   ");
        jpql.append("       t.orgcode,                                ");
        jpql.append("       t.name,                                   ");
        jpql.append("       decode(t.type, 2, '', 3, '&nbsp;&nbsp;', 4, '&nbsp;&nbsp;&nbsp;&nbsp;', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;') || t.name,");
        jpql.append("                                                 ");
        jpql.append("       (case                                     ");
        jpql.append("         when t.type = 2 then                    ");
        jpql.append("          t.orgcode                              ");
        jpql.append("         else                                    ");
        jpql.append("          (select a.orgcode                      ");
        jpql.append("             from d_cn_org a                     ");
        jpql.append("            where a.type = 2                     ");
        jpql.append("              and a.isvalid = 1                  ");
        jpql.append("            start with a.orgid = t.orgid         ");
        jpql.append("           connect by prior a.parentid = a.orgid)");
        jpql.append("       end) as deptcode,                         ");
        jpql.append("                                                 ");
        jpql.append("       (case                                     ");
        jpql.append("         when t.type = 2 then                    ");
        jpql.append("          ''                                     ");
        jpql.append("         when t.type = 3 then                    ");
        jpql.append("          t.orgcode                              ");
        jpql.append("         else                                    ");
        jpql.append("          (select a.orgcode                      ");
        jpql.append("             from d_cn_org a                     ");
        jpql.append("            where a.type = 3                     ");
        jpql.append("              and a.isvalid = 1                  ");
        jpql.append("            start with a.orgid = t.orgid         ");
        jpql.append("           connect by prior a.parentid = a.orgid)");
        jpql.append("       end) as bigAreaCode,                      ");
        jpql.append("                                                 ");
        jpql.append("       (case                                     ");
        jpql.append("         when t.type in (2, 3) then              ");
        jpql.append("          ''                                     ");
        jpql.append("         when t.type = 4 then                    ");
        jpql.append("          t.orgcode                              ");
        jpql.append("         else                                    ");
        jpql.append("          (select a.orgcode                      ");
        jpql.append("             from d_cn_org a                     ");
        jpql.append("            where a.type = 4                     ");
        jpql.append("              and a.isvalid = 1                  ");
        jpql.append("            start with a.orgid = t.orgid         ");
        jpql.append("           connect by prior a.parentid = a.orgid)");
        jpql.append("       end) as areaCode,                         ");
        jpql.append("       t.storeid                                 ");
        jpql.append("  from d_cn_org t                                ");
        jpql.append(" where t.isvalid = 1                             ").append(StringUtils.isNoneBlank(orgName) ? ("and t.name like '%" + orgName + "%'") : "");
        jpql.append(" start with t.type = 2                           ");
        jpql.append("connect by prior t.orgid = t.parentid            ");
        jpql.append(" order siblings by t.orderno, t.orgid            ");

        Query query = em.createNativeQuery(jpql.toString());
        List<Object[]> qryList = query.getResultList();

        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("orgId", rs[0]);
            map.put("type", rs[1]);
            map.put("orgCode", rs[2]);
            map.put("name", rs[3]);
            map.put("otherName", rs[4]);
            map.put("deptCode", rs[5]);
            map.put("bigAreaCode", rs[6]);
            map.put("areaCode", rs[7]);
            map.put("storeId", rs[8]);
            rsList.add(map);
        }
        return rsList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List qryBigAreaStore_dict(Integer dictCode) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.bigareaid,               ");
        jpql.append("       a.bigareano,               ");
        jpql.append("       a.bigareaname,             ");
        jpql.append("       a.areaid,                  ");
        jpql.append("       a.areano,                  ");
        jpql.append("       a.areaname,                ");
        jpql.append("       a.storeid,                 ");
        jpql.append("       a.storeno,                 ");
        jpql.append("       a.name,                    ");
        jpql.append("       a.attr,                    ");
        jpql.append("       (select n.name from d_data_dict n where n.dictcode = 8400 and n.value = a.attr),");
        jpql.append("       b.name,                    ");
        jpql.append("       b.value                    ");
        jpql.append("  from d_cn_store a, d_data_dict b");
        jpql.append(" where a.areaid is not null       ");
        jpql.append("   and a.isvalid = 1              ");
        jpql.append("   and b.dictcode = ?1            ");
        jpql.append(" order by a.bigAreaNo, a.areaNo, a.attr, a.form, a.storeId, b.orderno");

        Query query = em.createNativeQuery(jpql.toString()).setParameter(1, dictCode);
        List<Object[]> qryList = query.getResultList();

        List rsList = new ArrayList();
        Map map;
        for (Object[] rs : qryList) {
            map = new HashMap();
            map.put("bigAreaId", rs[0]);
            map.put("bigAreaNo", rs[1]);
            map.put("bigAreaName", rs[2]);
            map.put("areaId", rs[3]);
            map.put("areaNo", rs[4]);
            map.put("areaName", rs[5]);
            map.put("storeId", rs[6]);
            map.put("storeNo", rs[7]);
            map.put("name", rs[8]);
            map.put("attr", rs[9]);
            map.put("attrName", rs[10]);
            map.put("dictName", rs[11]);
            map.put("dictValue", rs[12]);
            rsList.add(map);
        }
        return rsList;
    }

}