package com.huiju.permission.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 资源组联合主键类
 * 
 * @author Linjx
 */
public class AuthGroupPK implements Serializable {

	private static final long serialVersionUID = 1521135348391585910L;
	@Column(updatable = false)
	private String clientCode;// 客户端编码
	@Column(updatable = false)
	private String agrCode;// 资源组编码

	public AuthGroupPK() {
	}

	public AuthGroupPK(String clientCode, String agrCode) {
		this.clientCode = clientCode;
		this.agrCode = agrCode;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agrCode == null) ? 0 : agrCode.hashCode());
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
		AuthGroupPK other = (AuthGroupPK) obj;
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