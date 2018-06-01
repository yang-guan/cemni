package com.huiju.afterservice.busiregist.entity;

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
import com.huiju.module.data.BaseEntity;

/**
 * 客户业务登记
 * 
 * @author：WangYuanJun
 * @date：2016年12月7日 下午3:16:11
 */
@Entity
@Table(name = "D_AFTERSERVICE_BUSIREGIST")
public class BusiRegist extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BUSIREGIST_PK")
    @TableGenerator(name = "BUSIREGIST_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "BUSIREGIST_PK", allocationSize = 1)
    private Long busiRegistId;

    private String registerNo;// 登记单号

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registerDate;// 登记日期

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;//个人客户

    private String custname;// 客户名称
    private Long custMobile;// 客户手机号码

    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    private Store store;// 处理门店

    @ManyToOne
    @JoinColumn(name = "orgId", referencedColumnName = "orgId")
    private Org org;// 处理部门（客服部）

    private String storeNo;// varchar2(20) 处理门店编码

    private String orgCode;// varchar2(20) 处理部门编码

    @ManyToOne
    @JoinColumn(name = "acceptStoreId", referencedColumnName = "storeId")
    private Store acceptStore;// 待受理门店

    @ManyToOne
    @JoinColumn(name = "acceptOrgId", referencedColumnName = "orgId")
    private Org acceptOrg;// 待受理部门（客服部）

    private String acceptStoreNo;// varchar2(20) 待受理门店编码

    private String acceptOrgCode;// varchar2(20) 待受理部门编码

    private String acceptPerson;// 受理人员
    private Integer acceptState;// 受理状态
    private Integer businessType;// 业务类型

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar handleDate;// 处理日期

    private String businessContent;// 业务内容
    private String handleResult;// 处理结果

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;
    private String modifyUser;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String acceptStateName;
    @Transient
    private String businessTypeName;
    @Transient
    private String orgStoreName;// 处理门店/部门名称
    @Transient
    private String acceptOrgStoreName; // 待受理门店/部门名称

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getBusiRegistId() {
        return busiRegistId;
    }

    public void setBusiRegistId(Long busiRegistId) {
        this.busiRegistId = busiRegistId;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public Long getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(Long custMobile) {
        this.custMobile = custMobile;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public Integer getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(Integer acceptState) {
        this.acceptState = acceptState;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Calendar getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Calendar handleDate) {
        this.handleDate = handleDate;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
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

    public String getAcceptStateName() {
        return acceptStateName;
    }

    public void setAcceptStateName(String acceptStateName) {
        this.acceptStateName = acceptStateName;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getOrgStoreName() {
        return orgStoreName;
    }

    public void setOrgStoreName(String orgStoreName) {
        this.orgStoreName = orgStoreName;
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

    public Store getAcceptStore() {
        return acceptStore;
    }

    public void setAcceptStore(Store acceptStore) {
        this.acceptStore = acceptStore;
    }

    public Org getAcceptOrg() {
        return acceptOrg;
    }

    public void setAcceptOrg(Org acceptOrg) {
        this.acceptOrg = acceptOrg;
    }

    public String getAcceptStoreNo() {
        return acceptStoreNo;
    }

    public void setAcceptStoreNo(String acceptStoreNo) {
        this.acceptStoreNo = acceptStoreNo;
    }

    public String getAcceptOrgCode() {
        return acceptOrgCode;
    }

    public void setAcceptOrgCode(String acceptOrgCode) {
        this.acceptOrgCode = acceptOrgCode;
    }

    public String getAcceptOrgStoreName() {
        return acceptOrgStoreName;
    }

    public void setAcceptOrgStoreName(String acceptOrgStoreName) {
        this.acceptOrgStoreName = acceptOrgStoreName;
    }

}