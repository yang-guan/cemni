package com.huiju.sms.sms.eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.common.GlobalConst;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.CollectionUtils;
import com.huiju.sms.sms.entity.Sms;

@Stateless(mappedName = "SmsEaoBean")
@SuppressWarnings({ "unchecked" })
public class SmsEaoBean extends GenericEaoImpl<Sms, Long> implements SmsEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    /**
     * 2.2发送对象
     */
    @Override
    public List<IndividCust> qryObjCust(Long smsId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.individCustId, a.cardNo, a.name, a.mobile");
        jpql.append("  from d_archive_individcust a, d_sms_obj_individcust b");
        jpql.append(" where a.individcustid = b.individcustid               ");
        jpql.append("   and b.smsid = ?1                                    ");
        jpql.append("   and a.enable = 1                                    ");
        jpql.append("   and a.active <> 4                                   ");
        jpql.append("   and a.issendsms = 1                                 ");
        jpql.append(" order by a.individcustid desc                         ");

        Query query = em.createNativeQuery(jpql.toString(), IndividCust.class).setParameter(1, smsId);
        return query.getResultList();
    }

    /**
     * 2.1发送条件
     */
    public List<String> qrySms_ObjCond(Long smsId, Integer type) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select c.comptable, c.compcolumn, b.compsymbol, b.compval");
        jpql.append("  from d_sms_obj_condition b, d_sms_type_cond c");
        jpql.append(" where b.typeCondId = c.typeCondId             ");
        jpql.append("   and b.smsid = ?1                            ");
        jpql.append(" order by c.comptable                          ");// 必须排序，方便在循环时“where or 并集”处理

        Query query = em.createNativeQuery(jpql.toString()).setParameter(1, smsId);
        List<Object[]> condList = query.getResultList();

        List<String> rsList = new ArrayList<String>();

        if (!CollectionUtils.isEmpty(condList)) {
            StringBuffer mobileJpql = new StringBuffer();
            String lastTable = null;
            String compsymbol;
            for (Object[] rs : condList) {
                // 需要确保字典表值中“级别越高值越小”：此处的值使用反向处理
                compsymbol = rs[2].toString();
                if (compsymbol.indexOf(">") != -1) {
                    compsymbol = compsymbol.replace(">", "<");
                } else if (compsymbol.indexOf("<") != -1) {
                    compsymbol = compsymbol.replace("<", ">");
                }
                String whereStr = rs[1].toString() + compsymbol + rs[3].toString();
                String compTable = rs[0].toString();

                if (lastTable == null) {
                    lastTable = compTable;
                    mobileJpql.append("select mobile from " + compTable + " where " + whereStr);
                } else if (!compTable.equals(lastTable)) {
                    lastTable = compTable;
                    mobileJpql.append(" union select mobile from " + compTable + " where " + whereStr);
                } else {
                    mobileJpql.append(" or " + whereStr);
                }
            }

            String jpqlStr = "select a.mobile from d_archive_individcust a, (" + mobileJpql.toString() + ") b where length(a.mobile) = 11 and a.mobile = b.mobile and a.enable = 1 and a.active <> 4 and a.issendsms = 1";
            // 特殊处理：生日短信
            if (type == GlobalConst.SMS_TYPE_1) {
                jpqlStr += " and a.birthmonthday = to_char(sysdate, 'mm-dd')";
            }
            Query mobileQry = em.createNativeQuery(jpqlStr);
            rsList = mobileQry.getResultList();
        }
        em.clear();
        return rsList;
    }

}