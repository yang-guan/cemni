package com.huiju.inter.saleorder.eao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.saleorder.entity.SaleOrder;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.CollectionUtils;

@Stateless(mappedName = "SaleOrderEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SaleOrderEaoBean extends GenericEaoImpl<SaleOrder, Long> implements SaleOrderEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public List loadOrder(Long activityId, Integer actType) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select a.orderId,                                                          ");
        jpql.append("       a.orderNo,                                                          ");
        jpql.append("       to_char(a.orderDate, 'yyyy-mm-dd'),                                 ");
        jpql.append("       a.fraCode,                                                          ");
        jpql.append("       a.fraName,                                                          ");
        jpql.append("       a.type,                                                             ");
        jpql.append("       a.storeNo,                                                          ");
        jpql.append("       a.storeName,                                                        ");
        jpql.append("       nvl(a.totalAmount, 0),                                              ");
        jpql.append("       b.franchiseeId,                                                     ");
        jpql.append("       c.activityId                                                        ");
        jpql.append("  from d_sale_order a, d_franchisee b, d_activity_frapartin c, d_activity d");
        jpql.append(" where a.fracode = b.fracode                                               ");
        jpql.append("   and b.franchiseeid = c.franchiseeid                                     ");
        jpql.append("   and c.activityid = d.activityid                                         ");
        jpql.append("   and a.orderDate between d.begintime and " + (actType == 9 ? "d.endTime" : "add_months(beginTime, 3)"));
        jpql.append("   and d.activityId = ?1                                                   ");

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString(), activityId);
        List rsList = new ArrayList();

        if (!CollectionUtils.isEmpty(qryList)) {
            double _totalAmount = 0;

            for (Object[] rs : qryList) {
                double totalAmount = ((BigDecimal) rs[8]).doubleValue();
                _totalAmount += totalAmount;

                Map map = new HashMap();
                map.put("orderId", rs[0]);
                map.put("orderNo", rs[1]);
                map.put("orderDate", rs[2]);
                map.put("fraCode", rs[3]);
                map.put("fraName", rs[4]);
                map.put("type", rs[5]);
                map.put("storeNo", rs[6]);
                map.put("storeName", rs[7]);
                map.put("totalAmount", totalAmount);
                map.put("franchiseeId", rs[9]);
                map.put("activityId", rs[10]);
                rsList.add(map);
            }
            Map map = new HashMap();
            map.put("fraCode", "合计");
            map.put("totalAmount", new BigDecimal(_totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            rsList.add(0, map);
        }
        return rsList;
    }

    @Override
    public int loadFra(Long franchiseeId, Long activityId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select count(1)                                                            ");
        jpql.append("  from d_sale_order a, d_franchisee b, d_activity_frapartin c, d_activity d");
        jpql.append(" where a.fracode = b.fracode                                               ");
        jpql.append("   and b.franchiseeid = c.franchiseeid                                     ");
        jpql.append("   and c.activityid = d.activityid                                         ");
        jpql.append("   and a.orderDate between d.begintime and d.endtime                       ");
        jpql.append("   and b.franchiseeid = ?1                                                   ");
        jpql.append("   and d.activityId = ?2                                                 ");

        BigDecimal cnt = (BigDecimal) this.executeSQLQueryOne(jpql.toString(), new Object[] { franchiseeId, activityId });
        return cnt.intValue();
    }

}