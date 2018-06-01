package com.huiju.archive.groupcust.entity;

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

import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ARCHIVE_GROUPCUST")
public class GroupCust extends BaseEntity<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUPCUST_PK")
    @TableGenerator(name = "GROUPCUST_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "GROUPCUST_PK", allocationSize = 1)
    private Long groupCustId;

    private String ncNo; // NC卡号
    private String cardNo;// 会员卡号
    private String groupName;// 单位名称
    private Integer type;// 客户类型
    private Integer category;// 行业类别
    private Integer nature;// 单位性质
    private String remark;// 备注
    private String vatNo;// 增值税号登记
    private String businessLicense;// 营业执照
    private String artificialPerson;// 法人
    private String city;// 城市
    private String dmName;// 决策人姓名
    private Long dmMobile;// 决策人手机
    private String dmTitle;// 决策人职务
    private String dmHobby;// 决策人喜好
    private String address;// 通讯地址
    private String businessRequirement;// 业务需求描述
    private String note;// 备注
    private Integer lv; // 会员等级
    private Double credit; // 当前可用积分
    private Double convertedCredits;// 已兑换积分
    private Integer creditStatus;// 积分状态
    private Integer enable; // 使用状态
    private Integer active; // 活跃状态
    private Double jewerlyAmount;// 珠宝折算额

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime; // 创建时间

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // 首次消费
    private String fristStoreNo;
    private String fristStoreName;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fristBuyTime;

    // 末次消费
    private String lastStoreNo;
    private String lastStoreName;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastBuyTime;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @OneToMany(mappedBy = "groupCust")
    private List<GradeAdjHis> gradeAdjHisList;// 等级变更历史

    @OneToMany(mappedBy = "groupCust")
    private List<IntegralAdjHis> integralAdjHisList;// 积分变更历史

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCust", orphanRemoval = true)
    private List<Product> product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCust", orphanRemoval = true)
    private List<CompetitorProduct> competitorProduct;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String typeName;
    @Transient
    private String categoryName;
    @Transient
    private String natureName;
    @Transient
    private String lvName;
    @Transient
    private String enableName;
    @Transient
    private String creditStatusName;
    @Transient
    private String activeName;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getGroupCustId() {
        return groupCustId;
    }

    public void setGroupCustId(Long groupCustId) {
        this.groupCustId = groupCustId;
    }

    public String getNcNo() {
        return ncNo;
    }

    public void setNcNo(String ncNo) {
        this.ncNo = ncNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getArtificialPerson() {
        return artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public Long getDmMobile() {
        return dmMobile;
    }

    public void setDmMobile(Long dmMobile) {
        this.dmMobile = dmMobile;
    }

    public String getDmTitle() {
        return dmTitle;
    }

    public void setDmTitle(String dmTitle) {
        this.dmTitle = dmTitle;
    }

    public String getDmHobby() {
        return dmHobby;
    }

    public void setDmHobby(String dmHobby) {
        this.dmHobby = dmHobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessRequirement() {
        return businessRequirement;
    }

    public void setBusinessRequirement(String businessRequirement) {
        this.businessRequirement = businessRequirement;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getConvertedCredits() {
        return convertedCredits;
    }

    public void setConvertedCredits(Double convertedCredits) {
        this.convertedCredits = convertedCredits;
    }

    public Integer getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Integer creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Double getJewerlyAmount() {
        return jewerlyAmount;
    }

    public void setJewerlyAmount(Double jewerlyAmount) {
        this.jewerlyAmount = jewerlyAmount;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getFristStoreNo() {
        return fristStoreNo;
    }

    public void setFristStoreNo(String fristStoreNo) {
        this.fristStoreNo = fristStoreNo;
    }

    public String getFristStoreName() {
        return fristStoreName;
    }

    public void setFristStoreName(String fristStoreName) {
        this.fristStoreName = fristStoreName;
    }

    public Calendar getFristBuyTime() {
        return fristBuyTime;
    }

    public void setFristBuyTime(Calendar fristBuyTime) {
        this.fristBuyTime = fristBuyTime;
    }

    public String getLastStoreNo() {
        return lastStoreNo;
    }

    public void setLastStoreNo(String lastStoreNo) {
        this.lastStoreNo = lastStoreNo;
    }

    public String getLastStoreName() {
        return lastStoreName;
    }

    public void setLastStoreName(String lastStoreName) {
        this.lastStoreName = lastStoreName;
    }

    public Calendar getLastBuyTime() {
        return lastBuyTime;
    }

    public void setLastBuyTime(Calendar lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    public List<GradeAdjHis> getGradeAdjHisList() {
        return gradeAdjHisList;
    }

    public void setGradeAdjHisList(List<GradeAdjHis> gradeAdjHisList) {
        this.gradeAdjHisList = gradeAdjHisList;
    }

    public List<IntegralAdjHis> getIntegralAdjHisList() {
        return integralAdjHisList;
    }

    public void setIntegralAdjHisList(List<IntegralAdjHis> integralAdjHisList) {
        this.integralAdjHisList = integralAdjHisList;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<CompetitorProduct> getCompetitorProduct() {
        return competitorProduct;
    }

    public void setCompetitorProduct(List<CompetitorProduct> competitorProduct) {
        this.competitorProduct = competitorProduct;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getLvName() {
        return lvName;
    }

    public void setLvName(String lvName) {
        this.lvName = lvName;
    }

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

    public String getCreditStatusName() {
        return creditStatusName;
    }

    public void setCreditStatusName(String creditStatusName) {
        this.creditStatusName = creditStatusName;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

}