package com.huiju.integral.integraladj.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_INTEGRAL_INTEGRALADJ_HIS")
public class IntegralAdjHis extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IntegralAdjHis")
    @SequenceGenerator(name = "SEQ_IntegralAdjHis", sequenceName = "SEQ_IntegralAdjHis", allocationSize = 1)
    private Long integralAdjHisId;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    @ManyToOne
    @JoinColumn(name = "groupCustId", referencedColumnName = "groupCustId")
    private GroupCust groupCust;

    private Integer custType;// 客户类型：1个人、2团体（字典表3105）
    private Double creditBefore;// 变更前可用积分
    private Double creditAfter;// 变更后可用积分
    private Double convertedCredits;// 已兑换积分
    private Integer creditStatus;// 积分状态：字典表3101（冻结还是解冻）
    private String muser;// 变更人（手动时有）

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;// 积分变更时间

    private String modReason;// 积分变更原因
    private Integer modType;// 变更类型：字典表3103
    private Integer creditOrigin;// 积分来源：字典表9100

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Transient
    private String creditOriginName;
    @Transient
    private String creditStatusName;
    @Transient
    private String modTypeName;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getIntegralAdjHisId() {
        return integralAdjHisId;
    }

    public void setIntegralAdjHisId(Long integralAdjHisId) {
        this.integralAdjHisId = integralAdjHisId;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

    public GroupCust getGroupCust() {
        return groupCust;
    }

    public void setGroupCust(GroupCust groupCust) {
        this.groupCust = groupCust;
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public Double getCreditBefore() {
        return creditBefore;
    }

    public void setCreditBefore(Double creditBefore) {
        this.creditBefore = creditBefore;
    }

    public Double getCreditAfter() {
        return creditAfter;
    }

    public void setCreditAfter(Double creditAfter) {
        this.creditAfter = creditAfter;
    }

    public Double getConvertedCredits() {
        return convertedCredits;
    }

    public void setConvertedCredits(Double convertedCredits) {
        this.convertedCredits = convertedCredits;
    }

    public Integer getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Integer creditStatus) {
        this.creditStatus = creditStatus;
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

    public String getModReason() {
        return modReason;
    }

    public void setModReason(String modReason) {
        this.modReason = modReason;
    }

    public Integer getModType() {
        return modType;
    }

    public void setModType(Integer modType) {
        this.modType = modType;
    }

    public Integer getCreditOrigin() {
        return creditOrigin;
    }

    public void setCreditOrigin(Integer creditOrigin) {
        this.creditOrigin = creditOrigin;
    }

    public String getCreditOriginName() {
        return creditOriginName;
    }

    public void setCreditOriginName(String creditOriginName) {
        this.creditOriginName = creditOriginName;
    }

    public String getCreditStatusName() {
        return creditStatusName;
    }

    public void setCreditStatusName(String creditStatusName) {
        this.creditStatusName = creditStatusName;
    }

    public String getModTypeName() {
        return modTypeName;
    }

    public void setModTypeName(String modTypeName) {
        this.modTypeName = modTypeName;
    }

}