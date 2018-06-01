package com.huiju.archive.individcust.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

/**
 * 操作日志
 * 
 * @author：yuhb
 * @date：2017年3月23日 上午1:23:37
 */
@Entity
@Table(name = "D_ARCHIVE_OPERATIONLOG")
public class OperationLog extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_archive_operationlog")
    @SequenceGenerator(name = "SEQ_archive_operationlog", sequenceName = "SEQ_archive_operationlog", allocationSize = 1)
    private Long operationLogId;

    private Long individCustId;
    private String remark;
    private String result;// 核定结果
    private Integer type;// 类型：1核定记录、2操作记录

    private String cuser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;

    public Long getOperationLogId() {
        return operationLogId;
    }

    public void setOperationLogId(Long operationLogId) {
        this.operationLogId = operationLogId;
    }

    public Long getIndividCustId() {
        return individCustId;
    }

    public void setIndividCustId(Long individCustId) {
        this.individCustId = individCustId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

}