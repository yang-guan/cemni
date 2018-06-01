package com.huiju.competitor.entity;

/**
 * 竞争对手公司人事信息
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

@Entity(name = "PersonnelInfo")
@Table(name = "D_COMPETITOR_PERSONNELINFO")
public class PersonnelInfo extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorEP_PK")
    @TableGenerator(name = "CompetitorEP_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorEP_PK", allocationSize = 1)
    private Long infoid;// 主键id，竞争对手公司人事信息表id

    private String job;// 竞争对手公司岗位
    private String commissionway;// 提成方式
    private Double salespermonth;// 月销售额
    private Long percentage;// 提点系数
    private Double basicsalary;// 竞争对手公司底薪
    private Double avgsalary;// 月综合收入
    private Double yearendaward;// 年终奖
    private String otherprofits;// 其他福利
    private Double socialinsurance;// 社保公积金
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

    public Long getInfoid() {
        return infoid;
    }

    public void setInfoid(Long infoid) {
        this.infoid = infoid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCommissionway() {
        return commissionway;
    }

    public void setCommissionway(String commissionway) {
        this.commissionway = commissionway;
    }

    public Double getSalespermonth() {
        return salespermonth;
    }

    public void setSalespermonth(Double salespermonth) {
        this.salespermonth = salespermonth;
    }

    public Double getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(Double basicsalary) {
        this.basicsalary = basicsalary;
    }

    public Double getAvgsalary() {
        return avgsalary;
    }

    public void setAvgsalary(Double avgsalary) {
        this.avgsalary = avgsalary;
    }

    public Double getYearendaward() {
        return yearendaward;
    }

    public void setYearendaward(Double yearendaward) {
        this.yearendaward = yearendaward;
    }

    public Double getSocialinsurance() {
        return socialinsurance;
    }

    public void setSocialinsurance(Double socialinsurance) {
        this.socialinsurance = socialinsurance;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public String getOtherprofits() {
        return otherprofits;
    }

    public void setOtherprofits(String otherprofits) {
        this.otherprofits = otherprofits;
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