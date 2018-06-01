package com.huiju.archive.franchisee.eao;

import javax.ejb.Local;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.archive.franchisee.entity.Franchisee;

@Local
public interface FranchiseeEaoLocal extends GenericEao<Franchisee, Long> {
}