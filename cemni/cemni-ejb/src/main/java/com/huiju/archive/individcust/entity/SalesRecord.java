package com.huiju.archive.individcust.entity;

import java.util.Date;

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
 * @author Administrator
 */
@Entity
@Table(name = "D_ARCHIVE_SALESRECORD")
public class SalesRecord extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SALESRECORD_PK")
    @TableGenerator(//
            name = "SALESRECORD_PK", //
            table = "s_pkGenerator", //
            pkColumnName = "PkGeneratorName", //
            valueColumnName = "PkGeneratorValue", //
            pkColumnValue = "SALESRECORD_PK", //
            allocationSize = 1//
    )
    private Long salesRecordId; //主键
    private String posNo;
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    private String commodityCode;
    private String buyGoods;
    private String barCode;
    private Long goodsNum;
    private Double goodsPrice;
    private Double goodsAmount;
    private Long salesStore;
    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;
    private String cardNo;
    private String storeName;
    private String cardLevel;
    
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Long getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(Long salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getBuyGoods() {
        return buyGoods;
    }

    public void setBuyGoods(String buyGoods) {
        this.buyGoods = buyGoods;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Long getSalesStore() {
        return salesStore;
    }

    public void setSalesStore(Long salesStore) {
        this.salesStore = salesStore;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}
