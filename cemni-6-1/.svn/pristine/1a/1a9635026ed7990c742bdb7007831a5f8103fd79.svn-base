package com.huiju.expandbusi.franchiseeprofit.costsupport.entity;

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
 * 加盟商盈利状况-公司成本支持
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月26日 下午2:43:04
 */
@Entity
@Table(name = "D_EXPANDBUSI_COSTSUPPORT")
public class CostSupport extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CostSupport_PK")
    @TableGenerator(name = "CostSupport_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CostSupport_PK", allocationSize = 1)
    private Long costSupportId;//number      主键    

    @ManyToOne
    @JoinColumn(name = "franchiseeProfitId", referencedColumnName = "franchiseeProfitId")
    private FranchiseeProfit franchiseeProfit;//number       加盟商盈利状况 

    private Double personSupport;//number      人员支持  

    private Double activitySupport;//number      活动支持 

    private Double propSupport;//number      道具支持  

    private String remake;//varchar2(50)备注     

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人 

    private Double total; // 总和
    
    public Long getCostSupportId() {
        return costSupportId;
    }

    public void setCostSupportId(Long costSupportId) {
        this.costSupportId = costSupportId;
    }

    public FranchiseeProfit getFranchiseeProfit() {
        return franchiseeProfit;
    }

    public void setFranchiseeProfit(FranchiseeProfit franchiseeProfit) {
        this.franchiseeProfit = franchiseeProfit;
    }

    public Double getPersonSupport() {
        return personSupport;
    }

    public void setPersonSupport(Double personSupport) {
        this.personSupport = personSupport;
    }

    public Double getActivitySupport() {
        return activitySupport;
    }

    public void setActivitySupport(Double activitySupport) {
        this.activitySupport = activitySupport;
    }

    public Double getPropSupport() {
        return propSupport;
    }

    public void setPropSupport(Double propSupport) {
        this.propSupport = propSupport;
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
