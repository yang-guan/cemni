package com.huiju.expandbusi.franchiseevalue.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.expandbusi.franchiseevalue.entity.FranchiseeValue;

@Local
public interface FranchiseeValueEaoLocal extends GenericEao<FranchiseeValue, Long> {
}