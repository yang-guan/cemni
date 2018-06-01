package com.huiju.sms.smslog.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

/**
 * 短信发送日志
 * 
 * @author：yuhb
 * @date：2017年12月31日 下午1:04:31
 */
@Entity
@Table(name = "D_SMS_LOG")
public class SmsLog extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SmsLog")
    @SequenceGenerator(name = "SEQ_SmsLog", sequenceName = "SEQ_SmsLog", allocationSize = 1)
    private Long smsLogId;

    private Long smsId;// 短信模版ID
    private Integer type;// 短信类型：对应字典表8000
    private Long mobile;
    private String reqContent;
    private String respContent;
    private Integer status;// 状态：字典表9000（1成功、0失败）
    private Integer reSendCnt;// 重发次数
    private String muser;// 重发人

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;// 重发时间

    @Transient
    private String typeName;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getSmsLogId() {
        return smsLogId;
    }

    public void setSmsLogId(Long smsLogId) {
        this.smsLogId = smsLogId;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getReqContent() {
        return reqContent;
    }

    public void setReqContent(String reqContent) {
        this.reqContent = reqContent;
    }

    public String getRespContent() {
        return respContent;
    }

    public void setRespContent(String respContent) {
        this.respContent = respContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReSendCnt() {
        return reSendCnt;
    }

    public void setReSendCnt(Integer reSendCnt) {
        this.reSendCnt = reSendCnt;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}