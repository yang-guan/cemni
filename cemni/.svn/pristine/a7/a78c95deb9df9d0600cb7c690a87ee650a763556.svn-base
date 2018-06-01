package com.huiju.console.orgext.logic;

import java.util.List;

import javax.ejb.Remote;

import com.huiju.console.orgext.entity.OrgExt;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface OrgExtRemote extends GenericLogic<OrgExt, Long> {

    void org2store(Long orgParentId, List<Long> storeIds);
}