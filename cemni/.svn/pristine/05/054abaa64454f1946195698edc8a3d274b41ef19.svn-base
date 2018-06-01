package com.huiju.archive.individcust.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ARCHIVE_ACTIVESTATUS")
public class ActiveStatus extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ActiveStatus_PK")
    @TableGenerator(name = "ActiveStatus_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ActiveStatus_PK", allocationSize = 1)
    private Long activeStatusId;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    private Integer beforeStatus;// 变更前状态
    private Integer afterStatus;// 变更后状态

    private String reason;// 变更原因
    private String muser;// 变更人

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;// 变更时间

    @Transient
    private String beforeStatusName;
    @Transient
    private String afterStatusName;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getActiveStatusId() {
        return activeStatusId;
    }

    public void setActiveStatusId(Long activeStatusId) {
        this.activeStatusId = activeStatusId;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

    public Integer getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(Integer beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public Integer getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Integer afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public String getBeforeStatusName() {
        return beforeStatusName;
    }

    public void setBeforeStatusName(String beforeStatusName) {
        this.beforeStatusName = beforeStatusName;
    }

    public String getAfterStatusName() {
        return afterStatusName;
    }

    public void setAfterStatusName(String afterStatusName) {
        this.afterStatusName = afterStatusName;
    }

}