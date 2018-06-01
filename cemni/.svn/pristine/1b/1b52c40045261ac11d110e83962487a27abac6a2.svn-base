package com.huiju.common.File.logic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import com.huiju.common.File.eao.NFileGroupEaoLocal;
import com.huiju.common.File.eao.NFileInfoEaoLocal;
import com.huiju.common.File.entity.NFileGroup;
import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.config.Param;
import com.huiju.module.config.ParamManager;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.module.fs.FileItem;
import com.huiju.module.fs.FileMetadata;
import com.huiju.module.fs.FileStorage;
import com.huiju.module.fs.FileStorageFactory;
import com.huiju.module.fs.FileStorageMetadata;
import com.huiju.module.fs.StorageType;
import com.huiju.module.fs.support.FileStorageFactoryBean;
import com.huiju.module.util.Coder;

@Stateless(mappedName = "NFileInfoBean")
public class NFileInfoBean extends GenericLogicImpl<NFileInfo, Long> implements NFileInfoRemote {
    
    @EJB(mappedName="NFileInfoEaoBean")
    private NFileInfoEaoLocal fileInfoEao;
    
    @EJB(mappedName="NFileGroupEaoBean")
    private NFileGroupEaoLocal fileGroupEao;
    
    @EJB(mappedName = "SysParamBean")
    private ParamManager<? extends Param> paramManager;
    private FileStorageFactory fileStorageFactory;
    private NConverterToFileInfo storageMetadataToFileInfoConverter = new NConverterToFileInfo();

    private NConverterToFileStorageMetadata fileInfoConverterToStorageMetadata = new NConverterToFileStorageMetadata();

    private StorageType storageType = StorageType.SERVER;

    private boolean ignoreNotExists = true;

    @PostConstruct
    public void init() {
        this.fileStorageFactory = new FileStorageFactoryBean(this.paramManager);
    }

    protected GenericEao<NFileInfo, Long> getGenericEao() {
        return this.fileInfoEao;
    }

    public NFileInfo findByCode(String code) {
        Map<String, Object> param = new HashMap();
        param.put("EQ_fileCode", code);
        return (NFileInfo) find(param);
    }

    public NFileInfo upload(File file) throws IOException {
        return upload(file.getName(), file, this.storageType, null);
    }

    public NFileInfo upload(File file, StorageType storageType) throws IOException {
        return upload(file.getName(), file, storageType, null);
    }

    public NFileInfo upload(String fileName, File file) throws IOException {
        return upload(fileName, file, this.storageType, null);
    }

    public NFileInfo upload(String fileName, File file, StorageType storageType) throws IOException {
        return upload(fileName, file, storageType, null);
    }

    public NFileInfo upload(File file, Long groupId) throws IOException {
        return upload(file.getName(), file, this.storageType, groupId);
    }

    public NFileInfo upload(File file, StorageType storageType, Long groupId) throws IOException {
        return upload(file.getName(), file, storageType, groupId);
    }

    public NFileInfo upload(String fileName, File file, Long groupId) throws IOException {
        return upload(fileName, file, this.storageType, groupId);
    }

    public NFileInfo upload(String fileName, File file, StorageType storageType, Long groupId) throws IOException {
        FileStorage fileStorage = this.fileStorageFactory.getFileStorage(storageType);
        try {
            FileStorageMetadata storageMetadata = fileStorage.putFile(new FileMetadata(fileName, file));
            NFileInfo fileInfo = (NFileInfo) this.storageMetadataToFileInfoConverter.convert(storageMetadata);
            if (groupId == null) {
                NFileGroup fileGroup = (NFileGroup) this.fileGroupEao.persist(new NFileGroup());
                fileInfo.setFileGroup(fileGroup);
            } else {
                fileInfo.setFileGroup(new NFileGroup(groupId));
            }
            fileInfo.setFileCode(encodeFileCode(fileInfo));
            
            
            InputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            out.toByteArray();
            
            fileInfo.setFileStorage(out.toByteArray());
            
            return (NFileInfo) persist(fileInfo);
        } catch (IOException e) {
            throw new EJBException(e);
        }
    }

