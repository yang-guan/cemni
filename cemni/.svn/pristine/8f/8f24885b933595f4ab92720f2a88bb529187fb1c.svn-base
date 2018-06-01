package com.huiju.inter.rightmaint.entity;

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
@Table(name = "D_AFTERSERVICE_RIGHTMAINTAUDIT")
public class RightMaintAudit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RightMaintAudit_PK")
    @TableGenerator(name = "RightMaintAudit_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "RightMaintAudit_PK", allocationSize = 1)
    private Long rightMaintAuditId;

    private String complaintNo;// 权益单号
    private String auditor;// 审核人
    private Integer result;// 审核结果
    private String suggest;// 审核意见
    private Integer status;// 审核状态

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar auditTime;// 审核时间

    @Transient
    private String resultName; // 审核结果

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public Long getRightMaintAuditId() {
        return rightMaintAuditId;
    }

    public void setRightMaintAuditId(Long rightMaintAuditId) {
        this.rightMaintAuditId = rightMaintAuditId;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Calendar getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Calendar auditTime) {
        this.auditTime = auditTime;
    }

}