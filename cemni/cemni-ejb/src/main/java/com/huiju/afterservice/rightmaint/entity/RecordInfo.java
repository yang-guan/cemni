package com.huiju.afterservice.rightmaint.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

/**
 * 权益活动记录信息
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年2月16日 下午2:11:28
 */
@Entity
@Table(name = "D_RECORDINFO")
public class RecordInfo extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RecordInfo_PK")
    @TableGenerator(name = "RecordInfo_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "RecordInfo_PK", allocationSize = 1)
    private Long recordInfoId;

    private String cardNumber;// varchar2(50) 单号      

    private Long checkUserId;// 核定人id

    private String checkUserName;//核定人姓名

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar checkDate;// 核定日期

    private Integer checkResult;// 核定结果

    public Long getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(Long recordInfoId) {
        this.recordInfoId = recordInfoId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Calendar getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Calendar checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public Long getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

}