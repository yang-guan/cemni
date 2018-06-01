package com.huiju.common.File.logic;

import java.io.Serializable;
import java.util.Calendar;

import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.context.ContextHelper;
import com.huiju.module.fs.FileStorageMetadata;
import com.huiju.module.json.Json;
import com.huiju.module.util.FileUtils;

public class NConverterToFileInfo implements  Serializable
{
    private static final long serialVersionUID = 1L;
    
    public NFileInfo convert(FileStorageMetadata t)
    {
        NFileInfo fileInfo = new NFileInfo();
      
      fileInfo.setFileName(t.getFileName());
      fileInfo.setFileExtension(FileUtils.getExtension(t.getFileName()));
      fileInfo.setStorageType(t.getStorageType());
      
      fileInfo.setCreateTime(Calendar.getInstance());
      fileInfo.setCreateUserId(ContextHelper.getUserId());
      fileInfo.setCreateUserName(ContextHelper.getUsername());
      
      fileInfo.setProperties(Json.toJson(t));
      return fileInfo;
    }
}
