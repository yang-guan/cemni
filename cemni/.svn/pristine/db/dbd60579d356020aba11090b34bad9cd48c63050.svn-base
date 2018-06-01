package com.huiju.common.File.entity;

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

import com.huiju.module.data.BaseEntity;
import com.huiju.module.fs.StorageType;

@Entity
@Table(name = "S_FILEINFONEW")
public class NFileInfo extends BaseEntity<Long>{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="FileInfoNew_PK")
    @TableGenerator(name="FileInfoNew_PK", table="s_pkGenerator", pkColumnName="PkGeneratorName", valueColumnName="PkGeneratorValue", pkColumnValue="FileInfoNew_PK", allocationSize=1)
    private Long fileInfoId;
    private String fileCode;
    private String fileName;
    private String fileExtension;
    private String properties;
    private String storageType;
    private byte[] fileStorage;
    public NFileGroup getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(NFileGroup fileGroup) {
        this.fileGroup = fileGroup;
    }

    private String remark;
    private Long createUserId;
    private String createUserName;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;
    @ManyToOne
    @JoinColumn(name="FILEGROUPID")
    private NFileGroup fileGroup;
    
    public Long getFileInfoId()
    {
      return this.fileInfoId;
    }
    
    public void setFileInfoId(Long fileInfoId) {
      this.fileInfoId = fileInfoId;
    }
    
    public String getFileCode() {
      return this.fileCode;
    }
    
    public void setFileCode(String fileCode) {
      this.fileCode = fileCode;
    }
    
    public String getFileName() {
      return this.fileName;
    }
    
    public void setFileName(String fileName) {
      this.fileName = fileName;
    }
    
    public String getFileExtension() {
      return this.fileExtension;
    }
    
    public void setFileExtension(String fileExtension) {
      this.fileExtension = fileExtension;
    }
    
    public String getProperties() {
      return this.properties;
    }
    
    public void setProperties(String properties) {
      this.properties = properties;
    }
    
    public String getRemark() {
      return this.remark;
    }
    
    public void setRemark(String remark) {
      this.remark = remark;
    }
    
    public StorageType getStorageType() {
      return this.storageType != null ? StorageType.valueOf(this.storageType) : null;
    }
    
    public void setStorageType(StorageType storageType) {
      this.storageType = (storageType != null ? storageType.name() : null);
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

    public byte[] getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(byte[] fileStorage) {
        this.fileStorage = fileStorage;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
    
    
}
