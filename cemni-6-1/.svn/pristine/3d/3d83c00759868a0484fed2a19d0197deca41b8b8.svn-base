package com.huiju.competitor.entity;

import java.util.Calendar;
/**
 * 竞争对手公司终端市场活动
 */
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
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity(name = "MarActivitty")
@Table(name = "D_COMPETITOR_MARACTIVITTY")
public class MarActivitty extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorTM_PK")
    @TableGenerator(name = "CompetitorTM_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorTM_PK", allocationSize = 1)
    private Long marActivityid;// 主键id，终端市场活动表id

    private String promoname;// 活动名称

    @Temporal(TemporalType.DATE)
    private Calendar promostart;// 活动开始时间

    @Temporal(TemporalType.DATE)
    private Calendar promoend;// 活动结束时间

    private String promo;// 活动内容
    private String evaluateadvice;// 评估及应对建议
    private String mktstrategy;// 市场策略
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

    public Long getMarActivityid() {
        return marActivityid;
    }

    public void setMarActivityid(Long marActivityid) {
        this.marActivityid = marActivityid;
    }

    public String getPromoname() {
        return promoname;
    }

    public void setPromoname(String promoname) {
        this.promoname = promoname;
    }

    public Calendar getPromostart() {
        return promostart;
    }

    public void setPromostart(Calendar promostart) {
        this.promostart = promostart;
    }

    public Calendar getPromoend() {
        return promoend;
    }

    public void setPromoend(Calendar promoend) {
        this.promoend = promoend;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
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