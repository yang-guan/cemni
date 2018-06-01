package com.huiju.salesment.yeartarget.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.salesment.yeartarget.entity.YearTarget;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface YearTargetRemote extends GenericLogic<YearTarget, Long> {

    /**
     * 修改“年度销售指标、个人业绩销售指标”
     * 
     * @param yt
     * 
     * @author：yuhb
     * @date：2017年3月15日 上午12:15:19
     */
    void updYearTargetIndividComp(YearTarget yt);

    Map excel(File file);
}