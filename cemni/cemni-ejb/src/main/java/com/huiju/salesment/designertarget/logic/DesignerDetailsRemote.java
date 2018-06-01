package com.huiju.salesment.designertarget.logic;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.salesment.designertarget.entity.DesignerDetails;

@Remote
public interface DesignerDetailsRemote extends
		GenericLogic<DesignerDetails, Long> {
}