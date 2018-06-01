package com.huiju.inter.posorder.eao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.CollectionUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "PosOrderEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PosOrderEaoBean extends GenericEaoImpl<PosOrder, Long> implements PosOrderEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public List loadPos(Long activityId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select posNo,                                                ");
        jpql.append("       posbillDate,                                          ");
        jpql.append("       cardName,                                             ");
        jpql.append("       d.cardNo,                                             ");
        jpql.append("       goodsName,                                            ");
        jpql.append("       nvl(goodsCnt, 0),                                     ");
        jpql.append("       goodsPrice,                                           ");
        jpql.append("       nvl(actualSaleAmount, 0),                             ");
        jpql.append("       nvl(jewelDiscountAmount, 0),                          ");
        jpql.append("       storeName,                                            ");
        jpql.append("       mainclerkName,                                        ");
        jpql.append("       assistantName,                                        ");
        jpql.append("       d.couponno                                            ");
        jpql.append("  from d_activity            a,                              ");
        jpql.append("       d_activity_indipartin b,                              ");
        jpql.append("       d_archive_individcust c,                              ");
        jpql.append("       d_pos_order           d                               ");
        jpql.append(" where a.activityId = b.activityid                           ");
        jpql.append("   and c.individcustid = b.individcustid                     ");
        jpql.append("   and c.cardNo = d.cardNo                                   ");
        jpql.append("   and a.activityid = ?1                                     ");
        jpql.append("   and d.posbilldate between a.begintime and a.begintime + 90");
        jpql.append(" order by d.posid desc                                       ");

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString(), activityId);
        List rsList = new ArrayList();

        if (!CollectionUtils.isEmpty(qryList)) {
            int _cnt = 0;
            double _actualSaleAmount = 0;
            double _jewelDiscountAmount = 0;
            Map distinctCardNoMap = new HashMap();

            for (Object[] rs : qryList) {
                int goodsCnt = ((BigDecimal) rs[5]).intValue();
                double actualSaleAmount = ((BigDecimal) rs[7]).doubleValue();
                double jewelDiscountAmount = ((BigDecimal) rs[8]).doubleValue();
                _cnt += goodsCnt;
                _actualSaleAmount += actualSaleAmount;
                _jewelDiscountAmount += jewelDiscountAmount;

                Map map = new HashMap();
                map.put("posNo", rs[0]);
                map.put("posbillDate", rs[1]);
                map.put("cardName", rs[2]);
                map.put("cardNo", rs[3]);
                map.put("goodsName", rs[4]);
                map.put("goodsCnt", goodsCnt);
                map.put("goodsPrice", rs[6]);
                map.put("actualSaleAmount", actualSaleAmount);
                map.put("jewelDiscountAmount", jewelDiscountAmount);
                map.put("storeName", rs[9]);
                map.put("mainclerkName", rs[10]);
                map.put("assistantName", rs[11]);
                map.put("couponNo", rs[12]);
                rsList.add(map);

                distinctCardNoMap.put(rs[3], null);
            }
            Map map = new HashMap();
            map.put("posNo", "合计");
            map.put("cardName", "会员数量：" + distinctCardNoMap.size());
            map.put("goodsCnt", _cnt);
            map.put("actualSaleAmount", NeuUtils.formatMath(_actualSaleAmount));
            map.put("jewelDiscountAmount", NeuUtils.formatMath(_jewelDiscountAmount));
            rsList.add(0, map);
        }
        return rsList;
    }

}