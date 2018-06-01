package com.huiju.competitor.entity;

/**
 * 竞争对手客户调研
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

@Entity(name = "CustSurvey")
@Table(name = "D_COMPETITOR_CUSTRESEARCH")
public class CustSurvey extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorCUR_PK")
    @TableGenerator(name = "CompetitorCUR_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorCUR_PK", allocationSize = 1)
    private Long crid;// 主键id，客户调研表id

    private String aftersaleservice;// 售后服务
    private String memberrights;// 会员权益
    private String promotion;// 促销品
    private String membergrade;// 会员等级（从字典表获取）
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

    public Long getCrid() {
        return crid;
    }

    public void setCrid(Long crid) {
        this.crid = crid;
    }

    public String getAftersaleservice() {
        return aftersaleservice;
    }

    public void setAftersaleservice(String aftersaleservice) {
        this.aftersaleservice = aftersaleservice;
    }

    public String getMemberrights() {
        return memberrights;
    }

    public void setMemberrights(String memberrights) {
        this.memberrights = memberrights;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getMembergrade() {
        return membergrade;
    }

    public void setMembergrade(String membergrade) {
        this.membergrade = membergrade;
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