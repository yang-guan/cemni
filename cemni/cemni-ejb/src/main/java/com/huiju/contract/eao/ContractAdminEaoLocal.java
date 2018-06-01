package com.huiju.contract.eao;

import javax.ejb.Local;

import com.huiju.contract.entity.ContractAdmin;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ContractAdminEaoLocal extends GenericEao<ContractAdmin, Long> {
}