package com.huiju.competitor.entity;

/**
 * 竞争对手渠道调研
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

@Entity(name = "ChannelSurvey")
@Table(name = "D_COMPETITOR_CHANNELRESEARCH")
public class ChannelSurvey extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorCNR_PK")
    @TableGenerator(name = "CompetitorCNR_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorCNR_PK", allocationSize = 1)
    private Long channelSurveyid;// 主键id，渠道调研表id

    private String cnexpand;// 渠道拓展
    private String cpsale;// 竞品销售
    private Long pointdeduction;// 竞品扣点
    private String vevents;// 重大活动/事件
    private String malladjust;// 商场调整
    private String salesadjust;// 拓展人员变动
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

    public Long getChannelSurveyid() {
        return channelSurveyid;
    }

    public void setChannelSurveyid(Long channelSurveyid) {
        this.channelSurveyid = channelSurveyid;
    }

    public String getCnexpand() {
        return cnexpand;
    }

    public void setCnexpand(String cnexpand) {
        this.cnexpand = cnexpand;
    }

    public String getCpsale() {
        return cpsale;
    }

    public void setCpsale(String cpsale) {
        this.cpsale = cpsale;
    }

    public Long getPointdeduction() {
        return pointdeduction;
    }

    public void setPointdeduction(Long pointdeduction) {
        this.pointdeduction = pointdeduction;
    }

    public String getVevents() {
        return vevents;
    }

    public void setVevents(String vevents) {
        this.vevents = vevents;
    }

    public String getMalladjust() {
        return malladjust;
    }

    public void setMalladjust(String malladjust) {
        this.malladjust = malladjust;
    }

    public String getSalesadjust() {
        return salesadjust;
    }

    public void setSalesadjust(String salesadjust) {
        this.salesadjust = salesadjust;
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