package com.huiju.afterservice.rightmaint.entity;

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

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.console.org.entity.Org;
import com.huiju.console.store.entity.Store;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.BaseEntity;

/**
 * 客户权益
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月5日 下午5:05:53
 */
@Entity
@Table(name = "D_AFTERSERVICE_RIGHTMAINT")
public class RightMaint extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RightMaint_PK")
    @TableGenerator(name = "RightMaint_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "RightMaint_PK", allocationSize = 1)
    private Long rightMaintId;

    private String complaintNo;// varchar2(50)         权益单号
    private Integer handleState;// number(4)           处理状态

    @Temporal(TemporalType.DATE)
    private Calendar complaintDate;// date             投诉日期      
    @Temporal(TemporalType.DATE)
    private Calendar handleDate;// date                处理日期

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;// number          个人客户

    @ManyToOne
    @JoinColumn(name = "posId", referencedColumnName = "posId")
    private PosOrder posOrder;// number                pos单

    private Integer urgencyLevel;// number(4)          紧急程度
    private Integer complaintType;// number(4)         投诉类型
    private Integer probType;// number(4)              问题类型
    private Integer complaintLevel;// number(4)        投诉等级 

    @ManyToOne
    @JoinColumn(name = "orgId", referencedColumnName = "orgId")
    private Org org;// number                          部门(客服部)
    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    private Store store;// number                      处理门店    

    private String storeNo;// varchar2(20) 门店编码
    private String orgCode;// varchar2(20)    编码

    private Integer reviewState;// number(4)           审核状态
    private String probExplain;// varchar2(200)        问题简要说明
    private String customerAdvice;// varchar2(200)     客户处理建议
    private String handleTypeResult;// varchar2(200)   处理方式及结果
    private String complaintReason;// varchar2(200)    投诉产生原因
    private String complaintProcess;// varchar2(200)   投诉处理过程
    private String complaintStep;// varchar2(200)      投诉处理措施
    private String finalResult;// varchar2(200)        最终处理结果
    private String causeAnalysis;// varchar2(200)      原因分析
    private String improveStrategy;// varchar2(200)    改善策略    

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;
    private String modifyUser;
    private String uploadFileGroupId; // 附件

    @Transient
    private String handleStateName;
    @Transient
    private String urgencyLevelName;
    @Transient
    private String complaintTypeName;
    @Transient
    private String probTypeName;
    @Transient
    private String complaintLevelName;
    @Transient
    private String reviewStateName;
    @Transient
    private String orgStoreName;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getRightMaintId() {
        return rightMaintId;
    }

    public void setRightMaintId(Long rightMaintId) {
        this.rightMaintId = rightMaintId;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    public Calendar getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Calendar complaintDate) {
        this.complaintDate = complaintDate;
    }

    public Calendar getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Calendar handleDate) {
        this.handleDate = handleDate;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

    public PosOrder getPosOrder() {
        return posOrder;
    }

    public void setPosOrder(PosOrder posOrder) {
        this.posOrder = posOrder;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public Integer getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(Integer complaintType) {
        this.complaintType = complaintType;
    }

    public Integer getProbType() {
        return probType;
    }

    public void setProbType(Integer probType) {
        this.probType = probType;
    }

    public Integer getComplaintLevel() {
        return complaintLevel;
    }

    public void setComplaintLevel(Integer complaintLevel) {
        this.complaintLevel = complaintLevel;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getReviewState() {
        return reviewState;
    }

    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }

    public String getProbExplain() {
        return probExplain;
    }

    public void setProbExplain(String probExplain) {
        this.probExplain = probExplain;
    }

    public String getCustomerAdvice() {
        return customerAdvice;
    }

    public void setCustomerAdvice(String customerAdvice) {
        this.customerAdvice = customerAdvice;
    }

    public String getHandleTypeResult() {
        return handleTypeResult;
    }

    public void setHandleTypeResult(String handleTypeResult) {
        this.handleTypeResult = handleTypeResult;
    }

    public String getComplaintReason() {
        return complaintReason;
    }

    public void setComplaintReason(String complaintReason) {
        this.complaintReason = complaintReason;
    }

    public String getComplaintProcess() {
        return complaintProcess;
    }

    public void setComplaintProcess(String complaintProcess) {
        this.complaintProcess = complaintProcess;
    }

    public String getComplaintStep() {
        return complaintStep;
    }

    public void setComplaintStep(String complaintStep) {
        this.complaintStep = complaintStep;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public String getCauseAnalysis() {
        return causeAnalysis;
    }

    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }

    public String getImproveStrategy() {
        return improveStrategy;
    }

    public void setImproveStrategy(String improveStrategy) {
        this.improveStrategy = improveStrategy;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getUploadFileGroupId() {
        return uploadFileGroupId;
    }

    public void setUploadFileGroupId(String uploadFileGroupId) {
        this.uploadFileGroupId = uploadFileGroupId;
    }

    public String getHandleStateName() {
        return handleStateName;
    }

    public void setHandleStateName(String handleStateName) {
        this.handleStateName = handleStateName;
    }

    public String getUrgencyLevelName() {
        return urgencyLevelName;
    }

    public void setUrgencyLevelName(String urgencyLevelName) {
        this.urgencyLevelName = urgencyLevelName;
    }

    public String getComplaintTypeName() {
        return complaintTypeName;
    }

    public void setComplaintTypeName(String complaintTypeName) {
        this.complaintTypeName = complaintTypeName;
    }

    public String getProbTypeName() {
        return probTypeName;
    }

    public void setProbTypeName(String probTypeName) {
        this.probTypeName = probTypeName;
    }

    public String getComplaintLevelName() {
        return complaintLevelName;
    }

    public void setComplaintLevelName(String complaintLevelName) {
        this.complaintLevelName = complaintLevelName;
    }

    public String getReviewStateName() {
        return reviewStateName;
    }

    public void setReviewStateName(String reviewStateName) {
        this.reviewStateName = reviewStateName;
    }

    public String getOrgStoreName() {
        return orgStoreName;
    }

    public void setOrgStoreName(String orgStoreName) {
        this.orgStoreName = orgStoreName;
    }

}