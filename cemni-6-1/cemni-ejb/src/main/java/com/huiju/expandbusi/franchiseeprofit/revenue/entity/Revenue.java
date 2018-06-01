package com.huiju.expandbusi.franchiseeprofit.revenue.entity;

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

import com.huiju.expandbusi.franchiseeprofit.franchiseeprofit.entity.FranchiseeProfit;
import com.huiju.module.data.BaseEntity;

/**
 * 加盟商盈利状况-收入
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月26日 下午2:35:14
 */
@Entity
@Table(name = "D_EXPANDBUSI_REVENUE")
public class Revenue extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Revenue_PK")
    @TableGenerator(name = "Revenue_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Revenue_PK", allocationSize = 1)
    private Long revenueId;//number      主键     

    @ManyToOne
    @JoinColumn(name = "franchiseeProfitId", referencedColumnName = "franchiseeProfitId")
    private FranchiseeProfit franchiseeProfit;//number       加盟商盈利状况   

    private Double sale;//number      销售      

    private Double rebate;//number      返利   

    private String remake;//varchar2(50)备注  

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人    

    private Double total; // 总和
    
    public Long getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Long revenueId) {
        this.revenueId = revenueId;
    }

    public FranchiseeProfit getFranchiseeProfit() {
        return franchiseeProfit;
    }

    public void setFranchiseeProfit(FranchiseeProfit franchiseeProfit) {
        this.franchiseeProfit = franchiseeProfit;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Double getRebate() {
        return rebate;
    }

    public void setRebate(Double rebate) {
        this.rebate = rebate;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}