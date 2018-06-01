package com.huiju.afterservice.telvisit.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_AFTERSERVICE_TELVISIT")
public class TelVisit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_afterservice_telvisit")
    @SequenceGenerator(name = "SEQ_afterservice_telvisit", sequenceName = "SEQ_afterservice_telvisit", allocationSize = 1)
    private Long telVisitId;

    private String telVisitNo;
    private Integer backfs;// 回访方式
    private Integer taskType;// 任务类型
    private Integer publishzt;// 是否已发布：1是、0否
    private String remark;// 回访任务说明

    @Temporal(TemporalType.DATE)
    private Calendar startrq;// 开始日期
    @Temporal(TemporalType.DATE)
    private Calendar endrq;// 结束日期

    private String cuser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String backfsName;
    @Transient
    private String publishztName;
    @Transient
    private String storeName;
    @Transient
    private String backtypeName;
    @Transient
    private String taskTypeName;
    @Transient
    private String queryParamsStr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telVisit", orphanRemoval = true)
    private List<TelVisitCust> telVisitCustList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telVisit", orphanRemoval = true)
    private List<TelVisitRecord> telVisitRecordList;

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getTelVisitId() {
        return telVisitId;
    }

    public void setTelVisitId(Long telVisitId) {
        this.telVisitId = telVisitId;
    }

    public String getTelVisitNo() {
        return telVisitNo;
    }

    public void setTelVisitNo(String telVisitNo) {
        this.telVisitNo = telVisitNo;
    }

    public Integer getBackfs() {
        return backfs;
    }

    public void setBackfs(Integer backfs) {
        this.backfs = backfs;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getPublishzt() {
        return publishzt;
    }

    public void setPublishzt(Integer publishzt) {
        this.publishzt = publishzt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Calendar getStartrq() {
        return startrq;
    }

    public void setStartrq(Calendar startrq) {
        this.startrq = startrq;
    }

    public Calendar getEndrq() {
        return endrq;
    }

    public void setEndrq(Calendar endrq) {
        this.endrq = endrq;
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

    public String getBackfsName() {
        return backfsName;
    }

    public void setBackfsName(String backfsName) {
        this.backfsName = backfsName;
    }

    public String getPublishztName() {
        return publishztName;
    }

    public void setPublishztName(String publishztName) {
        this.publishztName = publishztName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBacktypeName() {
        return backtypeName;
    }

    public void setBacktypeName(String backtypeName) {
        this.backtypeName = backtypeName;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getQueryParamsStr() {
        return queryParamsStr;
    }

    public void setQueryParamsStr(String queryParamsStr) {
        this.queryParamsStr = queryParamsStr;
    }

    public List<TelVisitCust> getTelVisitCustList() {
        return telVisitCustList;
    }

    public void setTelVisitCustList(List<TelVisitCust> telVisitCustList) {
        this.telVisitCustList = telVisitCustList;
    }

    public List<TelVisitRecord> getTelVisitRecordList() {
        return telVisitRecordList;
    }

    public void setTelVisitRecordList(List<TelVisitRecord> telVisitRecordList) {
        this.telVisitRecordList = telVisitRecordList;
    }

}