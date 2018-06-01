package com.huiju.afterservice.telvisitrecord.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.afterservice.telvisit.entity.TelVisit;
import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_AFTERSERVICE_TELVISITRECORD")
public class TelVisitRecord extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_afterservice_record")
    @SequenceGenerator(name = "SEQ_afterservice_record", sequenceName = "SEQ_afterservice_record", allocationSize = 1)
    private Long telVisitRecordId;

    @ManyToOne
    @JoinColumn(name = "telVisitId", referencedColumnName = "telVisitId")
    private TelVisit telVisit;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    private String telVisitRecordNo;// 回访记录单号
    private String storeManagerName;// 回访人员（店长名称）
    private String storeNo;// 回访门店-编码
    private String storeName;// 回访门店-名称
    private Integer expiredtype;// 是否已过期：1是、0否

    private Integer backfs;// 回访方式    
    private Integer backzt;// 是否已回访：1是、0否
    private Integer backtype;// 回访类型
    private Integer taskType;// 任务类型

    @Temporal(TemporalType.DATE)
    private Calendar backtime;// 回访日期-可编辑
    @Temporal(TemporalType.DATE)
    private Calendar startrq;// 开始日期
    @Temporal(TemporalType.DATE)
    private Calendar endrq;// 结束日期

    private String cuserCode;// varchar2(50)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    private String muserCode;// varchar2(50)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;

    private Integer saletype;// 消费类型

    // 已消费满意度
    private Integer shopservice;// 店面服务
    private Integer ornamentwear;// 饰品佩戴
    private String khadvice;// 客户建议
    private String khtalk;// 客户咨询
    private Integer professorknow;// 专业知识
    private String infoknowed;// 已告知讯息
    private Integer shopenvi;// 购物环境
    private Integer parentgant;// 赠品发放
    private String feedadvice;// 反馈意见
    private Integer wearupdate;// 佩戴后维护
    private Integer parentmanyi;// 赠品满意度
    private Integer careupdate;// 保养维修
    private Integer intentioncp;// 意向产品
    private Integer newrecoment;// 新品推荐
    private String saleremark;// 备注

    // 未消费满意度
    private Integer notshopservice;// 店面服务
    private String notkhadvice;// 客户建议
    private String notkhtalk;// 客户咨询
    private String notinfoknowed;// 已告知讯息
    private Integer notprofessorknow;// 专业知识
    private String notintentioncp;// 意向产品
    private Integer notshopenvi;// 购物环境
    private String notnewrecoment;// 新品推荐
    private String notfeedadvice;// 反馈意见
    private String notsaleremark;// 备注

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String backtypeName;// 回访类型
    @Transient
    private String backztName;// 回访状态
    @Transient
    private String expiredtypeName;// 有效期状态
    @Transient
    private String backfsName;// 回访方式
    @Transient
    private String taskTypeName;
    @Transient
    private String status;// 状态
    @Transient
    private String persuccess;// 成功率
    @Transient
    private String satisfaction;// 满意度

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getTelVisitRecordId() {
        return telVisitRecordId;
    }

    public void setTelVisitRecordId(Long telVisitRecordId) {
        this.telVisitRecordId = telVisitRecordId;
    }

    public TelVisit getTelVisit() {
        return telVisit;
    }

    public void setTelVisit(TelVisit telVisit) {
        this.telVisit = telVisit;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

    public String getTelVisitRecordNo() {
        return telVisitRecordNo;
    }

    public void setTelVisitRecordNo(String telVisitRecordNo) {
        this.telVisitRecordNo = telVisitRecordNo;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getExpiredtype() {
        return expiredtype;
    }

    public void setExpiredtype(Integer expiredtype) {
        this.expiredtype = expiredtype;
    }

    public Integer getBackfs() {
        return backfs;
    }

    public void setBackfs(Integer backfs) {
        this.backfs = backfs;
    }

    public Integer getBackzt() {
        return backzt;
    }

    public void setBackzt(Integer backzt) {
        this.backzt = backzt;
    }

    public Integer getBacktype() {
        return backtype;
    }

    public void setBacktype(Integer backtype) {
        this.backtype = backtype;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Calendar getBacktime() {
        return backtime;
    }

    public void setBacktime(Calendar backtime) {
        this.backtime = backtime;
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

    public String getCuserCode() {
        return cuserCode;
    }

    public void setCuserCode(String cuserCode) {
        this.cuserCode = cuserCode;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public String getMuserCode() {
        return muserCode;
    }

    public void setMuserCode(String muserCode) {
        this.muserCode = muserCode;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public Integer getSaletype() {
        return saletype;
    }

    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }

    public Integer getShopservice() {
        return shopservice;
    }

    public void setShopservice(Integer shopservice) {
        this.shopservice = shopservice;
    }

    public Integer getOrnamentwear() {
        return ornamentwear;
    }

    public void setOrnamentwear(Integer ornamentwear) {
        this.ornamentwear = ornamentwear;
    }

    public String getKhadvice() {
        return khadvice;
    }

    public void setKhadvice(String khadvice) {
        this.khadvice = khadvice;
    }

    public String getKhtalk() {
        return khtalk;
    }

    public void setKhtalk(String khtalk) {
        this.khtalk = khtalk;
    }

    public Integer getProfessorknow() {
        return professorknow;
    }

    public void setProfessorknow(Integer professorknow) {
        this.professorknow = professorknow;
    }

    public String getInfoknowed() {
        return infoknowed;
    }

    public void setInfoknowed(String infoknowed) {
        this.infoknowed = infoknowed;
    }

    public Integer getShopenvi() {
        return shopenvi;
    }

    public void setShopenvi(Integer shopenvi) {
        this.shopenvi = shopenvi;
    }

    public Integer getParentgant() {
        return parentgant;
    }

    public void setParentgant(Integer parentgant) {
        this.parentgant = parentgant;
    }

    public String getFeedadvice() {
        return feedadvice;
    }

    public void setFeedadvice(String feedadvice) {
        this.feedadvice = feedadvice;
    }

    public Integer getWearupdate() {
        return wearupdate;
    }

    public void setWearupdate(Integer wearupdate) {
        this.wearupdate = wearupdate;
    }

    public Integer getParentmanyi() {
        return parentmanyi;
    }

    public void setParentmanyi(Integer parentmanyi) {
        this.parentmanyi = parentmanyi;
    }

    public Integer getCareupdate() {
        return careupdate;
    }

    public void setCareupdate(Integer careupdate) {
        this.careupdate = careupdate;
    }

    public Integer getIntentioncp() {
        return intentioncp;
    }

    public void setIntentioncp(Integer intentioncp) {
        this.intentioncp = intentioncp;
    }

    public Integer getNewrecoment() {
        return newrecoment;
    }

    public void setNewrecoment(Integer newrecoment) {
        this.newrecoment = newrecoment;
    }

    public String getSaleremark() {
        return saleremark;
    }

    public void setSaleremark(String saleremark) {
        this.saleremark = saleremark;
    }

    public Integer getNotshopservice() {
        return notshopservice;
    }

    public void setNotshopservice(Integer notshopservice) {
        this.notshopservice = notshopservice;
    }

    public String getNotkhadvice() {
        return notkhadvice;
    }

    public void setNotkhadvice(String notkhadvice) {
        this.notkhadvice = notkhadvice;
    }

    public String getNotkhtalk() {
        return notkhtalk;
    }

    public void setNotkhtalk(String notkhtalk) {
        this.notkhtalk = notkhtalk;
    }

    public String getNotinfoknowed() {
        return notinfoknowed;
    }

    public void setNotinfoknowed(String notinfoknowed) {
        this.notinfoknowed = notinfoknowed;
    }

    public Integer getNotprofessorknow() {
        return notprofessorknow;
    }

    public void setNotprofessorknow(Integer notprofessorknow) {
        this.notprofessorknow = notprofessorknow;
    }

    public String getNotintentioncp() {
        return notintentioncp;
    }

    public void setNotintentioncp(String notintentioncp) {
        this.notintentioncp = notintentioncp;
    }

    public Integer getNotshopenvi() {
        return notshopenvi;
    }

    public void setNotshopenvi(Integer notshopenvi) {
        this.notshopenvi = notshopenvi;
    }

    public String getNotnewrecoment() {
        return notnewrecoment;
    }

    public void setNotnewrecoment(String notnewrecoment) {
        this.notnewrecoment = notnewrecoment;
    }

    public String getNotfeedadvice() {
        return notfeedadvice;
    }

    public void setNotfeedadvice(String notfeedadvice) {
        this.notfeedadvice = notfeedadvice;
    }

    public String getNotsaleremark() {
        return notsaleremark;
    }

    public void setNotsaleremark(String notsaleremark) {
        this.notsaleremark = notsaleremark;
    }

    public String getBacktypeName() {
        return backtypeName;
    }

    public void setBacktypeName(String backtypeName) {
        this.backtypeName = backtypeName;
    }

    public String getBackztName() {
        return backztName;
    }

    public void setBackztName(String backztName) {
        this.backztName = backztName;
    }

    public String getExpiredtypeName() {
        return expiredtypeName;
    }

    public void setExpiredtypeName(String expiredtypeName) {
        this.expiredtypeName = expiredtypeName;
    }

    public String getBackfsName() {
        return backfsName;
    }

    public void setBackfsName(String backfsName) {
        this.backfsName = backfsName;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersuccess() {
        return persuccess;
    }

    public void setPersuccess(String persuccess) {
        this.persuccess = persuccess;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

}