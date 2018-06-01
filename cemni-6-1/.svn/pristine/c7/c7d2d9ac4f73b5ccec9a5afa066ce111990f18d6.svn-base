package com.huiju.sms.sms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

/**
 * 发送对象
 * 
 * @author：yuhb
 * @date：2016年12月31日 下午1:10:23
 */
@Entity
@Table(name = "D_SMS_OBJ_INDIVIDCUST")
public class ObjCust extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ObjIndividCust_PK")
    @TableGenerator(name = "ObjIndividCust_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ObjIndividCust_PK", allocationSize = 1)
    private Long smsObjIndividCustId;

    @ManyToOne
    @JoinColumn(name = "smsId", referencedColumnName = "smsId")
    private Sms sms;

    private Long individCustId;

    @Transient
    private String cardNo;
    @Transient
    private String name;
    @Transient
    private String mobile;

    public Long getSmsObjIndividCustId() {
        return smsObjIndividCustId;
    }

    public void setSmsObjIndividCustId(Long smsObjIndividCustId) {
        this.smsObjIndividCustId = smsObjIndividCustId;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    public Long getIndividCustId() {
        return individCustId;
    }

    public void setIndividCustId(Long individCustId) {
        this.individCustId = individCustId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}