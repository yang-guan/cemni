package com.huiju.expandbusi.franchiseeprofit.profit.entity;

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
 * 加盟商盈利状况-盈利
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月26日 下午2:37:41
 */
@Entity
@Table(name = "D_EXPANDBUSI_PROFIT")
public class Profit extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Profit_PK")
    @TableGenerator(name = "Profit_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Profit_PK", allocationSize = 1)
    private Long profitId;//number      主键         

    @ManyToOne
    @JoinColumn(name = "franchiseeProfitId", referencedColumnName = "franchiseeProfitId")
    private FranchiseeProfit franchiseeProfit;//number       加盟商盈利状况   

    private Double profit;//number      盈利   

    private Double roi;//number      roi  

    private String remake;//varchar2(50)备注   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人  

    public Long getProfitId() {
        return profitId;
    }

    public void setProfitId(Long profitId) {
        this.profitId = profitId;
    }

    public FranchiseeProfit getFranchiseeProfit() {
        return franchiseeProfit;
    }

    public void setFranchiseeProfit(FranchiseeProfit franchiseeProfit) {
        this.franchiseeProfit = franchiseeProfit;
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
