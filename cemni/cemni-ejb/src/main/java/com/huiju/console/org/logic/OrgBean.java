package com.huiju.console.org.logic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.console.org.eao.OrgEaoLocal;
import com.huiju.console.org.entity.Org;
import com.huiju.console.store.eao.StoreEaoLocal;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "OrgBean")
@SuppressWarnings({ "rawtypes" })
public class OrgBean extends GenericLogicImpl<Org, Long> implements OrgRemote {
    @EJB
    private OrgEaoLocal orgEao;
    @EJB
    private StoreEaoLocal storeEao;

    @Override
    protected GenericEao<Org, Long> getGenericEao() {
        return orgEao;
    }

    @Override
    public List selOrgByParent(Integer type, Long fromOrgId) {
        return orgEao.selOrgByParent(type, fromOrgId);
    }

    @Override
    public List getOrgTreeList(String orgName) {
        return orgEao.getOrgTreeList(orgName);
    }

    @Override
    public List qryBigAreaStore_dict(Integer dictCode) {
        return orgEao.qryBigAreaStore_dict(dictCode);
    }

}