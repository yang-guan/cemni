package com.huiju.contract.logic;

import javax.ejb.Remote;

import com.huiju.contract.entity.ContractOp;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContractOpRemote extends GenericLogic<ContractOp, Long> {
}