package com.huiju.common.File.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import com.huiju.common.File.entity.NFileGroup;
import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.fs.FileItem;
import com.huiju.module.fs.StorageType;

public class NFileItemImpl implements FileItem, Serializable {
    private static final long serialVersionUID = 1L;
    private NFileInfo fileInfo;
    private File file;

    public NFileItemImpl() {
    }

    public NFileItemImpl(NFileInfo fileInfo, File file) {
        this.fileInfo = fileInfo;
        this.file = file;
    }

    public Long getFileId() {
        return this.fileInfo.getFileInfoId();
    }

    public Long getGroupId() {
        NFileGroup group = this.fileInfo.getFileGroup();
        return group != null ? group.getFileGroupId() : null;
    }

    public String getFileName() {
        return this.fileInfo.getFileName();
    }

    public StorageType getStorageType() {
        return this.fileInfo.getStorageType();
    }

    public String getExtension() {
        return this.fileInfo.getFileExtension();
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(getFile());
    }

    public File getFile() throws IOException {
        return this.file;
    }

    public NFileInfo getFileInfo() {
        return this.fileInfo;
    }

    public void setFileInfo(NFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

}
