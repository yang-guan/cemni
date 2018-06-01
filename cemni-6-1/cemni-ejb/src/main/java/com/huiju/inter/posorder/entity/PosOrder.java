package com.huiju.inter.posorder.entity;

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
import javax.persistence.Transient;

import com.huiju.archive.individcust.entity.IndividCust;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_POS_ORDER")
public class PosOrder extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PosOrder_PK")
    @TableGenerator(name = "PosOrder_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "PosOrder_PK", allocationSize = 1)
    private Long posId;

    private Integer custType;// 客户类型：1个人、2团体（字典表3105）
    private String posNo;// varchar2(50)              pos单号
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar posbillDate;// date              pos单据日期
    private String storeNo;// varchar2(20)            门店编码
    private String storeName;// varchar2(100)         门店名称
    private String ncNo;// varchar2(50)               nc卡号
    private String cardNo;// varchar2(50)             crm卡号
    private String cardName;// varchar2(50)           会员姓名
    private Long mobile;// number(11)                 手机号码
    private String lvName;// varchar2(20)             当前会员等级（加入珠宝折算额后计算出的会员等级）：crm根据珠宝折算额判断
    private Integer active; // number(4)              活跃状态
    private Double consumeIntegral;//                 消费积分
    private Double chargeIntegral;//                  抵现积分
    private Integer integralSrc;// number(4)          积分来源

    private String goodsNo;// varchar2(20)            商品编码
    private String goodsName;// varchar2(50)          商品名称
    private String goodsBar;// varchar2(20)           商品条形码
    private Double goodsPrice;// number(8,2)          单价
    private Long goodsCnt;// number                   数量
    private String certificateNo;// varchar2(100)     证书号码
    private Double actualSaleAmount;// number(8,2)    实际销售金额
    private Double discount;// number(8,2)            折扣（实际销售金额/单价）：crm计算获取
    private Double jewelDiscountAmount;// number(8,2) 珠宝折算额
    private Integer scoreSegment;// varchar2(100)     钻石分数（字典表：9801）
    private Integer designerStyle;// number(4)        设计师款（字典表：2200）
    private String designerStyleName;
    private String goodsClassHighestNo;//varchar2(100)商品所属分类最高级编码（文字）
    private String seriesTypeName;// varchar2(100)    系列分类（文字）
    private Integer stockType;// number(4)            存货分类（字典表：9801）

    private String mainclerkName;// varchar2(50)      主营业员姓名
    private String mainclerkPercent;// varchar2(20)   主营业员比例
    private Double mainclerkAmount;// number(8,2)     主营业员金额
    private String assistantName;// varchar2(50)      副营业员姓名
    private String assistantPercent;// varchar2(20)   副营业员比例
    private Double assistantAmount;// number(8,2)     副营业额金额

    private String refCrmCardNo;// varchar2(50)       推荐人CRM卡号
    private String refNcCardNo;// varchar2(50)        推荐人NC卡号
    private Long refMobile;// number(11)              推荐人手机号码
    private Double refGetIntegral;// number(8,2)      推荐人获取积分

    private String actNo;// varchar2(20)              活动编码
    private String subject;// varchar2(50)            活动主题
    private String couponNo;// varchar2(50)           卡卷编码

    private Double anniversaryIntegral;// 纪念日积分倍率
    private String anniversaryEvent;// 纪念日积分活动
    private Integer billType;// 单据类型
    private Integer flargess;// 是否赠品

    @Transient
    private String cardLevelName;
    @Transient
    private String scoreSegmentName;
    @Transient
    private String stockTypeName;
    @Transient
    private String integralSrcName;
    @Transient
    private Double jewelWeight;// 钻石重量
    @Transient
    private String billTypeName;
    @Transient
    private Integer exchangeChance;// 免费调换次数
    @Transient
    private Integer restructureChance;// 免费改款次数

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }

    public Calendar getPosbillDate() {
        return posbillDate;
    }

    public void setPosbillDate(Calendar posbillDate) {
        this.posbillDate = posbillDate;
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

    public String getNcNo() {
        return ncNo;
    }

    public void setNcNo(String ncNo) {
        this.ncNo = ncNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getLvName() {
        return lvName;
    }

    public void setLvName(String lvName) {
        this.lvName = lvName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Double getConsumeIntegral() {
        return consumeIntegral;
    }

    public void setConsumeIntegral(Double consumeIntegral) {
        this.consumeIntegral = consumeIntegral;
    }

    public Double getChargeIntegral() {
        return chargeIntegral;
    }

    public void setChargeIntegral(Double chargeIntegral) {
        this.chargeIntegral = chargeIntegral;
    }

    public Integer getIntegralSrc() {
        return integralSrc;
    }

    public void setIntegralSrc(Integer integralSrc) {
        this.integralSrc = integralSrc;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsBar() {
        return goodsBar;
    }

    public void setGoodsBar(String goodsBar) {
        this.goodsBar = goodsBar;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Long goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Double getActualSaleAmount() {
        return actualSaleAmount;
    }

    public void setActualSaleAmount(Double actualSaleAmount) {
        this.actualSaleAmount = actualSaleAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getJewelDiscountAmount() {
        return jewelDiscountAmount;
    }

    public void setJewelDiscountAmount(Double jewelDiscountAmount) {
        this.jewelDiscountAmount = jewelDiscountAmount;
    }

    public Integer getScoreSegment() {
        return scoreSegment;
    }

    public void setScoreSegment(Integer scoreSegment) {
        this.scoreSegment = scoreSegment;
    }

    public Integer getDesignerStyle() {
        return designerStyle;
    }

    public void setDesignerStyle(Integer designerStyle) {
        this.designerStyle = designerStyle;
    }

    public String getDesignerStyleName() {
        return designerStyleName;
    }

    public void setDesignerStyleName(String designerStyleName) {
        this.designerStyleName = designerStyleName;
    }

    public String getGoodsClassHighestNo() {
        return goodsClassHighestNo;
    }

    public void setGoodsClassHighestNo(String goodsClassHighestNo) {
        this.goodsClassHighestNo = goodsClassHighestNo;
    }

    public String getSeriesTypeName() {
        return seriesTypeName;
    }

    public void setSeriesTypeName(String seriesTypeName) {
        this.seriesTypeName = seriesTypeName;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public String getMainclerkName() {
        return mainclerkName;
    }

    public void setMainclerkName(String mainclerkName) {
        this.mainclerkName = mainclerkName;
    }

    public String getMainclerkPercent() {
        return mainclerkPercent;
    }

    public void setMainclerkPercent(String mainclerkPercent) {
        this.mainclerkPercent = mainclerkPercent;
    }

    public Double getMainclerkAmount() {
        return mainclerkAmount;
    }

    public void setMainclerkAmount(Double mainclerkAmount) {
        this.mainclerkAmount = mainclerkAmount;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getAssistantPercent() {
        return assistantPercent;
    }

    public void setAssistantPercent(String assistantPercent) {
        this.assistantPercent = assistantPercent;
    }

    public Double getAssistantAmount() {
        return assistantAmount;
    }

    public void setAssistantAmount(Double assistantAmount) {
        this.assistantAmount = assistantAmount;
    }

    public String getRefCrmCardNo() {
        return refCrmCardNo;
    }

    public void setRefCrmCardNo(String refCrmCardNo) {
        this.refCrmCardNo = refCrmCardNo;
    }

    public String getRefNcCardNo() {
        return refNcCardNo;
    }

    public void setRefNcCardNo(String refNcCardNo) {
        this.refNcCardNo = refNcCardNo;
    }

    public Long getRefMobile() {
        return refMobile;
    }

    public void setRefMobile(Long refMobile) {
        this.refMobile = refMobile;
    }

    public Double getRefGetIntegral() {
        return refGetIntegral;
    }

    public void setRefGetIntegral(Double refGetIntegral) {
        this.refGetIntegral = refGetIntegral;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Double getAnniversaryIntegral() {
        return anniversaryIntegral;
    }

    public void setAnniversaryIntegral(Double anniversaryIntegral) {
        this.anniversaryIntegral = anniversaryIntegral;
    }

    public String getAnniversaryEvent() {
        return anniversaryEvent;
    }

    public void setAnniversaryEvent(String anniversaryEvent) {
        this.anniversaryEvent = anniversaryEvent;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Integer getFlargess() {
        return flargess;
    }

    public void setFlargess(Integer flargess) {
        this.flargess = flargess;
    }

    public String getCardLevelName() {
        return cardLevelName;
    }

    public void setCardLevelName(String cardLevelName) {
        this.cardLevelName = cardLevelName;
    }

    public String getScoreSegmentName() {
        return scoreSegmentName;
    }

    public void setScoreSegmentName(String scoreSegmentName) {
        this.scoreSegmentName = scoreSegmentName;
    }

    public String getStockTypeName() {
        return stockTypeName;
    }

    public void setStockTypeName(String stockTypeName) {
        this.stockTypeName = stockTypeName;
    }

    public String getIntegralSrcName() {
        return integralSrcName;
    }

    public void setIntegralSrcName(String integralSrcName) {
        this.integralSrcName = integralSrcName;
    }

    public Double getJewelWeight() {
        return jewelWeight;
    }

    public void setJewelWeight(Double jewelWeight) {
        this.jewelWeight = jewelWeight;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public Integer getExchangeChance() {
        return exchangeChance;
    }

    public void setExchangeChance(Integer exchangeChance) {
        this.exchangeChance = exchangeChance;
    }

    public Integer getRestructureChance() {
        return restructureChance;
    }

    public void setRestructureChance(Integer restructureChance) {
        this.restructureChance = restructureChance;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}