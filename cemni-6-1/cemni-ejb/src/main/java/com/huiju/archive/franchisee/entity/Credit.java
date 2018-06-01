package com.huiju.archive.franchisee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_FRANCHISEE_CREDIT")
public class Credit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CREDIT_PK")
    @TableGenerator(name = "CREDIT_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CREDIT_PK", allocationSize = 1)
    private Long creditId;

    private Integer loan;// 资产状况有无贷款
    private Integer ownSource;// 是否自带渠道
    private Integer guarantee;// 是否有担保
    private Integer shares;// 股东股份状况
    private Integer firstArrears;// 首批欠款记录
    private Integer addArrears;// 补货欠款记录
    private Integer badRecord;// 赊销不良记录

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
        this.loan = loan;
    }

    public Integer getOwnSource() {
        return ownSource;
    }

    public void setOwnSource(Integer ownSource) {
        this.ownSource = ownSource;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Integer getFirstArrears() {
        return firstArrears;
    }

    public void setFirstArrears(Integer firstArrears) {
        this.firstArrears = firstArrears;
    }

    public Integer getAddArrears() {
        return addArrears;
    }

    public void setAddArrears(Integer addArrears) {
        this.addArrears = addArrears;
    }

    public Integer getBadRecord() {
        return badRecord;
    }

    public void setBadRecord(Integer badRecord) {
        this.badRecord = badRecord;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

}