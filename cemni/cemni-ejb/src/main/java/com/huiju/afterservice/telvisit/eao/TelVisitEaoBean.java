package com.huiju.afterservice.telvisit.eao;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.module.data.eao.GenericEaoImpl;

@Stateless(mappedName = "TelVisitEaoBean")
public class TelVisitEaoBean extends GenericEaoImpl<TelVisit, Long> implements TelVisitEaoLocal {
    EntityManager em;

    @Override
    @PersistenceContext(unitName = "showcase")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
        this.em = em;
    }

    @Override
    public long getTelvisitSeq() {
        BigDecimal bigSeq = (BigDecimal) this.executeSQLQueryOne("select seq_afterservice_telvisit.nextval from dual");
        return bigSeq.longValue();
    }

    @Override
    public void saveTelVisit(Long seq, TelVisit telVisit) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into d_afterservice_telvisit                  ");
        jpql.append("  (telvisitid,                                       ");
        jpql.append("   telvisitno,                                       ");
        jpql.append("   backfs,                                           ");
        jpql.append("   tasktype,                                         ");
        jpql.append("   startrq,                                          ");
        jpql.append("   endrq,                                            ");
        jpql.append("   publishzt,                                        ");
        jpql.append("   remark,                                           ");
        jpql.append("   cuser,                                            ");
        jpql.append("   cdate,                                            ");
        jpql.append("   muser,                                            ");
        jpql.append("   mdate)                                            ");
        jpql.append("values                                               ");
        jpql.append("  (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)");

        Query qry = em.createNativeQuery(jpql.toString());
        qry.setParameter(1, seq);
        qry.setParameter(2, telVisit.getTelVisitNo());
        qry.setParameter(3, telVisit.getBackfs());
        qry.setParameter(4, telVisit.getTaskType());
        qry.setParameter(5, telVisit.getStartrq(), TemporalType.DATE);
        qry.setParameter(6, telVisit.getEndrq(), TemporalType.DATE);
        qry.setParameter(7, telVisit.getPublishzt());
        qry.setParameter(8, telVisit.getRemark());
        qry.setParameter(9, telVisit.getCuser());
        qry.setParameter(10, telVisit.getCdate(), TemporalType.DATE);
        qry.setParameter(11, telVisit.getMuser());
        qry.setParameter(12, telVisit.getMdate(), TemporalType.DATE);
        qry.executeUpdate();
    }

    @Override
    public void updateTelVisit(TelVisit telVisit) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("update d_afterservice_telvisit");
        jpql.append("   set telvisitno = ?2,       ");
        jpql.append("       backfs     = ?3,       ");
        jpql.append("       tasktype   = ?4,       ");
        jpql.append("       startrq    = ?5,       ");
        jpql.append("       endrq      = ?6,       ");
        jpql.append("       publishzt  = ?7,       ");
        jpql.append("       remark     = ?8,       ");
        jpql.append("       cuser      = ?9,       ");
        jpql.append("       cdate      = ?10,      ");
        jpql.append("       muser      = ?11,      ");
        jpql.append("       mdate      = ?12       ");
        jpql.append(" where telvisitid = ?1        ");

        Query qry = em.createNativeQuery(jpql.toString());
        qry.setParameter(1, telVisit.getId());
        qry.setParameter(2, telVisit.getTelVisitNo());
        qry.setParameter(3, telVisit.getBackfs());
        qry.setParameter(4, telVisit.getTaskType());
        qry.setParameter(5, telVisit.getStartrq(), TemporalType.DATE);
        qry.setParameter(6, telVisit.getEndrq(), TemporalType.DATE);
        qry.setParameter(7, telVisit.getPublishzt());
        qry.setParameter(8, telVisit.getRemark());
        qry.setParameter(9, telVisit.getCuser());
        qry.setParameter(10, telVisit.getCdate(), TemporalType.DATE);
        qry.setParameter(11, telVisit.getMuser());
        qry.setParameter(12, telVisit.getMdate(), TemporalType.DATE);
        qry.executeUpdate();
    }

}