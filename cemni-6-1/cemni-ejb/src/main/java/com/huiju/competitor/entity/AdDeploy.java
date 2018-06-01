package com.huiju.competitor.entity;

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

import com.huiju.module.data.BaseEntity;

/**
 * 竞争对手广告投放页签
 */

@Entity(name = "AdDeploy")
@Table(name = "D_COMPETITOR_ADDEPLOY")
public class AdDeploy extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorAD_PK")
    @TableGenerator(name = "CompetitorAD_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorAD_PK", allocationSize = 1)
    private Long cpadid;// 主键id，广告投放表id

    private String adtype;// 广告形式

    @Temporal(TemporalType.DATE)
    private Calendar adstart;// 投放开始时间

    @Temporal(TemporalType.DATE)
    private Calendar adend;// 投放结束时间

    private Double adcost;// 广告投放费用
    private String evaluateadvice;// 评估及应对建议
    private String mktstrategy;// 市场策略
    private String comments;// 备注
    private Long uploadFileGroupId;// 附件

    @Transient
    private String[] uploadFile4View;
    @ManyToOne
    @JoinColumn(name = "cpid", referencedColumnName = "cpid")
    private Competitor competitor;

    public String[] getUploadFile4View() {
        return uploadFile4View;
    }

    public void setUploadFile4View(String[] uploadFile4View) {
        this.uploadFile4View = uploadFile4View;
    }

    public Long getCpadid() {
        return cpadid;
    }

    public void setCpadid(Long cpadid) {
        this.cpadid = cpadid;
    }

    public String getAdtype() {
        return adtype;
    }

    public void setAdtype(String adtype) {
        this.adtype = adtype;
    }

    public Calendar getAdstart() {
        return adstart;
    }

    public void setAdstart(Calendar adstart) {
        this.adstart = adstart;
    }

    public Calendar getAdend() {
        return adend;
    }

    public void setAdend(Calendar adend) {
        this.adend = adend;
    }

    public Double getAdcost() {
        return adcost;
    }

    public void setAdcost(Double adcost) {
        this.adcost = adcost;
    }

    public String getEvaluateadvice() {
        return evaluateadvice;
    }

    public void setEvaluateadvice(String evaluateadvice) {
        this.evaluateadvice = evaluateadvice;
    }

    public String getMktstrategy() {
        return mktstrategy;
    }

    public void setMktstrategy(String mktstrategy) {
        this.mktstrategy = mktstrategy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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