    protected String encodeFileCode(NFileInfo fileInfo) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(fileInfo.getFileName()).append(fileInfo.getStorageType()).append(fileInfo.getProperties()).append(fileInfo.getCreateUserId()).append(fileInfo.getCreateTime() == null ? null : Long.valueOf(fileInfo.getCreateTime().getTimeInMillis())).append(fileInfo.getCreateUserName()).append(fileInfo.getRemark());
        try {
            return Coder.encryptMD5(sb.toString());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public FileItem convert(NFileInfo fileInfo) throws IOException {
        FileStorage fileStorage = this.fileStorageFactory.getFileStorage(fileInfo.getStorageType());
        FileStorageMetadata storageMetadata = (FileStorageMetadata) this.fileInfoConverterToStorageMetadata.convert(fileInfo);
        File file = fileStorage.getFile(storageMetadata);
        return new NFileItemImpl(fileInfo, file);
    }

    public FileItem download(Long fileId) throws IOException {
        NFileInfo fileInfo = (NFileInfo) findById(fileId);
        if (fileInfo == null) {
            throw new IOException(new StringBuilder().append("file not found, fileId ").append(fileId).toString());
        }
        return convert(fileInfo);
    }

    public FileItem download(String fileCode) throws IOException {
        NFileInfo fileInfo = findByCode(fileCode);
        if (fileInfo == null) {
            throw new IOException(new StringBuilder().append("file not found, fileCode ").append(fileCode).toString());
        }
        return convert(fileInfo);
    }

    public File downloadFile(Long fileId) throws IOException {
        return download(fileId).getFile();
    }

    public File downloadFile(String fileCode) throws IOException {
        return download(fileCode).getFile();
    }

    public void group(Long groupId, Long[] items) {
        group(new NFileGroup(groupId), items);
    }

    public void group(NFileGroup fileGroup, Long... items) {
        for (Long id : items) {
            NFileInfo fileInfo = (NFileInfo) findById(id);
            fileInfo.setFileGroup(fileGroup);
            merge(fileInfo);
        }
    }

    public void group(Long groupId, NFileInfo... items) {
        group(new NFileGroup(groupId), items);
    }

    public void group(NFileGroup fileGroup, NFileInfo... items) {
        for (NFileInfo item : items) {
            item.setFileGroup(fileGroup);
            merge(item);
        }
    }

    public void delete(String... fileCode) throws IOException {
        delete(fileCode, this.ignoreNotExists);
    }

    public void delete(Long... fileIds) throws IOException {
        delete(fileIds, this.ignoreNotExists);
    }

    public void delete(NFileInfo... fileInfos) throws IOException {
        for (NFileInfo fileInfo : fileInfos) {
            FileStorage fileStorage = this.fileStorageFactory.getFileStorage(fileInfo.getStorageType());
            FileStorageMetadata storageMetadata = (FileStorageMetadata) this.fileInfoConverterToStorageMetadata.convert(fileInfo);
            fileStorage.deleteFile(storageMetadata);
            remove(fileInfo);
        }
    }

    public void delete(String[] codes, boolean ignoreNotExists) throws IOException {
        for (String code : codes) {
            NFileInfo fileInfo = findByCode(code);
            if ((fileInfo == null) && (!ignoreNotExists)) {
                throw new IOException(new StringBuilder().append("file not found, fileCode ").append(code).toString());
            }
            delete(new NFileInfo[] { fileInfo });
        }
    }

    public void delete(Long[] ids, boolean ignoreNotExists) throws IOException {
        for (Long id : ids) {
            NFileInfo fileInfo = (NFileInfo) findById(id);
            if ((fileInfo == null) && (!ignoreNotExists)) {
                throw new IOException(new StringBuilder().append("file not exists, fileid ").append(id).toString());
            }
            delete(new NFileInfo[] { fileInfo });
        }
    }





    public NConverterToFileInfo getStorageMetadataToFileInfoConverter() {
        return storageMetadataToFileInfoConverter;
    }

    public void setStorageMetadataToFileInfoConverter(NConverterToFileInfo storageMetadataToFileInfoConverter) {
        this.storageMetadataToFileInfoConverter = storageMetadataToFileInfoConverter;
    }

    public NConverterToFileStorageMetadata getFileInfoConverterToStorageMetadata() {
        return fileInfoConverterToStorageMetadata;
    }

    public void setFileInfoConverterToStorageMetadata(NConverterToFileStorageMetadata fileInfoConverterToStorageMetadata) {
        this.fileInfoConverterToStorageMetadata = fileInfoConverterToStorageMetadata;
    }

    public boolean isIgnoreNotExists() {
        return this.ignoreNotExists;
    }

    public void setIgnoreNotExists(boolean ignoreNotExists) {
        this.ignoreNotExists = ignoreNotExists;
    }
}
