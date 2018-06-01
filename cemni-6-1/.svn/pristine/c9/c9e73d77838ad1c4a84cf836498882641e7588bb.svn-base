package com.huiju.sms.sms.entity;

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

import com.huiju.module.data.BaseEntity;
import com.huiju.sms.objcondition.entity.ObjCondition;

/**
 * 短信模版
 * 
 * @author：yuhb
 * @date：2017年12月31日 下午1:05:02
 */
@Entity
@Table(name = "D_SMS")
public class Sms extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sms_PK")
    @TableGenerator(name = "Sms_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sms_PK", allocationSize = 1)
    private Long smsId;

    private Long tplId;// number           云片网中短信模版ID
    private String name;// varchar2(100)   短信名称
    private Integer type;// number(4)      短信类型：对应字典表8000
    private Integer isValid;// number(4)   是否有效
    private String content;// varchar2(500)短信内容

    private Integer sendType;// number(4)  发送类型：1立即发送、2定时发送、3循环发送
    private Integer cycleType;// number(4) 循环类型：对应字典表的8002
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timedSend;// date     定时发送时间：年月日时分秒

    private String cuser;// varchar2(50)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    private String muser;// varchar2(50)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;

    private Long activityId;// 活动ID（营销短信专用）

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sms", orphanRemoval = true)
    private List<SmsVar> smsVarList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sms", orphanRemoval = true)
    private List<ObjCondition> objCondtionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sms", orphanRemoval = true)
    private List<ObjCust> objCustList;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transient
    private String typeName;
    @Transient
    private String timedSendStr;
    @Transient
    private String sendTimeStr;
    @Transient
    private String activityTheme;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getTplId() {
        return tplId;
    }

    public void setTplId(Long tplId) {
        this.tplId = tplId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public Calendar getTimedSend() {
        return timedSend;
    }

    public void setTimedSend(Calendar timedSend) {
        this.timedSend = timedSend;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public List<SmsVar> getSmsVarList() {
        return smsVarList;
    }

    public void setSmsVarList(List<SmsVar> smsVarList) {
        this.smsVarList = smsVarList;
    }

    public List<ObjCondition> getObjCondtionList() {
        return objCondtionList;
    }

    public void setObjCondtionList(List<ObjCondition> objCondtionList) {
        this.objCondtionList = objCondtionList;
    }

    public List<ObjCust> getObjCustList() {
        return objCustList;
    }

    public void setObjCustList(List<ObjCust> objCustList) {
        this.objCustList = objCustList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTimedSendStr() {
        return timedSendStr;
    }

    public void setTimedSendStr(String timedSendStr) {
        this.timedSendStr = timedSendStr;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

}