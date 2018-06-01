package com.huiju.permission.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.huiju.module.data.BaseEntity;

/**
 * 用户与资源组关系实体
 * 
 * @author zhangxj
 */
@Entity
@Table(name = "S_SM_AUTHGROUPUSER")
@IdClass(UserAuthGroupPK.class)
public class UserAuthGroup extends BaseEntity<UserAuthGroupPK> {

    /** serialVersionUID */
    private static final long serialVersionUID = -8027021661874888467L;
    @Id
    private String clientCode;
    @Id
    private String userCode;
    @Id
    private String agrCode;
    @ManyToOne
    @JoinColumns(value = { @JoinColumn(name = "CLIENTCODE", referencedColumnName = "CLIENTCODE", insertable = false, updatable = false), @JoinColumn(name = "AGRCODE", referencedColumnName = "AGRCODE", insertable = false, updatable = false) })
    private AuthGroup authGroup;

    public UserAuthGroup() {
    }

    public AuthGroup getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(AuthGroup authGroup) {
        this.authGroup = authGroup;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
        result = prime * result + ((clientCode == null) ? 0 : clientCode.hashCode());
        result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
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
        UserAuthGroup other = (UserAuthGroup) obj;
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

}