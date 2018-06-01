package com.huiju.contract.eao;

import javax.ejb.Local;

import com.huiju.contract.entity.Contract;
import com.huiju.module.data.eao.GenericEao;

@Local
public interface ContractEaoLocal extends GenericEao<Contract, Long> {

}
