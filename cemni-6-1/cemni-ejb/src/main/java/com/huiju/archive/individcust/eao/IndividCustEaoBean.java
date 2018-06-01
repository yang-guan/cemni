package com.huiju.archive.individcust.eao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.Page;
import com.huiju.module.data.eao.GenericEaoImpl;
import com.huiju.module.util.StringUtils;
import com.huiju.utils.NeuUtils;

@Stateless(mappedName = "IndividCustEaoBean")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IndividCustEaoBean extends GenericEaoImpl<IndividCust, Long> implements IndividCustEaoLocal {
    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public Page<IndividCust> findAllExcludeBolb(Page<IndividCust> page, Map<String, Object> paramsMap) {
        String subJpql = this.getIndividCustWhereStr(paramsMap);

        String cntJpql = "select count(1) from d_archive_individcust t where 1 = 1" + subJpql;
        BigDecimal cntRs = (BigDecimal) this.executeSQLQueryOne(cntJpql);

        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT *                                        ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum                 ");
        jpql.append("          FROM (SELECT t.individcustid,         ");
        jpql.append("                       t.cardno,                ");
        jpql.append("                       t.name,                  ");
        jpql.append("                       t.type,                  ");
        jpql.append("                       t.mobile,                ");
        jpql.append("                       t.gender,                ");
        jpql.append("                       t.credit,                ");
        jpql.append("                       t.enable,                ");
        jpql.append("                       t.active,                ");
        jpql.append("                       t.lastbuytime,           ");
        jpql.append("                       t.laststoreno,           ");
        jpql.append("                       t.belongstorename ,      ");
        jpql.append("                       t.fresh                  ");
        jpql.append("                  FROM d_archive_individcust t  ");
        jpql.append("                 WHERE  1 = 1                   ").append(subJpql);
        jpql.append("                 ORDER BY t.individcustid asc) a");
        jpql.append("         WHERE ROWNUM <= " + page.getLimit() + page.getStart() + ") WHERE rnum > " + page.getStart());

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString());
        // 拼接结果
        List<IndividCust> rsList = new ArrayList<IndividCust>();
        for (Object[] obj : qryList) {
            IndividCust dt = new IndividCust();
            dt.setIndividCustId(((BigDecimal) obj[0]).longValue());
            dt.setCardNo((String) obj[1]);
            dt.setName((String) obj[2]);
            dt.setType(((BigDecimal) obj[3]).intValue());
            dt.setMobile(((BigDecimal) obj[4]).longValue());
            dt.setGender(((BigDecimal) obj[5]).intValue());
            dt.setCredit(((BigDecimal) obj[6]).doubleValue());
            dt.setEnable(((BigDecimal) obj[7]).intValue());
            dt.setActive(((BigDecimal) obj[8]).intValue());
            dt.setLastBuyTime(null == obj[9] ? null : NeuUtils.parseCalendar(((Timestamp) obj[9]).toString(), "yyyy-MM-dd"));
            dt.setLastStoreNo((String) obj[10]);
            dt.setBelongStoreName((String) obj[11]);
            dt.setFresh(((BigDecimal) obj[12]).intValue());
            rsList.add(dt);
        }
        page.setResult(rsList);
        page.setTotalCount(cntRs.longValue());
        return page;
    }

    @Override
    public void updateBelongStore(String isAllSelected, List<Long> individCustIds, Map searchParam, String belongStoreNo, String belongStoreName, String startPage, String endPage) {
        String subJpql = null;
        if ("1".equals(isAllSelected)) {
            subJpql = this.getIndividCustWhereStr(searchParam);
        } else {
            subJpql = " and t.individcustid in (" + StringUtils.join(individCustIds.toArray(), ",") + ")";
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append("update d_archive_individcust f                                              ");
        jpql.append("   set f.belongstoreno = '" + belongStoreNo + "', f.belongstorename = '" + belongStoreName + "'");
        jpql.append(" where f.individcustid in (select individcustid                             ");
        jpql.append("                             from (select a.individcustid, rownum rnum      ");
        jpql.append("                                     from (select t.individcustid           ");
        jpql.append("                                             from d_archive_individcust t   ");
        jpql.append("                                            where 1 = 1                     ").append(subJpql);
        jpql.append("                                            order by t.belongstorename desc) a");

        if (StringUtils.isBlank(endPage)) {
            jpql.append(")");
        } else {
            jpql.append(" where rownum <= " + Integer.parseInt(endPage) * 20 + ")");
        }
        if (StringUtils.isBlank(startPage)) {
            jpql.append(")");
        } else {
            jpql.append("where rnum > " + (Integer.parseInt(startPage) - 1) * 20 + ")");
        }
        this.executeSQLUpdate(jpql.toString());
    }

    @Override
    public String getIndividCustWhereStr(Map searchParam) {
        StringBuffer jpql = new StringBuffer();

        if (null != searchParam.get("LIKE_cardNo") && !"".equals(searchParam.get("LIKE_cardNo"))) {
            jpql.append(" and t.cardNo like '%" + searchParam.get("LIKE_cardNo") + "%'");
        } else if (null != searchParam.get("EQ_cardNo") && !"".equals(searchParam.get("EQ_cardNo"))) {
            jpql.append(" and t.cardNo = '" + searchParam.get("EQ_cardNo") + "'");
        }
        // 客户类型
        if (null != searchParam.get("IN_type") && !"".equals(searchParam.get("IN_type"))) {
            jpql.append(" and t.type in (" + searchParam.get("IN_type") + ")");
        }
        if (null != searchParam.get("LIKE_mobile") && !"".equals(searchParam.get("LIKE_mobile"))) {
            jpql.append(" and t.mobile = '%" + searchParam.get("LIKE_mobile") + "%'");
        } else if (null != searchParam.get("EQ_mobile") && !"".equals(searchParam.get("EQ_mobile"))) {
            jpql.append(" and t.mobile = " + searchParam.get("EQ_mobile"));
        }
        if (null != searchParam.get("LIKE_name") && !"".equals(searchParam.get("LIKE_name"))) {
            jpql.append(" and t.name like '%" + searchParam.get("LIKE_name") + "%' ");
        } else if (null != searchParam.get("EQ_name") && !"".equals(searchParam.get("EQ_name"))) {
            jpql.append(" and t.name = '" + searchParam.get("EQ_name") + "'");
        }
        if (null != searchParam.get("IN_lv") && !"".equals(searchParam.get("IN_lv"))) {
            jpql.append(" and t.lv in (" + searchParam.get("IN_lv") + ")");
        }
        if (null != searchParam.get("IN_active") && !"".equals(searchParam.get("IN_active"))) {
            jpql.append(" and t.active in (" + searchParam.get("IN_active") + ")");
        }
        if (null != searchParam.get("IN_status") && !"".equals(searchParam.get("IN_status"))) {
            jpql.append(" and t.status in (" + searchParam.get("IN_status") + ")");
        }
        if (null != searchParam.get("IN_fresh") && !"".equals(searchParam.get("IN_fresh"))) {
            jpql.append(" and t.fresh in (" + searchParam.get("IN_fresh") + ")");
        }
        if (null != searchParam.get("IN_enable") && !"".equals(searchParam.get("IN_enable"))) {
            jpql.append(" and t.enable in (" + searchParam.get("IN_enable") + ")");
        }
        if (null != searchParam.get("GE_credit") && !"".equals(searchParam.get("GE_credit"))) {
            jpql.append(" and  t.credit >= " + searchParam.get("GE_credit"));
        }
        if (null != searchParam.get("LE_credit") && !"".equals(searchParam.get("LE_credit"))) {
            jpql.append(" and  t.credit <=  " + searchParam.get("LE_credit"));
        }
        // 累计珠宝折算额
        if (null != searchParam.get("GE_jewerlyAmount") && !"".equals(searchParam.get("GE_jewerlyAmount"))) {
            jpql.append(" and t.jewerlyamount >= " + searchParam.get("GE_jewerlyAmount"));
        }
        if (null != searchParam.get("LE_jewerlyAmount") && !"".equals(searchParam.get("LE_jewerlyAmount"))) {
            jpql.append(" and t.jewerlyamount <= " + searchParam.get("LE_jewerlyAmount"));
        }
        // 生日
        if (null != searchParam.get("GE_birthday") && !"".equals(searchParam.get("GE_birthday"))) {
            jpql.append(" and t.birthday >= to_date('" + searchParam.get("GE_birthday") + "', 'yyyy-mm-dd')");
        }
        if (null != searchParam.get("LE_birthday") && !"".equals(searchParam.get("LE_birthday"))) {
            jpql.append(" and t.birthday <= to_date('" + searchParam.get("LE_birthday") + "', 'yyyy-mm-dd')");
        }
        // 出生月日
        if (null != searchParam.get("GE_birthMonthday") && !"".equals(searchParam.get("GE_birthMonthday"))) {
            jpql.append(" and t.birthMonthday >= '" + searchParam.get("GE_birthMonthday") + "'");
        }
        if (null != searchParam.get("LE_birthMonthday") && !"".equals(searchParam.get("LE_birthMonthday"))) {
            jpql.append(" and t.birthMonthday <= '" + searchParam.get("LE_birthMonthday") + "'");
        }
        // 末次消费日期
        if (null != searchParam.get("GE_lastBuyTime") && !"".equals(searchParam.get("GE_lastBuyTime"))) {
            jpql.append(" and t.lastbuytime >= to_date('" + searchParam.get("GE_lastBuyTime") + "', 'yyyy-mm-dd') ");
        }
        if (null != searchParam.get("LE_lastBuyTime") && !"".equals(searchParam.get("LE_lastBuyTime"))) {
            jpql.append(" and t.lastbuytime <= to_date('" + searchParam.get("LE_lastBuyTime") + "','yyyy-mm-dd') + 1");
        }
        // 创建日期
        if (null != searchParam.get("GE_createTime") && !"".equals(searchParam.get("GE_createTime"))) {
            jpql.append(" and t.cdate >= to_date('" + searchParam.get("GE_createTime") + "', 'yyyy-mm-dd') ");
        }
        if (null != searchParam.get("LE_createTime") && !"".equals(searchParam.get("LE_createTime"))) {
            jpql.append(" and t.cdate <= to_date('" + searchParam.get("LE_createTime") + "','yyyy-mm-dd') + 1");
        }
        // 归属门店
        Object IN_belongStoreNo = searchParam.get("IN_belongStoreNo");
        Object NOT_IN_belongStoreNo = searchParam.get("NOT_IN_belongStoreNo");
        if (null != IN_belongStoreNo && !"".equals(IN_belongStoreNo)) {
            jpql.append(" and t.belongstoreno in ('" + IN_belongStoreNo.toString().replaceAll(",", "','") + "')");
        } else if (null != searchParam.get("EQ_belongStoreNo") && !"".equals(searchParam.get("EQ_belongStoreNo"))) {
            jpql.append(" and t.belongstoreno = " + searchParam.get("EQ_belongStoreNo"));
        } else if (null != NOT_IN_belongStoreNo && !"".equals(NOT_IN_belongStoreNo)) {
            jpql.append(" and t.belongstoreno not in ('" + NOT_IN_belongStoreNo.toString().replaceAll(",", "','") + "')");
        }
        // 末次消费门店
        Object IN_lastStoreNo = searchParam.get("IN_lastStoreNo");
        if (null != IN_lastStoreNo && !"".equals(IN_lastStoreNo)) {
            jpql.append(" and t.laststoreno in ('" + IN_lastStoreNo.toString().replaceAll(",", "','") + "')");
        }
        // 省市县
        if (null != searchParam.get("EQ_province") && !"".equals(searchParam.get("EQ_province"))) {
            jpql.append(" and t.province = " + searchParam.get("EQ_mobile"));
        }
        if (null != searchParam.get("EQ_city") && !"".equals(searchParam.get("EQ_city"))) {
            jpql.append(" and t.city = " + searchParam.get("EQ_city"));
        }
        if (null != searchParam.get("EQ_county") && !"".equals(searchParam.get("EQ_county"))) {
            jpql.append(" and t.county = " + searchParam.get("EQ_county"));
        }
        // 来源
        if (null != searchParam.get("IN_sources") && !"".equals(searchParam.get("IN_sources"))) {
            jpql.append(" and t.sources in (" + searchParam.get("IN_sources") + ")");
        }

        Set<String> keySet = searchParam.keySet();
        for (String str : keySet) {
            // 是否关联pos
            if (str.contains("_posOrderList_")) {
                jpql.append(" and exists (select 1 from d_pos_order a where a.individcustid = t.individcustid");
                // 商品条码
                if (null != searchParam.get("LIKE_posOrderList_goodsBar") && !"".equals(searchParam.get("LIKE_posOrderList_goodsBar"))) {
                    jpql.append(" and a.goodsbar like '%" + searchParam.get("LIKE_posOrderList_goodsBar") + "%'");
                }
                if (null != searchParam.get("IN_posOrderList_scoreSegment") && !"".equals(searchParam.get("IN_posOrderList_scoreSegment"))) {
                    jpql.append(" and a.scoresegment in (" + searchParam.get("IN_posOrderList_scoreSegment") + ")");
                }
                if (null != searchParam.get("IN_posOrderList_stockType") && !"".equals(searchParam.get("IN_posOrderList_stockType"))) {
                    jpql.append(" and a.stocktype in (" + searchParam.get("IN_posOrderList_stockType") + ")");
                }
                if (null != searchParam.get("IN_posOrderList_designerStyle") && !"".equals(searchParam.get("IN_posOrderList_designerStyle"))) {
                    jpql.append(" and a.designerstyle in (" + searchParam.get("IN_posOrderList_designerStyle") + ")");
                }
                if (null != searchParam.get("EQ_posOrderList_goodsBar") && !"".equals(searchParam.get("EQ_posOrderList_goodsBar"))) {
                    jpql.append(" and a.goodsBar = '" + searchParam.get("EQ_posOrderList_goodsBar") + "'");
                }
                jpql.append(")");
            }else
            // 是否关联门店档案
            if (str.contains("_StoreList_")) {
                jpql.append(" and exists (select 1 from d_cn_store c where c.storeno = t.belongstoreno");
                if (null != searchParam.get("IN_StoreList_ATTR") && !"".equals(searchParam.get("IN_StoreList_ATTR"))) {
                    jpql.append(" and c.attr in (" + searchParam.get("IN_StoreList_ATTR")+ ")"); 
                }       
                jpql.append("and c.isvalid=1 )");
            }
        }
        return jpql.toString();
    }
    
   
    @Override
    public Page<IndividCust> findCustPage(Page<IndividCust> page, List<Map<String, Object>> paramsList) {
        String cardNos = convertInString(paramsList, "cardNo");
        String mobiles = convertInString(paramsList, "mobile");

        String cntJpql = "select count(1) from d_archive_individcust t where t.cardno in (" + cardNos + ") or t.mobile in (" + mobiles + ")";
        BigDecimal cntRs = (BigDecimal) this.executeSQLQueryOne(cntJpql);

        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT *                                         ");
        jpql.append("  FROM (SELECT a.*, ROWNUM rnum                  ");
        jpql.append("          FROM (SELECT t.individcustid,          ");
        jpql.append("                       t.cardno,                 ");
        jpql.append("                       t.name,                   ");
        jpql.append("                       t.type,                   ");
        jpql.append("                       t.mobile,                 ");
        jpql.append("                       t.gender,                 ");
        jpql.append("                       t.credit,                 ");
        jpql.append("                       t.enable,                 ");
        jpql.append("                       t.active,                 ");
        jpql.append("                       t.lastbuytime,            ");
        jpql.append("                       t.laststoreno,            ");
        jpql.append("                       t.belongstorename         ");
        jpql.append("                  FROM d_archive_individcust t   ");
        jpql.append("                 WHERE t.cardno in (" + cardNos + ") or t.mobile in (" + mobiles + ")");
        jpql.append("                 ORDER BY t.individcustid asc) a");
        jpql.append("         WHERE ROWNUM <= " + page.getLimit() + page.getStart() + ") WHERE rnum > " + page.getStart());

        List<Object[]> qryList = this.executeSQLQuery(jpql.toString());
        // 拼接结果
        List<IndividCust> rsList = new ArrayList<IndividCust>();
        for (Object[] obj : qryList) {
            IndividCust dt = new IndividCust();
            dt.setIndividCustId(((BigDecimal) obj[0]).longValue());
            dt.setCardNo((String) obj[1]);
            dt.setName((String) obj[2]);
            dt.setType(((BigDecimal) obj[3]).intValue());
            dt.setMobile(((BigDecimal) obj[4]).longValue());
            dt.setGender(((BigDecimal) obj[5]).intValue());
            dt.setCredit(((BigDecimal) obj[6]).doubleValue());
            dt.setEnable(((BigDecimal) obj[7]).intValue());
            dt.setActive(((BigDecimal) obj[8]).intValue());
            dt.setLastBuyTime(null == obj[9] ? null : NeuUtils.parseCalendar(((Timestamp) obj[9]).toString(), "yyyy-MM-dd"));
            dt.setLastStoreNo((String) obj[10]);
            dt.setBelongStoreName((String) obj[11]);
            rsList.add(dt);
        }
        page.setResult(rsList);
        page.setTotalCount(cntRs.longValue());
        return page;
    }

    @Override
    public String convertInString(List rsList, String str) {
        StringBuffer bf = new StringBuffer();
        for (Object obj : rsList) {
            Map map = (Map) obj;
            if ("cardNo".equals(str)) {
                bf.append(",'").append(map.get(str)).append("'");
            } else if ("mobile".equals(str)) {
                bf.append(",").append(map.get(str));
            }
        }
        return bf.substring(1).toString();
    }

}