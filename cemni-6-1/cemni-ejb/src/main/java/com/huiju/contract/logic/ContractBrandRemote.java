package com.huiju.contract.logic;

import javax.ejb.Remote;

import com.huiju.contract.entity.ContractBrand;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContractBrandRemote extends GenericLogic<ContractBrand, Long> {
}