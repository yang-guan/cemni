package com.huiju.expandbusi.memcompanalyze.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.expandbusi.memcompanalyze.entity.MemCompAnalyze;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface MemCompAnalyzeRemote extends GenericLogic<MemCompAnalyze, Long> {

    Map excel(File file);
}