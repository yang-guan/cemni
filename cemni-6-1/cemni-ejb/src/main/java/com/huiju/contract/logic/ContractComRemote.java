package com.huiju.contract.logic;

import javax.ejb.Remote;

import com.huiju.contract.entity.ContractCom;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContractComRemote extends GenericLogic<ContractCom, Long> {
}