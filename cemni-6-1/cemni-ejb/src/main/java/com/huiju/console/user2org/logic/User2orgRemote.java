package com.huiju.console.user2org.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.console.user2org.entity.User2org;
import com.huiju.module.data.logic.GenericLogic;

@Remote
@SuppressWarnings({ "rawtypes" })
public interface User2orgRemote extends GenericLogic<User2org, Long> {

    Map queryUser(Map searchParam);

    /**
     * 用户关联组织机构：先删后加
     * 
     * @author：yuhb
     * @date：2017年3月31日 下午8:27:40
     */
    void updUser2org(Long userId, List<Long> orgIds);

    List qryOrgByUserId(Long userId);

    List qryOrgByUserIdAndParms(Map searchParam);
}