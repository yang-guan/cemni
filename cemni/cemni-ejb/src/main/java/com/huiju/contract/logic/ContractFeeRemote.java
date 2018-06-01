package com.huiju.contract.logic;

import javax.ejb.Remote;

import com.huiju.contract.entity.ContractFee;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContractFeeRemote extends GenericLogic<ContractFee, Long>  {
	
}