package com.huiju.expandbusi.franchiseevalue.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.module.data.BaseEntity;

/**
 * 加盟商价值管理
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月27日 下午5:25:52
 */
@Entity
@Table(name = "D_EXPANDBUSI_FRANCHISEEVALUE")
public class FranchiseeValue extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FranchiseeValue_PK")
    @TableGenerator(name = "FranchiseeValue_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "FranchiseeValue_PK", allocationSize = 1)
    private Long franchiseeValueId;//number      主键  

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;//number       加盟商    

    private String remark;//varchar2(50)备注   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeValue", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<StoreCost> storeCost;

    public List<StoreCost> getStoreCost() {
        return storeCost;
    }

    public void setStoreCost(List<StoreCost> storeCost) {
        this.storeCost = storeCost;
    }

    public Long getFranchiseeValueId() {
        return franchiseeValueId;
    }

    public void setFranchiseeValueId(Long franchiseeValueId) {
        this.franchiseeValueId = franchiseeValueId;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Calendar getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

}