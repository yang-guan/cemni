package com.huiju.permission.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;


/**
 * 资源组分配权限字段实体
 * 
 * @author Linjx
 */
@Entity
@Table(name = "S_SM_AUTHGROUPDETAIL")
@IdClass(AuthGroupDetailPK.class)
public class AuthGroupDetail extends BaseEntity<AuthGroupDetailPK> {

	private static final long serialVersionUID = -8560120302194319787L;
	@Id
	private String clientCode;// 客户端编码
	@Id
	private String agrCode;// 资源组编码
	@Id
	private String authCode;// 权限字段编码

	private String authValue; // 权限值

	private String fieldOrder; // 字段的顺序

	private Long createUserId;// 创建人id

	private String createUserName;// 创建人名字

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createTime;// 创建时间

	private Long modifyUserId;// 修改人id

	private String modifyUserName;// 修改人名字

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifyTime;// 修改时间

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "AUTHCODE", referencedColumnName = "AUTHCODE", insertable = false, updatable = false) })
	private AuthField authField;

	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "CLIENTCODE", referencedColumnName = "CLIENTCODE", insertable = false, updatable = false),
			@JoinColumn(name = "AGRCODE", referencedColumnName = "AGRCODE", insertable = false, updatable = false) })
	private AuthGroup authGroup;

	public AuthField getAuthField() {
		return authField;
	}

	public void setAuthField(AuthField authField) {
		this.authField = authField;
	}

	public AuthGroup getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(AuthGroup authGroup) {
		this.authGroup = authGroup;
	}

	public AuthGroupDetail() {
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getAgrCode() {
		return agrCode;
	}

	public void setAgrCode(String agrCode) {
		this.agrCode = agrCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthValue() {
		return authValue;
	}

	public void setAuthValue(String authValue) {
		this.authValue = authValue;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Calendar getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Calendar modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(String fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agrCode == null) ? 0 : agrCode.hashCode());
        result = prime * result + ((authCode == null) ? 0 : authCode.hashCode());
        result = prime * result + ((clientCode == null) ? 0 : clientCode.hashCode());
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
        AuthGroupDetail other = (AuthGroupDetail) obj;
        if (agrCode == null) {
            if (other.agrCode != null)
                return false;
        } else if (!agrCode.equals(other.agrCode))
            return false;
        if (authCode == null) {
            if (other.authCode != null)
                return false;
        } else if (!authCode.equals(other.authCode))
            return false;
        if (clientCode == null) {
            if (other.clientCode != null)
                return false;
        } else if (!clientCode.equals(other.clientCode))
            return false;
        return true;
    }

}