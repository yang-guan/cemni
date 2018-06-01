package com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity;

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
import javax.persistence.Transient;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.expandbusi.franchiseeprofit.costsupport.entity.CostSupport;
import com.huiju.expandbusi.franchiseeprofit.expandcost.entity.ExpandCost;
import com.huiju.expandbusi.franchiseeprofit.profit.entity.Profit;
import com.huiju.expandbusi.franchiseeprofit.revenue.entity.Revenue;
import com.huiju.expandbusi.franchiseeprofit.shopcost.entity.ShopCost;
import com.huiju.module.data.BaseEntity;

/**
 * 加盟商盈利状况
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月23日 下午2:53:45
 */
@Entity
@Table(name = "D_EXPANDBUSI_FRANCHISEEPROFIT")
public class FranchiseeProfit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FranchiseeProfit_PK")
    @TableGenerator(name = "FranchiseeProfit_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "FranchiseeProfit_PK", allocationSize = 1)
    private Long franchiseeProfitId;//number       主键        

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;//number       加盟商   

    private String customerPattern;//varchar2(50) 客户模式    

    private String balanceLifeTest;//varchar2(50) 平衡年限预测

    private String remark;//varchar2(200)备注        

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人 

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeProfit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CostSupport> costSupport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeProfit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExpandCost> expandCost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeProfit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Profit> profit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeProfit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ShopCost> shopCost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchiseeProfit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Revenue> revenue;

    @Transient
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<CostSupport> getCostSupport() {
        return costSupport;
    }

    public void setCostSupport(List<CostSupport> costSupport) {
        this.costSupport = costSupport;
    }

    public List<ExpandCost> getExpandCost() {
        return expandCost;
    }

    public void setExpandCost(List<ExpandCost> expandCost) {
        this.expandCost = expandCost;
    }

    public List<Profit> getProfit() {
        return profit;
    }

    public void setProfit(List<Profit> profit) {
        this.profit = profit;
    }

    public List<ShopCost> getShopCost() {
        return shopCost;
    }

    public void setShopCost(List<ShopCost> shopCost) {
        this.shopCost = shopCost;
    }

    public List<Revenue> getRevenue() {
        return revenue;
    }

    public void setRevenue(List<Revenue> revenue) {
        this.revenue = revenue;
    }

    public Long getFranchiseeProfitId() {
        return franchiseeProfitId;
    }

    public void setFranchiseeProfitId(Long franchiseeProfitId) {
        this.franchiseeProfitId = franchiseeProfitId;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

    public String getCustomerPattern() {
        return customerPattern;
    }

    public void setCustomerPattern(String customerPattern) {
        this.customerPattern = customerPattern;
    }

    public String getBalanceLifeTest() {
        return balanceLifeTest;
    }

    public void setBalanceLifeTest(String balanceLifeTest) {
        this.balanceLifeTest = balanceLifeTest;
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