package com.huiju.expandbusi.franchiseevalue.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

/**
 * 加盟商价值管理-开店成本
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月27日 下午5:59:09
 */
@Entity
@Table(name = "D_EXPANDBUSI_STORECOST")
public class StoreCost extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StoreCost_PK")
    @TableGenerator(name = "StoreCost_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "StoreCost_PK", allocationSize = 1)
    private Long storeCostId;//number      主键     

    @ManyToOne
    @JoinColumn(name = "franchiseeValueId", referencedColumnName = "franchiseeValueId")
    private FranchiseeValue franchiseeValue;//number      加盟商价值管理  

    private Double contractLife;//number      合同年限      

    private Double breakEvenAge;//number      盈亏平衡年限 

    private Double costSupport;//number      公司支持成本 

    private Double takeGoodsCost;//number      整体拿货成本 

    private Double borrowFree;//number      借货免息     

    private Double loanFree;//number      借款免息   

    private Double profit;//number      盈利       

    private Double roi;//number      roi  

    private Double eliminateReason;//varchar2(50)汰换原因   

    private String remake;//varchar2(50)备注        

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人        

    public Long getStoreCostId() {
        return storeCostId;
    }

    public void setStoreCostId(Long storeCostId) {
        this.storeCostId = storeCostId;
    }

    public FranchiseeValue getFranchiseeValue() {
        return franchiseeValue;
    }

    public void setFranchiseeValue(FranchiseeValue franchiseeValue) {
        this.franchiseeValue = franchiseeValue;
    }

    public Double getContractLife() {
        return contractLife;
    }

    public void setContractLife(Double contractLife) {
        this.contractLife = contractLife;
    }

    public Double getBreakEvenAge() {
        return breakEvenAge;
    }

    public void setBreakEvenAge(Double breakEvenAge) {
        this.breakEvenAge = breakEvenAge;
    }

    public Double getCostSupport() {
        return costSupport;
    }

    public void setCostSupport(Double costSupport) {
        this.costSupport = costSupport;
    }

    public Double getTakeGoodsCost() {
        return takeGoodsCost;
    }

    public void setTakeGoodsCost(Double takeGoodsCost) {
        this.takeGoodsCost = takeGoodsCost;
    }

    public Double getBorrowFree() {
        return borrowFree;
    }

    public void setBorrowFree(Double borrowFree) {
        this.borrowFree = borrowFree;
    }

    public Double getLoanFree() {
        return loanFree;
    }

    public void setLoanFree(Double loanFree) {
        this.loanFree = loanFree;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public Double getEliminateReason() {
        return eliminateReason;
    }

    public void setEliminateReason(Double eliminateReason) {
        this.eliminateReason = eliminateReason;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
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
