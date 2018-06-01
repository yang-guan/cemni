package com.huiju.console.org.eao;

import java.util.List;

import javax.ejb.Local;

import com.huiju.console.org.entity.Org;
import com.huiju.module.data.eao.GenericEao;

@Local
@SuppressWarnings({ "rawtypes" })
public interface OrgEaoLocal extends GenericEao<Org, Long> {

    List selOrgByParent(Integer type, Long fromOrgId);

    List getOrgTreeList(String orgName);

    List qryBigAreaStore_dict(Integer dictCode);
}