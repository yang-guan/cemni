package com.huiju.common.File.logic;

import java.io.File;
import java.io.IOException;

import javax.ejb.Remote;

import com.huiju.common.File.entity.NFileGroup;
import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.module.fs.FileItem;
import com.huiju.module.fs.StorageType;

@Remote
public  interface NFileInfoRemote
  extends GenericLogic<NFileInfo, Long>
{
  public  NFileInfo findByCode(String paramString);
  
  public  NFileInfo upload(File paramFile)
    throws IOException;
  
  public  NFileInfo upload(File paramFile, StorageType paramStorageType)
    throws IOException;
  
  public  NFileInfo upload(String paramString, File paramFile)
    throws IOException;
  
  public  NFileInfo upload(String paramString, File paramFile, StorageType paramStorageType)
    throws IOException;
  
  public  NFileInfo upload(File paramFile, Long paramLong)
    throws IOException;
  
  public  NFileInfo upload(File paramFile, StorageType paramStorageType, Long paramLong)
    throws IOException;
  
  public  NFileInfo upload(String paramString, File paramFile, Long paramLong)
    throws IOException;
  
  public  NFileInfo upload(String paramString, File paramFile, StorageType paramStorageType, Long paramLong)
    throws IOException;
  
  public  FileItem convert(NFileInfo paramFileInfo)
    throws IOException;
  
  public  FileItem download(Long paramLong)
    throws IOException;
  
  public  FileItem download(String paramString)
    throws IOException;
  
  public  File downloadFile(Long paramLong)
    throws IOException;
  
  public  File downloadFile(String paramString)
    throws IOException;
  
  public  void group(Long paramLong, Long[] paramArrayOfLong);
  
  public  void group(NFileGroup paramFileGroup, Long... paramVarArgs);
  
  public  void group(Long paramLong, NFileInfo... paramVarArgs);
  
  public  void group(NFileGroup paramFileGroup, NFileInfo... paramVarArgs);
  
  public  void delete(String... paramVarArgs)
    throws IOException;
  
  public  void delete(Long... paramVarArgs)
    throws IOException;
  
  public  void delete(NFileInfo... paramVarArgs)
    throws IOException;
}
