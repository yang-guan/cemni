package com.huiju.expandbusi.individcompanalyze.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.expandbusi.individcompanalyze.entity.IndividCompAnalyze;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface IndividCompAnalyzeRemote extends GenericLogic<IndividCompAnalyze, Long> {

    Map excel(File file);
}