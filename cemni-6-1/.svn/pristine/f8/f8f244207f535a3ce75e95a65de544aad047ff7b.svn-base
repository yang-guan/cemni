package com.huiju.archive.franchisee.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.actment.activity.entity.FraPartIn;
import com.huiju.console.store.entity.Store;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_FRANCHISEE")
public class Franchisee extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Franchisee_PK")
    @TableGenerator(name = "Franchisee_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Franchisee_PK", allocationSize = 1)
    private Long franchiseeId;

    private String fraCode; // 加盟商编码
    private String fraName; // 加盟商名称
    private String shortName; // 加盟商简称
    private Integer fraType; // 加盟商类型
    private Integer fraStatus; // 加盟商状态
    private String actualCon; // 实际控制人
    private Double fpickCost; // 新店首批拿货额
    private Double fpickMan; // 当月新店首批拿货
    private String vatNo; // 增值税号登记
    private String busNo; // 营业执照编号
    private String legalre; // 法人代表
    private String city; // 城市
    private String comAddr; // 公司地址
    private String busMan; // 联系人
    private String mobile; // 电话
    private String mail; // 邮箱
    private String conAddr; // 联系人地址
    private Integer sources; // 来源
    private String intMan; // 引入人
    private Integer isValid;// 是否有效
    private String creditCode;// 统一社会信用代码
    private String orgNo;// 组织机构代码

    private String createUser;
    private String modifyUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;

    @Transient
    private String fraTypeName;
    @Transient
    private String fraStatusName;
    @Transient
    private String sourcesName;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchisee", orphanRemoval = true)
    private List<Contact> contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchisee", orphanRemoval = true)
    private List<Credit> credit;

    @OneToMany(mappedBy = "franchisee")
    private List<Store> storeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchisee", orphanRemoval = true)
    private List<Team> team;

    @OneToMany(mappedBy = "franchisee")
    private List<FraPartIn> fraPartIn;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getFranchiseeId() {
        return franchiseeId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public void setFranchiseeId(Long franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getFraCode() {
        return fraCode;
    }

    public void setFraCode(String fraCode) {
        this.fraCode = fraCode;
    }

    public String getFraName() {
        return fraName;
    }

    public void setFraName(String fraName) {
        this.fraName = fraName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getFraType() {
        return fraType;
    }

    public void setFraType(Integer fraType) {
        this.fraType = fraType;
    }

    public Integer getFraStatus() {
        return fraStatus;
    }

    public void setFraStatus(Integer fraStatus) {
        this.fraStatus = fraStatus;
    }

    public String getActualCon() {
        return actualCon;
    }

    public void setActualCon(String actualCon) {
        this.actualCon = actualCon;
    }

    public Double getFpickCost() {
        return fpickCost;
    }

    public void setFpickCost(Double fpickCost) {
        this.fpickCost = fpickCost;
    }

    public Double getFpickMan() {
        return fpickMan;
    }

    public void setFpickMan(Double fpickMan) {
        this.fpickMan = fpickMan;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getLegalre() {
        return legalre;
    }

    public void setLegalre(String legalre) {
        this.legalre = legalre;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComAddr() {
        return comAddr;
    }

    public void setComAddr(String comAddr) {
        this.comAddr = comAddr;
    }

    public String getBusMan() {
        return busMan;
    }

    public void setBusMan(String busMan) {
        this.busMan = busMan;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getConAddr() {
        return conAddr;
    }

    public void setConAddr(String conAddr) {
        this.conAddr = conAddr;
    }

    public Integer getSources() {
        return sources;
    }

    public void setSources(Integer sources) {
        this.sources = sources;
    }

    public String getIntMan() {
        return intMan;
    }

    public void setIntMan(String intMan) {
        this.intMan = intMan;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getFraTypeName() {
        return fraTypeName;
    }

    public void setFraTypeName(String fraTypeName) {
        this.fraTypeName = fraTypeName;
    }

    public String getFraStatusName() {
        return fraStatusName;
    }

    public void setFraStatusName(String fraStatusName) {
        this.fraStatusName = fraStatusName;
    }

    public String getSourcesName() {
        return sourcesName;
    }

    public void setSourcesName(String sourcesName) {
        this.sourcesName = sourcesName;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public List<Credit> getCredit() {
        return credit;
    }

    public void setCredit(List<Credit> credit) {
        this.credit = credit;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public List<FraPartIn> getFraPartIn() {
        return fraPartIn;
    }

    public void setFraPartIn(List<FraPartIn> fraPartIn) {
        this.fraPartIn = fraPartIn;
    }

}