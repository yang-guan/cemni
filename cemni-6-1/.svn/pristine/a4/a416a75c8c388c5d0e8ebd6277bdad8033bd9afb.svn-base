package com.huiju.console.orgext.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.console.store.entity.Store;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_CN_ORG_EXT")
public class OrgExt extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OrgExt_PK")
    @TableGenerator(name = "OrgExt_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "OrgExt_PK", allocationSize = 1)
    private Long orgId;

    private String orgCode;// varchar2(20)    编码
    private String name;// varchar2(100)      名称
    private Integer type;// number(4)         机构类型：1公司、2门店
    private Long parentId;// number           父模块
    private Integer orderNo;// number(4)      排序
    private Integer isValid;// number(4)      是否有效

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;

    @OneToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    private Store store;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}