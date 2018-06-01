package com.huiju.archive.franchisee.entity;

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

@Entity(name = "Team")
@Table(name = "D_FRANCHISEE_TEAM")
public class Team extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TEAM_PK")
    @TableGenerator(name = "TEAM_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "TEAM_PK", allocationSize = 1)
    private Long teamId;

    private String name;// 门店名称
    private String tname;// 姓名
    private String duty;// 岗位名称
    private Long mobile;// 手机号码
    private Integer joinWay;// 是否自招

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar joinTime;// 入职时间

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar outTime;// 离职时间

    private Integer free;// 是否托管    
    private String remark;// 备注

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;

    @Transient
    private String joinWayName;
    @Transient
    private String freeName;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(Integer joinWay) {
        this.joinWay = joinWay;
    }

    public Calendar getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Calendar joinTime) {
        this.joinTime = joinTime;
    }

    public Calendar getOutTime() {
        return outTime;
    }

    public void setOutTime(Calendar outTime) {
        this.outTime = outTime;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

    public String getJoinWayName() {
        return joinWayName;
    }

    public void setJoinWayName(String joinWayName) {
        this.joinWayName = joinWayName;
    }

    public String getFreeName() {
        return freeName;
    }

    public void setFreeName(String freeName) {
        this.freeName = freeName;
    }

}