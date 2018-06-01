package com.huiju.integral.gradeadj.entity;

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
@Table(name = "D_INTEGRAL_GRADEADJ_HIS")
public class GradeAdjHis extends BaseEntity<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GradeaAdjHis")
    @SequenceGenerator(name = "SEQ_GradeaAdjHis", sequenceName = "SEQ_GradeaAdjHis", allocationSize = 1)
    private Integer gradeAdjHisId;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    @ManyToOne
    @JoinColumn(name = "groupCustId", referencedColumnName = "groupCustId")
    private GroupCust groupCust;

    private Integer custType;// 客户类型：1个人、2团体（字典表3105）
    private Integer lvBefore;// 变更前等级
    private Integer lvAfter;// 变更后等级
    private Double jewerlyAmount;// 珠宝折算额
    private String muser;// 变更人（手动时有）

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;// 等级变更时间

    private String modReason;// 等级变更原因
    private Integer modType;// 变更类型：字典表3102

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String lvBeforeName;
    @Transient
    private String lvAfterName;
    @Transient
    private String modTypeName;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Integer getGradeAdjHisId() {
        return gradeAdjHisId;
    }

    public void setGradeAdjHisId(Integer gradeAdjHisId) {
        this.gradeAdjHisId = gradeAdjHisId;
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

    public Integer getLvBefore() {
        return lvBefore;
    }

    public void setLvBefore(Integer lvBefore) {
        this.lvBefore = lvBefore;
    }

    public Integer getLvAfter() {
        return lvAfter;
    }

    public void setLvAfter(Integer lvAfter) {
        this.lvAfter = lvAfter;
    }

    public Double getJewerlyAmount() {
        return jewerlyAmount;
    }

    public void setJewerlyAmount(Double jewerlyAmount) {
        this.jewerlyAmount = jewerlyAmount;
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

    public String getLvBeforeName() {
        return lvBeforeName;
    }

    public void setLvBeforeName(String lvBeforeName) {
        this.lvBeforeName = lvBeforeName;
    }

    public String getLvAfterName() {
        return lvAfterName;
    }

    public void setLvAfterName(String lvAfterName) {
        this.lvAfterName = lvAfterName;
    }

    public String getModTypeName() {
        return modTypeName;
    }

    public void setModTypeName(String modTypeName) {
        this.modTypeName = modTypeName;
    }

}