package com.huiju.permission.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 用户与资源组关系联合主键
 * 
 * @author zhangxj
 */
public class UserAuthGroupPK implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2204834003481801177L;
	@Column(updatable = false)
	private String clientCode;
	@Column(updatable = false)
	private String userCode;
	@Column(updatable = false)
	private String agrCode;

	public UserAuthGroupPK() {
	}

	public UserAuthGroupPK(String clientCode, String userCode, String agrCode) {
		this.clientCode = clientCode;
		this.userCode = userCode;
		this.agrCode = agrCode;
	}

	public String getClientCode() {
		return this.clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAgrCode() {
		return this.agrCode;
	}

	public void setAgrCode(String agrCode) {
		this.agrCode = agrCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAuthGroupPK other = (UserAuthGroupPK) obj;
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
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agrCode == null) ? 0 : agrCode.hashCode());
		result = prime * result
				+ ((clientCode == null) ? 0 : clientCode.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		return result;
	}
}