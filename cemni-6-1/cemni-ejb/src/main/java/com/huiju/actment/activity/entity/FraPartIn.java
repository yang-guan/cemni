package com.huiju.actment.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY_FRAPARTIN")
public class FraPartIn extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FRAPARTIN_PK")
    @TableGenerator(name = "FRAPARTIN_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "FRAPARTIN_PK", allocationSize = 1)
    private Long fraPartInId;

    private Integer isPartIn;// 是否参与

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;

    public Long getFraPartInId() {
        return fraPartInId;
    }

    public void setFraPartInId(Long fraPartInId) {
        this.fraPartInId = fraPartInId;
    }

    public Integer getIsPartIn() {
        return isPartIn;
    }

    public void setIsPartIn(Integer isPartIn) {
        this.isPartIn = isPartIn;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

}