package com.huiju.afterservice.callregist.entity;

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
 * 客户拜访登记
 * 
 * @author：WangYuanJun
 * @date：2016年12月7日 下午4:09:54
 */
@Entity
@Table(name = "D_AFTERSERVICE_CALLREGIST")
public class CallRegist extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CallRegist_PK")
    @TableGenerator(name = "CallRegist_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CallRegist_PK", allocationSize = 1)
    private Long callRegistId;

    private String visitNo;//varchar2(50)          拜访单号

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar visitDate;//Date              拜访日期

    private String visitPerson;//number            拜访人员
    private Integer visitForm;//number             拜访形式
    private Double visitMoney;//number             拜访费用
    private String visitReason;//varchar2(200)     拜访事由
    private String birthPlace; //                  出生地
    private String company;//varchar2(200)         工作单位
    private String job;//varchar2(200)             职位
    private String clothes;//varchar2(200)         喜欢服饰品牌
    private String topic;//varchar2(200)           喜欢的话题
    private String carNum;//varchar2(200)          车子型号
    private String chatContent;//varchar2(200)     主要交谈内容
    private String accessConclusion;//varchar2(200)访问结论
    private String suggestMaintain;//varchar2(200) 建议维护
    private String writePerson;//varchar2(200)     填写人

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar writeDate;//varchar2(200)     填写日期

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;// Date            创建时间
    private String createUser;// number            创建人

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;// Date            修改时间
    private String modifyUser;// number            修改人

    @Transient
    private String visitFormName;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    private Store store;// 受理门店

    @ManyToOne
    @JoinColumn(name = "orgId", referencedColumnName = "orgId")
    private Org org;// 部门（客服部）

    private String storeNo;// varchar2(20) 门店编码

    private String orgCode;// varchar2(20)    编码

    @Transient
    private String orgStoreName;

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getCallRegistId() {
        return callRegistId;
    }

    public void setCallRegistId(Long callRegistId) {
        this.callRegistId = callRegistId;
    }

    public String getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    public Calendar getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Calendar visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitPerson() {
        return visitPerson;
    }

    public void setVisitPerson(String visitPerson) {
        this.visitPerson = visitPerson;
    }

    public Integer getVisitForm() {
        return visitForm;
    }

    public void setVisitForm(Integer visitForm) {
        this.visitForm = visitForm;
    }

    public Double getVisitMoney() {
        return visitMoney;
    }

    public void setVisitMoney(Double visitMoney) {
        this.visitMoney = visitMoney;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getAccessConclusion() {
        return accessConclusion;
    }

    public void setAccessConclusion(String accessConclusion) {
        this.accessConclusion = accessConclusion;
    }

    public String getSuggestMaintain() {
        return suggestMaintain;
    }

    public void setSuggestMaintain(String suggestMaintain) {
        this.suggestMaintain = suggestMaintain;
    }

    public String getWritePerson() {
        return writePerson;
    }

    public void setWritePerson(String writePerson) {
        this.writePerson = writePerson;
    }

    public Calendar getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Calendar writeDate) {
        this.writeDate = writeDate;
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

    public String getVisitFormName() {
        return visitFormName;
    }

    public void setVisitFormName(String visitFormName) {
        this.visitFormName = visitFormName;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
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

    public String getOrgStoreName() {
        return orgStoreName;
    }

    public void setOrgStoreName(String orgStoreName) {
        this.orgStoreName = orgStoreName;
    }

}