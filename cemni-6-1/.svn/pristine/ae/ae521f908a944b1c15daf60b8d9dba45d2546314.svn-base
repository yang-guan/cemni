package com.huiju.actment.activity.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.console.org.entity.Org;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY")
public class Activity extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Activity_PK")
    @TableGenerator(name = "Activity_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Activity_PK", allocationSize = 1)
    private Long activityId;

    private String activityNo;// 活动单号
    private Integer activityType;// 活动类型
    private Integer activityForm;// 活动形式
    private String activityTheme;// 活动主题

    private String launchor;// 发起人员
    private String launchorCode;// 发起人员
    private Integer status;// 活动状态
    private ActStatus auditStatus;// 审核状态

    @OneToOne
    @JoinColumn(name = "orgId", referencedColumnName = "orgId")
    private Org org;// 发起部门

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar beginTime;// 开始时间
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;// 结束时间

    private String assistDept;// 协助部门

    private String promise;// 定位承诺
    private String overview;// 市场概览
    private String cardtatget;// 客流目标
    private String saletatget;// 销售目标
    private String background;// 活动背景
    private String aim;// 活动目的
    private String plan;// 传播计划
    private String assist;// 需协助事项
    private String content;// 活动内容
    private String remark;// 备注
    private Integer template;// 是否为模版
    private Double auditCost;// 审核费用总金额
    private String variety;// 适用品类
    private String series;// 适用系列
    private Double jewelleryAmountG;// 累计珠宝额大于等于
    private Double jewelleryAmountL;// 累计珠宝额小于等于
    private Double integralReward;// 积分奖励
    private String rules;// 适用规则
    private Long cardAmount;// 分发卡券数量
    private Integer isCreateCard;//是否生成卡券
    private Double lowerLimit;// 购买下限值
    private Double preferential;// 优惠金额

    private String uploadFileGroupId; // 上传附件ID

    private String activityScope;// 适用范围
    private String orgCode;// 门店编码

    private String wecharCodeAddr;// 二维码地址

    private String createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String modifyUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;

    // /////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String activityTypeName;
    @Transient
    private String activityFormName;
    @Transient
    private String auditStatusName;
    @Transient
    private String templateName;
    @Transient
    private String statusName;
    @Transient
    private String activityScopeName;
    @Transient
    private String assistDeptName;
    @Transient
    private String varietyName;
    @Transient
    private String seriesName;
    @Transient
    private String indiPartInParam;

    // /////////////////////////////////////////////////////////////////////////////////////////////////////

    @OneToOne(mappedBy = "activity")
    private JudgeAct judgeAct;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<FraPartIn> fraPartIn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<ParPartIn> parPartIn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<IndiPartIn> indiPartIn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<ExpectCost> expectCost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<Scope> scope;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
    private List<ActGive> actGive;

    // /////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityForm() {
        return activityForm;
    }

    public void setActivityForm(Integer activityForm) {
        this.activityForm = activityForm;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public String getLaunchor() {
        return launchor;
    }

    public void setLaunchor(String launchor) {
        this.launchor = launchor;
    }

    public String getLaunchorCode() {
        return launchorCode;
    }

    public void setLaunchorCode(String launchorCode) {
        this.launchorCode = launchorCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ActStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ActStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCardtatget() {
        return cardtatget;
    }

    public void setCardtatget(String cardtatget) {
        this.cardtatget = cardtatget;
    }

    public String getSaletatget() {
        return saletatget;
    }

    public void setSaletatget(String saletatget) {
        this.saletatget = saletatget;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public Double getAuditCost() {
        return auditCost;
    }

    public void setAuditCost(Double auditCost) {
        this.auditCost = auditCost;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getJewelleryAmountG() {
        return jewelleryAmountG;
    }

    public void setJewelleryAmountG(Double jewelleryAmountG) {
        this.jewelleryAmountG = jewelleryAmountG;
    }

    public Double getJewelleryAmountL() {
        return jewelleryAmountL;
    }

    public void setJewelleryAmountL(Double jewelleryAmountL) {
        this.jewelleryAmountL = jewelleryAmountL;
    }

    public Double getIntegralReward() {
        return integralReward;
    }

    public void setIntegralReward(Double integralReward) {
        this.integralReward = integralReward;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Long getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Long cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Integer getIsCreateCard() {
        return isCreateCard;
    }

    public void setIsCreateCard(Integer isCreateCard) {
        this.isCreateCard = isCreateCard;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Double getPreferential() {
        return preferential;
    }

    public void setPreferential(Double preferential) {
        this.preferential = preferential;
    }

    public String getUploadFileGroupId() {
        return uploadFileGroupId;
    }

    public void setUploadFileGroupId(String uploadFileGroupId) {
        this.uploadFileGroupId = uploadFileGroupId;
    }

    public String getActivityScope() {
        return activityScope;
    }

    public void setActivityScope(String activityScope) {
        this.activityScope = activityScope;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getWecharCodeAddr() {
        return wecharCodeAddr;
    }

    public void setWecharCodeAddr(String wecharCodeAddr) {
        this.wecharCodeAddr = wecharCodeAddr;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    public String getActivityFormName() {
        return activityFormName;
    }

    public void setActivityFormName(String activityFormName) {
        this.activityFormName = activityFormName;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getActivityScopeName() {
        return activityScopeName;
    }

    public void setActivityScopeName(String activityScopeName) {
        this.activityScopeName = activityScopeName;
    }

    public String getAssistDeptName() {
        return assistDeptName;
    }

    public void setAssistDeptName(String assistDeptName) {
        this.assistDeptName = assistDeptName;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getIndiPartInParam() {
        return indiPartInParam;
    }

    public void setIndiPartInParam(String indiPartInParam) {
        this.indiPartInParam = indiPartInParam;
    }

    public JudgeAct getJudgeAct() {
        return judgeAct;
    }

    public void setJudgeAct(JudgeAct judgeAct) {
        this.judgeAct = judgeAct;
    }

    public List<FraPartIn> getFraPartIn() {
        return fraPartIn;
    }

    public void setFraPartIn(List<FraPartIn> fraPartIn) {
        this.fraPartIn = fraPartIn;
    }

    public List<ParPartIn> getParPartIn() {
        return parPartIn;
    }

    public void setParPartIn(List<ParPartIn> parPartIn) {
        this.parPartIn = parPartIn;
    }

    public List<IndiPartIn> getIndiPartIn() {
        return indiPartIn;
    }

    public void setIndiPartIn(List<IndiPartIn> indiPartIn) {
        this.indiPartIn = indiPartIn;
    }

    public List<ExpectCost> getExpectCost() {
        return expectCost;
    }

    public void setExpectCost(List<ExpectCost> expectCost) {
        this.expectCost = expectCost;
    }

    public List<Scope> getScope() {
        return scope;
    }

    public void setScope(List<Scope> scope) {
        this.scope = scope;
    }

    public List<ActGive> getActGive() {
        return actGive;
    }

    public void setActGive(List<ActGive> actGive) {
        this.actGive = actGive;
    }

}