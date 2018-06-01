package com.huiju.inter.activity.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

/**
 * 审核情况
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年1月4日 下午5:42:23
 */
@Entity
@Table(name = "D_ACTIVITY_AUDIT")
public class ActivityAudit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ActivityAudit_PK")
    @TableGenerator(name = "ActivityAudit_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ActivityAudit_PK", allocationSize = 1)
    private Long activityAuditId;

    private String activityNo;//活动单号

    private String auditor;//审核人

    private Integer result;//审核结果

    private String suggest;//审核意见

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar auditTime;//审核时间

    private Double amount; //审核费用总金额

    private Integer status;//审核状态

    @Transient
    private String resultName;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public Long getActivityAuditId() {
        return activityAuditId;
    }

    public void setActivityAuditId(Long activityAuditId) {
        this.activityAuditId = activityAuditId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Calendar getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Calendar auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

}
