package com.huiju.common.File.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "S_FILEGROUPNEW")
public class NFileGroup extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FileGroupNew_PK")
    @TableGenerator(name = "FileGroupNew_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "FileGroupNew_PK", allocationSize = 1)
    private Long fileGroupId;
    private String remark;
    private Long createUserId;
    private String createUserName;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;
    @OneToMany(mappedBy = "fileGroup")
    private List<NFileInfo> fileInfos;

    public NFileGroup() {
    }

    public NFileGroup(Long fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public Long getFileGroupId() {
        return this.fileGroupId;
    }

    public void setFileGroupId(Long fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Calendar getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public List<NFileInfo> getFileInfos() {
        return this.fileInfos;
    }

    public void setFileInfos(List<NFileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

}
