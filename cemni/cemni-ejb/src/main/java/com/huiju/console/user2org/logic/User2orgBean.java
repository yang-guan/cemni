package com.huiju.console.user2org.logic;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.console.user2org.eao.User2orgEaoLocal;
import com.huiju.console.user2org.entity.User2org;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "User2orgBean")
@SuppressWarnings({ "rawtypes" })
public class User2orgBean extends GenericLogicImpl<User2org, Long> implements User2orgRemote {
    @EJB
    private User2orgEaoLocal user2orgEao;

    @Override
    protected GenericEao<User2org, Long> getGenericEao() {
        return user2orgEao;
    }

    @Override
    public Map queryUser(Map searchParam) {
        return user2orgEao.queryUser(searchParam);
    }

    @Override
    public void updUser2org(Long userId, List<Long> orgIds) {
        user2orgEao.executeUpdate("delete from User2org where userId = ?1", userId);
        for (Long orgId : orgIds) {
            if (orgId != null) {
                User2org dt = new User2org();
                dt.setUserId(userId);
                dt.setOrgId(orgId);
                user2orgEao.persist(dt);
            }
        }
    }

    @Override
    public List qryOrgByUserId(Long userId) {
        return user2orgEao.qryOrgByUserId(userId);
    }

    @Override
    public List qryOrgByUserIdAndParms(Map searchParam) {
        return user2orgEao.qryOrgByUserIdAndParms(searchParam);
    }

}