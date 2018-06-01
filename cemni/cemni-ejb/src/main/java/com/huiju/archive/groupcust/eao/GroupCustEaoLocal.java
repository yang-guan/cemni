package com.huiju.archive.groupcust.eao;

import javax.ejb.Local;

import com.huiju.archive.groupcust.entity.GroupCust;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface GroupCustEaoLocal extends GenericEao<GroupCust, Long> {
}