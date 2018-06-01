package com.huiju.afterservice.telvisit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_AFTERSERVICE_TELVISIT_CUST")
public class TelVisitCust extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_afterservice_telvisit_cust")
    @SequenceGenerator(name = "SEQ_afterservice_telvisit_cust", sequenceName = "SEQ_afterservice_telvisit_cust", allocationSize = 1)
    private Long telVisitCustId;

    @ManyToOne
    @JoinColumn(name = "telVisitId", referencedColumnName = "telVisitId")
    private TelVisit telVisit;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    public Long getTelVisitCustId() {
        return telVisitCustId;
    }

    public void setTelVisitCustId(Long telVisitCustId) {
        this.telVisitCustId = telVisitCustId;
    }

    public TelVisit getTelVisit() {
        return telVisit;
    }

    public void setTelVisit(TelVisit telVisit) {
        this.telVisit = telVisit;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}