package com.huiju.console.user2org.eao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.huiju.console.user2org.entity.User2org;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings({ "rawtypes" })
public interface User2orgEaoLocal extends GenericEao<User2org, Long> {

    Map queryUser(Map searchParam);

    List qryOrgByUserId(Long userId);

    List qryOrgByUserIdAndParms(Map searchParam);
}