package com.huiju.archive.individcust.entity;

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

import com.huiju.actment.activity.entity.IndiPartIn;
import com.huiju.afterservice.busiregist.entity.BusiRegist;
import com.huiju.afterservice.callregist.entity.CallRegist;
import com.huiju.afterservice.rightmaint.entity.RightMaint;
import com.huiju.afterservice.telvisit.entity.TelVisitCust;
import com.huiju.afterservice.telvisitrecord.entity.TelVisitRecord;
import com.huiju.integral.gradeadj.entity.GradeAdjHis;
import com.huiju.integral.integraladj.entity.IntegralAdjHis;
import com.huiju.inter.afterserv.entity.AfterServ;
import com.huiju.inter.posorder.entity.PosOrder;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ARCHIVE_INDIVIDCUST")
public class IndividCust extends BaseEntity<Integer> implements Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INDIVIDCUST_PK")
    @TableGenerator(name = "INDIVIDCUST_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INDIVIDCUST_PK", allocationSize = 1)
    private Long individCustId;

    private String ncNo;// nc卡号
    private String ncNoHis; // NC历史卡号
    private String ncNoInvalid; // 已失效的NC卡号
    private String cardNo; // 会员卡号
    private Integer type; // 客户类型
    private String name; // 客户姓名
    private Integer gender; // 性别
    private String idcard; // 身份证号

    @Temporal(TemporalType.DATE)
    private Calendar birthday;// 出生日期
    private String birthMonthday;// 出生月日(mm-dd)
    @Temporal(TemporalType.DATE)
    private Calendar weddingDay;// 结婚纪念日

    private Long groupCustId; // 所属团体
    private Long mobile; // 手机号码
    private String phone; // 固定号码
    private Integer country; // 国家
    private Integer province; // 省
    private Integer city; // 市
    private Integer county; // 区/县
    private String town; // 乡镇
    private String address; // 地址
    private String shippingAddr; // 收货地址
    private Integer job; // 职业
    private Integer income; // 收入
    private Integer brandChannel; // 品牌获知渠道
    private Integer motives; // 购买目的
    private Integer purchaseFactors; // 购买因素(喜欢千年的理由)
    private Integer belief; // 信仰
    private String email; // 邮箱
    private String wechat; // 微信
    private String wechatId; // 微信ID
    private String club; // 所属私人俱乐部
    private String subject; // 对什么主题有兴趣
    private String cosmeticsBrand; // 喜欢的化妆品品牌
    private String likeFood; // 喜欢的菜式
    private String consumerPlace; // 喜欢的消费场所
    private String hobby; // 嗜好与娱乐
    private String likeBook; // 喜欢读什么书
    private String resort; // 喜欢的度假方式
    private String likeSport; // 喜欢观赏的运动
    private String remark; // 备注

    private Integer purposePrice; // 意向价位
    private Integer purposeProduct; // 意向产品类型
    private Integer purposeCategory; // 意向产品品类
    @Temporal(TemporalType.DATE)
    private Calendar purposeDay;// 需求时间

    // 客户来源
    private Integer sources; // 来源渠道
    private Long planId; // 活动方案
    private Long unionActivitiesId; // 异业联盟活动
    private String referrer; // 推荐人
    private Integer relationship; // 与推荐人的关系
    private String srcStoreNo;// 来源门店
    private String srcStoreName;
    private String otherSources; // 其他来源

    private Integer lv; // 会员等级
    private Double credit; // 当前可用积分
    private Double convertedCredits;// 已兑换积分
    private CustStatus status; // 审核状态
    private Integer enable; // 使用状态
    private Integer active; // 活跃状态

    private Integer age;// 年龄段
    private Double jewerlyAmount;// 累计珠宝折算额
    private Integer creditStatus;// 积分状态

    private Integer fresh;// 新老用户：0准、1新、2老
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar freshChgTime;

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
    // 所属门店
    private String belongStoreNo;
    private String belongStoreName;

    private Integer isSendSms; // 是否发送短信
    private Integer isImport;// 是否是导入的会员
    private String imgPath;
    private byte[] image;// 头像

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;//创建时间
    private String cuser;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String pwd; // 耀我/微信-接口传递的密码
    @Transient
    private String fullAddress; // 省市县 + 地址
    @Transient
    private String jobName; // 职业
    @Transient
    private String creditStatusName;// 积分状态
    @Transient
    private String typeName;// 客户类型
    @Transient
    private String genderName;// 性别
    @Transient
    private String purposePriceName; // 意向价位
    @Transient
    private String purposeProductName; // 意向产品类型
    @Transient
    private String purposeCategoryName; // 意向产品品类
    @Transient
    private String lvName;// 会员等级
    @Transient
    private String activeName;// 活跃状态
    @Transient
    private String enableName;// 使用状态
    @Transient
    private String freshName;// 新老会员
    @Transient
    private String storeName;// 门店名称

    @Transient
    private String groupCustName;
    @Transient
    private String planName;
    @Transient
    private String unionActivitiesName;
    @Transient
    private String belongAttrName;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individCust", orphanRemoval = true)
    private List<IndividCustFamily> individCustFamily;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individCust", orphanRemoval = true)
    private List<Anniversary> anniversary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individCust", orphanRemoval = true)
    private List<SalesRecord> salesRecord;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individCust", orphanRemoval = true)
    private List<ActiveStatus> activeStatus;

    @OneToMany(mappedBy = "individCust")
    private List<GradeAdjHis> gradeAdjHisList;// 等级变更历史

    @OneToMany(mappedBy = "individCust")
    private List<IntegralAdjHis> integralAdjHisList;// 积分变更历史

    @OneToMany(mappedBy = "individCust")
    private List<PosOrder> posOrderList;// pos单记录

    @OneToMany(mappedBy = "individCust")
    private List<RightMaint> rightMaint;
    @OneToMany(mappedBy = "individCust")
    private List<BusiRegist> busiRegist;
    @OneToMany(mappedBy = "individCust")
    private List<CallRegist> callRegist;// 客户拜访

    @OneToMany(mappedBy = "individCust")
    private List<TelVisitCust> telVisitCustList;// 回访任务单
    @OneToMany(mappedBy = "individCust")
    private List<TelVisitRecord> recordList;// 电话回访单

    @OneToMany(mappedBy = "individCust")
    private List<IndiPartIn> indiPartIn;
    @OneToMany(mappedBy = "individCust")
    private List<AfterServ> afterserv;

    @Override
    public IndividCust clone() {
        IndividCust dt = null;
        try {
            dt = (IndividCust) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dt;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getIndividCustId() {
        return individCustId;
    }

    public void setIndividCustId(Long individCustId) {
        this.individCustId = individCustId;
    }

    public String getNcNo() {
        return ncNo;
    }

    public void setNcNo(String ncNo) {
        this.ncNo = ncNo;
    }

    public String getNcNoHis() {
        return ncNoHis;
    }

    public void setNcNoHis(String ncNoHis) {
        this.ncNoHis = ncNoHis;
    }

    public String getNcNoInvalid() {
        return ncNoInvalid;
    }

    public void setNcNoInvalid(String ncNoInvalid) {
        this.ncNoInvalid = ncNoInvalid;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getBirthMonthday() {
        return birthMonthday;
    }

    public void setBirthMonthday(String birthMonthday) {
        this.birthMonthday = birthMonthday;
    }

    public Calendar getWeddingDay() {
        return weddingDay;
    }

    public void setWeddingDay(Calendar weddingDay) {
        this.weddingDay = weddingDay;
    }

    public Long getGroupCustId() {
        return groupCustId;
    }

    public void setGroupCustId(Long groupCustId) {
        this.groupCustId = groupCustId;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShippingAddr() {
        return shippingAddr;
    }

    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getBrandChannel() {
        return brandChannel;
    }

    public void setBrandChannel(Integer brandChannel) {
        this.brandChannel = brandChannel;
    }

    public Integer getMotives() {
        return motives;
    }

    public void setMotives(Integer motives) {
        this.motives = motives;
    }

    public Integer getPurchaseFactors() {
        return purchaseFactors;
    }

    public void setPurchaseFactors(Integer purchaseFactors) {
        this.purchaseFactors = purchaseFactors;
    }

    public Integer getBelief() {
        return belief;
    }

    public void setBelief(Integer belief) {
        this.belief = belief;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCosmeticsBrand() {
        return cosmeticsBrand;
    }

    public void setCosmeticsBrand(String cosmeticsBrand) {
        this.cosmeticsBrand = cosmeticsBrand;
    }

    public String getLikeFood() {
        return likeFood;
    }

    public void setLikeFood(String likeFood) {
        this.likeFood = likeFood;
    }

    public String getConsumerPlace() {
        return consumerPlace;
    }

    public void setConsumerPlace(String consumerPlace) {
        this.consumerPlace = consumerPlace;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLikeBook() {
        return likeBook;
    }

    public void setLikeBook(String likeBook) {
        this.likeBook = likeBook;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getLikeSport() {
        return likeSport;
    }

    public void setLikeSport(String likeSport) {
        this.likeSport = likeSport;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPurposePrice() {
        return purposePrice;
    }

    public void setPurposePrice(Integer purposePrice) {
        this.purposePrice = purposePrice;
    }

    public Integer getPurposeProduct() {
        return purposeProduct;
    }

    public void setPurposeProduct(Integer purposeProduct) {
        this.purposeProduct = purposeProduct;
    }

    public Integer getPurposeCategory() {
        return purposeCategory;
    }

    public void setPurposeCategory(Integer purposeCategory) {
        this.purposeCategory = purposeCategory;
    }

    public Calendar getPurposeDay() {
        return purposeDay;
    }

    public void setPurposeDay(Calendar purposeDay) {
        this.purposeDay = purposeDay;
    }

    public Integer getSources() {
        return sources;
    }

    public void setSources(Integer sources) {
        this.sources = sources;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getUnionActivitiesId() {
        return unionActivitiesId;
    }

    public void setUnionActivitiesId(Long unionActivitiesId) {
        this.unionActivitiesId = unionActivitiesId;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    public String getSrcStoreNo() {
        return srcStoreNo;
    }

    public void setSrcStoreNo(String srcStoreNo) {
        this.srcStoreNo = srcStoreNo;
    }

    public String getSrcStoreName() {
        return srcStoreName;
    }

    public void setSrcStoreName(String srcStoreName) {
        this.srcStoreName = srcStoreName;
    }

    public String getOtherSources() {
        return otherSources;
    }

    public void setOtherSources(String otherSources) {
        this.otherSources = otherSources;
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

    public CustStatus getStatus() {
        return status;
    }

    public void setStatus(CustStatus status) {
        this.status = status;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getJewerlyAmount() {
        return jewerlyAmount;
    }

    public void setJewerlyAmount(Double jewerlyAmount) {
        this.jewerlyAmount = jewerlyAmount;
    }

    public Integer getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Integer creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getFresh() {
        return fresh;
    }

    public void setFresh(Integer fresh) {
        this.fresh = fresh;
    }

    public Calendar getFreshChgTime() {
        return freshChgTime;
    }

    public void setFreshChgTime(Calendar freshChgTime) {
        this.freshChgTime = freshChgTime;
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

    public String getBelongStoreNo() {
        return belongStoreNo;
    }

    public void setBelongStoreNo(String belongStoreNo) {
        this.belongStoreNo = belongStoreNo;
    }

    public String getBelongStoreName() {
        return belongStoreName;
    }

    public void setBelongStoreName(String belongStoreName) {
        this.belongStoreName = belongStoreName;
    }

    public Integer getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }

    public Integer getIsImport() {
        return isImport;
    }

    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCreditStatusName() {
        return creditStatusName;
    }

    public void setCreditStatusName(String creditStatusName) {
        this.creditStatusName = creditStatusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getPurposePriceName() {
        return purposePriceName;
    }

    public void setPurposePriceName(String purposePriceName) {
        this.purposePriceName = purposePriceName;
    }

    public String getPurposeProductName() {
        return purposeProductName;
    }

    public void setPurposeProductName(String purposeProductName) {
        this.purposeProductName = purposeProductName;
    }

    public String getPurposeCategoryName() {
        return purposeCategoryName;
    }

    public void setPurposeCategoryName(String purposeCategoryName) {
        this.purposeCategoryName = purposeCategoryName;
    }

    public String getLvName() {
        return lvName;
    }

    public void setLvName(String lvName) {
        this.lvName = lvName;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

    public String getFreshName() {
        return freshName;
    }

    public void setFreshName(String freshName) {
        this.freshName = freshName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGroupCustName() {
        return groupCustName;
    }

    public void setGroupCustName(String groupCustName) {
        this.groupCustName = groupCustName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getUnionActivitiesName() {
        return unionActivitiesName;
    }

    public void setUnionActivitiesName(String unionActivitiesName) {
        this.unionActivitiesName = unionActivitiesName;
    }

    public String getBelongAttrName() {
        return belongAttrName;
    }

    public void setBelongAttrName(String belongAttrName) {
        this.belongAttrName = belongAttrName;
    }

    public List<IndividCustFamily> getIndividCustFamily() {
        return individCustFamily;
    }

    public void setIndividCustFamily(List<IndividCustFamily> individCustFamily) {
        this.individCustFamily = individCustFamily;
    }

    public List<Anniversary> getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(List<Anniversary> anniversary) {
        this.anniversary = anniversary;
    }

    public List<SalesRecord> getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(List<SalesRecord> salesRecord) {
        this.salesRecord = salesRecord;
    }

    public List<ActiveStatus> getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(List<ActiveStatus> activeStatus) {
        this.activeStatus = activeStatus;
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

    public List<PosOrder> getPosOrderList() {
        return posOrderList;
    }

    public void setPosOrderList(List<PosOrder> posOrderList) {
        this.posOrderList = posOrderList;
    }

    public List<RightMaint> getRightMaint() {
        return rightMaint;
    }

    public void setRightMaint(List<RightMaint> rightMaint) {
        this.rightMaint = rightMaint;
    }

    public List<BusiRegist> getBusiRegist() {
        return busiRegist;
    }

    public void setBusiRegist(List<BusiRegist> busiRegist) {
        this.busiRegist = busiRegist;
    }

    public List<CallRegist> getCallRegist() {
        return callRegist;
    }

    public void setCallRegist(List<CallRegist> callRegist) {
        this.callRegist = callRegist;
    }

    public List<TelVisitCust> getTelVisitCustList() {
        return telVisitCustList;
    }

    public void setTelVisitCustList(List<TelVisitCust> telVisitCustList) {
        this.telVisitCustList = telVisitCustList;
    }

    public List<TelVisitRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<TelVisitRecord> recordList) {
        this.recordList = recordList;
    }

    public List<IndiPartIn> getIndiPartIn() {
        return indiPartIn;
    }

    public void setIndiPartIn(List<IndiPartIn> indiPartIn) {
        this.indiPartIn = indiPartIn;
    }

    public List<AfterServ> getAfterserv() {
        return afterserv;
    }

    public void setAfterserv(List<AfterServ> afterserv) {
        this.afterserv = afterserv;
    }

}