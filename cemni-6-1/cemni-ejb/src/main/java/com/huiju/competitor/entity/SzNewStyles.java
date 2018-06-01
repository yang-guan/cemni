package com.huiju.competitor.entity;

import java.util.Calendar;
/**
 * 竞争对手深圳新工艺款式动态
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

@Entity(name = "SzNewStyles")
@Table(name = "D_COMPETITOR_SZNEWSTYLES")
public class SzNewStyles extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CompetitorSZNS_PK")
    @TableGenerator(name = "CompetitorSZNS_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "CompetitorSZNS_PK", allocationSize = 1)
    private Long cpszid;// 竞争对手深圳新工艺款式动态主键id

    private String szcategory;// 品类
    private String sztech;// 工艺

    @Temporal(TemporalType.DATE)
    private Calendar sztimetomkt;// 上市时间
    private String szwholesalepolicy;// 批发政策
    private String szsalestatus;// 零售动态
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

    public Long getCpszid() {
        return cpszid;
    }

    public void setCpszid(Long cpszid) {
        this.cpszid = cpszid;
    }

    public String getSzcategory() {
        return szcategory;
    }

    public void setSzcategory(String szcategory) {
        this.szcategory = szcategory;
    }

    public String getSztech() {
        return sztech;
    }

    public void setSztech(String sztech) {
        this.sztech = sztech;
    }

    public Calendar getSztimetomkt() {
        return sztimetomkt;
    }

    public void setSztimetomkt(Calendar sztimetomkt) {
        this.sztimetomkt = sztimetomkt;
    }

    public String getSzwholesalepolicy() {
        return szwholesalepolicy;
    }

    public void setSzwholesalepolicy(String szwholesalepolicy) {
        this.szwholesalepolicy = szwholesalepolicy;
    }

    public String getSzsalestatus() {
        return szsalestatus;
    }

    public void setSzsalestatus(String szsalestatus) {
        this.szsalestatus = szsalestatus;
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