package com.huiju.console.orgext.logic;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.huiju.common.GlobalConst;
import com.huiju.console.orgext.eao.OrgExtEaoLocal;
import com.huiju.console.orgext.entity.OrgExt;
import com.huiju.console.store.eao.StoreEaoLocal;
import com.huiju.console.store.entity.Store;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;

@Stateless(mappedName = "OrgExtBean")
public class OrgExtBean extends GenericLogicImpl<OrgExt, Long> implements OrgExtRemote {
    @EJB
    private OrgExtEaoLocal orgEao;
    @EJB
    private StoreEaoLocal storeEao;

    @Override
    protected GenericEao<OrgExt, Long> getGenericEao() {
        return orgEao;
    }

    @Override
    public void org2store(Long orgParentId, List<Long> storeIds) {
        List<Store> rsList = storeEao.findAll(storeIds);
        for (Store dt : rsList) {
            OrgExt org = new OrgExt();
            org.setStore(dt);
            org.setOrgCode(dt.getStoreNo());
            org.setName(dt.getName());
            org.setParentId(orgParentId);
            org.setType(GlobalConst.ORGEXT_STORE);
            org.setIsValid(GlobalConst.YES);
            org.setCdate(Calendar.getInstance());
            orgEao.persist(org);
        }
    }

}