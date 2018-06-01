package com.huiju.actment.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.archive.partner.entity.Partner;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY_PARPARTIN")
public class ParPartIn extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PARPARTIN_PK")
    @TableGenerator(name = "PARPARTIN_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "PARPARTIN_PK", allocationSize = 1)
    private Long parPartInId;
    private Integer isPartIn;

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "partnerid", referencedColumnName = "partnerid")
    private Partner partner;

    public Long getParPartInId() {
        return parPartInId;
    }

    public void setParPartInId(Long parPartInId) {
        this.parPartInId = parPartInId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Integer getIsPartIn() {
        return isPartIn;
    }

    public void setIsPartIn(Integer isPartIn) {
        this.isPartIn = isPartIn;
    }

}