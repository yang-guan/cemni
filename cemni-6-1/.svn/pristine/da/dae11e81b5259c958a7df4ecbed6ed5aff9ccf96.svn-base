package com.huiju.inter.afterserv.entity;

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

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_AFTER_SERV")
public class AfterServ extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AfterServ_PK")
    @TableGenerator(name = "AfterServ_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "AfterServ_PK", allocationSize = 1)
    private Long afterservId;

    private String cardNo;// varchar2(20)     会员卡号
    private String goodsBar;// varchar2(20)   商品条码    
    private String goodsName;// varchar2(100) 商品名称
    private String servicePro;// varchar2(100)服务项目
    private String oldNo;// varchar2(50)      原号（圈号）
    private String newNo;// varchar2(50)      改号（圈号）

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startDay;// date         服务开始时间
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endDay;// date           服务结束时间

    private String storeNo;// number          服务门店
    private String storeName;// varchar2(100) 服务门店名称

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    public Long getAfterservId() {
        return afterservId;
    }

    public void setAfterservId(Long afterservId) {
        this.afterservId = afterservId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGoodsBar() {
        return goodsBar;
    }

    public void setGoodsBar(String goodsBar) {
        this.goodsBar = goodsBar;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getServicePro() {
        return servicePro;
    }

    public void setServicePro(String servicePro) {
        this.servicePro = servicePro;
    }

    public String getOldNo() {
        return oldNo;
    }

    public void setOldNo(String oldNo) {
        this.oldNo = oldNo;
    }

    public String getNewNo() {
        return newNo;
    }

    public void setNewNo(String newNo) {
        this.newNo = newNo;
    }

    public Calendar getStartDay() {
        return startDay;
    }

    public void setStartDay(Calendar startDay) {
        this.startDay = startDay;
    }

    public Calendar getEndDay() {
        return endDay;
    }

    public void setEndDay(Calendar endDay) {
        this.endDay = endDay;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}