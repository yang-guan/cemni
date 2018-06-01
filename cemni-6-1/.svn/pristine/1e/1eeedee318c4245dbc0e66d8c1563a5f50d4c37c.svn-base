package com.huiju.permission.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.huiju.module.data.BaseEntity;

/**
 * 资源组实体
 * 
 * @author Linjx
 */
@Entity
@Table(name = "S_SM_AUTHGROUP")
@IdClass(AuthGroupPK.class)
public class AuthGroup extends BaseEntity<AuthGroupPK> {
	private static final long serialVersionUID = 4006591458827612634L;
	@Id
	private String clientCode;// 客户端编码
	@Id
	private String agrCode;// 资源组编码

	private String agrDesc; // 资源组描述

	@OneToMany(mappedBy = "authGroup",orphanRemoval = true)
	private List<AuthGroupDetail> authGroupDetails;

	public AuthGroup() {
	}

	public String getAgrDesc() {
		return agrDesc;
	}

	public void setAgrDesc(String agrDesc) {
		this.agrDesc = agrDesc;
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

	public List<AuthGroupDetail> getAuthGroupDetails() {
		return authGroupDetails;
	}

	public void setAuthGroupDetails(List<AuthGroupDetail> authGroupDetails) {
		this.authGroupDetails = authGroupDetails;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agrCode == null) ? 0 : agrCode.hashCode());
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
        AuthGroup other = (AuthGroup) obj;
        if (agrCode == null) {
            if (other.agrCode != null)
                return false;
        } else if (!agrCode.equals(other.agrCode))
            return false;
        if (clientCode == null) {
            if (other.clientCode != null)
                return false;
        } else if (!clientCode.equals(other.clientCode))
            return false;
        return true;
    }

}