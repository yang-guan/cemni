package com.huiju.actment.activity.entity;

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
@Table(name = "D_ACTIVITY_INDIPARTIN")
public class IndiPartIn extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_activity_indipartin")
    @SequenceGenerator(name = "SEQ_activity_indipartin", sequenceName = "SEQ_activity_indipartin", allocationSize = 1)
    private Long indiPartInId;

    private String couponNo;// 卡券编号
    private String actUser;// 使用人
    private Integer isPartIn;// 是否参与
    private Integer isUsed;// 是否使用

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getIndiPartInId() {
        return indiPartInId;
    }

    public void setIndiPartInId(Long indiPartInId) {
        this.indiPartInId = indiPartInId;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getActUser() {
        return actUser;
    }

    public void setActUser(String actUser) {
        this.actUser = actUser;
    }

    public Integer getIsPartIn() {
        return isPartIn;
    }

    public void setIsPartIn(Integer isPartIn) {
        this.isPartIn = isPartIn;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}