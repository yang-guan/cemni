package com.huiju.actment.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY_JUDGE")
public class JudgeAct extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "JUDGE_PK")
    @TableGenerator(name = "JUDGE_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "JUDGE_PK", allocationSize = 1)
    private Long judgeActId;

    private String actEffect;// 活动效果
    private String totalJudge;// 活动总体评价
    private String improve;// 不足及改进
    private String advantage;// 有点及推广应用
    private Double potentialFra;// 潜在加盟商数量
    private Double signingFra;// 签约加盟商数量
    private String transRate;// 转化率

    @OneToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Long getJudgeActId() {
        return judgeActId;
    }

    public void setJudgeActId(Long judgeActId) {
        this.judgeActId = judgeActId;
    }

    public String getActEffect() {
        return actEffect;
    }

    public void setActEffect(String actEffect) {
        this.actEffect = actEffect;
    }

    public String getTotalJudge() {
        return totalJudge;
    }

    public void setTotalJudge(String totalJudge) {
        this.totalJudge = totalJudge;
    }

    public String getImprove() {
        return improve;
    }

    public void setImprove(String improve) {
        this.improve = improve;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public Double getPotentialFra() {
        return potentialFra;
    }

    public void setPotentialFra(Double potentialFra) {
        this.potentialFra = potentialFra;
    }

    public Double getSigningFra() {
        return signingFra;
    }

    public void setSigningFra(Double signingFra) {
        this.signingFra = signingFra;
    }

    public String getTransRate() {
        return transRate;
    }

    public void setTransRate(String transRate) {
        this.transRate = transRate;
    }

}