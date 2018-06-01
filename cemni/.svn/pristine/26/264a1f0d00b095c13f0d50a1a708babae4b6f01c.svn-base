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
@Table(name = "D_ACTIVITY_GIVE")
public class ActGive extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INV_PK")
    @TableGenerator(name = "INV_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INV_PK", allocationSize = 1)
    private Long invId;

    private String invCode;// 赠送商品编码
    private String invName;// 赠送商品名称
    private Integer giveNum;// 赠送数量
    private Integer nprice;// 单价

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public Integer getGiveNum() {
        return giveNum;
    }

    public void setGiveNum(Integer giveNum) {
        this.giveNum = giveNum;
    }

    public Integer getNprice() {
        return nprice;
    }

    public void setNprice(Integer nprice) {
        this.nprice = nprice;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}