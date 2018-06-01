package com.huiju.permission.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 资源组分配权限字段联合主键类
 * 
 * @author Linjx
 */
public class AuthGroupDetailPK implements Serializable {

	private static final long serialVersionUID = -3380056999469363023L;
	@Column(updatable = false)
	private String clientCode;// 客户端编码
	@Column(updatable = false)
	private String agrCode;// 资源组编码
	@Column(updatable = false)
	private String authCode;// 权限字段编码

	public AuthGroupDetailPK() {
	}

	public AuthGroupDetailPK(String clientCode, String agrCode, String authCode) {
		this.clientCode = clientCode;
		this.agrCode = agrCode;
		this.authCode = authCode;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agrCode == null) ? 0 : agrCode.hashCode());
		result = prime * result
				+ ((authCode == null) ? 0 : authCode.hashCode());
		result = prime * result
				+ ((clientCode == null) ? 0 : clientCode.hashCode());
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
		AuthGroupDetailPK other = (AuthGroupDetailPK) obj;
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