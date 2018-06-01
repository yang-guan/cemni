package com.huiju.salesment.designertarget.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;

import com.huiju.module.data.logic.GenericLogic;
import com.huiju.salesment.designertarget.entity.DesignerTarget;

@Remote
public interface DesignerTargetRemote extends GenericLogic<DesignerTarget, Long> {

    @SuppressWarnings("rawtypes")
	Map excel(File file);
}