package com.huiju.actment.activity.logic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.actment.activity.eao.ActivityEaoLocal;
import com.huiju.actment.activity.eao.IndiPartInEaoLocal;
import com.huiju.actment.activity.entity.Activity;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.common.GlobalConst;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "ActivityBean")
public class ActivityBean extends GenericLogicImpl<Activity, Long> implements ActivityRemote {
    @EJB
    private ActivityEaoLocal activityEao;
    @EJB
    private IndiPartInEaoLocal indiPartInEao;
    @EJB
    private IndividCustEaoLocal individCustEao;

    @Override
    protected GenericEao<Activity, Long> getGenericEao() {
        return activityEao;
    }

    @Override
    public String getSeqActCouponNo() {
        return activityEao.executeSQLQueryOne("select lpad(seq_activity_cardcode.nextval, 8, 0) from dual");
    }

    @Override
    public void saveActivityByExcel(Activity activity, List<Map<String, Object>> paramsList) {
        Long count = this.queryCountByExcel(paramsList);
        if (activity.getIsCreateCard() == GlobalConst.NO) {
            activity.setCardAmount((long) 0);
        } else {
            activity.setCardAmount(count);
        }
        activityEao.persist(activity);
        this.saveIndiPartInExcel(activity, paramsList);
    }

    @Override
    public void saveIndiPartInExcel(Activity activity, List<Map<String, Object>> paramsList) {
        String activityNo = activity.getActivityNo();
        String cardNos = individCustEao.convertInString(paramsList, "cardNo");
        String mobiles = individCustEao.convertInString(paramsList, "mobile");
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into d_activity_indipartin ");
        sql.append("   (INDIPARTINID, ");
        sql.append("    ACTIVITYID, ");
        sql.append("    INDIVIDCUSTID, ");
        sql.append("    COUPONNO) ");
        sql.append(" select seq_activity_indipartin.nextval, ?,d.individcustid, ");

        sql.append("    ");
        if (activity.getIsCreateCard().intValue() == GlobalConst.YES) {
            sql.append("    '" + activityNo + "'||seq_activity_cardcode.nextval ");
        } else {
            sql.append("   null ");
        }
        sql.append("    from d_archive_individcust d ");
        sql.append("  where  d.cardno in (" + cardNos + ") or  d.mobile in (" + mobiles + ") ");

        individCustEao.executeSQLUpdate(sql.toString(), new Object[] { activity.getActivityId() });
    }

    @Override
    public void saveActivityByQuery(Activity activity, Map<String, Object> searchParams) {
        Long count = this.queryCountByQuery(searchParams);
        if (activity.getIsCreateCard() == GlobalConst.NO) {
            activity.setCardAmount((long) 0);
        } else {
            activity.setCardAmount(count);
        }
        activityEao.persist(activity);
        this.saveIndiPartInByQuery(activity, searchParams);
    }

    @Override
    public void saveIndiPartInByQuery(Activity activity, Map<String, Object> searchParams) {
        String activityNo = activity.getActivityNo();
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into d_activity_indipartin ");
        sql.append("   (INDIPARTINID, ");
        sql.append("    ACTIVITYID, ");
        sql.append("    INDIVIDCUSTID, ");
        sql.append("    COUPONNO) ");
        sql.append(" select seq_activity_indipartin.nextval, ?,t.individcustid, ");

        sql.append("    ");
        if (activity.getIsCreateCard().intValue() == GlobalConst.YES) {
            sql.append("    '" + activityNo + "'|| lpad(SEQ_ACTIVITY_CARDCODE.NEXTVAL,8,'0') ");
        } else {
            sql.append("   null ");
        }
        sql.append("    from d_archive_individcust t ");
        sql.append(" where 1 = 1 ");
        sql.append(individCustEao.getIndividCustWhereStr(searchParams));

        if (activity.getIsCreateCard().intValue() == GlobalConst.YES) {
        }
        individCustEao.executeSQLUpdate(sql.toString(), new Object[] { activity.getActivityId() });
    }

    @Override
    public void updateActivityByExcel(Activity dt, List<Map<String, Object>> paramsList) {
        Long count = queryCountByExcel(paramsList);
        if (dt.getIsCreateCard() == GlobalConst.NO) {
            dt.setCardAmount((long) 0);
        } else {
            dt.setCardAmount(count);
        }
        activityEao.merge(dt);
        this.saveIndiPartInExcel(dt, paramsList);
    }

    @Override
    public void updateActivityByQuery(Activity dt, Map<String, Object> paramsMap) {
        Long count = queryCountByQuery(paramsMap);
        if (dt.getIsCreateCard() == GlobalConst.NO) {
            dt.setCardAmount((long) 0);
        } else {
            dt.setCardAmount(count);
        }
        activityEao.merge(dt);
        this.saveIndiPartInByQuery(dt, paramsMap);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private Long queryCountByQuery(Map<String, Object> searchParams) {
        String jpql = "select count(1) from d_archive_individcust t where 1 = 1" + individCustEao.getIndividCustWhereStr(searchParams);
        BigDecimal cntRs = (BigDecimal) individCustEao.executeSQLQueryOne(jpql);
        return cntRs.longValue();
    }

    private Long queryCountByExcel(List<Map<String, Object>> paramsList) {
        String cntJpql = "select count(1) from d_archive_individcust t where t.cardno in (" + individCustEao.convertInString(paramsList, "cardNo") + ") or t.mobile in (" + individCustEao.convertInString(paramsList, "mobile") + ")";
        BigDecimal cntRs = (BigDecimal) individCustEao.executeSQLQueryOne(cntJpql);
        return cntRs.longValue();
    }

}