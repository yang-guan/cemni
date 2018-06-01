package com.huiju.expandbusi.franchiseeaudit.entity;

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

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.console.store.entity.Store;
import com.huiju.module.data.BaseEntity;

/**
 * 加盟商稽核管理
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月12日 下午2:47:13
 */
@Entity
@Table(name = "D_EXPANDBUSI_FRANCHISEEAUDIT")
public class FranchiseeAudit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FranchiseeAudit_PK")
    @TableGenerator(name = "FranchiseeAudit_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "FranchiseeAudit_PK", allocationSize = 1)
    private Long franchiseeAuditId;//NUMBER       主键        

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;//number 
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar auditDate;//DATE         稽核时间    

    private String auditPerson;//VARCHAR2(10) 稽核人员    

    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    private Store store;//NUMBER       稽核门店   

    private Integer auditProbNum;//NUMBER(4)    稽核问题数量

    private String auditContent;//VARCHAR2(200)稽核内容    

    private String remark;//VARCHAR2(200)备注        

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;// DATE             创建时间      

    private String createUser;// NUMBER           创建人        

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;// DATE             修改时间      

    private String modifyUser;// NUMBER           修改人  

    private String uploadFileGroupId; // 附件
    
    private String auditPart; //稽核部分
    
    @Transient
    private String auditProbNumName;
    
    public String getAuditProbNumName() {
        return auditProbNumName;
    }

    public void setAuditProbNumName(String auditProbNumName) {
        this.auditProbNumName = auditProbNumName;
    }

    public Long getFranchiseeAuditId() {
        return franchiseeAuditId;
    }

    public void setFranchiseeAuditId(Long franchiseeAuditId) {
        this.franchiseeAuditId = franchiseeAuditId;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

    public Calendar getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Calendar auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getAuditProbNum() {
        return auditProbNum;
    }

    public void setAuditProbNum(Integer auditProbNum) {
        this.auditProbNum = auditProbNum;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAuditPart() {
        return auditPart;
    }

    public void setAuditPart(String auditPart) {
        this.auditPart = auditPart;
    }
}