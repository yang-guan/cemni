package com.huiju.common.sql.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.huiju.common.sql.entity.Sql;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface SqlRemote extends GenericLogic<Sql, Long> {

    String getCnNum(int numCode);

    /**
     * 获取序列号
     * 
     * @param seq
     *            序列名称
     * @param prefix
     *            序列前缀
     * @param dateformat
     *            如果包含日期则为对应的日期格式，如：yyyymmddhh24miss
     * @return
     * 
     * @author：yuhb
     * @date：2017年5月12日 下午4:49:31
     */
    String getSeq(String seq, String prefix, String dateformat);

    Map queryRightMaintAudit(Map searchParam);

    Map queryActivity(Map searchParam);

    Map queryContract(Map searchParam);

    Map queryIndividcust(Map searchParam);
}
