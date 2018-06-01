package com.huiju.competitor.entity;

/**
 * 竞争对手商品动态
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity(name = "GoodStatus")
@Table(name = "D_COMPETITOR_GOODSTATUS")
public class GoodStatus extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorGS_PK")
    @TableGenerator(name = "CompetitorGS_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorGS_PK", allocationSize = 1)
    private Long cpgsid;// 竞争对手商品动态表主键id

    private String pdseries;// 产品系列
    private String pdcategory;// 品类
    private String pdgrade;// 级别
    private Long pddistriamo;// 铺货量
    private String jewelweight;// 钻石重量
    private Double pdprice;// 价格
    private String comments;// 备注
    private String pdspanalysis;// 卖点分析
    private String evaluatestrategy;// 评估及应对
    private Long uploadFileGroupId;// 附件

    @Transient
    private String[] uploadFile4View;
    @ManyToOne
    @JoinColumn(name = "cpid", referencedColumnName = "cpid")
    private Competitor competitor;

    public String getJewelweightsssss() {
        return jewelweight;
    }

    public void setJewelweightsssss(String jewelweight) {
        this.jewelweight = jewelweight;
    }

    public String[] getUploadFile4View() {
        return uploadFile4View;
    }

    public void setUploadFile4View(String[] uploadFile4View) {
        this.uploadFile4View = uploadFile4View;
    }

    public Long getCpgsid() {
        return cpgsid;
    }

    public void setCpgsid(Long cpgsid) {
        this.cpgsid = cpgsid;
    }

    public String getPdseries() {
        return pdseries;
    }

    public void setPdseries(String pdseries) {
        this.pdseries = pdseries;
    }

    public String getPdcategory() {
        return pdcategory;
    }

    public void setPdcategory(String pdcategory) {
        this.pdcategory = pdcategory;
    }

    public String getPdgrade() {
        return pdgrade;
    }

    public void setPdgrade(String pdgrade) {
        this.pdgrade = pdgrade;
    }

    public Long getPddistriamo() {
        return pddistriamo;
    }

    public void setPddistriamo(Long pddistriamo) {
        this.pddistriamo = pddistriamo;
    }

    public Double getPdprice() {
        return pdprice;
    }

    public void setPdprice(Double pdprice) {
        this.pdprice = pdprice;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPdspanalysis() {
        return pdspanalysis;
    }

    public void setPdspanalysis(String pdspanalysis) {
        this.pdspanalysis = pdspanalysis;
    }

    public String getEvaluatestrategy() {
        return evaluatestrategy;
    }

    public void setEvaluatestrategy(String evaluatestrategy) {
        this.evaluatestrategy = evaluatestrategy;
    }

    public Long getUploadFileGroupId() {
        return uploadFileGroupId;
    }

    public void setUploadFileGroupId(Long uploadFileGroupId) {
        this.uploadFileGroupId = uploadFileGroupId;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

}