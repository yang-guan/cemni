package com.huiju.console.user2org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_CN_User2org")
public class User2org extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User2org_PK")
    @TableGenerator(name = "User2org_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "User2org_PK", allocationSize = 1)
    private Long user2orgId;

    private Long userId;
    private Long orgId;

    public Long getUser2orgId() {
        return user2orgId;
    }

    public void setUser2orgId(Long user2orgId) {
        this.user2orgId = user2orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}