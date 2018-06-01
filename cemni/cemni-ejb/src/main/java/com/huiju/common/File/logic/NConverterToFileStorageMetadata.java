package com.huiju.common.File.logic;

import java.io.Serializable;

import com.huiju.common.File.entity.NFileInfo;
import com.huiju.module.fs.FileStorageMetadata;
import com.huiju.module.fs.support.DefaultFileStorageMetadata;
import com.huiju.module.json.Json;

public class NConverterToFileStorageMetadata implements  Serializable {
    private static final long serialVersionUID = 1L;

    public FileStorageMetadata convert(NFileInfo t) {
        String properties = t.getProperties();
        return (FileStorageMetadata) Json.parse(properties, DefaultFileStorageMetadata.class);
    }
}
