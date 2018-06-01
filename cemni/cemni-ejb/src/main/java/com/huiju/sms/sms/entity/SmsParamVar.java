package com.huiju.sms.sms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 可选变量-参数
 * 
 * @author：yuhb
 * @date：2016年12月31日 下午1:09:51
 */
@Entity
@Table(name = "D_SMS_PARAM_VAR")
public class SmsParamVar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long paramVarId;
    private String name;
    private Integer type;
    private Integer orderNo;
    private Integer colIndex;
    private String colName;// 表列名（云片网模版中使用）

    public Long getParamVarId() {
        return paramVarId;
    }

    public void setParamVarId(Long paramVarId) {
        this.paramVarId = paramVarId;
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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

}