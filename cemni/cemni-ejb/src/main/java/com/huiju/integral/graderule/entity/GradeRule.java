package com.huiju.integral.graderule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_INTEGRAL_GRADERULE")
public class GradeRule extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    private Long gradeRuleId;

    private Integer lv;// 会员等级
    private String requirement;// 条件
    private Double jewerlyAmount;// 珠宝折算额
    private String discount;// 折扣权益
    private Integer exchangeChance;// 免费调换次数
    private Integer restructureChance;// 免费改款次数

    @Transient
    private String lvName;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getGradeRuleId() {
        return gradeRuleId;
    }

    public void setGradeRuleId(Long gradeRuleId) {
        this.gradeRuleId = gradeRuleId;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Double getJewerlyAmount() {
        return jewerlyAmount;
    }

    public void setJewerlyAmount(Double jewerlyAmount) {
        this.jewerlyAmount = jewerlyAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getExchangeChance() {
        return exchangeChance;
    }

    public void setExchangeChance(Integer exchangeChance) {
        this.exchangeChance = exchangeChance;
    }

    public Integer getRestructureChance() {
        return restructureChance;
    }

    public void setRestructureChance(Integer restructureChance) {
        this.restructureChance = restructureChance;
    }

    public String getLvName() {
        return lvName;
    }

    public void setLvName(String lvName) {
        this.lvName = lvName;
    }

}