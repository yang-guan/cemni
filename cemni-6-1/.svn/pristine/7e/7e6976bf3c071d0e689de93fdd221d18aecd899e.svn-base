package com.huiju.afterservice.telvisit.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.archive.individcust.eao.IndividCustEaoLocal;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "TelVisitCustEaoBean")
@SuppressWarnings({ "rawtypes" })
public class TelVisitCustEaoBean extends GenericEaoImpl<TelVisitCust, Long> implements TelVisitCustEaoLocal {
    @EJB
    private IndividCustEaoLocal individCustEao;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public void saveTelVisitCust(Long id, Map searchParams) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into d_afterservice_telvisit_cust                                    ");
        jpql.append("  (telvisitcustid, telvisitid, individcustid)                               ");
        jpql.append("  select seq_afterservice_telvisit_cust.nextval, " + id + ", t.individcustid");
        jpql.append("    from d_archive_individcust t                                            ");
        jpql.append("   where 1 = 1 ").append(individCustEao.getIndividCustWhereStr(searchParams));

        this.executeSQLUpdate(jpql.toString());
    }

    @Override
    public void updateTelVisitCust(Long id, Map searchParams) {
        this.executeSQLUpdate("delete from d_afterservice_telvisit_cust where telvisitid = " + id);
        this.saveTelVisitCust(id, searchParams);
    }

    @Override
    public void saveTelVisitCust3(Long id, List<TelVisitCust> custList) {
        StringBuffer ids = new StringBuffer();
        for (TelVisitCust dt : custList) {
            ids.append(",").append(dt.getIndividCust().getIndividCustId());
        }
        this.executeSQLUpdate("delete from d_afterservice_telvisit_cust where telvisitid = " + id);

        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into d_afterservice_telvisit_cust                                     ");
        jpql.append("  (telvisitcustid, telvisitid, individcustid)                                ");
        jpql.append("  select seq_afterservice_telvisit_cust.nextval, " + id + ", t.individcustid");
        jpql.append("    from d_archive_individcust t                                             ");
        jpql.append("   where t.individcustid in (" + ids.substring(1).toString() + ")");

        this.executeSQLUpdate(jpql.toString());
    }

    @Override
    public void saveIndividCustByExcel(Long id, List<Map<String, Object>> paramsList) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into d_afterservice_telvisit_cust                                     ");
        jpql.append("  (telvisitcustid, telvisitid, individcustid)                                ");
        jpql.append("  select seq_afterservice_telvisit_cust.nextval, " + id + ", t.individcustid");
        jpql.append("    from d_archive_individcust t                                             ");
        jpql.append("   where t.cardno in (" + individCustEao.convertInString(paramsList, "cardNo") + ")");
        jpql.append("      or t.mobile in (" + individCustEao.convertInString(paramsList, "mobile") + ")");

        this.executeSQLUpdate(jpql.toString());
    }

    @Override
    public void updateIndividCustByExcel(Long id, List<Map<String, Object>> paramsList) {
        this.executeSQLUpdate("delete from d_afterservice_telvisit_cust where telvisitid = " + id);
        this.saveIndividCustByExcel(id, paramsList);
    }

}