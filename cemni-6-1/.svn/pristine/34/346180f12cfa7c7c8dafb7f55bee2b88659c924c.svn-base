package com.huiju.inter.interLog.entity;

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

@Entity
@Table(name = "D_INTERFACE_LOG")
public class InterLog extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_InterfaceLog")
    @SequenceGenerator(name = "SEQ_InterfaceLog", sequenceName = "SEQ_InterfaceLog", allocationSize = 1)
    private Long interfaceLogId;

    private String crmClassMethod;// crm端的类方法名
    private String url;// 请求地址
    private String reqContent;// 请求
    private String respContent;// 响应

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar reqTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar respTime;

    private Integer src;// 接口来源：字典表9100（1NC、2CRM、3微信、4耀我网、crm短信扫描）
    private Integer status;// 状态：字典表9000（1成功、0失败）

    private String muser;// 重发人
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;// 重发时间

    @Transient
    private String srcName;

    public Long getInterfaceLogId() {
        return interfaceLogId;
    }

    public void setInterfaceLogId(Long interfaceLogId) {
        this.interfaceLogId = interfaceLogId;
    }

    public String getCrmClassMethod() {
        return crmClassMethod;
    }

    public void setCrmClassMethod(String crmClassMethod) {
        this.crmClassMethod = crmClassMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Calendar getReqTime() {
        return reqTime;
    }

    public void setReqTime(Calendar reqTime) {
        this.reqTime = reqTime;
    }

    public Calendar getRespTime() {
        return respTime;
    }

    public void setRespTime(Calendar respTime) {
        this.respTime = respTime;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

}