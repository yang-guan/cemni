package com.huiju.actment.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ACTIVITY_EXPECTCOST")
public class ExpectCost extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EXPECTCOST_PK")
    @TableGenerator(name = "EXPECTCOST_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "EXPECTCOST_PK", allocationSize = 1)
    private Long expectCostId;// 费用预估Id
    private String actItems;// 项目
    private String actSize;// 规格
    private String actPrice;// 单价
    private Long actCount;// 数量
    private Double budget;// 预算
    private Double actualCost;// 实际花费
    private String remark;// 备注
    private Double applyTotalCost;// 申请费用总金额
    private Double auditCost;// 审核费用总金额

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Double getApplyTotalCost() {
        return applyTotalCost;
    }

    public void setApplyTotalCost(Double applyTotalCost) {
        this.applyTotalCost = applyTotalCost;
    }

    public Double getAuditCost() {
        return auditCost;
    }

    public void setAuditCost(Double auditCost) {
        this.auditCost = auditCost;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Long getExpectCostId() {
        return expectCostId;
    }

    public void setExpectCostId(Long expectCostId) {
        this.expectCostId = expectCostId;
    }

    public String getActItems() {
        return actItems;
    }

    public void setActItems(String actItems) {
        this.actItems = actItems;
    }

    public String getActSize() {
        return actSize;
    }

    public void setActSize(String actSize) {
        this.actSize = actSize;
    }

    public String getActPrice() {
        return actPrice;
    }

    public void setActPrice(String actPrice) {
        this.actPrice = actPrice;
    }

    public Long getActCount() {
        return actCount;
    }

    public void setActCount(Long actCount) {
        this.actCount = actCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
