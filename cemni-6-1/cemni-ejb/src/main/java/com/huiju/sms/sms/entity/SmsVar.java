package com.huiju.sms.sms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

/**
 * 与短信模版关联的已选变量
 * 
 * @author：yuhb
 * @date：2016年12月31日 下午1:12:35
 */
@Entity
@Table(name = "D_SMS_VAR")
public class SmsVar extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SmsVar_PK")
    @TableGenerator(name = "SmsVar_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "SmsVar_PK", allocationSize = 1)
    private Long smsVarId;

    private Long paramVarId;

    @ManyToOne
    @JoinColumn(name = "smsId", referencedColumnName = "smsId")
    private Sms sms;

    public Long getSmsVarId() {
        return smsVarId;
    }

    public void setSmsVarId(Long smsVarId) {
        this.smsVarId = smsVarId;
    }

    public Long getParamVarId() {
        return paramVarId;
    }

    public void setParamVarId(Long paramVarId) {
        this.paramVarId = paramVarId;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

}