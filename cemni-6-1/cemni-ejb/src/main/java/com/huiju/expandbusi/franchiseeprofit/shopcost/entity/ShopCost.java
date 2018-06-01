package com.huiju.expandbusi.franchiseeprofit.shopcost.entity;

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
 * 加盟商盈利状况-开店成本
 * 
 * 
 * @author：WangYuanJun
 * @date：2016年12月23日 下午2:54:21
 */
@Entity
@Table(name = "D_EXPANDBUSI_SHOPCOST")
public class ShopCost extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ShopCost_PK")
    @TableGenerator(name = "ShopCost_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ShopCost_PK", allocationSize = 1)
    private Long shopCostId;//number       主键        

    @ManyToOne
    @JoinColumn(name = "franchiseeProfitId", referencedColumnName = "franchiseeProfitId")
    private FranchiseeProfit franchiseeProfit;//number       加盟商盈利状况

    private Double joinfee;//number      加盟费  

    private Double bond;//number      保证金 

    private Double decorationCost;//number      装修费 

    private Double infoCost;//number      信息费  

    private Double propCost;//number      道具费  

    private Double materielCost;//number      物料费

    private Double rent;//number      房租   

    private Double wages;//number      工资  

    private Double activity;//number      活动  

    private Double firstTakeGoods;//number      首批拿货

    private Double cumulativeTakeGoods;//number      累计拿货

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//date         创建时间    

    private String createUser;//varchar2(50) 创建人   

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDate;//date         修改时间   

    private String modifyUser;//varchar2(50) 修改人 

    private Double total; // 总和
    
    public Long getShopCostId() {
        return shopCostId;
    }

    public void setShopCostId(Long shopCostId) {
        this.shopCostId = shopCostId;
    }

    public FranchiseeProfit getFranchiseeProfit() {
        return franchiseeProfit;
    }

    public void setFranchiseeProfit(FranchiseeProfit franchiseeProfit) {
        this.franchiseeProfit = franchiseeProfit;
    }

    public Double getJoinfee() {
        return joinfee;
    }

    public void setJoinfee(Double joinfee) {
        this.joinfee = joinfee;
    }

    public Double getBond() {
        return bond;
    }

    public void setBond(Double bond) {
        this.bond = bond;
    }

    public Double getDecorationCost() {
        return decorationCost;
    }

    public void setDecorationCost(Double decorationCost) {
        this.decorationCost = decorationCost;
    }

    public Double getInfoCost() {
        return infoCost;
    }

    public void setInfoCost(Double infoCost) {
        this.infoCost = infoCost;
    }

    public Double getPropCost() {
        return propCost;
    }

    public void setPropCost(Double propCost) {
        this.propCost = propCost;
    }

    public Double getMaterielCost() {
        return materielCost;
    }

    public void setMaterielCost(Double materielCost) {
        this.materielCost = materielCost;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public Double getActivity() {
        return activity;
    }

    public void setActivity(Double activity) {
        this.activity = activity;
    }

    public Double getFirstTakeGoods() {
        return firstTakeGoods;
    }

    public void setFirstTakeGoods(Double firstTakeGoods) {
        this.firstTakeGoods = firstTakeGoods;
    }

    public Double getCumulativeTakeGoods() {
        return cumulativeTakeGoods;
    }

    public void setCumulativeTakeGoods(Double cumulativeTakeGoods) {
        this.cumulativeTakeGoods = cumulativeTakeGoods;
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