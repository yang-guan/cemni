package com.huiju.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.huiju.common.File.entity.NFileInfo;
import com.huiju.common.File.logic.NFileInfoRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.FileItem;
import com.huiju.module.fs.StorageType;
import com.huiju.module.web.action.BaseAction;

/**
 * 附件上传
 * 
 * @author：WangYuanJun
 * @date：2016年12月22日 上午10:46:35
 */
public class StdFileAction<T, K> extends BaseAction<NFileInfo, Long> {

    private static final long serialVersionUID = 1L;

    protected String fileCode;
    protected File myUpload;
    protected String myUploadContentType;
    protected String myUploadFileName;
    protected String uploadFileName;
    protected StorageType storageType;

    protected Long fileGroupId;
    protected List<String> fileCodes;
    protected List<String> fileIds;

    @EJB(mappedName = "")
    protected NFileInfoRemote nfileInfoLogic;
    public String list() {
        return LIST;
    }

    public String getJson() throws UnsupportedEncodingException {
        Page<NFileInfo> page = nfileInfoLogic.findAll(new Page<NFileInfo>(start, limit, sort, dir));
        return renderJson(page, "properties", "fileGroup.fileInfos");
    }

    public String getAll() {
        try {
            Map<String, Object> searchParams = new HashMap<String, Object>();
            searchParams.put("EQ_fileGroup_fileGroupId", fileGroupId);
            List<NFileInfo> list = nfileInfoLogic.findAll(searchParams);
            StringBuffer json = new StringBuffer("{success:true,rows:[");
            // 以employee为例解析，map类似
            for (int i = 0; i < list.size(); i++) {
                NFileInfo uf = list.get(i);
                //JSONObject jb = JSONObject.parseObject(uf.getProperties());
                /*json.append("{id:'").append(i + 1).append("',");
                json.append("fileId:'").append(uf.getFileInfoId()).append("',");
                json.append("fileSize:'").append(jb.getIntValue("contentLength")).append("',");
                json.append("fileCode:'").append(uf.getFileCode()).append("',");
                json.append("fileName:'").append(uf.getFileName()).append("',");
                json.append("fileType:'").append(uf.getFileExtension()).append("',");
                json.append("fileState:-4}");
                if (i < list.size() - 1) {
                    json.append(",");
                }*/
                json.append("{fileId:'").append(uf.getFileInfoId()).append("',");
                json.append("fileCode:'").append(uf.getFileCode()).append("',");
                json.append("fileName:'").append(uf.getFileName()).append("',");
                json.append("note:'").append("成功").append("',");
                json.append("state:1}");
                if (i < list.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]}");
            renderJson(json.toString(), new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String upload() {
        if (storageType == null) {
            storageType = StorageType.SERVER;
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            //            FileInfo fileInfo = fileInfoLogic.upload(myUploadFileName, myUpload, storageType, fileGroupId);
            NFileInfo fileInfo = nfileInfoLogic.upload(myUploadFileName, myUpload, storageType, fileGroupId);
            map.put("success", true);
            map.put("fileInfo", fileInfo);
        } catch (IOException e) {
            map.put("success", false);
        }
        // use file upload form must return contentType with text/html
        // see
        // ext-api@http://192.168.1.22/ext-3.4/docs/#!/api/Ext.form.BasicForm-cfg-fileUpload
        renderHtml(DataUtils.toJson(map, "fileInfo.fileGroup.fileInfos", "fileInfo.properties", "**.id"));
        return NONE;
    }

    public String download() {
        if (StringUtils.isBlank(fileCode)) {
            return dealJson(false, "file code not found");
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("EQ_fileCode", fileCode);
            NFileInfo nFileInfo = nfileInfoLogic.find(param);
            byte[] bs = nFileInfo.getFileStorage();
            in = new ByteArrayInputStream(bs);
            File file = new File(nFileInfo.getFileName());
            out = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }

            renderFile(nFileInfo.getFileName(), file);
        } catch (IOException e) {
            return dealJson(false, e.toString());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return NONE;
    }

    public String delete() {
        Map<String, Object> params = new HashMap<String, Object>();
        String stringFileIds = StringUtils.join(this.fileIds.toArray(), ",");
        params.put("IN_fileInfoId", stringFileIds);
        List<NFileInfo> fileInfos = nfileInfoLogic.findAll(params);
        try {
            nfileInfoLogic.delete(fileInfos.toArray(new NFileInfo[fileInfos.size()]));
        } catch (IOException e) {
            return dealJson(false, e);
        }
        return dealJson(true);
    }

    /**
     * 预览图片附件
     * 
     * @return
     */
    public String viewImageFile() {
        if (StringUtils.isBlank(fileCode)) {
            return dealJson(false, "file code not found");
        }
        FileItem fileItem = null;
        Map<String, String> headers = new HashMap<String, String>();
        try {
            headers.put("Content-Type", "image/jpeg");
            fileItem = nfileInfoLogic.download(fileCode);
            renderFile(fileItem.getFileName(), fileItem.getFile(), true, headers);
        } catch (IOException e) {
            return dealJson(false, e.toString());
        }
        return NONE;
    }

    public Long getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(Long fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public List<String> getFileCodes() {
        return fileCodes;
    }

    public void setFileCodes(List<String> fileCodes) {
        this.fileCodes = fileCodes;
    }

    public String getMyUploadFileName() {
        return myUploadFileName;
    }

    public void setMyUploadFileName(String myUploadFileName) {
        this.myUploadFileName = myUploadFileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public File getMyUpload() {
        return myUpload;
    }

    public void setMyUpload(File myUpload) {
        this.myUpload = myUpload;
    }

    public String getMyUploadContentType() {
        return myUploadContentType;
    }

    public void setMyUploadContentType(String myUploadContentType) {
        this.myUploadContentType = myUploadContentType;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }
    
}
