package com.huiju.permission.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.huiju.module.data.BaseEntity;

/**
 * 权限字段
 * 
 * @author Linjx
 */
@Entity
@Table(name = "S_SM_AUTHFIELD")
public class AuthField extends BaseEntity<String> {

    private static final long serialVersionUID = -719773181678389345L;

    @Id
    private String authCode; // 权限字段

    private String fieldCode; // 字段名

    private String fieldName; // 字段描述

    private String fieldType; // 字段类型

    private Integer fieldLength; // 字段长度

    private String tableName; // 表名

    public AuthField() {
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authCode == null) ? 0 : authCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthField other = (AuthField) obj;
        if (authCode == null) {
            if (other.authCode != null)
                return false;
        } else if (!authCode.equals(other.authCode))
            return false;
        return true;
    }

}